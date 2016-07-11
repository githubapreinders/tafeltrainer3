package afr.tafeltrainer3.widgets;

import java.util.ArrayList;

import afr.tafeltrainer3.client.pages.Tafeltrainer3Gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class NumPadWidget extends Composite 
{

private Button btn0;
private Button btn1;
private Button btn2;
private Button btn3;
private Button btn4;
private Button btn5;
private Button btn6;
private Button btn7;
private Button btn8;
private Button btn9;
private Button btn11;
public Button btn12;
private HorizontalPanel hpanelrow1;
private HorizontalPanel hpanelrow2;
private HorizontalPanel hpanelrow3;
private HorizontalPanel hpanelrow4;
public String number;
private Tafeltrainer3Gui gui;
private VerticalPanel vpanel;
private ArrayList<Button> buttons;

	public NumPadWidget(Tafeltrainer3Gui gui)
	{
		this.gui = gui;
		this.buttons = new ArrayList<Button>();
		vpanel = new VerticalPanel();
		vpanel.setSpacing(0);
		vpanel.setStyleName("numpadvpanel");
		initWidget(vpanel);
		hpanelrow1 = new HorizontalPanel();
		hpanelrow1.setSpacing(3);
		hpanelrow1.setStyleName("numpadhpanel");
		btn1 = new Button("1");
		btn1.addClickHandler(new btnNumPadClickHandler());
		btn1.setStyleName("numpadbutton");
		btn2 = new Button("2");
		btn2.addClickHandler(new btnNumPadClickHandler());
		btn2.setStyleName("numpadbutton");
		btn3 = new Button("3");
		btn3.addClickHandler(new btnNumPadClickHandler());
		btn3.setStyleName("numpadbutton");
		hpanelrow1.add(btn1);
		hpanelrow1.add(btn2);
		hpanelrow1.add(btn3);
		vpanel.add(hpanelrow1);
		
		hpanelrow2 = new HorizontalPanel();
		
		hpanelrow2.setStyleName("numpadhpanel");
		hpanelrow2.setSpacing(3);
		btn4 = new Button("4");
		btn4.addClickHandler(new btnNumPadClickHandler());
		btn4.setStyleName("numpadbutton");
		btn5 = new Button("5");
		btn5.addClickHandler(new btnNumPadClickHandler());
		btn5.setStyleName("numpadbutton");
		btn6 = new Button("6");
		btn6.addClickHandler(new btnNumPadClickHandler());
		btn6.setStyleName("numpadbutton");
		hpanelrow2.add(btn4);
		hpanelrow2.add(btn5);
		hpanelrow2.add(btn6);
		vpanel.add(hpanelrow2);
		
		hpanelrow3 = new HorizontalPanel();
		hpanelrow3.setStyleName("numpadhpanel");
		hpanelrow3.setSpacing(3);
		btn7 = new Button("7");
		btn7.addClickHandler(new btnNumPadClickHandler());
		btn7.setStyleName("numpadbutton");
		btn8 = new Button("8");
		btn8.addClickHandler(new btnNumPadClickHandler());
		btn8.setStyleName("numpadbutton");
		btn9 = new Button("9");
		btn9.addClickHandler(new btnNumPadClickHandler());
		btn9.setStyleName("numpadbutton");
		hpanelrow3.add(btn7);
		hpanelrow3.add(btn8);
		hpanelrow3.add(btn9);
		vpanel.add(hpanelrow3);
		
		hpanelrow4 = new HorizontalPanel();
		hpanelrow4.setStyleName("numpadhpanel");
		hpanelrow4.setSpacing(3);
		btn11 = new Button("C");
		btn11.addClickHandler(new btnNumPadClearClickHandler());
		btn11.setStyleName("numpadbutton");
		btn0 = new Button("0");
		btn0.addClickHandler(new btnNumPadClickHandler());
		btn0.setStyleName("numpadbutton");
		btn12 = new Button("=");
		btn12.addClickHandler(new btnNumPadSubmitClickHandler());
		btn12.setStyleName("numpadbutton");
		btn12.setEnabled(false);
		hpanelrow4.add(btn11);
		hpanelrow4.add(btn0);
		hpanelrow4.add(btn12);
		vpanel.add(hpanelrow4);
		number = "";
		buttons.add(btn0);
		buttons.add(btn1);
		buttons.add(btn2);
		buttons.add(btn3);
		buttons.add(btn4);
		buttons.add(btn5);
		buttons.add(btn6);
		buttons.add(btn7);
		buttons.add(btn8);
		buttons.add(btn9);
		buttons.add(btn11);
		buttons.add(btn12);
	}
	
	private class btnNumPadClickHandler implements ClickHandler
	{
		@Override
		public void onClick(ClickEvent event) 
		{
			Button s = (Button)event.getSource();
			number =  s.getText();
			gui.addToCache(number);
			gui.answerSubmitted();
		}
	}
	
	private class btnNumPadSubmitClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			gui.onNumPadUp();
		}
		
	}
	
	private class btnNumPadClearClickHandler implements ClickHandler
	{

		@Override
		public void onClick(ClickEvent event) 
		{
			number = "";
			gui.getTxt10().setText(number);
			gui.setFlag(0);
			gui.setSecondflag(0);
		}
		
	}
	
	
	public void enableWidget(boolean enabled)
	{
			for(Button b : buttons)
			{
				b.setEnabled(enabled);
			}
	}
	
	
	
	
}
