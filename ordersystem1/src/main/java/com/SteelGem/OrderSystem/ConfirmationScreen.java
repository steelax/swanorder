package com.SteelGem.OrderSystem;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;

public class ConfirmationScreen extends VerticalLayout {
	
	Label confLbl;
	
	
	Label currOrder;
	
	HorizontalLayout confHL = new HorizontalLayout();
	HorizontalLayout ordHL = new HorizontalLayout();
	
	public ConfirmationScreen(MainView parent){
		
		confHL.setClassName("padded-layout");
		
//		Label orderNumberLBLDtls = new Label("Order Number: ");
//		Label orderNumberLBL = new Label();
//		orderNumberLBL.setClassName("order-number");
//		orderNumberLBL.setText(parent.orderNumber);
		Icon confirmIcon = new Icon(VaadinIcon.CHECK);
		confirmIcon.setClassName("green-icon");
		confLbl = new Label("Your Order Has Been Confirmed!");
		confLbl.setClassName("bold-text");
		
		confHL.add(confirmIcon);
		confHL.add(confLbl);
		

		add(confHL);
//		add(orderNumberLBLDtls);
//		add(orderNumberLBL);
		setAlignItems(Alignment.CENTER);
	}
	
}
