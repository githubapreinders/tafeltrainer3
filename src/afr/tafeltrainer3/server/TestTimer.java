package afr.tafeltrainer3.server;

import com.google.gwt.user.client.Timer;

public class TestTimer extends Timer {

	public TestTimer()
	{
		scheduleRepeating(100);
	}
	
	@Override
	public void run() 
	{
		System.out.println();
	}

}
