package com.howtodoinjava.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.spi.container.servlet.ServletContainer;

//com.sun.jersey.spi.container.servlet.
//org.glassfish.jersey.servlet.ServletContainer
public class RestServlet extends ServletContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3124679840668259416L;
	/** Logger */
	private static final Logger LOG = LoggerFactory.getLogger(RestServlet.class);
	
	static {
		LOG.debug("RestServlet inited successully.");
	}
 

}
