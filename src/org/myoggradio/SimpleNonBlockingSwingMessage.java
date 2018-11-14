package org.myoggradio;
import org.myoggradio.interfaces.*;
import javax.swing.*;
public class SimpleNonBlockingSwingMessage extends Thread implements NonBlockingSwingMessage
{
	private String msg = null;
	public void show(String msg)
	{
		this.msg = msg;
		start();
	}
	public void run()
	{
		JOptionPane.showMessageDialog(null,msg);
	}
}
