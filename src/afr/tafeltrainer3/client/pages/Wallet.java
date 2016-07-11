package afr.tafeltrainer3.client.pages;

import java.util.ArrayList;

import afr.tafeltrainer3.client.shop.Product;
import afr.tafeltrainer3.main.ClientImp;
import afr.tafeltrainer3.main.MainView;
import afr.tafeltrainer3.main.tafeltrainer3messages;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.allen_sauer.gwt.dnd.client.drop.FlowPanelDropController;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.CloseEvent;
import com.google.gwt.event.logical.shared.CloseHandler;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.regexp.shared.MatchResult;

/**
 * Dit is een widget die bezittingen van een user toont; bezittingen kunnen
 * bestaan uit plaatjes en uit geld. De enige interactie hier is het kunnen sorteren van
 * de bezittingen
 * 
 * @author ap
 * 
 */
public class Wallet extends Composite {
	public boolean bedragveranderd = true;
	public int money;
	public AbsolutePanel abspanel;
	private ArrayList<Image> geldplaatjes;
	public ArrayList<Product> productlist;
	private ClientImp client;
	private HorizontalPanel hpanel00;
	private FlexTable flex1;
	private FlexTable flex10;

	private FlowPanel fpanel10;
	public FlowPanel flowpaneldroptarget;
	private HTML html00;
	private Label lbl00;
	private MainView main;
	public PickupDragController pickupdragcontroller;
	private ScrollPanel spanel10;
	private ScrollPanel spanel11;
	private HTML html00a;

	public Wallet(MainView main,tafeltrainer3messages messages) {
		this.main = main;
		this.money = main.getUser().getMoney();
		this.productlist = new ArrayList<Product>();
		flex1 = new FlexTable();
		initWidget(this.flex1);
		this.flex1.setStyleName("tafeltabel");
		flex1.setCellSpacing(0);
		flex1.setCellPadding(0);
		flex1.setBorderWidth(0);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "125px");
		this.flex1.getFlexCellFormatter().setHeight(1, 0, "375px");
		this.flex1.getFlexCellFormatter().setWidth(0, 0, "960px");
		this.flex1.getFlexCellFormatter().setColSpan(0, 0, 3);
		this.flex1.getFlexCellFormatter().setRowSpan(1, 0, 2);
		this.flex1.getCellFormatter().setWidth(1, 0, "250px");
		this.flex1.getCellFormatter().setWidth(1, 1, "710px");
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(1, 0,
				HasHorizontalAlignment.ALIGN_CENTER);

		this.flex10 = new FlexTable();
		this.flex10.getFlexCellFormatter().setWidth(0, 0, "320px");
		this.flex10.getFlexCellFormatter().setWidth(0, 1, "320px");
		this.flex10.getFlexCellFormatter().setWidth(0, 2, "320px");
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(0, 1,
				HasHorizontalAlignment.ALIGN_CENTER);

		
		
		
		// eerste rij
		//hpanel00 = new HorizontalPanel();
		lbl00 = new Label("123");
		lbl00.setStyleName("invulbox");
		html00 = new HTML("<h1>"+messages.wallet_title()+"</h1>");
		html00.setStyleName("topkader");
		html00a = new HTML();
		html00a.setStyleName("html_groot");
		//hpanel00.add(lbl00);
		//hpanel00.add(html00);
		//hpanel00.add(html00a);
		//hpanel00.setSpacing(30);
		flex10.setWidget(0, 0, lbl00);
		flex10.setWidget(0, 1, html00);
		flex1.setWidget(0, 0, flex10);

		// tweede rij
		spanel10 = new ScrollPanel();
		spanel10.setWidth("250px");
		spanel10.setHeight("375px");
		fpanel10 = new FlowPanel();
		fpanel10.setWidth("230px");
		fpanel10.setHeight("375px");
		spanel10.add(fpanel10);
		flex1.setWidget(1, 0, spanel10);
		//showWallet(main.getUser().getMoney());

