package org.myoggradio;
import javax.swing.*;
import org.myoggradio.interfaces.Factory;
import org.myoggradio.interfaces.Menu;
import org.myoggradio.interfaces.Protokol;
import org.myoggradio.interfaces.Provider;
import png.Locator;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.prefs.Preferences;
public class MenuNewURL extends Menu
{
	public static final long serialVersionUID = 0;
	private String url = "http://";
	private String radio = "";
	private Provider provider = Factory.getProvider();
	private JPanel cpan = new JPanel();
	private JLabel lab1 = new JLabel("URL");
	private JLabel lab2 = new JLabel("Radio Transmitter");
	private JTextField tf1 = new JTextField(20);
	private JTextField tf2 = new JTextField(20);
	private JButton butt1 = new JButton("add");
	private JButton butt2 = new JButton("cancel");
	private Preferences prefs = Preferences.userRoot();
	public MenuNewURL()
	{
		super("http://www.myoggradio.org");
		Locator locator= new Locator();
		URL urlNew = locator.getURL("new.png");
		Icon iNew = new ImageIcon(urlNew);
		URL urlCancel = locator.getURL("cancel.png");
		Icon iCancel = new ImageIcon(urlCancel);
		butt1.setIcon(iNew);
		butt2.setIcon(iCancel);
		tf1.setText(url);
		tf2.setText(radio);
		cpan.setLayout(new GridLayout(3,2));
		cpan.add(lab1);
		cpan.add(tf1);
		cpan.add(lab2);
		cpan.add(tf2);
		cpan.add(butt1);
		cpan.add(butt2);
		setContentPane(cpan);
		butt1.addActionListener(this);
		butt2.addActionListener(this);
		int posx = 0;
		int posy = 0;
		try
		{
			posx = new Integer(prefs.get("jmyoggradioplayer_menunewurl_posx","0")).intValue();
			posy = new Integer(prefs.get("jmyoggradioplayer_menunewurl_posy","0")).intValue();
			Protokol.write("MenuNewURL::posx:" + posx);
			Protokol.write("MenuNewURL::posy:" + posy);
		}
		catch (Exception e)
		{
			Protokol.write("MenuNewURL::Exception:");
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
		if (quelle == butt1) // add
		{
			url = tf1.getText();
			radio = tf2.getText();
			provider.addNewURL(url,radio,Global.benutzer,Global.passwort);
			onClose();
			dispose();
		}
		if (quelle == butt2) // cancel
		{
			onClose();
			dispose();
		}
	}
	@Override
	public int onClose()
	{
		Point p = this.getLocation();
		Protokol.write("MenuNewURL:onClose:posx:" + p.x);
		Protokol.write("MenuNewURL:onClose:posy:" + p.y);
		prefs.put("jmyoggradioplayer_menunewurl_posx","" + p.x);
		prefs.put("jmyoggradioplayer_menunewurl_posy","" + p.y);
		return Menu.dispose;
	}
}
