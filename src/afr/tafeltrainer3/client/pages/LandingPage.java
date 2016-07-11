package afr.tafeltrainer3.client.pages;


import afr.tafeltrainer3.main.ClientImp;
import afr.tafeltrainer3.main.MainView;
import afr.tafeltrainer3.main.tafeltrainer3messages;

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
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class LandingPage extends Composite 
{
	private ClientImp client;
	private MainView main;
	private FlexTable flex1;
	public HTML html;
	
	
	public LandingPage(MainView main,tafeltrainer3messages messages,ClientImp client)
	{
		this.main = main;
		this.client = client;
		main.menu.hpanel2.setVisible(false);
		flex1 = new FlexTable();
		initWidget(this.flex1);
		this.flex1.setStyleName("tafeltabel");
		flex1.setCellSpacing(0);
		flex1.setCellPadding(0);
		flex1.setBorderWidth(0);
		html = new HTML("E-mailadres is met succes geverifieerd; u bent vanaf nu \n een geregistreerde gebruiker."
				+ " Wij wensen u en uw leerling(en) \n veel succes en plezier met de tafeltrainer! \n Aarzel niet"
				+ " om bij vragen contact met ons op te nemen \n via apreinders74@gmail.com");
		html.setStyleName("html_groot");
		html.setWordWrap(true);
		this.flex1.getFlexCellFormatter().setHeight(0, 0, "250px");
		Button btnconfirm = new Button("OK");
		btnconfirm.setStyleName("standardbutton");
		btnconfirm.addClickHandler(new btnconfirmClickHandler());
		this.flex1.getFlexCellFormatter().setHeight(1, 0, "250px");
		this.flex1.getFlexCellFormatter().setWidth(1, 0, "280px");
		this.flex1.getFlexCellFormatter().setWidth(1, 1, "400px");
		this.flex1.getFlexCellFormatter().setWidth(1, 2, "280px");
		this.flex1.getFlexCellFormatter().setColSpan(0, 0, 3);
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
		this.flex1.getFlexCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
		this.flex1.getFlexCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
		this.flex1.getFlexCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);
		flex1.setWidget(0, 0, html);
		flex1.setWidget(1, 0, btnconfirm);
		
	}

	private class btnconfirmClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event)
		{
			main.showPageLoginBegeleider();			
		}
		
	}
	
		
}
