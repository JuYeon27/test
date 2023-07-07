package com.kgcoffee.web.users;

import java.sql.SQLException;

import com.kgcoffee.web.users.dao.UsersDAO;
import com.kgcoffee.web.users.vo.UsersVO;


public class UserService {
	
	public UsersVO getLoginUser(String user_id, String user_pw) throws ClassNotFoundException, SQLException {
		
		UsersDAO dao = UsersDAO.getInstance();
		
		UsersVO loginUser = dao.select_login(user_id, user_pw);
		
		return loginUser;
	}
	
}
