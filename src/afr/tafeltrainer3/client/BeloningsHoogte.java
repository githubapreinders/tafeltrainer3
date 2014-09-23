package afr.tafeltrainer3.client;

import java.util.ArrayList;
import java.util.Random;

import com.google.gwt.user.client.ui.Image;

public class BeloningsHoogte {
private int corrects;
private int errors;
private int successequence;
private int errorsequence;
private int beloning;
ArrayList<Image> geldplaatjes;	

public BeloningsHoogte()
	{
		this.errors=0;
		this.corrects=0;
		
	}

/**
 * bepaalt de hoogte van de beloning.In acht wordt genomen correctheid van het antwoord,
 * hoe vaak er correct of fout wordt geantwoord(succes of errorsequence), de moeilijkheid van de opgave,
 * en een randomfactor voor een grotere beloning.
 */
public int getBeloning(boolean correct, boolean moeilijk) 
	{
	Random ran = new Random();
	beloning = 0;
	if(correct)
	{ 
		this.corrects++;this.successequence++;this.errorsequence=0;beloning+=1;
	}
	if(!correct)
	{
		this.errors++;this.errorsequence++;this.successequence=0;beloning-=1;
	}
	if(successequence%10==0&&successequence>0)
		beloning+=5;
	if(successequence%30==0&&successequence>0)
		beloning+=50;
	if(errorsequence>=2)
		beloning-=errorsequence;
	
	int chance = 1+ran.nextInt(100);
	if(chance>90 && moeilijk && correct){beloning+=ran.nextInt(50);}
	if(chance>95 && !moeilijk && correct){beloning+=ran.nextInt(50);}
	return beloning;
	}

	//sorteert een beloningsbedrag naar zo groot mogelijke valutaeenheden
public ArrayList<Image>getGeldplaatjes(int beloning)
	{
	geldplaatjes = new ArrayList<Image>();	
	Image twintigtalmunt,tientalmunt,vijftalmunt,tweetalerafmunt,eenerafmunt,tafelmunt;
	
	if(beloning>0)
	{
		int twintigtallen = beloning/20;
		for (int i = 0; i< twintigtallen; i++)
		{
			twintigtalmunt = new Image("/images/tafelmunt_20.png");
			twintigtalmunt.setStyleName("imagesmall");
			geldplaatjes.add(twintigtalmunt);
		}
		int tientallen = (beloning%20)/10;
		for (int i = 0; i< tientallen; i++)
		{
			tientalmunt = new Image("/images/tafelmunt_10.png");
			tientalmunt.setStyleName("imagesmall");
			geldplaatjes.add(tientalmunt);
		}
		int vijftallen = (beloning%10)/5;
		for (int i = 0; i< vijftallen; i++)
		{
			vijftalmunt = new Image("/images/tafelmunt_5.png");
			vijftalmunt.setStyleName("imagesmall");
			geldplaatjes.add(vijftalmunt);
		}
		int eenheden = beloning - 10*tientallen - 5*vijftallen;
		for (int i = 0; i< eenheden; i++)
		{
			tafelmunt = new Image("/images/tafelmunt_1.png");
			tafelmunt.setStyleName("imagesmall");
			geldplaatjes.add(tafelmunt);
		}
	}
	
	if(beloning<0)
	{	
		beloning*=-1;
		int tweetalleneraf = beloning/2;
		for (int i = 0; i< tweetalleneraf; i++)
		{
			tweetalerafmunt = new Image("/images/tafelmunt_2eraf.png");
			tweetalerafmunt.setStyleName("imagesmall");
			geldplaatjes.add(tweetalerafmunt);
		}
		int eneneraf = beloning - 2*tweetalleneraf;
		for (int i = 0; i< eneneraf; i++)
		{
			eenerafmunt = new Image("/images/tafelmunt_1eraf.png");
			eenerafmunt.setStyleName("imagesmall");
			geldplaatjes.add(eenerafmunt);
		}
	}
	return geldplaatjes;
	}


public int getCorrects() {
	return corrects;
}

public void setCorrects(int corrects) {
	this.corrects = corrects;
}

public int getErrors() {
	return errors;
}

public void setErrors(int errors) {
	this.errors = errors;
}
}
