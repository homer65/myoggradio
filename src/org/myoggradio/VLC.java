package org.myoggradio;
import org.myoggradio.interfaces.*;
import java.util.*;
public class VLC extends Thread implements Player 
{
	private String url = "";
	private String vlc = null;
	private String exe = Global.windows_playercommand;
	public void play(String url)
	{
		this.url = url;
		this.start();
	}
	public void run()
	{
		if (vlc == null)
		{
			Searcher searcher = Factory.getSearcher();
			vlc = searcher.search(exe,"jmyoggradioplayer_player");
		}
		if (vlc == null)
		{
			Protokol.write("VLC:run: " + exe + " not found");
			SearchInfoDialog sid = Factory.getSearchInfoDialog();
			sid.anzeigen();
			if (sid.shouldSearch())
			{
				BackgroundSearcher bs = Factory.getBackgroundSearcher();
				bs.search(sid.searchDirectory());
				sid.kill();
			}
		}
		else
		{
			ArrayList<String> al = new ArrayList<String>();
			al.add(vlc);
			al.add(url);
			Command command = Factory.getCommand();
			command.start(al);
		}
		return;
	}
}
