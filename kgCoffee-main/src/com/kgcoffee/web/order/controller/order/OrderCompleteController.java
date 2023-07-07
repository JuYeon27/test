package com.kgcoffee.web.order.controller.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.kgcoffee.web.order.Controller;
import com.kgcoffee.web.order.dao.CartRepository;
import com.kgcoffee.web.order.dao.OrderDAO;
import com.kgcoffee.web.order.domain.CartVO;
import com.kgcoffee.web.order.domain.OrderVO;
import com.kgcoffee.web.order.domain.PaymentsVO;
import com.kgcoffee.web.order.service.IamportService;

public class OrderCompleteController implements Controller {

//    private ObjectMapper objectMapper;
	IamportService service = new IamportService();
	

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		// DB TABLE 생성해서 주문 정보 원하는 값들만 넣어주면 됨.

		String result = "order-complete-fail";
		
		String impUid = paramMap.get("imp_uid");
		System.out.println(paramMap.get("merchant_uid"));
		int amount = 0;
		try {
			String accessToken = service.getAccessToken();
			amount = service.getPaymentsInfo(impUid, accessToken);
					
		
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println(amount);
		String userId = paramMap.get("userId");
		int paidAmount = Integer.parseInt(paramMap.get("paid_amount"));
		String paidAt = (paramMap.get("paid_at"));
		int totalPrice = Integer.parseInt(paramMap.get("total_price"));
		int mapId = Integer.parseInt(paramMap.get("map_id"));

		long timestamp = Long.parseLong(paramMap.get("paid_at"));
		Date pDate = new java.util.Date(timestamp * 1000L);

		OrderDAO dao = new OrderDAO();
		

		System.out.println("paid"+paidAmount);
		System.out.println("total"+totalPrice);
		if (paidAmount == amount) {

			OrderVO order = new OrderVO();

			order.setUserId(userId);
			order.setMapId(mapId);
			order.setTotalPrice(paidAmount);
			order.setOrderDate(pDate);
			order.setImpUid(impUid);
			
			if (dao.insertOrder(order)) {
				
				int orderId = dao.getOrderSeq();
				
				CartRepository cartDao = new CartRepository();
				
				ArrayList<CartVO> cartList = cartDao.findAllCartsByUserId(userId);
				
				for(CartVO cart : cartList) {
					
					PaymentsVO payments = new PaymentsVO();
					
					
					payments.setUserId(userId);
					payments.setOrderId(orderId);
					payments.setMenuId(cart.getMenuId());
					payments.setMenuAmount(cart.getMenuAmount());

//	                payments.setFileName(cart.getFileName());
//	                payments.setCaffeineType(cart.getCaffeineType());
//	                payments.setMenuName(cart.getMenuType());
//	                payments.setMenuPrice(cart.getMenuPrice());
//	                payments.setMenuType(cart.getMenuType());
//	                payments.setMenuExplain(cart.getMenuExplain());

					dao.insertPayments(payments);
					
				}
				
				Map<String, Object> keyMap = new HashMap<String, Object>();

				keyMap.put("type", "user_id");
				keyMap.put("value", userId);
				
				cartDao.delete(keyMap);
				
				
				result = "order-complete";
			}

		}else {
			result="mismatch-paid";
		}

		model.put("res-msg", result);

		return "result-form";
	}
}
