package org.myoggradio;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import org.myoggradio.interfaces.*;
public class SimpleTouchURL implements TouchURL 
{
	public void touch(String s)
	{
		try
		{
			s = URLEncoder.encode(s,"UTF-8");
		}
		catch (Exception e)
		{
			Protokol.write("SimpleTouchURL:touch:1:Exception:");
			Protokol.write(e.toString());
		}
		String surl = Global.myoggradio + "/p_touch2.jsp?url=" + s;
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
			Protokol.write("SimpleTouchURL:touch:" + erg.trim());
		}
		catch (Exception e)
		{
			Protokol.write("SimpleTouchURL:touch:2:Exception:");
			Protokol.write(e.toString());
		}
	}
}
