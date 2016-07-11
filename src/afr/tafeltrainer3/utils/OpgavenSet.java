package afr.tafeltrainer3.utils;

import java.util.ArrayList;

import afr.tafeltrainer3.main.ClientImp;
import afr.tafeltrainer3.shared.Opgave;

import com.google.gwt.user.client.rpc.IsSerializable;

public class OpgavenSet implements IsSerializable
{
private ArrayList<Opgave> opgaven = new ArrayList<Opgave>();
private ClientImp client;
private int userid;
private static int MAXLENGTH = 20;

	public OpgavenSet()
	{

	}

	public OpgavenSet(ClientImp client, int userid)
	{
		this.client = client;
		this.userid = userid;
	}
	
	public void addOpgave(Opgave opgve)
	{
		opgaven.add(opgve);
		if(opgaven.size()==MAXLENGTH)
		{
			client.submitOpgavenSet(opgaven, userid);
			opgaven.clear();
		}
	}
	
	public void empty()
	{
		if(opgaven.size()>0)
		{
			client.submitOpgavenSet(opgaven, userid);
			opgaven.clear();
		}
	}
	
	public void clearOpgavenSet()
	{
		opgaven.clear();
	}
	
	public ArrayList<Opgave> getOpgavenSet()
	{
		return opgaven;
	}
	
	
	
}
