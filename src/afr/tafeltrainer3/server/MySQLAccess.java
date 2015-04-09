package afr.tafeltrainer3.server;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

import afr.tafeltrainer3.shared.Opgave;
import afr.tafeltrainer3.shared.Product;
import afr.tafeltrainer3.shared.SuperUser;
import afr.tafeltrainer3.shared.SurveyResult;
import afr.tafeltrainer3.shared.TafelResult;
import afr.tafeltrainer3.shared.User;
import afr.tafeltrainer3.shared.UserResults;

import com.google.appengine.api.utils.SystemProperty;

public class MySQLAccess
{
	private Connection connect;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	public int flag = 0;

	// private tafeltrainer3messages messages;

	public MySQLAccess()
	{
		// this.messages = GWT.create(tafeltrainer3messages.class);
	}

	private Connection getConn()
	{

		connect = null;
		String url = "";
		String db = "";
		String driver = "";
		String user = "";
		String pass = "";

		if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production)
		{
			url = "jdbc:google:mysql://subtle-reserve-547:mysql-instance/";
			driver = "com.mysql.jdbc.GoogleDriver";
			db = "mysql";
			user = "root";
		} else
		{
			url = "jdbc:mysql://localhost/";
			driver = "com.mysql.jdbc.Driver";
			db = "tafeltrainer";
			user = "root";
			pass = "Osiris74";
		}

