package afr.tafeltrainer3.client;

import java.util.ArrayList;

import afr.tafeltrainer3.shared.FeedbackContainer;
import afr.tafeltrainer3.shared.Opgave;
import afr.tafeltrainer3.shared.OpgaveSoort;
import afr.tafeltrainer3.shared.ProductWidget;
import afr.tafeltrainer3.shared.TafelResult;
import afr.tafeltrainer3.shared.User;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ButtonBase;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Tafeltrainer3Gui extends Composite {
	
	FlexTable flex1 = new FlexTable();
	private ClientImp client;
	private MainView main;
	
	public int answer;
	public int beloning;
	public int flag;
	public int secondflag;
	private int corrects=0;
	private int errors=0;
	private BeloningsHoogte beloningshoogte;
	public Button btn1 = new Button();
	public Button btn2 = new Button();
	public Button btn3 = new Button();
	private Button timerimg;
	private CheckBox chk2;
	private CheckBox chk3;
	private CheckBox chk4;
	private CheckBox chk5;
	private CheckBox chk6;
	private CheckBox chk7;
	private CheckBox chk8;
	private CheckBox chk9;
	private CheckBox chka;
	private CheckBox chkm;
	public CheckBox[] boxes;
	public Label lbl1 = new Label();
	public Label lbl2 = new Label();
	public Label lbl3 = new Label();
	public Label lbl4 = new Label();
	public HorizontalPanel hpanel_main = new HorizontalPanel();
	public HorizontalPanel hpanel2;
	public HorizontalPanel hpanel3;
	public HorizontalPanel hpanelrow0a;
	public HorizontalPanel hpanelrow0b;
	public HorizontalPanel hpanel40;
	public FlowPanel fpanel1 = new FlowPanel();
	public Opgave opg;
	public OpgaveSoort opgavesoort;
	public ScrollPanel spanel1;
	public ScrollPanel spanel2;
	private TafelTimer tim;
	private tafeltrainer3messages messages;
	public final TextBox txt1 = new TextBox();
	public VerticalPanel vpanel1;
	private NumPadWidget numpad;
	public HorizontalPanel gaugepanel;
	
	public Tafeltrainer3Gui(ClientImp client, MainView main,tafeltrainer3messages messages) 
	{
		this.client = client;
		this.main = main;
		this.messages = messages;
		initWidget(flex1);
		// formatteren van de flextable
		this.flex1.setStyleName("tafeltabel");
		flex1.setCellSpacing(0);
		flex1.setCellPadding(0);
		flex1.setBorderWidth(0);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "70px");
		this.flex1.getFlexCellFormatter().setHeight(1, 0, "70px");
		this.flex1.getFlexCellFormatter().setHeight(2, 0, "75px");
		this.flex1.getFlexCellFormatter().setHeight(3, 0, "75px");
		this.flex1.getFlexCellFormatter().setHeight(4, 0, "210px");
		this.flex1.getFlexCellFormatter().setWidth(0, 0, "320px");
		this.flex1.getFlexCellFormatter().setWidth(0, 1, "320px");
		this.flex1.getFlexCellFormatter().setWidth(0, 2, "320px");
		this.flex1.getFlexCellFormatter().setWidth(1, 1, "320px");

		this.flex1.getFlexCellFormatter().setRowSpan(0, 2, 3);
		this.flex1.getFlexCellFormatter().setVerticalAlignment(0, 2,HasVerticalAlignment.ALIGN_BOTTOM);
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);

		//eerste rij
		vpanel1 = new VerticalPanel();
		hpanelrow0a = new HorizontalPanel();
		hpanelrow0b = new HorizontalPanel();
		chk2 = new CheckBox("2");
		chk2.addStyleName("checkbox_ap");
		chk2.addClickHandler(new checkBoxHandler());
		chk3 = new CheckBox("3");
		chk3.addClickHandler(new checkBoxHandler());
		chk4 = new CheckBox("4");
		chk4.addClickHandler(new checkBoxHandler());
		chk5 = new CheckBox("5");
		chk5.addClickHandler(new checkBoxHandler());
		chk6 = new CheckBox("6");
		chk6.addClickHandler(new checkBoxHandler());
		chk7 = new CheckBox("7");
		chk7.addClickHandler(new checkBoxHandler());
		chk8 = new CheckBox("8");
		chk8.addClickHandler(new checkBoxHandler());
		chk9 = new CheckBox("9");
		chk9.addClickHandler(new checkBoxHandler());
		chka = new CheckBox(messages.tafeltrainerpage_alletafels());
		chka.setValue(true);
		chka.addClickHandler(new checkBoxHandler());
		chkm = new CheckBox(messages.tafeltrainerpage_moeilijk());
		chkm.addClickHandler(new checkBoxHandler());
		hpanelrow0a.add(chk2);
		hpanelrow0a.add(chk3);
		hpanelrow0a.add(chk4);
		hpanelrow0a.add(chk5);
		hpanelrow0a.add(chk6);
		hpanelrow0a.add(chk7);
		hpanelrow0a.add(chk8);
		hpanelrow0a.add(chk9);
		
		lbl1 = new Label(messages.tafeltrainerpage_welketafels());
		lbl1.setStyleName("middellabels");
		vpanel1.add(lbl1);
		vpanel1.add(hpanelrow0a);
		hpanelrow0b.add(chka);
		hpanelrow0b.add(chkm);
		vpanel1.add(hpanelrow0b);
		flex1.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flex1.setWidget(0, 0, vpanel1);
		
		lbl3.setText("    ");
		this.flex1.setWidget(0, 1, lbl3);
		
		// tweede rij
		hpanel2 = new HorizontalPanel();
		hpanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		btn1.setText(messages.tafeltrainerpage_startknop());
		btn1.addClickHandler(new Button1ClickHandler());
		btn1.setStyleName("standardbutton");
		btn1.setEnabled(true);
		btn2.setText(messages.tafeltrainerpage_stopknop());
		btn2.addClickHandler(new Button2ClickHandler());
		btn2.setStyleName("standardbutton");
		btn2.setEnabled(false);
		btn3.setText(messages.tafeltrainerpage_infoknop());
		btn3.addClickHandler(new Button3ClickHandler());
		btn3.setStyleName("standardbutton");
		this.hpanel2.add(btn1);
		this.hpanel2.add(btn2);
		this.hpanel2.add(btn3);
		this.flex1.setWidget(1, 0, hpanel2);
		
		this.fpanel1.setStyleName("timerbar");
		Image timerbardefault = new Image("/images/defaulttimerpicture.jpg");
		fpanel1.add(timerbardefault);
		this.flex1.setWidget(1, 1, fpanel1);
		

		// derde rij
		txt1.addKeyUpHandler(new txt1KeyUpHandler());
		txt1.setStyleName("invulbox");
		this.lbl2.setStyleName("opgavenlabel");
		this.flex1.setWidget(2, 0, lbl2);
		this.flex1.setWidget(2, 1, txt1);

		// vierde rij voor de feedbackblokjes
		this.spanel1 = new ScrollPanel();
		this.spanel1.setStyleName("scrollpanelpage1");
		this.hpanel3 = new HorizontalPanel();
		this.spanel1.add(hpanel3);
		this.spanel1.setWidth("960px");
		this.flex1.getFlexCellFormatter().setColSpan(3, 0, 3);
		this.flex1.setWidget(3, 0, spanel1);

		// onderste rij voor de prijzenkast
		this.gaugepanel = new HorizontalPanel();
		this.flex1.setWidget(4, 0, gaugepanel);
		this.spanel2 = new ScrollPanel();
		this.spanel2.setWidth("640px");
		this.hpanel40 = new HorizontalPanel();
		this.hpanel40.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		this.hpanel40.setSpacing(5);
		this.spanel2.add(hpanel40);
		this.flex1.getFlexCellFormatter().setColSpan(4, 1, 2);
		this.flex1.setWidget(4, 1, spanel2);

		numpad = new NumPadWidget(Tafeltrainer3Gui.this);
		this.flex1.getFlexCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_TOP);
		this.flex1.setWidget(0, 2, numpad);
	}
	
	private class  checkBoxHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			//hier bepalen wat legale checkbox combinaties zijn.
			CheckBox check  = (CheckBox) event.getSource();
			if(!(check.getText().equals("moeilijk"))
					&&!(check.getText().equals("alle tafels"))&&(chk2.getValue()==true||chk3.getValue()==true
					||chk4.getValue()==true||chk5.getValue()==true||chk6.getValue()==true
					||chk7.getValue()==true||chk8.getValue()==true||chk9.getValue()==true))
			{chka.setValue(false);chkm.setValue(false);}
			
			if(chka.getValue()==true&&(!(check.getText().equals("moeilijk"))))
			{chk2.setValue(false);chk3.setValue(false);chk4.setValue(false);
			chk5.setValue(false);chk6.setValue(false);chk7.setValue(false);chk8.setValue(false);
			chk9.setValue(false);chkm.setValue(false);}
			
			if(chkm.getValue()==true){chk2.setValue(false);chk3.setValue(false);chk4.setValue(false);
			chk5.setValue(false);chk6.setValue(false);chk7.setValue(false);chk8.setValue(false);
			chk9.setValue(false);chka.setValue(false);}

			boxes = new CheckBox[] {chk2,chk3,chk4,chk5,chk6,chk7,chk8,chk9,chka,chkm};
			opgavesoort = new OpgaveSoort(boxes);
		}
		
	}
	
	
