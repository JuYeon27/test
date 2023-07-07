package com.kgcoffee.web.users;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kgcoffee.web.users.vo.UsersVO;

public class UserLogin implements com.kgcoffee.web.common.ControllerImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String msg = null; //로그인 완료 했을때 보여줄 alert 메세지
		int result = 0; // 로그인X = 0, 관리자 1, 일반회원 2
		
		HttpSession session = request.getSession();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		
		UserService userService = new UserService();
		UsersVO loginUser = userService.getLoginUser(user_id, user_pw);
		
		//로그인이 성공되면 UsersVO 객체가 넘어오고 실패하면 null이 넘어옴
		if (loginUser != null) {
			
			session.setAttribute("loginUser", loginUser);
			
			if (user_id.equals("admin")) {

				result = 1; //관리자
				
				session.setAttribute("result", result);
				
			} else {
				
				result = 2; //일반회원
				
				session.setAttribute("result", result);
				
			}
			
			msg= "reg-success";
			
		} else {
			
			result = 0; 
			
			session.setAttribute("result", result);
			
			msg= "reg-failed";
			
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("loginUser", loginUser);
		
	}
	
}
