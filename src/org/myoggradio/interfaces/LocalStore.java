package org.myoggradio.interfaces;
import java.util.*;
import org.myoggradio.*;
public interface LocalStore 
{
	public ArrayList<StringPaar> getCommon();
	public void putCommon(ArrayList<StringPaar> al);
}
