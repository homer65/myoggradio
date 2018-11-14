package org.myoggradio;
import org.myoggradio.interfaces.*;
import java.util.*;
public class AUDACIOUS extends Thread implements Player 
{
	private String audacious = Global.linux_playercommand;
	private String url = "";
	public void play(String url)
	{
		this.url = url;
		this.start();
	}
	public void run()
	{
		ArrayList<String> al = new ArrayList<String>();
		al.add(audacious);
		al.add(url);
		Command command = Factory.getCommand();
		command.start(al);
	}
}
