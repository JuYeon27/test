package com.kgcoffee.web.order.controller.cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kgcoffee.web.order.Controller;
import com.kgcoffee.web.order.dao.CartRepository;
import com.kgcoffee.web.order.domain.CartVO;


public class CartDeleteController implements Controller {

    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        paramMap.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));
        String cartId = paramMap.get("cartId");
        String userId =  paramMap.get("userId");
        CartRepository cartRepository = new CartRepository();
        
        Map<String,Object> keyMap = new HashMap<String,Object>();
        
        keyMap.put("type", "cart_Id");
        keyMap.put("value", cartId);
        
        cartRepository.delete(keyMap);
        
        ArrayList<CartVO> cartList = cartRepository.findAllCartsByUserId(userId);
        model.put("cartList", cartList);
        return "cart-form";
    }
}
