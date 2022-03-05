package com.SteelGem.OrderSystem.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class FlavourInfo implements Serializable{

	@Id
	@Column(name = "FLAVOUR_ID")
	private Integer flavourID;
	
	@Column(name = "FLAVOUR_NAME")
	private String flavourName;
	
	@Column(name = "DRINK_ID")
	private Integer drinkID;
	
	@Column(name = "ADDITIONAL_PRICE")
	private Double additionalPrice;
		
	@Column(name = "IN_STOCK")
	private String inStock;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;

	
	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	
	
	
	public FlavourInfo(Integer flavourID, String flavourName, Integer drinkID, Double additionalPrice,
			String inStock, String updatedBy, Date updatedOn) {
		this.flavourID = flavourID;
		this.flavourName = flavourName;
		this.drinkID = drinkID;
		this.additionalPrice = additionalPrice;
		this.inStock = inStock;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}

	public FlavourInfo(){

	}

	public Integer getFlavourID() {
		return flavourID;
	}

	public void setFlavourID(Integer flavourID) {
		this.flavourID = flavourID;
	}

	public String getFlavourName() {
		return flavourName;
	}

	public void setFlavourName(String flavourName) {
		this.flavourName = flavourName;
	}

	public Integer getDrinkID() {
		return drinkID;
	}

	public void setDrinkID(Integer drinkID) {
		this.drinkID = drinkID;
	}

	public Double getAdditionalPrice() {
		return additionalPrice;
	}

	public void setAdditionalPrice(Double additionalPrice) {
		this.additionalPrice = additionalPrice;
	}

	public String getInStock() {
		return inStock;
	}

	public void setInStock(String inStock) {
		this.inStock = inStock;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

}