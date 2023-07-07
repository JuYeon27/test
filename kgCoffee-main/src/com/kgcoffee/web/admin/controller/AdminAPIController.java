package com.kgcoffee.web.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.kgcoffee.web.admin.DTO.ReportMapDTO;
import com.kgcoffee.web.admin.DTO.ReportMenuDTO;
import com.kgcoffee.web.admin.service.AdminService;
import com.kgcoffee.web.common.MyView;
import com.kgcoffee.web.users.vo.UsersVO;

/**
 * Servlet implementation class AdminController
 */
@WebServlet("/admin/api/*")
public class AdminAPIController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminAPIController() {
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

		String c = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println(c);

//		if (loginUser != null) {
//			userId = loginUser.getUser_id();
//
//			if (userId == "admin") {

				AdminService service = new AdminService();
		
				
				switch (c) {

				case "/admin/api/getChart.do":

		               String reqType = request.getParameter("select");
						/* System.out.println(reqType); */
		               
		               switch (reqType) {

		               case "chart-menu":

		                  model = service.reportOrderByMenu(keyMap);
		                  break;

		               case "chart-age":
		                  model = service.reportOrderByAgeGroup(keyMap);
		                  break;

		               case "chart-store":
		                  model = service.reportOrderByMap(keyMap);
		                  break;

		               }

		               response.setContentType("application/json");
		               response.setCharacterEncoding("UTF-8");

		       	

		               String gson = new Gson().toJson(model);
		               
		               try {

		                  response.getWriter().write(gson);
		                  
		               } catch (JsonIOException e) {
		                  e.printStackTrace();
		               } catch (IOException e) {
		                  e.printStackTrace();
		               }

		               
		               break;
			
				default:

					 System.out.println("mismatch"); 
					break;

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
