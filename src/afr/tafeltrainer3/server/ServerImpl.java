package afr.tafeltrainer3.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import afr.tafeltrainer3.client.events.DataEvent;
import afr.tafeltrainer3.client.events.EventAddSuperUser;
import afr.tafeltrainer3.client.events.EventGetGroup;
import afr.tafeltrainer3.client.events.EventGetGroupResults;
import afr.tafeltrainer3.client.events.EventProductsRetrieved;
import afr.tafeltrainer3.client.events.EventSuperUserRetrieved;
import afr.tafeltrainer3.client.events.EventSuperuserFeedback;
import afr.tafeltrainer3.client.events.EventUserFeedback;
import afr.tafeltrainer3.client.events.EventUserNew;
import afr.tafeltrainer3.client.events.EventUserRetrieved;
import afr.tafeltrainer3.client.events.EventVerifyMail;
import afr.tafeltrainer3.shared.FeedbackContainer;
import afr.tafeltrainer3.shared.Opgave;
import afr.tafeltrainer3.shared.Product;
import afr.tafeltrainer3.shared.SimpleService;
import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.TafelResult;
import afr.tafeltrainer3.shared.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;


public class ServerImpl extends RemoteServiceServlet implements SimpleService {

private static final long serialVersionUID = -8441122045657762850L;

private SessionSummary session;

private MySQLAccess mySQLAccess = new MySQLAccess();
	
	public ServerImpl() 
	{
		
	}

	@Override
	public void sendPw(String emailadress)
	{
		SuperUser su = mySQLAccess.findPw(emailadress);
		SendMessage me = new SendMessage(su.getName(), su.getEmail(), su.getPassword()); 
	}
	
	
	
	@Override
	public void sendAnotherVerificationMail(SuperUser superuser)
	{
		Encrypter encrypter = Encrypter.getInstance();
		String verificationcode = encrypter.encrypt(superuser.getName());
		String passw = encrypter.encrypt(superuser.getPassword());
		superuser.setVerificationcode(verificationcode);
		superuser.setPassword(passw);
		sendVerificationMail mail = new sendVerificationMail(superuser);
		mail.sendMail("apreinders74@gmail.com",superuser.getEmail(),"apreinders74@gmail.com",
					"Verificatie van uw e-mailadres",mail.createMessage());
	}
	
