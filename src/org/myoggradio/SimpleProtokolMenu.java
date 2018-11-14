package org.myoggradio;
import org.myoggradio.interfaces.Menu;
import org.myoggradio.interfaces.Protokol;

import javax.swing.*;
import java.awt.*;
import java.util.*;
@SuppressWarnings("serial")
public class SimpleProtokolMenu extends Menu
{
	private JPanel cpan = new JPanel();
	private JTextArea ta = new JTextArea(20,80);
	private JScrollPane sp = new JScrollPane(ta);
	public SimpleProtokolMenu(String name) 
	{
		super(name);
		ArrayList<String> protokol = Protokol.getMessages();
		int n = protokol.size();
		for (int i=0;i<n;i++)
		{
			String satz = protokol.get(n - i -1);
			ta.append(satz + "\n");
		}
		cpan.setLayout(new BorderLayout());
		cpan.add(sp);
		setContentPane(cpan);
	}
}
