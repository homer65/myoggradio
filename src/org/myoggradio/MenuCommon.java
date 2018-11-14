package org.myoggradio;
import java.net.URL;
import java.util.*;
import java.util.prefs.Preferences;
import javax.swing.*;
import org.myoggradio.interfaces.*;
import org.myoggradio.interfaces.Menu;

import png.Locator;
import java.awt.*;
import java.awt.event.*;
public class MenuCommon extends org.myoggradio.interfaces.Menu
{
	public static final long serialVersionUID = 0;
	private LocalStore localStore = Factory.getLocalStore();
	private ArrayList<StringPaar> common = null;
	private StringPaarPanel sppC = Factory.getStringPaarPanel();;
	private Provider provider = Factory.getProvider();
	private JPanel cpan = new JPanel();
	private JButton butt1 = new JButton("play");
	private JButton butt1a = new JButton("record");
	private JButton butt1b = new JButton("stop");
	private JButton butt2 = new JButton("login");
	private JButton butt3 = new JButton("Messages");
	private JMenuBar menu = new JMenuBar();
	private JMenu m1 = new JMenu("Info");
	private JMenuItem m11 = new JMenuItem("Version");
	private JMenuItem m12 = new JMenuItem("Wiki");
	private JMenu m2 = new JMenu("Linux");
	private JMenu m3 = new JMenu("Windows");
	private JMenuItem m21 = new JMenuItem("Preferences");
	private JMenuItem m31 = new JMenuItem("Preferences");
	private JMenuItem m32 = new JMenuItem("Search");
	private Preferences prefs = Preferences.userRoot();
	public MenuCommon()
	{
		super("http://www.myoggradio.org");
		int posx = 0;
		int posy = 0;
		try
		{
			posx = new Integer(prefs.get("jmyoggradioplayer_menucommon_posx","0")).intValue();
			posy = new Integer(prefs.get("jmyoggradioplayer_menucommon_posy","0")).intValue();
			Protokol.write("MenuCommon::posx:" + posx);
			Protokol.write("MenuCommon::posy:" + posy);
		}
		catch (Exception e)
		{
			Protokol.write("MenuCommon::Exception:");
			Protokol.write(e.toString());
			Protokol.write("Will position to (0,0)");
			posx = 0;
			posy = 0;
		}
		this.setLocation(posx,posy);
		pack();
		common = provider.getCommon();
		if (common.size() > Global.minCommon) localStore.putCommon(common);
		if (common.size() < Global.minCommon) common = localStore.getCommon();
		sppC.setName("Common");
		sppC.setPaare(common);
		cpan.setLayout(new BorderLayout());
		cpan.add(sppC,BorderLayout.CENTER);
		cpan.add(buildbpan(),BorderLayout.SOUTH);
		setContentPane(cpan);
		m11.addActionListener(this);
		m12.addActionListener(this);
		m21.addActionListener(this);
		m31.addActionListener(this);
		m32.addActionListener(this);
		m1.add(m11);
		m1.add(m12);
		m2.add(m21);
		m3.add(m31);
		m3.add(m32);
		menu.add(m1);
		menu.add(m2);
		menu.add(m3);
		setJMenuBar(menu);
	}
	public JPanel buildbpan()
	{
		Locator locator= new Locator();
		URL urlPlay = locator.getURL("play.png");
		Icon iPlay = new ImageIcon(urlPlay);
		URL urlStop = locator.getURL("stop.png");
		Icon iStop = new ImageIcon(urlStop);
		URL urlRecord = locator.getURL("record.png");
		Icon iRecord =  new ImageIcon(urlRecord);
		URL urlLogon = locator.getURL("logon.png");
		Icon iLogon = new ImageIcon(urlLogon);
		URL urlMessages = locator.getURL("messages.png");
		Icon iMessages = new ImageIcon(urlMessages);
		butt1.setIcon(iPlay);
		butt1a.setIcon(iRecord);
		butt1b.setIcon(iStop);
		butt2.setIcon(iLogon);
		butt3.setIcon(iMessages);
		JPanel erg = new JPanel();
		erg.setLayout(new GridLayout(2,1));
		JPanel bpan1 = new JPanel();
		JPanel bpan2 = new JPanel();
		bpan1.setLayout(new GridLayout(1,4));
		bpan1.add(butt1);
		bpan1.add(butt1a);
		bpan1.add(butt1b);
		bpan1.add(butt2);
		bpan2.setLayout(new GridLayout(1,1));
		bpan2.add(butt3);
		erg.add(bpan1);
		erg.add(bpan2);
		butt1.addActionListener(this);
		butt1a.addActionListener(this);
		butt1b.addActionListener(this);
		butt2.addActionListener(this);
		butt3.addActionListener(this);
		return erg;
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object quelle = ae.getSource();
		StringPaar spC = sppC.getSelected();
		if (quelle == butt1) // play
		{
			if (spC != null)
			{
				stop();
				Player player = Factory.getPlayer();
				player.play(spC.getWert());
				TouchURL turl = Factory.getTouchURL();
				turl.touch(spC.getWert());
			}
			else
			{
				Protokol.write("MenuMain:actionPerformed:play:nothing selected");
			}
		}
		if (quelle == butt1a) // record
		{
			if (spC != null)
			{
				stop();
				Recorder recorder = Factory.getRecorder();
				recorder.record(spC.getWert());
				TouchURL turl = Factory.getTouchURL();
				turl.touch(spC.getWert());
			}
			else
			{
				Protokol.write("MenuMain:actionPerformed:record:nothing selected");
			}
		}
		if (quelle == butt1b) //stop
		{
			Protokol.write("MenuCommon:butt1b:stop pressed");
			stop();
		}
		if (quelle == butt2) // login
		{
			Menu ml = Factory.getMenuLogon();
			ml.anzeigen();
			dispose();
		}
		if (quelle == butt3) // Messages
		{
			Menu pm = Factory.getProtokolMenu();
			pm.anzeigen();
		}
		if (quelle == m11) // Version Info
		{
			JOptionPane.showMessageDialog(
					null,
					"JMyOggRadioPlayer Version " + Global.version,
					"",
					JOptionPane.INFORMATION_MESSAGE
			);
		}
		if (quelle == m12) // Wiki
		{
			String wiki = "http://wiki.ubuntuusers.de/JMyOggRadioPlayer";
			URL url = null;
			try
			{
				url = new URL(wiki);
				Desktop desktop = Desktop.getDesktop();
				desktop.browse(url.toURI());
			}
			catch (Exception e)
			{
				Protokol.write("MenuCommon:actionPerformed:m12:Exception:");
				Protokol.write(e.toString());
			}
		}
		if (quelle == m21)
		{
			Menu lpm = Factory.getLinuxPreferencesMenu();
			lpm.anzeigen();
		}
		if (quelle == m31)
		{
			Menu wpm = Factory.getWindowsPreferencesMenu();
			wpm.anzeigen();
		}
		if (quelle == m32)
		{
			SearchInfoDialog sid = Factory.getSearchInfoDialog();
			sid.anzeigen();
			if (sid.shouldSearch())
			{
				BackgroundSearcher bs = Factory.getBackgroundSearcher();
				bs.search(sid.searchDirectory());
				sid.kill();
			}
		}
	}
	private void stop()
	{
		if (Global.process != null)
		{
			Global.process.destroy();
			Global.process = null;
			Protokol.write("MenuCommon:stop");
		}
		try
		{
			Thread.sleep(Global.stopDelta);
		}
		catch (Exception e)
		{
			//
		}
	}
	@Override
	public int onClose()
	{
		Point p = this.getLocation();
		Protokol.write("MenuCommon:onClose:posx:" + p.x);
		Protokol.write("MenuCommon:onClose:posy:" + p.y);
		prefs.put("jmyoggradioplayer_menucommon_posx","" + p.x);
		prefs.put("jmyoggradioplayer_menucommon_posy","" + p.y);
		return Menu.dispose;
	}
}
