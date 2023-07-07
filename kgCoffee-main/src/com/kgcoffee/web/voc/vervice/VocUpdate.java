package com.kgcoffee.web.voc.vervice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.voc.dao.VocDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class VocUpdate implements VocImpli {

	@Override
	public void haeva(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		MultipartRequest multi = null;
		
		VocDAO sdao1 = new VocDAO();
		
		int sizeLimit = 10 * 1024 * 1024 ; // 10메가
		
		String savePath = request.getRealPath("/file_upload");
		
		multi = new MultipartRequest(request, savePath, sizeLimit, 
										"UTF-8", new DefaultFileRenamePolicy()); 
												//이름 재명명 정책 객체 (동일한 이름일때 뒤에 숫자를 붙여줌)
		
		String filename = multi.getFilesystemName("filename");
		
		String jemok = multi.getParameter("jemok");
		String writer = multi.getParameter("writer");
		String content = multi.getParameter("content");
		
		int bunho = Integer.parseInt(multi.getParameter("bunho"));
		
		sdao1.update(jemok, writer, content, bunho, filename);
		
	}

}
