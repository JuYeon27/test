package com.kgcoffee.web.menu.menuAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.menu.menuDAO.MenuDAO;
import com.kgcoffee.web.menu.menuVO.MenuVO;

public class MenuInfo implements MenuImpl{

	@Override
	public void menuAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		MenuDAO mdao = new MenuDAO();
		
		int menuId = Integer.parseInt(request.getParameter("menuId"));
		
		MenuVO vo = mdao.serchMenu(menuId);
		request.setAttribute("vo", vo);
		
	}

}
