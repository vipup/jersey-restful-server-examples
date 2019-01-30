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
	final BufferedWriter os ;
	final InputStreamreaderTask myReader;
	
	public NativeNslookupExecutor() throws IOException{
	    ProcessBuilder pBuilder = new ProcessBuilder("nslookup");
 
	    pBuilder.redirectErrorStream(true); 
	    process = pBuilder.start();
	    is = new BufferedReader( new InputStreamReader(  process.getInputStream() )  );

	    myReader = new InputStreamreaderTask(is);
	    Timer time = new Timer();  
        time.schedule(myReader, 1);             // Create reader in 1 ms
 
	    os = new BufferedWriter( new OutputStreamWriter( process.getOutputStream() ));
	}

	/**
	 * make synchrony call 
	 * 
	 * @param cmd
	 * @return
	 * @throws IOException
	 */
	public String exec(String cmd) throws IOException {
		os.write(cmd);
		System.out.println(">>>>>>>"+cmd);
		os.flush();
		// TODO should 	I wait??!
		return myReader.readBufferFully(); 
	}

}
