package com.kgcoffee.web.order;


import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kgcoffee.web.common.MyView;
import com.kgcoffee.web.order.controller.cart.CartDeleteController;
import com.kgcoffee.web.order.controller.cart.CartInsertController;
import com.kgcoffee.web.order.controller.cart.CartListController;
import com.kgcoffee.web.order.controller.cart.CartUpdateController;
import com.kgcoffee.web.order.controller.order.OrderCompleteController;
import com.kgcoffee.web.order.controller.order.OrderController;
import com.kgcoffee.web.users.vo.UsersVO;


@WebServlet(name = "frontController", urlPatterns = "/order/*")
public class FrontController extends HttpServlet {
    private Map<String, Controller> controllerMap = new ConcurrentHashMap<>();
    String viewName ="";
    Map<String, Object> model=null;
    public FrontController() {
    	
        controllerMap.put("/kgCoffee/order/order", new
                OrderController());
        controllerMap.put("/kgCoffee/order/complete", new
                OrderCompleteController());
        controllerMap.put("/kgCoffee/order/cart", new
                CartListController());
        controllerMap.put("/kgCoffee/order/cart/insert", new
                CartInsertController());
        controllerMap.put("/kgCoffee/order/cart/delete", new
                CartDeleteController());
        controllerMap.put("/kgCoffee/order/cart/update", new
                CartUpdateController());
        
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse
            response)
            throws ServletException, IOException {
    	
    	request.setCharacterEncoding("UTF-8");
    	model = new ConcurrentHashMap<>();
    	MyView view=null;
        String requestURI = request.getRequestURI();
        
        System.out.println(requestURI);
 
        Controller controller = controllerMap.get(requestURI);
        // 해당 controller 없을 경우 404 page 반환
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        // 파라미터가 있을 경우 Map 구조에 담음
        Map<String, String> paramMap = createParamMap(request);
  
        
        HttpSession session = request.getSession();
        UsersVO loginUser=((UsersVO)session.getAttribute("loginUser"));
        String userId="";
        
        if(loginUser!=null) {
        	userId = loginUser.getUser_id();
//        }else {
//        	userId="admin";
//        }
//        
//        
        paramMap.put("userId",userId);
        
     
          
       
        viewName = controller.process(paramMap, model);
        view = viewResolver(viewName);
        
        }else {
        	
        	viewName= "/";
        	
        	view = new MyView(viewName);
        	model.put("msg", "no-login");
        	
        }
        
        // view 반환
        
        // 렌더링
        
        System.out.println("result page : " + viewName);
        view.render(model, request, response);
        
  
        
        
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new ConcurrentHashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName,
                        request.getParameter(paramName)));
       

        
        
        		
        return paramMap;
    }

    private MyView viewResolver(String viewName) {
        
   
    	return new MyView("/view/order/" + viewName + ".jsp");
        
        
        
    }
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	
    	
    	service(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	service(req, resp);
    }
}