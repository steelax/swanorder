package com.SteelGem.OrderSystem.entity;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
public class AddonFlags implements Serializable{

	@Id
	@Column(name = "ID")
	private Integer ids;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "OFFER_SHANDY")
	private String offerShandy;
	
	@Column(name = "OFFER_MIXERS")
	private String offerMixers;
	
	@Column(name = "OFFER_CORDIAL")
	private String offerCordial;
	
	@Column(name = "OFFER_GLASS")
	private String offerGlass;
	
	@Column(name = "OFFER_ICE")
	private String offerIce;
	
	@Column(name = "OFFER_LIME")
	private String offerLime;
	
	@Column(name = "OFFER_REDBULL")
	private String offerRedbull;
	
	@Column(name = "OFFER_ORANGE")
	private String offerOrange;
	
	@Column(name = "OFFER_HALVES")
	private String offerHalves;
	
	@Column(name = "OFFER_DOUBLE")
	private String offerDouble;
	
	@Column(name = "OFFER_LARGE")
	private String offerLarge;
	
	@Column(name = "OFFER_DOUBLE_PROMO")
	private String offerDoublePromo;
	
	@Column(name = "OFFER_BOMB")
	private String offerBomb;

	@Column(name = "OFFER_HOT_WATER")
	private String offerHot;
	
	@Column(name = "OFFER_FLAVOURS")
	private String offerFlavours;
	
	@Column(name = "OFFER_SMALL")
	private String offerSmall;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	
	@Column(name = "UPDATED_ON")
	private Date updatedOn;
	
	
	
	public AddonFlags(Integer ids, String name, String offerShandy, String offerMixers, String offerCordial, String offerGlass, String offerIce,String offerLime,String offerRedbull,
			String offerOrange,String offerHalves,String offerDouble,String offerLarge,String offerDoublePromo,String offerBomb, String offerHot, String offerFlavours,String offerSmall, String updatedBy, Date updatedOn){
		this.ids = ids;
		this.name = name;
		this.offerShandy = offerShandy;
		this.offerMixers = offerMixers;
		this.offerCordial = offerCordial;
		this.offerGlass = offerGlass;
		this.offerIce = offerIce;
		this.offerLime = offerLime;
		this.offerRedbull = offerRedbull;
		this.offerOrange = offerOrange;
		this.offerHalves = offerHalves;
		this.offerDouble = offerDouble;
		this.offerLarge = offerLarge;
		this.offerDoublePromo = offerDoublePromo;
		this.offerBomb = offerBomb;
		this.offerHot = offerHot;
		this.offerFlavours = offerFlavours;
		this.offerSmall = offerSmall;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		
	}

	public AddonFlags() {
		
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

	public String getOfferShandy() {
		return offerShandy;
	}

	public void setOfferShandy(String offerShandy) {
		this.offerShandy = offerShandy;
	}

	public String getOfferMixers() {
		return offerMixers;
	}

	public void setOfferMixers(String offerMixers) {
		this.offerMixers = offerMixers;
	}

	public String getOfferCordial() {
		return offerCordial;
	}

	public void setOfferCordial(String offerCordial) {
		this.offerCordial = offerCordial;
	}

	public String getOfferGlass() {
		return offerGlass;
	}

	public void setOfferGlass(String offerGlass) {
		this.offerGlass = offerGlass;
	}

	public String getOfferIce() {
		return offerIce;
	}

	public void setOfferIce(String offerIce) {
		this.offerIce = offerIce;
	}

	public String getOfferLime() {
		return offerLime;
	}

	public void setOfferLime(String offerLime) {
		this.offerLime = offerLime;
	}

	public String getOfferRedbull() {
		return offerRedbull;
	}

	public void setOfferRedbull(String offerRedbull) {
		this.offerRedbull = offerRedbull;
	}

	public String getOfferOrange() {
		return offerOrange;
	}

	public void setOfferOrange(String offerOrange) {
		this.offerOrange = offerOrange;
	}

	public String getOfferHalves() {
		return offerHalves;
	}

	public void setOfferHalves(String offerHalves) {
		this.offerHalves = offerHalves;
	}

	public String getOfferDouble() {
		return offerDouble;
	}

	public void setOfferDouble(String offerDouble) {
		this.offerDouble = offerDouble;
	}

	public String getOfferLarge() {
		return offerLarge;
	}

	public void setOfferLarge(String offerLarge) {
		this.offerLarge = offerLarge;
	}

	public String getOfferDoublePromo() {
		return offerDoublePromo;
	}

	public void setOfferDoublePromo(String offerDoublePromo) {
		this.offerDoublePromo = offerDoublePromo;
	}

	public String getOfferBomb() {
		return offerBomb;
	}

	public void setOfferBomb(String offerBomb) {
		this.offerBomb = offerBomb;
	}

	public String getOfferHot() {
		return offerHot;
	}

	public void setOfferHot(String offerHot) {
		this.offerHot = offerHot;
	}

	
	public String getOfferFlavours() {
		return offerFlavours;
	}

	public void setOfferFlavours(String offerFlavours) {
		this.offerFlavours = offerFlavours;
	}

	public String getOfferSmall() {
		return offerSmall;
	}

	public void setOfferSmall(String offerSmall) {
		this.offerSmall = offerSmall;
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
		
	