package com.kgcoffee.web.menu.menuAction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface MenuImpl {
	
	public void menuAction(HttpServletRequest request, 
			HttpServletResponse response) throws Exception;
}
