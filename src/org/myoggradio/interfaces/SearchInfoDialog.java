package org.myoggradio.interfaces;
import java.io.*;
public interface SearchInfoDialog 
{
	public void anzeigen();
	public void kill();
	public boolean shouldSearch();
	public File searchDirectory();
}
