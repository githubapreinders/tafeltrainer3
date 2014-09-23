package afr.tafeltrainer3.shared;



import com.google.gwt.user.client.rpc.AsyncCallback;

public interface SimpleServiceAsync  {
	
	public void sendPw(String emailadress,@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void sendAnotherVerificationMail(SuperUser superuser,@SuppressWarnings("rawtypes") AsyncCallback callback);

	public void verifyMailadress(String parameter,@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void getSuperuserFeedback(int userid, @SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void getUserFeedback(int userid, @SuppressWarnings("rawtypes") AsyncCallback callback);
	
	void sendMail(SuperUser superuser, AsyncCallback callback);
	
	public void superuserUpdatesUser(User user, @SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void updateSuperUser(SuperUser superuser, String oldemail,	@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void getProducts(int userid, @SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void addProduct(int userid, Product product,@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void getGroupResults(SuperUser superuser,@SuppressWarnings("rawtypes") AsyncCallback callback);

	public void retrieveSuperUser(String loginname, String passw,	@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void addNewSuperUser(SuperUser superuser,@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void getGroup(SuperUser superuser,@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void addNewUser(User user, @SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void retrieveUser(String loginname,String passw, @SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void getFeedbackData(int id, @SuppressWarnings("rawtypes")AsyncCallback callback);
	
	public void getUser(String loginname,String passw, @SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void updateUser(User user,@SuppressWarnings("rawtypes") AsyncCallback callback) ;
	
	public void startQuiz(@SuppressWarnings("rawtypes") AsyncCallback callback);
	
	public void stopQuiz(@SuppressWarnings("rawtypes")AsyncCallback callback);
	
	public void submitQuestion(Opgave opg,int userid, @SuppressWarnings("rawtypes")AsyncCallback callback);

	public void deleteUser(User user,@SuppressWarnings("rawtypes")AsyncCallback callback);

	



	



	



}
