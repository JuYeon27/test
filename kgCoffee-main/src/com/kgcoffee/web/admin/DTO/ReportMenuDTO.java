package com.kgcoffee.web.admin.DTO;

import com.kgcoffee.web.menu.menuVO.MenuVO;

public class ReportMenuDTO extends MenuVO{
	
	
	private int orderAmount;
	private String ageGroup;
	private String orderDate;
	
	public int getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}
	public String getAgeGroup() {
		return ageGroup;
	}
	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
	
	public ReportMenuDTO() {
		// TODO Auto-generated constructor stub
	}
	public ReportMenuDTO(int menuId, String fileName, String caffeineType, String menuName, int menuPrice,
			String menuExplain, String menuType, int orderAmount, String ageGroup, String orderDate) {
		super(menuId, fileName, caffeineType, menuName, menuPrice, menuExplain, menuType);
		this.orderAmount = orderAmount;
		this.ageGroup = ageGroup;
		this.orderDate = orderDate;
	}
	@Override
	public String toString() {
		return "ReportMenuDTO [orderAmount=" + orderAmount + ", ageGroup=" + ageGroup + ", orderDate=" + orderDate
				+ ", getMenuId()=" + getMenuId() + ", getFileName()=" + getFileName() + ", getCaffeineType()="
				+ getCaffeineType() + ", getMenuName()=" + getMenuName() + ", getMenuPrice()=" + getMenuPrice()
				+ ", getMenuExplain()=" + getMenuExplain() + ", getMenuType()=" + getMenuType() + "]";
	}
	
	
	
	
	

}
