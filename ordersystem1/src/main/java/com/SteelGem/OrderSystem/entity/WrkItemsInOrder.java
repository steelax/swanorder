package com.SteelGem.OrderSystem.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class WrkItemsInOrder implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ITEM_ID")
	private Integer orderItemID;
	
	@Column(name = "CUSTOMER_ID")
	private Integer customerID;
	
	@Column(name = "ITEM_GENERIC_ID")
	private Integer itemGenericID;
	
	@Column(name = "PARENT_ITEM_NO")
	private Integer parentItemNo;
	
	@Column(name = "CANCELLED_FLAG")
	private String cancelledFlag;
	
	@Column(name = "MAIN_DRINK_ID")
	private Integer mainDrinkId;
	
	@Column(name = "UPDATED_ON")
	private Date updatedOn;	

	@Column(name = "FLAVOUR_ID") 
	private String flavourID;
	
	public WrkItemsInOrder(Integer orderItemID, Integer customerID,  Integer itemGenericID,Integer parentItemNo,Integer itemSubCat,String cancelledFlag, Integer mainDrinkId, Date updatedOn, String flavourID) {

		this.orderItemID = orderItemID;
		this.customerID = customerID;
		this.itemGenericID = itemGenericID;
		this.parentItemNo = parentItemNo;
		this.cancelledFlag = cancelledFlag;
		this.updatedOn = updatedOn;
		this.mainDrinkId = mainDrinkId;
		this.flavourID = flavourID;
		
	}
	
	public WrkItemsInOrder() {
		
	}

	public Integer getOrderItemID() {
		return orderItemID;
	}


	public void setOrderItemID(Integer orderItemID) {
		this.orderItemID = orderItemID;
	}


	public Integer getCustomerID() {
		return customerID;
	}


	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}


	public Integer getItemGenericID() {
		return itemGenericID;
	}


	public void setItemGenericID(Integer itemGenericID) {
		this.itemGenericID = itemGenericID;
	}


	
	public Integer getParentItemNo() {
		return parentItemNo;
	} 

	public void setParentItemNo(Integer parentItemNo) {
		this.parentItemNo = parentItemNo;
	}
	
	public String getCancelledFlag() {
		return cancelledFlag;
	}

	public void setCancelledFlag(String cancelledFlag) {
		this.cancelledFlag = cancelledFlag;
	}

	public Integer getMainDrinkId() {
		return mainDrinkId;
	}

	public void setMainDrinkId(Integer mainDrinkId) {
		this.mainDrinkId = mainDrinkId;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}


	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getFlavourID() {
		return flavourID;
	}

	public void setFlavourID(String flavourID) {
		this.flavourID = flavourID;
	}		
		
	
	
}