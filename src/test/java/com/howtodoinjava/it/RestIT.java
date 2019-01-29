package com.howtodoinjava.it;

import java.io.IOException; 

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Test;

import com.howtodoinjava.jersey.Version;
 
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
	protected Version getVersion() throws IOException {
		String ver = "3d03202b";
		Version versionTmp = new Version(ver);
		return versionTmp;
	}
	
	@Test
	public void checkVersion() throws IOException {
		
	    Version versionTmp = getVersion();
		System.out.println("######################"+ versionTmp.getVersion() +"######################");
		
		Client client = ClientBuilder.newClient();
		// http://localhost:8080/jersey-restful-server-example/rest/xml/employees
		WebTarget webTarget = client.target("http://localhost:8080/jersey-restful-server-example/rest")
				.path("json")
				.path("version");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get(); 
		System.out.println(response.getStatus());
		System.out.println(response.getLength());
		System.out.println(response.toString() );
		System.out.println(response.getEntity() );
		Version vTmp = response.readEntity(Version.class);
		Assert.assertEquals( vTmp.getVersion()  , versionTmp.getVersion());
	     
	}

}
