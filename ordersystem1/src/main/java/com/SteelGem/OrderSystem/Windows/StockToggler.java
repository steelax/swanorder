package com.SteelGem.OrderSystem.Windows;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.SteelGem.OrderSystem.Staff.StaffScreen;
import com.SteelGem.OrderSystem.entity.ProductInfo;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class StockToggler extends VerticalLayout {

	VerticalLayout vL = new VerticalLayout();
	
	HorizontalLayout hL = new HorizontalLayout();
	
	Grid<ProductInfo> stockGrid = new Grid<>(ProductInfo.class);
	
	StaffScreen parent;
	
	Button backButton = new Button("Back");
	
	public StockToggler(StaffScreen parent) {

		stockGrid.removeAllColumns();
		stockGrid.addColumn("name");
		stockGrid.addColumn("inStock");
		stockGrid.getColumnByKey("name").setWidth("80%");
		stockGrid.getColumnByKey("inStock").setWidth("20%");
		stockGrid.getColumnByKey("name").setHeader("Item");
		stockGrid.setHeight("270px");
		stockGrid.asSingleSelect().addValueChangeListener(event -> {
			if(event.getValue() != null) {
				Integer iD = event.getValue().getIds();
				String name = event.getValue().getName();
				String stock = event.getValue().getInStock();
				TogglePopup(iD,name,stock);
			}
			
		});
		
		backButton.addClickListener(e -> {
			parent.checkInButton.setEnabled(true);
			parent.menuButton.setEnabled(true);
			parent.contentPlaceHolder.removeAll();
			parent.contentPlaceHolder.add(parent.orderNumberDtls);
			parent.GetDetails();
			
		});
		
		vL.add(stockGrid);
		vL.add(backButton);

		this.parent = parent;
		setWidth("100%");

		add(vL);
		
	}
	
	public void getList() {
		Collection<ProductInfo> prodList = parent.productInfoRepo.findAll();
		
		ArrayList<ProductInfo> prodArray = new ArrayList<ProductInfo>();
		
		for(ProductInfo o: prodList) {
			if(o.getCategory() != 8) {
				prodArray.add(o);
			}
		}
	
		
		stockGrid.setItems(prodArray);
		
	}
	
	public void TogglePopup(Integer iD, String name, String flag) {
		ProductInfo pI = parent.productInfoRepo.findByIds(iD);

		Dialog popup = new Dialog();
		popup.setCloseOnOutsideClick(false);
		popup.setCloseOnEsc(false);
		
		VerticalLayout popVL = new VerticalLayout();
		HorizontalLayout popHL = new HorizontalLayout();
		Label header = new Label("Change Stock For " + name);
		Checkbox inStock = new Checkbox();

		
		boolean initialFlag;
		
		if(pI.getInStock().equals("Y")) {
			inStock.setValue(true);
			initialFlag = true;
		}else {
			inStock.setValue(false);
			initialFlag = false;
		}
		Button submit = new Button("Change");
		submit.addClickListener(e -> {
			String changer = pI.getInStock();
			if(!inStock.getValue().equals(initialFlag)) {
				if(inStock.getValue().equals(true)) {
					changer = "Y";
				}else {
					changer = "N";
				}
				
				pI.setInStock(changer);
				pI.setUpdatedOn(new Date());
				parent.productInfoRepo.save(pI);
				getList();
				popup.close();
			}
			
		});
		
		Button back = new Button("Cancel");
		back.addClickListener(e -> popup.close());
		
		popHL.add(submit);
		popHL.add(back);
		popVL.add(header);
		popVL.add(inStock);
		popVL.add(popHL);
		popup.add(popVL);
		
		popup.open();
	}
}
