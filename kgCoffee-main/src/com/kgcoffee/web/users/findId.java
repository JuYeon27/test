package com.kgcoffee.web.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.common.ControllerImpl;
import com.kgcoffee.web.users.dao.UsersDAO;

public class findId implements ControllerImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UsersDAO dao = new UsersDAO();
		
		String user_name = request.getParameter("user_name");
		String tel = request.getParameter("tel");
		
		String user_id = dao.searchId(user_name, tel);
		
		String msg = null;
		
		if (user_id != null) {
			
			System.out.println("성공");
			msg = "find_id_success";
			
		} else {
			
			System.out.println("실패");
			msg = "find_id_fail";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("user_name", user_name);
		request.setAttribute("user_id", user_id);

	}

}
