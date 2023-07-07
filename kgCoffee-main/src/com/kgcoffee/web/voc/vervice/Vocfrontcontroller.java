package com.kgcoffee.web.voc.vervice;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.common.MyView;

@WebServlet("/voc/*") //어노테이션을 통해 서블릿이 처리할 URL 패턴을 설정. .do로 끝나는 모든 요청을 처리한다.
public class Vocfrontcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Vocfrontcontroller() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		String c = request.getRequestURI().substring(request.getContextPath().length());
		String str = null;
		
		VocImpli impl = null;
		
		System.out.println(c);

		switch (c) {
		
		/*
		 * case "/voc/vocBoardWrite.jsp":
		 * 
		 * str="vocBoardWrite";
		 * 
		 * break;
		 */
			
			
		case "/voc/getAll.do":
			impl = new VocGetAllinfo();
			try {
				impl.haeva(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			str = "vocBoardList";
			break;

		case "/voc/insert.do":
			impl = new VocInsert();
			try {
				impl.haeva(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "index";
			break;

		case "/voc/searchone.do":
			impl = new VocSearchone();
			try {
				impl.haeva(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			impl = new VocUpHItService();
			try {
				impl.haeva(request, response);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			impl = new VocSearchOneUpdate();
			try {
				impl.haeva(request, response);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			
			str = "vocBoardView";
			break;

		case "/voc/delete.do":
			impl = new VocDelete();
			try {
				impl.haeva(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			str = "index";
			break;

		case "/voc/update.do":
			impl = new VocUpdate();
			try {
				impl.haeva(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			str = "index";
			break;
			
		case "/voc/edit.do":
			impl = new VocSearchone();
			try {
				impl.haeva(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			str = "vocBoardEdit";
			break;
			
		case "/voc/Reinsert.do":
			impl = new VocReInsert();
			try {
				impl.haeva(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			str = "index";
			break;
			
		//검색창 
		case "/voc/vocSearchboard.do":
			impl = new VocSearchBoard();
			try {
				impl.haeva(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("검색창 에러");
			}
			
			str = "vocBoardSearchList";
			break;
		
		}
		
	   MyView view = viewResolver(str);
	       
		   Map<String, Object> model = new ConcurrentHashMap<>();
		   view.render(model, request, response);

	}

	
	 private MyView viewResolver(String viewName) {
			
		 
			 return new MyView("/view/voc/" + viewName + ".jsp");
		    
	 }

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
