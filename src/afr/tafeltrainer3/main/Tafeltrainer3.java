package afr.tafeltrainer3.main;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.ui.RootPanel;

public class Tafeltrainer3 implements EntryPoint
{
	
	public void onModuleLoad() 
	{
		//Devicetype d = (Devicetype)GWT.create(Devicetype.class);
		MainView page = new MainView();
		RootPanel.get().add(page);
	}

}
