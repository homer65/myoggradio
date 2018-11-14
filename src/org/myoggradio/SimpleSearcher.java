package org.myoggradio;
import java.io.File;
import java.util.prefs.Preferences;
import org.myoggradio.interfaces.*;
public class SimpleSearcher implements Searcher
{
	private Preferences prefs = Preferences.userRoot();
	public String search(String cmd,String preference) 
	{
		String erg = prefs.get(preference,null);
		if (erg != null) //Preference existiert
		{
			Protokol.write("SimpleSearcher:search:Path from Preferences:" + erg);
			File test = new File(erg);
			if (!test.exists())
			{
				erg = null;
				Protokol.write("SimpleSearcher:search:" + erg + " not exists");
			}
			else
			{
				boolean ok = inspect(cmd,erg);
				if (ok)
				{
					Protokol.write("SimpleSearcher:search:found from Preference " + erg);
				}
				else
				{
					Protokol.write("SimpleSearcher:search:" + cmd + " is not " + erg);
					erg = null;
				}
			}
		}
		else
		{
			Protokol.write("SimpleSearcher:search:Preference:" + preference + " not found");
		}
		if (erg == null) //Suche anhand Preference brachte kein Ergebnis
		{
			File test = new File(cmd);
			if (test.exists()) // Wurde der komplette Pfad angegeben?
			{
				erg = cmd;
			}
		}
		if (erg == null) // Es ist auch nicht der absolute Pfad angegeben
		{
			NonBlockingSwingMessage msg = Factory.getNonBlockingSwingMessage();
			msg.show("Search for " + cmd + "\n Please wait");
			File c = new File("C:\\");
			File[] files = c.listFiles();
			for (int i=0;i<files.length;i++)
			{
				File file = files[i];
				if (file.isDirectory())
				{
					String dir = file.getAbsolutePath();
					if (dir.length() > 4)
					{
						String prefix = dir.substring(0,5);
						boolean ok = false;
						if (prefix.equals("C:\\Pr")) ok = true;
						if (prefix.equals("C:\\PR")) ok = true;
						if (ok)
						{
							Protokol.write("SimpleSearcher:search:Diretory:" + file.getAbsolutePath());
							erg = searchDirectory(file,cmd);
							if (erg != null) break;
						}
					}
				}
			}
		}
		if (erg != null)
		{
			Protokol.write("SimpleSearcher:search:found " + erg);
			prefs.put(preference,erg);
		}
		else
		{
			Protokol.write("SimpleSearcher:search: " + cmd + " not found");
		}
		return erg;
	}
	public String searchDirectory(File dir,String cmd)
	{
		String erg = null;
		try
		{
			File[] files = dir.listFiles();
			if (files != null)
			{
				for (int i=0;i<files.length;i++)
				{
					File file = files[i];
					if (file.isDirectory())
					{
						erg = searchDirectory(file,cmd);
						if (erg != null) return erg;
					}
					else
					{
						String name = file.getAbsolutePath();
						if (inspect(cmd,name))
						{
							return name;
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			Protokol.write("SimpleSearcher:search:Exception:");
			Protokol.write(e.toString());
		}
		return erg;
	}
	private boolean inspect(String cmd,String pfad)
	{
		boolean erg = false;
		int lcmd = cmd.length();
		int lpfad = pfad.length();
		if (lpfad >= lcmd)
		{
			String suffix = pfad.substring(lpfad-lcmd,lpfad);
			if (suffix.equals(cmd)) erg = true;
		}
		return erg;
	}
}
