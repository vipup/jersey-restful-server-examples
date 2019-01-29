package com.howtodoinjava.rest;
 
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException; 
import java.util.Date;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 

import com.howtodoinjava.jersey.Employee;
import com.howtodoinjava.jersey.Employees;
import com.howtodoinjava.jersey.Version;
import com.howtodoinjava.jersey.XMLEnvelope; 

@Path("/json")
public class JerseyHelloWorldServiceJSON extends JerseyHelloWorldService{



	@DELETE
	@Path("/employees/{id}")
	public Response deleteEmployeeById(@PathParam("id") Integer id) {
		return Response.status(202).entity("Employee deleted successfully !!").build();
	}

	@PUT
	@Path("/employees/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateEmployeeById(@PathParam("id") Integer id, Employee e) {
		if (e.getName() == null) {
			return Response.status(400).entity("Please provide the employee name !!").build();
		}
		mergeObjects(e, emp);
		return Response.ok().entity(emp).build();
	}

	@POST
	@Path("/employees")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addEmployee(Employee e) throws URISyntaxException {
		if (e == null) {
			return Response.status(400).entity("Please add employee details !!").build();
		}

		if (e.getName() == null) {
			return Response.status(400).entity("Please provide the employee name !!").build();
		}

		return Response.created(new URI("/rest/employees/" + e.getId())).build();
	}

	@GET
	@Path("/employees/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployeeById(@PathParam("id") Integer id) {
		if (id < 0) {
			return Response.noContent().build();
		}

		GenericEntity<Employee> entity = new GenericEntity<>(emp, Employee.class);
		return Response.ok().entity(entity).build();
	}

	@GET
	@Path("/employees")
	@Produces(MediaType.APPLICATION_JSON)
	public Employees getAllEmployees() {
		return getAll();
	}
 
 
	/**
	 * put string PLACEHODERFORDATE into your String-Data, and it will be replaced to current Date()
	 * 
	 * @param xml
	 * @return
	 */
	@POST
	@Path("/xmldatademo")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response upXML(XMLEnvelope xml) {
		String newValue = xml.getXmlbody().replace("PLACEHODERFORDATE", "{\"date\":\""+new Date()+"\"}");
		xml.setXmlbody(newValue);	 
		return Response.status(200).entity(xml).build();
	}
	
	/**
	 * this method just generates the XML-container-Obj
	 * 
	 * <h1>H1</H1>
	 * <h2>H2</H2>  
	 * <form><input id="demo" type="text" value="demo html form input" onclick='myFunction()'/> 
	 * <button onclick="myFunction()">SUBMIT</button></form>
	 * <p style="color: green;">This is a paragraph.</p>
	 * <script>
	 * 		function myFunction() {
	 *   document.getElementById("demo").style.color = "red";
	 *   }
	 * </script>
	 * <label style="color: yellow;">This is the label</label>
	 * <pre>
	 * 	 
	 * {@code
	 * Set<String> s;
	 * System.out.println(s);
	 * }
	 * </pre>
	 * 
	 * @return
	 */
	@GET
	@Path("/xmldatademo")
	@Produces(MediaType.APPLICATION_JSON)
 	public Response getXML() {
		XMLEnvelope xml = new XMLEnvelope();
		xml.setXmlbody(""+new Date());
		return Response.status(200).entity(xml).build();
	}
	
	
	/**
	 * generates API-version in JSON-form
	 * @return version
	 * @throws IOException 
	 */
	@GET
	@Path("/version")
	@Produces(MediaType.APPLICATION_JSON)
 	public Response returnVersion() throws IOException {
		Version versionTmp = getVersion();
		return Response.status(200).entity(versionTmp ).build();
	}	
 
	/**
	 * this is the very simple method 
	 * 
	 * @param msg
	 * @return
	 */
	@GET
	@Path("/{message}")
	public Response getMsg(@PathParam("message") String msg) {
		String output = "Message requested : " + msg;
		// Simply return the parameter passed as message
		return Response.status(200).entity(output).build();
	}
 

}