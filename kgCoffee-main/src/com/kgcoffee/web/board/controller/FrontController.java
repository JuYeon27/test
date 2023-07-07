package com.kgcoffee.web.board.controller;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.board.controller.HaevaDelete;
import com.kgcoffee.web.common.ControllerImpl;
import com.kgcoffee.web.common.MyView;
import com.kgcoffee.web.users.UserLogin;
import com.kgcoffee.web.users.UserLogout;

@WebServlet("/board/*")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}  

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {


		String c = request.getRequestURI().substring(request.getContextPath().length()); // .do 파일명 only
		String str = "/board/";

		ControllerImpl impl = null; // 인터페이스

		switch (c) {

		// 게시판 - 공지사항
		// 전체 목록조회
		case "/board/getAll.do":
			impl = new HaevaGetAllinfo();
			try {
				impl.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			str = "boardList";
			break;

		// 공지사항 입력
		case "/board/insert.do":
			impl = new HaevaInsert();
			try {
				impl.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			str = "getAll.do";
			break;

		// 선택한 게시글 보기
		case "/board/searchone.do":
			impl = new HaevaSearchone();
			try {
				impl.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			impl = new UpHItService();
			try {
				impl.execute(request, response);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

			str = "boardView";
			break;

		// 공지사항 삭제
		case "/board/delete.do":
			impl = new HaevaDelete();
			try {
				impl.execute(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			str = "getAll.do";
			break;

		// 공지사항 수정
		case "/board/update.do":
			impl = new HaevaUpdate();
			try {
				impl.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			str = "getAll.do";
			break;

		// 공지사항 수정페이지로 이동
		case "/board/edit.do":
			impl = new HaevaSearchone();
			try {
				impl.execute(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			str = "boardEdit";
			break;
			
		//검색창 
		case "/board/seachboard.do":
			impl = new HaevaSearchBoard();
			try {
				impl.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("검색창 에러");
			}
			
			str = "boardSearchList";
			
			
	

		}// switch-end

		   MyView view = viewResolver(str);
	       
		   Map<String, Object> model = new ConcurrentHashMap<>();
		   view.render(model, request, response);

	}// doGet()-end

	
	 private MyView viewResolver(String viewName) {
		
		 if (viewName.substring(6).equals(".do")) {
			 return new MyView("/board/" + viewName);
		 } else {
			 return new MyView("/view/board/" + viewName + ".jsp");
		 }
		 
		    
	 }
	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
