package com.kgcoffee.web.voc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.kgcoffee.web.board.vo.SnsVO;
import com.kgcoffee.web.common.DBConn;
import com.kgcoffee.web.common.LoggableStatement;
import com.kgcoffee.web.voc.vo.VocVO;



public class VocDAO {

	private static Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 1. 스스로 객체를 1개 생성합니다.
	private static VocDAO instance;

	public VocDAO() throws ClassNotFoundException, SQLException {
		con = DBConn.getInstance().getCon();
	}
	
	public static VocDAO getInstance() throws ClassNotFoundException, SQLException {
		
		if(instance == null) {
			instance = new VocDAO();
		}
		
		return instance;
	}

	// ------------------------------------------------------------------
	// 게시글 전체보기
	/*
	 * public ArrayList<SnsVO> getAllInfo() throws SQLException { ArrayList<SnsVO>
	 * sarray = new ArrayList<SnsVO>(); String sql =
	 * "select * from mvcsnsboard order by bunho desc";
	 * 
	 * pstmt = con.prepareStatement(sql); rs = pstmt.executeQuery(); while
	 * (rs.next()) { int bunho = rs.getInt(1); String jemok = rs.getString(2);
	 * String writer = rs.getString(3); String content = rs.getString(4); Date date
	 * = rs.getDate(5); int count = rs.getInt(6);
	 * 
	 * SnsVO sv1 = new SnsVO(bunho, jemok, writer, content, date, count);
	 * 
	 * sarray.add(sv1); } return sarray; }
	 */

	// 게시글 전체보기 (+페이징)
	public ArrayList<VocVO> getAllInfo(int page) throws SQLException {

		int startNum = (page - 1) * 10 + 1;
		int endNum = page * 10;
		
		//서브쿼리볼떄는 하위쿼리부터 보면된다.
		//(1) (select * from board order by ref desc , re_step_asc)A
		// select * from board order by ref desc : ref(=글그룹)을 기준으로 내림차순해라. 최신글이 가장 위로 올라감.
		// re_step_asc : re_step(=글스텝)을 기준으로 오름차순해라. 
		// 게시글에서는 가장 늦게 쓴 글이 최신글이다. 
		// 이때 중요한것은 최신글인데(글번호는 높은데) 뒤쪽 페이지에 있는(글번호가 낮은)글의 답글일수도 있다. 이 경우, 최신글에 붙어있으면 안되고 뒤쪽 페이지의 답변글에 붙어있어야 된다.
		// 그래서 글 그룹을 디센딩 글 스텝을 어센딩으로 맞쳐준다.
		// A : 위 커리를 A 테이블로 지칭함
		//(2) Rownum Rnum from :
		// Rownum 은 오라클만 가지고 있는 컬럼명이다. 이건 우리가 만들지 않아도 자동으로 오라클에 있는 테이블을 사용하는 유저라면 무조건 만들어진다.
		//(3) where Rnum >= ? and Rnum <= ? :
		// Rnum(Rownum alias)이 start보다 크고 endrow보다 작은애에 해당하는 것을 
		//(4) select * from : 모두 가져오시오	
		String sql = "select * from (select A.* ,Rownum Rnum from (select *from vocboard order by ref desc ,re_step asc)A)where Rnum >= ? and Rnum <= ?";
		
		ArrayList<VocVO> sarray = new ArrayList<VocVO>();

		pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, startNum);
		pstmt.setInt(2, endNum);

		rs = pstmt.executeQuery();

