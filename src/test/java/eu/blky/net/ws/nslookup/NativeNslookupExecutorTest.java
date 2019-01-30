package eu.blky.net.ws.nslookup;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class NativeNslookupExecutorTest {

	@Test
	public void testExec() throws IOException, InterruptedException {
		NativeNslookupExecutor o = new NativeNslookupExecutor();
		
		String retval = o.exec("server 8.8.8.8"); 
		//Thread.sleep(11111);
		//o.getProcess().waitFor();
		System.out.println("<<<<<"+retval); 
//		assertTrue(retval, retval.indexOf( "Address:")>0);
		
		retval = o.exec("www.corel.com");
		System.out.println("<<<<<"+retval); 
		assertTrue(retval, retval.indexOf( "Address:")>0);		
		Thread.sleep(11);
		retval = o.exec("www.oracle.com");
		System.out.println("<<<<<"+retval); 
		assertTrue(retval, retval.indexOf( "Address:")>0);		
		Thread.sleep(1111);		
	}

}
