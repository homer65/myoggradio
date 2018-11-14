package org.myoggradio;
import org.myoggradio.interfaces.*;
public class Main 
{
	public static void main(String[] args) 
	{
		Initialisierung ini = Factory.getInitialisierung();
		int rc = ini.start();
		Protokol.write("Initialization ReturnCode:" + rc);
		Menu m = Factory.getMenuCommon();
		m.anzeigen();
	}
}
