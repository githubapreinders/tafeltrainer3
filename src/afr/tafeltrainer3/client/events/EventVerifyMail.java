package afr.tafeltrainer3.client.events;

import java.io.Serializable;

public class EventVerifyMail extends DataEvent implements Serializable
{

	private static final long serialVersionUID = 7674251060969505513L;
	String parameter;
	
	public EventVerifyMail()
	{
		
	}

	public String getParameter()
	{
		return parameter;
	}

	public void setParameter(String parameter)
	{
		this.parameter = parameter;
	}
	
}
