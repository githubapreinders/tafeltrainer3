package afr.tafeltrainer3.client;

import java.util.*;

public class Oefen
{

	public static void main(String[] args)
	{
		String urlstring = "balblabla/blabbasdf/autos.png";
		StringBuilder s = new StringBuilder(urlstring);
		System.out.println(s.toString());
		s.delete(urlstring.length()-4, urlstring.length());
		s.delete(0, s.lastIndexOf("/")+1);
		System.out.println(s.toString());
		
				
		
	}
}
