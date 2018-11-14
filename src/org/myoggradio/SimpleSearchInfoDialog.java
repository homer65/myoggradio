package org.myoggradio;
import org.myoggradio.interfaces.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
public class SimpleSearchInfoDialog extends JDialog implements ActionListener,SearchInfoDialog
{
	public static final long serialVersionUID = 0;
	private File sDirectory = null;
	private boolean sSearch = false;
	private JLabel lab1 = new JLabel("Should extend Search in:");
	private JButton butt1 = new JButton("yes");
	private JButton butt2 = new JButton("no");
	private JPanel bpan = new JPanel();
	private JPanel fpan = new JPanel();
	private JPanel cpan = new JPanel();
	private ButtonGroup bg = new ButtonGroup();
	private JRadioButton[] buttons = null;
	private File[] roots = null;
	public boolean shouldSearch()
	{
		return sSearch;
	}
	public File searchDirectory()
	{
		return sDirectory;
	}
	public void anzeigen()
	{
		this.setModal(true);
		this.setTitle("myoggradio.org");
		buildfpan();
		bpan.setLayout(new GridLayout(1,2));
		bpan.add(butt1);
		bpan.add(butt2);
		cpan.setLayout(new BorderLayout());
		cpan.add(lab1,BorderLayout.NORTH);
		cpan.add(fpan,BorderLayout.CENTER);
		cpan.add(bpan,BorderLayout.SOUTH);
		butt1.addActionListener(this);
		butt2.addActionListener(this);
		setContentPane(cpan);
		pack();
		setVisible(true);
	}
	private void buildfpan()
	{
		roots = File.listRoots();
		buttons = new JRadioButton[roots.length];
		fpan.setLayout(new GridLayout(roots.length,1));
		for (int i=0;i<roots.length;i++)
		{
			File root = roots[i];
			String pfad = root.getAbsolutePath();
			buttons[i] = new JRadioButton(pfad);
			bg.add(buttons[i]);
			fpan.add(buttons[i]);
			buttons[i].setSelected(true);
		}
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object quelle = ae.getSource();
		if (quelle == butt1)
		{
			sSearch = true;
			for (int i=0;i<buttons.length;i++)
			{
				JRadioButton button = buttons[i];
				if (button.isSelected())
				{
					sDirectory = roots[i];
				}
			}
			setVisible(false);
		}
		if (quelle == butt2)
		{
			sSearch = false;
			setVisible(false);
		}
	}
	public void kill()
	{
		dispose();
	}
}
