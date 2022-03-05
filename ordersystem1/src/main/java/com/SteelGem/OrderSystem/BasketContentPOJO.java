package com.SteelGem.OrderSystem;

public class BasketContentPOJO {

	private String itemName;
	
	private double price; 
	
	private Integer itemGroup;
	
	private String mainItem;
	
	private Integer custID;
	
	public BasketContentPOJO(String itemName,double price, Integer itemGroup, String mainItem, Integer custID ) {
		
		this.itemName = itemName;
		this.price = price;
		this.itemGroup = itemGroup;
		this.mainItem = mainItem;
		this.custID = custID;
	}
	
	public BasketContentPOJO() {
		
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getItemGroup() {
		return itemGroup;
	}

	public void setItemGroup(Integer itemGroup) {
		this.itemGroup = itemGroup;
	}

	public String getMainItem() {
		return mainItem;
	}

	public void setMainItem(String mainItem) {
		this.mainItem = mainItem;
	}

	public Integer getCustID() {
		return custID;
	}

	public void setCustID(Integer custID) {
		this.custID = custID;
	}
	
	
	
	
}
