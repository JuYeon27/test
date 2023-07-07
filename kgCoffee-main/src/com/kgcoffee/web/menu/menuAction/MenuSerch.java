package com.kgcoffee.web.menu.menuAction;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.common.Paging;
import com.kgcoffee.web.menu.menuDAO.MenuDAO;
import com.kgcoffee.web.menu.menuVO.MenuVO;

public class MenuSerch implements MenuImpl {

	@Override
	public void menuAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub

		response.setCharacterEncoding("UTF-8");
		
		
		MenuDAO mdao;
		try {
			mdao = new MenuDAO();

			int page = 0;
			int displayRow = 0;
			int displayPage = 0;

			String menuAll = request.getParameter("menuAll");
			String menuType = request.getParameter("menuType");
			String caffeineType = request.getParameter("caffeineType");
			String menuName = "";
			Paging paging = new Paging();

			if (caffeineType == null) {
				caffeineType = "";
			}

			if (menuType == null) {
				menuType = "";
			}
			
			if(menuAll == null) {
				if((boolean) request.getAttribute("chk")) {
					menuAll = "All";
					
					}else {
						menuAll="";
					}
				}
			
			if( request.getParameter("menuName") != null) {
				
					menuName=request.getParameter("menuName");
					request.setAttribute("menuName", menuName);
					
			}
			
			System.out.println(menuName);
			
			
			

			if (request.getParameter("page") != null) {

				page = Integer.parseInt(request.getParameter("page"));
			} else {
				page = 1;
			}
			if (request.getParameter("displayRow") != null) {

				displayRow = Integer.parseInt(request.getParameter("displayRow"));
			} else {
				displayRow = 12;
			}
			if (request.getParameter("displayPage") != null) {

				displayPage = Integer.parseInt(request.getParameter("displayPage"));
			} else {
				displayPage = 10;
			}

			ArrayList<MenuVO> alist = mdao.getInfoMenu(menuAll, caffeineType, menuType, menuName, page, displayRow);

			int totalCount = mdao.totalCnt(menuAll, caffeineType, menuType, menuName);
			
			paging.setPage(page);
			
			paging.setDisplayRow(displayRow);

			paging.setDisplayPage(displayPage);

			paging.setTotalCount(totalCount);
			
			System.out.println(paging);

			request.setAttribute("alist", alist);
			request.setAttribute("paging", paging);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
