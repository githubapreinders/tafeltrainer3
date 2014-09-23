package afr.tafeltrainer3.client.events;

import java.io.Serializable;
import afr.tafeltrainer3.shared.FeedbackContainer;

public class EventUserFeedback extends DataEvent implements Serializable
{

private static final long serialVersionUID = -843824694357842175L;
public FeedbackContainer fiba;

	public EventUserFeedback()
	{
		
	}
	
	public FeedbackContainer getFiba() 
	{
		return fiba;
	}
	
	public void setFiba(FeedbackContainer fiba) 
	{
		this.fiba = fiba;
	}

}
