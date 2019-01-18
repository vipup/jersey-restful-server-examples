package com.howtodoinjava.jersey;
 
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "employee")
@XmlAccessorType (XmlAccessType.FIELD)
public class Employee
{
    private Integer id;
    private String name;
    private Date dob;
    private Double lat;
    private Double lon;
    private Long phone;
    private String email;
    private Gender sex;
    
    
    
     
    public Employee() {
         
    }
     
    public Employee(Integer id, String name) {
        this.id  = id;
        this.name = name;
    }
 
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
     
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + "]";
    }

	public Gender getSex() {
		return sex;
	}

	public void setSex(Gender sex) {
		this.sex = sex;
	}
}