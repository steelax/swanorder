package com.SteelGem.OrderSystem;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import com.SteelGem.OrderSystem.Windows.ItemWindow;
import com.SteelGem.OrderSystem.entity.ProductInfo;
import com.SteelGem.OrderSystem.repository.AddonFlagsRepository;
import com.SteelGem.OrderSystem.repository.ProductInfoRepository;
import com.vaadin.flow.component.accordion.Accordion;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.details.DetailsVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.Grid.Column;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.NumberRenderer;
import com.vaadin.flow.data.renderer.Renderer;

public class OrderMenu extends VerticalLayout implements Serializable {

	
	VerticalLayout draughtVL = new VerticalLayout();
	VerticalLayout bottleVL = new VerticalLayout();
	VerticalLayout spiritVL = new VerticalLayout();
	VerticalLayout shotVL = new VerticalLayout();
	VerticalLayout wineVL = new VerticalLayout();
	VerticalLayout softVL = new VerticalLayout();
	VerticalLayout snackVL = new VerticalLayout();
	
	VerticalLayout	subVL1	= new VerticalLayout();
	VerticalLayout	subVL2	= new VerticalLayout();
	VerticalLayout	subVL3	= new VerticalLayout();
	VerticalLayout	subVL4	= new VerticalLayout();
	VerticalLayout	subVL5	= new VerticalLayout();
	VerticalLayout	subVL6	= new VerticalLayout();
	VerticalLayout	subVL7	= new VerticalLayout();
	VerticalLayout	subVL8	= new VerticalLayout();
	VerticalLayout	subVL9	= new VerticalLayout();
	VerticalLayout	subVL10	= new VerticalLayout();
	VerticalLayout	subVL11	= new VerticalLayout();
	VerticalLayout	subVL12	= new VerticalLayout();
	VerticalLayout	subVL13	= new VerticalLayout();
	VerticalLayout	subVL14	= new VerticalLayout();
	VerticalLayout	subVL15	= new VerticalLayout();
	VerticalLayout	subVL16	= new VerticalLayout();
	VerticalLayout	subVL17	= new VerticalLayout();
	VerticalLayout	subVL18	= new VerticalLayout();
	VerticalLayout	subVL19	= new VerticalLayout();
	VerticalLayout	subVL20	= new VerticalLayout();
	VerticalLayout	subVL21	= new VerticalLayout();
	VerticalLayout	subVL22	= new VerticalLayout();
	VerticalLayout	subVL23	= new VerticalLayout();
	VerticalLayout	subVL24	= new VerticalLayout();
	VerticalLayout	subVL25	= new VerticalLayout();
	VerticalLayout	subVL26	= new VerticalLayout();
	
	Accordion subLevelDraught;

	Accordion subLevelBottle;

	Accordion subLevelSpirits;

	Accordion subLevelShots;

	Accordion subLevelWine;

	Accordion subLevelSoft;

	Accordion subLevelSnack;

	Accordion topLevelM;
	
	String ItemString;
	
	public ArrayList<Integer> drinkDetails = new ArrayList<Integer>();
	ArrayList<ProductInfo> pIAdder;
	
	Grid<ProductInfo> productGrid;

	MainView parent;
	
	public int productId;
	
	public String productName;
	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double productPrice;
	public double productAltPrice;
	public double productAltPrice2;

	
	public ItemWindow iW;
	
	private static DecimalFormat df2 = new DecimalFormat("0.00");

