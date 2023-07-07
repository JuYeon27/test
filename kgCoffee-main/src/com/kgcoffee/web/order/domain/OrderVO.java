package com.kgcoffee.web.order.domain;

import java.util.Date;

import com.kgcoffee.web.kakaoMap.vo.KakaoMapVO;

public class OrderVO extends KakaoMapVO{
	

	private int orderId;
	private String userId;
	private int totalPrice;
	private Date orderDate;
	private String impUid;
	
	public OrderVO() {
		// TODO Auto-generated constructor stub
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getImpUid() {
		return impUid;
	}

	public void setImpUid(String impUid) {
		this.impUid = impUid;
	}

	public OrderVO(int orderId, String userId, int totalPrice, Date orderDate, String impUid) {
		super();
		this.orderId = orderId;
		this.userId = userId;
		this.totalPrice = totalPrice;
		this.orderDate = orderDate;
		this.impUid = impUid;
	}

	@Override
	public String toString() {
		return "OrderVO [orderId=" + orderId + ", userId=" + userId + ", totalPrice=" + totalPrice + ", orderDate="
				+ orderDate + ", impUid=" + impUid + ", getAddressName()=" + getAddressName()
				+ ", getCategoryGroupCode()=" + getCategoryGroupCode() + ", getCategoryGroupName()="
				+ getCategoryGroupName() + ", getCategoryName()=" + getCategoryName() + ", getMapId()=" + getMapId()
				+ ", getPlaceName()=" + getPlaceName() + ", getPlaceUrl()=" + getPlaceUrl() + ", getPhone()="
				+ getPhone() + ", getRoadAddressName()=" + getRoadAddressName() + ", getX()=" + getX() + ", getY()="
				+ getY() + "]";
	}


	
	

	

	
	
	
	
	
	

}
