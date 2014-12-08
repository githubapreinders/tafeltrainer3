 package afr.tafeltrainer3.client;


import afr.tafeltrainer3.client.shop.Shop;
import afr.tafeltrainer3.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class StartPage extends Composite 
{
	private AlertWidget alertwidget;
	private ClientImp client;
	private MainView main;
	private FlexTable flex1;
	private HorizontalPanel hpanel00;
	private Label lbl00;
	private HTML html00;
	private TextArea txtar10;
	private HorizontalPanel hpanel10;
	private HorizontalPanel hpanel20;
	private VerticalPanel vpanel10;
	private VerticalPanel vpanel10a;
	private VerticalPanel vpanel10b;
	public TextBox txtlogin;
	public TextBox txtpassw;
	private Label lbl10a;
	private Label lbl10b;
	public Button btn10;
	tafeltrainer3messages messages;
	
	public StartPage(MainView main,tafeltrainer3messages messages,AlertWidget alertwidget)
	{
		
		this.main = main;
		this.messages = messages;
		this.alertwidget = alertwidget;
		main.menu.hpanel2.setVisible(false);
		flex1 = new FlexTable();
		initWidget(this.flex1);
		this.flex1.setStyleName("tafeltabel");
		flex1.setCellSpacing(0);
		flex1.setCellPadding(0);
		flex1.setBorderWidth(0);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "250px");
		this.flex1.getFlexCellFormatter().setHeight(1, 0, "250px");
		this.flex1.getFlexCellFormatter().setWidth(1, 0, "280px");
		this.flex1.getFlexCellFormatter().setWidth(1, 1, "400px");
		this.flex1.getFlexCellFormatter().setWidth(1, 2, "280px");
		this.flex1.getFlexCellFormatter().setColSpan(0, 0, 3);
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		this.flex1.getFlexCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		this.flex1.getFlexCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);

		//eerste rij
		

		
		vpanel10  = new VerticalPanel();
		vpanel10.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		html00 = new HTML("<h1>"+messages.startpage_welkom()+"</h1>");
		html00.setStyleName("topkader");
		hpanel10 = new HorizontalPanel();
		hpanel10.setSpacing(10);
		hpanel10.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
		vpanel10a = new VerticalPanel();
		vpanel10a.setSpacing(10);
		vpanel10b = new VerticalPanel();
		vpanel10b.setSpacing(10);
		txtlogin = new TextBox();
		txtlogin.setText("gast");
		txtlogin.setStyleName("page4invulbox");
		txtpassw = new TextBox();
		txtpassw.setText("geheim");
		txtpassw.setStyleName("page4invulbox");
		lbl10a = new Label(messages.startpage_jeloginnaam());
		lbl10b = new Label(messages.startpage_jewachtwoord());
		vpanel10a.add(lbl10a);
		vpanel10a.add(txtlogin);
		vpanel10b.add(lbl10b);
		vpanel10b.add(txtpassw);
		btn10 = new Button("OK!");
		btn10.addClickHandler(new btn10ClickHandler());
		btn10.setStyleName("standardbutton");
		hpanel10.add(vpanel10a);
		hpanel10.add(vpanel10b);
		hpanel10.add(btn10);
		vpanel10.add(html00);
		vpanel10.add(hpanel10);
		flex1.setWidget(0, 0, vpanel10);
		
		
		//tweede rij
		VerticalPanel vpanel1 = new VerticalPanel();
		Image teacher = new Image("/images/leerkracht.png");
		teacher.addClickHandler(new anchor20dClickHandler());
		teacher.setHeight("200px");
		Label lbl00 = new Label(messages.startpage_demo_ouders());
		lbl00.setStyleName("html");
		lbl00.addClickHandler(new anchor20dClickHandler());
		vpanel1.add(teacher);
		vpanel1.add(lbl00);
		flex1.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flex1.setWidget(1, 0, vpanel1);
		
		VerticalPanel vpanel11 = new VerticalPanel();
		HTML sierlijn = new HTML("<HR width = '380px' size = '2'>");
		vpanel11.add(sierlijn);
		hpanel20 = new HorizontalPanel();
		Anchor hyper20a = new Anchor(messages.startpage_aanmnwbeg(),true);
		hyper20a.addClickHandler(new anchor20aClickHandler());
		Anchor hyper20b = new Anchor(messages.startpage_inloggenbeg(),true);
		hyper20b.addClickHandler(new anchor20bClickHandler());
		hpanel20.add(hyper20a);
		hpanel20.add(hyper20b);
		hpanel20.setSpacing(20);
		vpanel11.add(hpanel20);
		flex1.setWidget(1, 1, vpanel11);
		
		
		VerticalPanel vpanel2 = new VerticalPanel();
		Anchor hyper20c = new Anchor(messages.startpage_bekijkdedemo(),true);
		hyper20c.addClickHandler(new anchor20cClickHandler());
		Image kids = new Image("/images/kids.png");
		kids.setHeight("200px");
		kids.addClickHandler(new anchor20cClickHandler());
		Label lbl02 = new Label(messages.startpage_bekijkdedemo());
		lbl02.addClickHandler(new anchor20cClickHandler());
		lbl02.setStyleName("html");
		vpanel2.add(kids);
		vpanel2.add(lbl02);
		flex1.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flex1.setWidget(1, 2, vpanel2);
		main.client = new ClientImp(GWT.getModuleBaseURL()+"simpleservice", main);
		
		
		Devicetype d = GWT.create(Devicetype.class);
		main.gui = d;
		main.gui.initializeGui(main.client, main, messages);
		main.user = new User();
		main.fiba = null;
		main.wallet = new Wallet(main,messages);
		main.shop = new Shop(main,messages);
		

	}

	private class btn10ClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			btn10.setEnabled(false);
			if(txtlogin.getText().equals("gast") && txtpassw.getText().equals("geheim"))
			{
				alertwidget.getBox().setText("");
				alertwidget.getContentlabel().setText(messages.startpage_blankloginwarning());
				alertwidget.getBox().center();
			}
			
			main.showPageTafelTrainer();
			main.submitOnEntry(txtlogin.getText(),txtpassw.getText());
		}
		
	}
	
	private class anchor20aClickHandler implements ClickHandler
	{
	@Override
		public void onClick(ClickEvent event) 
		{
			main.showPageNieuweBegeleider();
		}
	}	

	private class anchor20bClickHandler implements ClickHandler
	{
	@Override
		public void onClick(ClickEvent event) 
		{
			main.showPageLoginBegeleider();
		}
	}	
	
	private class anchor20cClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			main.showDemopage();
		}
		
	}
	
	private class anchor20dClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			main.showDemoSuperuserpage();
		}
		
		
		
		
		
		
		
		
		
		
	}
}
