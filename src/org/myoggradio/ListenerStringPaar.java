package org.myoggradio;
import javax.swing.*;
import javax.swing.event.*;
public class ListenerStringPaar implements ListSelectionListener 
{
	int last = 0;
	private JTable tab;
	public StringPaar selectedPaar = null;
	static final long serialVersionUID = 1;
	public ListenerStringPaar(JTable tab)
	{
		this.tab = tab;
	}
	public void valueChanged(ListSelectionEvent e)
	{
		if (e.getValueIsAdjusting())
		{
			int FI = e.getFirstIndex();
			int LI = e.getLastIndex();
			if (last == FI)
			{
				last = LI;
			}
			else
			{
				last = FI;
			}
			StringPaarModel GM = (StringPaarModel) tab.getModel();
			selectedPaar = (StringPaar) GM.paare.get(last);
		}
	}
}
