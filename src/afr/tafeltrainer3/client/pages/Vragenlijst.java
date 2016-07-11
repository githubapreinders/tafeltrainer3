package afr.tafeltrainer3.client.pages;

import java.util.ArrayList;

import afr.tafeltrainer3.client.shop.Product;
import afr.tafeltrainer3.client.shop.Productcategorie;
import afr.tafeltrainer3.main.MainView;
import afr.tafeltrainer3.main.tafeltrainer3messages;
import afr.tafeltrainer3.shared.SurveyResult;
import afr.tafeltrainer3.widgets.AlertWidget;

import com.google.gwt.dom.client.Style;
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
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Vragenlijst extends Composite
{

	private static String INTROTEKST = "Een tijdje geleden heb je geoefend met de tafeltrainer. Wij willen de tafeltrainer beter maken. Daarom willen we"
			+ " weten wat jij er van vond. Er zijn zeven vragen waarbij je een rondje zwart moet maken. Daarna mag je opschrijven wat jouw mening is. "
			+ "Als je klaar bent druk je op de knop onderaan. <br><br>";

	private static String STELLING1 = "1. De tafeltrainer zag er goed uit";
	private static String STELLING2 = "2. Ik keek vaak op de pagina met resultaten.";
	private static String STELLING3 = "3. Door de tafeltrainer kan ik de tafels nu beter";
	private static String STELLING4 = "4. Je kon te makkelijk geld verdienen";
	private static String STELLING5 = "5. De spulletjes in het winkeltje vond ik aantrekkelijk";
	private static String STELLING6 = "6. Het duurde lang voordat ik begreep hoe alles werkte";
	private static String STELLING7 = "7. De tafeltrainer mist nog een paar leuke geluidjes en muziekjes";
	private static String VRAAG8 = "8. Schrijf hier op welke dingen de tafeltrainer nog beter zouden maken! We vinden"
			+ " het fijn dat je met ons meedenkt!";

	private static String AGREE0 = "helemaal mee oneens";
	private static String AGREE1 = "mee oneens";
	private static String AGREE2 = "beetje mee oneens";
	private static String AGREE3 = "beetje mee eens";
	private static String AGREE4 = "mee eens";
	private static String AGREE5 = "helemaal mee eens";

	private static String WRONG_INPUT = "Je hebt niet overal een zwart rondje gezet! Kijk nog even goed welke vraag je"
			+ " nog moet doen...";

	private static String CORRECT_INPUT = "Dankjewel voor je antwoorden, die gaan we gebruiken voor de volgende tafeltrainer."
			+ "Tot ziens en succes met tafels oefenen!";

	private static String WIDTH = "960px";

	private ArrayList<Product> prodlist;
	public ArrayList<Product> incart;
	public ArrayList<Product> incarthotlist;
	private FlexTable flex0;
	private FlexTable flex1;
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
	private Label[] stellingen = { new Label(STELLING1), new Label(STELLING2), new Label(STELLING3),
			new Label(STELLING4), new Label(STELLING5), new Label(STELLING6), new Label(STELLING7) };
	ArrayList<String> answers;
	ArrayList<RadioButton> rbgroup;
	private TextArea textarea;

	public Vragenlijst(MainView main)
	{
		this.main = main;
		this.flex0 = new FlexTable();
		this.flex1 = new FlexTable();
		this.spanel10 = new ScrollPanel();
		this.spanel10.setStyleName("tafeltabel");
		this.spanel10.setHeight("500px");
		this.spanel10.setWidth("960px");
		this.spanel10.getElement().getStyle().setOverflowX((Style.Overflow.HIDDEN));
		initWidget(this.flex0);
		HTML sierlijn = new HTML("<HR width = '680px' size = '2' color = 'green' >");
		// this.flex1.setStyleName("tafeltabel");
		this.flex1.setCellSpacing(0);
		this.flex1.setCellPadding(2);
		this.flex1.setBorderWidth(0);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "100px");
		this.flex1.getFlexCellFormatter().setWidth(0, 0, "940px");

		this.flex0.getFlexCellFormatter().setWidth(0, 0, "960px");
		this.flex0.setCellPadding(1);

		// eerste rij
		this.hpanel00 = new HorizontalPanel();
		this.hpanel00.add(new HTML("<br>"));
		this.html00 = new HTML("<h1>" + "Vragenlijst" + "</h1>");
		this.html00.setStyleName("topkader");
		this.hpanel00.setSpacing(30);
		this.hpanel00.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		this.hpanel00.add(html00);
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		this.flex1.setWidget(0, 0, hpanel00);

		for (Label l : stellingen)
		{
			l.setStyleName("gwt-Labeldark");
		}

		// tweede rij
		HTML lblintrotext = new HTML("Hoi " + main.getUser().getName() + "," + "<br>" + INTROTEKST);
		lblintrotext.setStyleName("html4");

		flex1.setWidget(1, 0, lblintrotext);

		rbgroup = new ArrayList<RadioButton>();
		answers = new ArrayList<String>();

		for (int j = 0; j < 7; j++)
		{

			VerticalPanel questioncontainer1 = new VerticalPanel();
			questioncontainer1.setWidth(WIDTH);
			questioncontainer1.getElement().setAttribute("cellpadding", "8");
			HorizontalPanel questionpanel1 = new HorizontalPanel();
			Label lbl1 = new Label(AGREE0);
			lbl1.setStyleName("gwt-Labellight");
			questionpanel1.add(lbl1);
			questionpanel1.setCellWidth(lbl1, "120px");

			for (int i = 0; i < 6; i++)
			{
				String stelling = "stelling" + String.valueOf(j + 1);
				RadioButton rb = new RadioButton(stelling, "");
				rb.setName(stelling);
				questionpanel1.add(rb);
				questionpanel1.setCellWidth(rb, "120px");
				questionpanel1.setCellHorizontalAlignment(rb, HasHorizontalAlignment.ALIGN_CENTER);
				rbgroup.add(rb);
			}
			Label lbl2 = new Label(AGREE5);
			lbl2.setStyleName("gwt-Labellight");
			questionpanel1.add(lbl2);
			questionpanel1.setCellHorizontalAlignment(lbl2, HasHorizontalAlignment.ALIGN_LEFT);
			questioncontainer1.add(stellingen[j]);
			questioncontainer1.add(questionpanel1);
			sierlijn = new HTML("<HR width = '780px' size = '2' color = 'blue' >");
			questioncontainer1.add(sierlijn);
			this.flex1.setWidget((j + 2), 0, questioncontainer1);
		}

		textarea = new TextArea();
		textarea.setHeight("120px");
		textarea.setWidth("740px");
		textarea.getElement().setAttribute("maxlenght", "500");
		HTML vraag8 = new HTML("<br><br>" + VRAAG8 + "<br>");
		vraag8.setStyleName("gwt-Labeldark");
		this.flex1.setWidget(9, 0, vraag8);
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(10, 0, HasHorizontalAlignment.ALIGN_CENTER);
		this.flex1.setWidget(10, 0, textarea);
		this.flex1.setWidget(11, 0, new HTML("<br><br><HR width = '680px' size = '2' color = '#ff6633' ><br><br>"));

		Button btnsubmit = new Button("Versturen");
		btnsubmit.setStyleName("standardbutton");
		btnsubmit.addClickHandler(new btnSubmitClickListener());
		this.flex1.getFlexCellFormatter().setAlignment(13, 0, HasHorizontalAlignment.ALIGN_CENTER,
				HasVerticalAlignment.ALIGN_MIDDLE);
		this.flex1.setWidget(13, 0, btnsubmit);
		this.flex1.setWidget(14, 0, new HTML("<br><br>"));

		this.spanel10.setWidget(flex1);
		this.flex0.setWidget(0, 0, spanel10);

	}

	private class btnSubmitClickListener implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event)
		{
			// TODO check if form is filled in completely

			int counter = 1;
			for (RadioButton r : rbgroup)
			{
				if (r.getValue())
				{
					int value = counter % 6;
					if (value == 0)
					{
						value = 6;
					}

					String e = r.getName() + " " + String.valueOf((value));
					if (!(answers.contains(e)))
					{
						answers.add(e);
					}
				}
				counter++;
			}
			if (textarea.getText() != null)
			{
				answers.add(textarea.getText());
			} else
			{
				answers.add("no answer");
			}

			if (answers.size() < 7)
			{
				AlertWidget alert = new AlertWidget(WRONG_INPUT);
				alert.getBox().center();
			} else
			{
				AlertWidget alert = new AlertWidget(CORRECT_INPUT);
				alert.getBox().center();
				SurveyResult result = new SurveyResult(main.getUser().getId(), answers.get(0), answers.get(1),
						answers.get(2), answers.get(3), answers.get(4), answers.get(5), answers.get(6), answers.get(7));
				main.client.submitSurveyResult(result);
				main.showPageTafelTrainer();
			}
		}

	}

}
