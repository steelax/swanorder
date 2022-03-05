package com.SteelGem.OrderSystem;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.SteelGem.OrderSystem.entity.ConfirmedOrders;
import com.SteelGem.OrderSystem.entity.OrderDetails;
import com.SteelGem.OrderSystem.entity.ProductInfo;
import com.SteelGem.OrderSystem.entity.WrkItemsInOrder;
import com.SteelGem.OrderSystem.repository.WrkItemsInOrderRepository;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.FooterRow;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
@Transactional
public class Basket extends VerticalLayout {

	Collection<WrkItemsInOrder> wIIOCol;
	
	public MainView parent;
	
	Grid<BasketContentPOJO> basketGrid;
	
	double costTotal = 0;
	
	Label costTotalLbl;
	TextArea comments;
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	
	Collection<BasketContentPOJO> bCPCol;
	
	 @PersistenceContext
	 private EntityManager em;

	 String flavour;
	 
	private ArrayList<Integer> parentItemNoList;
	
	public Basket(MainView parent) {
		this.parent = parent;
		Column<BasketContentPOJO> nameCol;

		Label msg = new Label("Click an item to Cancel");
		msg.addClassName("gap-label");
		Icon backIcon = new Icon(VaadinIcon.ARROW_BACKWARD);
		
		Button back = new Button(backIcon);
		back.addClickListener(e-> parent.BackToPrevScreen());
		
		basketGrid = new Grid<>(BasketContentPOJO.class);
		basketGrid.removeAllColumns();
		basketGrid.addColumn("itemGroup");
		basketGrid.addColumn("itemName");
		basketGrid.addColumn("price");
		basketGrid.setClassName("normal-Font");
		basketGrid.setHeightByRows(true);
		basketGrid.getColumnByKey("itemGroup").setSortable(false);
		basketGrid.getColumnByKey("itemGroup").setWidth("50px");
		basketGrid.getColumnByKey("itemGroup").setHeader("ID");
		basketGrid.getColumnByKey("itemName").setWidth("200px");
		basketGrid.getColumnByKey("itemName").setHeader("Item");
		basketGrid.getColumnByKey("itemName").setSortable(false);
		basketGrid.getColumnByKey("price").setWidth("50px");
		basketGrid.getColumnByKey("price").setHeader("£");
		basketGrid.getColumnByKey("price").setSortable(false);
		basketGrid.asSingleSelect().addValueChangeListener(event -> {
			if(event.getValue() != null) {
				RemoveItemFromBasketWin(event.getValue().getMainItem(),event.getValue().getItemGroup(),event.getValue().getCustID());	
			}
		});
		


		
		costTotalLbl = new Label();
				
		comments = new TextArea("Additional Requests: (e.g.Drink Flavors, Extra Ice etc)");
		comments.setMaxLength(200);
		comments.setClassName("text-area-style");
		
		HorizontalLayout msgHL = new HorizontalLayout();
		
		msgHL.add(back);
		msgHL.add(msg);
		this.add(msgHL);
		this.add(basketGrid);
		this.add(comments);
		this.add(costTotalLbl);
		

	}
	public void RemoveItemFromBasket(int itemGroup, Dialog aYSW, Integer custId) {
	
		Collection<WrkItemsInOrder> wIIOrder = parent.wrkItemsInOrderRepo.findByParentItemNoAndCustomerID(itemGroup,custId);
		for(WrkItemsInOrder o : wIIOrder) {
			o.setCancelledFlag("Y");
			parent.wrkItemsInOrderRepo.save(o);
		}
		GetItemsForBasket();
		aYSW.close();

	}
	
	public void RemoveItemFromBasketWin(String itemName, int itemGroup, int custID) {
		Dialog aYSW = new Dialog();
		Label aYSLbl = new Label("Are you sure you want to remove " + itemName + " from your basket?");
		Button yesBtn = new Button("Yes");
		yesBtn.addClickListener(e -> RemoveItemFromBasket(itemGroup,aYSW,custID));
		Button noBtn = new Button("No");
		noBtn.addClickListener(e -> aYSW.close());
		HorizontalLayout btnHL = new HorizontalLayout();
		btnHL.add(yesBtn);
		btnHL.add(noBtn);
		VerticalLayout aYSVL = new VerticalLayout();
		aYSVL.add(aYSLbl);
		aYSVL.add(btnHL);
		aYSVL.setAlignItems(Alignment.CENTER);


		aYSW.add(aYSVL);
		aYSW.open();
	}
	
