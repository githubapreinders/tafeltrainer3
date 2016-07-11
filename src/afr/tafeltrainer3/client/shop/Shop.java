package afr.tafeltrainer3.client.shop;

import java.util.ArrayList;
import java.util.Iterator;

import afr.tafeltrainer3.main.MainView;
import afr.tafeltrainer3.main.tafeltrainer3messages;
import afr.tafeltrainer3.utils.Utilities;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Shop extends Composite {

private ArrayList<Product> prodlist;
public ArrayList<Product> incart;
public ArrayList<Product> incarthotlist;
private FlexTable flex1;
private FlexTable flex10;
public HorizontalPanel hpanel10;
private HorizontalPanel hpanel00;
private HorizontalPanel hpanel30;
private HTML html00;
public Image img;
private Label lbl00;
private ListBox libo;
private MainView main;
private Productcategorie[] libocategorien = Productcategorie.values();
private ScrollPanel spanel30;
private tafeltrainer3messages messages;
private VerticalPanel vpanel00 = new VerticalPanel();
private int costs;
private int money;
private int alreadyincart = 0;
private ScrollPanel spanel10;
private Button button20a;
private Button button20b;
private HorizontalPanel hpanel20;

	public Shop(MainView main,tafeltrainer3messages messages)
	{
		this.main = main;
		this.costs = 0;
		this.messages = messages;
		this.money = main.getUser().getMoney();
		this.incart = new ArrayList<Product>();
		this.incarthotlist = new ArrayList<Product>();
		this.flex1 = new FlexTable();
		initWidget(this.flex1);
		this.flex1.setStyleName("tafeltabel");
		this.flex1.setCellSpacing(0);
		this.flex1.setCellPadding(0);
		this.flex1.setBorderWidth(0);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "125px");
		this.flex1.getFlexCellFormatter().setHeight(1, 0, "185px");
		this.flex1.getFlexCellFormatter().setHeight(2, 0, "40px");
		this.flex1.getFlexCellFormatter().setHeight(3, 0, "150px");
		this.flex1.getFlexCellFormatter().setWidth(0, 0, "960px");
		
		this.flex10 = new FlexTable();
		this.flex10.getFlexCellFormatter().setWidth(0, 0, "320px");
		this.flex10.getFlexCellFormatter().setWidth(0, 1, "320px");
		this.flex10.getFlexCellFormatter().setWidth(0, 2, "320px");
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(0, 1,
				HasHorizontalAlignment.ALIGN_CENTER);

		
		
		//eerste rij
		this.hpanel00 = new HorizontalPanel();
		this.lbl00 = new Label(String.valueOf(this.money));
		this.lbl00.setStyleName("invulbox");
		
		this.lbl00.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		
		this.html00 = new HTML("<h1>"+messages.winkeltje_title()+"</h1>");
		this.html00.setStyleName("topkader");
		
		this.html00.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		this.libo = new ListBox(false);
		this.libo.setHeight("30px");
		this.libo.setWidth("150px");
		this.libo.setStyleName("listboxshop");
		String[] items = {messages.winkeltje_categorie_autos(),
				messages.winkeltje_categorie_cartoons(),messages.winkeltje_categorie_edelstenen(),
				messages.winkeltje_categorie_landdieren(),messages.winkeltje_categorie_luchtdieren(),
				messages.winkeltje_categorie_zeedieren(),messages.winkeltje_categorie_voetballers(),
				messages.winkeltje_categorie_grappig(),messages.winkeltje_categorie_wereldsteden()
		};
		for (int i = 0; i< items.length; i++)
		{
			this.libo.addItem(items[i]);
		}
		this.libo.addChangeHandler(new ListBoxHandler());
		this.vpanel00.setSpacing(4);
		this.vpanel00.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		HTML html00a = new HTML(messages.winkeltje_kiesjecategorie());
		html00a.setStyleName("html_groot");
		this.vpanel00.add(html00a);
		this.vpanel00.add(libo);
		
		this.vpanel00.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		
		this.hpanel00.add(lbl00);
		this.hpanel00.add(html00);
		this.hpanel00.add(vpanel00);
		this.hpanel00.setSpacing(30);
		
		flex10.setWidget(0, 0, lbl00);
		flex10.setWidget(0, 1, html00);
		flex10.setWidget(0, 2, vpanel00);
		this.flex1.setWidget(0, 0, flex10);
		
		//tweede rij
		this.spanel10 = new ScrollPanel();
		this.spanel10.setWidth("960px");
		this.hpanel10 = new HorizontalPanel();
		this.hpanel10.setSpacing(5);
		this.hpanel10.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		this.hpanel10.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		this.img = new Image("/images/shop/winkelwagentje.jpg");
		this.spanel10.add(hpanel10);
		this.flex1.setWidget(1, 0, spanel10);
		
		//derde rij knoppen om af te rekenen en om te herstellen
		this.hpanel20 = new HorizontalPanel();
		this.button20a = new Button(messages.winkeltje_afrekenenbutton());
		this.button20a.addClickHandler(new Button20aClickHandler());
		this.button20a.setStyleName("standardbutton");
		this.button20b = new Button(messages.winkeltje_teruginwagentjebutton());
		this.button20b.addClickHandler(new Button20bClickHandler());
		this.button20b.setStyleName("standardbutton");
		hpanel20.add(button20a);
		hpanel20.add(button20b);
		this.flex1.setWidget(2, 0, hpanel20);
		
		
		//vierde rij, hier komen de producten in;
		this.spanel30 = new ScrollPanel();
		this.spanel30.setWidth("960px");
		this.hpanel30 = new HorizontalPanel();
		this.hpanel30.setSpacing(5);
		this.hpanel30.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		this.spanel30.add(hpanel30);
		this.flex1.setWidget(3, 0, spanel30);
		
		//uit ProductIO wordt een lijst samengesteld waaruit hier de producten worden uitgestald.
		Prodlijst prodlijst = new Prodlijst();
		this.prodlist = prodlijst.getProductlijst();
		Iterator<Product> p = prodlist.iterator();
		while(p.hasNext())
		{
			Product pr = p.next();
			if(pr.getProductcat()==Productcategorie.autos)
			{
				ProductWidget pw = new ProductWidget(pr,this,messages);
				hpanel30.add(pw);
			}
		}
	}

		
	

	private class ListBoxHandler implements ChangeHandler,Productlists
	{
		@Override
		public void onChange(ChangeEvent event) 
		{
			switch (libo.getSelectedIndex())
			{
			case 0 : hpanel30.clear();
					Iterator<Product> p = prodlist.iterator();
					while(p.hasNext())
					{
						Product pr = p.next();
						if(pr.getProductcat()==Productcategorie.autos)
						{
							ProductWidget pw = new ProductWidget(pr,getShop(),messages);
							hpanel30.add(pw);
						}
					}
					break;
			
			case 1 :	hpanel30.clear();
					Iterator<Product> p3 = prodlist.iterator();
					while(p3.hasNext())
					{ Product pr = p3.next();
						if(pr.getProductcat()==Productcategorie.cartoons)
						{
							ProductWidget pw = new ProductWidget(pr,getShop(),messages);
							hpanel30.add(pw);
						}
					}
					break;
			
			case 2 :	hpanel30.clear();
					Iterator<Product> p4 = prodlist.iterator();
					while(p4.hasNext())
					{ Product pr = p4.next();
						if(pr.getProductcat()==Productcategorie.edelstenen)
						{
							ProductWidget pw = new ProductWidget(pr,getShop(),messages);
							hpanel30.add(pw);
						}
					}
					break;
			
			case 3 :	hpanel30.clear();
			Iterator<Product> p5 = prodlist.iterator();
			while(p5.hasNext())
			{ Product pr = p5.next();
				if(pr.getProductcat()==Productcategorie.landdieren)
				{
					ProductWidget pw = new ProductWidget(pr,getShop(),messages);
					hpanel30.add(pw);
				}
			}
			break;
			
			case 4 :	hpanel30.clear();
			Iterator<Product> p6 = prodlist.iterator();
			while(p6.hasNext())
			{ Product pr = p6.next();
				if(pr.getProductcat()==Productcategorie.luchtdieren)
				{
					ProductWidget pw = new ProductWidget(pr,getShop(),messages);
					hpanel30.add(pw);
				}
			}
			break;
			
			case 5 :	hpanel30.clear();
			Iterator<Product> p7 = prodlist.iterator();
			while(p7.hasNext())
			{ Product pr = p7.next();
				if(pr.getProductcat()==Productcategorie.zeedieren)
				{
					ProductWidget pw = new ProductWidget(pr,getShop(),messages);
					hpanel30.add(pw);
				}
			}
			break;
			
			case 6 :	hpanel30.clear();
			Iterator<Product> p8 = prodlist.iterator();
			while(p8.hasNext())
			{ Product pr = p8.next();
				if(pr.getProductcat()==Productcategorie.voetballers)
				{
					ProductWidget pw = new ProductWidget(pr,getShop(),messages);
					hpanel30.add(pw);
				}
			}
			break;

			case 7 :	hpanel30.clear();
			Iterator<Product> p9 = prodlist.iterator();
			while(p9.hasNext())
			{ Product pr = p9.next();
				if(pr.getProductcat()==Productcategorie.grappig)
				{
					ProductWidget pw = new ProductWidget(pr,getShop(),messages);
					hpanel30.add(pw);
				}
			}
			break;

			case 8 :	hpanel30.clear();
			Iterator<Product> p10 = prodlist.iterator();
			while(p10.hasNext())
			{ Product pr = p10.next();
				if(pr.getProductcat()==Productcategorie.wereldsteden)
				{
					ProductWidget pw = new ProductWidget(pr,getShop(),messages);
					hpanel30.add(pw);
				}
			}
			break;
			
			}	
		}
	}
