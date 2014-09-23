package afr.tafeltrainer3.client;

import com.google.gwt.user.client.Timer;

public class WaitingTimer extends Timer {
	private int timeelapsed = 0 ;
	
	
	public WaitingTimer()
	{
		scheduleRepeating(100);
	}
	
	
	
	@Override
	public void run() 
	{
		timeelapsed += 100;
		if(timeelapsed/200==0){}
	}

}
