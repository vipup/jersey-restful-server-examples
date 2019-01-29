package com.howtodoinjava.it;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import com.howtodoinjava.jersey.Version;
import com.sun.jersey.core.spi.scanning.Scanner;
 
/**
 * 
 * this test should be started ONLY after deploying the WAR into tomcat, and rolledUP.
 * 
 * responce(s) should be tested on aproppriate app.
 * 
 * in any fails on "IntegrationTest" the deployment should be rollBACKed.
 * 
 * @author i1
 *
 */
public class RestIT {
 
	@Test
	public void checkVersion() throws IOException {
		
	     
		String url = "http://localhost:8080/jersey-restful-server-example/rest/json/version";
	    URLConnection connection = new URL(url).openConnection();
	    String restTmp="";
	    try (InputStream response = connection.getInputStream();
	      java.util.Scanner scanner = new java.util.Scanner(response)) {
	        String responseBody = scanner.nextLine();
	        System.out.println(responseBody);
	        restTmp+=responseBody;
	    }
		
		Assert.assertTrue(restTmp,   restTmp.indexOf( "\"version\":\"")>0);
	     
	}

}
