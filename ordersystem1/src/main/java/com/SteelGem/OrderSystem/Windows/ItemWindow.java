package com.SteelGem.OrderSystem.Windows;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.SteelGem.OrderSystem.MainView;
import com.SteelGem.OrderSystem.OrderMenu;

import com.SteelGem.OrderSystem.entity.AddonFlags;
import com.SteelGem.OrderSystem.entity.FlavourInfo;
import com.SteelGem.OrderSystem.entity.ProductInfo;
import com.SteelGem.OrderSystem.entity.WrkItemsInOrder;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.formlayout.FormLayout.ResponsiveStep;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.component.select.Select;

public class ItemWindow extends Dialog {

	VerticalLayout winVL = new VerticalLayout();

	FormLayout addonList = new FormLayout();
	
	HorizontalLayout buttonHL = new HorizontalLayout();
	
	HorizontalLayout shandyLemo = new HorizontalLayout();
	
	Label nameLbl;
	Label priceLbl;
	Label totCost;


	
	Checkbox shandySel;
	Checkbox lemoTop ;


	Select<String> mixer;
	Select<String> splash;
	Select<String> flavour;

	Checkbox glassChk ;

	Checkbox iceChk ;

	Checkbox limeWChk ;

	RadioButtonGroup<String>pintHalf;

	Checkbox doubleChk ;

	RadioButtonGroup<String>largeSel;

	Checkbox doublePromoChk ;

	RadioButtonGroup<String> bombRdo ;
	
	Select<Integer> quantity;

	Button cancel;
	
	Button addToOrder;

	String shandyFlag;
	String mixersFlag;
	String cordialFlag;
	String glassFlag;
	String iceFlag;
	String limeFlag;
	String redbullFlag;
	String orangeFlag;
	String hotFlag;
	String halvesFlag;
	String doublesFlag;
	String largeFlag;
	String doublePromoFlag;
	String bombFlag;
	String flavourFlag;
	String smallFlag;
	
	ArrayList<String> mixerList;
	ArrayList<String> flavourList;
	ArrayList<Integer> mixerListBehindScenes;
	ArrayList<String> splashList;
	
	String flavourName = null;
	
	MainView parent;
	OrderMenu oM;

	String name;
	int productID;
	double productPrice;
	double productAltPrice;
	double productAltPrice2;
	double largePrice;
	double regularPrice;

	boolean hasFlavour = false;
	
	int parentID = 1; 
	
	private static DecimalFormat df2 = new DecimalFormat("0.00");

