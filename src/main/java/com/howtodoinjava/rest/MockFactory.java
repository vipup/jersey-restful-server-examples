package com.howtodoinjava.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockFactory {
	/** Logger */
	private static final Logger LOG = LoggerFactory.getLogger(JerseyHelloWorldService.class);

	private MockFactory() {
		    throw new IllegalStateException("Utility class");
	}

	public static URL getURL() {
		String surl;
		URL retval = null;
		try {
			surl = getRandom("urls.txt");
			retval = new URL(surl);
		} catch (IOException e) {
			// DO nothing
			e.getMessage();
		}

		return retval;
	}

	private static String getRandom(String path) throws IOException {
		InputStream is = MockFactory.class.getClassLoader().getResourceAsStream(path);
		BufferedReader bin = new BufferedReader(new InputStreamReader(is));
		String retval = null;
		retval = bin.readLine();
		for (int i = 0; i < Math.random() * 1000; i++) {
			retval = bin.readLine();
		} 
		return retval;
	}

	public static String getFirstMidLastname()  {
		try {
			return  getRandom ("first-names.txt")+" "+
					getRandom ("middle-names.txt")+" "+
					getRandom ("names.txt");
		} catch (IOException e) {
			LOG.error("{}",e);
		}
		return null;
				
	}

}
