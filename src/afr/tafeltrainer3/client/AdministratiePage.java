package afr.tafeltrainer3.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import afr.tafeltrainer3.shared.FeedbackContainer;
import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.TafelResult;
import afr.tafeltrainer3.shared.User;
import afr.tafeltrainer3.shared.UserResults;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.visualization.client.AbstractDataTable;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.visualizations.Table;
import com.google.gwt.visualization.client.visualizations.Table.Options;
/**
 *Todo :  
 *-eerste van de userlijst kan niet verwijderd worden, herstelen
 *-gebruiksaanwijzingen user bijvoegen 
 *
 */



public class AdministratiePage extends Composite 
{
	
	public Anchor anchor11;
	public ArrayList<UserResults> mygroupresults;
	public ArrayList<User> mygroup;
	private Button btnadminpage2;
	private Button btnmailme;
	private HTML html1;
	private HTML html2;
	private HTML html3;
	private HTML html4;
	public FlexTable flex2;
	private FeedbackContainer fiba;
	public FlexTable flex1 = new FlexTable();
	private Label lbl11;
	private MainView main;
	private ScrollPanel spanel40;
	private ScrollPanel spanel41;
	public String defaultpassword;
	private SuperUser superuser;
	private VerticalPanel vpanel41;
	public boolean editingmode = false;
	tafeltrainer3messages messages;
	
	public AdministratiePage(MainView main, SuperUser superuser,tafeltrainer3messages messages)
	{
		this.main =main;
		this.superuser = superuser;
		this.fiba = new FeedbackContainer();
		this.messages = messages;
		mygroupresults = new ArrayList<UserResults>();
		mygroup = new ArrayList<User>();
		main.menu.hpanel2.setVisible(false);
		initWidget(flex1);
		
		this.flex1.setStyleName("tafeltabel");
		flex1.setCellSpacing(0);
		flex1.setCellPadding(0);
		flex1.setBorderWidth(0);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "5px");
		this.flex1.getFlexCellFormatter().setHeight(1, 0, "100px");
		this.flex1.getFlexCellFormatter().setHeight(2, 0, "45px");
		this.flex1.getFlexCellFormatter().setHeight(3, 0, "50px");
		this.flex1.getFlexCellFormatter().setHeight(4, 0, "300px");
		
		this.flex1.getFlexCellFormatter().setWidth(2, 0, "140px");
		this.flex1.getFlexCellFormatter().setWidth(2, 1, "140px");
		this.flex1.getFlexCellFormatter().setWidth(2, 2, "140px");
		this.flex1.getFlexCellFormatter().setWidth(2, 3, "140px");
		this.flex1.getFlexCellFormatter().setWidth(2, 4, "400px");
		this.flex1.getFlexCellFormatter().setColSpan(1, 0, 4);
		this.flex1.getFlexCellFormatter().setColSpan(3, 0, 4);

		this.flex1.getFlexCellFormatter().setColSpan(4, 0, 4);
		this.flex1.getFlexCellFormatter().setRowSpan(4, 0, 4);
		
		this.flex1.getFlexCellFormatter().setColSpan(2, 4, 3);
		this.flex1.getFlexCellFormatter().setRowSpan(2, 4, 6);

		//eerste rij
		HTML html10 = new HTML("<h1>"+messages.administratiepage_administratie()+"</h1>");
		html10.setStyleName("topkader");
		anchor11 = new Anchor(superuser.getName());
		anchor11.setStyleName("html");
		anchor11.addClickHandler(new anchor11ClickHandler());
		VerticalPanel vpanel11 = new VerticalPanel();
		lbl11 = new Label(superuser.getName());
		vpanel11.add(anchor11);
		flex1.setWidget(1, 0, html10);
		flex1.setWidget(1, 1, vpanel11);
		
		
		//tweede rij
		btnadminpage2 = new Button(messages.administratiepage_nieuw());
		btnadminpage2.setStyleName("standardbutton");
		btnadminpage2.addClickHandler(new BtnNewUserClickHandler(this.superuser.getEmail()));
		flex1.getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		flex1.setWidget(2, 0, btnadminpage2);
		
