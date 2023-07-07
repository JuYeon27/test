package com.kgcoffee.web.users;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kgcoffee.web.common.ControllerImpl;
import com.kgcoffee.web.common.MyView;
import com.kgcoffee.web.users.UserInsert;
import com.kgcoffee.web.users.UserLogin;
import com.kgcoffee.web.users.UserLogout;


@WebServlet("/user/*")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String c = request.getRequestURI().substring(request.getContextPath().length()); // .do 파일명 only
		String str = "/user/";
//		RequestDispatcher rd = null;
		
		ControllerImpl impl = null; // 인터페이스
		
		
		switch (c) {
		
		//로그인
		case "/user/login.do":
			impl = new UserLogin();
			
			try {
				impl.execute(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			str = "index";
			break;
			
		//로그아웃
		case "/user/logout.do":
			impl = new UserLogout();
			
			try {
				impl.execute(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			str = "index";
			break;
			
		
		//회원가입
		case "/user/join.do":
			impl = new UserInsert();
			
			try {
				impl.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			str = "login";
			break;
			
		//아이디찾기
		case "/user/findId.do":
			impl = new findId();
			
			try {
				impl.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			str = "findResult";
			break;
			
		//비밀번호찾기
		case "/user/findPw.do":
			impl = new findPw();
			
			try {
				impl.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			str = "findResultPw";
			break;
			
		//비밀번호 수정하기
		case "/user/findUpdatePw.do":
			impl = new findUpdatePw();
			
			try {
				impl.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			str = "login";
			break;
			
		}//switch-end	
			
		   MyView view = viewResolver(str);
	       
		   Map<String, Object> model = new ConcurrentHashMap<>();
		   view.render(model, request, response);
		
	}//doGet()-end

	
	 private MyView viewResolver(String viewName) {
	        return new MyView("/view/user/" + viewName + ".jsp");
	    }
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
