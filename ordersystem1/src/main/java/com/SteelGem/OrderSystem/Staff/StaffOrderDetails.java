package com.SteelGem.OrderSystem.Staff;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import java.text.DecimalFormat;
import java.util.Collection;

import com.SteelGem.OrderSystem.entity.ConfirmedOrders;
import com.SteelGem.OrderSystem.entity.LogInIdentity;
import com.SteelGem.OrderSystem.entity.OrderDetails;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextArea;

public class StaffOrderDetails extends VerticalLayout {

	private HorizontalLayout orderNumHLayout = new HorizontalLayout();
	private HorizontalLayout nameHLayout = new HorizontalLayout();
	//private HorizontalLayout tableNumHLayout = new HorizontalLayout();
	private HorizontalLayout commentButtonHLayout = new HorizontalLayout();
	//private HorizontalLayout paymentHLayout = new HorizontalLayout();
	private HorizontalLayout totalHLayout = new HorizontalLayout();
	
	private VerticalLayout commentsLayout = new VerticalLayout();
	private VerticalLayout buttonTotalLayout = new VerticalLayout();
	
	private Grid<OrderDetails> drinksList = new Grid<>(OrderDetails.class);

	private Label orderNum = new Label("Order Number:");
	private Label orderNumVal = new Label();
	private Label name = new Label("Customer Name:");
	private Label nameVal= new Label();
	private Label tableNum = new Label("Table Number:");
	private Label tableNumVal = new Label();
	private Label paymentType = new Label("Payment Type:");
	private Label paymentTypeVal = new Label();
	private Label total = new Label("Total Price:");
	private Label totalVal = new Label();
	
	public Button prepOrder = new Button("Order Ready");
	public Button paidOrder = new Button("Confirm Payment");
	public Button back = new Button("Back to Orders");
	
	private static DecimalFormat df2 = new DecimalFormat("0.00");
	
	private TextArea comments = new TextArea("Comments");

	
	StaffScreen parent;
	int orderID;
	
	Dialog conf;
	
	public StaffOrderDetails(StaffScreen parent, int orderID ) {
	
		comments.setClassName("comment");
		
		drinksList.removeAllColumns();
		drinksList.addColumn("parentItemNo");
		drinksList.addColumn("itemName");
		drinksList.addColumn("itemCost");
				
		drinksList.getColumnByKey("parentItemNo").setHeader("Drink No");
		drinksList.getColumnByKey("itemName").setHeader("Product");
		drinksList.getColumnByKey("itemCost").setHeader("Cost £");
		
		drinksList.getColumnByKey("parentItemNo").setSortable(false);
		drinksList.getColumnByKey("itemName").setSortable(false);
		drinksList.getColumnByKey("itemCost").setSortable(false);
		
		drinksList.getColumnByKey("parentItemNo").setWidth("15%");
		drinksList.getColumnByKey("itemName").setWidth("75%");
		drinksList.getColumnByKey("itemCost").setWidth("10%");
		
		drinksList.setHeightByRows(true);
		
		prepOrder.addClickListener(e -> PrepairOrderProcess());
		prepOrder.addClassName("button-style");
		
		paidOrder.addClickListener(e -> ConfirmPaid());
		paidOrder.addClassName("button-style");
		
		back.addClickListener(e -> BackToStaffScreen());
		back.addClassName("button-style");
		
		Icon cancelIcon = new Icon(VaadinIcon.TRASH);
		Button removeBtn = new Button(cancelIcon);
		removeBtn.addClickListener(e-> CancelOrder());
		removeBtn.addClassName("small-button-style");
		
		total.setClassName("gap-label");
		totalVal.setClassName("gap-label");
		
		
		orderNumHLayout.add(orderNum);
		orderNumHLayout.add(orderNumVal);
		
		nameHLayout.add(name);
		nameHLayout.add(nameVal);
		
		orderNumHLayout.add(tableNum);
		orderNumHLayout.add(tableNumVal);
		
		nameHLayout.add(paymentType);
		nameHLayout.add(paymentTypeVal);
		
		commentsLayout.add(comments);
		
		totalHLayout.add(total);
		totalHLayout.add(totalVal);
		totalHLayout.add(removeBtn);
		
		buttonTotalLayout.add(totalHLayout);

		
		commentButtonHLayout.add(comments);
		commentButtonHLayout.add(buttonTotalLayout);
		commentButtonHLayout.add(back);
		
		add(orderNumHLayout);
		add(nameHLayout);
		//add(tableNumHLayout);
		//add(paymentHLayout);
		add(drinksList);
		add(commentButtonHLayout);
		setAlignItems(Alignment.CENTER);
		

		
		this.parent = parent;
		this.orderID = orderID;
		
	}
	


