package com.SteelGem.OrderSystem.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class ProductInfo implements Serializable{

	@Id
	@Column(name = "ID")
	private Integer ids;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "PRICE")
	private Double price;
	
	@Column(name = "ALTERNATE_PRICE")
	private Double alternatePrice;
	
	@Column(name = "ALTERNATE_PRICE_2")
	private Double alternatePrice2;
	
	@Column(name = "CATEGORY")
	private Integer category;
	
	@Column(name = "SUB_CATEGORY")
	private Integer subCategory;
	
	@Column(name = "IN_STOCK")
	private String inStock;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;

	
	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	
	
	public ProductInfo(Integer ids, String name, Double price, Double alternatePrice, Double alternatePrice2, Integer category,Integer subCategory,String inStock, String updatedBy, Date updatedOn){
		this.ids = ids;
		this.name = name;
		this.price = price;
		this.alternatePrice = alternatePrice;
		this.alternatePrice2 = alternatePrice2;
		this.category = category;
		this.subCategory = subCategory;
		this.inStock = inStock;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
	}
		
	public ProductInfo(){

	}

	public Integer getIds() {
		return ids;
	}

	public void setIds(Integer ids) {
		this.ids = ids;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getAlternatePrice() {
		return alternatePrice;
	}

	public void setAlternatePrice(Double alternatePrice) {
		this.alternatePrice = alternatePrice;
	}
	
	public Double getAlternatePrice2() {
		return alternatePrice2;
	}

	public void setAlternatePrice2(Double alternatePrice2) {
		this.alternatePrice2 = alternatePrice2;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(Integer subCategory) {
		this.subCategory = subCategory;
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