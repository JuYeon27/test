package com.kgcoffee.web.order.controller.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.kgcoffee.web.kakaoMap.dao.KakaoMapDAO;
import com.kgcoffee.web.kakaoMap.vo.KakaoMapVO;
import com.kgcoffee.web.order.Controller;
import com.kgcoffee.web.order.dao.CartRepository;
import com.kgcoffee.web.order.domain.CartVO;

public class OrderController implements Controller {
    @Override
    public String process(Map<String, String> paramMap, Map<String, Object> model) {
       
    	String userId = paramMap.get("userId");
   
        
        CartRepository cartRepository = new CartRepository();
        
        
        ArrayList<CartVO> cartList = cartRepository.findAllCartsByUserId(userId);
        ArrayList<KakaoMapVO> storeList =null;
        HashMap<String, Object> keyMap = new HashMap<String, Object>();
        
        
        storeList = KakaoMapDAO.getInstance().findMap(keyMap);

     	model.put("userId", userId);
        model.put("totalPrice", paramMap.get("totalPrice"));
        model.put("storeList", storeList);
        model.put("cartList", cartList);
        
        return "order-form";
    }
}
