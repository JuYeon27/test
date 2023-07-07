package com.kgcoffee.web.board.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.board.dao.SnsDAO;



public class HaevaDelete implements com.kgcoffee.web.common.ControllerImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		SnsDAO sdao = null;
		
		int bunho = Integer.parseInt(request.getParameter("bunho"));
		
		try {
		sdao = new SnsDAO();
		sdao.delete(bunho);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
