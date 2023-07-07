package com.kgcoffee.web.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.kgcoffee.web.board.vo.SnsVO;
import com.kgcoffee.web.common.DBConn;

public class SnsDAO {

	private static Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	// 1. 스스로 객체를 1개 생성합니다.
	private static SnsDAO instance;

	public SnsDAO() throws ClassNotFoundException, SQLException {
		con = DBConn.getInstance().getCon();
	}

	public static SnsDAO getInstance() throws ClassNotFoundException, SQLException {

		con = DBConn.getInstance().getCon();

		if (instance == null) {
			instance = new SnsDAO();
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
	public ArrayList<SnsVO> getAllInfo(int page) throws SQLException {

		int startNum = (page - 1) * 10 + 1;
		int endNum = page * 10;

		String sql = "SELECT * FROM (" 
				+ " SELECT MVCSNSBOARD.*, ROWNUM row_num FROM ("
				+ " SELECT * FROM MVCSNSBOARD order by bunho desc) MVCSNSBOARD"
				+ " ) WHERE row_num >= ? AND row_num <= ?";

		ArrayList<SnsVO> sarray = new ArrayList<SnsVO>();

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

			SnsVO sv1 = new SnsVO(bunho, jemok, writer, content, date, count, filename);

			sarray.add(sv1);
		}
		return sarray;
	}

	// 게시글의 갯수를 count
	public int getAllCount() throws SQLException {

		String sql = "select count(*) count from MVCSNSBOARD";

		int count = 0;

		pstmt = con.prepareStatement(sql);
		rs = pstmt.executeQuery();

		if (rs.next()) {
			count = rs.getInt("count");
		}

		return count;
	}

	// 조회수 기능
	public void upHit(String bunho) throws SQLException {

		String sql = "update MVCSNSBOARD set snscnt = snscnt + 1 where bunho = ?";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, bunho);
		pstmt.executeUpdate();

	}

	// ----------------------------------------------------------------------

	public boolean insert(String jemok, String writer, String content, String filename, String user_id) throws SQLException {
		String sql = "insert into MVCSNSBOARD" 
					+ " values (mvcsnsboard_sequence1.NEXTVAL, ?, ?, ?,sysdate,0,?,?)";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, jemok);
		pstmt.setString(2, writer);
		pstmt.setString(3, content);
		pstmt.setString(4, filename);
		pstmt.setString(5, user_id);

		pstmt.executeUpdate();
		return true;
	}

	// ----------------------------------------------------------------------

	public SnsVO searchOne(int bunho2) throws SQLException {

		SnsVO sv1 = null;
		String sql = "select * from MVCSNSBOARD where bunho = ?";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bunho2);
		rs = pstmt.executeQuery();
		if (rs.next()) {
			int bunho = rs.getInt(1);
			String jemok = rs.getString(2);
			String writer = rs.getString(3);
			String content = rs.getString(4);
			Date date = rs.getDate(5);
			int count = rs.getInt(6);
			String filename = rs.getString(7);

			sv1 = new SnsVO(bunho, jemok, writer, content, date, count, filename);
		} else {
			sv1 = null;
		}
		return sv1;
	}

	public boolean update(String jemok1, String writer1, String content1, int bunho1, String filename)
			throws SQLException {
		String sql = "update MVCSNSBOARD set snsjemok=?, snswriter=?, snscontent=?, filename=? where bunho=?";

		pstmt = con.prepareStatement(sql);
		pstmt.setString(1, jemok1);
		pstmt.setString(2, writer1);
		pstmt.setString(3, content1);
		pstmt.setString(4, filename);
		pstmt.setInt(5, bunho1);
		pstmt.executeUpdate();
		
		return true;
	}

	public boolean delete(int bunho1) throws SQLException {
		String sql = "delete from MVCSNSBOARD where bunho=?";

		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, bunho1);
		pstmt.executeUpdate();
		
		return true;
	}

	// 게시판 검색했을때 검색되는 게시글의 카운트만 불러오는 메소드
	public int searchCount(String searchKeywordType, String searchKeyword) {

		int count = 0;

		String countSql = "SELECT COUNT(*) AS count FROM MVCSNSBOARD WHERE ";

		if (searchKeywordType.equals("jemok")) {
			countSql += "snsjemok LIKE ?";
		} else if (searchKeywordType.equals("writer")) {
			countSql += "snswriter LIKE ?";
		} else if (searchKeywordType.equals("content")) {
			countSql += "snscontent LIKE ?";
		}

		/*
		 * countSql += " ORDER BY bunho DESC) MVCSNSBOARD" +
		 * " ) WHERE row_num >= ? AND row_num <= ?";
		 */

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
	public ArrayList<SnsVO> searchBoard1(String searchKeywordType, String searchKeyword, int page) {

		ArrayList<SnsVO> list = new ArrayList<SnsVO>();

		int startNum = (page - 1) * 10 + 1;
		int endNum = page * 10;

		String sql = "SELECT * FROM (" 
				+ " SELECT MVCSNSBOARD.*, ROWNUM row_num FROM ("
				+ " SELECT * FROM MVCSNSBOARD WHERE ";

		if (searchKeywordType.equals("jemok")) {
			sql += "snsjemok LIKE ?";
		} else if (searchKeywordType.equals("writer")) {
			sql += "snswriter LIKE ?";
		} else if (searchKeywordType.equals("content")) {
			sql += "snscontent LIKE ?";
		}

		sql += " ORDER BY bunho DESC) MVCSNSBOARD" 
			+ " ) WHERE row_num >= ? AND row_num <= ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			rs = pstmt.executeQuery();

			// 검색 결과를 SnsVO 객체로 변환하여 리스트에 추가
			while (rs.next()) {
				int bunho = rs.getInt(1);
				String jemok = rs.getString(2);
				String writer = rs.getString(3);
				String content = rs.getString(4);
				Date date = rs.getDate(5);
				int count = rs.getInt(6);
				String filename = rs.getString(7);

				SnsVO sns = new SnsVO(bunho, jemok, writer, content, date, count, filename);
				list.add(sns);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
}
	
	
	//이전글
	public SnsVO getPrevious(int currentbunho) {
		SnsVO sns=null;

		
		// 이전글 조회 로직 구현
		String sql = "SELECT * FROM mvcsnsboard WHERE bunho IN (SELECT MAX(bunho) FROM mvcsnsboard WHERE bunho < ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, currentbunho);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int bunho = rs.getInt(1);
				String jemok = rs.getString(2);
				String writer = rs.getString(3);
				String content = rs.getString(4);
				Date date = rs.getDate(5);
				int count = rs.getInt(6);
				String filename = rs.getString(7);
				
				sns = new SnsVO(bunho, jemok, writer, content, date, count, filename);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return sns;
	
	}

	//다음글
	public SnsVO getNext(int currentbunho) {
		
		SnsVO sns=null;
		// 다음글 조회 로직 구현
		String sql = "SELECT * FROM mvcsnsboard WHERE bunho IN (SELECT MIN(bunho) FROM mvcsnsboard WHERE bunho > ?)";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, currentbunho);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int bunho = rs.getInt(1);
				String jemok = rs.getString(2);
				String writer = rs.getString(3);
				String content = rs.getString(4);
				Date date = rs.getDate(5);
				int count = rs.getInt(6);
				String filename = rs.getString(7);
				
				sns = new SnsVO(bunho, jemok, writer, content, date, count, filename);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		return sns;
	
	}
	 

}
