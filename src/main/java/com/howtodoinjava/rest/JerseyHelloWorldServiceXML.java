package com.howtodoinjava.rest;
 
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;

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
import com.howtodoinjava.jersey.XMLEnvelope; 

@Path("/xml")
public class JerseyHelloWorldServiceXML extends JerseyHelloWorldService{

 
	@DELETE
	@Path("/employees/{id}")
	public Response deleteEmployeeById(@PathParam("id") Integer id) {
		return Response.status(202).entity("Employee deleted successfully !!").build();
	}

	@PUT
	@Path("/employees/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response updateEmployeeById(@PathParam("id") Integer id, Employee e) {
		if (e.getName() == null) {
			return Response.status(400).entity("Please provide the employee name !!").build();
		}
		mergeObjects(e, emp);
		return Response.ok().entity(emp).build();
	}

	@POST
	@Path("/employees")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
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
	@Produces(MediaType.APPLICATION_XML)
	public Response getEmployeeById(@PathParam("id") Integer id) {
		if (id < 0) {
			return Response.noContent().build();
		}
		GenericEntity<Employee> entity = new GenericEntity<>(emp, Employee.class);
		return Response.ok().entity(entity).build();
	}

	@GET
	@Path("/employees")
	@Produces(MediaType.APPLICATION_XML)
	public Employees getAllEmployees() {
		return getAll();
	}
 
	@POST
	@Path("/echo/")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Response respMsg(XMLEnvelope xml) {
		String newValue = xml.getXmlbody().replace("PLACEHODERFORDATE", "{\"date\":\""+new Date()+"\"}");
		xml.setXmlbody(newValue);
		return Response.status(200).entity(xml).build();
	}
	
	@GET
	@Path("/echo/{message}")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Response echoMsg(@PathParam("message")XMLEnvelope xml) {
		String newValue = xml.getXmlbody().replace("PLACEHODERFORDATE", "{\"date\":\""+new Date()+"\"}");
		xml.setXmlbody(newValue);
		return Response.status(200).entity(xml).build();
	}
	
	@GET
	@Path("/{message}")
	public Response getMsg(@PathParam("message") String msg) {
		String output = "Message requested : " + msg;
		// Simply return the parameter passed as message
		return Response.status(200).entity(output).build();
	}
 

}