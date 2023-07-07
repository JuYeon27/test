package com.kgcoffee.web.mypage;

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


@WebServlet("/mypage/*")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontController() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		String c = request.getRequestURI().substring(request.getContextPath().length()); // .do 파일명 only
		String str = "/mypage/";
		
		ControllerImpl impl = null; // 인터페이스
		
		switch (c) {
		
		//회원 비밀번호 확인 => 수정페이지로 넘길때 회원 정보도 같이 넘김
		case "/mypage/modifyck.do":
			
			impl = new modifyck();
			try {
				impl.execute(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			impl = new modify();
			try {
				impl.execute(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			str = "userModify";
			break;
			
			
		case "/mypage/userUpdate.do":
			
			impl = new userUpdate();
			try {
				impl.execute(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			str = "/mypage/findOrder.do";
			break;
			
		case "/mypage/findOrder.do":
			impl = new MypageController();
			try {
				impl.execute(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			str = "userOrderList";
			break;
			
		case "/mypage/userOrderView.do":
			impl = new MypageController();
			try {
				impl.execute(request, response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
			str = "userOrderView";
			break;
			
			
		}//switch-end	
			
		   MyView view = viewResolver(str);
	       
		   Map<String, Object> model = new ConcurrentHashMap<>();
		   view.render(model, request, response);
		
	}//doGet()-end

	
	 private MyView viewResolver(String viewName) {
		 if((viewName.substring(viewName.length()-2,viewName.length()).equals("do"))){
			 return new MyView(viewName);
		 }else {
			return new MyView("/view/mypage/" + viewName + ".jsp");
		 }
	    }
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
