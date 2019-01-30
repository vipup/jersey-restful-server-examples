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
	public String exec(String cmd) throws IOException, InterruptedException {
		os.write(cmd);
		os.write("\n"); 
		System.out.println(">>>>>>>"+cmd);
		os.flush();
		// TODO should 	I wait??!
		Thread.sleep(1111);
		String errStr = myErrorReader.readBufferFully(); 
		return "".equals(errStr)?myReader.readBufferFully() : errStr; 
	}



	public Process getProcess() {
		return  process; 
	}

}
