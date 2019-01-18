package com.howtodoinjava.rest;
 
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import com.howtodoinjava.jersey.Gender;
 
@Path("/xml")
public class JerseyHelloWorldService
{
	
	@DELETE
	@Path("/employees/{id}")
	public Response deleteEmployeeById(@PathParam("id") Integer id)
	{      
	    return Response.status(202).entity("Employee deleted successfully !!").build();
	}
	
	@PUT
	@Path("/employees/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response updateEmployeeById(@PathParam("id") Integer id, Employee e)
	{	      
	    if(e.getName() == null) {
	        return Response.status(400).entity("Please provide the employee name !!").build();
	    } 
	    loadData(e, emp);
	    return Response.ok().entity(emp).build();
	}
	
	
	

	@POST
	@Path("/employees")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addEmployee( Employee e ) throws URISyntaxException
	{
	    if(e == null){
	        return Response.status(400).entity("Please add employee details !!").build();
	    }
	     
	    if(e.getName() == null) {
	        return Response.status(400).entity("Please provide the employee name !!").build();
	    }
	     
	    return Response.created(new URI("/rest/employees/"+e.getId())).build();
	}
    
    @GET
    @Path("/employees/{id}")
    @Produces(MediaType.APPLICATION_XML)
    public Response getEmployeeById(@PathParam("id") Integer id)
    {
        if(id  < 0){
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
    @Produces(MediaType.APPLICATION_XML)
    public Employees getAllEmployees() { 
		return getAll();
	}
	
	
	static final Employees list = new Employees();
	static final Employee emp = new Employee(11, "Vasja Pupkin", new Date(), Math.PI, Float.MIN_VALUE , Long.MAX_VALUE, "test@java.ws", Gender.bigender, true);

	static {
		list.setEmployeeList(new ArrayList<Employee>());
		list.getEmployeeList().add(new Employee(1, "Lokesh Gupta"));
		list.getEmployeeList().add(new Employee(2, "Alex Kolenchiskey"));
		list.getEmployeeList().add(new Employee(3, "David Kameron"));
		
		list.getEmployeeList().add(emp);
		
	}
	
	private static final Employees getAll() { 
		return list;
	}
    
    
    @GET
    @Path("/{message}")
    public Response getMsg(@PathParam("message") String msg)
    {
        String output = "Message requested : " + msg;
        //Simply return the parameter passed as message
        return Response.status(200).entity(output).build();
    }

    
    /**
     * copy all properies from objA to objB via accessible getters --to--> getters
     * 
     * @param object_from
     * @param object_to
     * @return
     * @throws Exception
     */
    private static final Object loadData(Employee object_from, Employee object_to ) {
		 
		
		Method[] gettersAndSetters = object_from.getClass().getMethods();

		for (int i = 0; i < gettersAndSetters.length; i++) {
			String methodName = gettersAndSetters[i].getName();
			try {
				if (methodName.startsWith("get")) {
					Object valueTmp = gettersAndSetters[i].invoke(object_from, null);
					if (valueTmp != null )
					object_to.getClass()
							.getMethod(methodName.replaceFirst("get", "set"), gettersAndSetters[i].getReturnType())
							.invoke(object_to, valueTmp );
				} else if (methodName.startsWith("is")) {
					object_to.getClass()
							.getMethod(methodName.replaceFirst("is", "set"), gettersAndSetters[i].getReturnType())
							.invoke(object_to, gettersAndSetters[i].invoke(object_from, null));
				}

			} catch (NoSuchMethodException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return object_to;
	}
    
}