	public void CancelOrder() {
		VerticalLayout canVL = new VerticalLayout();
		HorizontalLayout canHL = new HorizontalLayout();
		Label cancelLBL = new Label("Cancel Order?");
		Dialog cancelWindow = new Dialog();
		Button yes = new Button("Yes");
		yes.addClickListener(e -> CancelOrderProcess(cancelWindow));
		
		Button no = new Button("No");
		no.addClickListener(e -> cancelWindow.close());
		canHL.add(yes);
		canHL.add(no);
		canVL.add(cancelLBL);
		canVL.add(canHL);
		cancelWindow.add(canVL);
		cancelWindow.open();
	}
	
	public void CancelOrderProcess(Dialog cancelWindow){
		cancelWindow.close();
		ConfirmedOrders cO  = parent.confirmedOrdersRepo.findByOrderID(orderID);
		cO.setOrderStatus(ConfirmedOrders.ORDER_STATUS_CANCEL);
		parent.confirmedOrdersRepo.save(cO);
		parent.closeOrderScreen();
		parent.GetDetails();
	}
	
	public void PrepairOrderProcess() {
		ConfirmedOrders cO  = parent.confirmedOrdersRepo.findByOrderID(orderID);
		cO.setOrderStatus(ConfirmedOrders.ORDER_STATUS_PREPARED);
		cO.setStaffReceived(parent.userId);
		parent.confirmedOrdersRepo.save(cO);
		parent.closeOrderScreen();
		parent.GetDetails();
	}
	
	public void ConfirmPaid() {
		
		Label confTxt = new Label("Confirm Customer Paid");
		
		Button yesBtn = new Button("Yes");
		yesBtn.addClickListener(e -> PaidOrderProcess());
		
		Button noBtn = new Button("No");
		yesBtn.addClickListener(e -> conf.close());
		
		HorizontalLayout confHL = new HorizontalLayout();
		confHL.add(yesBtn);
		confHL.add(noBtn);
		
		VerticalLayout confVL = new VerticalLayout();
		confVL.add(confTxt);
		confVL.add(confHL);
		confVL.setAlignItems(Alignment.CENTER);
		conf = new Dialog(confVL);
		conf.open();
	}
	
	public void PaidOrderProcess() {
		ConfirmedOrders cO  = parent.confirmedOrdersRepo.findByOrderID(orderID);
		cO.setOrderStatus(ConfirmedOrders.ORDER_STATUS_PAID);
		cO.setStaffConfirmed(parent.userId);
		parent.confirmedOrdersRepo.save(cO);
		conf.close();
		parent.closeOrderScreen();
		parent.GetDetails();
	}
	
	public void GetOrderDetails(String status) {
		
		System.out.println(orderID);
		Collection<OrderDetails> oD = parent.orderDetailsRepo.findByOrderId(orderID);
		drinksList.setItems(oD);
				
		ConfirmedOrders cO  = parent.confirmedOrdersRepo.findByOrderID(orderID);
		
		LogInIdentity lII = parent.logInIdentityRepo.findByCustomerID(cO.getCustomerID());
		
		System.out.println("cO.getOrderStatus() " +cO.getOrderStatus());
		orderNumVal.setText(cO.getOrderID().toString()); 
		
		nameVal.setText(cO.getCustomerName());
		
		paymentTypeVal.setText(lII.getPaymentType());
		tableNumVal.setText(cO.getTableNumber().toString());
		totalVal.setText(df2.format(cO.getOrderTotal()));
		System.out.println(" status " + status);
		

		if(status.equals(ConfirmedOrders.ORDER_STATUS_PREPARED)) {
			buttonTotalLayout.add(paidOrder);
		}else if(status.equals(ConfirmedOrders.ORDER_STATUS_PENDING)) {
			buttonTotalLayout.add(prepOrder);
		}else {
			System.out.println("Failed");
		}
		
		
		comments.setValue(cO.getComments());

	}
	
	public void BackToStaffScreen() {
		parent.contentPlaceHolder.removeAll();
		parent.contentPlaceHolder.add(parent.orderNumberDtls);
		parent.GetDetails();
	}
	
//	public void openCommentWindow() {
//		VerticalLayout comVL = new VerticalLayout();
//		TextArea cWTxt = new TextArea("Full Comment");
//		Dialog commentWindow = new Dialog();
//		Button okay = new Button("Okay");
//		okay.addClickListener(e -> commentWindow.close());
//		cWTxt.setValue(fullComment);
//		comVL.add(cWTxt);
//		comVL.add(okay);
//		commentWindow.add(comVL);
//		commentWindow.open();
//	}

	
}
