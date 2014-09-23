package afr.tafeltrainer3.shared;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.rpc.IsSerializable;

public class FeedbackContainer implements IsSerializable 

{
	
private int howmuchopgaven;
private double averagespeed;
private Date begindatum;
private String begindatumstring = "";
private String oefentijd;
private int errors;
private ArrayList<TafelResult> tafelresultaten;
private String pastThreeSessions;

public FeedbackContainer()
	{
	
	}

public FeedbackContainer(int howmuchopgaven,double averagespeed,Date begindatum,String oefentijd,int errors
		,ArrayList<TafelResult> tafelresultaten,String pastthreesessions)
	{
		this.howmuchopgaven = howmuchopgaven;
		this.averagespeed = averagespeed;
		this.begindatum = begindatum;
		this.oefentijd = oefentijd;
		this.errors = errors;
		this.tafelresultaten = tafelresultaten;
		this.pastThreeSessions = pastthreesessions;
	}

public String getPercentage()
	{
	String str = "0,0%";
	if(this.howmuchopgaven>0)
	{
	double percentage = 100-((double)this.errors*100)/(double)this.howmuchopgaven;
	str = NumberFormat.getFormat("##,#").format(percentage)+"%";
	}
	return str;
	}

public double getPercentageAsDouble()
{
	
	if(this.howmuchopgaven>0)

	{
	double percentage = 100-((double)this.errors*100)/(double)this.howmuchopgaven;
	return percentage;
	}
	return 0;
}

public int getErrors() {
	return errors;
}

public String getOefentijd() {
	return oefentijd;
}

public Date getBegindatum() {
	return begindatum;
}

public double getAveragespeed() {
	return this.averagespeed;
}

public int getHowmuchopgaven() {
	return this.howmuchopgaven;
}

public ArrayList<TafelResult> getTafelresultaten() {
	return tafelresultaten;
}

public String getPastThreeSessions() {
	return pastThreeSessions;
}

public String getBegindatumstring() {
	return begindatumstring;
}

public void setBegindatumstring(String begindatumstring) {
	this.begindatumstring = begindatumstring;
}



}
