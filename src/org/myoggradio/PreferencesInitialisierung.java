package org.myoggradio;
import java.util.prefs.Preferences;
import org.myoggradio.interfaces.*;
public class PreferencesInitialisierung implements Initialisierung
{
	private Preferences prefs = Preferences.userRoot();
	public int start()
	{
		int rc = 0;
		Protokol.write("JMyOggRadioPlayer Version " + Global.version);
		try
		{
			String s = prefs.get("jmyoggradioplayer_linux_playercommand",null);
			if (s != null)
			{
				Global.linux_playercommand = s;
			}
			s = prefs.get("jmyoggradioplayer_linux_recordercommand",null);
			if (s != null)
			{
				Global.linux_recordercommand = s;
			}
			s = prefs.get("jmyoggradioplayer_linux_recorderdirectory",null);
			if (s != null)
			{
				Global.linux_recorderdirectory = s;
			}
			s = prefs.get("jmyoggradioplayer_windows_playercommand",null);
			if (s != null)
			{
				Global.windows_playercommand = s;
			}
			s = prefs.get("jmyoggradioplayer_windows_recordercommand",null);
			if (s != null)
			{
				Global.windows_recordercommand = s;
			}
			s = prefs.get("jmyoggradioplayer_windows_recorderdirectory",null);
			if (s != null)
			{
				Global.windows_recorderdirectory = s;
			}
		}
		catch (Exception e)
		{
			rc = 8;
			Protokol.write("PreferencesInitialisierung:start:Exception:");
			Protokol.write(e.toString());
		}
		return rc;
	}
}
