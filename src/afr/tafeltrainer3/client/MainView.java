package afr.tafeltrainer3.client;

import java.util.ArrayList;
import java.util.Date;

import afr.tafeltrainer3.client.events.DataEvent;
import afr.tafeltrainer3.client.events.EventAddParentsMailaddress;
import afr.tafeltrainer3.client.events.EventAddSuperUser;
import afr.tafeltrainer3.client.events.EventGetGroup;
import afr.tafeltrainer3.client.events.EventGetGroupResults;
import afr.tafeltrainer3.client.events.EventProductsRetrieved;
import afr.tafeltrainer3.client.events.EventSuperUserRetrieved;
import afr.tafeltrainer3.client.events.EventSuperuserFeedback;
import afr.tafeltrainer3.client.events.EventUserFeedback;
import afr.tafeltrainer3.client.events.EventUserNew;
import afr.tafeltrainer3.client.events.EventUserRetrieved;
import afr.tafeltrainer3.client.events.EventVerifyMail;
import afr.tafeltrainer3.client.shop.Shop;
import afr.tafeltrainer3.shared.FeedbackContainer;
import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.TafelResult;
import afr.tafeltrainer3.shared.User;
import afr.tafeltrainer3.shared.UserResults;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HasAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class MainView extends Composite implements ValueChangeHandler
{

	private static final String Startpagina_Token = "startpagina";
	private static final String Tafeltrainerpagina_Token = "tafeltrainerpagina";
	private static final String Loginbegeleiderpagina_Token = "loginbegeleiderpagina";
	private static final String Administratiepagina_Token = "administratiepagina";
	private static final String Feedbackpagina_Token = "feedbackpagina";
	private static final String Walletpagina_Token = "portemonneepagina";
	private static final String Shoppagina_Token = "shoppagina";
	private static final String Nieuwebegeleiderpagina_Token = "inschrijven";
	private static final String Demobegeleiderpagina_Token = "demobegeleiderpagina";
	private static final String Demokidspagina_Token = "demokidspagina";
	private static final String Landingspagina_Token = "landingpage";
	private static final String Parentmenupagina_Token = "parentmenupage";

	private static final String SOUND1 = "/sounds/124755__tec-studios_bell_A.mp3";
	private static final String SOUND2 = "/sounds/124755__tec-studios_bell_C.mp3";
	private static final String SOUND3 = "/sounds/124755__tec-studios_bell_D.mp3";
	private static final String SOUND4 = "/sounds/124755__tec-studios_bell_Fis.mp3";
	private static final String SOUND5 = "/sounds/124755__tec-studios_bell_DFisAC.mp3";
	private static final String SOUND6 = "/sounds/124755__tec-studios_bell_Dissonant.mp3";

	private ArrayList<String> soundurls;
	public ArrayList<Audio> soundfiles;
	private Audio thesound;

	public AdministratiePage administratiepage;
	public ClientImp client;
	public AlertWidget alertwidget;
	public FeedbackPage fbpage;
	public FeedbackContainer fiba;
	public LoginSuperUser loginsuperuser;
	public ParentMenu parentmenu;
	public MenuView menu;
	public NewSuperUser newsuperuser;
	public Shop shop;
	public StartPage startpage;
	private SuperUser superuser;
	public Tafeltrainer3Gui gui;
	public tafeltrainer3messages messages;
	public User user;
	private VerticalPanel vpanel;
	private VerticalPanel contentpanel;
	public Wallet wallet;
	public LandingPage landingpage;
	public Vragenlijst vragenlijst;
	public Gast gast;

	// alle pagina's worden hier gemaakt en vanuit hier geupdatet. Het
	// contentpanel bevat deze pagina's,
	// het menu wordt hier ook geinstantieerd. De rpc-calls gaan allemaal via de
	// clientimplementation.
	public MainView()
	{
		vpanel = new VerticalPanel();
		vpanel.setStyleName("vpanelmain");
		initWidget(this.vpanel);
		this.messages = GWT.create(tafeltrainer3messages.class);
		this.menu = new MenuView(this, messages);
		this.vpanel.add(menu);
		this.contentpanel = new VerticalPanel();
		this.contentpanel.setStyleName("contentpanel");
		alertwidget = new AlertWidget(messages);
		this.startpage = new StartPage(this, messages, alertwidget);
		this.vragenlijst = new Vragenlijst(this);
		this.contentpanel.add(startpage);
		this.vpanel.add(contentpanel);
		this.fbpage = new FeedbackPage(this, fiba, this.messages);
		this.loginsuperuser = new LoginSuperUser(this);
		this.parentmenu = new ParentMenu(this);
		this.newsuperuser = new NewSuperUser(this, messages, alertwidget);
		
		//initSound();

		History.addValueChangeHandler(this);
		History.newItem(Startpagina_Token);

		// verification email-adress
		String s = Window.Location.getParameter("codestring");
		if (s != null)
		{
			client.verifyMailadress(Window.Location.getParameter("codestring"));
		}

	}

	private void initSound()
	{
		soundurls = new ArrayList<String>();
		soundfiles = new ArrayList<Audio>();
		soundurls.add(SOUND1);
		soundurls.add(SOUND2);
		soundurls.add(SOUND3);
		soundurls.add(SOUND4);
		soundurls.add(SOUND5);
		soundurls.add(SOUND6);
		for (String url : soundurls)
		{
			thesound = Audio.createIfSupported();
			thesound.setSrc(url);
			soundfiles.add(thesound);
		}
	}
	
	
	
	// Backbuttonkliks worden hier afgehandeld om te voorkomen dat gebruikers
	// van de site af kukelen bij
	// een klik op "terug"
	@Override
	public void onValueChange(ValueChangeEvent event)
	{
		String historytoken = (String) event.getValue();
		if (historytoken.equals(Startpagina_Token))
		{
			showPageStartPage();
		}
		if (historytoken.equals(Tafeltrainerpagina_Token))
		{
			showPageTafelTrainer();
		}
		if (historytoken.equals(Administratiepagina_Token))
		{
			showPageAdministratie();
		}
		if (historytoken.equals(Loginbegeleiderpagina_Token))
		{
			showPageLoginBegeleider();
		}
		if (historytoken.equals(Feedbackpagina_Token))
		{
			showPageResultaten();
		}
		if (historytoken.equals(Walletpagina_Token))
		{
			showPagePortemonnee();
			;
		}
		if (historytoken.equals(Shoppagina_Token))
		{
			showPageWinkeltje();
		}
		if (historytoken.equals(Nieuwebegeleiderpagina_Token))
		{
			showPageNieuweBegeleider();
		}
		if (historytoken.equals(Demobegeleiderpagina_Token))
		{
			showDemoSuperuserpage();
		}
		if (historytoken.equals(Demokidspagina_Token))
		{
			showDemopage();
		}
		if (historytoken.equals(Landingspagina_Token))
		{
			showLandingpage();
		}

		if(historytoken.equals(Parentmenupagina_Token))
		{
			showPageParentMenu();
		}
	}

	// hanteert rpc-calls die door de users worden geintieerd : inloggen,
	// gebruikers aanmaken en wijzigen
	// groepslijsten maken en dergelijke.
	public void handleEvent(DataEvent result)
	{

		if (result instanceof EventVerifyMail)
		{
			EventVerifyMail evm = (EventVerifyMail) result;
			if (evm.getParameter().equals("succes"))
			{
				showLandingpage();
			} else
			{
				alertwidget.getBox().setText(messages.main_helaas());
				alertwidget.getContentlabel().setText(messages.verificatienietgelukt());
				alertwidget.getBox().center();
			}

		}

		if (result instanceof EventAddParentsMailaddress)
		{
			EventAddParentsMailaddress eapma = (EventAddParentsMailaddress) result;
			if (eapma.getReply() != null)
			{
				String reply = eapma.getReply();
				switch (reply)
				{
				case "mail added":
				{
					alertwidget.getBox().setText("Mail toegevoegd");
					alertwidget.getContentlabel().setText("Uw mailadres is toegevoegd. Elke zondagavond krijgt u een berichtje.");
					alertwidget.getBox().center();
					break;
				}
				case "mail removed":
				{
					alertwidget.getBox().setText("Mail verwijderd");
					alertwidget.getContentlabel().setText("Uw mailadres is verwijderd. U krijgt geen mail meer van ons.");
					alertwidget.getBox().center();					
					break;
				}
				case "failure":
				{
					alertwidget.getBox().setText("Een fout...");
					alertwidget.getContentlabel().setText("De wijziging kon niet worden doorgevoerd. Probeer het nu of later opnieuw.");
					alertwidget.getBox().center();					
					break;
				}
				}
			}
		}

		if (result instanceof EventProductsRetrieved)
		{
			EventProductsRetrieved epr = (EventProductsRetrieved) result;
			if (epr.getProducts() != null)
			{
				wallet.setProductlist(epr.getProducts());
			}
		}

		// na een inlogpoging van een superuser
		if (result instanceof EventSuperUserRetrieved)
		{
			EventSuperUserRetrieved event = (EventSuperUserRetrieved) result;
			if (event.getSuperuser() != null)
			{
				this.superuser = event.getSuperuser();
				if (superuser.getVerified())
				{
					this.administratiepage = new AdministratiePage(this, this.superuser, messages);
					showPageAdministratie();
				} else
				{

					Utilities.alertWidgetButtonVerifMail("", messages.main_mailadresnognietgeverifieerd(), messages,
							superuser, this).center();
				}
			} else
			{
				this.loginsuperuser.txtlogin.setStyleName("admininvulbox_fouteinvoer");
				this.loginsuperuser.txtpw.setStyleName("admininvulbox_fouteinvoer");
				this.loginsuperuser.btnsubmit.setEnabled(true);
			}
		}

		// na het aanmaken van een superuser
		if (result instanceof EventAddSuperUser)
		{
			EventAddSuperUser event = (EventAddSuperUser) result;
			if (event.getSuperuser() != null)
			{
				if (event.getSuperuser().getEmail().equals("Emailadres bestaat al"))
				{
					Utilities.alertWidget("", "Dit E-mailadres bestaat al, probeer een ander e-mailadres", messages)
							.center();
				} else
				{
					Utilities.alertWidget("", "Klik op de link in uw verificatiemail om uw account te activeren.",
							messages).center();
				}

			} else
			{
				newsuperuser.lbl20.setText("probeer een ander emailadres...");
				Utilities.alertWidget("", "Emailadres staat al in de lijst", messages).center();
			}
		}

		// na loginpoging van een user
		if (result instanceof EventUserRetrieved)
		{
			EventUserRetrieved event = (EventUserRetrieved) result;
			if (event.getUser() != null)
			{
				this.user = event.getUser();
				if (user.getLoginname().equals("gast"))
				{
					this.user.setId(1);
					this.gast = new Gast();
					this.menu.lbl12.setText(messages.menupage_welkomgebruiker() + " " + this.user.getName());
					this.menu.hpanel2.setVisible(true);
					this.wallet.productlist.clear();
					this.wallet.showWallet(getUser().getMoney());
					this.gui.hpanel3.clear();
					this.fiba = new FeedbackContainer(0, 0.0, new Date(), "0", 0, new ArrayList<TafelResult>(),
							"Je hebt alleen vandaag geoefend");
					this.fbpage.setFeedbackContainer(fiba);
				}
				this.menu.lbl12.setText(messages.menupage_welkomgebruiker() + " " + this.user.getName());
				this.menu.hpanel2.setVisible(true);
				this.wallet.productlist.clear();
				this.wallet.showWallet(getUser().getMoney());
				this.client.getUserFeedback(this.user.getId());
				this.client.getProducts(this.user.getId());
				// this.gui.hpanel3.clear();
				if (this.user.getEmailsuperuser().equals("jufanne@tweemasterlisse.nl") && user.isSurveydone() == false)
				{
					this.contentpanel.clear();
					this.contentpanel.add(new Vragenlijst(this));
				}
			} else
			{
				alertwidget.getBox().setText("");
				alertwidget.getContentlabel().setText(messages.startpage_failedloginmessage());
				alertwidget.getBox().center();
				this.startpage.btn10.setEnabled(true);
				this.menu.lbl12.setText(messages.menupage_diekennenweniet());
				showPageStartPage();
			}
		}
		// na het invoeren van een nieuwe user
		if (result instanceof EventUserNew)
		{
			EventUserNew eun = (EventUserNew) result;
			User usernew = eun.getUser();
			if (usernew == null)
			{
				System.out.println("fout in main.eventusernew");
			} else
			{
				administratiepage.addGroupmember(usernew);
			}
		}

		// zet de groepsresultaten op de eerste administratiepage.
		if (result instanceof EventGetGroupResults)
		{
			EventGetGroupResults eggr = (EventGetGroupResults) result;
			ArrayList<UserResults> userresults = eggr.getUserresults();
			if (userresults.size() > 0)
				administratiepage.makeUserResultsList(userresults);
		}

		// usergroepgegevens worden op de eerste administratiepage gezet zodat
		// ze klaar staan om te editen
		if (result instanceof EventGetGroup)
		{
			EventGetGroup egg = (EventGetGroup) result;
			ArrayList<User> users = egg.getUsers();
			administratiepage.mygroup = users;
		}

		if (result instanceof EventUserFeedback)
		{
			EventUserFeedback euf = (EventUserFeedback) result;
			FeedbackContainer f = euf.getFiba();
			this.fiba = f;
			this.fbpage.setFeedbackContainer(f);
		}

		if (result instanceof EventSuperuserFeedback)
		{
			EventSuperuserFeedback esuf = (EventSuperuserFeedback) result;
			FeedbackContainer f = esuf.getFiba();
			this.administratiepage.setFiba(f);
			this.administratiepage.updateVpanel41(f);
		}

	}

	// maakt de rpc-call bij een loginpoging van een user
	public void submitOnEntry(String loginname, String passw)
	{

		if (Validate.checkTextbox(loginname) && Validate.checkTextbox(passw))
		{
			client.retrieveUser(loginname, passw);
		}

		else
		{
			Utilities.alertWidget("", messages.startpage_failedloginmessage(), messages).center();
			startpage.btn10.setEnabled(true);
			startpage.txtlogin.setText("");
			startpage.txtpassw.setText("");
			showPageStartPage();
		}
	}

	// Stopt de vorige resultaten van de user in een object
	public void setFeedbackContainer(FeedbackContainer fiba)
	{
		this.fiba = fiba;
		this.fbpage.setFeedbackContainer(fiba);
		try
		{
			this.administratiepage.setFiba(fiba);
			this.administratiepage.updateVpanel41(fiba);
		} catch (NullPointerException n)
		{

		}
	}

	// Toont de pagina voor ouders
	public void showPageParentMenu()
	{
		History.newItem(Parentmenupagina_Token);
		this.contentpanel.clear();
		this.contentpanel.add(parentmenu);
	}

	// Toont de tafeltrainer
	public void showPageTafelTrainer()
	{
		History.newItem(Tafeltrainerpagina_Token);
		this.contentpanel.clear();
		this.contentpanel.add(gui);
	}

	// Toont pagina met resultaten
	public void showPageResultaten()
	{

		History.newItem(Feedbackpagina_Token);
		if (this.user.getId() == 1)
			this.fbpage.setFeedbackContainer(gast.makeFeedbackContainer());
		this.contentpanel.clear();
		this.fbpage.btn00.setEnabled(true);
		this.contentpanel.add(this.fbpage);
	}

	// Toont en berekent de inhoud van de portemonnee en zet de plaatjes in het
	// het scherm
	public void showPagePortemonnee()
	{
		History.newItem(Walletpagina_Token);
		this.contentpanel.clear();
		this.contentpanel.add(this.wallet);
		this.wallet.showWallet(getUser().getMoney());
		this.wallet.updateFlowpanelDroptarget();
	}

	// Toont het winkeltje
	public void showPageWinkeltje()
	{
		History.newItem(Shoppagina_Token);
		this.contentpanel.clear();
		shop.setMoney(getUser().getMoney());
		shop.hpanel10.clear();
		shop.hpanel10.add(shop.img);
		shop.incart.clear();
		shop.incarthotlist.clear();
		this.contentpanel.add(this.shop);
	}

	// Toont de pagina met groepsresultaten
	public void showPageAdministratie()
	{
		History.newItem(Administratiepagina_Token);
		this.contentpanel.clear();
		this.contentpanel.add(this.administratiepage);
	}

	// Toon het formulier om een nieuwe superuser aan te maken
	public void showPageNieuweBegeleider()
	{
		History.newItem(Nieuwebegeleiderpagina_Token);
		this.contentpanel.clear();
		this.contentpanel.add(new NewSuperUser(this, messages, alertwidget));
	}

	// Toont het loginscherm voor de superuser
	public void showPageLoginBegeleider()
	{
		History.newItem(Loginbegeleiderpagina_Token);
		this.loginsuperuser.btnsubmit.setEnabled(true);
		this.contentpanel.clear();
		this.contentpanel.add(this.loginsuperuser);
	}

	// Toonthet beginscherm
	public void showPageStartPage()
	{
		History.newItem(Startpagina_Token);
		this.startpage.btn10.setEnabled(true);
		// this.startpage.txtpassw.setText("");
		// this.startpage.txtlogin.setText("");
		this.menu.lbl12.setText("");
		this.menu.showEntranceMenu();
		this.client.stopQuiz();
		this.fbpage.flex10.clear();
		this.fbpage.flex11.clear();
		try
		{
			this.gui.hpanel3.clear();
			this.gui.hpanel40.clear();
			this.gui.lbl3.setText("");
			this.gui.gaugepanel.clear();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		this.wallet.flowpaneldroptarget.clear();
		this.wallet.bedragveranderd = true;
		this.contentpanel.clear();
		this.contentpanel.add(this.startpage);
	}

	public void showDemopage()
	{
		History.newItem(Demokidspagina_Token);
		this.contentpanel.clear();
		this.contentpanel.add(new DemoPage());
	}

	public void showLandingpage()
	{
		this.contentpanel.clear();
		landingpage = new LandingPage(this, messages, client);
		this.contentpanel.add(new LandingPage(this, messages, client));
	}

	public void showDemoSuperuserpage()
	{
		History.newItem(Demobegeleiderpagina_Token);
		this.contentpanel.clear();
		this.contentpanel.add(new DemoSuperuserPage());
	}

	public User getUser()
	{
		return this.user;
	}

	public Tafeltrainer3Gui getGui()
	{
		return this.gui;
	}

	public DialogBox getAlertWidget()
	{
		DialogBox box = new DialogBox();
		Label contentlabel = new Label();
		box.setPixelSize(300, 400);
		box.setStyleName("popup2");
		VerticalPanel panel = new VerticalPanel();
		panel.setBorderWidth(0);
		// TODO
		// box.setText(header);
		Image img1 = new Image("/images/startpage_curious.png");
		img1.setHeight("200px");
		panel.add(img1);
		panel.add(contentlabel);
		Button buttonClose = new Button(messages.utilities_sluitbutton());
		buttonClose.addClickHandler(new buttonCloseClickHandler(box));
		buttonClose.setStyleName("standardbutton");
		Label emptyLabel = new Label("");
		emptyLabel.setSize("auto", "25px");
		panel.add(emptyLabel);
		panel.add(emptyLabel);
		buttonClose.setWidth("90px");
		panel.add(buttonClose);
		panel.setCellHorizontalAlignment(buttonClose, HasAlignment.ALIGN_CENTER);
		box.add(panel);
		return box;

	}

	public class buttonCloseClickHandler implements ClickHandler
	{
		DialogBox box;

		public buttonCloseClickHandler(DialogBox box)
		{
			this.box = box;
		}

		@Override
		public void onClick(ClickEvent event)
		{
			box.hide();
		}
	}

}
