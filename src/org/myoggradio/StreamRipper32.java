package org.myoggradio;
import org.myoggradio.interfaces.*;
import java.util.*;
public class StreamRipper32 extends Thread implements Recorder 
{
	private String url = "";
	private String ripper = null;
	private String exe = Global.windows_recordercommand;
	public void record(String url)
	{
		this.url = url;
		this.start();
	}
	public void run()
	{
		if (ripper == null)
		{
			Searcher searcher = Factory.getSearcher();
			ripper = searcher.search(exe,"jmyoggradioplayer_recorder");
		}
		if (ripper == null)
		{
			Protokol.write("StreamRipper32:run: " + exe + " not found");
			SearchInfoDialog sid = Factory.getSearchInfoDialog();
			sid.anzeigen();
			if (sid.shouldSearch())
			{
				BackgroundSearcher bs = Factory.getBackgroundSearcher();
				bs.search(sid.searchDirectory());
			}
		}
		else
		{
			ArrayList<String> al = new ArrayList<String>();
			al.add(ripper);
			al.add(url);
			if (!Global.windows_recorderdirectory.equals(""))
			{
				al.add("-d");
				al.add(Global.windows_recorderdirectory);
			}
			Command command = Factory.getCommand();
			command.start(al);
		}
		return;
	}
}
