package com.howtodoinjava.rest;

import static org.junit.Assert.*;

import org.junit.Test;

public class MockFactoryTest  {

	@Test
	public void testGetDate() {
		//2018-03-28 06:36:04
		assertNotNull(  MockFactory.getDate() );
	}

}