	public void GetItemsForBasket(){
		
		costTotal = 0;
		wIIOCol = parent.wrkItemsInOrderRepo.findByCustomerID(parent.custID);

		bCPCol = new ArrayList<>() ;
		double MainDrinkAlt = 0;
		double MainDrinkAlt2 = 0;
		String mainDrink = null;
		for(WrkItemsInOrder o : wIIOCol) {
			if(o.getCancelledFlag().equals("N")) {
				BasketContentPOJO bCP = new BasketContentPOJO();
				ProductInfo pI = parent.productInfoRepo.findByIds(o.getItemGenericID());
	

				
				if(pI.getIds().equals(o.getMainDrinkId())) {
					mainDrink = pI.getName();
				}
				
				if(pI.getCategory()!=8) {
					if(o.getFlavourID() != null ) {
						System.out.println("flav");
						bCP.setItemName(pI.getName() + " -" +o.getFlavourID());
					}else {
						System.out.println("noflav");
						bCP.setItemName(pI.getName());
					}
					
				}else {
					bCP.setItemName("--"+pI.getName()+ "      +");
				}
				if(!pI.getCategory().equals(8)) {
					if(pI.getAlternatePrice() != null) {
						System.out.println("GETSSHERE " + pI.getCategory());
						MainDrinkAlt = pI.getAlternatePrice();
					}
					if(pI.getAlternatePrice2() != null) {
						System.out.println("GETSSHERE " + pI.getCategory());
						MainDrinkAlt2 = pI.getAlternatePrice2();
					}
				}
	
				if(pI.getSubCategory().equals(33)) {
					bCP.setPrice(MainDrinkAlt);
				}else if(pI.getSubCategory().equals(38)) {
					bCP.setPrice(MainDrinkAlt2);
				}else{
					bCP.setPrice(pI.getPrice());	
				}
				costTotal = costTotal + bCP.getPrice();
				System.out.println(bCP.getPrice() +" price on bCP");
				bCP.setItemGroup(o.getParentItemNo());
				
				bCP.setMainItem(mainDrink);
				
				bCP.setCustID(o.getCustomerID());
				
				bCPCol.add(bCP);
				}
			}
	
			costTotalLbl.setText("Total: £" +df2.format(costTotal));
			basketGrid.setItems(bCPCol);
			
			System.out.println("tot cost " + costTotal);
			if(costTotal > 0 ) {
				parent.placeOrderBtn.setEnabled(true);
			}else {
				parent.placeOrderBtn.setEnabled(false);
			}
		
		
	}
	
	public void PopulateTable() {
		

		ConfirmedOrders cO = new ConfirmedOrders();
		
		cO.setCustomerID(parent.custID);
		cO.setCustomerName(parent.custName);
		cO.setTableNumber(parent.tableNo);
		cO.setOrderStatus(ConfirmedOrders.ORDER_STATUS_PENDING);
		cO.setOrderTotal(costTotal);
		cO.setComments(comments.getValue());
		cO.setUpdatedOn(new Date());

		parent.confirmedOrdersRepo.save(cO);
		
		Collection<WrkItemsInOrder> wIIOrder = parent.wrkItemsInOrderRepo.findByCustomerIDAndCancelledFlag(parent.custID,"N");
		parentItemNoList = null;
		Integer mainItem = null;
			for(WrkItemsInOrder o : wIIOrder) {
			if(o != null) {

				System.out.println(o.getCancelledFlag());
				System.out.println(o.getCustomerID());
				System.out.println(o.getItemGenericID());
				System.out.println(o.getOrderItemID());
				System.out.println(o.getParentItemNo());
				System.out.println(o.getUpdatedOn());
				
				
				OrderDetails oD = new OrderDetails();
				
				ProductInfo productInfo = parent.productInfoRepo.findByIds(o.getItemGenericID());
				System.out.println(productInfo.getName());

				System.out.println(mainItem + " MainItem");
				if(productInfo.getCategory().equals(8)) {
					oD.setItemName("---" + productInfo.getName());
				}else {

					if(o.getFlavourID() != null) {
						oD.setItemName(productInfo.getName() + " -" + o.getFlavourID());
						mainItem = productInfo.getIds();
					}else {
						mainItem = productInfo.getIds();
						oD.setItemName(productInfo.getName());
					}
				}
				System.out.println(oD.getItemName());
				if(productInfo.getSubCategory().equals(33) ){
					ProductInfo mainProductInfo = parent.productInfoRepo.findByIds(mainItem);
					oD.setItemCost(mainProductInfo.getAlternatePrice());
				}else if(productInfo.getSubCategory().equals(38) ){
					ProductInfo mainProductInfo = parent.productInfoRepo.findByIds(mainItem);
					oD.setItemCost(mainProductInfo.getAlternatePrice2());
				}else {
					oD.setItemCost(productInfo.getPrice());
				}
				System.out.println("oD.getItemCost()" + oD.getItemCost());
				oD.setParentItemNo(o.getParentItemNo());
				System.out.println("getOrderID" + oD.getParentItemNo());
				oD.setOrderId(cO.getOrderID());
				System.out.println("getOrderId" + oD.getOrderId());
				oD.setUpdatedOn(new Date());
				System.out.println("oD.getUpdatedOn()" + oD.getUpdatedOn());
				parent.orderDetailsRepo.save(oD);
			}
		}

		parent.orderNumber = cO.getOrderID().toString();

		}
		
}
