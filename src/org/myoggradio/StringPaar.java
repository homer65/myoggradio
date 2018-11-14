package org.myoggradio;
public class StringPaar 
{
	private String name = "";
	private String wert = "";
	public StringPaar()
	{
		
	}
	public StringPaar(String sn,String sw)
	{
		name = sn;
		wert = sw;
	}
	public String getName()
	{
		return name;
	}
	public String getWert()
	{
		return wert;
	}
	public void setName(String s)
	{
		name = s;
	}
	public void setWert(String s)
	{
		wert = s;
	}
	public String toString()
	{
		String erg = name + ":" + wert;
		return erg;
	}
}
