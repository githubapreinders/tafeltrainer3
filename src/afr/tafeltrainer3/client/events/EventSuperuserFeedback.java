package afr.tafeltrainer3.client.events;

import java.io.Serializable;

import afr.tafeltrainer3.shared.FeedbackContainer;

public class EventSuperuserFeedback extends DataEvent implements Serializable 

{

private static final long serialVersionUID = -4581059467282887042L;
public FeedbackContainer fiba;

	public EventSuperuserFeedback()
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
