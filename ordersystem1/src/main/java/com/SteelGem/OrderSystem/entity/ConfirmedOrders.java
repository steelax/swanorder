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
public class ConfirmedOrders implements Serializable{
	
	public static final String ORDER_STATUS_PENDING = "PENDING";
	public static final String ORDER_STATUS_PREPARED = "READY";
	public static final String ORDER_STATUS_PAID = "PAID";
	public static final String ORDER_STATUS_CANCEL = "CANCELLED";
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ORDER_ID")
	private Integer orderID;
	
	@Column(name = "CUSTOMER_ID")
	private Integer customerID;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "TABLE_NUMBER")
	private Integer tableNumber;
	
	@Column(name = "ORDER_STATUS")
	private String orderStatus;
	
	@Column(name = "ORDER_TOTAL")
	private Double orderTotal;
	
	@Column(name = "STAFF_RECEIVED")
	private String staffReceived;
	
	@Column(name = "STAFF_CONFIRMED")
	private String staffConfirmed;
	
	@Column(name = "COMMENTS")
	private String comments;
	
	@Column(name = "UPDATED_ON")
	private Date updatedOn;	


	public ConfirmedOrders(Integer orderID, Integer customerID, String customerName,Integer tableNumber, String orderStatus, Double orderTotal, String staffReceived,String staffConfirmed, String comments, Date updatedOn) {

		this.orderID = orderID;
		this.customerName = customerName;
		this.tableNumber = tableNumber;
		this.orderStatus = orderStatus;
		this.orderTotal = orderTotal;
		this.staffReceived = staffReceived;
		this.staffConfirmed = staffConfirmed;
		this.comments = comments;
		this.updatedOn = updatedOn;
		
	}		
		
	public ConfirmedOrders() {
		
	}

	public Integer getOrderID() {
		return orderID;
	}

	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	
	public Integer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public Integer getTableNumber() {
		return tableNumber;
	}

	public void setTableNumber(Integer tableNumber) {
		this.tableNumber = tableNumber;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getStaffReceived() {
		return staffReceived;
	}

	public void setStaffReceived(String staffReceived) {
		this.staffReceived = staffReceived;
	}

	public String getStaffConfirmed() {
		return staffConfirmed;
	}

	public void setStaffConfirmed(String staffConfirmed) {
		this.staffConfirmed = staffConfirmed;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
}
