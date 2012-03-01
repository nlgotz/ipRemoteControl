package gotz.remoteControl;

import java.io.*;
import java.net.*;

/**
 * Allows the Application to Execute the Actions on the TV
 * 
 * @author nathan
 * 
 */
public class Execute {

	private String hostName;
	private int hostPort;
	public Socket s;
	private int volume;
	private boolean powerToggle;

//	public Execute() {
//		this.hostName = "192.168.1.197";
//		this.hostPort = 10002;
//		this.powerToggle = false;
//	}
//
//	public Execute(int hostPort) {
//		this.hostName = "192.168.1.197";
//		this.hostPort = hostPort;
//		this.powerToggle = false;
//	}
//
//	public Execute(String hostName) {
//		this.hostName = hostName;
//		this.hostPort = 10002;
//		this.powerToggle = false;
//	}

	public Execute(String hostName, int hostPort) {
		this.hostName = hostName;
		this.hostPort = hostPort;
		this.powerToggle = false;
	}

	public int getHostPort() {
		return this.hostPort;
	}

	public String getHostName() {
		return this.hostName;
	}

	private void execute(String cmd) {
		try {
			System.out.println(cmd);
			s = new Socket(this.hostName, this.hostPort);
			Writer w = new OutputStreamWriter(s.getOutputStream());
			w.write(cmd + "\r");
			w.flush();
			System.out.println("cmd: " + cmd);
			s.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void volumeUp() {

		this.execute("RCKY33  ");
	}

	public void volumeDown() {
		this.execute("RCKY32  ");
	}

	public void adjustVolume(int vol) {
		if (vol >= 0 || vol <= 60) {
			this.volume = vol;
			this.execute("VOLM" + this.volume + "  ");
		}
	}

	public void mute() {
		this.execute("MUTE0   ");
	}

	public void allowPowerControl() {
		this.execute("RSPW2   ");
	}
	
	public void denyPowerControl() {
		this.execute("RSPW0   ");
	}
	
	public void power() {
		if(this.powerToggle) {
			this.powerOff();
			this.powerToggle = false;
		} else {
			this.powerOn();
			this.powerToggle = true;
		}
	}
	
	public void powerOn() {
		this.execute("POWR1   ");
	}

	public void powerOff() {
		this.execute("POWR0   ");
	}

	public void channelUp() {
		this.execute("CHUP    ");
	}

	public void channelDown() {
		this.execute("CHDWx   ");
	}

	/**
	 * FIXME: Figure out the proper channel command
	 * 
	 * @param channel
	 */
	public void adjustChannel(CharSequence channel) {
		if (channel.toString().contains(".")) {
			String [] chan = channel.toString().split("\\.");
			String upper = chan[0];
			String lower = chan[1];
			
			if (upper.length() == 1) {
				upper = "00" + upper;
			} else if (upper.length() == 2) {
				upper = "0" + upper;
			}
			
			if (lower.length() == 1) {
				lower = "00" + lower;
			} else if (lower.length() == 2) {
				lower = "0" + lower;
			}
			
			//DC2U***_
			this.execute("DC2U" + upper + " ");
			//DC2L***_
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.execute("DC2L" + lower + " ");
		} else {
			if (channel.length() == 1) {
				channel = "00" + channel;
			} else if (channel.length() == 2) {
				channel = "0" + channel;
			}
			this.execute("DCCH" + channel + " ");
		}
	}
	
	public void flashback() {
		this.execute("RCKY30  ");
	}
	
	public void selectTV() {
		this.execute("ITVD0   ");
	}
	
	public void selectInput(int n) {
		this.execute("IAVD" + n + "   ");
	}

}
