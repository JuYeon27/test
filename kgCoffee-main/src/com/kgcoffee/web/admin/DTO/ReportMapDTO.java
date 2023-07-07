package com.kgcoffee.web.admin.DTO;

public class ReportMapDTO {
	
	
	private int orderAmount;
	private String orderDate;
	private int mapId;
	private String placeName;
	private String addressName;
	private String roadaddressName;
	
	
	public ReportMapDTO() {
		// TODO Auto-generated constructor stub
	}


	public int getOrderAmount() {
		return orderAmount;
	}


	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}


	public String getOrderDate() {
		return orderDate;
	}


	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}


	public int getMapId() {
		return mapId;
	}


	public void setMapId(int mapId) {
		this.mapId = mapId;
	}


	public String getPlaceName() {
		return placeName;
	}


	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}


	public String getAddressName() {
		return addressName;
	}


	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}


	public String getRoadaddressName() {
		return roadaddressName;
	}


	public void setRoadaddressName(String roadaddressName) {
		this.roadaddressName = roadaddressName;
	}


	public ReportMapDTO(int orderAmount, String orderDate, int mapId, String placeName, String addressName,
			String roadaddressName) {
		super();
		this.orderAmount = orderAmount;
		this.orderDate = orderDate;
		this.mapId = mapId;
		this.placeName = placeName;
		this.addressName = addressName;
		this.roadaddressName = roadaddressName;
	}


	@Override
	public String toString() {
		return "ReportMapDTO [orderAmount=" + orderAmount + ", orderDate=" + orderDate + ", mapId=" + mapId
				+ ", placeName=" + placeName + ", addressName=" + addressName + ", roadaddressName=" + roadaddressName
				+ "]";
	}
	
	
	
	
	
	
	
	

}
