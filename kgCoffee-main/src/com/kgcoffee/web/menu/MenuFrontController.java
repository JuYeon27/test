package com.kgcoffee.web.menu;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.common.MyView;
import com.kgcoffee.web.menu.menuAction.MenuDelete;
import com.kgcoffee.web.menu.menuAction.MenuImpl;
import com.kgcoffee.web.menu.menuAction.MenuInsert;
import com.kgcoffee.web.menu.menuAction.MenuSerch;
import com.kgcoffee.web.menu.menuAction.MenuUpdate;

/**
 * Servlet implementation class MenuFrontController
 */
@WebServlet("/menu/*")
public class MenuFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MenuFrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String c = request.getRequestURI().substring(request.getContextPath().length());
		String str = null;

		MenuImpl mi = null;
		System.out.println(c);
		switch (c) { 

		// 상품등록
		case "/menu/insert.do":
			mi = new MenuInsert();
			try { 
				mi.menuAction(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("controller error");
			}
			str = "menu";
			break;

		// 페이징 기법 처리
		case "/menu/getAllmenu.do":
			mi = new MenuSerch();
			try {
				request.setAttribute("chk", true);  
				
				mi.menuAction(request, response);
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			str = "menu";
			break;

		// 메뉴 비동기 처리
		case "/menu/getSerchmenu.do":
			mi = new MenuSerch();
			try {
				
				request.setAttribute("chk", false); 
				mi.menuAction(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
			str = "menuGetpage";

			

			break;
			
			
		//메뉴 삭제	
		case "#":
			mi = new MenuDelete();
			try {
				mi.menuAction(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
			str = "#";

			
			break;
			
			
		//메뉴 수정	
		case "":
			mi = new MenuUpdate();
			try {
				mi.menuAction(request, response);
			} catch (Exception e) {
				// TODO: handle exception
			}
			str = "#";

			
			break;
			
		}

		MyView view = viewResolver(str);

		Map<String, Object> model = new ConcurrentHashMap<>();
		view.render(model, request, response);

	}

	private MyView viewResolver(String viewName) {
		return new MyView("/view/menu/" + viewName + ".jsp");
	}

}
