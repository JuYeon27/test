package com.kgcoffee.web.menu.menuAction;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.menu.menuDAO.MenuDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class MenuInsert implements MenuImpl {

	@Override
	public void menuAction(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String img = "img/menuImg";
		ServletContext context = request.getServletContext();
		String saveImg = context.getRealPath(img);
		
		System.out.println(context.getRealPath(img));
		
		try {
			MultipartRequest mr = new MultipartRequest(request, saveImg, 1024*1024*10,
					"UTF-8", new DefaultFileRenamePolicy());
			
			//System.out.println("파일 업로드 굿");
			
			String menuImg = mr.getFilesystemName("img");			
			String caffeineType = mr.getParameter("caffeinType");
			String menuName = mr.getParameter("menuName");
			int menuPrice = Integer.parseInt(mr.getParameter("menuPrice"));
			String menuExplain = mr.getParameter("menuExplain");
			String menuType = mr.getParameter("menuType");
			
			MenuDAO mdao = new MenuDAO();
			
			mdao.insertMenu(menuImg, caffeineType, menuName, 
					menuPrice, menuExplain, menuType);
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//System.out.println("MenuInsert Error");
		}
		
	}
	
}
