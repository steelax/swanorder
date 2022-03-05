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
public class StaffSession implements Serializable{
	
	@Id
	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "IP_ADDRESS")
	private String ipAddress;
	
	@Column(name = "SESSION_ID")
	private String sessionID;
	
	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	public StaffSession(String userId,String ipAddress,String sessionID, Date updatedOn) {
		
		this.userId = userId;
		this.ipAddress = ipAddress;
		this.sessionID = sessionID;
		this.updatedOn = updatedOn;
	}
	
	public StaffSession() {
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	
	
}
