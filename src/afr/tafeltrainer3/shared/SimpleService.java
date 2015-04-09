package afr.tafeltrainer3.shared;
import afr.tafeltrainer3.client.events.DataEvent;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("simpleservice")

public interface SimpleService extends RemoteService {

	public DataEvent addParentsMailaddress(String username, String password, String emailaddress, boolean subscribed);
	
	public void submitSurveyResult(SurveyResult surveyresult);
	
	public void sendPw(String emailadress);
	
	public void sendAnotherVerificationMail(SuperUser superuser);
	
	DataEvent verifyMailadress(String parameter);
	
	DataEvent getSuperuserFeedback(int userid);
	
	DataEvent getUserFeedback(int userid);
	
	public void sendMail(SuperUser superuser);
	
	void superuserUpdatesUser(User user);
	
	void updateSuperUser(SuperUser superuser, String oldemail);
	
	DataEvent getProducts(int userid);
	
	void addProduct(int userid, Product product);
	
	DataEvent getGroupResults(SuperUser superuser);
	
	DataEvent retrieveSuperUser(String loginname, String passw);
	
	DataEvent addNewSuperUser(SuperUser superuser);
	
	DataEvent getGroup(SuperUser superuser);
	
	void deleteUser(User user);
	
	DataEvent addNewUser(User user);
	
	DataEvent retrieveUser(String loginname,String passw);
	
	User getUser(String loginname,String passw);
	
	void updateUser(User user) ;

	void startQuiz();
	
	void stopQuiz();
	
	void submitQuestion(Opgave opg,int userid);

	FeedbackContainer getFeedbackData(int id);

	

}
