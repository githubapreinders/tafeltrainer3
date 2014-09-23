package afr.tafeltrainer3.client.events;

import java.io.Serializable;

import afr.tafeltrainer3.shared.User;

public class EventUserRetrieved extends DataEvent implements Serializable
{

/**
	 * 
	 */
private static final long serialVersionUID = -2207671017632319450L;

public User user;
	
	public EventUserRetrieved()
	{
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}