	public OrderMenu(MainView parent) {

		
		
		subVL1.setClassName("sub-indent");
		subVL2.setClassName("sub-indent");
		subVL3.setClassName("sub-indent");
		subVL4.setClassName("sub-indent");
		subVL5.setClassName("sub-indent");
		subVL6.setClassName("sub-indent");
		subVL7.setClassName("sub-indent");
		subVL8.setClassName("sub-indent");
		subVL9.setClassName("sub-indent");
		subVL10.setClassName("sub-indent");
		subVL11.setClassName("sub-indent");
		subVL12.setClassName("sub-indent");
		subVL13.setClassName("sub-indent");
		subVL14.setClassName("sub-indent");
		subVL15.setClassName("sub-indent");
		subVL16.setClassName("sub-indent");
		subVL17.setClassName("sub-indent");
		subVL18.setClassName("sub-indent");
		subVL19.setClassName("sub-indent");
		subVL20.setClassName("sub-indent");
		subVL21.setClassName("sub-indent");
		subVL22.setClassName("sub-indent");
		subVL23.setClassName("sub-indent");
		subVL24.setClassName("sub-indent");
		subVL25.setClassName("sub-indent");
		subVL26.setClassName("sub-indent");
		System.out.println(subVL26.getWidth()+" width here");

		
		productGrid = new Grid<>(ProductInfo.class);
		productGrid.removeAllColumns();
		productGrid.setColumns("name","price");
		productGrid.getColumnByKey("name").setHeader("Item");
		productGrid.getColumnByKey("price").setHeader("From £");
		productGrid.getColumnByKey("name").setSortable(false);
		productGrid.getColumnByKey("price").setSortable(false);
		productGrid.setHeightByRows(true);



		productGrid.asSingleSelect().addValueChangeListener(event -> {
				if(event.getValue() != null) {
					if(event.getValue().getIds() >= 0) {
						System.out.println("event.getValue() "+ event.getValue());
						productId = event.getValue().getIds();
						productName = event.getValue().getName();
						productPrice = event.getValue().getPrice();
						if(event.getValue().getAlternatePrice() != null) {
							productAltPrice = event.getValue().getAlternatePrice();
						}
						if(event.getValue().getAlternatePrice2() != null) {
							productAltPrice2 = event.getValue().getAlternatePrice2();
						}
					}
				}

				if(productName != null) {
					iW = new ItemWindow(productName, productId, productPrice, productAltPrice,productAltPrice2, parent, this);
					iW.getAddonFlags();
					iW.open();
				}
			});
				
		
		this.parent = parent;
		
		subLevelDraught = new Accordion();
		subLevelDraught.close();
		
		draughtVL.add(subLevelDraught);
		draughtVL.addClassName("sub-indent");

		
		subLevelDraught.add("Lagers", new Span(subVL6));
	    
	
		subLevelDraught.add("Ciders", new Span(subVL5));
	    
		
		subLevelDraught.add("Bitters and Ales", new Span(subVL4));
	    
		
		subLevelDraught.add("Guiness and Stouts", new Span(subVL7));
	    
		
		subLevelDraught.addOpenedChangeListener(event -> DetectOpenedTab(subLevelDraught,2));
		
		subLevelBottle = new Accordion();
		subLevelBottle.close();

		bottleVL.add(subLevelBottle);
		bottleVL.addClassName("sub-indent");
		
		subLevelBottle.add("Lager", new Span(subVL2));
	    

		subLevelBottle.add("Ciders", new Span(subVL1));
	    
		
		subLevelBottle.add("Ales/Misc", new Span(subVL3));
	    
		
		subLevelBottle.addOpenedChangeListener(event -> DetectOpenedTab(subLevelBottle,1));

		subLevelSpirits = new Accordion();
		subLevelSpirits.close();
		
		spiritVL.add(subLevelSpirits);
		spiritVL.addClassName("sub-indent");
		
		subLevelSpirits.add("Gin", new Span(subVL14));
	    

		subLevelSpirits.add("Rum", new Span(subVL16));
	    
		
		subLevelSpirits.add("Vodka", new Span(subVL17));
	    
		
		subLevelSpirits.add("Whisky/Whiskey", new Span(subVL18));
	    
		
		subLevelSpirits.add("Other Spirits and Liqeurs", new Span(subVL15));
	    

		subLevelSpirits.addOpenedChangeListener(event -> DetectOpenedTab(subLevelSpirits,6));
		
		// ** debug swap with just options
		subLevelShots = new Accordion();
		subLevelShots.close();
		
		shotVL.add(subLevelShots);
		shotVL.addClassName("sub-indent");
		
		subLevelShots.add("Shots", new Span(subVL8));
	    
		
		subLevelShots.add("Bombs", new Span(subVL9));
	    

		subLevelShots.addOpenedChangeListener(event -> DetectOpenedTab(subLevelShots,3));
		
		subLevelWine = new Accordion();
		subLevelWine.close();
		
		wineVL.add(subLevelWine);
		wineVL.addClassName("sub-indent");
		
		subLevelWine.add("By the Bottle", new Span(subVL24));
		
		
		subLevelWine.add("Red Wine", new Span(subVL20));
	    

		subLevelWine.add("White Wine", new Span(subVL23));
	    
		
		subLevelWine.add("Rosé Wine", new Span(subVL21));
	    
		
		subLevelWine.add("Sparkling", new Span(subVL22));
	    
		
		subLevelWine.add("Port", new Span(subVL19));
		
	    
		subLevelWine.addOpenedChangeListener(event -> DetectOpenedTab(subLevelWine,7));
		
		subLevelSoft = new Accordion();
		subLevelSoft.close();
		
		softVL.add(subLevelSoft);
		softVL.addClassName("sub-indent");
		
		subLevelSoft.add("Water and Soft Drinks", new Span(subVL10));
	    

		subLevelSoft.add("Juice Drink", new Span(subVL12));
	    
		
		subLevelSoft.add("Energy Drink", new Span(subVL11));
	    
		
		subLevelSoft.add("Tonics", new Span(subVL13));
	    
				
		subLevelSoft.addOpenedChangeListener(event -> DetectOpenedTab(subLevelSoft,5));
		
		subLevelSnack = new Accordion();
		subLevelSnack.close();
		
		snackVL.add(subLevelSnack);
		snackVL.addClassName("sub-indent");
		
		subLevelSnack.add("Crisps", new Span(subVL25));
		
		subLevelSnack.add("Nuts", new Span(subVL26));
		
		subLevelSnack.addOpenedChangeListener(event -> DetectOpenedTab(subLevelSnack,4));
		
		
		//---TopLevel---
		topLevelM = new Accordion();
		topLevelM.close();

		topLevelM.add("Draught", new Span(draughtVL));

		topLevelM.add("Bottles", new Span(bottleVL));

		topLevelM.add("Spirits and Liqeurs", new Span(spiritVL));
		
		topLevelM.add("Shots/Bombs", new Span(shotVL));

		topLevelM.add("Wine", new Span(wineVL));

		topLevelM.add("Soft Drinks", new Span(softVL));
		
		topLevelM.add("Snacks", new Span(snackVL));
		
		String topLevelMString = topLevelM.getOpenedIndex().toString();
		
		


		add(topLevelM);
		setAlignItems(Alignment.STRETCH);
		
    	addClassName("order-menu-style");
	}
	

	
	public void closeAccordionTabs() {
		topLevelM.close();
		subLevelDraught.close();
		subLevelBottle.close();
		subLevelSpirits.close();
		subLevelShots.close();
		subLevelWine.close();
		subLevelSoft.close();
		subLevelSnack.close();
		
	}
	
