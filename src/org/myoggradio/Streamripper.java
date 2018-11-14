package org.myoggradio;
import org.myoggradio.interfaces.*;
import java.util.*;
public class Streamripper extends Thread implements Recorder 
{
	private String streamripper = Global.linux_recordercommand;
	private String url = "";
	public void record(String url)
	{
		this.url = url;
		this.start();
	}
	public void run()
	{
		ArrayList<String> al = new ArrayList<String>();
		al.add(streamripper);
		al.add(url);
		if (!Global.linux_recorderdirectory.equals(""))
		{
			al.add("-d");
			al.add(Global.linux_recorderdirectory);
		}
		Command command = Factory.getCommand();
		command.start(al);
	}
}
