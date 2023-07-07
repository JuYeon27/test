package com.kgcoffee.web.order.domain;

public class PaymentsVO extends CartVO{

	private int paymentsId;
	
	private int OrderId;
	
		
	public PaymentsVO() {
		// TODO Auto-generated constructor stub
	}


	public int getPaymentsIdl() {
		return paymentsId;
	}


	public void setpPaymentsId(int paymentsId) {
		paymentsId = paymentsId;
	}


	public int getOrderId() {
		return OrderId;
	}


	public void setOrderId(int orderId) {
		OrderId = orderId;
	}


	public PaymentsVO(int paymentsId, int orderId) {
		super();
		paymentsId = paymentsId;
		OrderId = orderId;
	}

	

	@Override
	public String toString() {
		return "PaymentsVO [paymentsId=" + paymentsId + ", OrderId=" + OrderId + ", getCartId()=" + getCartId()
				+ ", getUserId()=" + getUserId() + ", getMenuAmount()=" + getMenuAmount() + ", getMenuId()="
				+ getMenuId() + ", getFileName()=" + getFileName() + ", getCaffeineType()=" + getCaffeineType()
				+ ", getMenuName()=" + getMenuName() + ", getMenuPrice()=" + getMenuPrice() + ", getMenuExplain()="
				+ getMenuExplain() + ", getMenuType()=" + getMenuType() + "]";
	}



	
	

}
