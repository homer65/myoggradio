package org.myoggradio.interfaces;
import java.util.*;
import org.myoggradio.StringPaar;
public interface Provider 
{
	public ArrayList<StringPaar> getFavoriten(String benutzer,String passwort);
	public ArrayList<StringPaar> getCommon();
	public void addFavorit(StringPaar sp,String benutzer,String passwort);
	public void dropFavorit(StringPaar sp,String benutzer,String passwort);
	public void changeFavorit(StringPaar sp,String benutzer,String passwort);
	public void addNewURL(String url,String bemerkung,String benutzer,String passwort);
	public String login(String benutzer,String passwort);
}
