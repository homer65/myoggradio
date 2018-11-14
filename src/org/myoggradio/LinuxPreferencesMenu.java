package org.myoggradio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.prefs.Preferences;
import org.myoggradio.interfaces.*;
import org.myoggradio.interfaces.Menu;
public class LinuxPreferencesMenu extends org.myoggradio.interfaces.Menu
{
	public static final long serialVersionUID= 0;
	private JPanel cpan = new JPanel();
	private JPanel apan = new JPanel();
	private JLabel lab1 = new JLabel("Player Command");
	private JTextField tf1 = new JTextField(20);
	private JButton butt1 = new JButton("Default");
	private JLabel lab2 = new JLabel("Recorder Command");
	private JTextField tf2 = new JTextField(20);
	private JButton butt2 = new JButton("Default");
	private JLabel lab3 = new JLabel("Recorder Directory");
	private JTextField tf3 = new JTextField(20);
	private JButton butt3 = new JButton("Default");
	private JPanel bpan = new JPanel();
	private JPanel bpanx = new JPanel();
	private JButton buttb1 = new JButton("save");
	private JButton buttb2 = new JButton("cancel");
	private Preferences prefs = Preferences.userRoot();
	public LinuxPreferencesMenu()
	{
		super("Linux Preferences");
		tf1.setText(Global.linux_playercommand);
		tf2.setText(Global.linux_recordercommand);
		tf3.setText(Global.linux_recorderdirectory);
		apan.setLayout(new GridLayout(3,3));
		apan.add(lab1);
		apan.add(tf1);
		apan.add(butt1);
		apan.add(lab2);
		apan.add(tf2);
		apan.add(butt2);
		apan.add(lab3);
		apan.add(tf3);
		apan.add(butt3);
		bpan.setLayout(new GridLayout(1,2));
		bpan.add(buttb1);
		bpan.add(buttb2);
		bpanx.setLayout(new BorderLayout());
		bpanx.add(bpan,BorderLayout.WEST);
		cpan.setLayout(new BorderLayout());
		cpan.add(apan,BorderLayout.CENTER);
		cpan.add(bpanx,BorderLayout.SOUTH);
		setContentPane(cpan);
		butt1.addActionListener(this);
		butt2.addActionListener(this);
		butt3.addActionListener(this);
		buttb1.addActionListener(this);
		buttb2.addActionListener(this);
		int posx = 0;
		int posy = 0;
		try
		{
			posx = new Integer(prefs.get("jmyoggradioplayer_linuxpreferencesmenu_posx","0")).intValue();
			posy = new Integer(prefs.get("jmyoggradioplayer_linuxpreferencesmenu_posy","0")).intValue();
			Protokol.write("LinuxPreferencesMenu::posx:" + posx);
			Protokol.write("LinuxPreferencesMenu::posy:" + posy);
		}
		catch (Exception e)
		{
			Protokol.write("LinuxPreferencesMenu::Exception:");
			Protokol.write(e.toString());
			Protokol.write("Will position to (0,0)");
			posx = 0;
			posy = 0;
		}
		this.setLocation(posx,posy);
		pack();
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object quelle = ae.getSource();
		if (quelle == butt1)
		{
			tf1.setText(Global.linux_playercommand_default);
		}
		if (quelle == butt2)
		{
			tf2.setText(Global.linux_recordercommand_default);
		}
		if (quelle == butt3)
		{
			tf3.setText(Global.linux_recorderdirectory_default);
		}
		if (quelle == buttb1)
		{
			String s1 = tf1.getText();
			String s2 = tf2.getText();
			String s3 = tf3.getText();
			Global.linux_playercommand = s1;
			Global.linux_recordercommand = s2;
			Global.linux_recorderdirectory = s3;
			prefs.put("jmyoggradioplayer_linux_playercommand",s1);
			prefs.put("jmyoggradioplayer_linux_recordercommand",s2);
			prefs.put("jmyoggradioplayer_linux_recorderdirectory",s3);
			Protokol.write("LinuxPreferencesMenu:jmyoggradio_linux_playercommand:" + s1);
			Protokol.write("LinuxPreferencesMenu:jmyoggradio_linux_recordercommand:" + s2);
			Protokol.write("LinuxPreferencesMenu:jmyoggradio_linux_recorderdirectory:" + s3);
		}
		if (quelle == buttb2)
		{
			onClose();
			dispose();
		}
	}
	@Override
	public int onClose()
	{
		Point p = this.getLocation();
		Protokol.write("LinuxPreferencesMenu:onClose:posx:" + p.x);
		Protokol.write("LinuxPreferencesMenu:onClose:posy:" + p.y);
		prefs.put("jmyoggradioplayer_linuxpreferencesmenu_posx","" + p.x);
		prefs.put("jmyoggradioplayer_linuxpreferencesmenu_posy","" + p.y);
		return Menu.dispose;
	}
}
