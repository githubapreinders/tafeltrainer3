package afr.tafeltrainer3.shared;

import java.io.Serializable;

public class UserResults implements Serializable
{

private static final long serialVersionUID = 814832536006808791L;
private int userid;
private String username;
private String familyname;
private double percentagescore;
private int howmuchopgaven;
private double averagespeed;

	public UserResults()
	{
		userid = 0;
		username = "Jan";
		familyname = "Jansen";
		percentagescore = 0;
		howmuchopgaven  = 0;
		averagespeed = 0;
	}
	
	public UserResults(int userid, String username, String familyname, double percentagescore, int 
			homuchopgaven, double averagespeed)
	{
		this.userid = userid;
		this.username = username;
		this.familyname = familyname;
		this.percentagescore = percentagescore;
		this.howmuchopgaven = homuchopgaven;
		this.averagespeed = averagespeed;
	}
	
	
	
	
		public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getFamilyname() {
		return familyname;
	}
	
	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}
	
	public double getPercentagescore() {
		return percentagescore;
	}
	
	public void setPercentagescore(double percentagescore) {
		this.percentagescore = percentagescore;
	}
	
	public int getHowmuchopgaven() {
		return howmuchopgaven;
	}
	
	public void setHowmuchopgaven(int howmuchopgaven) {
		this.howmuchopgaven = howmuchopgaven;
	}
	
	public double getAveragespeed() {
		return averagespeed;
	}
	
	public void setAveragespeed(double averagespeed) {
		this.averagespeed = averagespeed;
	}

	
	
}
