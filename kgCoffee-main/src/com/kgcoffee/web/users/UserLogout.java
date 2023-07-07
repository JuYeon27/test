package com.kgcoffee.web.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserLogout implements com.kgcoffee.web.common.ControllerImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		session.invalidate();
		
		String msg = null;
		
		msg = "logout";
				
		request.setAttribute("msg", msg);
		
	}
	
}