		btnmailme = new Button(messages.administratiepage_mail());
		btnmailme.setStyleName("standardbutton");
		btnmailme.addClickHandler(new BtnMailMeClickHandler());
		flex1.getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		flex1.setWidget(2, 1, btnmailme);
		
		
		//derde rij
		FlexTable flex3 = new FlexTable();
		flex3.setBorderWidth(0);
		flex3.setWidth("540px");
		flex3.getFlexCellFormatter().setWidth(0, 0, "150px");
		flex3.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flex3.getFlexCellFormatter().setWidth(0, 1, "90px");
		flex3.getFlexCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_CENTER);
		flex3.getFlexCellFormatter().setWidth(0, 2, "100px");
		flex3.getFlexCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);
		flex3.getFlexCellFormatter().setWidth(0, 3, "90px");
		flex3.getFlexCellFormatter().setHorizontalAlignment(0, 3, HasHorizontalAlignment.ALIGN_CENTER);
		flex3.getFlexCellFormatter().setWidth(0, 4, "110px");
		
		html1 = new HTML(messages.administratiepage_naam());
		html1.setStyleName("kleinelabels");
		flex3.setWidget(0, 0, html1);
		html2 = new HTML(messages.administratiepage_opgaven());
		html2.setStyleName("kleinelabels");
		flex3.setWidget(0, 1, html2);
		html3 = new HTML(messages.administratiepage_score());
		html3.setStyleName("kleinelabels");
		flex3.setWidget(0, 2, html3);
		html4 = new HTML(messages.administratiepage_snelheid());
		html4.setStyleName("kleinelabels");
		flex3.setWidget(0, 3, html4);
		
		flex1.setWidget(3, 0, flex3);
		
		
		//vierde rij
		this.spanel40 = new ScrollPanel();
		this.spanel40.setWidth("560px");
		this.spanel40.setHeight("250px");
		flex2 = new FlexTable();
		flex2.setBorderWidth(0);
		flex2.setWidth("540px");
		//flex2.setHeight("230px");
		flex2.getFlexCellFormatter().setWidth(0, 0, "150px");
		flex2.getFlexCellFormatter().setWidth(0, 1, "90px");
		flex2.getFlexCellFormatter().setWidth(0, 2, "100px");
		flex2.getFlexCellFormatter().setWidth(0, 3, "90px");
		flex2.getFlexCellFormatter().setWidth(0, 4, "55px");
		flex2.getFlexCellFormatter().setWidth(0, 5, "55px");
		this.spanel40.add(flex2);
		flex1.setWidget(4, 0, spanel40);
		
		this.spanel41 = new ScrollPanel();
		this.spanel41.setWidth("400px");
		this.spanel41.setHeight("395px");
		this.vpanel41 = new VerticalPanel();
		this.vpanel41.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
		spanel41.add(vpanel41);
		flex1.getFlexCellFormatter().setAlignment(2, 4,HasHorizontalAlignment.ALIGN_CENTER
				,HasVerticalAlignment.ALIGN_TOP);
		flex1.setWidget(2, 4, spanel41);
		
		main.client.getGroupResults(this.superuser);
	}

	private class BtnMailMeClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			Utilities.alertWidget(messages.administratiepage_onderweg(), messages.administratiepage_mail_is_verstuurd(), messages).center();
			main.client.sendMail(superuser);
		}
		
	}
	
	private class BtnNewUserClickHandler implements ClickHandler
	{
	String emailsuperuser = "";	
		public BtnNewUserClickHandler(String emailsuperuser) 
		{
			this.emailsuperuser = emailsuperuser;
		}
		
		@Override
		public void onClick(ClickEvent event) 
		{
			defaultpassword = "";
			if (!mygroup.isEmpty())
			{
				defaultpassword = mygroup.get(0).getPassword();
			}
			final DialogBox box = new DialogBox();
	        box.setStyleName("popup2");
	        final VerticalPanel panel = new VerticalPanel();
	        panel.setBorderWidth(0);
	        panel.setSpacing(20);
	        HTML htmlheading = new HTML("<h1>Nieuwe Leerling </h1>");
			htmlheading.setStyleName("topkader");
	        panel.add(htmlheading);
			
	        HorizontalPanel hpanel10 = new HorizontalPanel();
			HTML html10a = new HTML("Voornaam: ");
			html10a.setWidth("140px");
			html10a.setStyleName("html");
			html10a.setWordWrap(false);
			final TextBox txt10a = new TextBox();
			txt10a.setStyleName("page4invulbox");
			
			HTML html10b = new HTML("Achternaam: ");
			html10b.setWidth("140px");
			html10b.setStyleName("html");
			html10b.setWordWrap(false);
			final TextBox txt10b = new TextBox();
			txt10b.setStyleName("page4invulbox");
			
			final Label lbl10 = new Label();
			hpanel10.add(html10a);
			hpanel10.add(txt10a);
			hpanel10.add(html10b);
			hpanel10.add(txt10b);
			panel.add(hpanel10);
			//derde rij
			
			HorizontalPanel hpanel20 = new HorizontalPanel();
			
			HTML html20a = new HTML("Gebruikersnaam: ");
			html20a.setWidth("140px");
			html20a.setStyleName("html");
			html20a.setWordWrap(false);
			final TextBox txt20a = new TextBox();
			txt20a.setStyleName("page4invulbox");
			final Label lbl20 = new Label("");
			
			HTML html20b = new HTML("Wachtwoord: ");
			html20b.setWidth("140px");
			html20b.setStyleName("html");
			html20b.setWordWrap(false);
			final TextBox txt20b = new TextBox();
			txt20b.setText(defaultpassword);
			txt20b.setStyleName("page4invulbox");
			
			hpanel20.add(html20a);
			hpanel20.add(txt20a);
			hpanel20.add(html20b);
			hpanel20.add(txt20b);
			
			panel.add(hpanel20);
			
			//radiobuttons
			VerticalPanel vpanel40 = new VerticalPanel();
			HTML html40 = new HTML("Hou me op de hoogte");
			html40.setStyleName("html_fout");
		    final RadioButton rb0 = new RadioButton("Hou in de gaten", "nee ");
		    final RadioButton rb1 = new RadioButton("Hou in de gaten", "doe maar");
		    rb0.setStyleName("html");
		    rb1.setStyleName("html");
		    rb0.setValue(true);
		    
		    HorizontalPanel hpanelrbrow = new HorizontalPanel();
		    vpanel40.add(html40);
			HorizontalPanel hpanelrbutton = new HorizontalPanel();
			hpanelrbutton.add(rb0);
			hpanelrbutton.add(rb1);
			vpanel40.add(hpanelrbutton);
			hpanelrbrow.add(vpanel40);
			
			final Anchor deleteuser = new Anchor(" X ", false);
			deleteuser.setStyleName("anchor_red");
			
			panel.add(hpanelrbrow);
	        final Button buttonClose = new Button("Annuleren",new ClickHandler() 
		        {
					@Override
					public void onClick(ClickEvent event) 
					{
						 box.hide();				
					}
		        });
	        
	        final Button buttonSubmit = new Button("Opslaan",new ClickHandler() 
			{
			@Override
			public void onClick(ClickEvent event) 
			{
				boolean valid=validate(txt10a,txt10b,txt20a,txt20b);
				String username = txt10a.getText();
				String familyname = txt10b.getText();
				String loginname = txt20a.getText();
				String passw = txt20b.getText();
						
				if(valid)
				{
					main.client.addNewUser(new User(0,emailsuperuser,correctName(username),correctName(familyname),"my group", loginname,passw,0,false));
					box.hide();
				}
				}

			

			});
	        
	        buttonClose.setStyleName("standardbutton");
	        buttonSubmit.setStyleName("standardbutton");
	        buttonClose.setWidth("120px");
	        buttonSubmit.setWidth("120px");
	        HorizontalPanel hpanelbuttons = new HorizontalPanel();
	        hpanelbuttons.add(buttonClose);
	        hpanelbuttons.add(buttonSubmit);
	        panel.add(hpanelbuttons);
	        box.add(panel);
			box.center();
			//main.showPageAdministratie2();
			}
	}
	
	public String correctName(String name)
	{
		ArrayList<Integer>capitalindexes = new ArrayList<Integer>();
		capitalindexes.add(0);
		name.trim();
		StringBuilder str = new StringBuilder(name);
		for(int i =0;i<str.length()-1;i++)
		{
			if(str.charAt(i)==' ')
			{
				capitalindexes.add(i+1);
			}
			else str.setCharAt(i, Character.toLowerCase(str.charAt(i)));
		}
		for(int j : capitalindexes)
		
		str.setCharAt(j, Character.toUpperCase(str.charAt(j)));
		return str.toString();
	}
	
	
