package com.kgcoffee.web.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.common.ControllerImpl;
import com.kgcoffee.web.users.dao.UsersDAO;
import com.kgcoffee.web.users.vo.UsersVO;

public class findPw implements ControllerImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		UsersDAO dao = new UsersDAO();
		
		String user_id = request.getParameter("user_id");
		String user_name = request.getParameter("user_name");
		String tel = request.getParameter("tel");
		
		UsersVO vo = dao.searchPw(user_id, user_name, tel);
		
		String findpwMag = null;
		
		if (vo != null) {
			
			System.out.println("성공");
			findpwMag = "find_pw_success";
			
		} else {
			
			System.out.println("실패");
			findpwMag = "find_pw_fail";
		}
		
		request.setAttribute("findpwMag", findpwMag);
		
		
		request.setAttribute("vo", vo);
		
	}

}
