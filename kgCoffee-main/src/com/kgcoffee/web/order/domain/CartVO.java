package com.kgcoffee.web.order.domain;

import com.kgcoffee.web.menu.menuVO.MenuVO;

public class CartVO extends MenuVO {

	private int cartId;

	private String userId;
	
	private int menuAmount;

	@Override
	public String toString() {
		return "CartVO [cartId=" + cartId + ", userId=" + userId + ", menuAmount=" + menuAmount + "]";
	}

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getMenuAmount() {
		return menuAmount;
	}

	public void setMenuAmount(int menuAmount) {
		this.menuAmount = menuAmount;
	}



	public CartVO() {
		// TODO Auto-generated constructor stub
	}
	

	public CartVO(int menuId, String fileName, String caffeineType, String menuName, int menuPrice, String menuExplain,
			String menuType, int cartId, String userId, int menuAmount) {
		super(menuId, fileName, caffeineType, menuName, menuPrice, menuExplain, menuType);
		this.cartId = cartId;
		this.userId = userId;
		this.menuAmount = menuAmount;
	}



}
