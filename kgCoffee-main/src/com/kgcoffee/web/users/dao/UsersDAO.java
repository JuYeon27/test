package com.kgcoffee.web.users.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kgcoffee.web.common.DBConn;
import com.kgcoffee.web.users.vo.UsersVO;

public class UsersDAO {

	private static Connection con = null;

	private static UsersDAO userDAO;

	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public UsersDAO() throws ClassNotFoundException, SQLException {
		try {
			con = DBConn.getInstance().getCon();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static UsersDAO getInstance() throws ClassNotFoundException, SQLException {

		con = DBConn.getInstance().getCon();

		if (userDAO == null) {
			userDAO = new UsersDAO();
		}

		return userDAO;
	}

	// 회원가입 ================================================
	public boolean insert_user(String user_id, String user_pw, String user_name, String birthday, String tel) {

		String sql = "insert into users values(?,?,?,?,?)";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pw);
			pstmt.setString(3, user_name);
			pstmt.setString(4, birthday);
			pstmt.setString(5, tel);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Insert Exception");
			return false;
		}

		return true;
	}

	// 아이디 중복 체크 => false 중복됨
	public boolean idChk(String user_id) {
		boolean result = false;

		String sql = "SELECT * FROM users where user_id=?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, user_id);

			rs = pstmt.executeQuery();

			if (!rs.next())
				result = true;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("idChk Exception");
		}

		return result;
	}

	// 로그인 ================================================
	public UsersVO select_login(String user_id, String user_pw) {

		UsersVO loginUser = null;

		String sql = "SELECT * FROM users where user_id=? And user_pw=?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, user_id);
			pstmt.setString(2, user_pw);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				loginUser = new UsersVO();
				loginUser.setUser_id(rs.getString("user_id"));
				loginUser.setUser_pw(rs.getString("user_pw"));
				loginUser.setUser_name(rs.getString("user_name"));
				loginUser.setBirthday(rs.getDate("birthday"));
				loginUser.setTel(rs.getString("tel"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("select_login dao Exception");
		}

		return loginUser;
	}

	// 회원 아이디 찾기
	public String searchId(String user_name, String tel) {

		String result_id = null;

		String sql = "SELECT user_id FROM users where user_name=? and tel=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, tel);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				result_id = rs.getString("user_id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("searchId dao Exception");
		}

		return result_id;
	}

	
	public UsersVO searchPw(String user_id2, String user_name2, String tel2) {

		UsersVO vo = null;

		String sql = "SELECT * FROM users where user_id=? and user_name=? and tel=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id2);
			pstmt.setString(2, user_name2);
			pstmt.setString(3, tel2);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String user_id = rs.getString("user_id");
				String user_pw = rs.getString("user_pw");
				String user_name = rs.getString("user_name");
				Date birthday = rs.getDate("birthday");
				String tel = rs.getString("tel");

				vo = new UsersVO(user_id, user_pw, user_name, birthday, tel);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("searchPw dao Exception");
		}

		return vo;
	}

	
	public boolean findUpdatePw(String user_pw, String user_name, String birthday, String tel, String user_id) {

		String sql = "update users set user_pw=?, user_name=?, birthday=?, tel=? where user_id=?";

		try {
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, user_pw);
			pstmt.setString(2, user_name);
			pstmt.setString(3, birthday);
			pstmt.setString(4, tel);
			pstmt.setString(5, user_id);
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

}
