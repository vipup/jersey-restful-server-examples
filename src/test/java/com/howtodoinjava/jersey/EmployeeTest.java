package com.howtodoinjava.jersey;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class EmployeeTest {

	@Test
	public void testEmployee() {
		Employee e = new Employee();
		assertNotNull(e);
	}

	@Test
	public void testEmployeeObjectArray() {
		Employee e = null;
		e = new Employee(11);
		assertEquals(  e.getId(), new Integer(11));
		e = new Employee(11, "Text Name");
		assertEquals(  e.getId(), new Integer(11));
		assertEquals(  e.getName() , "Text Name");

		// ...
		e = new Employee(11, "Text Name", new Date(), Math.PI, Float.MIN_VALUE , Long.MAX_VALUE, "test@java.lang", Gender.BIGENDER, true);
		assertEquals(  e.getId(), new Integer(11));
		assertEquals(  e.getName() , "Text Name");
		assertNotNull( e.getDob() );
		
		assertNotNull( e.getLat()  );
		assertNotNull( e.getLon()  );
		assertNotNull( e.getPhone()   );
		assertNotNull( e.getPhone()   );
		assertEquals(  e.getSex(),  Gender.BIGENDER );
		assertTrue( e.getAlive() );
		
	}

}