		abspanel = new AbsolutePanel();
		this.abspanel.setPixelSize(690, 1075);
		this.pickupdragcontroller = new PickupDragController(abspanel, true);
		pickupdragcontroller.setBehaviorDragStartSensitivity(2);
		this.flowpaneldroptarget = new FlowPanel();
		this.flowpaneldroptarget.setPixelSize(690, 375);
		abspanel.add(flowpaneldroptarget);
		FlowPanelDropController flowpaneldropcontroller = new FlowPanelDropController(
				flowpaneldroptarget);
		pickupdragcontroller.setBehaviorDragProxy(true);
		pickupdragcontroller.registerDropController(flowpaneldropcontroller);
		spanel11 = new ScrollPanel();
		spanel11.setWidth("710px");
		spanel11.setHeight("375px");
		spanel11.add(abspanel);
		flex1.setWidget(1, 1, spanel11);

	}

	public void updateFlowpanelDroptarget() {
		flowpaneldroptarget.clear();
		for (final Product p : productlist) 
		{
			Image img = new Image(p.getUrl());
			img.addClickHandler(new ClickHandler() 
			{

				@Override
				public void onClick(ClickEvent event) 
				{
					Image bigcopy = new Image(p.getUrl());
					bigcopy.setStyleName("imagebigcopy");
					StringBuilder url = new StringBuilder(p.getUrl());
					String slash = "/";
					String dot = ".";
					url.delete(0, url.lastIndexOf(slash) + 1);
					url.delete(url.lastIndexOf(dot), url.length());
					
					for(int i =0; i<url.length();i++)
					{
						if(url.charAt(i)=='_')
						{
							url.replace(i, i+1, " ");
						}
					}
					
					HTML htmlpopup = new HTML(url.toString()); 
					htmlpopup.setStyleName("html");
					PopupPanel popup = new PopupPanel(true);
					popup.setPopupPosition(0, 0);
					VerticalPanel vpanel = new VerticalPanel();
					popup.setStyleName("popup");
					vpanel.add(bigcopy);
					vpanel.add(htmlpopup);
					popup.setWidget(vpanel);
					popup.show();
				}
			});

			img.setHeight("90px");
			this.pickupdragcontroller.makeDraggable(img);
			this.flowpaneldroptarget.add(img);
		}
	}

	public void showWallet(int money) 
	{
		if(bedragveranderd == true)
		{
			this.fpanel10.clear();
			geldplaatjes = getGeldplaatjes(money);
			for (int i = 0; i < geldplaatjes.size(); i++) 
			{
				this.fpanel10.add(geldplaatjes.get(i));
			}
			lbl00.setText(String.valueOf(money));
			bedragveranderd = false;
		}
	}

	// maakt van een geldbedrag een set plaatjes van munten die getoond worden
	public ArrayList<Image> getGeldplaatjes(int money) {
		geldplaatjes = new ArrayList<Image>();
		Image tafelmunt;

		int i = 5;
		while (money > 0) {
			int randomturningangle = Random.nextInt(7);
			String turningangle = String.valueOf(randomturningangle + 1);
			int randomnumber = Random.nextInt(i);
			switch (randomnumber) {
			case 0:
				tafelmunt = new Image("/images/tafelmunt_1" + turningangle
						+ ".png");
				tafelmunt.setStyleName("imagesmall");
				money -= 1;
				geldplaatjes.add(tafelmunt);
				break;
			case 1:
				tafelmunt = new Image("/images/tafelmunt_5" + turningangle
						+ ".png");
				tafelmunt.setStyleName("imagesmall");
				money -= 5;
				if (money < 0) {
					money += 5;
					i = 1;
					break;
				}
				geldplaatjes.add(tafelmunt);
				break;
			case 2:
				tafelmunt = new Image("/images/tafelmunt_10" + turningangle
						+ ".png");
				tafelmunt.setStyleName("imagesmall");
				money -= 10;
				if (money < 0) {
					money += 10;
					i = 2;
					break;
				}
				geldplaatjes.add(tafelmunt);
				break;
			case 3:
				tafelmunt = new Image("/images/tafelmunt_20" + turningangle
						+ ".png");
				tafelmunt.setStyleName("imagesmall");
				money -= 20;
				if (money < 0) {
					money += 20;
					i = 3;
					break;
				}
				geldplaatjes.add(tafelmunt);
				break;
			case 4:
				tafelmunt = new Image("/images/tafelmunt_50" + turningangle
						+ ".png");
				tafelmunt.setStyleName("imagesmall");
				money -= 50;
				if (money < 0) {
					money += 50;
					i = 4;
					break;
				}
				geldplaatjes.add(tafelmunt);
				break;
			}
		}
		if(money<0)
		{
			Image eenerafmunt;
			Image tweetalerafmunt;
			money*=-1;
			int tweetalleneraf = money/2;
			for (int k = 0; k< tweetalleneraf; k++)
			{
				tweetalerafmunt = new Image("/images/tafelmunt_2eraf.png");
				tweetalerafmunt.setStyleName("imagesmall");
				geldplaatjes.add(tweetalerafmunt);
			}
			int eneneraf = money - 2*tweetalleneraf;
			for (int j = 0; j< eneneraf; j++)
			{
				eenerafmunt = new Image("/images/tafelmunt_1eraf.png");
				eenerafmunt.setStyleName("imagesmall");
				geldplaatjes.add(eenerafmunt);
			}

		}
		return geldplaatjes;
	}

	public ArrayList<Product> getProductlist() {
		return productlist;
	}

	public void setProductlist(ArrayList<Product> productlist) {
		this.productlist = productlist;
	}

}
