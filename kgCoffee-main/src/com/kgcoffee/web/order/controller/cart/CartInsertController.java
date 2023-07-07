package com.kgcoffee.web.order.controller.cart;

import java.util.ArrayList;
import java.util.Map;

import com.kgcoffee.web.order.Controller;
import com.kgcoffee.web.order.dao.CartRepository;
import com.kgcoffee.web.order.domain.CartVO;

public class CartInsertController implements Controller {

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {

		String result="fail";

		int menuId = Integer.parseInt(paramMap.get("menuId"));
	
		String userId = paramMap.get("userId");
		int menuAmount = Integer.parseInt(paramMap.get("menuAmount"));
		if(menuAmount!=0) {
		CartRepository CartRepository = new CartRepository();
		
		int cartId= CartRepository.findCartByMenuId(userId, menuId);
		if ( cartId >= 1) {
			CartRepository.update(cartId, menuAmount);
			
			result="update-success";

		}else {
			
			CartVO cart = new CartVO();
			cart.setMenuId(menuId);
			cart.setUserId(userId);
			cart.setMenuAmount(menuAmount);
			CartRepository.save(cart);
			
			result="create-success";
			
		}}else {
			result="empty_amount";
		}
		
		
		model.put("res-msg", result);

		return "result-form";
	}

		
	

}
