package com.howtodoinjava.it;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

public class RestIT {
	
	@Test
	public void whenSendingGet_thenMessageIsReturned() throws IOException {
	    String dataTmp= ""+System.currentTimeMillis();
		String url = "http://localhost:8080/jersey-restful-client-example/rest/json/"+dataTmp;
	    URLConnection connection = new URL(url).openConnection();
	    try (InputStream response = connection.getInputStream();
	      Scanner scanner = new Scanner(response)) {
	        String responseBody = scanner.nextLine();
	        System.out.println(responseBody);
	        Assert.assertEquals("Message requested : "+dataTmp, responseBody);
	    }
	}

}
