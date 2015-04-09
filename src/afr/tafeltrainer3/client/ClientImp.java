package afr.tafeltrainer3.client;

import afr.tafeltrainer3.client.events.DataEvent;
import afr.tafeltrainer3.shared.FeedbackContainer;
import afr.tafeltrainer3.shared.Product;
import afr.tafeltrainer3.shared.SimpleService;
import afr.tafeltrainer3.shared.SimpleServiceAsync;
import afr.tafeltrainer3.shared.Opgave;
import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.SurveyResult;
import afr.tafeltrainer3.shared.User;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Composite;

public class ClientImp extends Composite implements ClientImpInterface
{

	private SimpleServiceAsync service;
	private MainView main;
	private Tafeltrainer3Gui gui;

	public ClientImp()
	{

	}

	public ClientImp(String url, MainView main)
	{
		// creeert een instantie van de server/client verbinding
		this.service = GWT.create(SimpleService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) this.service;
		endpoint.setServiceEntryPoint(url);
		this.main = main;

	}

	@Override
	public void addParentsMailaddress(String username, String password, String emailaddress, boolean subscribed)
	{
		this.service.addParentsMailaddress(username, password, emailaddress, subscribed, new DefaultCallBack());
	}

	
	
	
	public void submitSurveyResult(SurveyResult sr)
	{
		this.service.submitSurveyResult(sr , new DefaultCallBack());
		
	}

	
	
	
	@Override
	public void sendPw(String emailadress)
	{
		this.service.sendPw(emailadress, new DefaultCallBack());
	}

	@Override
	public void sendAnotherVerificationMail(SuperUser superuser)
	{
		this.service.sendAnotherVerificationMail(superuser, new DefaultCallBack());
	}

	@Override
	public void verifyMailadress(String parameter)
	{
		this.service.verifyMailadress(parameter, new DefaultCallBack());
	}

	@Override
	public void getSuperuserFeedback(int id)
	{
		this.service.getSuperuserFeedback(id, new DefaultCallBack());
	}

	@Override
	public void getUserFeedback(int id)
	{
		this.service.getUserFeedback(id, new DefaultCallBack());
	}

	@Override
	public void sendMail(SuperUser superuser)
	{
		this.service.sendMail(superuser, new DefaultCallBack());

	}

	public void superuserUpdatesUser(User user)
	{
		this.service.superuserUpdatesUser(user, new DefaultCallBack());
	}

	@Override
	public void updateSuperUser(SuperUser superuser, String oldemail)
	{
		this.service.updateSuperUser(superuser, oldemail, new DefaultCallBack());
	}

	@Override
	public void getProducts(int userid)
	{
		if (userid != 1)
			this.service.getProducts(userid, new DefaultCallBack());
	}

	@Override
	public void addProduct(int userid, Product product)
	{
		if (userid != 1)
			this.service.addProduct(userid, product, new DefaultCallBack());
	}

	@Override
	public void getGroupResults(SuperUser superuser)
	{
		this.service.getGroupResults(superuser, new DefaultCallBack());
	}

	@Override
	public void getSuperUser(String loginname, String passw)
	{
		this.service.retrieveSuperUser(loginname, passw, new DefaultCallBack());
	}

	@Override
	public void addNewSuperUser(SuperUser superuser)
	{
		this.service.addNewSuperUser(superuser, new DefaultCallBack());
	}

	@Override
	public void getGroup(SuperUser superuser)
	{
		this.service.getGroup(superuser, new DefaultCallBack());
	}

	@Override
	public void deleteUser(User user)
	{
		this.service.deleteUser(user, new DefaultCallBack());
	}

	@Override
	public void addNewUser(User user)
	{
		this.service.addNewUser(user, new DefaultCallBack());
	}

	@Override
	public void retrieveUser(String loginname, String passw)
	{
		this.service.retrieveUser(loginname, passw, new DefaultCallBack());
	}

	@Override
	public void getFeedbackData(int id)
	{
		this.service.getFeedbackData(id, new DefaultCallBack());
	}

	// haalt de user uit de database
	@Override
	public void getUser(String loginname, String passw)
	{
		this.service.getUser(loginname, passw, new DefaultCallBack());
	}

	// houdt de user die bezig is bij in de database door geldtoename of afname
	// te registreren.
	@Override
	public void updateUser(User user)
	{
		try
		{
			if (user.getId() != 1)
				this.service.updateUser(user, new DefaultCallBack());
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void startQuiz()
	{
		this.service.startQuiz(new DefaultCallBack());
	}

	@Override
	public void stopQuiz()
	{
		this.service.stopQuiz(new DefaultCallBack());

	}

	// registreert een gemaakte opgave
	@Override
	public void submitQuestion(Opgave opgave, int userid)
	{
		if (userid != 1)
			this.service.submitQuestion(opgave, userid, new DefaultCallBack());
		else
			main.gast.addOpgave(opgave);
	}

	// sluist de antwoorden van de server door
	@SuppressWarnings("rawtypes")
	private class DefaultCallBack implements AsyncCallback
	{

		@Override
		public void onFailure(Throwable caught)
		{
			System.out.print("foutje bedankt");
		}

		@Override
		public void onSuccess(Object result)
		{
			if (result instanceof DataEvent)
			{
				main.handleEvent((DataEvent) result);
			}

			// de vorige resultaten van de user uit de database
			if (result instanceof FeedbackContainer)
			{
				main.setFeedbackContainer((FeedbackContainer) result);
			}
		}
	}

	public Tafeltrainer3Gui getGui()
	{
		return this.gui;
	}

	
	
}
