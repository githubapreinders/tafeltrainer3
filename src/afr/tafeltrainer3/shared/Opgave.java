package afr.tafeltrainer3.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import com.google.gwt.user.client.ui.CheckBox;



public class Opgave implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int opgavenid;
	int id;
	int factor1;
	int factor2;
	public int antwoord;
	public int useranswer;
	public boolean iscorrect;
	double time;
	public static int vorigevorigefactor1;
	public static int vorigefactor1;
	
	public Opgave()
	{
		this.id=opgavenid;
		this.factor1=getRandomDigit(10);
		this.factor2=getRandomDigit(10);
		this.antwoord = getAntwoord(this.factor1,this.factor2);
		this.useranswer=-1;
		this.time = -1;
		this.iscorrect=false;
		opgavenid++;
	}
	//gebruiker kiest tafeltype om te oefenen
	
	public Opgave(OpgaveSoort opgavesoort)
	{
		//moeilijke opgaven, tafels tm 20
		if (opgavesoort.isX11())
		{
			this.id=opgavenid;
			this.factor1 = getRandomDigit(20);
			this.factor2 = getRandomDigit(20);
			this.antwoord = getAntwoord(this.factor1, this.factor2);
			this.useranswer = -1;
			this.iscorrect=false;
			this.time = -1;
			opgavenid++;
		}
		//tafels tm 9
		if(opgavesoort.isXalle())
		{
			this.id=opgavenid;
			this.factor1=getRandomDigit(10);
			this.factor2=getRandomDigit(10);
			this.antwoord = getAntwoord(this.factor1,this.factor2);
			this.useranswer=-1;
			this.iscorrect=false;
			this.time = -1;
			opgavenid++;
		}
		//opgegeven tafels,checkboxen worden hier nagelopen
		if(!opgavesoort.isXalle()&&!opgavesoort.isX11())
		{
			ArrayList<Integer> digits = new ArrayList<Integer>();
			CheckBox[] boxes = opgavesoort.getBoxes();
			for(int i =0; i<boxes.length-2;i++)
			{
				if(boxes[i].getValue()==true){digits.add((i+2));}
			}
			this.id=opgavenid;
			this.factor1=getRandomDigit(10);
			this.factor2=getSpecificRandomDigit(10,digits);
			this.antwoord = getAntwoord(this.factor1,this.factor2);
			this.useranswer=-1;
			this.iscorrect=false;
			this.time = -1;
			opgavenid++;
			
		}
	}
	public Opgave(int id, int factor1, int factor2, int useranswer, double time)
	{
		this.id=id;
		this.factor1=factor1;
		this.factor2=factor2;
		this.antwoord=getAntwoord(this.factor1, this.factor2);
		this.useranswer = useranswer;
		this.time = time;
	}
	
	
	private int getSpecificRandomDigit(int howmuch, ArrayList<Integer> digits) {
		Random ran = new Random();
		boolean okay = false;
		int digit;
		do
		{
			digit = 1 + ran.nextInt(howmuch);
			for(int i: digits)
				{
					if(digit==i)
					{
						okay = true;
					}
				}
		}
		while(okay==false);
		vorigevorigefactor1 = vorigefactor1;//om duplicaten te voorkomen
		vorigefactor1 = digit;
		return  digit;
	}
	
	
	
	
	private int getRandomDigit(int howmuch) {
		Random ran = new Random();
		int digit;
		do
		{
			digit = 1 + ran.nextInt(howmuch);
		}
		while(digit == vorigefactor1 || digit == vorigevorigefactor1);
		vorigevorigefactor1 = vorigefactor1;//om duplicaten te voorkomen
		vorigefactor1 = digit;
		return  digit;
	}
	public String toString()
	{
		String output = String.valueOf(this.factor1) + " X " +
				String.valueOf(this.factor2) + " = " +
				String.valueOf(this.antwoord) ;
		return output;
	}

	public void setIscorrect(boolean iscorrect) {
		this.iscorrect = iscorrect;
	}
	
	private int getAntwoord(int factor1, int factor2) {
		
		return factor1*factor2 ;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFactor1() {
		return factor1;
	}
	public void setFactor1(int factor1) {
		this.factor1 = factor1;
	}
	public int getFactor2() {
		return factor2;
	}
	public void setFactor2(int factor2) {
		this.factor2 = factor2;
	}
	public int getAntwoord() {
		return antwoord;
	}
	public void setAntwoord(int antwoord) {
		this.antwoord = antwoord;
	}
	public int getUseranswer() {
		return useranswer;
	}
	public void setUseranswer(int useranswer) {
		this.useranswer = useranswer;
		if (this.useranswer == this.antwoord)
		{
			this.iscorrect = true;
		}
	}
	public void setTime(double time) {
		this.time = time;
		
	}
	public double getTime() {
		return this.time;
		
	}
	
}
