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
public class CheckInIdentity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CHK_CUSTOMER_ID")
	private Integer customerID;
	
	@Column(name = "CHK_CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "CHK_CUSTOMER_TEL_NO")
	private String customerTelNo;
		
	@Column(name = "CHK_CUSTOMER_EMAIL")
	private String customerEmail;
	
	@Column(name = "CHK_CUSTOMER_ADDRESS")
	private String customerAddress;
	
	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	public CheckInIdentity(Integer customerID, String customerName, String customerTelNo, String customerEmail,
			String customerAddress, Date updatedOn) {
		this.customerID = customerID;
		this.customerName = customerName;
		this.customerTelNo = customerTelNo;
		this.customerEmail = customerEmail;
		this.customerAddress = customerAddress;
		this.updatedOn = updatedOn;
	}

	public CheckInIdentity() {
		// TODO Auto-generated constructor stub
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

	public String getCustomerTelNo() {
		return customerTelNo;
	}

	public void setCustomerTelNo(String customerTelNo) {
		this.customerTelNo = customerTelNo;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}	

	
}
