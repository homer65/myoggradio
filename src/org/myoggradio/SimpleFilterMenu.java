package org.myoggradio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.myoggradio.interfaces.*;
public class SimpleFilterMenu extends JDialog implements ActionListener
{
	public static final long serialVersionUID= 0;
	private JPanel cpan = new JPanel();
	private JTextField tf1 = new JTextField(20);
	private JButton butt1 = new JButton("Set");
	private JButton butt2 = new JButton("Cancel");
	private JButton butt3 = new JButton("Reset");
	private JPanel bpan = new JPanel();
	private Filter filter = Factory.getFilter();
	public SimpleFilterMenu()
	{
		tf1.setText(filter.get());
		bpan.setLayout(new GridLayout(1,3));
		bpan.add(butt1);
		bpan.add(butt2);
		bpan.add(butt3);
		cpan.setLayout(new BorderLayout());
		cpan.add(tf1,BorderLayout.CENTER);
		cpan.add(bpan,BorderLayout.SOUTH);
		setContentPane(cpan);
		butt1.addActionListener(this);
		butt2.addActionListener(this);
		butt3.addActionListener(this);
		pack();
		this.setModal(true);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object quelle = ae.getSource();
		if (quelle == butt1) // Set
		{
			filter.set(tf1.getText());
		}
		if (quelle == butt2) // Cancel
		{
			
		}
		if (quelle == butt3) // Reset
		{
			filter.set("");
		}
		dispose();
	}
}
