package afr.tafeltrainer3.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.User;

public class MailCronServletParents extends HttpServlet
{


	private static final Logger _logger = Logger.getLogger(MailCronServletParents.class.getName());
	private MySQLAccess mySQLAccess = new MySQLAccess();

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{

		try
		{
			ArrayList<User> users = mySQLAccess.getUserEmailList(); 
			for(User user : users)
			{
					SendMessage message = new SendMessage(user);
					_logger.info("Cron Job parent has been executed, sent to: " + user.getEmailparent());
			}
			
		} catch (Exception ex)
		{
			// Log any exceptions in your Cron Job
			_logger.warning("Cron job failed, please contact me...");
		}
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doGet(req, resp);
	}
}
