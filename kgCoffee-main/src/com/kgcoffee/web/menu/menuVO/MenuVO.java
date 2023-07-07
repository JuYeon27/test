package com.kgcoffee.web.menu.menuVO;

public class MenuVO {
	
	private int menuId;
	private String fileName;
	private String caffeineType;
	private String menuName;
	private int  menuPrice;
	private String menuExplain;
	private String menuType;
	
	public MenuVO(int menuId, String fileName, String caffeineType, String menuName, int menuPrice,
			String menuExplain, String menuType) {
		
		//super();
		this.menuId = menuId;
		this.fileName = fileName;
		this.caffeineType = caffeineType;
		this.menuName = menuName;
		this.menuPrice = menuPrice;
		this.menuExplain = menuExplain;
		this.menuType = menuType;
	}
	

	@Override
	public String toString() {
		return "MenuVO [menuId=" + menuId + ", fileName=" + fileName + ", caffeineType=" + caffeineType + ", menuName="
				+ menuName + ", menuPrice=" + menuPrice + ", menuExplain=" + menuExplain + ", menuType=" + menuType
				+ "]";
	}


	public MenuVO() {}
	
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getCaffeineType() {
		return caffeineType;
	}
	public void setCaffeineType(String caffeineType) {
		this.caffeineType = caffeineType;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getMenuPrice() {
		return menuPrice;
	}
	public void setMenuPrice(int menuPrice) {
		this.menuPrice = menuPrice;
	}
	public String getMenuExplain() {
		return menuExplain;
	}
	public void setMenuExplain(String menuExplain) {
		this.menuExplain = menuExplain;
	}
	public String getMenuType() {
		return menuType;
	}
	public void setMenuType(String menuType) {
		this.menuType = menuType;
	}
	
	
	
}
