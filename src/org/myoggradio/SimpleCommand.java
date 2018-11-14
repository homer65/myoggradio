package org.myoggradio;
import org.myoggradio.interfaces.*;
import java.io.*;
import java.util.*;
public class SimpleCommand implements Command
{
	public int start(ArrayList<String> cmd)
	{
		int rc = 0;
		if (Global.process != null)
		{
			Protokol.write("SimpleCommand:Command already running");
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
			Protokol.write("SimpleCommand:start:" + scmd);
			ProcessBuilder pb = new ProcessBuilder(cmd);
			Process p = pb.start();
			Global.process = p;
			InputStream is = p.getInputStream();
			Reader r = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(r);
			String satz = br.readLine();
			int n = 0;
			while (satz != null)
			{
				n++;
				if (n > 15)
				{
					Protokol.write("SimpleCommand:write no more messages");
					break;
				}
				Protokol.write(satz);
				satz = br.readLine();
			}
			rc = p.waitFor();
			Protokol.write("SimpleCommand:end with Return Code:" + rc);
		}
		catch (Exception e)
		{
			Protokol.write("SimpleCommand:start:Exception:");
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