		while (rs.next()) {
			int bunho = rs.getInt(1);
			String jemok = rs.getString(2);
			String writer = rs.getString(3);
			String content = rs.getString(4);
			Date date = rs.getDate(5);
			int count = rs.getInt(6);
			String filename = rs.getString(7);
			int ref = rs.getInt(8);
			int re_step = rs.getInt(9);
			int re_level = rs.getInt(10);
			String user_id = rs.getString(11);

			VocVO sv1 = new VocVO(bunho, jemok, writer, content, date, count,filename, ref, re_step, re_level,user_id);

			sarray.add(sv1);
		}
		return sarray;
	}

	// 페이징 처리를 위해 전체게시글의 갯수를 count
	public int getAllCount() throws SQLException {

		String sql = "select count(*) count from VOCBOARD";

		int count = 0;

		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			count = rs.getInt("count");
		}

		return count;
	}
	
	//조회수 기능
	public void upHit(String bunho) throws SQLException {
		
		String sql = "update VOCBOARD set v_cnt = v_cnt + 1 where v_bunho = ?";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bunho);
		pstmt.executeUpdate();
		
	}
	
	
	
	//답변글이 저장되는 메소드
	public boolean reInsert(String jemok, String writer, String content, String filename, int ref1, int re_step1, int re_level1, String user_id) {
		
		VocVO vo = new VocVO();
		
		///////////////////////////////////////////////////////////////
		//부모글 그룹과 글레벨 글 스텝을 읽어들임
		int ref = ref1;
		int re_step = re_step1;
		int re_level =re_level1;
		
		//부모글 보다 큰 re_level의 값을 전부 1씩 증가시켜줌
		String levelsql = "update VOCBOARD set re_level=re_level+1 where ref=? and re_level > ?";
		try {
			pstmt = con.prepareStatement(levelsql);
	
		pstmt.setInt(1, ref);
		pstmt.setInt(2, re_level);
		pstmt.executeUpdate();
		
		
		
		//답변글 데이터를 저장
		String sql = "insert into VOCBOARD values(vocboard_seq.NEXTVAL, ?, ?, ?, sysdate, 0, ?, ?, ?, ?, ?)";
		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, jemok);
		pstmt.setString(2, writer);
		pstmt.setString(3, content);
		pstmt.setString(4, filename);
		pstmt.setInt(5, ref); // 부모의 ref 값을 넣어줌
		pstmt.setInt(6, re_step + 1); //답글이기에 부모글 re_step에 1을 증가
		pstmt.setInt(7, re_level + 1); //답글이기에 부모글 re_level에 1으을 증가
		pstmt.setString(8, user_id);
					
		if(pstmt.executeUpdate()>=1) {
			
			return true;
			
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
		
	}

	
	// ------------------------------------------------------------------------------------------------------

	public boolean insert(String jemok, String writer, String content, String filename, String user_id) {
		
		
		int ref = 0; // 글 그룹을 의미 = 퀴리를 실행시켜 가장 큰 ref값을 가져온 후 +1을 더해주면 된다
		int re_step = 1; // 새글이기에 = 부모 글 이기에
		int re_level = 1;
	
		String refSQL = "select MAX(ref) from VOCBOARD";
		try {
			pstmt = con.prepareStatement(refSQL);
	
		rs = pstmt.executeQuery();
		if(rs.next()) { //결과 값이 있다면
			ref = rs.getInt(1)+1; //최대 값에 +1을 더해서 글 그룹을 설정
		}
		//실제로 게시글 전체값을 테이블에 저장	
		
		System.out.println(ref);
		String sql = "insert into VOCBOARD"
				+ " values (vocboard_seq.NEXTVAL, ?, ?, ?,sysdate,0,?,?,?,?,?)";

		pstmt = new LoggableStatement(con,sql);
	
		pstmt.setString(1, jemok);
		pstmt.setString(2, writer);
		pstmt.setString(3, content);
		pstmt.setString(4, filename);
		pstmt.setInt(5, ref);
		pstmt.setInt(6, re_step);
		pstmt.setInt(7, re_level);
		pstmt.setString(8, user_id);
		
		System.out.println(((LoggableStatement)pstmt).getQueryString());

		
		if(pstmt.executeUpdate()>=1) {
			
			return true;
			
		}
		
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	// ----------------------------------------------------------------------
	
	//하나의 게시글을 읽어들이는 메소드
	public VocVO searchOne(int bunho1) throws SQLException {
		
		
		VocVO sv1 = null;
		String sql = "select * from VOCBOARD where v_bunho = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bunho1);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			int bunho = rs.getInt(1);
			String jemok = rs.getString(2);
			String writer = rs.getString(3);
			String content = rs.getString(4);
			Date date = rs.getDate(5);
			int count = rs.getInt(6);
			String filename = rs.getString(7);
			int ref = rs.getInt(8);
			int re_step = rs.getInt(9);
			int re_level = rs.getInt(10);
			String user_id = rs.getString(11);

			sv1 = new VocVO(bunho, jemok, writer, content, date, count, filename, ref, re_step, re_level, user_id);
		} else {
			sv1 = null;
		}
		return sv1;
		
	}
	
	public VocVO searchOneUpdate(int bunho1) {
		
		VocVO sv1 = null;
		String sql = "select v_writer from vocboard where v_bunho=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, bunho1);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				String writer = rs.getString(1);
				sv1 = new VocVO(writer);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return sv1;
	}
	

	//하나의 게시글을 수정하는 메소드
	public boolean update(String jemok1, String writer1, String content1, int bunho1, String filename) throws SQLException {
		String sql = "update VOCBOARD set v_jemok=?, v_writer=?, v_content=?, v_filename=? where v_bunho=?";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, jemok1);
		pstmt.setString(2, writer1);
		pstmt.setString(3, content1);
		pstmt.setString(4, filename);
		pstmt.setInt(5, bunho1);
		pstmt.executeUpdate();
		return true;
	}

	
	//하나의 게시글을 삭제하는 메소드
	public boolean delete(int bunho1) throws SQLException {
		String sql = "delete from VOCBOARD where v_bunho=?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bunho1);
		pstmt.executeUpdate();
		System.out.println(bunho1 + "번 게시글삭제완료");
		return true;
	}

	// 게시판 검색했을때 검색되는 게시글의 카운트만 불러오는 메소드
	public int searchCount(String searchKeywordType, String searchKeyword) {
	    int count = 0;

	    String countSql = "SELECT COUNT(*) AS count FROM VOCBOARD WHERE ";

	    if (searchKeywordType.equals("jemok")) {
	        countSql += "v_jemok LIKE ?";
	    } else if (searchKeywordType.equals("writer")) {
	        countSql += "v_writer LIKE ?";
	    } else if (searchKeywordType.equals("content")) {
	        countSql += "v_content LIKE ?";
	    }

	    try (PreparedStatement pstmt = con.prepareStatement(countSql)) {
	        pstmt.setString(1, "%" + searchKeyword + "%");

	        try (ResultSet rs = pstmt.executeQuery()) {
	            if (rs.next()) {
	                count = rs.getInt("count");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return count;
	}

	
	
	
	public int searchCount3(String searchKeywordType, String searchKeyword) {

		int count = 0;

		String countSql = "SELECT COUNT(*) AS count FROM VOCBOARD WHERE ";

		if (searchKeywordType.equals("jemok")) {
			countSql += "v_jemok LIKE ?";
		} else if (searchKeywordType.equals("writer")) {
			countSql += "v_writer LIKE ?";
		} else if (searchKeywordType.equals("content")) {
			countSql += "v_content LIKE ?";
		}


		try {
			pstmt = con.prepareStatement(countSql);
			pstmt.setString(1, "%" + searchKeyword + "%");

			rs = pstmt.executeQuery();

			if (rs.next()) {
				count = rs.getInt("count");
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	// 검색기능 + 페이징 
	public ArrayList<VocVO> searchBoard1(String searchKeywordType, String searchKeyword, int page) {

		ArrayList<VocVO> list = new ArrayList<VocVO>();

		int startNum = (page - 1) * 10 + 1;
		int endNum = page * 10;

		String sql = "SELECT * FROM (" 
					+ "  SELECT A.*, ROWNUM Rnum FROM (" 
					+ "  SELECT * FROM vocboard WHERE ";

		if (searchKeywordType.equals("jemok")) {
			sql += "v_jemok LIKE ?";
		} else if (searchKeywordType.equals("writer")) {
			sql += "v_writer LIKE ?";
		} else if (searchKeywordType.equals("content")) {
			sql += "v_content LIKE ?";
		}

		sql += " ORDER BY ref DESC, re_step ASC" 
			+ "  ) A" 
			+ ") WHERE Rnum >= ? AND Rnum <= ?";


		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int bunho = rs.getInt(1);
				String jemok = rs.getString(2);
				String writer = rs.getString(3);
				String content = rs.getString(4);
				Date date = rs.getDate(5);
				int count = rs.getInt(6);
				String filename = rs.getString(7);
				int ref = rs.getInt(8);
				int re_step = rs.getInt(9);
				int re_level = rs.getInt(10);
				String user_id = rs.getString(11);

				VocVO vvo = new VocVO(bunho, jemok, writer, content, date, count, filename,ref,re_step,re_level,user_id);
				list.add(vvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
}


}
