package com.kgcoffee.web.kakaoMap.controller;

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
import com.kgcoffee.web.kakaoMap.service.KakaoService;

/**
 * Servlet implementation class KakaoMapController
 */
@WebServlet("/store/*")
public class KakaoMapController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	RequestDispatcher rd = null;
	
	String str = "";


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		KakaoService service = new KakaoService();
		
		request.setCharacterEncoding("UTF-8");
		
		String c = request.getRequestURI().substring(request.getContextPath().length());
		System.out.println(c);

		switch(c) {
		
		
		case "/store/find.map":
			
				str="kakaoMap";
				
				break;
			
		case "/store/save.map":
			
			service.saveMap();
			
			break;
			
	

		default:
			
			System.out.println("mismatch");
			break;
			
		
		}
		

	   MyView view = viewResolver(str);
	       
	   
	   Map<String, Object> model = new ConcurrentHashMap<>();
	   view.render(model, request, response);
		
		
	}

	
	 private MyView viewResolver(String viewName) {
	        return new MyView("/view/store/" + viewName + ".jsp");
	    }
	  
	  
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

	}

	

}