		try
		{
			Class.forName(driver).newInstance();
		} catch (InstantiationException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		try
		{
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production)

				connect = DriverManager.getConnection(url + db + "?user=" + user);
			else
			{
				connect = DriverManager.getConnection(url + db, user, pass);
			}
		} catch (SQLException e)
		{
			System.err.println("Mysql Connection Error: ");
			e.printStackTrace();
		}
		return connect;
	}

	public SuperUser findPw(String emailadress)
	{
		SuperUser su = null;
		try
		{
			connect = getConn();
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.begeleider" + " where email = '"
					+ emailadress + "'");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				Random ran = new Random();
				int i = 1000 + ran.nextInt(9999);
				String password = String.valueOf(i);
				su = new SuperUser(resultSet.getString("name"), emailadress, password);
				Encrypter e = Encrypter.getInstance();
				String passw = e.encrypt(password);
				preparedStatement = connect.prepareStatement("update tafeltrainer.begeleider set " + "password = '"
						+ passw + "' where email = '" + emailadress + "'");
				i = preparedStatement.executeUpdate();
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return su;
	}

	public boolean submitSurveyResult(SurveyResult surveyresult)
	{
		boolean succes = false;
		try
		{
			connect = getConn();
			preparedStatement = connect
					.prepareStatement("insert into tafeltrainer.surveyresults (id,userid,stelling1,stelling2,stelling3,"
							+ "stelling4,stelling5,stelling6,stelling7,vraag8) values (default,?,?,?,?,?,?,?,?,?)");
			preparedStatement.setInt(1, surveyresult.getUserid());
			preparedStatement.setString(2, surveyresult.getVraag1());
			preparedStatement.setString(3, surveyresult.getVraag2());
			preparedStatement.setString(4, surveyresult.getVraag3());
			preparedStatement.setString(5, surveyresult.getVraag4());
			preparedStatement.setString(6, surveyresult.getVraag5());
			preparedStatement.setString(7, surveyresult.getVraag6());
			preparedStatement.setString(8, surveyresult.getVraag7());
			preparedStatement.setString(9, surveyresult.getVraag8());
			preparedStatement.executeUpdate();
			succes = true;
			preparedStatement = connect.prepareStatement("update tafeltrainer.user set hasdonesurvey = 1 where id = ?");
			preparedStatement.setInt(1, surveyresult.getUserid());
			preparedStatement.executeUpdate();

		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}

		return succes;
	}

	public boolean verifyMailadress(String code)
	{
		boolean returnvalue = false;
		try
		{
			connect = getConn();
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.begeleider"
					+ " where verificationcode = '" + code + "'");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
			{
				preparedStatement = connect.prepareStatement("update tafeltrainer.begeleider "
						+ " set verified = 1 where verificationcode = '" + code + "'");
				int geslaagd = preparedStatement.executeUpdate();
				if (geslaagd == 1)
				{
					returnvalue = true;
				}
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}

		return returnvalue;
	}

	public ArrayList<User> getHouindegatenUsers(SuperUser superuser)
	{
		ArrayList<User> users = new ArrayList<User>();
		try
		{
			connect = getConn();
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.user where"
					+ " emailsuperuser = '" + superuser.getEmail() + "'and houindegaten = 1");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				users.add(new User(resultSet.getInt("id"), resultSet.getString("emailsuperuser"), resultSet
						.getString("name"), resultSet.getString("familyname"), resultSet.getString("groupname"),
						resultSet.getString("loginname"), resultSet.getString("password"), resultSet.getInt("money"),
						resultSet.getBoolean("houindegaten")));
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return users;
	}

	public ArrayList<SessionSummary> getSessionDates(int userid)
	{
		ArrayList<SessionSummary> reports = new ArrayList<SessionSummary>();
		connect = getConn();
		try
		{
			preparedStatement = connect.prepareStatement("select summary,minutes, seconds,howmuchopgaven,howmucherrors"
					+ " from tafeltrainer.sessie where userid = " + userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				reports.add(new SessionSummary(resultSet.getString("summary"), resultSet.getInt("howmuchopgaven"),
						resultSet.getInt("howmucherrors"), resultSet.getInt("minutes"), resultSet.getInt("seconds")));
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return reports;
	}

	public String getSessionDatesHtmlString(int userid)
	{
		String message = "<br>";
		ArrayList<SessionSummary> reports = getSessionDates(userid);
		User u = getUserById(userid);
		message = message + "Laatst bekende oefendata: <br><br>";
		ArrayList<String> dates = getdates(reports);
		if (dates != null)
		{
			for (String date : dates)
			{
				int seconds = 0;
				for (SessionSummary s : reports)
				{
					if (date.equals(s.sessiondate))
					{
						seconds += s.getMinutes() * 60 + s.getSeconds();
					}
				}
				message = message + date + " " + seconds / 60 + " min " + seconds % 60 + " seconden<br>";
			}
		} else
			message = message + "<i>Geen oefenmomenten bekend</i><br>";
		return message + "<br><br><br>";
	}

	private ArrayList<String> getdates(ArrayList<SessionSummary> reports)
	{
		int maximum = 0;
		ArrayList<String> returndates = new ArrayList<String>();
		if (reports.size() == 0)
		{
			return null;
		}
		String newdate = reports.get((reports.size() - 1)).getSessiondate();
		returndates.add(newdate);
		if (reports.size() == 1)
		{
			return returndates;
		}
		for (int i = reports.size() - 1; i > 0; i--)
		{
			if (reports.get(i - 1).getSessiondate().equals(newdate))
				newdate = reports.get(i - 1).getSessiondate();
			else
			{
				newdate = reports.get(i - 1).getSessiondate();
				returndates.add(newdate);
				maximum++;
				if (maximum == 2)
				{
					return returndates;
				}
			}
		}
		return returndates;
	}

	// TODO foutmelding nullpointerexception
	public ArrayList<Product> getProducts(int userid)
	{
		connect = getConn();
		ArrayList<Product> productlist = new ArrayList<Product>();
		productlist.add(new Product());
		try
		{
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.product " + " where userid = "
					+ userid);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				Product p = new Product(resultSet.getString("url"), resultSet.getString("message"),
						resultSet.getString("productcatg"));
				productlist.add(p);
			}
			productlist.remove(0);
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (SQLException e)
		{
			System.out.println("fout in getproducts");
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return productlist;
	}

	public void addProduct(int userid, Product product)
	{
		connect = getConn();
		try
		{
			preparedStatement = connect.prepareStatement("insert into tafeltrainer.product"
					+ " (id, url, message, userid,productcatg) values (default,?,?,?,?)");
			preparedStatement.setString(1, product.getUrl());
			preparedStatement.setString(2, product.getMessage());
			preparedStatement.setInt(3, userid);
			preparedStatement.setString(4, String.valueOf(product.getProductcat()));
			preparedStatement.executeUpdate();
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("fout in addproduct");

		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
	}

	public SuperUser retrieveSuperUser(String email, String password)
	{
		connect = getConn();
		Encrypter encrypter = Encrypter.getInstance();
		String passw = encrypter.encrypt(password);
		SuperUser resultuser = null;
		try
		{
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.begeleider where" + " password ='"
					+ passw + "'and email ='" + email + "'");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				resultuser = new SuperUser(resultSet.getString("name"), resultSet.getString("email"), password,
						resultSet.getInt("emailfrequency"), resultSet.getBoolean("verified"));
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fout in getuser of superuser onbekend");
			return resultuser;
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return resultuser;
	}

	public ArrayList<SuperUser> getSuperUserList()
	{
		connect = getConn();
		ArrayList<SuperUser> resultusers = new ArrayList<SuperUser>();
		try
		{
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.begeleider");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				resultusers.add(new SuperUser(resultSet.getString("name"), resultSet.getString("email"), resultSet
						.getString("password"), resultSet.getInt("emailfrequency")));
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fout in getuser of superuser onbekend");
			return resultusers;
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return resultusers;
	}

	public SuperUser addNewSuperUser(SuperUser superuser)
	{
		connect = getConn();
		SuperUser resultuser = null;
		try
		{
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.begeleider where" + " email ='"
					+ superuser.getEmail() + "'");
			resultSet = preparedStatement.executeQuery();
			resultSet.first();
			if (resultSet.getRow() > 0)
			{
				if (resultSet.getRow() == 1)
				{
					resultuser = new SuperUser("Emailadres bestaat al", "Of nog niet geverifieerd");
					preparedStatement.close();
					resultSet.close();
					connect.close();
					return resultuser;
				}
				resultuser = new SuperUser("Emailadres bestaat al", "Of nog niet geverifieerd");
				return resultuser;
			}
			preparedStatement = connect
					.prepareStatement("insert into tafeltrainer.begeleider "
							+ "(id,name,email,password,emailfrequency,sendadress,verificationcode,verified) values (default,?,?,?,?,?,?,default)");
			preparedStatement.setString(1, superuser.getName());
			preparedStatement.setString(2, superuser.getEmail());
			preparedStatement.setString(3, superuser.getPassword());
			preparedStatement.setInt(4, superuser.getEmailfrequency());
			preparedStatement.setString(5, superuser.getEmail());
			preparedStatement.setString(6, superuser.getVerificationcode());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();

		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fout in addnew superuser...");
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return superuser;
	}

	public void updateUserMetaData(int id)
	{
		connect = getConn();

		try
		{
			int howmuchopgaven = 0;
			int errors = 0;
			preparedStatement = connect.prepareStatement("select howmuchopgaven,howmucherrors from tafeltrainer.sessie"
					+ " where userid =" + id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				howmuchopgaven = howmuchopgaven + resultSet.getInt("howmuchopgaven");
				errors += resultSet.getInt("howmucherrors");
			}
			preparedStatement.clearParameters();
			double percentage = 0;
			if (howmuchopgaven > 0)
			{
				percentage = round(((double) (howmuchopgaven - errors) * 100) / (double) howmuchopgaven, 1);
			}
			double averagespeed = 0;

			preparedStatement = connect.prepareStatement("select howmuchopgaven,averagespeed from tafeltrainer.sessie"
					+ " where userid = " + id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				averagespeed += (resultSet.getDouble("averagespeed") * ((double) resultSet.getInt("howmuchopgaven") / (double) howmuchopgaven));
			}
			averagespeed = round(averagespeed, 1);
			preparedStatement.clearParameters();

			preparedStatement = connect
					.prepareStatement("update tafeltrainer.summaryres set howmuchopgaven = ?,accuracy = ?,"
							+ " speed = ? where userid = " + id);
			preparedStatement.setInt(1, howmuchopgaven);
			preparedStatement.setDouble(2, percentage);
			preparedStatement.setDouble(3, averagespeed);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("updateusermetadata");

		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
	}

	public ArrayList<User> getGroup(SuperUser superuser)
	{
		connect = getConn();
		ArrayList<User> users = new ArrayList<User>();
		User resultuser = null;
		users.add(resultuser);
		try
		{
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.user where" + " emailsuperuser='"
					+ superuser.getEmail() + "'");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				resultuser = new User(resultSet.getInt("id"), resultSet.getString("emailsuperuser"),
						resultSet.getString("name"), resultSet.getString("familyname"),
						resultSet.getString("groupname"), resultSet.getString("loginname"),
						resultSet.getString("password"), resultSet.getInt("money"),
						resultSet.getBoolean("houindegaten"));
				users.add(resultuser);
			}
			users.remove(0);
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fout in getgroup");
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return users;
	}

	public void deleteUser(User user)
	{
		connect = getConn();
		try
		{
			preparedStatement = connect.prepareStatement("delete from tafeltrainer.sessie" + " where userid ="
					+ user.getId());
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement("delete from tafeltrainer.opgave" + " where userid ="
					+ user.getId());
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement("delete from tafeltrainer.product" + " where userid ="
					+ user.getId());
			preparedStatement.executeUpdate();
			preparedStatement = connect.prepareStatement(" delete from tafeltrainer.summaryres" + " where id="
					+ user.getId());
			preparedStatement.executeUpdate();
			preparedStatement = connect
					.prepareStatement(" delete from tafeltrainer.user" + " where id=" + user.getId());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
	}

	// maakt een nieuwe user aan
	public User addNewUser(User user)
	{
		int lastrow = -1;
		connect = getConn();
		try
		{
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.user" + " where loginname='"
					+ user.getLoginname() + "'and password='" + user.getPassword() + "'");
			resultSet = preparedStatement.executeQuery();
			if (resultSet.getRow() != 0)
			{
				preparedStatement.close();
				connect.close();
				return user;
			}
			resultSet.close();

			preparedStatement = connect.prepareStatement("insert into tafeltrainer.user"
					+ "(id,emailsuperuser,name,familyname,groupname,loginname,password,money,houindegaten)"
					+ "values (default,?,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, user.getEmailsuperuser());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getFamilyname());
			preparedStatement.setString(4, user.getGroupname());
			preparedStatement.setString(5, user.getLoginname());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setInt(7, user.getMoney());
			preparedStatement.setBoolean(8, user.getHouindegaten());
			preparedStatement.executeUpdate();

			preparedStatement = connect.prepareStatement("select * from tafeltrainer.user where id = "
					+ " all(select max(id)from tafeltrainer.user)");
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			lastrow = resultSet.getInt("id");
			preparedStatement.close();
			resultSet.close();
			connect.close();

			connect = getConn();
			preparedStatement = connect.prepareStatement("insert into tafeltrainer.summaryres (id,username,familyname"
					+ " ,howmuchopgaven,accuracy,speed,emailsuperuser,userid) values (default,?,?,?,?,?,?,?)");
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getFamilyname());
			preparedStatement.setInt(3, 0);
			preparedStatement.setDouble(4, 0.0);
			preparedStatement.setDouble(5, 0.0);
			preparedStatement.setString(6, user.getEmailsuperuser());
			preparedStatement.setInt(7, lastrow);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("fout in addNewUser");
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return new User(lastrow, user.getEmailsuperuser(), user.getName(), user.getFamilyname(), user.getGroupname(),
				user.getLoginname(), user.getPassword(), 0, user.getHouindegaten());
	}

	public ArrayList<UserResults> getMetaData(SuperUser superuser)
	{
		ArrayList<UserResults> userresults = new ArrayList<UserResults>();
		connect = getConn();
		try
		{
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.summaryres"
					+ " where emailsuperuser = '" + superuser.getEmail() + "'");
			resultSet = preparedStatement.executeQuery();
			UserResults dummy = new UserResults();
			dummy = null;
			userresults.add(dummy);

			while (resultSet.next())
			{
				UserResults ur = new UserResults(resultSet.getInt("userid"), resultSet.getString("username"),
						resultSet.getString("familyname"), resultSet.getDouble("accuracy"),
						resultSet.getInt("howmuchopgaven"), resultSet.getDouble("speed"));
				userresults.add(ur);
			}
			userresults.remove(0);
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return userresults;
	}

	public String addParentsMailaddress(String username, String password, String parentsemail,boolean subscribe)
	{
		String result = "mail added";
		connect = getConn();
		try
		{
		if(subscribe == false)
		{
			parentsemail = "0";
			result = "mail removed";
		}
		preparedStatement = connect.prepareStatement("update tafeltrainer.user set emailparent = ? where loginname = ?"
				+ " and password = ? ");
		preparedStatement.setString(1, parentsemail);
		preparedStatement.setString(2, username);
		preparedStatement.setString(3, password);
		preparedStatement.executeUpdate();
		preparedStatement.close();
		connect.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
			result = "failure";
		}
		finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return result;
	}
	
	public UserResults getUserMetaData(User user)
	{
		UserResults ur = null;
		connect = getConn();
		try
		{
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.summaryres"
					+ " where userid =  " + user.getId() );
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next())
			{
				 ur = new UserResults(resultSet.getInt("userid"), resultSet.getString("username"),
						resultSet.getString("familyname"), resultSet.getDouble("accuracy"),
						resultSet.getInt("howmuchopgaven"), resultSet.getDouble("speed"));
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return ur;
	}

	
	
	
	/**
	 * berekent het foutpercentage voor een bepaalde tafel en retourneert het
	 * tafelresultaat-object
	 * 
	 * @param id
	 */
	public TafelResult getTafelResults_1(int id, int factor)
	{
		TafelResult tr = new TafelResult();
		try
		{
			int score = 0;
			int aantalopgaven = 0;
			connect = getConn();

			preparedStatement = connect.prepareStatement("select factor2,iscorrect,userid from tafeltrainer.opgave "
					+ " where factor2 =" + factor + " and userid =" + id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				if (resultSet.getBoolean("iscorrect"))
				{
					score++;
				}
				aantalopgaven++;
			}
			// preparedStatement.clearParameters();
			double percentage = 0;
			if (aantalopgaven > 0)
			{
				percentage = ((double) score * 100) / (double) aantalopgaven;
				tr = new TafelResult(factor, (int) percentage, aantalopgaven);
			} else
			{
				tr = new TafelResult(factor, 0, 0);
			}
			// preparedStatement.close();
			connect.close();
		} catch (Exception e)
		{
			System.out.println("fout in gettafelresults_1");
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}

		return tr;
	}

	/**
	 * berekent het foutpercentage voor een bepaalde tafel, en stopt de
	 * resultaten per tafeltype in een ArrayList
	 * 
	 * @param id
	 */
	public ArrayList<TafelResult> getTafelResults(int id)
	{
		connect = getConn();
		ArrayList<TafelResult> tafelresultaten = new ArrayList<TafelResult>();
		try
		{

			int factor = 0;
			for (factor = 2; factor < 20; factor++)
			{
				int score = 0;
				int aantalopgaven = 0;
				preparedStatement = connect
						.prepareStatement("select factor2,iscorrect,userid from tafeltrainer.opgave "
								+ " where factor2 =" + factor + " and userid =" + id);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next())
				{
					if (resultSet.getBoolean("iscorrect"))
					{
						score++;
					}
					aantalopgaven++;
				}
				preparedStatement.clearParameters();
				double percentage = 0;
				if (aantalopgaven > 0)
				{
					percentage = ((double) score * 100) / (double) aantalopgaven;
					TafelResult tr = new TafelResult(factor, (int) percentage, aantalopgaven);
					tafelresultaten.add(tr);
				} else
				{
					TafelResult tr = new TafelResult(factor, (int) percentage, aantalopgaven);
					tafelresultaten.add(tr);
				}
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		}

		catch (Exception e)
		{
			System.out.println("fout in gettafelresults");
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return tafelresultaten;
	}

	/**
	 * Berekent het aantal fouten
	 * 
	 * @param id
	 */
	public int getErrors(int id)
	{
		connect = getConn();
		int errors = 0;
		try
		{
			preparedStatement = connect.prepareStatement("select howmucherrors from tafeltrainer.sessie"
					+ " where userid =" + id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				errors += resultSet.getInt("howmucherrors");
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			System.out.println("fout in geterrors");
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return errors;
	}

	/**
	 * Berekent de gemiddelde snelheid over alle sessies.
	 * 
	 * @param id
	 */
	public double getAverageSpeed(int totalopgaven, int id)
	{
		connect = getConn();
		double averagespeed = 0;
		try
		{
			preparedStatement = connect.prepareStatement("select howmuchopgaven,averagespeed from tafeltrainer.sessie"
					+ " where userid = " + id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				averagespeed += (resultSet.getDouble("averagespeed") * ((double) resultSet.getInt("howmuchopgaven") / (double) totalopgaven));
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();

		} catch (Exception e)
		{
			System.out.println("foutje in getaveragespeed");
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return averagespeed;
	}

	/**
	 * Telt van alle sessies de totale hoeveelheid opgaven bij elkaar op
	 * 
	 * @param id
	 * 
	 */
	public int getHowMuchOpgaven(int id)
	{
		connect = getConn();
		int result = 0;
		try
		{
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.sessie" + " where userid =" + id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				result = result + resultSet.getInt("howmuchopgaven");
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (SQLException s)
		{
			System.out.println("ergens iets fout...in howmuchopgaven");
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return result;
	}

	// werkt gebruikersgegevens van een begeleider bij
	public void updateSuperUser(SuperUser superuser, String oldemail)
	{
		connect = getConn();
		Encrypter encrypt = Encrypter.getInstance();
		String passw = encrypt.encrypt(superuser.getPassword());
		try
		{
			/**
			 * checkt eerst of het nieuwe emailadres al niet in de lijst staat,
			 * daarna of het emailadres is veranderd ten opzichte van wat er al
			 * stond; zo ja, moeten de gegevens van zijn users ook bijgewerkt
			 * worden want dit emailadres verbindt steeds een user met zijn
			 * begeleider.
			 */
			if (superuser.getEmail() != oldemail)
			{
				preparedStatement = connect.prepareStatement("select email from tafeltrainer.begeleider ");
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next())
				{
					if (resultSet.getString("email").equals(superuser.getEmail()))
					{
						return;
					}
				}
				preparedStatement = connect.prepareStatement("update tafeltrainer.user set"
						+ " emailsuperuser = ? where emailsuperuser = '" + oldemail + "'");
				preparedStatement.setString(1, superuser.getEmail());
				preparedStatement.executeUpdate();
				preparedStatement.close();
				preparedStatement = connect.prepareStatement("update tafeltrainer.summaryres"
						+ " set emailsuperuser = ? where emailsuperuser ='" + oldemail + "'");
				preparedStatement.setString(1, superuser.getEmail());
				preparedStatement.executeUpdate();
				preparedStatement.close();
			}
			preparedStatement = connect
					.prepareStatement("update tafeltrainer.begeleider set "
							+ " name = ? , email = ?, password = ? , emailfrequency = ?  " + "where email = '"
							+ oldemail + "'");
			preparedStatement.setString(1, superuser.getName());
			preparedStatement.setString(2, superuser.getEmail());
			preparedStatement.setString(3, passw);
			preparedStatement.setInt(4, superuser.getEmailfrequency());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();
		}

		catch (Exception e)
		{
			System.out.println("fout in updatesuperuser");
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
	}

	/**
	 * schrijft de veranderingen van een user bij in de database. bijv geld of
	 * gekochte goederen.
	 */
	public void updateUser(User user)
	{
		connect = getConn();
		try
		{
			preparedStatement = connect.prepareStatement("update tafeltrainer.user set emailsuperuser=?,name=?,"
					+ "familyname=? ,groupname=? ,loginname=? ,money=?,houindegaten=? where id = " + user.getId());
			preparedStatement.setString(1, user.getEmailsuperuser());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getFamilyname());
			preparedStatement.setString(4, user.getGroupname());
			preparedStatement.setString(5, user.getLoginname());
			preparedStatement.setInt(6, user.getMoney());
			preparedStatement.setBoolean(7, user.getHouindegaten());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();
		}

		catch (Exception e)
		{
			System.out.println("fout in updateuser");
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
	}

	public void superuserUpdatesUser(User user)
	{
		connect = getConn();

		try
		{
			preparedStatement = connect.prepareStatement("update tafeltrainer.user set emailsuperuser=?,name=?,"
					+ "familyname=? ,groupname=? ,loginname=? ,money=?,houindegaten=? where id = " + user.getId());
			preparedStatement.setString(1, user.getEmailsuperuser());
			preparedStatement.setString(2, user.getName());
			preparedStatement.setString(3, user.getFamilyname());
			preparedStatement.setString(4, user.getGroupname());
			preparedStatement.setString(5, user.getLoginname());
			preparedStatement.setInt(6, user.getMoney());
			preparedStatement.setBoolean(7, user.getHouindegaten());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();
			connect = getConn();
			preparedStatement = connect.prepareStatement("update tafeltrainer.summaryres set"
					+ " username = ?,familyname = ? where userid =" + user.getId());
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getFamilyname());
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();

		} catch (Exception e)
		{
			System.out.println("fout in administratorUpdatesUser");
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
	}

	public void writeOpgave(Opgave opgave, int userid)
	{
		connect = getConn();
		try
		{
			preparedStatement = connect.prepareStatement("insert into tafeltrainer.opgave (id, factor1, factor2, "
					+ "antwoord, useranswer,iscorrect, time,userid) values (default,?,?,?,?,?,?,?)");
			preparedStatement.setInt(1, opgave.getFactor1());
			preparedStatement.setInt(2, opgave.getFactor2());
			preparedStatement.setInt(3, opgave.getAntwoord());
			preparedStatement.setInt(4, opgave.getUseranswer());
			preparedStatement.setBoolean(5, opgave.iscorrect);
			preparedStatement.setDouble(6, opgave.getTime());
			preparedStatement.setInt(7, userid);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connect.close();

		}

		catch (Exception e)
		{
			System.out.println("fout in writeopgave");
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}

	}

	public SessionSummary retrieveSession(int userid)
	{
		SessionSummary session = new SessionSummary();
		connect = getConn();
		try
		{
			Timestamp begindatum = null;
			String oefentijd = "";
			int hours = 0;
			int minutes = 0;
			int seconds = 0;
			int result = 0;
			int errors = 0;
			double averagespeed = 0;
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.sessie" + " where userid ="
					+ userid);
			resultSet = preparedStatement.executeQuery();
			resultSet.first();
			if (resultSet.getRow() > 0)
			{
				while (resultSet.next())
				{
					result = result + resultSet.getInt("howmuchopgaven");
				}
				resultSet.beforeFirst();
				while (resultSet.next())
				{
					hours += resultSet.getInt("hours");
					minutes += resultSet.getInt("minutes");
					seconds += resultSet.getInt("seconds");
					errors += resultSet.getInt("howmucherrors");
					averagespeed += (resultSet.getDouble("averagespeed") * ((double) resultSet.getInt("howmuchopgaven") / (double) result));
				}

				resultSet.first();
				begindatum = resultSet.getTimestamp("starttijd");
				session.setTimestamp(begindatum);
				minutes += seconds / 60;
				seconds = seconds % 60;
				hours += minutes / 60;
				minutes = minutes % 60;
				oefentijd = String.valueOf(hours) + " uur," + String.valueOf(minutes) + " minuten";
				session.setSessionLength(oefentijd);
				session.setHowMuchOpgaven((double) result);
				session.setErrors(errors);
				session.setAverageSpeed(averagespeed);
			}

			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			System.out.println("fout in retrieve session");
			e.printStackTrace();
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return session;
	}

	public void writeSession(SessionSummary session, int userid)
	{
		connect = getConn();

		if (flag == 0)
			try
			{
				preparedStatement = connect
						.prepareStatement("insert into tafeltrainer.sessie (id,starttijd,summary,hours,"
								+ "minutes,seconds,howmuchopgaven,howmucherrors,accuracy,averagespeed,userid)	 "
								+ "values (default,?,?,?,?,?,?,?,?,?,?)");
				preparedStatement.setTimestamp(1, session.timestamp);
				preparedStatement.setString(2, session.toString());
				preparedStatement.setInt(3, session.hours);
				preparedStatement.setInt(4, session.minutes);
				preparedStatement.setInt(5, session.seconds);
				preparedStatement.setInt(6, (int) session.howMuchOpgaven);
				preparedStatement.setInt(7, (int) session.fouten);
				preparedStatement.setDouble(8, session.accuracy);
				preparedStatement.setDouble(9, session.averageSpeed);
				preparedStatement.setInt(10, userid);
				preparedStatement.executeUpdate();
				flag = 1;
				preparedStatement.close();
				connect.close();
				return;

			} catch (Exception e)
			{
				System.out.println("fout in writesession");
				e.printStackTrace();
			} finally
			{
				if (connect != null)
					try
					{
						connect.close();
					} catch (SQLException e)
					{
						e.printStackTrace();
					}
			}

		if (flag == 1)
			try
			{
				preparedStatement = connect
						.prepareStatement("update tafeltrainer.sessie set summary = ?,hours = ?, minutes = ?,"
								+ "seconds = ?, howmuchopgaven = ?, howmucherrors = ?, accuracy = ?,"
								+ "averagespeed = ?" + "order by id desc limit 1");
				preparedStatement.setString(1, session.toString());
				preparedStatement.setInt(2, session.hours);
				preparedStatement.setInt(3, session.minutes);
				preparedStatement.setInt(4, session.seconds);
				preparedStatement.setInt(5, (int) session.howMuchOpgaven);
				preparedStatement.setInt(6, (int) session.fouten);
				preparedStatement.setDouble(7, session.accuracy);
				preparedStatement.setDouble(8, session.averageSpeed);
				preparedStatement.executeUpdate();
				preparedStatement.close();
				connect.close();
			}

			catch (Exception e)
			{
				System.out.println("fout in writesession");
				e.printStackTrace();
			} finally
			{
				if (connect != null)
					try
					{
						connect.close();
					} catch (SQLException e)
					{
						e.printStackTrace();
					}
			}
	}

	public User getUser(String loginname, String password)
	{
		connect = getConn();
		User resultuser = null;
		try
		{
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.user where" + " loginname='"
					+ loginname + "'and password='" + password + "'");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				resultuser = new User(resultSet.getInt("id"), resultSet.getString("emailsuperuser"),
						resultSet.getString("name"), resultSet.getString("familyname"),
						resultSet.getString("groupname"), resultSet.getString("loginname"),
						resultSet.getString("password"), resultSet.getInt("money"),
						resultSet.getBoolean("houindegaten"), resultSet.getBoolean("hasdonesurvey"));
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			System.out.println("fout in getuser of user onbekend");
			return resultuser;
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return resultuser;
	}

	public ArrayList<User> getUserEmailList()
	{
		ArrayList<User> users = new ArrayList<User>();
		connect = getConn();
		try
		{
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.user");
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				if (!(resultSet.getString("emailparent").equals("0")))
				{
					User resultuser = null;
					resultuser = new User(resultSet.getInt("id"), resultSet.getString("emailsuperuser"),
							resultSet.getString("name"), resultSet.getString("familyname"),
							resultSet.getString("groupname"), resultSet.getString("loginname"),
							resultSet.getString("password"), resultSet.getInt("money"),
							resultSet.getBoolean("houindegaten"), resultSet.getBoolean("hasdonesurvey"));
					resultuser.setEmailparent(resultSet.getString("emailparent"));
					users.add(resultuser);
				}
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			System.out.println("fout in getuseremaillist of user onbekend");
			return users;
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return users;

	}

	public User getUserById(int id)
	{
		connect = getConn();
		User resultuser = null;
		try
		{
			preparedStatement = connect.prepareStatement("select * from tafeltrainer.user where" + " id =" + id);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next())
			{
				resultuser = new User(resultSet.getInt("id"), resultSet.getString("emailsuperuser"),
						resultSet.getString("name"), resultSet.getString("familyname"),
						resultSet.getString("groupname"), resultSet.getString("loginname"),
						resultSet.getString("password"), resultSet.getInt("money"),
						resultSet.getBoolean("houindegaten"));
			}
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			System.out.println("fout in getuserById of user onbekend");
			return resultuser;
		} finally
		{
			if (connect != null)
				try
				{
					connect.close();
				} catch (SQLException e)
				{
					e.printStackTrace();
				}
		}
		return resultuser;
	}

	public void closeAll()
	{
		try
		{
			preparedStatement.close();
			resultSet.close();
			connect.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static double round(double value, int places)
	{
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(places, BigDecimal.ROUND_HALF_UP);
		return bd.doubleValue();
	}

}