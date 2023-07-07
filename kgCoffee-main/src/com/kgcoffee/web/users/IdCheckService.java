package com.kgcoffee.web.users;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.users.dao.UsersDAO;


@WebServlet("/users/IdCheckService")
public class IdCheckService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    public IdCheckService() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String user_id = request.getParameter("user_id");
		
		PrintWriter out = response.getWriter();
		
		try {
			UsersDAO dao = new UsersDAO();
			
			boolean idCheck = dao.idChk(user_id);
			
			//성공확인여부
			if(idCheck) {
				System.out.println("이미 존재하는 아이디입니다.");
			} else if (idCheck) {
				System.out.println("사용가능한 아이디입니다.");
			}
			
			response.setContentType("application/json");
			
			out.write(idCheck + ""); //ajax 결과값인 result가 됨, String으로 값을 내보낼 수 있도록 ""
			
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
