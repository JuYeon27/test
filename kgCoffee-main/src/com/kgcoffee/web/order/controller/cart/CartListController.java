package com.kgcoffee.web.order.controller.cart;

import java.util.ArrayList;
import java.util.Map;

import com.kgcoffee.web.order.Controller;
import com.kgcoffee.web.order.dao.CartRepository;
import com.kgcoffee.web.order.domain.CartVO;

public class CartListController implements Controller {


    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
        // user data 없어서 임시로 test 바로 보냄 후에 paramMap에서 받아오는 것으로 수정
        CartRepository cartRepository = new CartRepository();
        
        String userId = paramMap.get("userId");
        ArrayList<CartVO> cartList = cartRepository.findAllCartsByUserId(userId);
        int totalPrice = getTotalAmountByUser(cartList);
        model.put("totalPrice", totalPrice);
        model.put("cartList", cartList);
        model.put("userId", userId);

        return "cart-form";
    }
    
    
    public int getTotalAmountByUser(ArrayList<CartVO> cartList){
        int tmp = 0;
        for (CartVO cart: cartList) {
            tmp += (cart.getMenuAmount() * cart.getMenuPrice());
        }
        return tmp;
    }
}
