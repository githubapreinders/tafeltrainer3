package afr.tafeltrainer3.client;
import java.util.*;
import java.text.DateFormat;

public class DateUtils
{
	static final int MILLS_IN_DAY = 24 * 60 * 60 * 1000;

	public static void main(String args[])

	{
		GregorianCalendar currentGC = new GregorianCalendar();
		int currentYear = currentGC.get(Calendar.YEAR);
		Date currentDate = getCurrentDate();
		Date christmas = createDate(currentYear, Calendar.DECEMBER, 25);
		int daysToChristmas = daysDiff(currentDate, christmas);
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG);
		String formattedDate = dateFormat.format(currentDate);

		System.out.println("Today is " + formattedDate);
		System.out.println("There are " + daysToChristmas + " days until Christmas.");
	}


	public static Date getCurrentDate()
	{
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.set(Calendar.HOUR, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTime();
	}

	public static Date stripTime(Date date)
	{
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.setTime(date);
		currentDate.set(Calendar.HOUR, 0);
		currentDate.set(Calendar.MINUTE, 0);
		currentDate.set(Calendar.SECOND, 0);
		return currentDate.getTime();
	}

	public static int daysDiff(Date date1, Date date2)
	{
		date1 = stripTime(date1);
		date2 = stripTime(date2);
		long longDate1 = date1.getTime();
		long longDate2 = date2.getTime();
		long longDiff = longDate2 - longDate1;
		return (int) (longDiff / MILLS_IN_DAY);
	}

	public static Date createDate(int year, int month, int day)
	{
		GregorianCalendar date = new GregorianCalendar(year, month, day);
		return date.getTime();
	}


}