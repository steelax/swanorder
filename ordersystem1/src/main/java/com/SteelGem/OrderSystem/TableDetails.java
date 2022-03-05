package com.SteelGem.OrderSystem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;

import com.SteelGem.OrderSystem.entity.LogInIdentity;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class TableDetails extends VerticalLayout {

	HorizontalLayout tableHL = new HorizontalLayout();
	
	TextField tableNumber;
	
	Select<String> tableCBO;
	Select<String> partyCBO;
	
	TextField name;
	
	TextField telNo;
	
	RadioButtonGroup<String> paymentType;
	
	
	MainView parent;
	
	boolean allCorrect = false;
	
	boolean nameCorrect = false;
	boolean telCorrect = false;
	boolean tableCorrect = false;
	boolean partyCorrect = false;
	
	List<String> tabData = new ArrayList();
	List<String> partyData = new ArrayList();;
	
	Notification notification;
	
	public TableDetails(MainView parent) {
		
		notification = new Notification(
		        "Details Not Valid", 2000,
		        Position.MIDDLE);
		
		this.parent = parent;
		
		List<String> data = Arrays.asList("Card (Contactless)", "Cash");

		
		tableCBO = new Select<String>();
		tableCBO.setLabel("Table No:");
		tableCBO.addValueChangeListener(e->{
			TableNoCheck();
			parent.nextBtn.setEnabled(true);
		});
		
//		partyCBO = new Select<String>();
//		partyCBO.setLabel("Party Size:");
//		partyCBO.setWidth("80px");
//		partyCBO.addValueChangeListener(e->{
//			TableNoCheck();
//			parent.nextBtn.setEnabled(true);
//		});
				
		name = new TextField("Name");
		name.addValueChangeListener(e->{
			CheckFullName();
			parent.nextBtn.setEnabled(true);
		});
		
//		telNo = new TextField("Contact Details *");
//		telNo.addValueChangeListener(e->{
//			CheckTelFormat();
//			parent.nextBtn.setEnabled(true);
//		});
		//telNo.setMaxLength(11);


		paymentType = new RadioButtonGroup<>();
		paymentType.setLabel("Select Payment Method *");
		paymentType.setItems(data);
		paymentType.setValue(data.get(0));
		paymentType.isRequired();
		
		
		tableHL.add(tableCBO/*, partyCBO*/);
		add(tableHL);
		add(name);
		//add(telNo);
		add(paymentType);
		setAlignItems(Alignment.CENTER);
	}
	
	
	public void TableNoCheck() {
			if (tableCBO.getValue() == null) {
				tableCorrect = false;
			}else {
				tableCorrect = true;
			}
	}
	
	public void PartyNoCheck() {
		if (partyCBO.getValue() == null) {
			partyCorrect = false;
		}else {
			partyCorrect = true;
		}
}
	
	public void CheckFullName() {
		

		if (Pattern.matches("[a-zA-Z- ]+",name.getValue() ) != false){
			nameCorrect = true;
			name.removeClassName("red-text");
			name.setLabel("Full Name *");
		}else {
			nameCorrect = false;
		}
	}
	
	
//	public void CheckTelFormat() {
//		telNo.setValue(telNo.getValue().replaceAll("\\s+",""));
//		System.out.println("here : " + telNo.getValue());
////		if( !telNo.getValue().equals("")) {
////			if (Pattern.matches("[0-9]+",telNo.getValue() ) != false ){
////				telNo.removeClassName("red-text");
////				telNo.setLabel("Telephone Number ");
////				telCorrect = true;
////			}else{
////				telNo.setLabel("Telephone Number INVALID");
////				telNo.addClassName("red-text");
////				telCorrect = false;
////			}
////		}else {
////			telNo.removeClassName("red-text");
////			telNo.setLabel("Telephone Number ");
////			telCorrect = true;
////		}
//		if (telNo.getValue()== "") {
//			telNo.setLabel("Contact Details * INVALID");
//			telNo.addClassName("red-text");
//			telCorrect = false;
//		}else {
//			telNo.removeClassName("red-text");
//			telNo.setLabel("Contact Details *");
//			telCorrect = true;
//		}
//	}
	
	
	public void CheckFlags(){
		if(/*telCorrect ==true &&*/ tableCorrect ==true && nameCorrect ==true/* && partyCorrect == true*/) {
			allCorrect = true;
		}else {
			allCorrect = false;
		}
	}

	public void UpdateCustomerTable() {
		TableNoCheck();
		//PartyNoCheck();
		CheckFullName();
		//CheckTelFormat();
		CheckFlags();
		System.out.println(allCorrect);
		if(allCorrect == true) {
			LogInIdentity lII = new LogInIdentity();
			
			Date now = new Date();
	
			lII.setCustomerName(name.getValue());
			lII.setCustomerTel(null);
			lII.setTableNo(Integer.parseInt(tableCBO.getValue()));
			lII.setPartyNo(null);
			lII.setPaymentType(paymentType.getValue());
			lII.setUpdatedOn(now);
			
			System.out.println("Name " + lII.getCustomerName() + " tel " + lII.getCustomerTel() + " tableNumber " +lII.getTableNo() + " Payment Type " + lII.getPaymentType());
			
			parent.logInIdentityRepo.save(lII);
			System.out.println("ID" + lII.getCustomerID());
			parent.custID = lII.getCustomerID();
			parent.tableNo = lII.getTableNo();
			parent.custName = lII.getCustomerName();
			parent.smallLogo.addClassName("img-style");
			parent.holderVL.remove(parent.tableDetails);
			parent.holderVL.remove(parent.logo);
			parent.holderVL.add(parent.smallLogo);
			parent.holderVL.add(parent.menu);
			parent.remove(parent.nextBtn);
			parent.add(parent.confirmBtn);
			
		}else {
			notification.open();
		                
		}
	}
	
}
