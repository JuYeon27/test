package com.kgcoffee.web.voc.vervice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.voc.dao.VocDAO;
import com.kgcoffee.web.voc.vo.VocVO;

public class VocSearchone implements VocImpli {

	@Override
	public void haeva(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		/*
		 * response.setContentType("text/html; charset=UTF-8");
		 * 
		 * PrintWriter out = response.getWriter();
		 * 
		 * String url = null;
		 * 
		 * MultipartRequest multi = null;
		 * 
		 * int sizeLimit = 10 * 1024 * 1024 ; // 10메가
		 * 
		 * String savePath = request.getRealPath("/file_upload");
		 * 
		 * //이름 재명명 정책 객체 (동일한 이름일때 뒤에 숫자를 붙여줌) multi = new MultipartRequest(request,
		 * savePath, sizeLimit, "UTF-8", new DefaultFileRenamePolicy());
		 * 
		 * Enumeration em1 = multi.getFileNames();
		 * 
		 * while(em1.hasMoreElements()) { //처리할 내용이 있어??
		 * 
		 * String filename1 = (String)em1.nextElement(); //끄집어냄 String orifilename1 =
		 * multi.getOriginalFileName(filename1); //원래이름 String upfilename1 =
		 * multi.getFilesystemName(filename1); //바뀐이름
		 * 
		 * 
		 * //http://localhost:8080/프로젝트명/폴더이름/ url =
		 * "http://localhost:8080/xxxeb_kg_coffee_조회수까지_수정까지_이제첨부파일/file_upload/" +
		 * upfilename1; System.out.println(url); //확인용
		 * 
		 * request.setAttribute("url", url);
		 * 
		 * }//while-end
		 */
		
		VocDAO sdao1 = new VocDAO();
		int bunho = Integer.parseInt(request.getParameter("bunho"));

		System.out.println("bunho ::::" + bunho);
		
		VocVO sv1 = sdao1.searchOne(bunho);

		request.setAttribute("ssv", sv1);
		System.out.println(sv1.getFilename());
		
	}

}
