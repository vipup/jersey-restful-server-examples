package com.howtodoinjava.rest;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howtodoinjava.jersey.Gender;

public class MockFactory {
	/** Logger */
	private static final Logger LOG = LoggerFactory.getLogger(JerseyHelloWorldService.class);
	private static final Gender[] GENDERS = {Gender.FEMALE, Gender.MALE, Gender.NEUTRAL, Gender.TRANS, Gender.NON, Gender.ANOTHER};

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

	static Random r = new Random();
	/** 
	 * it should be not VERY FAST
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private static String getRandom(String path) throws IOException {
		InputStream is = MockFactory.class.getClassLoader().getResourceAsStream(path);
		BufferedReader bin = new BufferedReader(new InputStreamReader(is));
		String retval = null;
		ArrayList<String> lines = new ArrayList<>();
		retval = bin.readLine();
		while (retval != null) {
			lines .add(retval);
			retval = bin.readLine();
		} 
		int index = r.nextInt(lines.size());
		return lines.get(index);
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

	public static Date getDate() {
		try {
			// "2018-04-04 21:22:51"
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"). parse( getRandom ("dates.txt"));
		} catch (IOException | ParseException e) {
			LOG.error("{}",e);
		}
		return new Date();
	}

	public static String getEmail() {
		try {
			return getRandom ("emails.txt");
		} catch (IOException e) {
			return "error@"+e.getMessage();
		}
		
	}

	public static Gender getGender() {
		 return GENDERS[r.nextInt(GENDERS.length)];
	}

	public static Float getLatitude() {
		try {
			return Float.parseFloat(getRandom("lattitudes.txt"));
		} catch (NumberFormatException | IOException e) {
			return Float.MIN_VALUE;
		}
	}

	public static Double getLongitude() {
		try {
			return Double.parseDouble(getRandom("longtitudes.txt"));
		} catch (NumberFormatException | IOException e) {
			return Double.MAX_VALUE;
		}
	}

	public static Long  getPhone() {
		try {
			return Long.parseLong(getRandom("phones.txt"));
		} catch (NumberFormatException | IOException e) {
			return System.currentTimeMillis();
		} 
	}

 
}
