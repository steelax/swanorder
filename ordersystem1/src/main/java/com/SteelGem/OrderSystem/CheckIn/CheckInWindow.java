package com.SteelGem.OrderSystem.CheckIn;

import java.util.Date;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;

import com.SteelGem.OrderSystem.Staff.StaffScreen;
import com.SteelGem.OrderSystem.Windows.StaffLogin;
import com.SteelGem.OrderSystem.entity.CheckInIdentity;
import com.SteelGem.OrderSystem.entity.StaffSession;
import com.SteelGem.OrderSystem.repository.CheckInIdentityRepository;
import com.SteelGem.OrderSystem.repository.ConfirmedOrdersRepository;
import com.SteelGem.OrderSystem.repository.StaffSessionRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;


public class CheckInWindow extends VerticalLayout{
	
	TextField name = new TextField("Name: ");
	
	TextField telNo = new TextField("Telephone Number: ");
	
	TextField email = new TextField("Email Address: ");
	
	TextField address = new TextField("Address: ");
	
	Button submitBtn = new Button("Submit");
	Button backBtn = new Button("Back");
	
	Date now = new Date();
	
	HorizontalLayout btnHL = new HorizontalLayout();
	
	boolean telCorrect = false;
	boolean nameCorrect = false;
	boolean emailCorrect = false;
	boolean addressCorrect = false;
 
	Notification notification;
	Notification notificationSubmit;

	//StaffLogin loginWin = new StaffLogin();
	boolean loginSuccess = false;
	
	StaffScreen parent;
	
	public CheckInWindow(StaffScreen parent){
	
		this.parent = parent;
		
		notification = new Notification(
		        "Must contain a valid name and one form of contact details", 2000,
		        Position.MIDDLE);
		
		notificationSubmit = new Notification(
		        "Details Submitted", 2000,
		        Position.MIDDLE);
		
		submitBtn.addClickListener(e -> Submit());
		backBtn.addClickListener(e -> BackToMain());

		btnHL.add(submitBtn);
		btnHL.add(backBtn);
		
		this.add(name);
		this.add(telNo);
		this.add(email);
		this.add(address);
		this.add(btnHL);
		setAlignItems(Alignment.CENTER);
	}
	
	
	void Submit() {

		
		CheckFullName();
		CheckAddressFormat();
		CheckEmailFormat();
		CheckEmailFormat();
		CheckTelFormat();
		
		System.out.println("nameCorrect " + nameCorrect + " telCorrect " + telCorrect + " emailCorrect " + emailCorrect+ " addressCorrect " + addressCorrect);
		if(nameCorrect == true && (telCorrect == true || emailCorrect == true ||  addressCorrect == true)) {
			CheckInIdentity cII = new CheckInIdentity();
			
			cII.setCustomerName(name.getValue());
			if(telCorrect == true) {
				cII.setCustomerTelNo(telNo.getValue());
			}else {
				cII.setCustomerTelNo(null);
			}
			if(emailCorrect == true) {
				cII.setCustomerEmail(email.getValue());
			}else{
				cII.setCustomerEmail(null);
			}
			if(addressCorrect == true) {
				cII.setCustomerAddress(address.getValue());
			}else{
				cII.setCustomerAddress(null);
			}
			cII.setUpdatedOn(now);
			
			parent.checkInIdentityRepo.save(cII);
			
			notificationSubmit.open();
			
			ClearScreen();
		}else {
			notification.open();
		}

		
	}
	
	void ClearScreen(){
		
		name.setValue("");
		telNo.setValue("");
		email.setValue("");
		address.setValue("");
		
		nameCorrect = false;
		telCorrect= false;
		emailCorrect= false;
		addressCorrect= false;
		
	}
	
	public void CheckFullName() {
		if (Pattern.matches("[a-zA-Z- ]+",name.getValue() ) != false){
			nameCorrect = true;
		}else {
			nameCorrect = false;
		}
	}
	
	public void CheckAddressFormat() {
		System.out.println(address.getValue());
		if (address.getValue()!= "") {
			addressCorrect = true;
		}else {
			addressCorrect = false;
		}
	}
	
	public void CheckEmailFormat() {
		System.out.println(address.getValue());
		if (email.getValue()!= "") {
			if(email.getValue().contains("@")) {
				emailCorrect = true;
			}else {
				emailCorrect = false;
			}
		}else {
			emailCorrect = false;
		}
	}
	
	public void BackToMain() {
		ClearScreen();
		parent.checkInButton.setEnabled(true);
		parent.menuButton.setEnabled(true);
		parent.contentPlaceHolder.removeAll();
		parent.contentPlaceHolder.add(parent.orderNumberDtls);
		parent.GetDetails();

	}
	
	public void CheckTelFormat() {
		telNo.setValue(telNo.getValue().replaceAll("\\s+",""));
		if( !telNo.getValue().equals("")) {
			if (Pattern.matches("[0-9]+",telNo.getValue() ) != false ){
				telCorrect = true;
			}else{
				telCorrect = false;
			}
		}else {
			telCorrect = false;
		}

	}
	
}
