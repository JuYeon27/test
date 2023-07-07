package com.kgcoffee.web.order.controller.cart;

import java.util.ArrayList;
import java.util.Map;

import com.kgcoffee.web.order.Controller;
import com.kgcoffee.web.order.dao.CartRepository;
import com.kgcoffee.web.order.domain.CartVO;

public class CartUpdateController implements Controller {

	@Override
	public String process(Map<String, String> paramMap, Map<String, Object> model) {
		
		
		int cartId = Integer.parseInt(paramMap.get("cartId"));
	
		String userId = paramMap.get("userId");
		int menuAmount = Integer.parseInt(paramMap.get("menuAmount"));
		CartRepository CartRepository = new CartRepository();

		CartRepository.update(cartId, menuAmount);
		ArrayList<CartVO> CartList = CartRepository.findAllCartsByUserId(userId);
		model.put("CartList", CartList);

		return "cart-form";
	}
}
