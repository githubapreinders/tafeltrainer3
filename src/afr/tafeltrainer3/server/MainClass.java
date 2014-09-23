package afr.tafeltrainer3.server;

public class MainClass {

	  public static void main(String[] args) {
	    java.util.Date utilDate = new java.util.Date();
	    java.sql.Timestamp timestamp = new java.sql.Timestamp(utilDate.getTime());
	    System.out.println("utilDate:" + utilDate);
	    System.out.println("sqlDate:" + timestamp);

	  }

	}