private class anchor11ClickHandler implements ClickHandler 
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			final DialogBox box = new DialogBox();
	        //box.setPixelSize(350, 550);
	        box.setStyleName("popup2");
	        final VerticalPanel panel = new VerticalPanel();
	        panel.setBorderWidth(0);
	        panel.setSpacing(20);
	        HTML htmlheading = new HTML("<h1>Gegevens Wijzigen</h1>");
			htmlheading.setStyleName("topkader");
	        panel.add(htmlheading);
			
	        HorizontalPanel hpanel10 = new HorizontalPanel();
			HTML html10 = new HTML("Uw naam: ");
			html10.setWidth("140px");
			html10.setStyleName("html");
			html10.setWordWrap(false);
			final TextBox txt10 = new TextBox();
			txt10.setStyleName("page4invulbox");
			txt10.setText(superuser.getName());
			final Label lbl10 = new Label();
			hpanel10.add(html10);
			hpanel10.add(txt10);
			hpanel10.add(lbl10);
			panel.add(hpanel10);
			//derde rij
			HorizontalPanel hpanel20 = new HorizontalPanel();
			HTML html20 = new HTML("Emailadres: ");
			html20.setWidth("140px");
			html20.setStyleName("html");
			html20.setWordWrap(false);
			final TextBox txt20 = new TextBox();
			txt20.setText(superuser.getEmail());
			txt20.setStyleName("page4invulbox");
			final Label lbl20 = new Label("");
			hpanel20.add(html20);
			hpanel20.add(txt20);
			hpanel20.add(lbl20);
			panel.add(hpanel20);
			//vierde rij
			HorizontalPanel hpanel30 = new HorizontalPanel();
			HTML html30a = new HTML("Wachtwoord: ");
			html30a.setWidth("140px");
			html30a.setStyleName("html");
			html30a.setWordWrap(false);
			final PasswordTextBox txt30a = new PasswordTextBox();
			txt30a.setText(superuser.getPassword());
			txt30a.setStyleName("page4invulbox");
			HTML html30b = new HTML("Ww herhaald: ");
			html30b.setWidth("100px");
			html30b.setStyleName("html");
			html30b.setWordWrap(false);
			final PasswordTextBox txt30b = new PasswordTextBox();
			txt30b.setText(superuser.getPassword());
			txt30b.setStyleName("page4invulbox");
			final Label lbl30a  = new Label();
			lbl30a.setWordWrap(true);
			final Label lbl30b  = new Label();
			lbl30b.setWordWrap(true);
			hpanel30.add(html30a);
			hpanel30.add(txt30a);
			hpanel30.add(lbl30a);
			hpanel30.add(html30b);
			hpanel30.add(txt30b);
			hpanel30.add(lbl30b);
			panel.add(hpanel30);
	        
			//radiobuttons
			VerticalPanel vpanel40 = new VerticalPanel();
			HTML html40 = new HTML("Hou me op de hoogte");
			html40.setStyleName("html_fout");
		    final RadioButton rb0 = new RadioButton("Hou me op de hoogte", "nee bedankt");
		    final RadioButton rb1 = new RadioButton("Hou me op de hoogte", "wekelijks");
		    final RadioButton rb2 = new RadioButton("Hou me op de hoogte", "tweewekelijks");
		    rb0.setStyleName("html");
		    rb1.setStyleName("html");
		    rb2.setStyleName("html");
		    if(superuser.getEmailfrequency()==0)
		    rb0.setValue(true);
			if(superuser.getEmailfrequency()==1)
			rb1.setValue(true);
			if(superuser.getEmailfrequency()==2)
			rb2.setValue(true);	
		    
		    vpanel40.add(html40);
			HorizontalPanel hpanelrbutton = new HorizontalPanel();
			hpanelrbutton.add(rb0);
			hpanelrbutton.add(rb1);
			hpanelrbutton.add(rb2);
			vpanel40.add(hpanelrbutton);
			panel.add(vpanel40);
			
	        
	        final Button buttonClose = new Button("Annuleren",new ClickHandler() 
		        {
					@Override
					public void onClick(ClickEvent event) 
					{
						 box.hide();				
					}
		        });
	        
	        final Button buttonSubmit = new Button("Opslaan",new ClickHandler() 
    		{
			@Override
			public void onClick(ClickEvent event) 
			{
				boolean valid=false;
				boolean validpw=false;
				boolean validusername = false;
				
					String username = txt10.getText();
					validusername = Validate.checkTextbox(username);
					if(!validusername)
					{
						lbl10.setVisible(true);
						lbl10.setStyleName("middellabels");
						lbl10.setText(NewSuperUser.infoString("naam"));
						txt10.setText("");
						txt10.setFocus(true);
						txt20.setStyleName("page4invulbox_fout");
					}
					else
					{
						lbl10.setVisible(true);
						lbl10.setStyleName("labelgoedgekeurd");
						lbl10.setText(" V");
					}
					
					String email = txt20.getText();
					valid = Validate.checkEmail(email);
					if(!valid)
					{
						lbl20.setVisible(true);
						lbl20.setStyleName("middellabels");
						lbl20.setText(NewSuperUser.infoString("email"));
						txt20.setText("");
						txt20.setFocus(true);
						txt20.setStyleName("page4invulbox_fout");
					}
					else
					{
						txt20.setStyleName("page4invulbox");
						lbl20.setStyleName("labelgoedgekeurd");
						lbl20.setText(" V");
					}
					
					String ww1 = txt30a.getText();
					String ww2 = txt30b.getText();
					if(!(ww1.equals(ww2)))
					{	
						txt30a.setText("");
						txt30b.setText("");
						txt30a.setFocus(true);
						txt30a.setStyleName("page4invulbox_fout");
						txt30b.setStyleName("page4invulbox_fout");
						lbl30b.setVisible(true);
						lbl30b.setStyleName("middellabels");
						lbl30b.setText(NewSuperUser.infoString("unequalpasswords"));
					}
					if(ww1.equals(ww2))
					
						{validpw = Validate.checkPassword(ww1);
					
					if(!validpw)
					{
						txt30a.setText("");
						txt30b.setText("");
						txt30a.setFocus(true);
						lbl30b.setVisible(true);
						txt30a.setStyleName("page4invulbox_fout");
						txt30b.setStyleName("page4invulbox_fout");
						lbl30b.setStyleName("middellabels");
						lbl30b.setText(NewSuperUser.infoString("password"));
					}	
					else
					{
						lbl30a.setStyleName("labelgoedgekeurd");
						lbl30a.setText(" V");
						lbl30b.setStyleName("labelgoedgekeurd");
						lbl30b.setText(" V");
						txt30a.setStyleName("page4invulbox");
						txt30b.setStyleName("page4invulbox");
					}
					}
					
				if(valid&&validpw&&validusername)
				{
					int frequency =0;
					if(rb1.getValue()==true){frequency = 1;}
					if(rb2.getValue()==true){frequency = 2;}
					main.client.updateSuperUser(new SuperUser(username,email,ww1,frequency),superuser.getEmail());
					superuser.setName(username);
					superuser.setEmail(email);
					superuser.setPassword(ww1);
					superuser.setEmailfrequency(frequency);
					main.administratiepage.anchor11.setText(username);
					box.hide();
				}
				}

			});
	        
	        buttonClose.setStyleName("standardbutton");
	        buttonSubmit.setStyleName("standardbutton");
	        buttonClose.setWidth("120px");
	        buttonSubmit.setWidth("120px");
	        HorizontalPanel hpanelbuttons = new HorizontalPanel();
	        hpanelbuttons.add(buttonClose);
	        hpanelbuttons.add(buttonSubmit);
	        panel.add(hpanelbuttons);
	        box.add(panel);
	        box.center();
		}
		
	}
	
	public void setFiba(FeedbackContainer fiba) 
	{
		this.fiba = fiba;
	}

		
	public void updateVpanel41(FeedbackContainer fiba)
	{
		flex1.setWidget(2, 3, new HTML(""));
		for(TafelResult t : fiba.getTafelresultaten())
		{
			TafelResultWidget trw = new TafelResultWidget(t);
			vpanel41.add(trw);
		}
		HTML pastthreesessions = new HTML(fiba.getPastThreeSessions());
		pastthreesessions.setStyleName("html");
		vpanel41.add(pastthreesessions);
	}
		
	/**
	 *bij het binnenkomen op deze pagina worden de samengevatte resultaten van de groepsleden 
	 *in een tabel gezet. Nadat de tabel er staat worden alvast de verdere groepsgegevens gehaald 
	 *Na het aankliken van "edit" verschijnt er een popup waar usergegevens kunnen worden bijgewerkt.
	 */
	
	public void makeUserResultsList( ArrayList<UserResults> results)
	{
		ArrayList<UserResults> mygroupresultsdummy = new ArrayList<UserResults>();
		flex2.clear();
		flex1.setWidget(2, 3, new HTML(""));
		Anchor edituser = new Anchor();
		Anchor lookatuser;
		HTML html1;
		HTML html2;
		HTML html3;
		HTML html4;
		int y = 1;
		for( UserResults u : results)
		{
			edituser = new Anchor(messages.administratiepage_bewerk(),false);
			edituser.addClickHandler(new EdituserClickHandler(u)); 
				
			lookatuser = new Anchor(messages.administratiepage_resultaten(),false);
			lookatuser.addClickHandler(new LookatUserClickHandler(u)); 
			
			html1 = new HTML(u.getUsername() + " " + u.getFamilyname());
			html1.setStyleName("middellabels");
			html2 = new HTML(String.valueOf(u.getHowmuchopgaven()));
			html2.setStyleName("middellabels");
			html3 = new HTML(String.valueOf(u.getPercentagescore()+ "%"));
			html3.setStyleName("middellabels");
			html4 = new HTML(String.valueOf(u.getAveragespeed()));
			html4.setStyleName("middellabels");
			flex2.setWidget(y, 0, html1);
			flex2.setWidget(y, 1, html2);
			flex2.setWidget(y, 2, html3);
			flex2.setWidget(y, 3, html4);
			flex2.setWidget(y, 4, edituser);
			flex2.setWidget(y, 5, lookatuser);
			y++;
			
			mygroupresultsdummy.add(u);
		}
	mygroupresults.clear();
	mygroupresults = mygroupresultsdummy;
	main.client.getGroup(superuser);
	}

	private class LookatUserClickHandler implements ClickHandler
	{
		UserResults u ;
		public LookatUserClickHandler(UserResults userresults)
		{
			this.u = userresults;
		}
		
		@Override
		public void onClick(ClickEvent event) 
		{
			HTML waitingmessage = new HTML("loading...");
			waitingmessage.setStyleName("middellabels");
			flex1.setWidget(2, 3, waitingmessage);
			vpanel41.clear();
			HTML name = new HTML(u.getUsername() + " " + u.getFamilyname());
			name.setStyleName("kleinelabelsvoldoende");
			vpanel41.add(name);
			main.client.getFeedbackData(u.getUserid());
		}
		
	}
	
	private class EdituserClickHandler implements ClickHandler

	{
		
	public UserResults u;	
		public EdituserClickHandler(UserResults userresult)
		{
			this.u = userresult;
		}
		
		@Override
		public void onClick(ClickEvent event) 
		{
			User currentuser = null;	
			for(User thisuser : mygroup)
			{
				if(u.getUserid()==thisuser.getId())
				{
					currentuser = thisuser;
				}
			}
			final int id = currentuser.getId();
			final String emailsuperuser = currentuser.getEmailsuperuser();
			final int moneyuser = currentuser.getMoney();
			//final User anotheruser = currentuser;
			
			final DialogBox box = new DialogBox();
	        box.setStyleName("popup2");
	        final VerticalPanel panel = new VerticalPanel();
	        panel.setBorderWidth(0);
	        panel.setSpacing(20);
	        HTML htmlheading = new HTML("<h1>Leerling </h1>");
			htmlheading.setStyleName("topkader");
	        panel.add(htmlheading);
			
	        HorizontalPanel hpanel10 = new HorizontalPanel();
			HTML html10a = new HTML("Voornaam: ");
			html10a.setWidth("140px");
			html10a.setStyleName("html");
			html10a.setWordWrap(false);
			final TextBox txt10a = new TextBox();
			txt10a.setStyleName("page4invulbox");
			txt10a.setText(currentuser.getName());
			
			HTML html10b = new HTML("Achternaam: ");
			html10b.setWidth("140px");
			html10b.setStyleName("html");
			html10b.setWordWrap(false);
			final TextBox txt10b = new TextBox();
			txt10b.setStyleName("page4invulbox");
			txt10b.setText(currentuser.getFamilyname());
			
			final Label lbl10 = new Label();
			hpanel10.add(html10a);
			hpanel10.add(txt10a);
			hpanel10.add(html10b);
			hpanel10.add(txt10b);
			panel.add(hpanel10);
			//derde rij
			
			HorizontalPanel hpanel20 = new HorizontalPanel();
			
			HTML html20a = new HTML("Gebruikersnaam: ");
			html20a.setWidth("140px");
			html20a.setStyleName("html");
			html20a.setWordWrap(false);
			final TextBox txt20a = new TextBox();
			txt20a.setText(currentuser.getLoginname());
			txt20a.setStyleName("page4invulbox");
			final Label lbl20 = new Label("");
			
			HTML html20b = new HTML("Wachtwoord: ");
			html20b.setWidth("140px");
			html20b.setStyleName("html");
			html20b.setWordWrap(false);
			final TextBox txt20b = new TextBox();
			txt20b.setText(currentuser.getPassword());
			txt20b.setStyleName("page4invulbox");
			
			hpanel20.add(html20a);
			hpanel20.add(txt20a);
			hpanel20.add(html20b);
			hpanel20.add(txt20b);
			
			panel.add(hpanel20);
			
			//radiobuttons
			VerticalPanel vpanel40 = new VerticalPanel();
			HTML html40 = new HTML("Hou me op de hoogte");
			html40.setStyleName("html_fout");
		    final RadioButton rb0 = new RadioButton("Hou in de gaten", "nee ");
		    final RadioButton rb1 = new RadioButton("Hou in de gaten", "doe maar");
		    rb0.setStyleName("html");
		    rb1.setStyleName("html");
		    if(currentuser.getHouindegaten()==true)
		    rb1.setValue(true);
		    else rb0.setValue(true);
		    
		    HorizontalPanel hpanelrbrow = new HorizontalPanel();
		    vpanel40.add(html40);
			HorizontalPanel hpanelrbutton = new HorizontalPanel();
			hpanelrbutton.add(rb0);
			hpanelrbutton.add(rb1);
			vpanel40.add(hpanelrbutton);
			hpanelrbrow.add(vpanel40);
			
			final Anchor deleteuser = new Anchor(" X ", false);
			deleteuser.setStyleName("anchor_red");
			panel.add(hpanelrbrow);
			
	        
	        final Button buttonClose = new Button("Annuleren",new ClickHandler() 
		        {
					@Override
					public void onClick(ClickEvent event) 
					{
						 box.hide();				
					}
		        });
	        
	        final Button buttonSubmit = new Button("Opslaan",new ClickHandler() 
			{
			@Override
			public void onClick(ClickEvent event) 
			{
				boolean valid=validate(txt10a,txt10b,txt20a,txt20b);
				String username = txt10a.getText();
				String familyname = txt10b.getText();
				String loginname = txt20a.getText();
				String passw = txt20b.getText();
				if(valid)
				{
					main.client.superuserUpdatesUser(new User(id,emailsuperuser,
							correctName(username),correctName(familyname),"my group",loginname,passw,moneyuser,rb1.getValue()));
					main.administratiepage.updateUserResults(id,correctName(username),correctName(familyname));
					box.hide();
				}
				}

			});
	        
	        buttonClose.setStyleName("standardbutton");
	        buttonSubmit.setStyleName("standardbutton");
	        buttonClose.setWidth("120px");
	        buttonSubmit.setWidth("120px");
	        HorizontalPanel hpanelbuttons = new HorizontalPanel();
	        hpanelbuttons.add(buttonClose);
	        hpanelbuttons.add(buttonSubmit);
	        panel.add(hpanelbuttons);
	        box.add(panel);
			box.center();
			//main.showPageAdministratie2();
			}


	
	}
	
	
	public void makeUserResultsList() 
	{
		//flex1.setWidget(2, 3, new HTML(""));
		flex2.clear();
		Anchor edituser = new Anchor();
		Anchor lookatuser;
		HTML html1;
		HTML html2;
		HTML html3;
		HTML html4;
		int y = 1;
		for(final UserResults u : mygroupresults)
		{
			edituser = new Anchor("edit",false);
			edituser.addClickHandler(new ClickHandler() 
				{
				/**
				 * gaat naar de edituserpage en zet de gegevens in het editvak;	
				 */
				@Override
					public void onClick(ClickEvent event) 
					
				{
					User currentuser = null;	
					for(User thisuser : mygroup)
					{
						if(u.getUserid()==thisuser.getId())
						{
							currentuser = thisuser;
						}
					}
					final int id = currentuser.getId();
					final int usermoney = currentuser.getMoney();
					final String emailsuperuser = currentuser.getEmailsuperuser();
					//final User anotheruser = currentuser;
					
					final DialogBox box = new DialogBox();
					//initWidget(box);
			        box.setStyleName("popup2");
			        final VerticalPanel panel = new VerticalPanel();
			        panel.setBorderWidth(0);
			        panel.setSpacing(20);
			        HTML htmlheading = new HTML("<h1>Leerling </h1>");
					htmlheading.setStyleName("topkader");
			        panel.add(htmlheading);
					
			        HorizontalPanel hpanel10 = new HorizontalPanel();
					HTML html10a = new HTML("Voornaam: ");
					html10a.setWidth("140px");
					html10a.setStyleName("html");
					html10a.setWordWrap(false);
					final TextBox txt10a = new TextBox();
					txt10a.setStyleName("page4invulbox");
					txt10a.setText(currentuser.getName());
					
					HTML html10b = new HTML("Achternaam: ");
					html10b.setWidth("140px");
					html10b.setStyleName("html");
					html10b.setWordWrap(false);
					final TextBox txt10b = new TextBox();
					txt10b.setStyleName("page4invulbox");
					txt10b.setText(currentuser.getFamilyname());
					
					final Label lbl10 = new Label();
					hpanel10.add(html10a);
					hpanel10.add(txt10a);
					hpanel10.add(html10b);
					hpanel10.add(txt10b);
					panel.add(hpanel10);
					//derde rij
					
					HorizontalPanel hpanel20 = new HorizontalPanel();
					
					HTML html20a = new HTML("Gebruikersnaam: ");
					html20a.setWidth("140px");
					html20a.setStyleName("html");
					html20a.setWordWrap(false);
					final TextBox txt20a = new TextBox();
					txt20a.setText(currentuser.getLoginname());
					txt20a.setStyleName("page4invulbox");
					final Label lbl20 = new Label("");
					
					HTML html20b = new HTML("Wachtwoord: ");
					html20b.setWidth("140px");
					html20b.setStyleName("html");
					html20b.setWordWrap(false);
					final TextBox txt20b = new TextBox();
					txt20b.setText(currentuser.getPassword());
					txt20b.setStyleName("page4invulbox");
					
					hpanel20.add(html20a);
					hpanel20.add(txt20a);
					hpanel20.add(html20b);
					hpanel20.add(txt20b);
					
					panel.add(hpanel20);
					
					//radiobuttons
					VerticalPanel vpanel40 = new VerticalPanel();
					HTML html40 = new HTML("Hou me op de hoogte");
					html40.setStyleName("html_fout");
				    final RadioButton rb0 = new RadioButton("Hou in de gaten", "nee ");
				    final RadioButton rb1 = new RadioButton("Hou in de gaten", "doe maar");
				    rb0.setStyleName("html");
				    rb1.setStyleName("html");
				    rb0.setValue(true);
				    
				    HorizontalPanel hpanelrbrow = new HorizontalPanel();
				    vpanel40.add(html40);
					HorizontalPanel hpanelrbutton = new HorizontalPanel();
					hpanelrbutton.add(rb0);
					hpanelrbutton.add(rb1);
					vpanel40.add(hpanelrbutton);
					hpanelrbrow.add(vpanel40);
					
					panel.add(hpanelrbrow);
					
			        
			        final Button buttonClose = new Button("Annuleren",new ClickHandler() 
				        {
							@Override
							public void onClick(ClickEvent event) 
							{
								 box.hide();				
							}
				        });
			        
			        final Button buttonSubmit = new Button("Opslaan",new ClickHandler() 
					{
					@Override
					public void onClick(ClickEvent event) 
					{
						boolean valid=validate(txt10a,txt10b,txt20a,txt20b);
						String username = txt10a.getText();
						String familyname = txt10b.getText();
						String loginname = txt20a.getText();
						String passw = txt20b.getText();
								
						if(valid)
						{
							main.client.superuserUpdatesUser(new User(id,emailsuperuser,
									correctName(username),correctName(familyname),"my group",loginname,passw,usermoney,rb1.getValue()));
							
							box.hide();
						}
						}

					});
			        
			        buttonClose.setStyleName("standardbutton");
			        buttonSubmit.setStyleName("standardbutton");
			        buttonClose.setWidth("120px");
			        buttonSubmit.setWidth("120px");
			        HorizontalPanel hpanelbuttons = new HorizontalPanel();
			        hpanelbuttons.add(buttonClose);
			        hpanelbuttons.add(buttonSubmit);
			        panel.add(hpanelbuttons);
			        box.add(panel);
					box.center();
					//main.showPageAdministratie2();
					}
				});
				lookatuser = new Anchor("results",false);
				lookatuser.addClickHandler(new ClickHandler() 
				{
				/**
				 * haalt resultaten van een user op en zet die in een tabel	
				 */
				@Override
					public void onClick(ClickEvent event) 
					{
						HTML waitingmessage = new HTML("loading...");
						waitingmessage.setStyleName("middellabels");
						flex1.setWidget(2, 3, waitingmessage);
						vpanel41.clear();
						HTML name = new HTML(u.getUsername() + " " + u.getFamilyname());
						name.setStyleName("kleinelabelsvoldoende");
						vpanel41.add(name);
						main.client.getSuperuserFeedback(u.getUserid());
					}
				});
			html1 = new HTML(u.getUsername() + " " + u.getFamilyname());
			html1.setStyleName("middellabels");
			html2 = new HTML(String.valueOf(u.getHowmuchopgaven()));
			html2.setStyleName("middellabels");
			html3 = new HTML(String.valueOf(u.getPercentagescore()+ "%"));
			html3.setStyleName("middellabels");
			html4 = new HTML(String.valueOf(u.getAveragespeed()));
			html4.setStyleName("middellabels");
			flex2.setWidget(y, 0, html1);
			flex2.setWidget(y, 1, html2);
			flex2.setWidget(y, 2, html3);
			flex2.setWidget(y, 3, html4);
			flex2.setWidget(y, 4, edituser);
			flex2.setWidget(y, 5, lookatuser);
			y++;
		}
	}

	
