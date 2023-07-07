/*
 * package com.kgcoffee.web.menu.menuAction;
 * 
 * import java.util.ArrayList;
 * 
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpServletResponse;
 * 
 * import com.kgcoffee.web.menu.menuDAO.MenuDAO; import
 * com.kgcoffee.web.menu.menuVO.MenuVO;
 * 
 * public class MenugetAll implements MenuImpl{
 * 
 * @Override public void menuAction(HttpServletRequest request,
 * HttpServletResponse response) throws Exception { // TODO Auto-generated
 * method stub
 * 
 * response.setCharacterEncoding("UTF-8");
 * 
 * MenuDAO mdao = new MenuDAO();
 * 
 * ArrayList<MenuVO> alist = mdao.getMenuList();
 * 
 * System.out.println();
 * 
 * System.out.println();
 * 
 * System.out.println("aList : "+alist); request.setAttribute("alist", alist);
 * 
 * }
 * 
 * }
 */