	@Override
	public DataEvent verifyMailadress(String parameter)
	{
		String returnstring = "Helaas, verificatie was niet mogelijk...";
		EventVerifyMail evm = new EventVerifyMail();
		try
		{
			Boolean verified = mySQLAccess.verifyMailadress(parameter);
			if(verified)
				returnstring = "succes";
			evm.setParameter(returnstring);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return evm;
	}

	
	@Override
	public DataEvent getSuperuserFeedback(int userid) 
	{
		EventSuperuserFeedback esuf = new EventSuperuserFeedback();
		try
		{
			ArrayList<TafelResult>tafelresultaten = new ArrayList<TafelResult>();
			int factor =2;
			while(factor < 20)
			{
				TafelResult tr = mySQLAccess.getTafelResults_1(userid,factor);
				tafelresultaten.add(tr);
				factor++;
			}
			SessionSummary pastsession = mySQLAccess.retrieveSession(userid);
			Date thisdate = new Date(pastsession.timestamp.getTime());
			String pastthreesessions = mySQLAccess.getSessionDatesHtmlString(userid);
			FeedbackContainer fiba = new FeedbackContainer((int)pastsession.howMuchOpgaven, pastsession.averageSpeed,
			thisdate,pastsession.sessionLength,(int)pastsession.getErrors(),tafelresultaten,pastthreesessions);
			Locale nederland = new Locale("nl");
			SimpleDateFormat sf = new SimpleDateFormat("EEEE dd MMM YYYY", nederland);
			String begindatum = sf.format(fiba.getBegindatum());
			fiba.setBegindatumstring(begindatum);
			esuf.setFiba(fiba);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return esuf;
	}

	@Override
	public DataEvent getUserFeedback(int userid) 
	{
		EventUserFeedback euf = new EventUserFeedback();
		try
		{
			ArrayList<TafelResult>tafelresultaten = new ArrayList<TafelResult>();
			int factor =2;
			while(factor < 20)
			{
				TafelResult tr = mySQLAccess.getTafelResults_1(userid,factor);
				tafelresultaten.add(tr);
				factor++;
			}
			SessionSummary pastsession = mySQLAccess.retrieveSession(userid);
			Date thisdate = new Date(pastsession.timestamp.getTime());
			String pastthreesessions = mySQLAccess.getSessionDatesHtmlString(userid);
			FeedbackContainer fiba = new FeedbackContainer((int)pastsession.howMuchOpgaven, pastsession.averageSpeed,
			thisdate,pastsession.sessionLength,(int)pastsession.getErrors(),tafelresultaten,pastthreesessions);
			Locale nederland = new Locale("nl");
			SimpleDateFormat sf = new SimpleDateFormat("EEEE dd MMM YYYY", nederland);
			String begindatum = sf.format(fiba.getBegindatum());
			fiba.setBegindatumstring(begindatum);
			euf.setFiba(fiba);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		return euf;
	}

	
	
	
	@Override
	public void sendMail(SuperUser superuser) 
	{
				SendMessage m = new SendMessage(superuser);
	}

	
	@Override
	public void superuserUpdatesUser(User user) 
	{
		try
		{
			mySQLAccess.superuserUpdatesUser(user);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
@Override
	public void updateSuperUser(SuperUser superuser, String oldemail) 
	{
		try
		{
			mySQLAccess.updateSuperUser(superuser, oldemail);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return;
	}



@Override
	public DataEvent getProducts(int userid) 
	{
	EventProductsRetrieved epr = new EventProductsRetrieved();
		try
		{
			epr.setProducts(mySQLAccess.getProducts(userid));
			return epr;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return epr;
	}



//registreert een "gekocht" product van de user
@Override
	public void addProduct(int userid, Product product) 
	{
		try
		{
			mySQLAccess.addProduct(userid,product);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}



//checkt validiteit van de hoofdgebruiker
@Override
	public DataEvent retrieveSuperUser(String loginname, String passw) 
	{
		EventSuperUserRetrieved ersu = new EventSuperUserRetrieved();
		try
		{
			SuperUser su = mySQLAccess.retrieveSuperUser(loginname, passw);
			ersu.setSuperuser(su);
			return ersu;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return ersu;
	}
//maakt een nieuwe hoofdgebruiker/superuser aan
@Override
	public DataEvent addNewSuperUser(SuperUser superuser) {
		EventAddSuperUser easu = new EventAddSuperUser();
		Encrypter encrypter = Encrypter.getInstance();
		String verificationcode = encrypter.encrypt(superuser.getName());
		String passw = encrypter.encrypt(superuser.getPassword());
		superuser.setVerificationcode(verificationcode);
		superuser.setPassword(passw);
		sendVerificationMail mail = new sendVerificationMail(superuser);
		mail.sendMail("apreinders74@gmail.com",superuser.getEmail(),"apreinders74@gmail.com",
					"Verificatie van uw e-mailadres",mail.createMessage());
		
		try
		{
			SuperUser su = mySQLAccess.addNewSuperUser(superuser);
			easu.setSuperuser(su);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return easu;
	}

//haalt de resultaten groepsgegevens uit de db
	public DataEvent getGroupResults(SuperUser superuser)
	{
		EventGetGroupResults eggr = new EventGetGroupResults();
		try
		{
			eggr.setUserresults(mySQLAccess.getMetaData(superuser));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return eggr;
	}


//haalt de naw groepsgegevens uit de db
@Override
	public DataEvent getGroup(SuperUser superuser) 
	{

	EventGetGroup egg = new EventGetGroup();
	try
	{
		egg.setUsers(mySQLAccess.getGroup(superuser));
		return egg;
	}
	catch(Exception e)
	{
		
	}
		return egg;
	}


//voegt een nieuwe user toe in de database;
@Override
public DataEvent addNewUser(User user) 
	{
	EventUserNew eun = new EventUserNew();
		try
		{
			User newuser =  mySQLAccess.addNewUser(user);
			eun.setUser(newuser);
			return eun;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return null;
}

//zoekt een user op bij een inlogpoging
@Override
public DataEvent retrieveUser(String loginname, String passw) {
	
	EventUserRetrieved eur = new EventUserRetrieved();
	try {
		mySQLAccess = new MySQLAccess();
		User user = mySQLAccess.getUser(loginname, passw);
		eur.setUser(user);
		return eur;
	} catch (Exception e) {
		e.printStackTrace();
	}
	return eur;
}

//haalt een user uit de db en deleted alle bijbehorende records in de db
@Override
	public void deleteUser(User user) 
	{
		if(user!=null)
		{
			mySQLAccess.deleteUser(user);
		}
	}

//maakt statistieken van de voorgaande sessies
@Override 
	public FeedbackContainer getFeedbackData(int id )
	{
		ArrayList<TafelResult>tafelresultaten = new ArrayList<TafelResult>();
		int factor =2;
		while(factor < 20)
		{
			TafelResult tr = mySQLAccess.getTafelResults_1(id,factor);
			tafelresultaten.add(tr);
			factor++;
		}
		SessionSummary pastsession = mySQLAccess.retrieveSession(id);
		Date thisdate = new Date(pastsession.timestamp.getTime());
		String pastthreesessions = mySQLAccess.getSessionDatesHtmlString(id);
		FeedbackContainer fiba = new FeedbackContainer((int)pastsession.howMuchOpgaven, pastsession.averageSpeed,
		thisdate,pastsession.sessionLength,(int)pastsession.getErrors(),tafelresultaten,pastthreesessions);
		Locale nederland = new Locale("nl");
		SimpleDateFormat sf = new SimpleDateFormat("EEEE dd MMM YYYY", nederland);
		String begindatum = sf.format(fiba.getBegindatum());
		fiba.setBegindatumstring(begindatum);
	return fiba;
	}



//checkt de user bij login en geeft onthouden gegevens mee terug
@Override
	public User getUser(String loginname, String passw) 
	{
		try {
			User user = mySQLAccess.getUser(loginname, passw);
			return user;
		} catch (Exception e) {
			e.printStackTrace();
		}
	return null;
	}

//houdt geld en bezittingen van de user bij
@Override
	public void updateUser(User user) 
	{
		if(user!=null)
		{
			mySQLAccess.updateUser(user);
		}
	}



//registreert gemaakte opgave en updatet de samenvatting van de sessie
@Override
	public void submitQuestion(Opgave opg,int userid) 
	{
		if (opg != null) 
		{
			mySQLAccess.writeOpgave(opg,userid);
			session.addOpgave();
			session.setAccuracy(opg.antwoord==opg.useranswer);
			session.setAvgSpeed(opg.getTime());
			session.setSessionLength();
			mySQLAccess.writeSession(session,userid);
			mySQLAccess.updateUserMetaData(userid);
		} 
		else 
		{
			System.out.println("opg = null in server");
		}
	return ;
	}
	
//maakt een nieuwe sessie aan voor een user
@Override
	public void startQuiz() 
	{
		session = new SessionSummary();
	return;
	}
	
//beeindigt de sessie
@Override
	public void stopQuiz() {
		try 
		{
			mySQLAccess.flag = 0;
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	return ;
	}
















}
