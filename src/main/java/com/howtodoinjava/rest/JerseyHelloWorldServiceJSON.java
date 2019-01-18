package com.howtodoinjava.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

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

@Path("/json")
public class JerseyHelloWorldServiceJSON {

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
		Employee updatedEmployee = new Employee();

		if (e.getName() == null) {
			return Response.status(400).entity("Please provide the employee name !!").build();
		}

		updatedEmployee.setId(id);
		updatedEmployee.setName(e.getName());

		return Response.ok().entity(updatedEmployee).build();
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
		Employee emp = new Employee();

		emp.setId(id);
		emp.setName("Lokesh Gupta");

		GenericEntity<Employee> entity = new GenericEntity<>(emp, Employee.class);
		return Response.ok().entity(entity).build();
	}

	@GET
	@Path("/employees")
	@Produces(MediaType.APPLICATION_JSON)
	public Employees getAllEmployees() { 
		return  getAll();  
	}

	static final Employees list = new Employees();

	static {
		list.setEmployeeList(new ArrayList<Employee>());
		list.getEmployeeList().add(new Employee(1, "Lokesh Gupta"));
		list.getEmployeeList().add(new Employee(2, "Alex Kolenchiskey"));
		list.getEmployeeList().add(new Employee(3, "David Kameron"));
	}

	private static final Employees getAll() {
		return list;
	}

	@GET
	@Path("/{message}")
	public Response getMsg(@PathParam("message") String msg) {
		String output = "Message requested : " + msg;
		// Simply return the parameter passed as message
		return Response.status(200).entity(output).build();
	}

}