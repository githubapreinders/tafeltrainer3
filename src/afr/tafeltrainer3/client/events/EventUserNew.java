package afr.tafeltrainer3.client.events;

import java.io.Serializable;

import afr.tafeltrainer3.shared.User;

public class EventUserNew extends DataEvent implements Serializable 

{

/**
* 
*/
private static final long serialVersionUID = -1557143778036307422L;
private User user;
	
	public EventUserNew()
	{
		
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
