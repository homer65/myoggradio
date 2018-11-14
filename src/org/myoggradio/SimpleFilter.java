package org.myoggradio;
import org.myoggradio.interfaces.*;
import java.util.*;
public class SimpleFilter implements Filter
{
	@Override
	public String get() 
	{
		return Global.filter;
	}
	@Override
	public ArrayList<StringPaar> match(ArrayList<StringPaar> al) 
	{
		ArrayList<StringPaar> erg = new ArrayList<StringPaar>();
		for (int i=0;i<al.size();i++)
		{
			boolean ok = false;
			StringPaar sp = al.get(i);
			String t1 = sp.getName().toLowerCase();
			String t2 = sp.getName().toLowerCase();
			if (Global.filter.length() > 0)
			{
				int x1 = t1.indexOf(Global.filter);
				int x2 = t2.indexOf(Global.filter);
				if (x1 >= 0) ok = true;
				if (x2 >= 0) ok = true;
			}
			else ok = true;
			if (ok) erg.add(sp);
		}
		return erg;
	}
	@Override
	public void reset() 
	{
		Global.filter = "";		
	}
	@Override
	public void set(String s) 
	{
		Global.filter = s.toLowerCase();;
	}
}
