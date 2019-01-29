package sample.hello.resources; 

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
@Path("/hello")
/**
 * 
 * here is the new API-part
 * 
 * @author i1
 *
 */
public class HelloResource { 
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello() {
        return "Hello Jersey";
    }
    
    @POST
	@Path("/execute")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response execute(XmlContainer _input) {
		if (_input == null) {
			return Response.status(400).entity("Please send data!!").build();
		}
		
		_input.Change(" changed");
		return Response.ok().entity(_input).build();
	}
    
}
