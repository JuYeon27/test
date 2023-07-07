package com.kgcoffee.web.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.common.ControllerImpl;
import com.kgcoffee.web.users.dao.UsersDAO;

public class findUpdatePw implements ControllerImpl {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UsersDAO dao = new UsersDAO();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_name = request.getParameter("user_name");
		String birthday = request.getParameter("birthday");
		String tel = request.getParameter("tel");
		
		String update_msg = null;
		
		
		dao.findUpdatePw(user_pw, user_name, birthday, tel, user_id);
		
		if (dao.findUpdatePw(user_pw, user_name, birthday, tel, user_id)) {
			
			update_msg = "update-success";
			
		} else {
			
			update_msg = "update-failed";
			
		}
		
		request.setAttribute("update_msg", update_msg);
		
	}

}
