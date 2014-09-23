package afr.tafeltrainer3.client.events;

import java.io.Serializable;

import afr.tafeltrainer3.shared.SuperUser;

public class EventSuperUserRetrieved extends DataEvent implements Serializable

{
public SuperUser superuser;

	public SuperUser getSuperuser() 
	{
		return superuser;
	}
	
	public void setSuperuser(SuperUser superuser) 
	{
		this.superuser = superuser;
	}

}
