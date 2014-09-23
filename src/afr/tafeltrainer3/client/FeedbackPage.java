package afr.tafeltrainer3.client;

import java.util.List;

import afr.tafeltrainer3.shared.FeedbackContainer;
import afr.tafeltrainer3.shared.TafelResult;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FeedbackPage extends Composite
{

	public Button btn00;
	private ClientImp client;
	private MainView main;
	private FeedbackContainer fiba;
	private FlexTable flex1;
	private FlexTable flex00;
	public FlexTable flex10;
	public FlexTable flex11;
	private HorizontalPanel hpanel00;
	private HTML html00;
	private Label lbl31opgaven;
	private Label lbl31speed;
	private Label lbl31begindatum;
	private Label lbl31oefentijd;
	private Label lbl31errors;
	private Label lbl31percentage;
	private ScrollPanel spanel1;
	private ScrollPanel spanel2;
	private tafeltrainer3messages messages;
	private HTML html00a;
	private HTML html00b;
	private HTML html00c;
	private HTML html00d;
	private Label lbl00;

	public FeedbackPage(MainView main, FeedbackContainer fiba, tafeltrainer3messages messages)
	{
		this.main = main;
		this.fiba = fiba;
		this.messages = messages;
		flex1 = new FlexTable();
		initWidget(this.flex1);
		this.flex1.setStyleName("tafeltabel");
		flex1.setCellSpacing(0);
		flex1.setCellPadding(0);
		flex1.setBorderWidth(0);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "125px");
		this.flex1.getFlexCellFormatter().setHeight(1, 0, "375px");
		this.flex1.getFlexCellFormatter().setWidth(0, 0, "960px");
		this.flex1.getFlexCellFormatter().setColSpan(0, 0, 2);
		this.flex1.getFlexCellFormatter().setRowSpan(1, 0, 2);
		this.flex1.getCellFormatter().setWidth(1, 0, "500px");

		// eerste rij
		flex00 = new FlexTable();
		flex00.getFlexCellFormatter().setWidth(0, 0, "320px");
		flex00.getFlexCellFormatter().setWidth(0, 1, "320px");
		flex00.getFlexCellFormatter().setWidth(0, 2, "320px");
		flex00.getFlexCellFormatter().setHorizontalAlignment(0, 2, HasHorizontalAlignment.ALIGN_RIGHT);
		this.hpanel00 = new HorizontalPanel();
		this.html00 = new HTML("<h1>" + messages.feedbackpage_mijnresultaten() + "</h1>");
		this.html00.setStyleName("topkader");
		VerticalPanel vpanel00 = new VerticalPanel();

		this.html00a = new HTML();
		this.html00a.setStyleName("html");
		this.html00b = new HTML();
		this.html00b.setStyleName("html");
		this.html00c = new HTML();
		this.html00c.setStyleName("html");
		this.html00d = new HTML();
		this.html00d.setStyleName("html");
		vpanel00.add(html00a);
		vpanel00.add(html00b);
		vpanel00.add(html00c);
		vpanel00.add(html00d);

		btn00 = new Button(messages.feedbackpage_checkbutton());
		btn00.setStyleName("standardbutton");
		btn00.addClickHandler(new btn00ClickHandler());
		lbl00 = new Label(messages.feedbackpage_checkvoornieuweresultaten());
		VerticalPanel vpanel00b = new VerticalPanel();
		vpanel00b.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		vpanel00b.add(btn00);
		vpanel00b.add(lbl00);

		this.hpanel00.add(html00);
		this.hpanel00.add(vpanel00);
		this.hpanel00.add(vpanel00b);
		this.flex00.setWidget(0, 0, vpanel00);
		this.flex00.setWidget(0, 1, html00);
		this.flex00.setWidget(0, 2, vpanel00b);

		this.flex1.setWidget(0, 0, flex00);

		// tweede rij, kolom met de resultaten per individuele tafel
		this.spanel1 = new ScrollPanel();
		flex10 = new FlexTable();
		flex10.setBorderWidth(0);
		flex10.getFlexCellFormatter().setWidth(0, 0, "150px");
		flex10.getFlexCellFormatter().setWidth(0, 1, "150px");
		flex10.getFlexCellFormatter().setWidth(0, 2, "150px");
		this.spanel1.setHeight("365px");
		this.spanel1.setWidth("470px");
		spanel1.add(flex10);
		this.flex1.setWidget(1, 0, spanel1);

		// tweede rij, kolom met awards
		flex11 = new FlexTable();
		flex11.getFlexCellFormatter().setWidth(0, 0, "150px");
		flex11.getFlexCellFormatter().setWidth(0, 1, "150px");
		flex11.getFlexCellFormatter().setWidth(0, 2, "150px");
		flex11.setBorderWidth(0);
		this.spanel2 = new ScrollPanel();
		this.spanel2.setHeight("365px");
		this.spanel2.setWidth("490px");
		this.spanel2.add(flex11);
		this.flex1.setWidget(1, 1, spanel2);

	}

	private class btn00ClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event)
		{
			if (main.getUser().getId() != 1)
			{
				btn00.setEnabled(false);
				main.client.getFeedbackData(main.user.getId());
			}
			else
			{
				main.alertwidget.getBox().setText("");
				main.alertwidget.getContentlabel().setText(messages.feedbackpage_blankloginwarning());
				main.alertwidget.getBox().center();
			}
		}
	}

	// hier worden awards bepaald. Een gehaalde tafel moet bijv minstens 50
	// opgaven hebben en een score groter dan 90%
	private void fillFlowPanel()
	{
		flex11.clear();
		int awardcount = 0;
		int x = 0;
		int y = 0;
		for (TafelResult t : fiba.getTafelresultaten())
		{
			if (t.getTafelsoort() < 10)
			{
				final Image awardimg;
				if (t.getAantalopgaven() >= 50 && t.getScore() >= 90)
				{
					awardimg = new Image("/images/awards/tafelsoort" + String.valueOf(t.getTafelsoort()) + ".png");
					awardcount++;
					awardimg.setWidth("140px");
					awardimg.addClickHandler(new ClickHandler()
					{

						@Override
						public void onClick(ClickEvent event)
						{
							PopupPanel popup = new PopupPanel(true);
							popup.setPopupPosition(0, 0);
							VerticalPanel vpanel = new VerticalPanel();
							popup.setStyleName("popup");
							vpanel.add(new Image(awardimg.getUrl()));
							popup.setWidget(vpanel);
							popup.show();
						}
					});

				} else
				{
					awardimg = new Image("/images/awards/tafelsoort" + String.valueOf(t.getTafelsoort()) + "dark.png");
					awardimg.setWidth("150px");
					awardimg.addClickHandler(new ClickHandler()
					{
						
						@Override
						public void onClick(ClickEvent event)
						{
							main.alertwidget.getBox().setText("");
							main.alertwidget.getContentlabel().setText(messages.feedbackpage_hoehaaliktafel());
							main.alertwidget.getBox().center();
						}
					});
				}
				flex11.setWidget(y, x, awardimg);
			}
			flex10.setWidget(y, x, new GaugeWidget(t.getTafelsoort(), t.getScore(), messages));
			x++;
			if (x > 2)
			{
				x = 0;
				y++;
			}
		}
		x = 2;
		y = 2;
		double moeilijkeopgavencount = 0;
		for (TafelResult t : fiba.getTafelresultaten())
		{
			if (t.getTafelsoort() > 9)
			{
				moeilijkeopgavencount += t.getAantalopgaven();
			}
		}
		double percentagescore = 0;
		final Image awardimg1;
		if (moeilijkeopgavencount > 100)
		{
			for (TafelResult t : fiba.getTafelresultaten())
			{
				if (t.getTafelsoort() > 9)
					percentagescore += ((double) t.getScore())
							* ((double) t.getAantalopgaven() / moeilijkeopgavencount);
			}
			if (percentagescore > 90)
			{
				awardimg1 = new Image("/images/awards/moeilijketafels.png");
				flex11.setWidget(y, x, awardimg1);
				awardimg1.setWidth("140px");
				awardimg1.addClickHandler(new ClickHandler()
				{

					@Override
					public void onClick(ClickEvent event)
					{
						PopupPanel popup = new PopupPanel(true);
						popup.setPopupPosition(0, 0);
						VerticalPanel vpanel = new VerticalPanel();
						popup.setStyleName("popup");
						vpanel.add(new Image(awardimg1.getUrl()));
						popup.setWidget(vpanel);
						popup.show();
					}
				});
			} else
			{
				awardimg1 = new Image("/images/awards/moeilijketafels_dark.png");
				awardimg1.setWidth("140px");
				awardimg1.addClickHandler(new ClickHandler()
				{
					
					@Override
					public void onClick(ClickEvent event)
					{
						main.alertwidget.getBox().setText("");
						main.alertwidget.getContentlabel().setText(messages.feedbackpage_hoehaalikmoeilijketafels());
						main.alertwidget.getBox().center();
					}
				});
				flex11.setWidget(y, x, awardimg1);
			}

		} else
		{
			awardimg1 = new Image("/images/awards/moeilijketafels_dark.png");
			awardimg1.setWidth("140px");
			awardimg1.addClickHandler(new ClickHandler()
			{
				
				@Override
				public void onClick(ClickEvent event)
				{
					main.alertwidget.getBox().setText("");
					main.alertwidget.getContentlabel().setText(messages.feedbackpage_hoehaalikmoeilijketafels());
					main.alertwidget.getBox().center();
				}
			});
			
			flex11.setWidget(y, x, awardimg1);
		}
		x++;
		if (x > 2)
		{
			x = 0;
			y++;
		}

		final Image awardimgsnel;
		if (fiba.getHowmuchopgaven() > 200 && fiba.getAveragespeed() > 0 && fiba.getAveragespeed() < 5)
		{
			awardimgsnel = new Image("/images/awards/snellejelle.png");
			awardimgsnel.setWidth("140px");
			awardimgsnel.addClickHandler(new ClickHandler()
			{

				@Override
				public void onClick(ClickEvent event)
				{
					PopupPanel popup = new PopupPanel(true);
					popup.setPopupPosition(0, 0);
					VerticalPanel vpanel = new VerticalPanel();
					popup.setStyleName("popup");
					vpanel.add(new Image(awardimgsnel.getUrl()));
					popup.setWidget(vpanel);
					popup.show();
				}
			});
		} else
		{
			awardimgsnel = new Image("/images/awards/snellejelle_dark.png");
			awardimgsnel.setWidth("140px");
			awardimgsnel.addClickHandler(new ClickHandler()
			{
				
				@Override
				public void onClick(ClickEvent event)
				{
					main.alertwidget.getBox().setText("");
					main.alertwidget.getContentlabel().setText(messages.feedbackpage_andereawards());
					main.alertwidget.getBox().center();
				}
			});
			
			
		}
		flex11.setWidget(y, x, awardimgsnel);
		x++;
		if (x > 2)
		{
			x = 0;
			y++;
		}

		final Image awardimg2;
		if (fiba.getHowmuchopgaven() > 400 && fiba.getAveragespeed() > 0 && fiba.getAveragespeed() < 3)
		{
			awardimg2 = new Image("/images/awards/snelheidsduivel.png");
			awardimg2.setWidth("140px");
			awardimg2.addClickHandler(new ClickHandler()
			{

				@Override
				public void onClick(ClickEvent event)
				{
					PopupPanel popup = new PopupPanel(true);
					popup.setPopupPosition(0, 0);
					VerticalPanel vpanel = new VerticalPanel();
					popup.setStyleName("popup");
					vpanel.add(new Image(awardimg2.getUrl()));
					popup.setWidget(vpanel);
					popup.show();
				}
			});
		} else
		{
			awardimg2 = new Image("/images/awards/snelheidsduivel_dark.png");
			awardimg2.setWidth("140px");
			awardimg2.addClickHandler(new ClickHandler()
			{
				
				@Override
				public void onClick(ClickEvent event)
				{
					main.alertwidget.getBox().setText("");
					main.alertwidget.getContentlabel().setText(messages.feedbackpage_andereawards());
					main.alertwidget.getBox().center();
				}
			});
		}
		flex11.setWidget(y, x, awardimg2);
		x++;
		if (x > 2)
		{
			x = 0;
			y++;
		}

		final Image awardimg3;
		double percentage = 0;
		int g = 1;
		//String str = fiba.getPercentage().substring(0, fiba.getPercentage().length() - 1);
		//percentage = Double.parseDouble(str);
		percentage = fiba.getPercentageAsDouble();
		
		// Als je lang zonder succes speelt krijg je een award voor
		// doorzettingsvermogen
		if ((percentage < 80 && fiba.getHowmuchopgaven() > 200) || fiba.getHowmuchopgaven() > 400)
		{
			awardimg3 = new Image("/images/awards/aanhouderwint.png");
			awardimg3.setWidth("140px");
			awardimg3.addClickHandler(new ClickHandler()
			{

				@Override
				public void onClick(ClickEvent event)
				{
					PopupPanel popup = new PopupPanel(true);
					popup.setPopupPosition(0, 0);
					VerticalPanel vpanel = new VerticalPanel();
					popup.setStyleName("popup");
					vpanel.add(new Image(awardimg3.getUrl()));
					popup.setWidget(vpanel);
					popup.show();
				}
			});
		} else
		{
			awardimg3 = new Image("/images/awards/aanhouderwint_dark.png");
			awardimg3.setWidth("140px");
			awardimg3.addClickHandler(new ClickHandler()
			{
				
				@Override
				public void onClick(ClickEvent event)
				{
					main.alertwidget.getBox().setText("");
					main.alertwidget.getContentlabel().setText(messages.feedbackpage_andereawards());
					main.alertwidget.getBox().center();
				}
			});
			
		}
		flex11.setWidget(y, x, awardimg3);
		x++;
		if (x > 2)
		{
			x = 0;
			y++;
		}

		// als alle tafels gehaald zijn krijg je je tafeldiploma
		final Image awardimg4;
		if (awardcount > 7)
		{
			awardimg4 = new Image("/images/awards/tafeldiploma.png");
			awardimg4.setWidth("140px");
			awardcount++;
			awardimg4.addClickHandler(new ClickHandler()
			{

				@Override
				public void onClick(ClickEvent event)
				{
					PopupPanel popup = new PopupPanel(true);
					popup.setPopupPosition(0, 0);
					VerticalPanel vpanel = new VerticalPanel();
					popup.setStyleName("popup");
					Image tafeldiploma = new Image(awardimg4.getUrl());
					tafeldiploma.setWidth("95%");
					vpanel.add(tafeldiploma);
					popup.setWidget(vpanel);
					popup.show();
				}

			});
		} else
		{
			awardimg4 = new Image("/images/awards/tafeldiploma_dark.png");
			awardimg4.setWidth("140px");
			awardimg4.addClickHandler(new ClickHandler()
			{
				
				@Override
				public void onClick(ClickEvent event)
				{
					main.alertwidget.getBox().setText("");
					main.alertwidget.getContentlabel().setText(messages.feedbackpage_tafeldiploma());
					main.alertwidget.getBox().center();
				}
			});
			
		}
		flex11.setWidget(y, x, awardimg4);
		x++;
		if (x > 2)
		{
			x = 0;
			y++;
		}

	}

	public void setFeedbackContainer(FeedbackContainer fiba)
	{
		if (fiba != null)
		{
			this.flex10.clear();
			this.fiba = fiba;
			this.html00a.setHTML(main.getUser().getName() + " " + main.getUser().getFamilyname());
			String begindatum = fiba.getBegindatumstring();
			// String datestring =
			// DateTimeFormat.getFormat("dd-MMM-yyyy").format(fiba.getBegindatum());
			this.html00b.setHTML(messages.feedbackpage_begonnenop() + " : " + begindatum);
			this.html00c.setHTML(messages.feedbackpage_oefentijd() + " : " + fiba.getOefentijd());
			this.html00d.setHTML(fiba.getHowmuchopgaven() + " " +  messages.feedbackpage_sommengemaakt());
			fillFlowPanel();
		}
	}

}