//afrekenen van een product
	private class Button20aClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) 
		{
		if(incarthotlist.size()>0)
			{	
			int index = 0;
			for(Product p : incarthotlist)
			{
				money-=p.getPrice();
				ProductWidget pclone = new ProductWidget(p.getUrl());
				main.getGui().updateSpanel2(pclone);
				Image imag1 = new Image(p.getUrl());
				imag1.setSize("90px", "90px");
				main.wallet.flowpaneldroptarget.add(imag1);
				main.getUser().addMoney((-1)*p.getPrice());
				main.client.updateUser(main.getUser());
				main.client.addProduct(main.getUser().getId(),p);
				main.wallet.productlist.add(p);
				main.wallet.bedragveranderd = true;
				alreadyincart-=p.getPrice();
				lbl00.setText(String.valueOf(money));
					for(Product prod : incart)
					{
						if(prod == p)
						{index = incart.indexOf(prod);}
					}
					incart.remove(index);
			}
			incarthotlist.clear();
			hpanel10.clear();
			hpanel10.add(img);
			if(incart.size()>0)
			{
			for(final Product p : incart)
			{
				final Image imgcart = new Image(p.getUrl());
				imgcart.setHeight("100px");
				imgcart.addClickHandler(new ClickHandler()
					{
					int flag = 0;
					@Override
					public void onClick(ClickEvent event) 
					{
						switch(flag)
						{
						case 0 :	
								incarthotlist.add(p);
								imgcart.setStyleName("redborder");
								flag = 1;
								break;
						case 1 : imgcart.setStyleName("noborder");
								int index = 0 ;	
								for(Product s : incarthotlist)
								{
									if(p == s)
									{
										index = incarthotlist.indexOf(s);
									}
								}	
								incarthotlist.remove(index);
								flag =0; 
								break;
						}
					}
					});
				
				hpanel10.add(imgcart);

			}
			}
		}
		}	
	}
	
	//verwijderen uit het winkelwagentje zonder te betalen
	private class Button20bClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) 
		{
			for(Product p : incarthotlist)
			{int index =0;
				for(Product prod : incart)
				{
					if(p==prod)
					{
						index = incart.indexOf(prod);
					}
				}
			alreadyincart-=p.getPrice();
			incart.remove(index);
			}
		incarthotlist.clear();
		hpanel10.clear();
		hpanel10.add(img);
		if(incart.size()>0)
		{
		for(final Product p : incart)
		{
			final Image imgcart = new Image(p.getUrl());
			imgcart.setHeight("100px");
			imgcart.addClickHandler(new ClickHandler()
				{
				int flag = 0;
				@Override
				public void onClick(ClickEvent event) 
				{
					switch(flag)
					{
					case 0 :	
							incarthotlist.add(p);
							imgcart.setStyleName("redborder");
							flag = 1;
							break;
					case 1 : imgcart.setStyleName("noborder");
							int index = 0 ;	
							for(Product s : incarthotlist)
							{
								if(p == s)
								{
									index = incarthotlist.indexOf(s);
								}
							}	
							incarthotlist.remove(index);
							flag =0; 
							break;
					}
				}
				});
			
			hpanel10.add(imgcart);
		}
		}
		}
	}
	
	public void setMoney(int money) 
	{
		this.money = money;
		this.lbl00.setText(String.valueOf(this.money));
	}

	public void addToCart(final ProductWidget p)
		{
			
		if(main.getUser().getMoney()>=p.price + alreadyincart)
			{
				this.costs+=p.price;
				incart.add(p.getProduct());
				
				String resourcename = p.getresourcename(p.url);
				final Image imgcart = new Image(p.findResource(resourcename));
				imgcart.addClickHandler(new ClickHandler()
					{
					int flag = 0;
					@Override
					public void onClick(ClickEvent event) 
					{
						switch(flag)
						{
						case 0 :	
								incarthotlist.add(p.getProduct());
								imgcart.setStyleName("redborder");
								flag = 1;
								break;
						case 1 : imgcart.setStyleName("noborder");
								int index = 0 ;	
								for(Product s : incarthotlist)
								{
									if(p.getProduct() == s)
									{
										index = incarthotlist.indexOf(s);
									}
								}	
								incarthotlist.remove(index);
								flag =0; 
								break;
						}
					}
					});
				alreadyincart+=p.price;
				this.hpanel10.add(imgcart);
			}
			else 
			{
				Utilities.alertWidget("", messages.utilities_shop_not_enough_money(), messages).center();
			}
		}	
	public Shop getShop()
	{
		return this;
	}
	
	public FlexTable getFlex1() {
		return flex1;
	}
	
	public void setFlex1(FlexTable flex1) {
		this.flex1 = flex1;
	}
	
	
	public int getMoney() {
		return money;
	}
	
}
