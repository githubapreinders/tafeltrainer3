package afr.tafeltrainer3.shared;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable
{	
	
	
	private static final long serialVersionUID = 2767767209234983481L;
	int id;
	String emailsuperuser;
	String name;
	String familyname;
	String groupname;
	String loginname;
	String password;
	String emailparent;
	int money;
	boolean houindegaten;
	boolean surveydone;
	
	
	public User()
	{
		this.id =1;
		this.emailsuperuser = "default@nothing.com";
		this.name = "guestuser";
		this.familyname = "deffamname";
		this.groupname = "defgroupname";
		this.loginname = "deflogin";
		this.password = "defpassw";
		this.money = 0;
		this.houindegaten = false;
		this.emailparent = "0";
	}

	public User(int id, String emailsuperuser, String name, String familyname,
			String groupname, String loginname, String password, int money, boolean houindegaten) 
	{
		this.id = id;
		this.emailsuperuser = emailsuperuser;
		this.name = name;
		this.familyname = familyname;
		this.groupname = groupname;
		this.loginname = loginname;
		this.password = password;
		this.money = money;
		this.houindegaten=houindegaten;
	}
	
	public User(int id, String emailsuperuser, String name, String familyname,
			String groupname, String loginname, String password, int money, boolean houindegaten, boolean surveydone) 
	{
		this.id = id;
		this.emailsuperuser = emailsuperuser;
		this.name = name;
		this.familyname = familyname;
		this.groupname = groupname;
		this.loginname = loginname;
		this.password = password;
		this.money = money;
		this.houindegaten=houindegaten;
		this.surveydone=surveydone;
	}

	
	
	
	
	
	public boolean isSurveydone()
	{
		return surveydone;
	}

	public void setSurveydone(boolean surveydone)
	{
		this.surveydone = surveydone;
	}

	public void addMoney(int money)
	{
		this.money+=money;
	}

	public String toString()
	{
		String returnstring = this.name + " " + this.familyname + ", " + this.groupname +
						"loginnaam: " + this.loginname + " ww: " + this.password ;
		return returnstring;
	}
	
	public boolean getHouindegaten() 
	{
		return houindegaten;
	}

	public void setHouindegaten(boolean houindegaten) 
	{
		this.houindegaten = houindegaten;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFamilyname() {
		return familyname;
	}

	public void setFamilyname(String familyname) {
		this.familyname = familyname;
	}

	public String getGroupname() {
		return groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}


	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
		
	public String getEmailsuperuser() {
		return emailsuperuser;
	}
	
	public void setEmailsuperuser(String emailsuperuser) {
		this.emailsuperuser = emailsuperuser;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public String getEmailparent()
	{
		return emailparent;
	}

	public void setEmailparent(String emailparent)
	{
		this.emailparent = emailparent;
	}

}

