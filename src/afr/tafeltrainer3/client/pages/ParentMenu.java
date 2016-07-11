package afr.tafeltrainer3.client.pages;

import afr.tafeltrainer3.main.ClientImp;
import afr.tafeltrainer3.main.MainView;
import afr.tafeltrainer3.utils.Validate;
import afr.tafeltrainer3.widgets.AlertWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ParentMenu extends Composite 
{

	private static String MESSAGEPARENTS = " Met de oudermail krijgt u gelegenheid om een oogje te houden op de verrichtingen van uw kind. "
			+ " Uit ervaring blijkt dat kinderen weliswaar vaak bezig zijn op de computer maar dat het voor ouders en begeleiders lastig is om "
			+ "hierop het zicht te houden. Wanneer u zich inschrijft krijgt u wekelijks een berichtje waarin de resultaten en de oefenemomenten van uw kind staan "
			+ "vermeld. Het is een goede aanleiding om met uw kind in gesprek te raken en uw betrokkenheid te tonen. Wanneer"
			+ " u zich af wilt melden vul dan het emailadres niet in.";
	private ClientImp client;
	private MainView main;
	private FlexTable flex1;
	private HorizontalPanel hpanel00;
	private Label lbl00;
	private HTML html00;
	private TextArea txtar10;
	private HorizontalPanel hpanel10;
	private HorizontalPanel hpanel20;
	private HorizontalPanel hpanel30;
	public TextBox txtusername;
	public TextBox txtpw;
	public TextBox txtemailaddress;
	public Button btnsubmit;
	private VerticalPanel vpanel20;
	private AlertWidget alertwidget;

	
	public ParentMenu(MainView main)
	{
		this.main = main;
		this.alertwidget = new AlertWidget("");
		flex1 = new FlexTable();
		initWidget(this.flex1);
		this.flex1.setStyleName("tafeltabel");
		flex1.setCellSpacing(0);
		flex1.setCellPadding(0);
		flex1.setBorderWidth(0);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "125px");
		this.flex1.getFlexCellFormatter().setHeight(1, 0, "125px");
		this.flex1.getFlexCellFormatter().setHeight(2, 0, "75px");
		this.flex1.getFlexCellFormatter().setHeight(3, 0, "175px");
		this.flex1.getFlexCellFormatter().setWidth(0, 0, "960px");
		
		//eerste rij
		hpanel00 = new HorizontalPanel();
		flex1.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		html00 = new HTML("<h1>Oudermail</h1>");
		html00.setStyleName("topkader");
		hpanel00.add(html00);
		hpanel00.setSpacing(30);;
		flex1.setWidget(0, 0, hpanel00);
		
		
		//tweede rij
		txtar10 = new TextArea();
		txtar10.setReadOnly(true);
		txtar10.setWidth("850px");
		txtar10.setHeight("120px");
		txtar10.setText(MESSAGEPARENTS);
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		this.flex1.setWidget(1, 0, txtar10);
		
		//derde rij
		hpanel20 = new HorizontalPanel();
		flex1.getFlexCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_CENTER);
		hpanel20.setSpacing(4);
		VerticalPanel vpanel20a = new VerticalPanel();
		vpanel20a.setSpacing(5);
		VerticalPanel vpanel20b = new VerticalPanel();
		vpanel20b.setSpacing(5);
		VerticalPanel vpanel20c = new VerticalPanel();
		vpanel20c.setSpacing(5);
		Label lbl1 = new Label("gebruikersnaam kind");
		lbl1.setStyleName("middellabels");
		Label lbl2 = new Label("wachtwoord");
		lbl2.setStyleName("middellabels");
		Label lbl3 = new Label("uw mailadres");
		lbl3.setStyleName("middellabels");
		txtusername = new TextBox();
		txtusername.setStyleName("admininvulbox");
		txtpw = new TextBox();
		txtpw.setStyleName("admininvulbox");
		txtemailaddress = new TextBox();
		txtemailaddress.setStyleName("admininvulbox");
		
		vpanel20a.add(lbl1);
		vpanel20a.add(txtusername);
		
		vpanel20b.add(lbl2);
		vpanel20b.add(txtpw);
		
		vpanel20c.add(lbl3);
		vpanel20c.add(txtemailaddress);
		
		hpanel20.add(vpanel20a);
		hpanel20.add(vpanel20b);
		hpanel20.add(vpanel20c);
		this.flex1.setWidget(2, 0, hpanel20);
		
		//vierde rij
		vpanel20 = new VerticalPanel();
		vpanel20.setSpacing(5);
		vpanel20.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		hpanel30 = new HorizontalPanel();
		hpanel30.setSpacing(10);
		flex1.getFlexCellFormatter().setHorizontalAlignment(3, 0, HasHorizontalAlignment.ALIGN_CENTER);
		btnsubmit = new Button("Verzenden");
		btnsubmit.setStyleName("standardbutton");
		btnsubmit.addClickHandler(new btnsubmitClickHandler());
		Anchor hyper20a = new Anchor("mail ons","mailto:apreinders74@gmail.com");
		hpanel30.add(hyper20a);
		vpanel20.add(btnsubmit);
		vpanel20.add(hpanel30);
		this.flex1.setWidget(3, 0, vpanel20);
	}
	
	private class btnsubmitClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) 
		{
			boolean subscribed;
			String uname = txtusername.getText();
			String pw = txtpw.getText();
			String email = txtemailaddress.getText();
			if(Validate.checkEmail(email))
			{
				subscribed = true;
			}
			else
			{
				subscribed = false;
			}
			if(Validate.checkTextbox(uname)&&Validate.checkTextbox(pw))
			{
				main.client.addParentsMailaddress(uname, pw, email, subscribed);
			}
			else
			{
				alertwidget.getBox().setText("foute invoer");
				alertwidget.getContentlabel().setText("controleer of alle velden correct zijn ingevuld!");
				alertwidget.getBox().center();
			}
			
		}
	}
	
}
