package org.myoggradio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.prefs.Preferences;
import org.myoggradio.interfaces.*;
import org.myoggradio.interfaces.Menu;
public class WindowsPreferencesMenu extends org.myoggradio.interfaces.Menu
{
	public static final long serialVersionUID= 0;
	private JPanel cpan = new JPanel();
	private JPanel apan = new JPanel();
	private JLabel lab1 = new JLabel("Player Command");
	private JLabel lab3 = new JLabel("Recorder Command");
	private JLabel lab4 = new JLabel("Recorder Directory");
	private JTextField tf1 = new JTextField(20);
	private JTextField tf3 = new JTextField(20);
	private JTextField tf4 = new JTextField(20);
	private JButton butt1 = new JButton("Default");
	private JButton butt3 = new JButton("Default");
	private JButton butt4 = new JButton("Default");
	private JPanel bpan = new JPanel();
	private JPanel bpanx = new JPanel();
	private JButton buttb1 = new JButton("save");
	private JButton buttb2 = new JButton("cancel");
	private Preferences prefs = Preferences.userRoot();
	public WindowsPreferencesMenu()
	{
		super("Windows Preferences");
		tf1.setText(Global.windows_playercommand);
		tf3.setText(Global.windows_recordercommand);
		tf4.setText(Global.windows_recorderdirectory);
		apan.setLayout(new GridLayout(3,3));
		apan.add(lab1);
		apan.add(tf1);
		apan.add(butt1);
		apan.add(lab3);
		apan.add(tf3);
		apan.add(butt3);
		apan.add(lab4);
		apan.add(tf4);
		apan.add(butt4);
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
		butt3.addActionListener(this);
		butt4.addActionListener(this);
		buttb1.addActionListener(this);
		buttb2.addActionListener(this);
		int posx = 0;
		int posy = 0;
		try
		{
			posx = new Integer(prefs.get("jmyoggradioplayer_windowspreferencesmenu_posx","0")).intValue();
			posy = new Integer(prefs.get("jmyoggradioplayer_windowspreferencesmenu_posy","0")).intValue();
			Protokol.write("WindowsPreferencesMenu::posx:" + posx);
			Protokol.write("WindowsPreferencesMenu::posy:" + posy);
		}
		catch (Exception e)
		{
			Protokol.write("WindowsPreferencesMenu::Exception:");
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
			tf1.setText(Global.windows_playercommand_default);
		}
		if (quelle == butt3)
		{
			tf3.setText(Global.windows_recordercommand_default);
		}
		if (quelle == butt4)
		{
			tf4.setText(Global.windows_recorderdirectory_default);
		}
		if (quelle == buttb1)
		{
			String s1 = tf1.getText();
			String s3 = tf3.getText();
			String s4 = tf4.getText();
			Global.windows_playercommand = s1;
			Global.windows_recordercommand = s3;
			Global.windows_recorderdirectory = s4;
			prefs.put("jmyoggradioplayer_windows_playercommand",s1);
			prefs.put("jmyoggradioplayer_windows_recordercommand",s3);
			prefs.put("jmyoggradioplayer_windows_recorderdirectory",s4);
			Protokol.write("WindowsPreferencesMenu:jmyoggradio_windows_playercommand:" + s1);
			Protokol.write("WindowsPreferencesMenu:jmyoggradio_windows_recordercommand:" + s3);
			Protokol.write("WindowsPreferencesMenu:jmyoggradio_windows_recorderdirectory:" + s4);
		}
		if (quelle == buttb2)
		{
			dispose();
		}
	}
	@Override
	public int onClose()
	{
		Point p = this.getLocation();
		Protokol.write("WindowsPreferencesMenu:onClose:posx:" + p.x);
		Protokol.write("WindowsPreferencesMenu:onClose:posy:" + p.y);
		prefs.put("jmyoggradioplayer_windowspreferencesmenu_posx","" + p.x);
		prefs.put("jmyoggradioplayer_windowspreferencesmenu_posy","" + p.y);
		return Menu.dispose;
	}
}