	public ItemWindow(String name, int productID, double productPrice, double productAltPrice, double productAltPrice2, MainView parent, OrderMenu oM) {

		double largePrice = productPrice + productAltPrice;
		double regularPrice  = productPrice + productAltPrice2;
		quantity = new Select<Integer>();
		quantity.setLabel("Quantity");
		quantity.setItems(1,2,3,4,5,6,7,8,9,10);
		quantity.setValue(1);
		
		mixerList = new ArrayList<String>();
		mixerListBehindScenes = new ArrayList<Integer>();
		flavourList = new ArrayList<String>();
		splashList = new ArrayList<String>();
		
		pintHalf = new RadioButtonGroup<>();
		pintHalf.setLabel("Size:");
		pintHalf.setItems("Pint £"+df2.format(largePrice),"Half £"+df2.format(productPrice));
		pintHalf.setValue("Pint £"+df2.format(largePrice));
		pintHalf.isRequired();
		
		shandySel = new Checkbox("Shandy");
		shandySel.addValueChangeListener(event -> {
			if(event.getValue() != null) {
				if(shandySel.getValue().equals(true)) {
					lemoTop.setEnabled(false);
				}else {
					lemoTop.setEnabled(true);
				}
			}
		});
		
		lemoTop = new Checkbox("Lemonade Top");
		lemoTop.addValueChangeListener(event -> {
			if(event.getValue() != null) {
				if(lemoTop.getValue().equals(true)) {
					shandySel.setEnabled(false);
				}else {
					shandySel.setEnabled(true);
				}
			}
		});

		shandyLemo.add(shandySel);
		shandyLemo.add(lemoTop);
		
		mixer = new Select<String>();
		mixer.setLabel("Mixer");
		mixer.setItems(mixerList);
		mixer.setEmptySelectionAllowed(true);
		mixer.setEmptySelectionCaption("NONE");
		mixer.setValue("NONE");
		
		splash = new Select<String>();
		splash.setLabel("Splash");
		splash.setItems(splashList);
		splash.setEmptySelectionAllowed(true);
		splash.setEmptySelectionCaption("NONE");
		splash.setValue("NONE");

		flavour = new Select<String>();
		flavour.setLabel("Flavour");
		flavour.setItems(flavourList);
		flavour.setEmptySelectionAllowed(false);

		
		
		glassChk = new Checkbox("Add Glass");
		iceChk = new Checkbox("Add Ice");
		limeWChk= new Checkbox("Add Lime Wedge");

		doubleChk = new Checkbox("Double Up +£"+df2.format(productAltPrice));
		
		largeSel = new RadioButtonGroup<>();
		largeSel.setLabel("Size:");
		largeSel.setItems("Regular " + productPrice,"Large " + largePrice);
		largeSel.setValue("Regular");
		largeSel.isRequired();
		
		doublePromoChk = new Checkbox("Double Up +£1");
				
		bombRdo = new RadioButtonGroup<>();
		bombRdo.setLabel("Deal:");
		bombRdo.setItems("2 Jager 1 redbull £"+df2.format(productPrice),"3 Jager 1 redbull £"+df2.format(largePrice));
		bombRdo.setValue("2 Jager 1 redbull £"+df2.format(productPrice));
		bombRdo.isRequired();
		
		
		nameLbl = new Label(name);
		nameLbl.addClassName("beer-label");
		
		priceLbl = new Label("Price From: £"+df2.format(productPrice));
		
		cancel = new Button("Cancel");
		cancel.addClickListener(e -> closeWindow());
		addToOrder = new Button("Add to Order");
		addToOrder.addClickListener(e -> AddToOrder());

		addonList.setResponsiveSteps(new ResponsiveStep("250px", 1),
		           new ResponsiveStep("400px", 2),
		           new ResponsiveStep("600px", 3));
		addonList.add(quantity);
		
		buttonHL.add(cancel,addToOrder);
		buttonHL.setAlignItems(Alignment.STRETCH);
		
		winVL.add(nameLbl);
		winVL.add(priceLbl);
		winVL.add(addonList);
		winVL.add(buttonHL);
		winVL.addClassName("window-theme");
		this.add(winVL);
		this.setCloseOnOutsideClick(false);
		this.setModal(true);
		this.setResizable(false);
		
		this.parent = parent; 
		this.productID = productID;
		this.name = name;
		this.oM = oM;
		this.productPrice = productPrice;
		this.productAltPrice = productAltPrice;
		this.productAltPrice2 = productAltPrice2;
		this.regularPrice = regularPrice;
		this.largePrice = largePrice;
	}	

	public static String capitalize(String str) {
	    if(str == null || str.isEmpty()) {
	        return str;
	    }

	    return str.substring(0, 1).toUpperCase() + str.substring(1);
	}
	public void getAddonFlags() {
		
		AddonFlags aF = parent.addonFlagRepo.findByIds(productID);
		
		shandyFlag = aF.getOfferShandy();
		mixersFlag = aF.getOfferMixers();
		cordialFlag = aF.getOfferCordial();
		glassFlag = aF.getOfferGlass();
		iceFlag = aF.getOfferIce();
		limeFlag = aF.getOfferLime();
		redbullFlag = aF.getOfferRedbull();
		orangeFlag = aF.getOfferOrange();
		hotFlag = aF.getOfferHot();
		halvesFlag = aF.getOfferHalves();
		doublesFlag = aF.getOfferDouble();
		largeFlag = aF.getOfferLarge();
		smallFlag = aF.getOfferSmall();
		doublePromoFlag = aF.getOfferDoublePromo();
		bombFlag = aF.getOfferBomb();
		flavourFlag = aF.getOfferFlavours();
		


		if(flavourFlag.equals("Y")) {
			FlavourDrop();
			hasFlavour = true;
		}
		
		if(halvesFlag.equals("Y")) {
			HalvesButtons();
		}
		if(iceFlag.equals("Y")) {
			IceButtons();
		}	
		if(glassFlag.equals("Y")) {
			GlassButtons();
		}
		if(doublesFlag.equals("Y")) {
			DoublesButtons();
		}
		if(doublePromoFlag.equals("Y")) {
			DoublePromoButtons();
		}
		
		if(shandyFlag.equals("Y")) {
			ShandyButtons();
		}
		
		if(hotFlag.equals("Y")) {
			HotButtons();
		}
		
		if(mixersFlag.equals("Y")) {
			MixerButtons() ;
		}
		
		if(orangeFlag.equals("Y")) {
			OrangeButtons();
		}

		if(redbullFlag.equals("Y")){
			RedbullButtons();
		}
		
		if(cordialFlag.equals("Y")) {
			CordialButtons();
		}

		if(limeFlag.equals("Y")) {
			LimeButtons();
		}


		if(largeFlag.equals("Y")) {
			LargeButtons();
		}
		
		if(largeFlag.equals("Y")) {
			SmallButtons();
		}

		if(bombFlag.equals("Y")) {
			BombButtons();
		}
		
	}
	
