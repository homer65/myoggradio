package org.myoggradio;
import javax.swing.table.*;
import org.myoggradio.interfaces.StringPaarPanelListener;
import java.util.*;
public class StringPaarModel extends AbstractTableModel 
{
	static final long serialVersionUID = 1;
	String[] columnNames = new String[2];
	ArrayList<StringPaar> paare = null;
	ArrayList<StringPaarPanelListener> listener = null;
	public StringPaarModel(ArrayList<StringPaar> al,ArrayList<StringPaarPanelListener> listener)
	{
		this.listener = listener;
		paare = al;
		columnNames[0] = "Radio Transmitter";
		columnNames[1] = "URL";
	}
	public Object getValueAt(int row,int col)
	{
		StringPaar sp = paare.get(row);
		Object o = new Object();
		if (col == 0)
		{
			o = sp.getName();
		}
		if (col == 1)
		{
			o = sp.getWert();
		}
		return o;
	}
	public int getRowCount()
	{
		int i = paare.size();
		return i;
	}
	public int getColumnCount()
	{
		int i = 2;
		return i;
	}
	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	public void setValueAt(Object value,int row,int col)
	{
		StringPaar alt = paare.get(row);
		StringPaar neu = new StringPaar();
		neu.setName(alt.getName());
		neu.setWert(alt.getWert());
		if (value instanceof String)
		{
			String s = (String) value;
			if (col == 0) neu.setName(s);
			if (col == 1) neu.setWert(s);
			for (int i=0;i<listener.size();i++)
			{
				StringPaarPanelListener sppl = listener.get(i);
				sppl.changed(alt,neu);
			}
		}
	}
	public boolean isCellEditable(int row,int col)
	{
		return true;
	}
}
