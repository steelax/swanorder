package com.SteelGem.OrderSystem;

import com.SteelGem.OrderSystem.Windows.ItemWindow;
import com.SteelGem.OrderSystem.entity.AddonFlags;
import com.SteelGem.OrderSystem.entity.ProductInfo;
import com.SteelGem.OrderSystem.entity.WrkItemsInOrder;
import com.SteelGem.OrderSystem.repository.AddonFlagsRepository;
import com.SteelGem.OrderSystem.repository.ConfirmedOrdersRepository;
import com.SteelGem.OrderSystem.repository.FlavourInfoRepository;
import com.SteelGem.OrderSystem.repository.LogInIdentityRepository;
import com.SteelGem.OrderSystem.repository.OrderDetailsRepository;
import com.SteelGem.OrderSystem.repository.ProductInfoRepository;
import com.SteelGem.OrderSystem.repository.WrkItemsInOrderRepository;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Page;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.WebBrowser;

import java.awt.Menu;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Swan Order System",
        shortName = "Swan-Bevy",
        description = "This Application allows you to place drinks orders to your table.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {


	@Value("${max_table_num}")
	private Integer max_table_num;
	
	@Value("${max_party_size}")
	private Integer max_party_size;
	
	@Autowired
	public
	AddonFlagsRepository addonFlagRepo;
	
	@Autowired
	public
	ProductInfoRepository productInfoRepo;

	@Autowired
	public
	FlavourInfoRepository flavourInfoRepo;
	
	@Autowired
	public
	OrderDetailsRepository orderDetailsRepo;
	
	@Autowired
	public
	ConfirmedOrdersRepository confirmedOrdersRepo;	
	
	@Autowired
	public
	LogInIdentityRepository logInIdentityRepo;
	
	@Autowired
	public
	WrkItemsInOrderRepository wrkItemsInOrderRepo;
	
	public Integer screenWidth = 0;
	public int custID;
	public String custName;
	public int tableNo;
	public int orderItemID;
	public int mainDrink;
	public String mainDrinkStr;
	public String orderNumber;
	
	public Image logo = new Image("img/swan.jpg","ImageNotFound");
	public Image smallLogo = new Image("img/SwanSmall.jpg","ImageNotFound");
	public TableDetails tableDetails = new TableDetails(this);
	public OrderMenu menu = new OrderMenu(this);
	public Basket basket = new Basket(this);
	public ConfirmationScreen confScreen = new ConfirmationScreen(this);

	public Button nextBtn = new Button("Next");
	public Button confirmBtn = new Button("View Basket");
	public Button placeOrderBtn = new Button("Place Order");
	public Button clearBtn = new Button("Clear");
	 
	public VerticalLayout holderVL = new VerticalLayout();
	
	public WrkItemsInOrder wIIO;
	
	public WebBrowser webBrowser;
	
	
	String widthGridString;
	double widthGrid;
	
    public MainView(@Autowired GreetService service) {
    	

    	logo.addClassName("img-style");
    	

    	nextBtn.addClassName("button-style");
		nextBtn.addClickListener(e -> submitTableDetails());
		nextBtn.setEnabled(true);
		
		confirmBtn.addClassName("button-style");
		confirmBtn.addClickListener(e -> GoToBasket());
		confirmBtn.setEnabled(false);
		
		placeOrderBtn.addClassName("button-style");
		placeOrderBtn.addClickListener(e -> PlaceOrder());
		placeOrderBtn.setEnabled(false);
		
		clearBtn.addClassName("button-style");
		clearBtn.addClickListener(e -> clearScreen());
		clearBtn.setEnabled(true);
		
		holderVL.addClassName("menu-area");
		holderVL.add(logo);
    	holderVL.add(tableDetails);
    	holderVL.setAlignItems(Alignment.CENTER);
        addClassName("main-menu");
        
        
        add(holderVL);
		add(nextBtn);
        setAlignItems(Alignment.CENTER);
        
        UI.getCurrent().getPage().retrieveExtendedClientDetails(receiver -> {
            screenWidth = receiver.getScreenWidth();
    	    System.out.println("screenWidth " + screenWidth);
    		widthGrid = screenWidth /1.220338983050847;
    		widthGridString = String.valueOf(widthGrid) + "px";
    		System.out.println(widthGridString); 
    		menu.subLevelDraught.setWidth(widthGridString);
    		menu.subLevelBottle.setWidth(widthGridString);
    		menu.subLevelSpirits.setWidth(widthGridString);
    		menu.subLevelShots.setWidth(widthGridString);
    		menu.subLevelWine.setWidth(widthGridString);
    		menu.subLevelSoft.setWidth(widthGridString);
    		menu.subLevelSnack.setWidth(widthGridString);
    		if(screenWidth <= 320) {
    			menu.productGrid.setClassName("small-Font");
    			menu.productGrid.getColumnByKey("name").setWidth("125px");
    			menu.productGrid.getColumnByKey("price").setWidth("55px");

    		}else if(screenWidth > 320 && screenWidth <= 360) {
    			menu.productGrid.setClassName("med-Font");
    			menu.productGrid.getColumnByKey("name").setWidth("155px");
    			menu.productGrid.getColumnByKey("price").setWidth("65px");
    		}else if(screenWidth > 360) {
    			menu.productGrid.setClassName("normal-Font");
    			menu.productGrid.getColumnByKey("name").setWidth("170px");
    			menu.productGrid.getColumnByKey("price").setWidth("75px");
    		}
    		Integer tbleSetup= 1;
    		while(tbleSetup <= max_table_num) {
    			tableDetails.tabData.add(tbleSetup.toString());
    			tbleSetup++;
    		}
    		
    		Integer prtySetup= 1;
//    		while(prtySetup <= max_party_size) {
//    			tableDetails.partyData.add(prtySetup.toString());
//    			prtySetup++;
//    		}
    		tableDetails.tableCBO.setItems(tableDetails.tabData);
    		//tableDetails.partyCBO.setItems(tableDetails.partyData);
        });
    }

    
    public void onAttach(AttachEvent attachEvent){

		
    }
	
	public void submitTableDetails() {
        tableDetails.UpdateCustomerTable();
        nextBtn.setEnabled(false);
	}
	
	public void BackToPrevScreen() {
		holderVL.remove(basket);
		holderVL.add(menu);
		add(confirmBtn);
		remove(placeOrderBtn);
		confirmBtn.setEnabled(true);
		
	}
	
	public void GoToBasket() {
		holderVL.remove(menu);
		holderVL.add(basket);
		remove(confirmBtn);
		add(placeOrderBtn);
		basket.GetItemsForBasket();
		confirmBtn.setEnabled(false);
	}
	
	public void PlaceOrder() {
		basket.PopulateTable();
		holderVL.remove(basket);
		holderVL.add(confScreen);
		remove(placeOrderBtn);
		add(clearBtn);
		placeOrderBtn.setEnabled(false);
	}
	
	public void clearScreen() {
		UI.getCurrent().getPage().reload();
	}

}
