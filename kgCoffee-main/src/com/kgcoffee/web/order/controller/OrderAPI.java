package com.kgcoffee.web.order.controller;

import java.io.IOException;
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
import com.kgcoffee.web.admin.service.AdminService;
import com.kgcoffee.web.order.service.IamportService;
import com.kgcoffee.web.users.vo.UsersVO;

/**
 * Servlet implementation class OrderAPI
 */
@WebServlet("/order/api/*")
public class OrderAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		

	request.setCharacterEncoding("UTF-8");
		
		Map<String, String> keyMap = createParamMap(request);

		Map<String, Object> model = null;
		
		HttpSession session = request.getSession();
		UsersVO loginUser = ((UsersVO) session.getAttribute("loginUser"));
		String userId = "";

		String c = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println(c);
		
		IamportService service = new IamportService();

//		if (loginUser != null) {
//			userId = loginUser.getUser_id();
//
//			if (userId == "admin") {

				
		
				
				switch (c) {

				case "/order/api/refund.do":

					try {
						String accessToken = service.getAccessToken();
						
						model = service.refundOrder(keyMap, accessToken);
						
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					

		       	

		    
		               
		               break;
			
				default:

					 System.out.println("mismatch"); 
					break;

				}
				
		           String gson = new Gson().toJson(model);
	               
	               try {

	                  response.getWriter().write(gson);
	                  
	               } catch (JsonIOException e) {
	                  e.printStackTrace();
	               } catch (IOException e) {
	                  e.printStackTrace();
	               }

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private Map<String, String> createParamMap(HttpServletRequest request) {
		Map<String, String> paramMap = new ConcurrentHashMap<>();
		request.getParameterNames().asIterator()
				.forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));

		return paramMap;
	}

	
	

}
