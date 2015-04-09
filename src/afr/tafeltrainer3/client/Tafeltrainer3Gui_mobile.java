package afr.tafeltrainer3.client;

import java.util.ArrayList;

import afr.tafeltrainer3.shared.Opgave;
import afr.tafeltrainer3.shared.OpgaveSoort;
import afr.tafeltrainer3.shared.ProductWidget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.event.logical.shared.HasValueChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Tafeltrainer3Gui_mobile extends Tafeltrainer3Gui {
	
	FlexTable flex1 = new FlexTable();
	public ClientImp client;
	public MainView main;
	public int answer;
	public int beloning;
	public int flag;
	public int secondflag;
	public int corrects = 0;
	public int errors = 0;
	public BeloningsHoogte beloningshoogte;
	public Button btn1;
	public Button btn2 ;
	public Button btn3;
	public Button timerimg;
	public CheckBox chk2;
	public CheckBox chk3;
	public CheckBox chk4;
	public CheckBox chk5;
	public CheckBox chk6;
	public CheckBox chk7;
	public CheckBox chk8;
	public CheckBox chk9;
	public CheckBox chka;
	public CheckBox chkm;
	public CheckBox[] boxes;
	public Label lbl1;
	public Label lbl2;
	public Label lbl3;
	public Label lbl4;
	public Label lbl_forhpanel11;
	public HorizontalPanel hpanel_main ;
	public HorizontalPanel hpanel2;
	public HorizontalPanel hpanel3;
	public HorizontalPanel hpanelrow0a;
	public HorizontalPanel hpanelrow0b;
	public HorizontalPanel hpanel40;
	public HorizontalPanel hpanel11;
	public FlowPanel fpanel1;
	public Opgave opg;
	public OpgaveSoort opgavesoort;
	public ScrollPanel spanel1;
	public ScrollPanel spanel2;
	public TafelTimer tim;
	public tafeltrainer3messages messages;
	public TextBox txt10;
	public VerticalPanel vpanel1;
	public NumPadWidget numpad;
	public HorizontalPanel gaugepanel;
	
	
	
	public Tafeltrainer3Gui_mobile()
	{
		btn1 = new Button();
		btn2 = new Button();
		btn3 = new Button();
		lbl1 = new Label();
		lbl2 = new Label();
		lbl3 = new Label();
		lbl4 = new Label();
		txt10 = new TextBox();
		fpanel1 = new FlowPanel();
		hpanel_main = new HorizontalPanel();
		hpanel11 = new HorizontalPanel();
		lbl_forhpanel11 = new Label("");

		initWidget(flex1);
		// formatteren van de flextable1
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
		this.flex1.getFlexCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_BOTTOM);
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);

		// eerste rij : checkboxen voor de moeilijkheidsgraad, feedbacklabel, en het virtuele toetsenbordje
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
		//chka = new CheckBox(messages.tafeltrainerpage_alletafels());
		chka = new CheckBox("alle tafels");
		
		chka.setValue(true);
		chka.addClickHandler(new checkBoxHandler());
		//chkm = new CheckBox(messages.tafeltrainerpage_moeilijk());
		chkm = new CheckBox("moeilijk");
		chkm.addClickHandler(new checkBoxHandler());
		hpanelrow0a.add(chk2);
		hpanelrow0a.add(chk3);
		hpanelrow0a.add(chk4);
		hpanelrow0a.add(chk5);
		hpanelrow0a.add(chk6);
		hpanelrow0a.add(chk7);
		hpanelrow0a.add(chk8);
		hpanelrow0a.add(chk9);

		//lbl1 = new Label(messages.tafeltrainerpage_welketafels());
		lbl1 = new Label("Welke Tafels");
		lbl1.setStyleName("middellabels");
		vpanel1.add(lbl1);
		vpanel1.add(hpanelrow0a);
		hpanelrow0b.add(chka);
		hpanelrow0b.add(chkm);
		vpanel1.add(hpanelrow0b);
		flex1.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		flex1.setWidget(0, 0, vpanel1);

		lbl3.setText("    ");
		//Changed
		//this.flex1.setWidget(0, 1, lbl3);

		numpad = new NumPadWidget(this);
		this.flex1.getFlexCellFormatter().setVerticalAlignment(0, 2, HasVerticalAlignment.ALIGN_BOTTOM);
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_CENTER);
		this.flex1.setWidget(0, 2, numpad);

		// tweede rij : de knoppen en de timerblokjes e
		hpanel2 = new HorizontalPanel();
		hpanel2.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		
		btn1.setText("Start");
		btn1.addClickHandler(new Button1ClickHandler());
		btn1.setStyleName("standardbutton");
		btn1.setEnabled(true);
		
		btn2.setText("Stop");
		btn2.addClickHandler(new Button2ClickHandler());
		btn2.setStyleName("standardbutton");
		btn2.setEnabled(true);
		
		btn3.setText("Info");
		btn3.addClickHandler(new Button3ClickHandler());
		btn3.setStyleName("standardbutton");
		this.hpanel2.add(btn1);
		this.hpanel2.add(btn2);
		this.hpanel2.add(btn3);
		this.flex1.setWidget(1, 0, hpanel2);

		this.fpanel1.setStyleName("timerbar");
		Image timerbardefault = new Image("/images/defaulttimerpicture.jpg");
		fpanel1.add(timerbardefault);
		//CHANGED:
		//this.flex1.setWidget(1, 1, fpanel1);
		
		this.hpanel11.add(lbl3);
		this.hpanel11.add(lbl_forhpanel11);
		this.hpanel11.setWidth("320px");
		this.hpanel11.setCellHorizontalAlignment(lbl3, HasHorizontalAlignment.ALIGN_LEFT);
		this.hpanel11.setCellHorizontalAlignment(lbl_forhpanel11, HasHorizontalAlignment.ALIGN_RIGHT);
		
		this.flex1.setWidget(0, 1, fpanel1);
		this.flex1.setWidget(1, 1, hpanel11);
		// derde rij met het opgavenlabel en de invulbox voor het antwoord
		this.lbl2.setStyleName("opgavenlabel");

		txt10 = new TextBox();
		txt10.addKeyUpHandler(new txt10KeyUpHandler());
		txt10.setStyleName("invulbox");
		
		this.flex1.setWidget(2, 0, lbl2);
		this.flex1.setWidget(2, 1, txt10);

		// vierde rij voor de feedbackblokjes
		this.spanel1 = new ScrollPanel();
		this.spanel1.setStyleName("scrollpanelpage1");
		this.hpanel3 = new HorizontalPanel();
		// Label txttest = new Label();
		// txttest.setText("test");
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

		txt10.addFocusHandler(new FocusHandler()
		{
			@Override
			public void onFocus(FocusEvent event)
			{
				txt10.getElement().blur();
			}
		});
					
	}

	private class Button1ClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event)
		{
			try
			{
				client.startQuiz();
				numpad.number = "";
				numpad.enableWidget(true);
				numpad.btn12.setEnabled(true);
				corrects = 0;
				errors = 0;
				beloningshoogte = new BeloningsHoogte(main);
				boxes = new CheckBox[] { chk2, chk3, chk4, chk5, chk6, chk7, chk8, chk9, chka, chkm };
				tim = new TafelTimer(getGui());
				opgavesoort = new OpgaveSoort(boxes);
				opg = new Opgave(opgavesoort);
				String opgave = String.valueOf(opg.getFactor1()) + " X " + String.valueOf(opg.getFactor2()) + " = ";
				lbl2.setText(opgave);
				lbl3.setText("");
				btn1.setEnabled(false);
				btn2.setEnabled(true);
				fpanel1.clear();
			} catch (Exception e)
			{
				e.printStackTrace();
				System.out.println("fout in startbutton...");
			}
		}
	}

	
	
	
	
	public void onNumPadUp()
	{
		int answer = opg.getAntwoord();
		int numofdigits = (int) Math.log10(answer) + 1;

		if (numofdigits == 1)
		{
			computeTime();
			tim.cancel();
			int antwoord = Integer.valueOf(txt10.getText());
			opg.setUseranswer(antwoord);
			if (opg.antwoord == antwoord)
				opg.setIscorrect(true);
			else
				opg.setIscorrect(false);
			updateLabel3(opg);
			client.submitQuestion(opg, main.getUser().getId());
			opg = new Opgave(opgavesoort);
			numpad.number = "";
			updateLabel2(opg);
		}

		if (numofdigits == 2)
		{
			computeTime();
			tim.cancel();
			int antwoord = Integer.valueOf(txt10.getText());
			opg.setUseranswer(antwoord);
			if (opg.antwoord == antwoord)
				opg.setIscorrect(true);
			else
				opg.setIscorrect(false);
			updateLabel3(opg);
			client.submitQuestion(opg, main.getUser().getId());
			opg = new Opgave(opgavesoort);
			updateLabel2(opg);
			numpad.number = "";
		}
		if (numofdigits == 3)
		{
			computeTime();
			tim.cancel();
			int antwoord = Integer.valueOf(txt10.getText());
			opg.setUseranswer(antwoord);
			if (opg.antwoord == antwoord)
				opg.setIscorrect(true);
			else
				opg.setIscorrect(false);
			updateLabel3(opg);
			client.submitQuestion(opg, main.getUser().getId());
			opg = new Opgave(opgavesoort);
			updateLabel2(opg);
			numpad.number = "";
		}
	}

	public void answerSubmitted()
	{
		try
		{
			int answer = opg.getAntwoord();
			int numofdigits = (int) Math.log10(answer) + 1;

			if (numofdigits == 1)
			{
				computeTime();
				tim.cancel();
				int antwoord = Integer.valueOf(txt10.getText());
				opg.setUseranswer(antwoord);
				if (opg.antwoord == antwoord)
					opg.setIscorrect(true);
				else
					opg.setIscorrect(false);
				updateLabel3(opg);
				client.submitQuestion(opg, main.getUser().getId());
				opg = new Opgave(opgavesoort);
				flag = 0;
				updateLabel2(opg);
				
			}

			if (numofdigits == 2)

				switch (flag)
				{
				case 1:
					computeTime();
					tim.cancel();
					int antwoord = Integer.valueOf(txt10.getText());
					opg.setUseranswer(antwoord);
					if (opg.antwoord == antwoord)
						opg.setIscorrect(true);
					else
						opg.setIscorrect(false);
					updateLabel3(opg);
					client.submitQuestion(opg, main.getUser().getId());
					opg = new Opgave(opgavesoort);
					updateLabel2(opg);
					flag = 0 ;
					break;
				case 0:
					flag = 1;
					break;
				}
			if (numofdigits == 3)

				switch (secondflag)
				{
				case 0:
					secondflag = 1;
					break;
				case 1:
					secondflag = 2;
					break;
				case 2:
					computeTime();
					tim.cancel();
					int antwoord = Integer.valueOf(txt10.getText());
					opg.setUseranswer(antwoord);
					if (opg.antwoord == antwoord)
						opg.setIscorrect(true);
					else
						opg.setIscorrect(false);
					updateLabel3(opg);
					secondflag = 0;
					client.submitQuestion(opg, main.getUser().getId());
					opg = new Opgave(opgavesoort);
					updateLabel2(opg);
					break;
				}
		} catch (NumberFormatException n)
		{
			txt10.setText("");
		}

	}

	private class checkBoxHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event)
		{
			// hier bepalen wat legale checkbox combinaties zijn.
			CheckBox check = (CheckBox) event.getSource();
			if (!(check.getText().equals("moeilijk"))
					&& !(check.getText().equals("alle tafels"))
					&& (chk2.getValue() == true || chk3.getValue() == true || chk4.getValue() == true
							|| chk5.getValue() == true || chk6.getValue() == true || chk7.getValue() == true
							|| chk8.getValue() == true || chk9.getValue() == true))
			{
				chka.setValue(false);
				chkm.setValue(false);
			}

			if (chka.getValue() == true && (!(check.getText().equals("moeilijk"))))
			{
				chk2.setValue(false);
				chk3.setValue(false);
				chk4.setValue(false);
				chk5.setValue(false);
				chk6.setValue(false);
				chk7.setValue(false);
				chk8.setValue(false);
				chk9.setValue(false);
				chkm.setValue(false);
			}

			if (chkm.getValue() == true)
			{
				chk2.setValue(false);
				chk3.setValue(false);
				chk4.setValue(false);
				chk5.setValue(false);
				chk6.setValue(false);
				chk7.setValue(false);
				chk8.setValue(false);
				chk9.setValue(false);
				chka.setValue(false);
			}

			boxes = new CheckBox[] { chk2, chk3, chk4, chk5, chk6, chk7, chk8, chk9, chka, chkm };
			opgavesoort = new OpgaveSoort(boxes);
		}

	}

	/**
	 * Timerbar middenboven fpanel1 die moet worde2n gevuld; vulling bestaat uit
	 * buttons die van kleur verschuiven van groen naar rood en worden
	 * toegevoegd aan een flowpanel
	 */
	public void updatefpanel1(int colour)

	{
		timerimg = new Button();
		timerimg.setStyleName("timerimg" + colour);
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
	 * Startbutton zet een opgave in het opdrachtveld, geeft de server een
	 * seintje voor sql verbinding ,zet de timer aan, maakt het feedbackveld- en
	 * label schoon
	 */
	
	
	

	/**
	 * Stopbutton: geeft een signaal aan de server, die de resultaten
	 * wegschrijft naar de database. berekent het gemiddelde; Maakt het
	 * opdrachtveld leeg, reset de score, maakt de startknop weer toegankelijk,
	 * haalt de timer weg;
	 */
	private class Button2ClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event)
		{
			try
			{
				client.stopQuiz();
				double percentage;
				if (corrects > 0)
				{
					percentage = (((double) getCorrects()) / ((double) getCorrects() + (double) getErrors())) * 100;
				} else
				{
					percentage = 0;
				}
				lbl3.setStyleName("labelgoedgekeurd");
				lbl3.setText("score :" + Utilities.round(percentage, 1) + "%");
				lbl2.setText("");
				lbl_forhpanel11.setText("");
				errors = 0;
				corrects = 0;
				tim.cancel();
				fpanel1.clear();
				Image timerbardefault = new Image("/images/defaulttimerpicture.jpg");
				fpanel1.add(timerbardefault);
				numpad.enableWidget(false);
				txt10.setText("");
				btn1.setEnabled(true);
				// btn3.setEnabled(true);
				btn2.setEnabled(false);
			} catch (Exception e)
			{
				System.out.println("foutje in stopbutton...");
			}

		}
	}

	/**
	 * Luistert naar input van het keyboard op de textbox; er komt steeds een
	 * karakter binnen dat wordt gecheckt op validiteit. wanneer het antwoord
	 * genoeg cijfers heeft wordt het gecontroleerd, naar de server gestuurd en
	 * wordt de nieuwe opgave klaargezet;
	 */
	
	private class Button3ClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event)
		{
			main.showDemopage();
		}

	}

	private class txt10KeyUpHandler implements KeyUpHandler
	{

		@Override
		public void onKeyUp(KeyUpEvent event)
		{

			try
			{
				if (event.getNativeKeyCode() == KeyCodes.KEY_BACKSPACE)
				{
					switch (flag)
					{
					case 1:
						flag = 0;
						break;
					case 0:
						break;
					}
					switch (secondflag)
					{
					case 1:
						secondflag = 0;
						break;
					case 2:
						secondflag = 1;
						break;
					}

				}

				else{
				int answer = opg.getAntwoord();
				int numofdigits = (int) Math.log10(answer) + 1;

				if (numofdigits == 1)
				{
					computeTime();
					tim.cancel();
					int antwoord = Integer.valueOf(txt10.getText());
					opg.setUseranswer(antwoord);
					if (opg.antwoord == antwoord)
						opg.setIscorrect(true);
					else
						opg.setIscorrect(false);
					updateLabel3(opg);
					client.submitQuestion(opg, main.getUser().getId());
					opg = new Opgave(opgavesoort);
					flag = 0;
					updateLabel2(opg);

				}

				if (numofdigits == 2)

					switch (flag)
					{
					case 1:
						computeTime();
						tim.cancel();
						int antwoord = Integer.valueOf(txt10.getText());
						opg.setUseranswer(antwoord);
						if (opg.antwoord == antwoord)
							opg.setIscorrect(true);
						else
							opg.setIscorrect(false);
						updateLabel3(opg);
						client.submitQuestion(opg, main.getUser().getId());
						opg = new Opgave(opgavesoort);
						updateLabel2(opg);
						break;
					case 0:
						flag = 1;
						break;
					}
				if (numofdigits == 3)

					switch (secondflag)
					{
					case 0:
						secondflag = 1;
						break;
					case 1:
						secondflag = 2;
						break;
					case 2:
						computeTime();
						tim.cancel();
						int antwoord = Integer.valueOf(txt10.getText());
						opg.setUseranswer(antwoord);
						if (opg.antwoord == antwoord)
							opg.setIscorrect(true);
						else
							opg.setIscorrect(false);
						updateLabel3(opg);
						secondflag = 0;
						client.submitQuestion(opg, main.getUser().getId());
						opg = new Opgave(opgavesoort);
						updateLabel2(opg);
						break;
					}
				}
			} catch (NumberFormatException n)
			{
				txt10.setText("");
			}
		}
	}

	/**
	 * Zet de nieuwe opgave in het label naast het tekstvak. Nieuwe timer wordt
	 * gestart.
	 * 
	 */

	public void updateLabel2(Opgave opgave)
	{
		final String opg = String.valueOf(opgave.getFactor1()) + " X " + String.valueOf(opgave.getFactor2()) + " = ";
		flag = 0;
		secondflag = 0;
		Timer timer = new Timer()
        {
            @Override
            public void run()
            {
            	tim = new TafelTimer(getGui());
            	lbl2.setText(opg);
            	txt10.setText("");
            }
        };
        timer.schedule(500);
		
		
		

	}

	public int getPercentage()
	{
		double percentage;
		if (corrects > 0)
		{
			percentage = (((double) getCorrects()) / ((double) getCorrects() + (double) getErrors())) * 100;
		} else
		{
			percentage = 0;
		}
		return (int) percentage;
	}

	/**
	 * Houdt het aantal fouten bij, Geeft feedback in het label rechtsboven.laat
	 * een muntje in hpanel3 zetten, ruimt het timerblok op.
	 * 
	 */
	public void updateLabel3(Opgave opg)
	{
		beloning = 0;
		this.lbl3.setText(opg.toString());
		this.lbl_forhpanel11.setText(opg.getTime() + " sec");
		
		if (opg.getAntwoord() == opg.getUseranswer())
		{
			lbl3.setStyleName("labelgoedgekeurd");
			lbl_forhpanel11.setStyleName("labelgoedgekeurd");
			this.corrects++;
			beloning = beloningshoogte.getBeloning(true, chkm.getValue());
			main.getUser().addMoney(beloning);
			main.wallet.bedragveranderd = true;
			client.updateUser(main.getUser());
		} else
		{
			lbl3.setStyleName("labelafgekeurd");
			lbl_forhpanel11.setStyleName("labelafgekeurd");
			this.errors++;
			beloning = beloningshoogte.getBeloning(false, chkm.getValue());
			main.getUser().addMoney(beloning);
			main.wallet.bedragveranderd = true;
			client.updateUser(main.getUser());
		}

		ArrayList<Image> geldplaatjes = main.wallet.getGeldplaatjes(beloning);
		for (int i = 0; i < geldplaatjes.size(); i++)
		{
			this.hpanel3.insert(geldplaatjes.get(i), 0);
		}
		this.fpanel1.clear();

		this.gaugepanel.clear();
		GaugeWidget gauge = new GaugeWidget(getPercentage(), messages);
		this.gaugepanel.add(gauge);
	}

	/**
	 * berekent de bedenktijd voor een opgave
	 */
	public void computeTime()
	{
		double time = ((double) 10000 - (double) tim.timeLeft) / (double) 1000;
		if (time < 0.0)
			time = 0.0;
		time = Utilities.round(time, 1);
		opg.setTime(time);

	}

	public Tafeltrainer3Gui getGui()
	{

		return this;
	}

	// vult het onderste paneel met de vergaarde bezittingen
	public void updateSpanel2(Object product)
	{
		hpanel40.add((ProductWidget) product);

	}

	public void addToCache(String number)
	{
		txt10.setText(txt10.getText() + number) ; 
	}

	public void setClient(ClientImp client)
	{
		this.client = client;
	}

	public void setMain(MainView main)
	{
		this.main = main;
	}

	public void setMessages(tafeltrainer3messages messages)
	{
		this.messages = messages;
	}

	public TextBox getTxt10()
	{
		return txt10;
	}

	public int getFlag()
	{
		return flag;
	}

	public void setFlag(int flag)
	{
		this.flag = flag;
	}

	public int getSecondflag()
	{
		return secondflag;
	}

	public void setSecondflag(int secondflag)
	{
		this.secondflag = secondflag;
	}
	
	
}


