package org.myoggradio.interfaces;
import javax.swing.JDialog;
import org.myoggradio.*;
public class Factory 
{
	public static Filter getFilter()
	{
		return new SimpleFilter();
	}
	public static LocalStore getLocalStore()
	{
		return new SimpleLocalStore();
	}
	public static TouchURL getTouchURL()
	{
		return new SimpleTouchURL();
	}
	public static SearchInfoDialog getSearchInfoDialog()
	{
		return new SimpleSearchInfoDialog();
	}
	public static BackgroundSearcher getBackgroundSearcher()
	{
		return new SimpleBackgroundSearcher();
	}
	public static NonBlockingSwingMessage getNonBlockingSwingMessage()
	{
		return new SimpleNonBlockingSwingMessage();
	}
	public static Command getCommand()
	{
		return new AdvancedCommand();
	}
	public static Menu getLinuxPreferencesMenu()
	{
		return new LinuxPreferencesMenu();
	}
	public static Menu getWindowsPreferencesMenu()
	{
		return new WindowsPreferencesMenu();
	}
	public static Initialisierung getInitialisierung()
	{
		return new PreferencesInitialisierung();
	}
	public static Menu getMenuCommon()
	{
		return new MenuCommon();
	}
	public static Menu getMenuNewURL()
	{
		return new MenuNewURL();
	}
	public static Menu getProtokolMenu()
	{
		return new SimpleProtokolMenu("Messages");
	}
	public static JDialog getFilterMenu()
	{
		return new SimpleFilterMenu();
	}
	public static Provider getProvider()
	{
		// return new TestProvider();
		return new MyOggRadioProvider();
	}
	public static Menu getMenuLogon()
	{
		return new MenuLogon();
	}
	public static Menu getMenuMain()
	{
		return new MenuMain();
	}
	public static StringPaarPanel getStringPaarPanel()
	{
		return new SimpleStringPaarPanel();
	}
	public static Searcher getSearcher()
	{
		return new SimpleSearcher();
	}
	public static Player getPlayer()
	{
		String os = System.getProperty("os.name");
		Protokol.write("Factory:getPlayer:os:" + os);
		if (os == null) os = "Unkown";
		String id = os.substring(0,1).toUpperCase();
		if (id.equals("W"))
		{
			return new VLC();
		}
		else if (id.equals("L"))
		{
			return new AUDACIOUS();
		}
		else
		{
			Protokol.write("Factory:getPlayer:Operatingsystem unkown");
		}
		return null;
	}
	public static Recorder getRecorder()
	{
		String os = System.getProperty("os.name");
		Protokol.write("Factory:getRecorder:os:" + os);
		if (os == null) os = "Unkown";
		String id = os.substring(0,1).toUpperCase();
		if (id.equals("W"))
		{
			return new StreamRipper32();
		}
		else if (id.equals("L"))
		{
			return new Streamripper();
		}
		else
		{
			Protokol.write("Factory:getRecorder:Operatingsystem unkown");
		}
		return null;
	}
}
