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
public class LogInIdentity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CUSTOMER_ID")
	private Integer customerID;
	
	@Column(name = "CUSTOMER_NAME")
	private String customerName;
	
	@Column(name = "CUSTOMER_TEL")
	private String customerTel;
	
	@Column(name = "TABLE_NO")
	private Integer tableNo;
	
	@Column(name = "PARTY_NO")
	private Integer partyNo;
	
	@Column(name = "PAYMENT_TYPE")
	private String paymentType;
	
	@Column(name = "UPDATED_ON")
	private Date updatedOn;	


	public LogInIdentity(Integer customerID, String customerName, String customerTel,Integer tableNo,Integer partyNo,String paymentType, Date updatedOn) {

		this.customerID = customerID;
		this.customerName = customerName;
		this.customerTel = customerTel;
		this.tableNo = tableNo;
		this.partyNo = partyNo;
		this.paymentType = paymentType;
		this.updatedOn = updatedOn;
		
	}		
		
	public LogInIdentity() {
		
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

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

		
	public Integer getTableNo() {
		return tableNo;
	}

	public void setTableNo(Integer tableNo) {
		this.tableNo = tableNo;
	}

	public Integer getPartyNo() {
		return partyNo;
	}

	public void setPartyNo(Integer partyNo) {
		this.partyNo = partyNo;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}