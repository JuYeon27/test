package com.kgcoffee.web.board.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kgcoffee.web.board.dao.SnsDAO;
import com.kgcoffee.web.board.vo.Paging;
import com.kgcoffee.web.board.vo.SnsVO;

public class HaevaSearchBoard implements com.kgcoffee.web.common.ControllerImpl{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		
		SnsDAO sns = new SnsDAO();

		String searchKeywordType = request.getParameter("searchKeywordType");
		String searchKeyword = request.getParameter("searchKeyword");

		
		System.out.println("searchKeywordType :::::::::"+request.getParameter("searchKeywordType"));
		System.out.println("searchKeyword :::::::::"+request.getParameter("searchKeyword"));

		
		int page = 1;
		if(request.getParameter("page") != null) {
		    page = Integer.parseInt(request.getParameter("page"));
		}

		
		int count = sns.searchCount(searchKeywordType, searchKeyword);

		
		Paging paging = new Paging(); 
		paging.setPage(page);
		paging.setTotalCount(count);

		
		ArrayList<SnsVO> list = sns.searchBoard1(searchKeywordType, searchKeyword, page);

		
		request.setAttribute("list", list); 
		request.setAttribute("paging", paging);
		request.setAttribute("searchKeywordType", searchKeywordType);
		request.setAttribute("searchKeyword", searchKeyword);

	}

}
