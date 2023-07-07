package com.kgcoffee.web.voc.vervice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kgcoffee.web.users.vo.UsersVO;
import com.kgcoffee.web.voc.dao.VocDAO;
import com.kgcoffee.web.voc.vo.VocVO;

public class VocSearchOneUpdate implements VocImpli {

	@Override
	public void haeva(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		VocDAO sdao1 = new VocDAO();
		int bunho = Integer.parseInt(request.getParameter("bunho"));

		String voc_id = (String) request.getParameter("voc_id");

		HttpSession session = request.getSession();
		UsersVO loginUser = ((UsersVO) session.getAttribute("loginUser"));
		String user_id = "";

		if (loginUser != null) {
			user_id = loginUser.getUser_id();

		}

		VocVO sv1 = sdao1.searchOneUpdate(bunho);

		int result = 0;

		if (user_id.equals(voc_id)) {
			// 글쓴이와 로그인한 사용자의 이름이 같을때
			// 모든 권한(수정, 삭제)
			result = 1;
		} else if (user_id.equals("admin")) {
			// 관리자
			// 모든 권한(답글, 수정, 삭제)
			result = 2;
		} else {
			// 글쓴이와 로그인한 사용자의 이름이 다를때
			// 답글쓰기만 가능
			result = 3;
		}

		request.setAttribute("result", result);

	}

}
