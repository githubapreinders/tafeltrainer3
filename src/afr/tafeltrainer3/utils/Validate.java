package afr.tafeltrainer3.utils;


public class Validate {
	
	public static Boolean checkTextbox(String txt)
	{
		boolean valid = false;
		if(txt.length()>20 || txt.length()<2)
		{
			return valid;
		}
	valid = true;
	return valid;
	}
	
	public static Boolean checkEmail(String txt1)
	{
		boolean valid = false;
		if(txt1.length()>254)
		{
			return valid;
		}
		for (int i = 0; i< txt1.length() ; i++)
		{
			if(txt1.charAt(i)=='@')
			{
				for (int j = i+1; j<txt1.length() ; j++)
				{
					if(txt1.charAt(j)=='.')
					{
						valid = true;
						for (int k = i+1; k<txt1.length() ; k++)
						{
							if(txt1.charAt(k)=='@')
							{
								valid = false;i=txt1.length()-1;
							}
						}
					}
				}
			}
			if(txt1.charAt(i)==' '||txt1.charAt(i)=='!'||txt1.charAt(i)=='"'||txt1.charAt(i)=='#'||
					txt1.charAt(i)=='$'||txt1.charAt(i)=='%'||txt1.charAt(i)=='&'||
					txt1.charAt(i)==','||txt1.charAt(i)==';'||txt1.charAt(i)==':'||
					txt1.charAt(i)=='<'||txt1.charAt(i)=='>'||txt1.charAt(i)=='['||
					txt1.charAt(i)==']'||txt1.charAt(i)=='/'||txt1.charAt(i)=='|'
					){valid = false; break;}
		}
		return valid;
		
	}

	public static Boolean checkPassword(String ww1) 
	{
	boolean valid = false;
		if(ww1.length()<6||ww1.length()>15)
		{
			return valid;
		}
		int capitalcount = 0;
		int digitcount = 0;
		for (int i = 0; i<ww1.length() ; i++)
		{
			if(Character.isDigit(ww1.charAt(i)))
				{
					digitcount++;
				}
			if(Character.isUpperCase(ww1.charAt(i)))
				{
					capitalcount++;
				}
			if (capitalcount>0&&digitcount>0)
				{
					valid = true;break;
				}
			
		}
	return valid;
	}
	
	
}
