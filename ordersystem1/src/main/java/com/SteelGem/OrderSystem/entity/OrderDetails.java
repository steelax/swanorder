package com.SteelGem.OrderSystem.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class OrderDetails implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ITEM_ID")
	private Integer orderItemID;
	
	@Column(name = "ITEM_NAME")
	private String itemName;
	
	@Column(name = "ITEM_COST")
	private Double itemCost;
	
	@Column(name = "PARENT_ITEM_NO")
	private Integer parentItemNo;
	
	@Column(name = "ORDER_ID")
	private Integer orderId;
	
	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	public OrderDetails(Integer orderItemID, String itemName, Double itemCost,Integer parentItemNo, Integer orderId, Date updatedOn) {
		this.orderItemID = orderItemID;
		this.itemName = itemName;
		this.itemCost = itemCost;
		this.parentItemNo = parentItemNo;
		this.orderId = orderId;
		this.updatedOn = updatedOn;

	}
	
	public OrderDetails() {
		
	}

	public Integer getOrderItemID() {
		return orderItemID;
	}

	public void setOrderItemID(Integer orderItemID) {
		this.orderItemID = orderItemID;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Double getItemCost() {
		return itemCost;
	}

	public void setItemCost(Double itemCost) {
		this.itemCost = itemCost;
	}
	
	public Integer getParentItemNo() {
		return parentItemNo;
	}

	public void setParentItemNo(Integer parentItemNo) {
		this.parentItemNo = parentItemNo;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}


	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
	
}
