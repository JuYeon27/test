package com.kgcoffee.web.mypage.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.kgcoffee.web.common.DBConn;
import com.kgcoffee.web.users.vo.UsersVO;

public class MypageDAO {
	
	private static Connection con = null;
	
	private static MypageDAO mypageDAO;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public MypageDAO() throws ClassNotFoundException, SQLException {
		try {
			con = DBConn.getInstance().getCon();
			}catch(Exception e) {
				e.printStackTrace();
			}									
	}
	
	public static MypageDAO getInstance() throws ClassNotFoundException, SQLException {
		
		con = DBConn.getInstance().getCon();
		
		if(mypageDAO == null) {
			mypageDAO = new MypageDAO();
		}
		
		return mypageDAO;
	}
	
	
	//비밀번호가 맞는지 확인
	public boolean modifyck(String user_pw, String user_id) {
		
		String db_pw = null;
		String user_pw1 = user_pw;
		
		String sql = "SELECT user_pw FROM users where user_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, user_id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				db_pw = rs.getString("user_pw");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("modifyck dao Exception");
		}
		
		if(user_pw1.equals(db_pw)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	//아이디를 받아와서 해당 회원의 정보를 불러오기
	public UsersVO searchUser(String user_id2) {
		
		UsersVO vo = null;
		
		String sql = "SELECT * FROM users where user_id=?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id2);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String user_id = rs.getString("user_id");
				String user_pw = rs.getString("user_pw");
				String user_name = rs.getString("user_name");
				Date birthday = rs.getDate("birthday");
				String tel = rs.getString("tel");
				
				vo = new UsersVO(user_id, user_pw, user_name, birthday, tel);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("searchUser dao Exception");
		}
		
		return vo;
	}
	
	
	public boolean userUpdate(String user_pw, String user_name, String birthday, String tel, String user_id) {
		
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
