package afr.tafeltrainer3.client.events;

import java.io.Serializable;
import java.util.ArrayList;

import afr.tafeltrainer3.shared.UserResults;

public class EventGetGroupResults extends DataEvent implements Serializable

{
	
private static final long serialVersionUID = 5247585023716483281L;

public ArrayList<UserResults> userresults;
	
	public EventGetGroupResults()
	{
		
	}
	public ArrayList<UserResults> getUserresults() {
		return userresults;
	}
	public void setUserresults(ArrayList<UserResults> userresults) {
		this.userresults = userresults;
	}

	
}
