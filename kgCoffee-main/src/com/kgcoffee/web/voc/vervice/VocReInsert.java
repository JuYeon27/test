package com.kgcoffee.web.voc.vervice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.voc.dao.VocDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class VocReInsert implements VocImpli {

	@Override
	public void haeva(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		MultipartRequest multi = null;
		
		int sizeLimit = 10 * 1024 * 1024 ; // 10메가
		
		String savePath = request.getRealPath("/file_upload");
		
		try {
			multi = new MultipartRequest(request, savePath, sizeLimit, 
											"UTF-8", new DefaultFileRenamePolicy());

												//이름 재명명 정책 객체 (동일한 이름일때 뒤에 숫자를 붙여줌)
		
		String filename = multi.getFilesystemName("filename");
		
		
		System.out.println("filename :::"+ filename);
		String jemok = multi.getParameter("jemok");
		String writer = multi.getParameter("writer");
		String content = multi.getParameter("content");
		
		System.out.println("ref :::::::::"+multi.getParameter("ref"));
		int ref = Integer.parseInt(multi.getParameter("ref"));
		int re_step = Integer.parseInt(multi.getParameter("re_step"));
		int re_level =Integer.parseInt(multi.getParameter("re_level"));
		
		String user_id = multi.getParameter("user_id");
		
		
		VocDAO sdao1 = new VocDAO();                              
		
		sdao1.reInsert(jemok, writer, content, filename, ref, re_step, re_level, user_id);
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
