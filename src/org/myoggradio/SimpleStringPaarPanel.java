package org.myoggradio;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import org.myoggradio.interfaces.*;
@SuppressWarnings("serial")
public class SimpleStringPaarPanel extends StringPaarPanel
{
	private ArrayList<StringPaarPanelListener> sppl = new ArrayList<StringPaarPanelListener>();
	private String name = "SimpleStringPaarPanel"; 
	private ArrayList<StringPaar> paare = new ArrayList<StringPaar>();
	private JTable table = null;
	private JScrollPane sp = null;
	private StringPaarModel spM = null;
	private ListenerStringPaar listenerSP = null;
	@Override
	public StringPaar getSelected() 
	{
		return listenerSP.selectedPaar;
	}
	@Override
	public void setName(String s) 
	{
		name = s;
	}
	@Override
	public void setPaare(ArrayList<StringPaar> al) 
	{
		paare = al;		
		spM = new StringPaarModel(paare,sppl);
		table = new JTable(spM);
		listenerSP = new ListenerStringPaar(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(listenerSP);
		sp = new JScrollPane(table);
		build();
	}
	public void build()
	{
		this.removeAll();
		setLayout(new BorderLayout());
		add(new JLabel(name),BorderLayout.NORTH);
		add(sp,BorderLayout.CENTER);
		this.setPreferredSize(new Dimension(480,480));
		this.validate();
	}
	@Override
	public void addListener(StringPaarPanelListener listener) 
	{
		sppl.add(listener);		
	}
}