public void addGroupmember(User user)
	{
			this.mygroup.add(0, user);
			updateUserResults(user.getId(),user.getName(),user.getFamilyname());
	}
	
	
protected void updateUserResults(int id, String uname, String fname) 
	{
		int index = -1;	
		int opgaven =0 ;
		double score =0;
		double speed = 0;
		
		for(UserResults u : mygroupresults)
		{
			if(u.getUserid()==id)
			{
				opgaven = u.getHowmuchopgaven();
				score = u.getPercentagescore();
				speed = u.getAveragespeed();
				index = mygroupresults.indexOf(u);
			}
		}
		if (index!=-1)
		{
			mygroupresults.remove(index);
			mygroupresults.add(new UserResults(id,uname,fname,score,opgaven,speed));
			makeUserResultsList(mygroupresults);
		}
		else
		{
			mygroupresults.add(new UserResults(id,uname,fname,score,opgaven,speed));
			makeUserResultsList(mygroupresults);
		}
	}

	public boolean validate(TextBox txt10a,TextBox txt10b,TextBox txt20a,TextBox txt20b)
	{
		boolean valid=true; 
		
		if(txt10a.getText().length()<1||txt10a.getText().length()>15)
		{
			txt10a.setStyleName("admininvulbox_fouteinvoer");valid = false;
		}
		else txt10a.setStyleName("admininvulbox");
		
		if(txt10b.getText().length()<1||txt10b.getText().length()>15)
		{
			txt10b.setStyleName("admininvulbox_fouteinvoer");valid = false;
		}
		else txt10b.setStyleName("admininvulbox");
		
		if(txt20a.getText().length()<1||txt20a.getText().length()>15)
		{
			txt20a.setStyleName("admininvulbox_fouteinvoer");valid = false;
		}
		else txt20a.setStyleName("admininvulbox");
		
		if(txt20b.getText().length()<1||txt20b.getText().length()>15)
		{
			txt20b.setStyleName("admininvulbox_fouteinvoer");valid = false;
		}
		else txt20b.setStyleName("admininvulbox");
		
		
	return valid;
	}	

	
	
	
	
}
	
	
		
	
	
	
	
	
	
	
	
	

