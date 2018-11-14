package org.myoggradio;
import javax.swing.*;

import org.myoggradio.interfaces.*;
import org.myoggradio.interfaces.Menu;

import png.Locator;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.util.prefs.Preferences;
public class MenuLogon extends Menu implements KeyListener
{
	public static final long serialVersionUID = 0;
	private JLabel lab1 = new JLabel(" User ");
	private JLabel lab2 = new JLabel(" Password ");
	private JLabel labm = new JLabel("Please login");
	private JTextField tf1 = new JTextField(10);
	private JPasswordField tf2 = new JPasswordField(10);
	private JButton butt1 = new JButton("login");
	private JButton butt2 = new JButton("anonymous");
	private JPanel cpan = new JPanel();
	private JPanel ppan = new JPanel();
	private JPanel bpan = new JPanel();
	private Preferences prefs = Preferences.userRoot();
	public MenuLogon()
	{
		super("myoggradio.org");
		Locator locator= new Locator();
		URL urlAnonymous = locator.getURL("anonymous.png");
		Icon iAnonymous = new ImageIcon(urlAnonymous);
		URL urlLogon = locator.getURL("logon.png");
		Icon iLogon = new ImageIcon(urlLogon);
		butt1.setIcon(iLogon);
		butt2.setIcon(iAnonymous);
		bpan.setLayout(new GridLayout(1,2));
		ppan.setLayout(new GridLayout(2,2));
		cpan.setLayout(new BorderLayout());
		bpan.add(butt1);
		bpan.add(butt2);
		ppan.add(lab1);
		ppan.add(tf1);
		ppan.add(lab2);
		ppan.add(tf2);
		cpan.add(ppan,BorderLayout.NORTH);
		cpan.add(bpan,BorderLayout.CENTER);
		cpan.add(labm,BorderLayout.SOUTH);
		setContentPane(cpan);
		butt1.addActionListener(this);
		butt2.addActionListener(this);
		tf1.addKeyListener(this);
		tf2.addKeyListener(this);
		int posx = 0;
		int posy = 0;
		try
		{
			posx = new Integer(prefs.get("jmyoggradioplayer_menulogon_posx","0")).intValue();
			posy = new Integer(prefs.get("jmyoggradioplayer_menulogon_posy","0")).intValue();
			Protokol.write("MenuLogon::posx:" + posx);
			Protokol.write("MenuLogon::posy:" + posy);
		}
		catch (Exception e)
		{
			Protokol.write("MenuLogon::Exception:");
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
		if (quelle == butt1) // login
		{
			Global.benutzer = tf1.getText();
			char [] pw = tf2.getPassword();
			Global.passwort = new String(pw);
			start();
	
		}
		if (quelle == butt2) // ohne login
		{
			Global.benutzer = "anonymous";
			Global.passwort = "anonymous";
			start();
		}
	}
	private void start()
	{
		Provider provider = Factory.getProvider();
		String ok = provider.login(Global.benutzer,Global.passwort).trim();
		if (ok.equals("ok"))
		{
			Menu mm = Factory.getMenuMain();
			mm.anzeigen();
			onClose();
			dispose();
		}
		else
		{
			labm.setText(ok);
		}
	}
	public void keyPressed(KeyEvent ke) 
	{
		int kc = ke.getKeyCode();
		if (kc == 10) // Enter wurde gedrueckt
		{
			Global.benutzer = tf1.getText();
			char [] pw = tf2.getPassword();
			Global.passwort = new String(pw);
			start();
		}
	}
	public void keyReleased(KeyEvent ke) 
	{
			
	}
	public void keyTyped(KeyEvent ke) 
	{
				
	}
	@Override
	public int onClose()
	{
		Point p = this.getLocation();
		Protokol.write("MenuLogon:onClose:posx:" + p.x);
		Protokol.write("MenuLogon:onClose:posy:" + p.y);
		prefs.put("jmyoggradioplayer_menulogon_posx","" + p.x);
		prefs.put("jmyoggradioplayer_menulogon_posy","" + p.y);
		return Menu.dispose;
	}
}
