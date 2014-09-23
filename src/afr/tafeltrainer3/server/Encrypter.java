package afr.tafeltrainer3.server;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class Encrypter {

	@Nonnull
	private MessageDigest msgDigest;

	@Nullable
	private static Encrypter instance;

	private Encrypter() 
	{
		try 
		{
			msgDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	public static Encrypter getInstance() 
	{
		if (instance == null) 
		{
			instance = new Encrypter();
		}
		return instance;
	}

	public String encrypt(@Nullable String pass) 
	{
		if (pass == null) {
			return null;
		}
	    byte[] passBytes = pass.getBytes();
	    msgDigest.reset();
	    byte[] digested = msgDigest.digest(passBytes);
	    StringBuffer sb = new StringBuffer();
	    for(int i=0;i<digested.length;i++){
	        sb.append(Integer.toHexString(0xff & digested[i]));
	    }
	    return sb.toString();
	}
}
