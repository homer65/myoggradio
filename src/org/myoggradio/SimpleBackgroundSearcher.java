package org.myoggradio;
import java.io.*;
import java.util.prefs.Preferences;
import org.myoggradio.interfaces.*;
public class SimpleBackgroundSearcher extends Thread implements BackgroundSearcher
{
	private String player = Global.windows_playercommand;
	private String recorder = Global.windows_recordercommand;
	private String player_preference = "jmyoggradioplayer_player";
	private String recorder_preference = "jmyoggradioplayer_recorder";
	private int lp = player.length();
	private int lr = recorder.length();
	private long anzDir = 0;
	private int anzTemp = 0;
	private File sdir = null;
	private Preferences prefs = Preferences.userRoot();
	public void search(File dir)
	{
		sdir = dir;
		if (dir != null) Protokol.write("SimpleBackgroundSearcher:search:" + dir.getAbsolutePath());
		else Protokol.write("SimpleBackgroundSearcher:search:Null Directory");
		start();
	}
	public void run()
	{
		Protokol.write("SimpleBackgroundSearcher:start");
		searchDir(sdir);
		Protokol.write("SimpleBackgroundSearcher:ende");
	}
	private void searchDir(File dir)
	{
		if (dir != null)
		{
			if (dir.isDirectory())
			{
				File[] files = dir.listFiles();
				if (files != null)
				{
					for (int i=0;i<files.length;i++)
					{
						File file = files[i];
						if (file.isDirectory()) searchDir(file);
						else match(file);
					}
				}
			}
		}
	}
	private void match(File file)
	{
		if (file != null)
		{
			anzDir++;
			anzTemp++;
			if (anzTemp > 99999)
			{
				anzTemp = 0;
				Protokol.write("SimpleBackgroudSearcher:match:" + anzDir + " Files");
			}
			String pfad = file.getAbsolutePath();
			int lf = pfad.length();
			if (lf >= lp)
			{
				String suffix = pfad.substring(lf-lp,lf);
				if (suffix.equals(player))
				{
					prefs.put(player_preference,pfad);
					Protokol.write("SimpleBackgroudSearcher:match:Player found:" + pfad);
				}
			}
			if (lf > lr)
			{
				String suffix = pfad.substring(lf-lr,lf);
				if (suffix.equals(recorder))
				{
					prefs.put(recorder_preference,pfad);
					Protokol.write("SimpleBackgroudSearcher:match:Recorder found:" + pfad);
				}
			}
		}
	}
}
