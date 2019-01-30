package eu.blky.net.ws.nslookup;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.TimerTask;

public class InputStreamreaderTask extends TimerTask  {
	
	private String buffer = "";
	BufferedReader myIn;
	private boolean alive = true;
 
	
	synchronized String readBufferFully() {
		synchronized(myIn) {
			String retval = buffer;
			buffer = "";
			return retval;
		}
		
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
		while(alive) {
			try {
//				buffer += myIn.readLine();
//				buffer +="\n";
//				System.out.println("<<<<<"+buffer);
				String line = myIn.readLine();
				if (null!=line)append(line);
			} catch (IOException e) {
				System.out.println("++++++++++++"+e.getMessage());
				alive = false;		
				
			}
		}
		  
	}

	private synchronized void append(String readLine) {
		synchronized(myIn) {
			buffer += readLine;
			buffer +="\n";
			//System.out.println("......"+buffer);
		}
	}

}