	public void DetectOpenedTab(Accordion accordion, int catID) {
		

		
		if(accordion.getOpenedIndex().toString() != "OptionalInt.empty") {
			
			String requiredString = "500";
			


			requiredString = accordion.getOpenedIndex().toString().substring(accordion.getOpenedIndex().toString().indexOf("[") + 1, accordion.getOpenedIndex().toString().indexOf("]")).toUpperCase();
			System.out.println("accordion.getOpenedIndex() " +  requiredString);

			int subCount = 0;
			int subID = 0;
	
			if(catID == 2) {
	
				switch (Integer.parseInt(requiredString)) {
					case 0:
						subID = 6;
						subVL6.add(productGrid);
						break;
					case 1:
						subID = 5;
						subVL5.add(productGrid);
						break;
					case 2:
						subID = 4;
						subVL4.add(productGrid);
						break;
					case 3:
						subID = 7;
						subVL7.add(productGrid);
						break;
				    default:
				        System.out.println("not found");
				        break;
			
				}
			}else if(catID ==1) {
				switch (Integer.parseInt(requiredString)) {
					case 0:
						subID = 2;
						subVL2.add(productGrid);
						break;
					case 1:
						subID = 1;
						subVL1.add(productGrid);
						break;
					case 2:
						subID = 3;
						subVL3.add(productGrid);
						break;
				    default:
				        System.out.println("not found");
				        break;
				}
				
			}else if(catID ==6) {
				switch (Integer.parseInt(requiredString)) {
					case 0:
						subID = 14;
						subVL14.add(productGrid);
						break;
					case 1:
						subID = 16;
						subVL16.add(productGrid);
						break;
					case 2:
						subID = 17;
						subVL17.add(productGrid);
						break;
					case 3:
						subID = 18;
						subVL18.add(productGrid);
						break;
					case 4:
						subID = 15;
						subVL15.add(productGrid);
						break;
				    default:
				        System.out.println("not found");
				        break;
	
				}
			}else if(catID ==3) {
				switch (Integer.parseInt(requiredString)) {
					case 0:
						subID = 8;
						subVL8.add(productGrid);
						break;
					case 1:
						subID = 9;
						subVL9.add(productGrid);
						break;
					default:
				        System.out.println("not found");
				        break;
				}
				
			}else if(catID ==7) {
				switch (Integer.parseInt(requiredString)) {
					case 1:
						subID = 20;
						subVL20.add(productGrid);
						break;
					case 2:
						subID = 23;
						subVL23.add(productGrid);
						break;
					case 3:
						subID = 21;
						subVL21.add(productGrid);
						break;
					case 4:
						subID = 22;
						subVL22.add(productGrid);
						break;
					case 5:
						subID = 19;
						subVL19.add(productGrid);
						break;
					case 0:
						subID = 34;
						subVL24.add(productGrid);
						break;
					default:
				        System.out.println("not found");
				        break;
	
				}
				 
			}else if(catID ==5) {
				switch (Integer.parseInt(requiredString)) {
					case 0:
						subID = 10;
						subVL10.add(productGrid);
						break;
					case 1:
						subID = 12;
						subVL12.add(productGrid);
						break;
					case 2:
						subID = 11;
						subVL11.add(productGrid);
						break;
					case 3:
						subID = 13;
						subVL13.add(productGrid);
						break;
					default:
				        System.out.println("not found");
				        break;
	
				}
				
			}else if(catID ==4) {
				switch (Integer.parseInt(requiredString)) {
					case 0:
						subID = 35;
						subVL25.add(productGrid);
						break;
					case 1:
						subID = 37;
						System.out.println("productGrid.getPageSize() " + productGrid.getPageSize());
						subVL26.add(productGrid);
						break;
					default:
				        System.out.println("not found");
				        break;

			}
			
		}
			pIAdder = new ArrayList<ProductInfo>();
			ProductInfo pIEmpty = new ProductInfo();
			pIEmpty.setName("No Items In Stock");
			pIEmpty.setPrice(0.00);
			pIEmpty.setIds(-1);
			
			Collection<ProductInfo> pI = parent.productInfoRepo.findBySubCategory(subID);
			for(ProductInfo o : pI) {
				if(o.getInStock().equals("Y")) {
					pIAdder.add(o);
				}
			}
			if(pIAdder.size() > 0) {
				productGrid.setItems(pIAdder);
			}else {
				productGrid.setItems(pIEmpty);
				
			}

		}else {
			productName = null;
		}
	}
}
	

