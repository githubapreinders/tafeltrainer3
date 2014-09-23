package afr.tafeltrainer3.server;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.TafelResult;
import afr.tafeltrainer3.shared.User;
import afr.tafeltrainer3.shared.UserResults;

import com.google.gwt.user.client.ui.HTML;


public class SendMessage 
{
private MySQLAccess mysqlacces = new MySQLAccess();
private SuperUser superuser;	

	public SendMessage(SuperUser superuser)
	{
		this.superuser = superuser;
		sendMail("apreinders74@gmail.com",superuser.getEmail(),"apreinders74@gmail.com",
				"Verslag Tafeltrainer",createMessage());
	}
		
	public SendMessage(String name,String emailadress, String password)
		{
			String message = "<B>Tijdelijk wachtwoord</B><BR><BR>Beste "+ name +" Wij hebben een tijdelijk wachtwoord "
					+ "voor u aangemaakt. Log in als begeleider en vul dit wachtwoord in. Daarna kunt "
					+ "u in het administratiescherm uw naam aanklikken en uw wachtwoord opnieuw instellen. "
					+ "<BR><BR>Uw tijdelijke wachtwoord is :<B> " + password + "</B><BR><BR>Met "
							+ "vriendelijke groet,  tafeltrainer gebruikersservice. ";
			sendMail("apreinders74@gmail.com",emailadress,"apreinders74@gmail.com",
					"Tafeltrainer tijdelijk wachtwoord",message);
			
		}

	public String sendMail(String from, String to, String replyTo, String subject, String message) {
	       
			String output=null;
			Properties props = new Properties();
	        Session session = Session.getDefaultInstance(props, null);

	        try {
		        	Multipart mp = new MimeMultipart();
		        	MimeBodyPart htmlpart = new MimeBodyPart();
		        	htmlpart.setContent(message,"text/html");
		        mp.addBodyPart(htmlpart); 	
	            Message msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress(from, "Tafeltrainer gebruikersservice"));
	            msg.addRecipient(Message.RecipientType.TO,
	                             new InternetAddress(to, "Gebruiker Tafeltrainer"));
	            msg.setSubject(subject);
	            msg.setContent(mp);
	            msg.setReplyTo(new InternetAddress[]{new InternetAddress(replyTo)});
	            Transport.send(msg);

	        } 
	        catch (AddressException e) 
	    		{
	            e.printStackTrace();
	        }
	         catch (MessagingException e) 
	        {
	        	 	e.printStackTrace();
	        } 
	        catch (UnsupportedEncodingException e) 
	        {
				e.printStackTrace();
			}
	        catch (Exception e) 
	        {
	        		output=e.toString();                
	        }   
	return output;
	}
	
	public String createMessage()
	{
		//TODO logo in de mail plaatsen en extra styling 
		String message = "<B>Resultaten Tafeltrainer gebruikersservice.</B><br><p>"
				+ "Beste "+superuser.getName()+" ,<br>Hierbij de laatste resultaten van uw leerling(en) op de "
						+ "tafeltrainer. U kunt uw instellingen altijd wijzigen via het gebruikersmenu op"
						+ " de <A href = 'http:1-dot-subtle-reserve-547.appspot.com'>site.</A><BR> Met vriendelijke groet,"
						+ "<br>team Tafeltrainer<br><p align = 'right'><I><A href = 'mailto:apreinders74@gmail.com'>Stuur ons een mail...</A></I></p><BR>";
		message += " <table border='0' cellpadding='0' cellspacing='0' width='900'>";
		String str = "<tr><th width='150'>naam</th><th width='100'>opgaven</th><th width='100'>score</th><th width='100'>snelheid</th>"
				+ "<th width='55'>t2</th><th width='55'>t3</th><th width='55'>t4</th><th width='55'>t5</th><th width='55'>t6</th><th width='55'>t7</th><th width='55'>t8</th><th width='65'>t9</th>";
		message+=str;
		
		String colorstring = "#EAF2D3";
		int switcher =0;
		ArrayList<UserResults> list = mysqlacces.getMetaData(superuser);
		for(UserResults u : list)
		{
			String str1 = u.getUsername();
			String str2 = u.getFamilyname();
			String str3 = String.valueOf(u.getHowmuchopgaven());
			String str4 = String.valueOf(u.getPercentagescore());
			String str5 = String.valueOf(u.getAveragespeed());
			message+="<tr style = 'text-align : center;background-color:"+colorstring+"'><td style='text-align:left;'>"+str1 + " " + str2 +"</td><td >"+str3+"</td><td >"+str4+"</td><td >"+str5+"</td>";
			ArrayList<TafelResult> results = mysqlacces.getTafelResults(u.getUserid()); 
			String tablecolor ="";
			for (int i = 0; i<8 ; i++)
			{
				tablecolor = "#D31145";
				if(results.get(i).getScore()>89&&results.get(i).getAantalopgaven()>20)
					tablecolor = "#002a06";
				message+="<td style = 'color:"+tablecolor+";'>"+results.get(i).getScore()+"</td>";
			}
			switch(switcher)
			{
			case 0 : colorstring = "#FFFFFF" ;switcher=1;break;
			case 1 : colorstring = "#EAF2D3" ;switcher=0;break;
			} 
			
		}	
		
		ArrayList<User> users = new ArrayList<User>(); 
		users = mysqlacces.getHouindegatenUsers(superuser);
		ArrayList<SessionSummary> reports = new ArrayList<SessionSummary>();
		if(users.size()>0)
		{
			message = message + "</table><br><br><u>Individuele gegevens:</u><br>Optie \"hou in de gaten \""
					+ ". De laatste drie dagen dat werd geoefend worden weergegeven.<br>";
			for(User u : users)
			{
				message = message + "<br><b>"+u.getName()+" "+u.getFamilyname()+" :"+"</b>"+"<br>";	
				reports = mysqlacces.getSessionDates(u.getId());
				ArrayList<String> dates = getdates(reports);
				if(dates!=null)
				{	
					for (String date : dates)
					{
						int seconds=0;
						for(SessionSummary s : reports)
						{
							if(date.equals(s.sessiondate))
							{
								seconds+=s.getMinutes()*60+s.getSeconds();
							}
						}
					message = message + date + " " + seconds/60+ "min " +seconds%60 + " seconden<br>";	
					}
				}
				else message = message + "Geen oefenmomenten bekend<br>";	
			}
		}
		
		return message;
}

	private ArrayList<String> getdates(ArrayList<SessionSummary> reports) 
	{
		int maximum=0;
		ArrayList<String>returndates = new ArrayList<String>();
		if(reports.size()==0)
		{
			return null;
		}
		String newdate = reports.get((reports.size()-1)).getSessiondate();
		returndates.add(newdate);
		if(reports.size()==1)
		{
			return returndates;
		}
		for(int i =reports.size()-1;i>0;i--)
		{
			if(reports.get(i-1).getSessiondate().equals(newdate))
			newdate = reports.get(i-1).getSessiondate();
			else
			{
				newdate=reports.get(i-1).getSessiondate();
				returndates.add(newdate);
				maximum++;
				if(maximum==2)
				{
					return returndates;
				}
			}
			
		}
	return returndates;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
