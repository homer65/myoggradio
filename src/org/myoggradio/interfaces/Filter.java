package org.myoggradio.interfaces;
import java.util.*;
import org.myoggradio.*;
public interface Filter 
{
	public void reset();
	public String get();
	public void set(String s);
	public ArrayList<StringPaar> match(ArrayList<StringPaar> al);
}
