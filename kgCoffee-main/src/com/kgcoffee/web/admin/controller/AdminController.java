package com.kgcoffee.web.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kgcoffee.web.admin.service.AdminService;
import com.kgcoffee.web.common.MyView;
import com.kgcoffee.web.menu.menuAction.MenuDelete;
import com.kgcoffee.web.menu.menuAction.MenuImpl;
import com.kgcoffee.web.menu.menuAction.MenuInfo;
import com.kgcoffee.web.menu.menuAction.MenuInsert;
import com.kgcoffee.web.menu.menuAction.MenuSerch;
import com.kgcoffee.web.menu.menuAction.MenuUpdate;
import com.kgcoffee.web.users.vo.UsersVO;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin/*")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		Map<String, String> keyMap = createParamMap(request);

		Map<String, Object> model = null;

		HttpSession session = request.getSession();
		UsersVO loginUser = ((UsersVO) session.getAttribute("loginUser"));
		String userId = "";
		String str = "";
		String c = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println(c);
		MenuImpl mi = null;
		MyView view=null;
		if (loginUser != null) {
			userId = loginUser.getUser_id();

			
			if (userId.equals("admin")) {

				AdminService service = new AdminService();

				switch (c) {

				case "/admin/getOrderReport":
					str = "adminOrderData";

					break;

				case "/admin/getAllMember.do":

					model = service.getAllMember(keyMap);

					str = "memberlistttttt";
					break;

				// 한명 멤버 불러오기
				case "/admin/getOne.do":

					model = service.getOneMember(keyMap);

					str = "memberUpdate";
					break;

				case "/admin/update.do":
					model = service.memberUpdate(keyMap);

					str = "/admin/getAllMember.do";
					break;

				case "/admin/searchMember.do":
					model = service.memberSearch(keyMap);

					str = "memberSearchList";
					break;

				case "/admin/deleteMember.do":

					StringBuilder checkId = new StringBuilder();
					for (String id : request.getParameterValues("checkId")) {
						checkId.append(id + ",");

					}
					keyMap.put("checkId", checkId.toString());

					model = service.memberDelete(keyMap);

					str = "/admin/getAllMember.do";
					break;

					
					
					
					
					//메뉴
					
				case "/admin/adminMenu.do":
					System.out.println("메뉴");
					mi = new MenuSerch();
					try { 
						request.setAttribute("chk", true);  
						mi.menuAction(request, response);
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println("controller error");
					}
					str = "adminMenuForm";
					break;
					
				case "/admin/admingetSerchmenu.do":
					mi = new MenuSerch();
					try {
						
						request.setAttribute("chk", false); 
						mi.menuAction(request, response);
					} catch (Exception e) {
						// TODO: handle exception
					}
					str = "adminMenuGetpage";
					break;	
					
					
					
					// 상품등록
				case "/admin/menuInsertForm.do":
				
					str = "adminMenuInsert";
					break;	
				case "/admin/adminMenuInsert.do":
					mi = new MenuInsert();
					try { 
					mi.menuAction(request, response);
					} catch (Exception e) {
						// TODO: handle exception
					
					}
					str = "/admin/adminMenu.do";
					break;	
					
					
					//메뉴 삭제	
				case "/admin/adminMenuDelete.do":
					mi = new MenuDelete();
					try {
						mi.menuAction(request, response);
					} catch (Exception e) {
						// TODO: handle exception
					}
					str = "/admin/adminMenu.do";			
					break;
								
								
					//메뉴 수정 이동	
				case "/admin/adminMenuGetpage.do":
					mi = new MenuInfo();
					try {
						mi.menuAction(request, response);
					} catch (Exception e) {
						// TODO: handle exception
					}
					str = "adminMenuUpdate";			
					break;	
					
					//메뉴 수정
				case "/admin/adminMenuUpdate.do":
					mi = new MenuUpdate();
					try {
						mi.menuAction(request, response);
					} catch (Exception e) {
						// TODO: handle exception
					}
					str = "/admin/adminMenu.do";			
					break;
					
					//메뉴 검색
				case "/admin/adminSearchMenu.do":
					mi = new MenuSerch();
					try {
						request.setAttribute("chk", true); 
						mi.menuAction(request, response);
					} catch (Exception e) {
						// TODO: handle exception
					}
					str = "/admin/adminMenu.do";			
					break;		
					
						
					
				default:

					System.out.println("mismatch");
					break;

				}
				view = viewResolver(str);
	

			} else {

				model=new HashMap<String, Object>();
						
				model.put("msg", "invalid");
				str="/";
				view =new MyView(str);
				
						
			}
		} else {

			model=new HashMap<String, Object>();
			model.put("msg","no-login");
			str="/";
			view =new MyView(str);
			
		}
		

		view.render(model, request, response);
	}

	private MyView viewResolver(String viewName) {
		System.out.println(viewName);
		if ((viewName.substring(viewName.length() - 2, viewName.length()).equals("do"))) {
			
			return new MyView(viewName);
		} else {
			return new MyView("/view/admin/" + viewName + ".jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	private Map<String, String> createParamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new ConcurrentHashMap<>();
		request.getParameterNames().asIterator()
				.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

		return paramMap;
	}

}
