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
public class MenuMain extends org.myoggradio.interfaces.Menu implements StringPaarPanelListener
{
	public static final long serialVersionUID = 0;
	private Filter filter = Factory.getFilter();
	private ArrayList<StringPaar> favoriten = null;
	private ArrayList<StringPaar> common = null;
	private StringPaarPanel sppF = Factory.getStringPaarPanel();
	private StringPaarPanel sppC = Factory.getStringPaarPanel();;
	private Provider provider = Factory.getProvider();
	private JPanel cpan = new JPanel();
	private JButton butt1 = new JButton("playF");
	private JButton butt1a = new JButton("recordF");
	private JButton butt1b = new JButton("stop");
	private JButton butt2 = new JButton("dropF");
	private JButton butt3 = new JButton("playC");
	private JButton butt3a = new JButton("recordC");
	private JButton butt3b = new JButton("stop");
	private JButton butt4 = new JButton("makeF");
	private JButton butt5 = new JButton("Messages");
	private JButton butt6 = new JButton("logoff");
	private JButton butt7 = new JButton("new URL");
	private JButton butt8 = new JButton("refresh");
	private JMenuBar menu = new JMenuBar();
	private JMenu m1 = new JMenu("Info");
	private JMenuItem m11 = new JMenuItem("Version");
	private JMenuItem m12 = new JMenuItem("Wiki");
	private JMenu m2 = new JMenu("Linux");
	private JMenuItem m21 = new JMenuItem("Preferences");
	private JMenu m3 = new JMenu("Windows");
	private JMenuItem m31 = new JMenuItem("Preferences");
	private JMenu m4 = new JMenu("Filter");
	private JMenuItem m41 = new JMenuItem("Reset Filter");
	private JMenuItem m42 = new JMenuItem("Set Filter");
	private Preferences prefs = Preferences.userRoot();
	public MenuMain()
	{
		super("http://www.myoggradio.org");
		int posx = 0;
		int posy = 0;
		try
		{
			posx = new Integer(prefs.get("jmyoggradioplayer_menumain_posx","0")).intValue();
			posy = new Integer(prefs.get("jmyoggradioplayer_menumain_posy","0")).intValue();
			Protokol.write("MenuMain::posx:" + posx);
			Protokol.write("MenuMain::posy:" + posy);
		}
		catch (Exception e)
		{
			Protokol.write("MenuMain::Exception:");
			Protokol.write(e.toString());
			Protokol.write("Will position to (0,0)");
			posx = 0;
			posy = 0;
		}
		this.setLocation(posx,posy);
		pack();
		favoriten = provider.getFavoriten(Global.benutzer,Global.passwort); 
		common = filter.match(provider.getCommon());
		sppF.addListener(this);
		sppF.setName("Favorite");
		sppC.setName("Common");
		sppF.setPaare(favoriten);
		sppC.setPaare(common);
		cpan.setLayout(new BorderLayout());
		cpan.add(sppF,BorderLayout.WEST);
		cpan.add(sppC,BorderLayout.EAST);
		cpan.add(buildbpan(),BorderLayout.SOUTH);
		setContentPane(cpan);
		m11.addActionListener(this);
		m12.addActionListener(this);
		m21.addActionListener(this);
		m31.addActionListener(this);
		m41.addActionListener(this);
		m42.addActionListener(this);
		m1.add(m11);
		m1.add(m12);
		m2.add(m21);
		m3.add(m31);
		m4.add(m41);
		m4.add(m42);
		menu.add(m1);
		menu.add(m2);
		menu.add(m3);
		menu.add(m4);
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
		URL urlAdd = locator.getURL("add.png");
		Icon iAdd =  new ImageIcon(urlAdd);
		URL urlRemove = locator.getURL("remove.png");
		Icon iRemove =  new ImageIcon(urlRemove);
		URL urlRefresh = locator.getURL("refresh.png");
		Icon iRefresh =  new ImageIcon(urlRefresh);
		URL urlLogoff = locator.getURL("logoff.png");
		Icon iLogoff =  new ImageIcon(urlLogoff);
		URL urlMessages = locator.getURL("messages.png");
		Icon iMessages = new ImageIcon(urlMessages);
		URL urlNew = locator.getURL("new.png");
		Icon iNew = new ImageIcon(urlNew);
		butt1.setIcon(iPlay);
		butt1a.setIcon(iRecord);
		butt1b.setIcon(iStop);
		butt3.setIcon(iPlay);
		butt3a.setIcon(iRecord);
		butt3b.setIcon(iStop);
		butt2.setIcon(iRemove);
		butt4.setIcon(iAdd);
		butt8.setIcon(iRefresh);
		butt6.setIcon(iLogoff);
		butt5.setIcon(iMessages);
		butt7.setIcon(iNew);
		JPanel erg = new JPanel();
		erg.setLayout(new GridLayout(2,1));
		JPanel bpan1 = new JPanel();
		JPanel bpan2 = new JPanel();
		bpan1.setLayout(new GridLayout(1,8));
		bpan1.add(butt1);
		bpan1.add(butt1a);
		bpan1.add(butt1b);
		bpan1.add(butt2);
		bpan1.add(butt3);
		bpan1.add(butt3a);
		bpan1.add(butt3b);
		bpan1.add(butt4);
		bpan2.setLayout(new GridLayout(1,4));
		bpan2.add(butt8);
		bpan2.add(butt7);
		bpan2.add(butt5);
		bpan2.add(butt6);
		erg.add(bpan1);
		erg.add(bpan2);
		butt1.addActionListener(this);
		butt1a.addActionListener(this);
		butt1b.addActionListener(this);
		butt2.addActionListener(this);
		butt3.addActionListener(this);
		butt3a.addActionListener(this);
		butt3b.addActionListener(this);
		butt4.addActionListener(this);
		butt5.addActionListener(this);
		butt6.addActionListener(this);
		butt7.addActionListener(this);
		butt8.addActionListener(this);
		return erg;
	}
	public void actionPerformed(ActionEvent ae)
	{
		Object quelle = ae.getSource();
		StringPaar spF = sppF.getSelected();
		StringPaar spC = sppC.getSelected();
		if (quelle == butt1) // playFavorit
		{
			if (spF != null)
			{
				stop();
				Player player = Factory.getPlayer();
				player.play(spF.getWert());
				TouchURL turl = Factory.getTouchURL();
				turl.touch(spF.getWert());
			}
			else
			{
				Protokol.write("MenuMain:actionPerformed:play Favorit:nothing selected");
			}
		}
		if (quelle == butt1a) // recordFavorit
		{
			if (spF != null)
			{
				stop();
				Recorder recorder = Factory.getRecorder();
				recorder.record(spF.getWert());
				TouchURL turl = Factory.getTouchURL();
				turl.touch(spF.getWert());
			}
			else
			{
				Protokol.write("MenuMain:actionPerformed:record Favorit:nothing selected");
			}
		}
		if (quelle == butt1b) // stop
		{
			Protokol.write("MenuCommon:butt1b:stop pressed");
			stop();
		}
		if (quelle == butt2) // dropFavorit
		{
			if (spF != null)
			{
				provider.dropFavorit(spF,Global.benutzer,Global.passwort);
				favoriten = provider.getFavoriten(Global.benutzer,Global.passwort); 
				sppF.setPaare(favoriten);
			}
			else
			{
				Protokol.write("MenuMain:actionPerformed:dropFavorit:nothing selected");
			}
		}
		if (quelle == butt3) // playCommon
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
				Protokol.write("MenuMain:actionPerformed:play Common:nothing selected");
			}
		}
		if (quelle == butt3a) // recordCommon
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
				Protokol.write("MenuMain:actionPerformed:record Common:nothing selected");
			}
		}
		if (quelle == butt3b)
		{
			Protokol.write("MenuCommon:butt3b:stop pressed");
			stop();
		}
		if (quelle == butt4) // makeFavorit
		{
			if (spC != null)
			{
				provider.addFavorit(spC,Global.benutzer,Global.passwort);
				favoriten = provider.getFavoriten(Global.benutzer,Global.passwort); 
				sppF.setPaare(favoriten);
			}
			else
			{
				Protokol.write("MenuMain:actionPerformed:makeFavorit:nothing selected");
			}
		}
		if (quelle == butt5)
		{
			Menu pm = Factory.getProtokolMenu();
			pm.anzeigen();
		}
		if (quelle == butt6) // logoff
		{
			Menu mc = Factory.getMenuCommon();
			mc.anzeigen();
			onClose();
			dispose();
		}
		if (quelle == butt7) // new URL
		{
			Menu mnu = Factory.getMenuNewURL();
			mnu.anzeigen();
		}
		if (quelle == butt8) // refresh
		{
			Menu mm = Factory.getMenuMain();
			mm.anzeigen();
			onClose();
			dispose();
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
				Protokol.write("MenuMain:actionPerformed:m12:Exception:");
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
		if (quelle == m41) // Reset Filter
		{
			Filter filter = Factory.getFilter();
			filter.reset();
			Menu mm = Factory.getMenuMain();
			mm.anzeigen();
			onClose();
			dispose();
		}
		if (quelle == m42) // Set Filter
		{
			Factory.getFilterMenu();
			Menu mm = Factory.getMenuMain();
			mm.anzeigen();
			onClose();
			dispose();
		}
	}
	private void stop()
	{
		if (Global.process != null)
		{
			Global.process.destroy();
			Global.process = null;
			Protokol.write("MenuMain:stop");
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
	public void changed(StringPaar alt, StringPaar neu) 
	{
		provider.changeFavorit(neu,Global.benutzer,Global.passwort);
		alt.setName(neu.getName());
		alt.setWert(neu.getWert());
	}
	@Override
	public int onClose()
	{
		Point p = this.getLocation();
		Protokol.write("MenuMain:onClose:posx:" + p.x);
		Protokol.write("MenuMain:onClose:posy:" + p.y);
		prefs.put("jmyoggradioplayer_menumain_posx","" + p.x);
		prefs.put("jmyoggradioplayer_menumain_posy","" + p.y);
		return Menu.dispose;
	}
}
