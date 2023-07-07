package com.kgcoffee.web.users;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.users.dao.UsersDAO;
import com.kgcoffee.web.users.vo.UsersVO;

public class UserInsert implements com.kgcoffee.web.common.ControllerImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_name = request.getParameter("user_name");
		String birthday = request.getParameter("birthday");
		String tel = request.getParameter("tel");
		
		UsersVO uservo = new UsersVO();
		
		UsersDAO dao = new UsersDAO();
		
		String msg=null;
		
		//여기서 아이디 중복 검사를 해서 insert_user()를 실행하면 됨
		if (dao.idChk(user_id)) {
			
			dao.insert_user(user_id, user_pw, user_name, birthday, tel);
			
			msg= "join-success";
			
		} else {
			
			msg="join-failed";
			
		}
		
		request.setAttribute("msg", msg);
		
	}

}
