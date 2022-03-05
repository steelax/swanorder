package com.SteelGem.OrderSystem.Windows;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import com.SteelGem.OrderSystem.Staff.StaffScreen;
import com.SteelGem.OrderSystem.entity.StaffDetails;
import com.SteelGem.OrderSystem.entity.StaffSession;
import com.SteelGem.OrderSystem.repository.StaffDetailsRepository;
import com.SteelGem.OrderSystem.repository.StaffSessionRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;

public class StaffLogin extends Dialog {

	VerticalLayout loginVL= new VerticalLayout();
	
	Label title;
	
	TextField userId;
	
	PasswordField passCode;
	
	Button loginButton;
	
	Notification errorMsg;
		
	StaffScreen parent;
	
	@Autowired
	public
	StaffSessionRepository staffSessionRepo;
	
	
	public StaffLogin(StaffScreen parent) {
		
		this.parent = parent;
		
		
		errorMsg = new Notification(
		        "Details Not Valid", 2000,
		        Position.MIDDLE);
		
		title = new Label("Log In");
		
		userId = new TextField("User Name");

		passCode = new PasswordField("Password");
	
		loginButton = new Button("Log In");
		loginButton.addClickListener(e -> LoginConfirmation());
		
		loginVL.add(title);
		loginVL.add(userId);
		loginVL.add(passCode);
		loginVL.add(loginButton);
		loginVL.setAlignItems(Alignment.CENTER);
		
		this.add(loginVL);
		this.setModal(true);
		this.setCloseOnOutsideClick(false);
		
		
		
	}
	
	public void LoginConfirmation() {

		Collection<StaffDetails> sD = parent.staffDetailsRepo.findAll();
		boolean matchUser =false;
		boolean matchPassword = false;
		if(sD != null) {
			for(StaffDetails o : sD) {
				if(o != null) {
					if(o.getUserId().equals(userId.getValue())) {
						matchUser=true; 
						if(o.getPassword().equals(passCode.getValue())) {
							matchPassword = true;
							parent.userId = o.getUserId();
						}	

					}
				}
			}
			System.out.println("" + matchUser + matchPassword) ;
			if(matchUser == true && matchPassword == true) {
				this.close();
				parent.GetDetails();
				parent.loginSuccess = true;
				
				StaffSession sSession = parent.staffSessionRepo.findByUserId(userId.getValue());
				if(sSession == null) {
					sSession = new StaffSession();
					sSession.setUserId(userId.getValue());
				}
				sSession.setSessionID(parent.browserID);
				sSession.setIpAddress(parent.currentIP);
				sSession.setUpdatedOn(new Date());
				parent.staffSessionRepo.save(sSession);
				

			}else {
				errorMsg.open();
				parent.loginSuccess = false;
			}
			
		}
	}
}
