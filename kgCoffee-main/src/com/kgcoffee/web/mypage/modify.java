package com.kgcoffee.web.mypage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.common.ControllerImpl;
import com.kgcoffee.web.mypage.dao.MypageDAO;
import com.kgcoffee.web.users.vo.UsersVO;

public class modify implements ControllerImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		MypageDAO dao = new MypageDAO();
		
		String user_id = request.getParameter("user_id");
		
		UsersVO vo = dao.searchUser(user_id);
		
		request.setAttribute("vo", vo);
		
	}

}
