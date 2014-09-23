package afr.tafeltrainer3.client.events;

import java.io.Serializable;
import java.util.ArrayList;

import afr.tafeltrainer3.shared.User;

public class EventGetGroup extends DataEvent implements Serializable
{

private static final long serialVersionUID = 4214803689045992989L;
ArrayList<User> users ;

	public EventGetGroup()
	{
		
	}

	public ArrayList<User> getUsers() 
	{
		return users;
	}
	
	public void setUsers(ArrayList<User> users) 
	{
		this.users = users;
	}
	
}
