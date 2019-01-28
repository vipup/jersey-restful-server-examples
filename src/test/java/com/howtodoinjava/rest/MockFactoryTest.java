package com.howtodoinjava.rest;

import static org.junit.Assert.*;

import javax.ws.rs.core.Response;

import org.junit.Test;

import com.howtodoinjava.jersey.Employee;

public class MockFactoryTest  {

	@Test
	public void testGetDate() {
		//2018-03-28 06:36:04
		assertNotNull(  MockFactory.getDate() );
		
		JerseyHelloWorldServiceXML x = new JerseyHelloWorldServiceXML();
		
		assertEquals( 19, x.getAll().getEmployeeList().size() );
		Employee e = new Employee();
		e.setEmail("test@junit.java");
		e.setName(""+this.getClass().getName());
		Response y = x.updateEmployeeById(5, e );
		assertEquals( 200, y.getStatus());
		assertEquals( "test@junit.java", x.getAll().getEmployeeList().get(18).getEmail()  ); 
	}

}
