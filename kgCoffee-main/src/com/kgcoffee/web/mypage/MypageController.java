package com.kgcoffee.web.mypage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kgcoffee.web.common.Paging;
import com.kgcoffee.web.common.ControllerImpl;
import com.kgcoffee.web.order.dao.OrderDAO;
import com.kgcoffee.web.order.domain.OrderVO;
import com.kgcoffee.web.order.domain.PaymentsVO;
import com.kgcoffee.web.order.dto.OrderListDTO;
import com.kgcoffee.web.users.vo.UsersVO;

public class MypageController implements ControllerImpl{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		OrderDAO dao = new OrderDAO();
		
		UsersVO user = (UsersVO)request.getSession().getAttribute("loginUser");
		String user_id = user.getUser_id();
		
		
		Map<String, Object> keyMap = new HashMap<String, Object>();
		
		keyMap.put("type","user_id");
		
		keyMap.put("value", user_id);
		
		Paging paging = new Paging();
		
		

		
		
		int page = 1;
		int count = dao.findTotalCnt(keyMap);
		
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		paging.setPage(page);
		paging.setTotalCount(count);
		
		List<OrderVO> orderList = dao.findOrder(keyMap, paging);
		
		System.out.println(orderList.size());
		List<OrderListDTO> dtoList = new ArrayList<OrderListDTO>();
		for(OrderVO vo : orderList) {
			
			keyMap.put("type","order_id");
			
			keyMap.put("value", vo.getOrderId());
			
			List<PaymentsVO> paymentsList = dao.findPayments(keyMap);
			
			OrderListDTO dto = new OrderListDTO(vo, paymentsList);
			dtoList.add(dto);
			
			
		}
		
		System.out.println(dtoList.size());
		
		
		request.setAttribute("dtoList", dtoList);
		request.setAttribute("paging", paging);
		
		
		
	}

}
