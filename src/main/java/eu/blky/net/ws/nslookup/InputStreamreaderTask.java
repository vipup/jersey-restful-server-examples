package eu.blky.net.ws.nslookup;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.TimerTask;

public class InputStreamreaderTask extends TimerTask  {
	
	String buffer = "";
	BufferedReader myIn;
	private boolean alive = true;
	
	String readBufferFully() {
		// TODO draft && dirty...
		String retval = buffer;
		buffer = "";
		return retval;
	}
	
	void destroy() throws IOException {
		alive=false;
		myIn.close();
	}

	InputStreamreaderTask (BufferedReader myIn){
		this.myIn = myIn;
	}
	@Override
	public void run() {
		while(alive ) {
			try {
				buffer += myIn.readLine();
				System.out.println("<<<<<"+buffer);
			} catch (IOException e) {
				alive = false;				
			}
		}
		  
	}

}
