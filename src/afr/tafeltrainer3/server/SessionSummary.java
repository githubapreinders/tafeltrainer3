package afr.tafeltrainer3.server;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SessionSummary  {

String sessionLength="";
double howMuchOpgaven=0;
double fouten=0;
double errors = 0;
double averageSpeed=0;
double accuracy=0;
public String sessiondate="";
public Date start = new Date(); 
public Timestamp timestamp = new Timestamp(start.getTime());

int hours;
int minutes;
int seconds;



	public SessionSummary() 
	{
	
	}
	
	public SessionSummary(String sessiondate, int howmuchopgaven,int howmucherrors,int minutes, int seconds) 
	{
		this.sessiondate = sessiondate;
		this.howMuchOpgaven = howmuchopgaven;
		this.fouten = howmucherrors;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	
	public void addOpgave()
	{
		this.howMuchOpgaven++;
	}
	
	public void setAvgSpeed(double time)
	{
		this.averageSpeed = getSpeed(time);
	}
	
	public void setAccuracy(boolean correctness)
	{
		this.accuracy = getAccuracy(correctness);
	}
	
	public void setSessionLength()
	{
		this.sessionLength = getsessionLength();
	}
	
	public String getsessionLength() 
	{
		String output = "";
		Date currentTime = new Date();
		long diff = currentTime.getTime() - start.getTime();
        long diffSeconds = diff / 1000 % 60;
        this.seconds = (int)diffSeconds;
        long diffMinutes = diff / (60 * 1000) % 60;
        this.minutes = (int)diffMinutes;
        long diffHours = diff / (60 * 60 * 1000);
        this.hours = (int)diffHours;
        
        if(diffHours == 0)
        {
        output = "    Sessieduur: " + diffMinutes + " Min: " + diffSeconds +
        			" Sec";
        }
        if(diffHours > 0)
        {
        output = "    Sessieduur: " + diffHours + " Uur, "+ diffMinutes + " Min: " + diffSeconds +
        			" Sec";
        }
		return output;
	}

	public int getSessionLengthInSeconds()
	{
		return this.minutes*60 + seconds;
	}
	
	public void setSessionLength(String sessionLength) {
		this.sessionLength = sessionLength;
	}

	public void setHowMuchOpgaven(double howMuchOpgaven) {
		this.howMuchOpgaven = howMuchOpgaven;
	}

	public void setAverageSpeed(double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public double getSpeed(double time)
	{
		double avgSpeed = ((howMuchOpgaven-1)/howMuchOpgaven)*this.averageSpeed
				+ time/howMuchOpgaven;
		return round(avgSpeed,1);
	}
	
	public double getErrors() {
		return errors;
	}

	public void setErrors(double errors) {
		this.errors = errors;
	}

	
	public double getAccuracy(boolean juistheid)
	{
		if(juistheid==false)
			{this.fouten++;}
		double percentage = ((howMuchOpgaven - fouten)/howMuchOpgaven)*100;
		this.accuracy = round(percentage,1); 
		return accuracy;
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();
	    BigDecimal bd = new BigDecimal(value);
	    bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
	    return bd.doubleValue();
	}
	
	public String toString()
	{	
		Locale nederland = new Locale("nl");
		SimpleDateFormat sf = new SimpleDateFormat("EEEE dd MMM YYYY", nederland);
		String part1 = sf.format(start);
		return part1 ;
	}
	
	public double getFouten() {
		return fouten;
	}

	public void setFouten(double fouten) {
		this.fouten = fouten;
	}

	public String getSessiondate() {
		return sessiondate;
	}

	public void setSessiondate(String sessiondate) {
		this.sessiondate = sessiondate;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public double getHowMuchOpgaven() {
		return howMuchOpgaven;
	}

}
