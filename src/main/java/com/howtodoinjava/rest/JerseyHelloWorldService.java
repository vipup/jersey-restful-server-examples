package com.howtodoinjava.rest;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howtodoinjava.jersey.Employee;
import com.howtodoinjava.jersey.Employees;
import com.howtodoinjava.jersey.Gender;
import com.howtodoinjava.jersey.Version;


abstract class JerseyHelloWorldService {
	
	@Context ServletContext context;
	
	public JerseyHelloWorldService() {}

	/** Logger */
	private static final Logger LOG = LoggerFactory.getLogger(JerseyHelloWorldService.class);
	static {
		LOG.debug("JerseyHelloWorldService inited");
	}

	private static final Employees list = new Employees();
	protected static final Employee emp = new Employee(11, "Rest Domin", new Date(0), Math.PI, Float.MIN_VALUE, Long.MAX_VALUE,
			"rest@dom.xml", Gender.BIGENDER, true, MockFactory.getURL());

	static {
		list.setEmployeeList(new ArrayList<Employee>());
		int id=0;
		list.getEmployeeList().add(new Employee(++id, MockFactory.getFirstMidLastname()));
		list.getEmployeeList().add(new Employee(++id, MockFactory.getFirstMidLastname()));
		list.getEmployeeList().add(new Employee(++id, MockFactory.getFirstMidLastname())); 
		
		for (int i=0; i<15;i++) {
			Employee employee = new Employee(
					++id, 
					MockFactory.getFirstMidLastname(), 
					MockFactory.getDate(), 
					MockFactory.getLongitude(),
					MockFactory.getLatitude(), 
					MockFactory.getPhone(), 
					MockFactory.getEmail(), 
					MockFactory.getGender(), 
					i%3==0, 
					MockFactory.getURL());
			list.getEmployeeList().add(
					employee
					);
		}

		list.getEmployeeList().add(emp);

	}

	protected final Employees getAll() {
		return list;
	}
 
	protected Version getVersion() throws IOException {
		Manifest mf = new Manifest();
		mf.read (  context.getResourceAsStream("/META-INF/MANIFEST.MF") );
		Attributes atts = mf.getMainAttributes();
		String ver = atts.getValue("Implementation-Build");
		Version versionTmp = new Version(ver);
		return versionTmp;
	}

	protected static final Object mergeObjects(Employee object_from, Employee object_to) {

		Method[] gettersAndSetters = object_from.getClass().getMethods();

		for (int i = 0; i < gettersAndSetters.length; i++) {
			Method getMethodTmp = gettersAndSetters[i];
			String methodName = getMethodTmp.getName();
			try {
				if (methodName.startsWith("get")) {
					 
					Object valueTmp = getMethodTmp.invoke(object_from);  
					if (valueTmp != null) {
						Method setMethodTMP = object_to.getClass()
								.getMethod(methodName.replaceFirst("get", "set"), getMethodTmp.getReturnType());
						setMethodTMP
								.invoke(object_to, valueTmp);
					}
				} else if (methodName.startsWith("is")) {
					Object oArr = null;
					object_to.getClass()
							.getMethod(methodName.replaceFirst("is", "set"), getMethodTmp.getReturnType())
							.invoke(object_to, getMethodTmp.invoke(object_from, oArr));
				}

			} catch (NoSuchMethodException | IllegalArgumentException | IllegalAccessException
					| InvocationTargetException | SecurityException e) {
				LOG.error("{}", e);
			}

		}

		return object_to;
	}

}