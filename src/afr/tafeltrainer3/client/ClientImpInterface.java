package afr.tafeltrainer3.client;


import afr.tafeltrainer3.shared.Opgave;
import afr.tafeltrainer3.shared.Product;
import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.User;


public interface ClientImpInterface {

	public void sendPw(String emailadress);
	
	public void sendAnotherVerificationMail(SuperUser superuser);
	
	public void verifyMailadress(String parameter);
	
	public void getSuperuserFeedback(int id);
	
	public void getUserFeedback(int id);
	
	public void sendMail(SuperUser superuser);
	
	public void superuserUpdatesUser(User user);
	
	public void updateSuperUser(SuperUser superUser, String oldemail);
	
	public void getProducts(int userid);
	
	public void addProduct(int userid, Product product);
	
	public void getGroupResults(SuperUser superuser);
	
	public void getSuperUser(String loginname, String passw);
	
	public void addNewSuperUser(SuperUser superuser);
	
	public void getGroup(SuperUser superuser);
	
	public void deleteUser(User user);
	
	public void addNewUser(User user);
	
	public void retrieveUser(String loginname,String passw);
	
	public void getFeedbackData(int id);
	
	public void getUser(String loginname, String passw);
	
	public void updateUser(User user);
	
	public void startQuiz();
	
	void stopQuiz();

	void submitQuestion(Opgave opgave, int userid);

}
