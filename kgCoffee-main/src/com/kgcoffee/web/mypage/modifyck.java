package com.kgcoffee.web.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.common.ControllerImpl;
import com.kgcoffee.web.mypage.dao.MypageDAO;

public class modifyck implements ControllerImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		String user_pw = request.getParameter("user_pw");
		String user_id = request.getParameter("user_id");

		MypageDAO dao = new MypageDAO();

		boolean pw_check = dao.modifyck(user_pw, user_id);
		
		String msg = null;
		
		if (pw_check) {
			
			System.out.println("성공");
			msg = "pw_success";
			
		} else {
			
			System.out.println("실패");
			msg = "pw_fail";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("pw_check", pw_check);
		
	}

}
