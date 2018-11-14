package org.myoggradio;
import java.util.*;
import org.myoggradio.interfaces.Provider;
public class TestProvider implements Provider
{
	private ArrayList<StringPaar> alC = new ArrayList<StringPaar>();
	private ArrayList<StringPaar> alF = new ArrayList<StringPaar>();
	public TestProvider()
	{
		StringPaar sp1 = new StringPaar("Radio Swiss Pop","http://zlz-stream11.streaming.init7.net:80/1/rsp/mp3_128");
		StringPaar sp2 = new StringPaar("Einslive","http://gffstream.ic.llnwd.net/stream/gffstream_stream_wdr_einslive_a ");
		alC.add(sp1);
		alC.add(sp2);
		alF.add(sp1);
	}
	public void addFavorit(StringPaar sp, String benutzer, String passwort) 
	{
		alF.add(sp);
	}
	public void dropFavorit(StringPaar sp, String benutzer, String passwort) 
	{
		alF.remove(sp);
	}
	public ArrayList<StringPaar> getCommon() 
	{
		return alC;
	}
	public ArrayList<StringPaar> getFavoriten(String benutzer, String passwort) 
	{
		return alF;
	}

	public String login(String benutzer, String passwort) 
	{
		String erg = "ok";
		return erg;
	}
	@Override
	public void changeFavorit(StringPaar sp, String benutzer, String passwort) 
	{
		// Nicht implementiert
	}
	@Override
	public void addNewURL(String url, String bemerkung, String benutzer,
			String passwort) {
		// TODO Auto-generated method stub
		
	}
}
