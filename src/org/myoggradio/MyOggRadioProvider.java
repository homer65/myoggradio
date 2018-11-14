package org.myoggradio;
import java.io.*;
import java.net.*;
import java.util.*;
import org.myoggradio.interfaces.*;
import org.myoggradio.interfaces.Provider;
public class MyOggRadioProvider implements Provider
{
	public void changeFavorit(StringPaar sp,String benutzer,String passwort)
	{
		String name = sp.getName();
		String xurl = sp.getWert();
		try
		{
			benutzer = URLEncoder.encode(benutzer,"UTF-8");
			passwort = URLEncoder.encode(passwort,"UTF-8");
			xurl = URLEncoder.encode(xurl,"UTF-8");
			name = URLEncoder.encode(name,"UTF-8");
		}
		catch (Exception e)
		{
			Protokol.write("MyOggRadioProvider:changeFavorit:Exception:");
			Protokol.write(e.toString());
		}
		String surl = Global.myoggradio + "/p_change.jsp";
		surl += "?benutzer=" + benutzer;
		surl += "&passwort=" + passwort;
		surl += "&url=" + xurl;
		surl += "&name=" + name;
		Protokol.write("MyOggRadioProvider:changeFavorit:will open:");
		Protokol.write(surl);
		try
		{
			URL url = new URL(surl);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String erg = "";
			String satz = br.readLine();
			while (satz != null)
			{
				erg += satz;
				satz = br.readLine();
			}
			br.close();
			erg = extract(erg);
			Protokol.write("MyOggRadioProvider:changeFavorit:" + erg);
		}
		catch (Exception e)
		{
			Protokol.write("MyOggRadioProvider:changeFavorit:Exception:");
			Protokol.write(e.toString());
		}
	}
	public void addFavorit(StringPaar sp, String benutzer, String passwort) 
	{
		String xurl = sp.getWert();
		try
		{
			benutzer = URLEncoder.encode(benutzer,"UTF-8");
			passwort = URLEncoder.encode(passwort,"UTF-8");
			xurl = URLEncoder.encode(xurl,"UTF-8");
		}
		catch (Exception e)
		{
			Protokol.write("MyOggRadioProvider:addFavorit:Exception:");
			Protokol.write(e.toString());
		}
		String surl = Global.myoggradio + "/p_add2.jsp";
		surl += "?benutzer=" + benutzer;
		surl += "&passwort=" + passwort;
		surl += "&url=" + xurl;
		Protokol.write("MyOggRadioProvider:addFavorit:will open:");
		Protokol.write(surl);
		try
		{
			URL url = new URL(surl);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String erg = "";
			String satz = br.readLine();
			while (satz != null)
			{
				erg += satz;
				satz = br.readLine();
			}
			br.close();
			erg = extract(erg);
			Protokol.write("MyOggRadioProvider:addFavorit:" + erg);
		}
		catch (Exception e)
		{
			Protokol.write("MyOggRadioProvider:addFavorit:Exception:");
			Protokol.write(e.toString());
		}
	}
	public void addNewURL(String surl,String bemerkung,String benutzer, String passwort) 
	{
		try
		{
			benutzer = URLEncoder.encode(benutzer,"UTF-8");
			passwort = URLEncoder.encode(passwort,"UTF-8");
			surl = URLEncoder.encode(surl,"UTF-8");
			bemerkung = URLEncoder.encode(bemerkung,"UTF-8");
		}
		catch (Exception e)
		{
			Protokol.write("MyOggRadioProvider:addNewURL:Exception:");
			Protokol.write(e.toString());
		}
		String xurl = Global.myoggradio + "/p_newURL.jsp";
		xurl += "?benutzer=" + benutzer;
		xurl += "&passwort=" + passwort;
		xurl += "&url=" + surl;
		xurl += "&bemerkung=" + bemerkung;
		Protokol.write("MyOggRadioProvider:addNewURL:will open:");
		Protokol.write(xurl);
		try
		{
			URL url = new URL(xurl);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String erg = "";
			String satz = br.readLine();
			while (satz != null)
			{
				erg += satz;
				satz = br.readLine();
			}
			br.close();
			erg = extract(erg);
			Protokol.write("MyOggRadioProvider:addNewURL:" + erg);
		}
		catch (Exception e)
		{
			Protokol.write("MyOggRadioProvider:addNewURL:Exception:");
			Protokol.write(e.toString());
		}
	}
	public void dropFavorit(StringPaar sp, String benutzer, String passwort) 
	{
		String xurl = sp.getWert();
		try
		{
			benutzer = URLEncoder.encode(benutzer,"UTF-8");
			passwort = URLEncoder.encode(passwort,"UTF-8");
			xurl = URLEncoder.encode(xurl,"UTF-8");
		}
		catch (Exception e)
		{
			Protokol.write("MyOggRadioProvider:changeFavorit:Exception:");
			Protokol.write(e.toString());
		}
		String surl = Global.myoggradio + "/p_drop2.jsp";
		surl += "?benutzer=" + benutzer;
		surl += "&passwort=" + passwort;
		surl += "&url=" + xurl;
		try
		{
			URL url = new URL(surl);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String erg = "";
			String satz = br.readLine();
			while (satz != null)
			{
				erg += satz;
				satz = br.readLine();
			}
			br.close();
			erg = extract(erg);
			Protokol.write("MyOggRadioProvider:dropFavorit:" + erg);
		}
		catch (Exception e)
		{
			Protokol.write("MyOggRadioProvider:dropFavorit:Exception:");
			Protokol.write(e.toString());
		}		
	}
	public ArrayList<StringPaar> getCommon() 
	{
		ArrayList<StringPaar> erg = new ArrayList<StringPaar>();
		try
		{
			URL url = new URL(Global.myoggradio + "/common.json");
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String json = "";
			String satz = br.readLine();
			while (satz != null)
			{
				json += satz;
				satz = br.readLine();
			}
			br.close();
			String jsonx = "";
			for (int i=0;i<json.length();i++)
			{
				String ch = json.substring(i,i+1);
				if (ch.equals("{"))
				{
					jsonx = "";
				}
				else if (ch.equals("}"))
				{
					StringPaar sp = getStringPaar(jsonx);
					erg.add(sp);
				}
				else
				{
					jsonx += ch;
				}
			}
		}
		catch (Exception e)
		{
			Protokol.write("MyOggRadioProvider:getCommon:Exception:");
			Protokol.write(e.toString());
		}
		return erg;
	}
	public ArrayList<StringPaar> getFavoriten(String benutzer, String passwort) 
	{
		ArrayList<StringPaar> erg = new ArrayList<StringPaar>();
		try
		{
			URL url = new URL(Global.myoggradio + "/player.json?user=" + benutzer + "&passwort=" + passwort);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String json = "";
			String satz = br.readLine();
			while (satz != null)
			{
				json += satz;
				satz = br.readLine();
			}
			br.close();
			String jsonx = "";
			for (int i=0;i<json.length();i++)
			{
				String ch = json.substring(i,i+1);
				if (ch.equals("{"))
				{
					jsonx = "";
				}
				else if (ch.equals("}"))
				{
					StringPaar sp = getStringPaar(jsonx);
					erg.add(sp);
				}
				else
				{
					jsonx += ch;
				}
			}
		}
		catch (Exception e)
		{
			Protokol.write("MyOggRadioProvider:getFavoriten:Exception:");
			Protokol.write(e.toString());
		}
		return erg;
	}
	private StringPaar getStringPaar(String jsonx)
	{
		StringPaar erg = new StringPaar();
		String name = "";
		String wert = "";
		String temp = "";
		int anz=0;
		for (int i=0;i<jsonx.length();i++)
		{
			String ch = jsonx.substring(i,i+1);
			if (ch.equals("\""))
			{
				anz++;
				if (anz == 4) wert = temp;
				if (anz == 8) name = temp;
				temp = "";
			}
			else
			{
				temp += ch;
			}
		}
		erg.setName(name);
		erg.setWert(wert);
		return erg;
	}
	public String login(String benutzer, String passwort) 
	{
		String erg = "unbekannter Fehler";
		try
		{
			URL url = new URL(Global.myoggradio + "/p_login.jsp?benutzer=" + benutzer + "&passwort=" + passwort);
			URLConnection con = url.openConnection();
			InputStream in = con.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			erg = "";
			String satz = br.readLine();
			while (satz != null)
			{
				erg += satz;
				satz = br.readLine();
			}
			br.close();
			erg = extract(erg);
		}
		catch (Exception e)
		{
			Protokol.write("MyOggRadioProvider:login:Exception:");
			Protokol.write(e.toString());
			erg = "Exception";
		}
		return erg;
	}
	private String extract(String s)
	{
		String erg = "";
		int l = s.length();
		boolean ok = false;
		for (int i=0;i<l;i++)
		{
			String ch = s.substring(i,i+1);
			if (ch.equals("["))
			{
				ok = true;
			}
			else if (ch.equals("]"))
			{
				ok = false;
			}
			else
			{
				if (ok) erg += ch;
			}
		}
		return erg;
	}
}
