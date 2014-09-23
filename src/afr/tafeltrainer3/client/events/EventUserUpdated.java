package afr.tafeltrainer3.client.events;

import java.io.Serializable;

import afr.tafeltrainer3.shared.User;

public class EventUserUpdated extends DataEvent implements Serializable

{
/**
	 * 
	 */
private static final long serialVersionUID = 639320994018436139L;

public User user;
	public EventUserUpdated()
	{
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