/**
 * Timerbar middenboven fpanel1 die moet worden gevuld; vulling bestaat uit buttons
 * die van kleur verschuiven van groen naar rood en worden toegevoegd aan een flowpanel 	
 */
	public void updatefpanel1(int colour)
	
	{
		timerimg = new Button();
		timerimg.setStyleName("timerimg"+colour);
		this.fpanel1.add(timerimg);
	}
	
	public int getCorrects()
	{
		return this.corrects;
	}
	
	public int getErrors()
	{
		return this.errors;
	}
	
/**
 *Startbutton zet een opgave in het opdrachtveld, geeft de server een seintje voor sql verbinding
 *,zet de timer aan, maakt het feedbackveld- en label schoon 
 */
	private class Button1ClickHandler implements ClickHandler {


		@Override
		public void onClick(ClickEvent event) 
		{
			try
			{
			client.startQuiz();
			numpad.number="";
			numpad.btn12.setEnabled(true);
			txt1.setText("");
			corrects = 0;
			errors = 0;
			beloningshoogte = new BeloningsHoogte();
			boxes = new CheckBox[] {chk2,chk3,chk4,chk5,chk6,chk7,chk8,chk9,chka,chkm};
			tim = new TafelTimer( getGui());
			opgavesoort = new OpgaveSoort(boxes);
			opg = new Opgave(opgavesoort);
			String opgave = String.valueOf(opg.getFactor1()) + " X "
					+ String.valueOf(opg.getFactor2()) + " = ";
			lbl2.setText(opgave);
			lbl3.setText("");
			txt1.setFocus(true);
			btn1.setEnabled(false);
			btn2.setEnabled(true);
			//btn3.setEnabled(true);
			fpanel1.clear();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				System.out.println("fout in startbutton...");
			}
		}
	}
