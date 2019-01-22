package com.howtodoinjava.rest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.howtodoinjava.jersey.Employee;
import com.howtodoinjava.jersey.Employees;
import com.howtodoinjava.jersey.Gender;


abstract class JerseyHelloWorldService {
	
	public JerseyHelloWorldService() {}

	/** Logger */
	private static final Logger LOG = LoggerFactory.getLogger(JerseyHelloWorldService.class);

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
			list.getEmployeeList().add(
					new Employee(
							++id, 
							MockFactory.getFirstMidLastname(), 
							MockFactory.getDate(), 
							MockFactory.getLongitude(),
							MockFactory.getLatitude(), 
							MockFactory.getPhone(), 
							MockFactory.getEmail(), 
							MockFactory.getGender(), 
							i%3==0, 
							MockFactory.getURL())
					);
		}

		list.getEmployeeList().add(emp);

	}

	protected final Employees getAll() {
		return list;
	}
 
	protected static final Object mergeObjects(Employee object_from, Employee object_to) {

		Method[] gettersAndSetters = object_from.getClass().getMethods();

		for (int i = 0; i < gettersAndSetters.length; i++) {
			String methodName = gettersAndSetters[i].getName();
			try {
				if (methodName.startsWith("get")) {
					Object oArr = null;
					Object valueTmp = gettersAndSetters[i].invoke(object_from, oArr);
					if (valueTmp != null) {
						object_to.getClass()
								.getMethod(methodName.replaceFirst("get", "set"), gettersAndSetters[i].getReturnType())
								.invoke(object_to, valueTmp);
					}
				} else if (methodName.startsWith("is")) {
					Object oArr = null;
					object_to.getClass()
							.getMethod(methodName.replaceFirst("is", "set"), gettersAndSetters[i].getReturnType())
							.invoke(object_to, gettersAndSetters[i].invoke(object_from, oArr));
				}

			} catch (NoSuchMethodException | IllegalArgumentException | IllegalAccessException
					| InvocationTargetException | SecurityException e) {
				LOG.error("{}", e);
			}

		}

		return object_to;
	}

}