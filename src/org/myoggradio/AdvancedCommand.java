package org.myoggradio;
import org.myoggradio.interfaces.*;
import java.io.*;
import java.util.*;
public class AdvancedCommand implements Command
{
	public int start(ArrayList<String> cmd)
	{
		int rc = 0;
		if (Global.process != null)
		{
			Protokol.write("AdvancedCommand:Command already running");
			rc = 4;
			return rc;
		}
		try
		{
			String scmd = "";
			for (int i=0;i<cmd.size();i++)
			{
				scmd += cmd.get(i) + " ";
			}
			Protokol.write("AdvancedCommand:start:" + scmd);
			ProcessBuilder pb = new ProcessBuilder(cmd);
			pb.redirectErrorStream(true);
			Process p = pb.start();
			Global.process = p;
			InputStream is = p.getInputStream();
			Reader r = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(r);
			String satz = br.readLine();
			long n = 0;
			while (satz != null)
			{
				n++;
				if (n > 15)
				{
					// Mache nichts
				}
				else if (n == 15)
				{
					Protokol.write("AdvancedCommand:write no more messages");
				}
				else
				{
					Protokol.write(satz);
				}
				satz = br.readLine();
			}
			rc = p.waitFor();
			Protokol.write("AdvancedCommand:end with Return Code:" + rc);
		}
		catch (Exception e)
		{
			Protokol.write("AdvancedCommand:start:Exception:");
			Protokol.write(e.toString());
		}
		if (Global.process != null)
		{
			Global.process.destroy();
		}
		Global.process = null;
		return rc;
	}
}
