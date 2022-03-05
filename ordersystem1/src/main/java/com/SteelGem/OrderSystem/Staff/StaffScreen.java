package com.SteelGem.OrderSystem.Staff;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.vaadin.erik.SlideTab;
import org.vaadin.erik.SlideTabBuilder;

import com.SteelGem.OrderSystem.OrderDetailsPOJO;
import com.SteelGem.OrderSystem.CheckIn.CheckInWindow;
import com.SteelGem.OrderSystem.Windows.StaffLogin;
import com.SteelGem.OrderSystem.Windows.StockToggler;
import com.SteelGem.OrderSystem.entity.ConfirmedOrders;
import com.SteelGem.OrderSystem.entity.StaffSession;
import com.SteelGem.OrderSystem.repository.CheckInIdentityRepository;
import com.SteelGem.OrderSystem.repository.ConfirmedOrdersRepository;
import com.SteelGem.OrderSystem.repository.LogInIdentityRepository;
import com.SteelGem.OrderSystem.repository.OrderDetailsRepository;
import com.SteelGem.OrderSystem.repository.ProductInfoRepository;
import com.SteelGem.OrderSystem.repository.StaffDetailsRepository;
import com.SteelGem.OrderSystem.repository.StaffSessionRepository;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;

@Route("Staff")
@Push
public class StaffScreen extends VerticalLayout {

    private FeederThread thread;
	
	
	@Autowired
	public
	StaffDetailsRepository staffDetailsRepo;
	
	@Autowired
	ConfirmedOrdersRepository confirmedOrderRepo;
	
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
	StaffSessionRepository staffSessionRepo;
	
	@Autowired
	public
	ProductInfoRepository productInfoRepo;
	
	@Autowired
	public
	CheckInIdentityRepository checkInIdentityRepo;
	
	
	HorizontalLayout topBanner = new HorizontalLayout();
	VerticalLayout popoutMenu = new VerticalLayout();
	
	StaffLogin loginWin = new StaffLogin(this);
	
	StockToggler stockWindow = new StockToggler(this);
	
	boolean loaded;
	
	public boolean loginSuccess = false;
	
	Collection<OrderDetailsPOJO> oDPAL;
	
	Integer orderId;
	
	public Grid<OrderDetailsPOJO> orderNumberDtls;
	
	public String userId;
	
	private Icon menuIcon = new Icon(VaadinIcon.MENU);
	//private Button menuButton= new Button("",menuIcon);
	public Button menuButton= new Button("Stock Control");
	public Button checkInButton= new Button("Check In");

	public Image smallLogo = new Image("img/SwanSmall.jpg","Image Not Found");
	
	Icon refreshIcon = new Icon(VaadinIcon.REFRESH);
	
	Button refresh = new Button("Refresh", refreshIcon);

	public String currentUser;
	public String currentIP;
	public String browserID ;
	
	public WebBrowser webBrowser;
	
	public VaadinSession vSession;
	
    public VerticalLayout contentPlaceHolder = new VerticalLayout();
    
    StaffOrderDetails sOD;
    
    static boolean inMenu;
    
    CheckInWindow cIW = new CheckInWindow(this);
    
    float screenWidth;
    
    HorizontalLayout buttonLowRes = new HorizontalLayout();
    
	public StaffScreen() {
		
        UI.getCurrent().getPage().retrieveExtendedClientDetails(receiver -> {
            screenWidth = receiver.getScreenWidth();
            System.out.println("screenWidth " + screenWidth);
            
            if(screenWidth>=786.0) {
            	
        	    menuButton.setClassName("corner-button");
        	    checkInButton.setClassName("corner-button2");
            	
        		topBanner.add(menuButton);
        		topBanner.add(checkInButton);
        		add(topBanner);
        		add(contentPlaceHolder);
        		
            }else {
            	buttonLowRes.add(menuButton);
            	buttonLowRes.add(checkInButton);
        		add(topBanner);
        		add(buttonLowRes);
        		add(contentPlaceHolder);
            }
        });
		
		vSession =VaadinSession.getCurrent().getCurrent();

	    System.out.println("getId =" +vSession.getSession().getId());
	    browserID = vSession.getSession().getId();



	    menuButton.addClickListener(e -> OpenPopup());


	    checkInButton.addClickListener(e -> OpenCheckIn());
	    
	    
	    smallLogo.setClassName("staff-image");
	    
		webBrowser = UI.getCurrent().getSession().getBrowser();
		currentIP = webBrowser.getAddress();
	    System.out.println("currentIP =" +currentIP);
		//checkUserDetails();
		
		refresh.addClickListener(e -> GetDetails());
		
		orderNumberDtls = new Grid<>(OrderDetailsPOJO.class);
		orderNumberDtls.setHeightByRows(true);
		orderNumberDtls.removeAllColumns();
		orderNumberDtls.addColumns("name","orderID","status","tableNumber","orderTime");
		orderNumberDtls.asSingleSelect().addValueChangeListener(event -> {
			if(event.getValue() != null) {
				contentPlaceHolder.setClassName("staff-menu-area");
				String status = event.getValue().getStatus();
				orderId = event.getValue().getOrderID();
//				oDW = new OrderDetailWindow(this, OrderId);
//				oDW.open();
//				oDW.GetOrderDetails(status);
				sOD = new StaffOrderDetails(this, orderId);
				System.out.println(orderId);
				sOD.GetOrderDetails(status);
				openOrderScreen();
				
			}
		});
		
		contentPlaceHolder.setClassName("staff-menu-area");
		contentPlaceHolder.add(orderNumberDtls);

		topBanner.add(menuButton);
		topBanner.add(checkInButton);
		topBanner.add(smallLogo);
		

		
		setAlignItems(Alignment.CENTER);

	}
	