/**
 *Stopbutton: geeft een signaal aan de server, die de resultaten wegschrijft naar de database.
 *berekent het gemiddelde; Maakt het opdrachtveld leeg, reset de score,
 *maakt de startknop weer toegankelijk, haalt de timer weg;	
 */
	private class Button2ClickHandler implements ClickHandler {

		@Override
		public void onClick(ClickEvent event) {
			try
			{
			client.stopQuiz();
			double percentage;
			if(corrects>0)
			{percentage =(((double)getCorrects())/((double)getCorrects()+(double)getErrors()))*100;}
			else{percentage = 0;}
			lbl3.setStyleName("labelgoedgekeurd");
			lbl3.setText("score :" + Utilities.round(percentage,1) + "%");
			lbl2.setText("");
			errors = 0;
			corrects = 0;
			tim.cancel();
			fpanel1.clear();
			Image timerbardefault = new Image("/images/defaulttimerpicture.jpg");
			fpanel1.add(timerbardefault);
			numpad.btn12.setEnabled(false);
			txt1.setText("");
			btn1.setEnabled(true);
			//btn3.setEnabled(true);
			btn2.setEnabled(false);
			}
			catch(Exception e)
			{
				System.out.println("foutje in stopbutton...");
			}

		}
	}
/**
 * Luistert naar input van het keyboard op de textbox; er komt steeds een karakter
 * binnen dat wordt gecheckt op validiteit. wanneer het antwoord genoeg cijfers heeft
 * wordt het gecontroleerd, naar de server gestuurd en wordt de nieuwe opgave 
 * klaargezet;
 */
	private class Button3ClickHandler implements ClickHandler
	{

	@Override
	public void onClick(ClickEvent event) 
	{
		main.showDemopage();
	}
		
	}
	
	public void onNumPadUp()
	{
		int answer = opg.getAntwoord();
		int numofdigits = (int) Math.log10(answer) + 1;

		if (numofdigits == 1) {
			computeTime();
			tim.cancel();
			int antwoord = Integer.valueOf(txt1.getText());
			txt1.setFocus(true);
			opg.setUseranswer(antwoord);
			if(opg.antwoord==antwoord)
				opg.setIscorrect(true);
			else opg.setIscorrect(false);
			updateLabel3(opg);
			client.submitQuestion(opg,main.getUser().getId());
			opg = new Opgave(opgavesoort);
			numpad.number = "";
			updateLabel2(opg);
		}

		if (numofdigits == 2)
		{
				computeTime();
				tim.cancel();
				int antwoord = Integer.valueOf(txt1.getText());
				txt1.setFocus(true);
				opg.setUseranswer(antwoord);
				if(opg.antwoord==antwoord)
					opg.setIscorrect(true);
				else opg.setIscorrect(false);
				updateLabel3(opg);
				client.submitQuestion(opg,main.getUser().getId());
				opg = new Opgave(opgavesoort);
				updateLabel2(opg);
				numpad.number = "";
		}
		if (numofdigits == 3) 
		{
				computeTime();
				tim.cancel();
				int antwoord = Integer.valueOf(txt1.getText());
				opg.setUseranswer(antwoord);
				txt1.setFocus(true);
				if(opg.antwoord==antwoord)
					opg.setIscorrect(true);
				else opg.setIscorrect(false);
				updateLabel3(opg);
				client.submitQuestion(opg,main.getUser().getId());
				opg = new Opgave(opgavesoort);
				updateLabel2(opg); 
				numpad.number = "";
			}
	}
	
	
	
	private class txt1KeyUpHandler implements KeyUpHandler {

		@Override
		public void onKeyUp(KeyUpEvent event) {
			
			try{
			int answer = opg.getAntwoord();
			int numofdigits = (int) Math.log10(answer) + 1;

			if (numofdigits == 1) {
				computeTime();
				tim.cancel();
				int antwoord = Integer.valueOf(txt1.getText());
				txt1.setText("");
				txt1.setFocus(true);
				opg.setUseranswer(antwoord);
				if(opg.antwoord==antwoord)
					opg.setIscorrect(true);
				else opg.setIscorrect(false);
				updateLabel3(opg);
				client.submitQuestion(opg,main.getUser().getId())	;
				opg = new Opgave(opgavesoort);
				flag = 0;
				updateLabel2(opg);

			}

			if (numofdigits == 2)

				switch (flag) {
				case 1:
					computeTime();
					tim.cancel();
					int antwoord = Integer.valueOf(txt1.getText());
					txt1.setText("");
					txt1.setFocus(true);
					opg.setUseranswer(antwoord);
					if(opg.antwoord==antwoord)
						opg.setIscorrect(true);
					else opg.setIscorrect(false);
					updateLabel3(opg);
					client.submitQuestion(opg,main.getUser().getId());
					opg = new Opgave(opgavesoort);
					updateLabel2(opg);
					break;
				case 0:
					flag = 1;
					break;
				}
			if (numofdigits == 3) 
			
				switch(secondflag)
				{
				case 0 : 	secondflag = 1;break;
				case 1 :		secondflag = 2; break;
				case 2 :		computeTime();
							tim.cancel();
							int antwoord = Integer.valueOf(txt1.getText());
							txt1.setText("");
							txt1.setFocus(true);
							opg.setUseranswer(antwoord);
							if(opg.antwoord==antwoord)
								opg.setIscorrect(true);
							else opg.setIscorrect(false);
							updateLabel3(opg);
							secondflag = 0;
							client.submitQuestion(opg,main.getUser().getId());
							opg = new Opgave(opgavesoort);
							updateLabel2(opg); break;
				}
			}
			catch(NumberFormatException n)
			{
				txt1.setText("");
				txt1.setFocus(true);
			}
		}
	}

