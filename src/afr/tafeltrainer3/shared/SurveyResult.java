package afr.tafeltrainer3.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class SurveyResult implements IsSerializable
{
	int userid;
	String vraag1;
	String vraag2;
	String vraag3;
	String vraag4;
	String vraag5;
	String vraag6;
	String vraag7;
	String vraag8;

	public SurveyResult(int userid,String v1, String v2,String v3,String v4,String v5,String v6,String v7,String v8)
	{
		this.userid= userid;
		this.vraag1 = v1;
		this.vraag2 = v2;
		this.vraag3 = v3;
		this.vraag4 = v4;
		this.vraag5 = v5;
		this.vraag6 = v6;
		this.vraag7 = v7;
		this.vraag8 = v8;
	}

	public SurveyResult()
	{
	}

	public int getUserid()
	{
		return userid;
	}

	public void setUserid(int userid)
	{
		this.userid = userid;
	}

	public String getVraag1()
	{
		return vraag1;
	}

	public void setVraag1(String vraag1)
	{
		this.vraag1 = vraag1;
	}

	public String getVraag2()
	{
		return vraag2;
	}

	public void setVraag2(String vraag2)
	{
		this.vraag2 = vraag2;
	}

	public String getVraag3()
	{
		return vraag3;
	}

	public void setVraag3(String vraag3)
	{
		this.vraag3 = vraag3;
	}

	public String getVraag4()
	{
		return vraag4;
	}

	public void setVraag4(String vraag4)
	{
		this.vraag4 = vraag4;
	}

	public String getVraag5()
	{
		return vraag5;
	}

	public void setVraag5(String vraag5)
	{
		this.vraag5 = vraag5;
	}

	public String getVraag6()
	{
		return vraag6;
	}

	public void setVraag6(String vraag6)
	{
		this.vraag6 = vraag6;
	}

	public String getVraag7()
	{
		return vraag7;
	}

	public void setVraag7(String vraag7)
	{
		this.vraag7 = vraag7;
	}

	public String getVraag8()
	{
		return vraag8;
	}

	public void setVraag8(String vraag8)
	{
		this.vraag8 = vraag8;
	}

}
