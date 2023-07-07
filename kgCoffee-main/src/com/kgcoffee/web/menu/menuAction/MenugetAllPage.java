//package com.kgcoffee.web.menu.menuAction;
//
//import java.util.ArrayList;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.kgcoffee.web.common.Paging;
//import com.kgcoffee.web.menu.menuDAO.MenuDAO;
//import com.kgcoffee.web.menu.menuVO.MenuVO;
//
//public class MenugetAllPage implements MenuImpl {
//
//	@Override
//	public void menuAction(HttpServletRequest request, HttpServletResponse response) {
//		// TODO Auto-generated method stub
//
//		response.setCharacterEncoding("UTF-8");
//
//		MenuDAO mdao;
//		try {
//			mdao = new MenuDAO();
//
//			Paging paging = new Paging();
//
//			int page = 0; 
//			int displayRow = 0;
//			int displayPage = 0;
//
//			if (request.getParameter("page") != null) {
//
//				page = Integer.parseInt(request.getParameter("page"));
//			} else {
//				page = 1;
//			}
//			if (request.getParameter("displayRow") != null) {
//
//				displayRow = Integer.parseInt(request.getParameter("displayRow"));
//			} else {
//				displayRow = 12;
//			}
//			if (request.getParameter("displayPage") != null) {
//
//				displayPage = Integer.parseInt(request.getParameter("displayPage"));
//			} else {
//				displayPage = 10;
//			}
//
//			paging.setPage(page);
//			paging.setDisplayRow(displayRow);
//
//			paging.setDisplayRow(12);
//			
//
//			int totalCount = mdao.totalCnt();
//
//			paging.setDisplayPage(displayPage);
//
//			paging.setTotalCount(totalCount);
//
//			ArrayList<MenuVO> alist = mdao.getMenuListByPage(paging.getPage(), displayRow);
//
//
//			request.setAttribute("alist", alist);
//			request.setAttribute("paging", paging);
//
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
//
//}
