package com.SteelGem.OrderSystem;

import java.util.Date;

public class OrderDetailsPOJO {
	
	
	private Integer orderID;
	
	private String name;
	
	private String status;
	
	private Integer tableNumber;
	
	private double total;
	
	private String orderTime;
	
	
	public OrderDetailsPOJO(Integer orderID, String name, String status, Integer tableNumber, double total, String orderTime) {
		this.orderID = orderID;
		this.name = name;
		this.status = status;
		this.tableNumber = tableNumber;
		this.total = total;
		this.orderTime = orderTime;
	}
	
	public OrderDetailsPOJO() {
		
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(Integer tableNumber) {
		this.tableNumber = tableNumber;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	
	
	
	
}
