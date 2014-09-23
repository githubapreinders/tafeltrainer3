package afr.tafeltrainer3.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import afr.tafeltrainer3.shared.SuperUser;

public class MailCronServlet extends HttpServlet
{


	private static final Logger _logger = Logger.getLogger(MailCronServlet.class.getName());
	private MySQLAccess mySQLAccess = new MySQLAccess();

	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
	{

		try
		{
			ArrayList<SuperUser> superusers = mySQLAccess.getSuperUserList(); 
			for(SuperUser user : superusers)
			{
				if(user.getEmailfrequency() == 1)
				{
					SendMessage message = new SendMessage(user);
				}
			}
			
			_logger.info("Cron Job 1W has been executed" );
			
			
			
			
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
