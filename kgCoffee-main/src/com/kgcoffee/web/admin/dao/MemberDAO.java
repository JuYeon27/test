package com.kgcoffee.web.admin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.kgcoffee.web.common.DBConn;
import com.kgcoffee.web.common.LoggableStatement;
import com.kgcoffee.web.users.vo.UsersVO;

public class MemberDAO {

	private Connection con;

	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public MemberDAO() {

		try {
			con = DBConn.getInstance().getCon();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 회원 게시글의 갯수를 count
	public int getAllMemeberCount() {

		String sql = "select count(*) count from users";

		int count = 0;

		try {
			pstmt = con.prepareStatement(sql);

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

	// 회원 전체 불러오기 + 페이징
	public ArrayList<UsersVO> getAllMember(int page) {

		int startNum = (page - 1) * 10 + 1;
		int endNum = page * 10;

		ArrayList<UsersVO> list = new ArrayList<UsersVO>();

		String sql = "SELECT * FROM (" + "SELECT users.*, ROWNUM row_num FROM ("
				+ "SELECT * FROM users ORDER BY user_name DESC) users" + ") WHERE row_num >= ? AND row_num <= ?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setInt(1, startNum);
			pstmt.setInt(2, endNum);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				String user_id = rs.getString(1);
				String user_pw = rs.getString(2);
				String user_name = rs.getString(3);
				Date birthday = rs.getDate(4);
				String tel = rs.getString(5);

				UsersVO users = new UsersVO(user_id, user_pw, user_name, birthday, tel);

				list.add(users);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	// 아이디로 회원 한명 정보 불러오기
	public UsersVO getOneMember(String user_id1) {

		UsersVO uv1 = null;
		String sql = "select * from users where user_id = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id1);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				String user_id = rs.getString(1);
				String user_pw = rs.getString(2);
				String user_name = rs.getString(3);
				Date birthday = rs.getDate(4);
				String tel = rs.getString(5);

				uv1 = new UsersVO(user_id, user_pw, user_name, birthday, tel);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			uv1 = null;
		}

		return uv1;

	}

	// 수정 메소드
	public boolean update(String user_id, String user_pw, String user_name, String birthday, String tel) {

		String sql = "update users set user_pw=?, user_name=? , birthday=? , tel=? where user_id=?";

		try {

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_pw);
			pstmt.setString(2, user_name);
			pstmt.setString(3, birthday);
			pstmt.setString(4, tel);
			pstmt.setString(5, user_id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("dao update 에러");
		}

		return true;
	}

	// 삭제 메소드
	public boolean delete(String deleteId) {
		// TODO Auto-generated method stub
		boolean result = false;
		String usersql = "delete from users where user_id=?";

		if (!deleteId.equals("admin")) {

			try {
				pstmt = con.prepareStatement(usersql);
				pstmt.setString(1, deleteId);
				if (pstmt.executeUpdate() >= 1) {
					result = true;
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return result;
	}

	// 검색 카운팅
	public int searchCount(String searchKeywordType, String searchKeyword) {

		int count = 0;

		String sql = "SELECT COUNT(*) AS count FROM Users WHERE " + searchKeywordType + " like ?";

		try {
			pstmt = new LoggableStatement(con, sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			System.out.println(((LoggableStatement)pstmt).getQueryString());
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

	// 검색 + 페이징
	public ArrayList<UsersVO> memberSearch(String searchKeywordType, String searchKeyword, int page) {

		ArrayList<UsersVO> list = new ArrayList<UsersVO>();

		int startNum = (page - 1) * 10 + 1;
		int endNum = page * 10;

		String sql = "SELECT * FROM (" + " SELECT USERS.*, ROWNUM row_num FROM (" + " SELECT * FROM USERS WHERE "
				+ searchKeywordType + " like ?";

		sql += " ORDER BY user_name DESC) USERS" + " ) WHERE row_num >= ? AND row_num <= ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + searchKeyword + "%");
			pstmt.setInt(2, startNum);
			pstmt.setInt(3, endNum);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String user_id = rs.getString(1);
				String user_pw = rs.getString(2);
				String user_name = rs.getString(3);
				Date birthday = rs.getDate(4);
				String tel = rs.getString(5);

				UsersVO uvo = new UsersVO(user_id, user_pw, user_name, birthday, tel);
				list.add(uvo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
