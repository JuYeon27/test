package com.kgcoffee.web.menu.menuAction;



import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.menu.menuDAO.MenuDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class MenuUpdate implements MenuImpl {

    @Override
    public void menuAction(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=utf-8");
        
        
        String img = "img/menuImg";
        ServletContext context = request.getServletContext();
        String saveImg = context.getRealPath(img);
	

        MultipartRequest mr = null;
        try {
        	
            mr = new MultipartRequest(request, saveImg, 1024 * 1024 * 10, "UTF-8",
            		new DefaultFileRenamePolicy());

           int menuId = Integer.parseInt(mr.getParameter("menuId"));
           
           
           // 이미지 수정 하면 수정한 이미지명, 수정 안하면 기존 이미지명
           String menuImg = null;
           if(mr.getFilesystemName("img") == null) {
        	   menuImg = mr.getParameter("img2");
           }else {
        	   menuImg = mr.getFilesystemName("img");
           }
            
            String caffeineType = mr.getParameter("caffeineType");
            String menuName = mr.getParameter("menuName");
            int menuPrice = Integer.parseInt(mr.getParameter("menuPrice"));
            String menuExplain = mr.getParameter("menuExplain");
            String menuType = mr.getParameter("menuType");
            
            MenuDAO mdao = new MenuDAO();
           mdao.updateMenu(menuId, menuImg, caffeineType, menuName, menuPrice, menuExplain, menuType);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
       
        
    }
}