	public void ShandyButtons() {

		addonList.add(shandyLemo);
	}
	
	public void HotButtons() {
		Collection<ProductInfo> pI = parent.productInfoRepo.findBySubCategory(36);
		if(pI != null) {
			for (ProductInfo o :  pI){
				mixerList.add(o.getName());
			}
			mixer.setItems(mixerList);
		}
		addonList.add(mixer);

	}
	
	public void MixerButtons() {
		
		Collection<ProductInfo> pI = parent.productInfoRepo.findBySubCategory(24);
		if(pI != null) {
			for (ProductInfo o :  pI){
				if(o.getPrice() != 0) {
					mixerList.add(o.getName() +" £" + df2.format(o.getPrice()));
				}else {
					mixerList.add(o.getName());
				}
			}
			mixer.setItems(mixerList);
		}
		//mixerList.add("Coke +" +productPrice);
		addonList.add(mixer);
		
	}
	public void CordialButtons() {
		Collection<ProductInfo> pI = parent.productInfoRepo.findBySubCategory(26);
		if(pI != null) {
			for (ProductInfo o :  pI){

				splashList.add(o.getName());
				splash.setItems(splashList);
			}
		}
		addonList.add(splash);

	}
	public void GlassButtons() {

		addonList.add(glassChk);
	}
	public void IceButtons() {

		addonList.add(iceChk);
	}
	public void LimeButtons() {

		addonList.add();
	}
	public void RedbullButtons() {
		Collection<ProductInfo> pI = parent.productInfoRepo.findBySubCategory(27);
		if(pI != null) {
			for (ProductInfo o :  pI){
				mixerList.add(o.getName()+ " £" + df2.format(o.getPrice()));
			}
			mixer.setItems(mixerList);
		}
		addonList.add(mixer);
	}
	public void OrangeButtons() {
		Collection<ProductInfo> pI = parent.productInfoRepo.findBySubCategory(29);
		if(pI != null) {
			for (ProductInfo o :  pI){
				mixerList.add(o.getName()+ " £" + df2.format(o.getPrice()));
			}
			mixer.setItems(mixerList);
		}
		addonList.add(mixer);

	}
	

	public void FlavourDrop() {
		Collection<FlavourInfo> pI = parent.flavourInfoRepo.findByDrinkID(productID);

		if(pI != null) {
			for (FlavourInfo o :  pI){
				System.out.println(productID);
				
				System.out.println(o.getDrinkID());
				System.out.println(o.getFlavourID());
				System.out.println(o.getAdditionalPrice());
				System.out.println(o.getFlavourName());

				
				if(o.getAdditionalPrice() != 0) {
					flavourList.add(o.getFlavourName() +" £" + df2.format(o.getAdditionalPrice()));
				}else {
					flavourList.add(o.getFlavourName());
				}
			}
			flavour.setItems(flavourList);
			if(flavourList.size() >0) {
				flavour.setValue(flavourList.get(0));
			}else {
				hasFlavour = false;
			}
		}
		addonList.add(flavour);
	}
	
	public void HalvesButtons() {
		addonList.add(pintHalf);
		
	}
	public void DoublesButtons() {

		addonList.add(doubleChk);
	}
	public void LargeButtons() {
		addonList.add(largeSel);
	}
	public void SmallButtons() {
		largeSel.setItems("Small £"+df2.format(productPrice),"Regular £"+df2.format(regularPrice),"Large £"+df2.format(largePrice));
		addonList.add(largeSel);
	}
	public void DoublePromoButtons() {
		addonList.add(doublePromoChk);
	}
	public void BombButtons() {

		addonList.add(bombRdo);
	}

	public void closeWindow() {
		oM.setProductName(null);
		this.close();
		Notification note = new Notification(
		        "Item Cancelled", 1500,
		        Position.MIDDLE);
		note.open();
	}
	
	public String SplitComboText(String text) {
		String cutText;
		if(text.contains("£")) {
			String[] arrSplit = text.split("£");
			cutText = arrSplit[0].trim().toUpperCase();
			System.out.println("£ Found");
		}else {
			cutText = text;
			System.out.println("£ Not Found");
		}
		return cutText;
	}
		
