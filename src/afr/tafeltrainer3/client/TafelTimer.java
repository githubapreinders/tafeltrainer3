package afr.tafeltrainer3.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.ui.Image;

public class TafelTimer extends Timer {

	public final int totalTime = 10000;
	public int timeLeft = totalTime;
	private int timeStep = 360;
	private int timeDone;
	private Tafeltrainer3Gui gui;

	public TafelTimer(Tafeltrainer3Gui gui) 
	{
		scheduleRepeating(timeStep);
		this.gui = gui;
	}

	@Override
	public void run() {
		timeDone += timeStep;
		timeLeft = totalTime - timeDone;
		if (timeDone > 10000) {
			timeDone = 10000;
		}
		if(timeDone>0&&timeDone<1000)
		{
			gui.updatefpanel1(1);
		}
		if(timeDone>=1000&&timeDone<2000)
		{
			gui.updatefpanel1(2);
		}	
		if(timeDone>2000&&timeDone<3000)
		{	
			gui.updatefpanel1(3);
		}
		if(timeDone>3000&&timeDone<4000)
		{	
			gui.updatefpanel1(4);
		}
		if(timeDone>4000&&timeDone<5000)
		{	
			gui.updatefpanel1(5);
		}
		if(timeDone>5000&&timeDone<6000)
		{	
			gui.updatefpanel1(6);
		}
		if(timeDone>6000&&timeDone<7000)
		{	
			gui.updatefpanel1(7);
		}
		if(timeDone>7000&&timeDone<8000)
		{	
			gui.updatefpanel1(8);
		}
		if(timeDone>8000&&timeDone<9000)
		{	
			gui.updatefpanel1(9);
		}
		if(timeDone>9000&&timeDone<10000)
		{	
			gui.updatefpanel1(10);
		}
		if (timeDone == totalTime) 
		{
			cancel();
		}
	
	}

	public void cancelTimer() {
		this.cancel();
		
	}
}
