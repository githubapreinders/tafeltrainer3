package afr.tafeltrainer3.client.events;

public class EventAddParentsMailaddress extends DataEvent
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String reply;

	public String getReply()
	{
		return reply;
	}

	public void setReply(String reply)
	{
		this.reply = reply;
	}
	
}
