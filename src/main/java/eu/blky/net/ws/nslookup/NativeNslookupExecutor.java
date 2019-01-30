package eu.blky.net.ws.nslookup;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException; 
import java.io.InputStreamReader; 
import java.io.OutputStreamWriter;
import java.util.Timer;

public class NativeNslookupExecutor {
	final Process process;
	final BufferedReader is;
	final BufferedReader err;
	final BufferedWriter os ;
	final InputStreamreaderTask myReader;
	final InputStreamreaderTask myErrorReader;
	
	long myExecTimeoutInMilliseconds = 200;
	private boolean locked;
	
	public NativeNslookupExecutor(long execTimeout) throws IOException{
		this();
		myExecTimeoutInMilliseconds = execTimeout;
	}
		
	public NativeNslookupExecutor() throws IOException{	
		
		
	    ProcessBuilder pBuilder = new ProcessBuilder("/usr/bin/nslookup" );
 
	    pBuilder.redirectErrorStream(true); 
	    process = pBuilder.start();
	    
	    is = new BufferedReader( new InputStreamReader(  process.getInputStream() )  , 1);
	    err = new BufferedReader( new InputStreamReader(  process.getErrorStream() )  ,1 );

	    myReader = new InputStreamreaderTask(is);
	    myErrorReader = new InputStreamreaderTask(err);
	    
	    Timer time = new Timer();  
	    time.schedule(myReader, 1);             // Create reader in 1 ms
	    time.schedule(myErrorReader, 1);             // Create reader in 1 ms
 
	    os = new BufferedWriter( new OutputStreamWriter( process.getOutputStream() ), 1);
	}
	
	

	/**
	 * make synchrony call 
	 * 
	 * @param cmd
	 * @return
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public synchronized String exec(String cmd) throws IOException, InterruptedException {
		
		this.lock();
		
		os.write(cmd);
		os.write("\n"); 
		System.out.println(">>>>>>>"+cmd);
		os.flush(); 
		Thread.sleep(myExecTimeoutInMilliseconds);
		String errStr = myErrorReader.readBufferFully();
		
		this.unlock();
		return "".equals(errStr)?myReader.readBufferFully() : errStr; 
	}



	private synchronized void lock() {
		this.setLocked(true);
	}
	private synchronized void unlock() {
		this.setLocked(false);
	}

	
	public Process getProcess() {
		return  process; 
	}

	public boolean isLocked() {
		return locked;
	}

	private void setLocked(boolean locked) {
		this.locked = locked;
	}

}
