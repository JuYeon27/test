package com.kgcoffee.web.menu.menuAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.menu.menuDAO.MenuDAO;

public class MenuDelete implements MenuImpl{

	@Override
	public void menuAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		int menuId = Integer.parseInt(request.getParameter("menuId"));
		
		MenuDAO mdao = new MenuDAO();
		
		mdao.deleteMenu(menuId);
		
		
	}

}
