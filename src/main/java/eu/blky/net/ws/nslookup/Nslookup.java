package eu.blky.net.ws.nslookup;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.howtodoinjava.jersey.Employees;

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
		if (nsList.size() < 3) {
			// very slow external process
			myNs = new NativeNslookupExecutor(10001);
			nsList.add(myNs);
		} else {
			nsList.get(nsList.size() % 3);
		}
	}

	@DELETE
	@Path("/rm/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response nsList(@PathParam("id") Integer id) throws IOException {
		NativeNslookupExecutor deletedTmp = nsList.remove(id.intValue());

		return Response.ok().entity(deletedTmp).build();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response nsPost(NsCommand cmd) throws IOException, InterruptedException, NoFreeExecutorAvailableException {
		if (cmd == null) {
			return Response.status(400).entity("Please send data!!").build();
		}
		// prevent call the locked NS-lookuper
		try {
			if (myNs.isLocked()) {
				myNs = getNextFreeNsLookuper();
			}

			String resp = myNs.exec(cmd.getCmd());
			cmd.setResponce(resp);
			return Response.ok().entity(resp).build();
		} catch (NullPointerException e) {
			myNs = getNextFreeNsLookuper();
			if (myNs == null)
				throw new NoFreeExecutorAvailableException(e);
			else
				return nsPost(cmd); // repeat the call
		}
	}

	@POST
	@Path("/x2j")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Response nsPostXML(NsCommand cmd)
			throws IOException, InterruptedException, NoFreeExecutorAvailableException, URISyntaxException {
		if (cmd == null) {
			return Response.status(400).entity("Please send data!!").build();
		}
		// prevent call the locked NS-lookuper
		try {
			if (myNs.isLocked()) {
				myNs = getNextFreeNsLookuper();
			}

			String resp = myNs.exec(cmd.getCmd());
			cmd.setResponce(resp);

			return Response.created(new URI("/x2j")).build();
		} catch (NullPointerException e) {
			myNs = getNextFreeNsLookuper();
			if (myNs == null)
				throw new NoFreeExecutorAvailableException(e);
			else
				return nsPostXML(cmd); // repeat the call
		}
	}

	@PUT
	@Path("/j2x")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_XML)
	public Response nsPostJSON(NsCommand cmd)
			throws IOException, InterruptedException, NoFreeExecutorAvailableException {
		if (cmd == null) {
			return Response.status(400).entity("Please send data!!").build();
		}
		// prevent call the locked NS-lookuper
		try {
			if (myNs.isLocked()) {
				myNs = getNextFreeNsLookuper();
			}

			String resp = myNs.exec(cmd.getCmd());
			cmd.setResponce(resp);
			return Response.ok().entity(cmd).build();

		} catch (NullPointerException e) {
			myNs = getNextFreeNsLookuper();
			if (myNs == null)
				throw new NoFreeExecutorAvailableException(e);
			else
				return nsPostJSON(cmd); // repeat the call
		}
	}

	@GET
	@Path("/{nscommand}")
	public Response nsGet(@PathParam("nscommand") String cmd)
			throws IOException, InterruptedException, NoFreeExecutorAvailableException {
		if (cmd == null) {
			return Response.status(400).entity("Please send data!!").build();
		}
		// prevent call the locked NS-lookuper
		try {
			if (myNs.isLocked()) {
				myNs = getNextFreeNsLookuper();
			}

			String resp = myNs.exec(cmd);
			return Response.ok().entity(resp).build();
		} catch (NullPointerException e) {
			myNs = getNextFreeNsLookuper();
			if (myNs == null)
				throw new NoFreeExecutorAvailableException(e);
			else
				return nsGet(cmd); // repeat the call
		}
	}

	private static NativeNslookupExecutor getNextFreeNsLookuper() {
		NativeNslookupExecutor retval = null;
		for (NativeNslookupExecutor next : nsList) {
			if (!next.isLocked()) {
				retval = next;
				break;
			}
		}
		return retval;
	}
 

	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public ExList nsList() throws IOException {
		ExList retval = new ExList();
		retval.setExecutorList(nsList);
		return retval;
	}
}
