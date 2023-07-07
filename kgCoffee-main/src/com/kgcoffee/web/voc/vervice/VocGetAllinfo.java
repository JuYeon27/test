package com.kgcoffee.web.voc.vervice;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.voc.dao.VocDAO;
import com.kgcoffee.web.voc.vo.Paging;
import com.kgcoffee.web.voc.vo.VocVO;

public class VocGetAllinfo implements VocImpli {

	@Override
	public void haeva(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		VocDAO sdao1 = new VocDAO();
		
		//페이지 번호와 게시글의 총 개수(count)를 초기화
		int page = 1;
		int count = sdao1.getAllCount();
		
		//파라미터에서 "page"값을 가져와서 페이지번호 (page)변수에 할당.
		if(request.getParameter("page") != null) { 
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		Paging paging = new Paging();
		paging.setPage(page);
		paging.setTotalCount(count);
		
		ArrayList<VocVO> alist1 = sdao1.getAllInfo(page);
		
		
		request.setAttribute("alist1", alist1);
		request.setAttribute("paging", paging);
		
	}
	
}