/**
 * Zet de nieuwe opgave in het label naast het tekstvak. Nieuwe timer wordt gestart.	
 * 
 */
	
	public void updateLabel2(Opgave opgave) {
		String opg = String.valueOf(opgave.getFactor1()) + " X "
				+ String.valueOf(opgave.getFactor2()) + " = ";
		this.lbl2.setText(opg);
		flag = 0;
		secondflag = 0;
		tim = new TafelTimer(getGui());
		this.txt1.setText("");

	}

	public int getPercentage()
	{
		double percentage;
		if(corrects>0)
		{percentage =(((double)getCorrects())/((double)getCorrects()+(double)getErrors()))*100;}
		else{percentage = 0;}
		return (int) percentage;
	}
	
	
	
/**
 * Houdt het aantal fouten bij, Geeft feedback in het label rechtsboven.laat een muntje in
 * hpanel3 zetten, ruimt het timerblok op.
 * 
 */
	public void updateLabel3(Opgave opg) {
		beloning=0;
		this.lbl3.setText(opg.toString());
		
		if (opg.getAntwoord() == opg.getUseranswer()) 
		{
			lbl3.setStyleName("labelgoedgekeurd");
			this.corrects++;
			beloning = beloningshoogte.getBeloning(true,chkm.getValue());
			main.getUser().addMoney(beloning);
			main.wallet.bedragveranderd = true;
			client.updateUser(main.getUser());
		} else 
		{
			lbl3.setStyleName("labelafgekeurd");
			this.errors++;
			beloning = beloningshoogte.getBeloning(false,chkm.getValue());
			main.getUser().addMoney(beloning);
			main.wallet.bedragveranderd = true;
			client.updateUser(main.getUser());
		}
		
		ArrayList<Image> geldplaatjes = main.wallet.getGeldplaatjes(beloning); 
		for (int i = 0; i<geldplaatjes.size() ; i++)
		{
			this.hpanel3.insert(geldplaatjes.get(i),0);
		}
		this.fpanel1.clear();
		
		this.gaugepanel.clear();
		GaugeWidget gauge = new GaugeWidget(getPercentage(),messages);
		this.gaugepanel.add(gauge);
	}

	
	
	/**
	 * berekent de bedenktijd voor een opgave
	 */
	public void computeTime() {
		double time = ((double)10000 - (double)tim.timeLeft) / (double)1000;
		if (time < 0.0)
			time = 0.0;
		time = Utilities.round(time, 1);
		opg.setTime(time);
		
	}

	public Tafeltrainer3Gui getGui() {

		return this;
	}

	// vult het onderste paneel met de vergaarde bezittingen
	public void updateSpanel2(Object product) 
	{
			hpanel40.add((ProductWidget)product);

	}

	

}
