package org.myoggradio.interfaces;
import java.util.*;
import javax.swing.*;
import org.myoggradio.StringPaar;
@SuppressWarnings("serial")
public abstract class StringPaarPanel extends JPanel
{
	public abstract void setName(String name);
	public abstract void setPaare(ArrayList<StringPaar> al);
	public abstract StringPaar getSelected();
	public abstract void addListener(StringPaarPanelListener listener);
}
