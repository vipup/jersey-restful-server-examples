package eu.blky.net.ws.nslookup; 

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.howtodoinjava.jersey.Employee;
 
@Path("/ns")
/**
 * 
 * api for nslookup as exernal process
 * 
 * @author i1
 *
 */
public class Nslookup { 
    
	final static List<NativeNslookupExecutor> nsList = new ArrayList<>();
	
	NativeNslookupExecutor myNs = null;
	public Nslookup() throws IOException {
		if (nsList.size() < 3 ) {
			myNs = new NativeNslookupExecutor();
			nsList.add(myNs );
		}else {
			myNs = nsList.get(nsList.size()%3);
		}
	} 
	
    @POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)    
	public Response nsPost(NsCommand cmd) throws IOException, InterruptedException {
		if (cmd == null) {
			return Response.status(400).entity("Please send data!!").build();
		}
		String resp = myNs.exec(cmd.getCmd());
		cmd.setResponce(resp);
		 
		return Response.ok().entity(cmd).build();
	}
	@GET
	@Path("/{nscommand}")
	public Response nsGet(@PathParam("nscommand") String cmd) throws IOException, InterruptedException {
		if (cmd == null) {
			return Response.status(400).entity("Please send data!!").build();
		}
		String resp = myNs.exec(cmd);
		 
		return Response.ok().entity(resp).build();
	}
	
	
	@GET
	@Path("/list")
	public Response nsList() throws IOException { 
		String resp = nsList.toString();
		return Response.ok().entity(resp ).build();
	}
}
