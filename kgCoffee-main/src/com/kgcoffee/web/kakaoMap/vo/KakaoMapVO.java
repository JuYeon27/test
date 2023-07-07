package com.kgcoffee.web.kakaoMap.vo;

public class KakaoMapVO {

	// 주소명
	private String addressName;

	private String categoryGroupCode;

	private String categoryGroupName;

	private String categoryName;

	private int mapId;

	private String placeName;

	private String placeUrl;

	
	// 전화번호
	private String phone;
	// 도로명 주소명
	private String roadAddressName;
	// x 값
	private Double x;
	// y 값
	private Double y;
	
	
	public KakaoMapVO() {
		// TODO Auto-generated constructor stub
	}

	public KakaoMapVO(String addressName, String categoryGroupCode, String categoryGroupName, String categoryName,
			int mapId, String placeName, String placeUrl, String phone, String roadAddressName, Double x, Double y) {
		super();
		this.addressName = addressName;
		this.categoryGroupCode = categoryGroupCode;
		this.categoryGroupName = categoryGroupName;
		this.categoryName = categoryName;
		this.mapId = mapId;
		this.placeName = placeName;
		this.placeUrl = placeUrl;
		this.phone = phone;
		this.roadAddressName = roadAddressName;
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "KakaoMapVO [addressName=" + addressName + ", categoryGroupCode=" + categoryGroupCode
				+ ", categoryGroupName=" + categoryGroupName + ", categoryName=" + categoryName + ", mapId=" + mapId
				+ ", placeName=" + placeName + ", placeUrl=" + placeUrl + ", phone=" + phone + ", roadAddressName="
				+ roadAddressName + ", x=" + x + ", y=" + y + "]";
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getCategoryGroupCode() {
		return categoryGroupCode;
	}

	public void setCategoryGroupCode(String categoryGroupCode) {
		this.categoryGroupCode = categoryGroupCode;
	}

	public String getCategoryGroupName() {
		return categoryGroupName;
	}

	public void setCategoryGroupName(String categoryGroupName) {
		this.categoryGroupName = categoryGroupName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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

	public String getPlaceUrl() {
		return placeUrl;
	}

	public void setPlaceUrl(String placeUrl) {
		this.placeUrl = placeUrl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRoadAddressName() {
		return roadAddressName;
	}

	public void setRoadAddressName(String roadAddressName) {
		this.roadAddressName = roadAddressName;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}



}