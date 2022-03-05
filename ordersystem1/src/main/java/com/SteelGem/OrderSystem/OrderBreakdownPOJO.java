package com.SteelGem.OrderSystem;

public class OrderBreakdownPOJO {
	
	
	private Integer orderID;
	
	private String status;
	
	private double total;
	
	
	public OrderBreakdownPOJO(Integer orderID, String status, double total) {
		this.orderID = orderID;
		this.status = status;
		this.total = total;
	}
	
	public OrderBreakdownPOJO() {
		
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
}
