package afr.tafeltrainer3.server;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import afr.tafeltrainer3.client.events.DataEvent;
import afr.tafeltrainer3.client.events.EventAddParentsMailaddress;
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
import afr.tafeltrainer3.client.shop.Product;
import afr.tafeltrainer3.shared.FeedbackContainer;
import afr.tafeltrainer3.shared.Opgave;
import afr.tafeltrainer3.shared.SimpleService;
import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.SurveyResult;
import afr.tafeltrainer3.shared.TafelResult;
import afr.tafeltrainer3.shared.User;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ServerImpl extends RemoteServiceServlet implements SimpleService
{

	Logger logger = Logger.getLogger(ServerImpl.class.getSimpleName());
	
	private static final long serialVersionUID = -8441122045657762850L;

	private SessionSummary session;

	private MySQLAccess mySQLAccess = new MySQLAccess();

	public ServerImpl()
	{

	}

	
	
	
	@Override
	public DataEvent addParentsMailaddress(String username, String password, String emailaddress, boolean subscribed)
	{
		logger.log(Level.INFO,"addParentsMailaddress");

	String result = "";
	EventAddParentsMailaddress  eapma = new EventAddParentsMailaddress();
	try
	{
		result = mySQLAccess.addParentsMailaddress(username, password, emailaddress, subscribed);
		eapma.setReply(result);
	}
	catch(Exception e)
	{
		e.printStackTrace();
		eapma.setReply("failure");
	}
	return eapma;
	}
	
	
	
	
	@Override
	public void submitSurveyResult(SurveyResult surveyresult)
	{
		logger.log(Level.INFO,"submitSurveyResult");	
		try
		{
			boolean succes = mySQLAccess.submitSurveyResult(surveyresult);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void sendPw(String emailadress)
	{
		logger.log(Level.INFO,"sendPw");	

		SuperUser su = mySQLAccess.findPw(emailadress);
		SendMessage me = new SendMessage(su.getName(), su.getEmail(), su.getPassword());
	}

	@Override
	public void sendAnotherVerificationMail(SuperUser superuser)
	{
		logger.log(Level.INFO,"sendAnotherVerificationMail");	

		Encrypter encrypter = Encrypter.getInstance();
		String verificationcode = encrypter.encrypt(superuser.getName());
		String passw = encrypter.encrypt(superuser.getPassword());
		superuser.setVerificationcode(verificationcode);
		superuser.setPassword(passw);
		sendVerificationMail mail = new sendVerificationMail(superuser);
		mail.sendMail("apreinders74@gmail.com", superuser.getEmail(), "apreinders74@gmail.com",
				"Verificatie van uw e-mailadres", mail.createMessage());
	}

	@Override
	public DataEvent verifyMailadress(String parameter)
	{
		logger.log(Level.INFO,"verifyMailadress");	

		String returnstring = "Helaas, verificatie was niet mogelijk...";
		EventVerifyMail evm = new EventVerifyMail();
		try
		{
			Boolean verified = mySQLAccess.verifyMailadress(parameter);
			if (verified)
				returnstring = "succes";
			evm.setParameter(returnstring);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return evm;
	}

	@Override
	public DataEvent getSuperuserFeedback(int userid)
	{
		logger.log(Level.INFO,"getSuperuserFeedback");	

		EventSuperuserFeedback esuf = new EventSuperuserFeedback();
		try
		{
			ArrayList<TafelResult> tafelresultaten = new ArrayList<TafelResult>();
			int factor = 2;
			while (factor < 20)
			{
				TafelResult tr = mySQLAccess.getTafelResults_1(userid, factor);
				tafelresultaten.add(tr);
				factor++;
			}
			SessionSummary pastsession = mySQLAccess.retrieveSession(userid);
			Date thisdate = new Date(pastsession.timestamp.getTime());
			String pastthreesessions = mySQLAccess.getSessionDatesHtmlString(userid);
			FeedbackContainer fiba = new FeedbackContainer((int) pastsession.howMuchOpgaven, pastsession.averageSpeed,
					thisdate, pastsession.sessionLength, (int) pastsession.getErrors(), tafelresultaten,
					pastthreesessions);
			Locale nederland = new Locale("nl");
			SimpleDateFormat sf = new SimpleDateFormat("EEEE dd MMM YYYY", nederland);
			String begindatum = sf.format(fiba.getBegindatum());
			fiba.setBegindatumstring(begindatum);
			esuf.setFiba(fiba);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return esuf;
	}

	@Override
	public DataEvent getUserFeedback(int userid)
	
	{
		logger.log(Level.INFO,"getUserFeedback");	

		EventUserFeedback euf = new EventUserFeedback();
		try
		{
//			ArrayList<TafelResult> tafelresultaten = new ArrayList<TafelResult>();
//			int factor = 2;
//			while (factor < 20)
//			{
//				TafelResult tr = mySQLAccess.getTafelResults_1(userid, factor);
//				tafelresultaten.add(tr);
//				factor++;
//			}
			ArrayList<TafelResult> tafelresultaten = mySQLAccess.getTafelResults_3(userid);
			SessionSummary pastsession = mySQLAccess.retrieveSession(userid);
			Date thisdate = new Date(pastsession.timestamp.getTime());
			String pastthreesessions = mySQLAccess.getSessionDatesHtmlString(userid);
			FeedbackContainer fiba = new FeedbackContainer((int) pastsession.howMuchOpgaven, pastsession.averageSpeed,
					thisdate, pastsession.sessionLength, (int) pastsession.getErrors(), tafelresultaten,
					pastthreesessions);
			Locale nederland = new Locale("nl");
			SimpleDateFormat sf = new SimpleDateFormat("EEEE dd MMM YYYY", nederland);
			String begindatum = sf.format(fiba.getBegindatum());
			fiba.setBegindatumstring(begindatum);
			euf.setFiba(fiba);
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Serverimp getUserFeedback");
		}

		return euf;
	}

	@Override
	public void sendMail(SuperUser superuser)
	{
		logger.log(Level.INFO,"sendMail");	

		SendMessage m = new SendMessage(superuser);
	}

	@Override
	public void superuserUpdatesUser(User user)
	{
		logger.log(Level.INFO,"superuserUpdatesUser");	

		try
		{
			mySQLAccess.superuserUpdatesUser(user);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void updateSuperUser(SuperUser superuser, String oldemail)
	{
		logger.log(Level.INFO,"updateSuperUser");	
	
		try
		{
			mySQLAccess.updateSuperUser(superuser, oldemail);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return;
	}

	@Override
	public DataEvent getProducts(int userid)
	{
		logger.log(Level.INFO,"getProducts");	

		EventProductsRetrieved epr = new EventProductsRetrieved();
		try
		{
			epr.setProducts(mySQLAccess.getProducts(userid));
			return epr;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return epr;
	}

	// registreert een "gekocht" product van de user
	@Override
	public void addProduct(int userid, Product product)
	{
		logger.log(Level.INFO,"addProduct");	

		try
		{
			mySQLAccess.addProduct(userid, product);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	// checkt validiteit van de hoofdgebruiker
	@Override
	public DataEvent retrieveSuperUser(String loginname, String passw)
	{
		logger.log(Level.INFO,"retrieveSuperUser");	

		EventSuperUserRetrieved ersu = new EventSuperUserRetrieved();
		try
		{
			SuperUser su = mySQLAccess.retrieveSuperUser(loginname, passw);
			ersu.setSuperuser(su);
			return ersu;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return ersu;
	}

	// maakt een nieuwe hoofdgebruiker/superuser aan
	@Override
	public DataEvent addNewSuperUser(SuperUser superuser)
	{
		logger.log(Level.INFO,"addNewSuperUser");	

		EventAddSuperUser easu = new EventAddSuperUser();
		Encrypter encrypter = Encrypter.getInstance();
		String verificationcode = encrypter.encrypt(superuser.getName());
		String passw = encrypter.encrypt(superuser.getPassword());
		superuser.setVerificationcode(verificationcode);
		superuser.setPassword(passw);
		sendVerificationMail mail = new sendVerificationMail(superuser);
		mail.sendMail("apreinders74@gmail.com", superuser.getEmail(), "apreinders74@gmail.com",
				"Verificatie van uw e-mailadres", mail.createMessage());

		try
		{
			SuperUser su = mySQLAccess.addNewSuperUser(superuser);
			easu.setSuperuser(su);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return easu;
	}

	// haalt de resultaten groepsgegevens uit de db
	public DataEvent getGroupResults(SuperUser superuser)
	{
		logger.log(Level.INFO,"getGroupResults");	

		EventGetGroupResults eggr = new EventGetGroupResults();
		try
		{
			eggr.setUserresults(mySQLAccess.getMetaData(superuser));
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return eggr;
	}

	// haalt de naw groepsgegevens uit de db
	@Override
	public DataEvent getGroup(SuperUser superuser)
	{
		logger.log(Level.INFO,"getGroup");	

		EventGetGroup egg = new EventGetGroup();
		try
		{
			egg.setUsers(mySQLAccess.getGroup(superuser));
			return egg;
		} catch (Exception e)
		{

		}
		return egg;
	}

	// voegt een nieuwe user toe in de database;
	@Override
	public DataEvent addNewUser(User user)
	{
		logger.log(Level.INFO,"addNewUser");	

		EventUserNew eun = new EventUserNew();
		try
		{
			User newuser = mySQLAccess.addNewUser(user);
			eun.setUser(newuser);
			return eun;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	// zoekt een user op bij een inlogpoging
	@Override
	public DataEvent retrieveUser(String loginname, String passw)
	{
		logger.log(Level.INFO,"retrieveUser");	

		
		EventUserRetrieved eur = new EventUserRetrieved();
		try
		{
			mySQLAccess = new MySQLAccess();
			User user = mySQLAccess.getUser(loginname, passw);
			eur.setUser(user);
			return eur;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return eur;
	}

	// haalt een user uit de db en deleted alle bijbehorende records in de db
	@Override
	public void deleteUser(User user)
	{
		logger.log(Level.INFO,"deleteUser");	

		if (user != null)
		{
			mySQLAccess.deleteUser(user);
		}
	}

	// maakt statistieken van de voorgaande sessies
	@Override
	public FeedbackContainer getFeedbackData(int id)
	{
		logger.log(Level.INFO,"getFeedbackData");	

		ArrayList<TafelResult> tafelresultaten = mySQLAccess.getTafelResults_3(id);
//		ArrayList<TafelResult> tafelresultaten = new ArrayList<TafelResult>();
//		int factor = 2;
//		while (factor < 20)
//		{
//			TafelResult tr = mySQLAccess.getTafelResults_1(id, factor);
//			tafelresultaten.add(tr);
//			factor++;
//		}
		SessionSummary pastsession = mySQLAccess.retrieveSession(id);
		Date thisdate = new Date(pastsession.timestamp.getTime());
		String pastthreesessions = mySQLAccess.getSessionDatesHtmlString(id);
		FeedbackContainer fiba = new FeedbackContainer((int) pastsession.howMuchOpgaven, pastsession.averageSpeed,
				thisdate, pastsession.sessionLength, (int) pastsession.getErrors(), tafelresultaten, pastthreesessions);
		Locale nederland = new Locale("nl");
		SimpleDateFormat sf = new SimpleDateFormat("EEEE dd MMM YYYY", nederland);
		String begindatum = sf.format(fiba.getBegindatum());
		fiba.setBegindatumstring(begindatum);
		return fiba;
	}

	// checkt de user bij login en geeft onthouden gegevens mee terug
	@Override
	public User getUser(String loginname, String passw)
	{
		logger.log(Level.INFO,"getUser");	
	
		try
		{
			User user = mySQLAccess.getUser(loginname, passw);
			return user;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	// houdt geld en bezittingen van de user bij
	@Override
	public void updateUser(User user)
	{
		logger.log(Level.INFO,"updateUser");	
	
		if (user != null)
		{
			mySQLAccess.updateUser(user);
		}
	}

	// registreert gemaakte opgave en updatet de samenvatting van de sessie
	@Override
	public void submitQuestion(Opgave opg, int userid)
	{
		logger.log(Level.INFO,"submitQuestion");	

		if (opg != null)
		{
			mySQLAccess.writeOpgave(opg, userid);
			session.addOpgave();
			session.setAccuracy(opg.antwoord == opg.useranswer);
			session.setAvgSpeed(opg.getTime());
			session.setSessionLength();
			mySQLAccess.writeSession(session, userid);
			mySQLAccess.updateUserMetaData(userid);
		} else
		{
			System.out.println("opg = null in server");
		}
		return;
	}

	
	@Override
	public void submitOpgavenSet(ArrayList<Opgave> opgaven, int userid)
	{
		logger.log(Level.INFO,"submitOpgavenSet");	

		try
		{
			mySQLAccess.submitOpgavenSet(opgaven, userid);
			for(Opgave opg : opgaven)
			{
				session.addOpgave();
				session.setAccuracy(opg.antwoord == opg.useranswer);
				session.setAvgSpeed(opg.getTime());
				session.setSessionLength();
			}
			mySQLAccess.writeSession(session, userid);
			mySQLAccess.updateUserMetaData(userid);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public void emptyAndUpdateUser(ArrayList<Opgave> opgaven, int userid, int money)
	{
		logger.log(Level.INFO,"emptyAndUpdateUser");	
		if(session != null)
		{
		for(Opgave opg : opgaven)
		{
			session.addOpgave();
			session.setAccuracy(opg.antwoord == opg.useranswer);
			session.setAvgSpeed(opg.getTime());
			session.setSessionLength();
		}
		mySQLAccess.emptyAndUpdateUser(opgaven,session,userid, money);
		}
		session = new SessionSummary();
		mySQLAccess.flag = 0;
	}
	
	
	
	
	// maakt een nieuwe sessie aan voor een user
	@Override
	public void startQuiz()
	{
		logger.log(Level.INFO,"startQuiz");	

		session = new SessionSummary();
		return;
	}

	// beeindigt de sessie
	@Override
	public void stopQuiz()
	{
		logger.log(Level.INFO,"stopQuiz");	

		try
		{
			mySQLAccess.flag = 0;
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return;
	}

	
}
