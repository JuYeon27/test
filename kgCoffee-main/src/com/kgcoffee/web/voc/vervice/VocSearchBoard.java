package com.kgcoffee.web.voc.vervice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.voc.dao.VocDAO;
import com.kgcoffee.web.voc.vo.Paging;
import com.kgcoffee.web.voc.vo.VocVO;

public class VocSearchBoard implements VocImpli {

	@Override
	public void haeva(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		VocDAO vdo = new VocDAO();

		String searchKeywordType = request.getParameter("searchKeywordType");
		String searchKeyword = request.getParameter("searchKeyword");

		
		System.out.println("searchKeywordType :::::::::"+request.getParameter("searchKeywordType"));
		System.out.println("searchKeyword :::::::::"+request.getParameter("searchKeyword"));

		
		int page = 1;
		if(request.getParameter("page") != null) {
		    page = Integer.parseInt(request.getParameter("page"));
		}

		
		int count = vdo.searchCount(searchKeywordType, searchKeyword);

		
		Paging paging = new Paging(); 
		paging.setPage(page);
		paging.setTotalCount(count);

		
		ArrayList<VocVO> list = vdo.searchBoard1(searchKeywordType, searchKeyword, page);

		
		request.setAttribute("list", list); 
		request.setAttribute("paging", paging);
		request.setAttribute("searchKeywordType", searchKeywordType);
		request.setAttribute("searchKeyword", searchKeyword);
		
	}


}
