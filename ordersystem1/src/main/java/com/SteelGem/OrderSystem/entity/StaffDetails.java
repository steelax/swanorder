package com.SteelGem.OrderSystem.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
public class StaffDetails implements Serializable{

	@Id
	@Column(name = "USER_ID")
	private String userId;
	
	@Column(name = "USERNAME")
	private String userName;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ADMIN")
	private String admin;

	@Column(name = "UPDATED_ON")
	private Date updatedOn;
	
	public StaffDetails(String userId, String userName, String password,String admin,Date updatedOn  ) {
		
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.admin = admin;
		this.updatedOn = updatedOn;
		
		
	}
	
	public StaffDetails() {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
	
}
