package com.kgcoffee.web.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControllerImpl {
	
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws Exception;
	
}
