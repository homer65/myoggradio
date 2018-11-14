package org.myoggradio.interfaces;
import java.text.*;
import java.util.*;
public class Protokol 
{
	private static ArrayList<String> al = new ArrayList<String>();
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static void write(String s)
	{
		Date jetzt = new Date();
		String prefix = sdf.format(jetzt);
		System.out.println(prefix + " " + s);
		al.add(prefix + " " + s);
	}
	public static ArrayList<String> getMessages()
	{
		return al;
	}
}