	public void AddToOrder() {
		addToOrder.setEnabled(false);
		CheckFlags();
		
		WriteToWorkTable();
		oM.setProductName(null);
		oM.closeAccordionTabs();
		this.close();
		Notification addedItem = new Notification(
		        "Item Added To Basket", 1500,
		        Position.MIDDLE);
		addedItem.open();
	}
	
	public void CheckFlags() {
		
		ArrayList<String> addonName = new ArrayList<String>();

		
		Integer addons = 0;
		
		//Collection<ProductInfo> pI = parent.productInfoRepo.findByCategory(8);
		//String addonName[] =null;
		
		addonName.add(name);

		if(pintHalf.getValue().equals("Pint £"+df2.format(largePrice))) {
			if(halvesFlag.equals("Y")) {
				addonName.add("PINT");
			}
		}
		if(largeSel.getValue().toUpperCase().contains("LARGE")) {
			addonName.add("LARGE");
			System.out.println("HERE!!!" + largeSel.getValue().toUpperCase());
		}else if(largeSel.getValue().toUpperCase().contains("REGULAR") && smallFlag.equals("Y")) {
			addonName.add("REGULAR");
			System.out.println("HERE!!!" + largeSel.getValue().toUpperCase());
		}
		
		if(bombRdo.getValue().equals("3 Jager 1 redbull £"+df2.format(largePrice))) {
			addonName.add("LARGE");
		}
		
		if(shandySel.getValue().equals(true)) {
			addonName.add("SHANDY");
		}
		
		if(lemoTop.getValue().equals(true)) {
			addonName.add("LEMONADE TOP");
		}
		
		if(doubleChk.getValue().equals(true)) {
			addonName.add("DOUBLE");
		}
		
		if(mixer.getValue() != null){
			addonName.add(SplitComboText(mixer.getValue()));
		}
		
		if(splash.getValue() != null){
			addonName.add(splash.getValue());
		}
		
		if(glassChk.getValue().equals(true)) {
			addonName.add("GLASS");
		}

		if(iceChk.getValue().equals(true)) {
			addonName.add("ICE");
		}
		
		if(doublePromoChk.getValue().equals(true)) {
			addonName.add("--- DOUBLE UP FOR £1");
		}
		
		if(hasFlavour) {
			flavourName = flavour.getValue();
		}
		

		for(String o : addonName) {
			ProductInfo pI = parent.productInfoRepo.findByName(o);
			if(pI != null){
				addons = pI.getIds();
				System.out.println(addons);
				oM.drinkDetails.add(addons);
				if(pI.getName().equals(name)) {
					parent.mainDrink = pI.getIds();
					parent.mainDrinkStr = pI.getName();
				}
			}
		}
		


	}
	public void WriteToWorkTable(){
		int quantityVal = quantity.getValue();
		
		while (quantityVal > 0) {
			Collection<WrkItemsInOrder> wrkCol =  parent.wrkItemsInOrderRepo.findByCustomerID(parent.custID);
			for(WrkItemsInOrder o : wrkCol) {
				if(o != null) {
					parentID = o.getParentItemNo()+1;
				}
			}
			System.out.println(quantityVal);
						
			for(Integer o : oM.drinkDetails) {
				System.out.println("o :" + o);
			
				
				parent.wIIO = new WrkItemsInOrder();
				parent.wIIO.setCustomerID(parent.custID);
				parent.wIIO.setUpdatedOn(new Date());
				parent.wIIO.setItemGenericID(o);
				parent.wIIO.setParentItemNo(parentID);
				parent.wIIO.setCancelledFlag("N");
				parent.wIIO.setMainDrinkId(parent.mainDrink);
				
				System.out.println("parent.wIIO.getMainDrinkId() "+parent.wIIO.getMainDrinkId());
				
				System.out.println("parent.wIIO.getItemGenericID() " + parent.wIIO.getItemGenericID());
				if(parent.wIIO.getItemGenericID().equals(parent.wIIO.getMainDrinkId())){
					parent.wIIO.setFlavourID(flavourName);
				}

				
				parent.wrkItemsInOrderRepo.save(parent.wIIO);

				parent.orderItemID = parent.wIIO.getOrderItemID();
				System.out.println("parent.orderItemID " +parent.orderItemID);
			}
			

			parent.confirmBtn.setEnabled(true);

			quantityVal = quantityVal - 1;
		}
		oM.drinkDetails.clear();
	}
}