    @Override
	public void onAttach(AttachEvent attachEvent){
		CheckSession(); 
		if(loginSuccess == true) {
			GetDetails();
		}

    thread = new FeederThread(attachEvent.getUI(), this);
    thread.start();

	}
    @Override
    protected void onDetach(DetachEvent detachEvent) {
        // Cleanup
        thread.interrupt();
        thread = null;
    }
	
	public void CheckSession() {
		StaffSession sSession = staffSessionRepo.findBySessionID(browserID);
		if(sSession == null) {
			loginWin.open();
			loginSuccess = false;
		}else {
			loginSuccess = true;
		}
	}


	
	public void GetDetails() {
		
		String pattern = "HH:mm:ss";
		SimpleDateFormat df = new SimpleDateFormat(pattern); 
		int anyPending = 0;
		oDPAL = new ArrayList<OrderDetailsPOJO>();
		Collection<ConfirmedOrders> cO = confirmedOrderRepo.findAll(); 
		for(ConfirmedOrders o : cO) {
			if(o.getOrderStatus().contentEquals(ConfirmedOrders.ORDER_STATUS_PENDING)){
				anyPending++;
			}
			String updatedOn = df.format(o.getUpdatedOn());
			OrderDetailsPOJO oDP = new OrderDetailsPOJO();
			oDP.setOrderID(o.getOrderID());
			oDP.setName(o.getCustomerName());
			oDP.setStatus(o.getOrderStatus());
			oDP.setTableNumber(o.getTableNumber());
			oDP.setTotal(o.getOrderTotal());
			oDP.setOrderTime(updatedOn);
			if(o.getOrderStatus().equals(ConfirmedOrders.ORDER_STATUS_PENDING) || o.getOrderStatus().equals(ConfirmedOrders.ORDER_STATUS_PREPARED)){
				oDPAL.add(oDP);
			}

		}
		if(anyPending > 0 && inMenu == false) {
			contentPlaceHolder.setClassName("staff-menu-area-red");
		}
		orderNumberDtls.setItems(oDPAL);
		loaded = true;		
	}
	
	
	public void clearScreen() {
		UI.getCurrent().getPage().reload();
	}
	
	public void OpenPopup() {

		contentPlaceHolder.setClassName("staff-menu-area");
		menuButton.setEnabled(false);
		checkInButton.setEnabled(false);
		contentPlaceHolder.removeAll();
		contentPlaceHolder.add(stockWindow);
		stockWindow.getList();
	}
	
	public void OpenCheckIn() {
		inMenu = true;
		contentPlaceHolder.removeAll();
		contentPlaceHolder.add(cIW);
		menuButton.setEnabled(false);
		checkInButton.setEnabled(false);
		contentPlaceHolder.setClassName("staff-menu-area");
	}
	
	public void closeOrderScreen() {
		inMenu = false;
		contentPlaceHolder.removeAll();
		contentPlaceHolder.add(orderNumberDtls);
		
	}
	public void openOrderScreen() {
		inMenu = true;
		contentPlaceHolder.removeAll();
		contentPlaceHolder.add(sOD);
		
	}
	
    private static class FeederThread extends Thread {
        private final UI ui;
        private final StaffScreen view;

        private int count = 0;

        public FeederThread(UI ui, StaffScreen view) {
            this.ui = ui;
            this.view = view;
        }
        
        @Override
        public void run() {
        	while (count < 1) {
	        	try {
					Thread.sleep(20000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					System.out.println("here");
					Thread.currentThread().interrupt();
					break;
				}
	        	if(view.loginSuccess == true) {
	        		if(inMenu ==false) {
	        			ui.access(() -> view.GetDetails());
	        		}
	        	}
        	}
        }
    }
	
}
