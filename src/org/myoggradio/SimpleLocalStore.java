package org.myoggradio;
import java.util.*;
import java.io.*;
import org.myoggradio.interfaces.*;
public class SimpleLocalStore implements LocalStore
{
	private File file = new File("JMyOggRadioPlayer.common.json");
	@Override
	public ArrayList<StringPaar> getCommon()
	{
		ArrayList<StringPaar> erg = new ArrayList<StringPaar>();
		try
		{
			Reader reader = new FileReader(file);
			BufferedReader br = new BufferedReader(reader);
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
			Protokol.write("SimpleLocalStore:getCommon:Read from File:");
			Protokol.write(file.getAbsolutePath());
		}
		catch (Exception e)
		{
			Protokol.write("SimpleLocalStore:getCommon:Exception:");
			Protokol.write(e.toString());
		}
		return erg;
	}
	@Override
	public void putCommon(ArrayList<StringPaar> al) 
	{
		try
		{
			FileWriter fw = new FileWriter(file);
			fw.write("[" + "\n");
			for (int i=0;i<al.size();i++)
			{
				StringPaar sp = al.get(i);
				String name = sp.getName();
				String url = sp.getWert();
				fw.write("{" + "\n");
				fw.write("\"url\":\"" + url + "\",\n");
				fw.write("\"name\":\"" + name + "\"\n");
				fw.write("}," + "\n");
			}
			fw.write("]" + "\n");
			fw.close();
			Protokol.write("SimpleLocalStore:putCommon:Written to File:");
			Protokol.write(file.getAbsolutePath());
		}
		catch (Exception e)
		{
			Protokol.write("SimpleLocalStore:putCommon:Exception:");
			Protokol.write(e.toString());
		}
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
}
