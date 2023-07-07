package com.kgcoffee.web.voc.vervice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.voc.dao.VocDAO;

public class VocDelete implements VocImpli{

	@Override
	public void haeva(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//요청과 응답의 인코딩을 UTF-8로 설정
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		//SnsDAO 객체선언하고 초기화
		VocDAO sdao = null;
		
		//파라미터에서 bunho값을 가져와서 정수로 변환
		int bunho = Integer.parseInt(request.getParameter("bunho"));
		
		//SnsDAO 객체를 사용하여 delete()메서드를 호출하여 게시글 삭제
		try {
		sdao = new VocDAO();
		sdao.delete(bunho);//bunho를 매개변수로 전달해서 해당 번호 게시글을 삭제
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
