package com.kgcoffee.web.board.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.board.dao.SnsDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class HaevaInsert implements com.kgcoffee.web.common.ControllerImpl {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MultipartRequest multi = null;
		
		int sizeLimit = 10 * 1024 * 1024 ; // 10메가
		
		String savePath = request.getRealPath("/file_upload");
		
		multi = new MultipartRequest(request, savePath, sizeLimit, 
										"UTF-8", new DefaultFileRenamePolicy()); 
												//이름 재명명 정책 객체 (동일한 이름일때 뒤에 숫자를 붙여줌)
		
		String filename = multi.getFilesystemName("filename");
		
		String jemok = multi.getParameter("jemok");
		String writer = multi.getParameter("writer");
		String content = multi.getParameter("content");
		String user_id = multi.getParameter("user_id");
		
		SnsDAO sdao1 = new SnsDAO();
		
		sdao1.insert(jemok, writer, content, filename, user_id);
		
	}

}
