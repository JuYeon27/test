package com.kgcoffee.web.board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.board.dao.SnsDAO;
import com.kgcoffee.web.board.vo.Paging;
import com.kgcoffee.web.board.vo.SnsVO;

public class HaevaGetAllinfo implements com.kgcoffee.web.common.ControllerImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		SnsDAO sdao1 = new SnsDAO();
		
		int page = 1;
		int count = sdao1.getAllCount();
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		Paging paging = new Paging();
		paging.setPage(page);
		paging.setTotalCount(count);
		
		ArrayList<SnsVO> alist1 = sdao1.getAllInfo(page);
		
		
		request.setAttribute("alist1", alist1);
		request.setAttribute("paging", paging);
		
	}
	
}
