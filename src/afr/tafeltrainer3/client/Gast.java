package afr.tafeltrainer3.client;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;

import afr.tafeltrainer3.shared.FeedbackContainer;
import afr.tafeltrainer3.shared.Opgave;
import afr.tafeltrainer3.shared.TafelResult;

public class Gast
{
	public ArrayList<Opgave> opgaven;
	public Date starttijd;

	public Gast()
	{
		this.opgaven = new ArrayList<Opgave>();
		this.starttijd = new Date();
	}

	public void addOpgave(Opgave opg)
	{
		this.opgaven.add(opg);
	}

	public int getOefentijd()
	{
		Date nu = new Date();
		return (int) ((nu.getTime() - starttijd.getTime()))/1000;
	}

	public double getAverageSpeed()
	{
		double speed = 0;
		NumberFormat nf = NumberFormat.getFormat("##.#");
		int howmuchopgaven = opgaven.size();
		for (Opgave opg : opgaven)
		{
			speed += opg.getTime();
		}
		if (howmuchopgaven > 0)
		{
			return speed / howmuchopgaven;
		} else
			return 0;
	}

	public TafelResult getTafelResult(int factor)
	{
		TafelResult tr;
		int howmuchopgaven = 0;
		int score = 0;
		for (Opgave o : opgaven)
		{
			if (o.getFactor2() == factor)
			{
				howmuchopgaven++;
				if (o.iscorrect)
				{
					score++;
				}
			}
		}
		double percentage = 0;
		if (howmuchopgaven > 0)
		{
			percentage = ((double) score * 100) / (double) howmuchopgaven;
			tr = new TafelResult(factor, (int) percentage, howmuchopgaven);
		} else
		{
			tr = new TafelResult(factor, 0, 0);
		}

		return tr;
	}

	public FeedbackContainer makeFeedbackContainer()
	{
		int howmuchopgaven = opgaven.size();
		double averagespeed = getAverageSpeed();
		String oefentijd;
		int tijd = getOefentijd();
		int minutes = tijd/60;
		int seconds = tijd%60;
		if(minutes==0)
		{
			oefentijd = String.valueOf(seconds)+" sec";
		}
		else
		{
			oefentijd = String.valueOf(minutes) + " min, "+String.valueOf(seconds)+" sec"; 
		}
		
		int errors = 0;
		for (int i = 0; i < opgaven.size(); i++)
		{
			if (opgaven.get(i).iscorrect == false)
			{
				errors++;
			}
		}
		
		ArrayList<TafelResult> results = new ArrayList<TafelResult>();
		for (int i = 2; i < 20; i++)
		{
			results.add(getTafelResult(i));
		}
		FeedbackContainer f = new FeedbackContainer(howmuchopgaven, averagespeed, starttijd, oefentijd, errors,
				results, "Alleen vandaag geoefend");
		DateTimeFormat sf = DateTimeFormat.getFormat("EEEE dd MMM YYYY" );
		f.setBegindatumstring(sf.format(starttijd));
		return f;
	}

}












