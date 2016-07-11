package afr.tafeltrainer3.client.pages;

import afr.tafeltrainer3.client.shop.ProductWidget;
import afr.tafeltrainer3.main.ClientImp;
import afr.tafeltrainer3.main.MainView;
import afr.tafeltrainer3.main.tafeltrainer3messages;
import afr.tafeltrainer3.shared.BeloningsHoogte;
import afr.tafeltrainer3.shared.Opgave;
import afr.tafeltrainer3.shared.OpgaveSoort;
import afr.tafeltrainer3.utils.OpgavenSet;
import afr.tafeltrainer3.widgets.NumPadWidget;
import afr.tafeltrainer3.widgets.TafelTimer;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class Tafeltrainer3Gui extends Composite
{
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
	public Button btn2;
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
	public HorizontalPanel hpanel_main;
	public HorizontalPanel hpanel2;
	public HorizontalPanel hpanel3;
	public HorizontalPanel hpanelrow0a;
	public HorizontalPanel hpanelrow0b;
	public HorizontalPanel hpanel40;
	public FlowPanel fpanel1;
	public Opgave opg;
	public OpgaveSoort opgavesoort;
	public OpgavenSet opgavenset;
	public ScrollPanel spanel1;
	public ScrollPanel spanel2;
	public TafelTimer tim;
	public tafeltrainer3messages messages;
	public TextBox txt1;
	public TextBox txt10;
	public VerticalPanel vpanel1;
	public NumPadWidget numpad;
	public HorizontalPanel gaugepanel;

	public Tafeltrainer3Gui()
	{

	}

	public void addToCache(String number)
	{
		
	}
	
	public void answerSubmitted()
	{
		
	}
	
	public void onNumPadUp()
	{
		
	}
	
	public void updatefpanel1(int number)
	{
		
	}
	
	public void updateSpanel2(ProductWidget widget)
	{
		
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
	}

	public TextBox getTxt10()
	{
		return txt1;
	}
	
	public int getFlag()
	{
		return flag;
	}

	
	public void setFlag(int flag)
	{
	}

	public int getSecondflag()
	{
		return secondflag;
	}

	public void setSecondflag(int secondflag)
	{
	}
	
	public OpgavenSet getOpgavenSet()
	{
		return opgavenset;
	}
	
}
