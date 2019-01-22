package com.howtodoinjava.jersey;

import java.net.URL;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
	private Integer id;
	private String name;
	private Date dob;
	private Double lat;
	private Float lon;
	private Long phone;
	private String email;
	private Gender sex;
	private Boolean alive;
	private URL lasturl;

	public Employee() {

	}

	public Employee(Object... o) {
		this();
		int index=0;
		try {
			this.setId((Integer) o[index++]);
			this.setName((String) o[index++]);
			this.setDob( (Date) o[index++]);
			this.setLat( (Double) o[index++]);
			this.setLon( (Float) o[index++]);
			this.setPhone((Long) o[index++]);
			this.setEmail((String) o[index++]);
			this.setSex( (Gender) o[index++]);
			this.setAlive((Boolean) o[index++]);
			this.setLasturl( (URL) o[index++]);
			
		}catch(Exception e) {
			// DO nothing
			e.getMessage();
		}
	}

	public Employee(Integer id, String name) {
		this.id = id;
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Float getLon() {
		return lon;
	}

	public void setLon(Float lon) {
		this.lon = lon;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	public URL getLasturl() {
		return lasturl;
	}

	public void setLasturl(URL lasturl) {
		this.lasturl = lasturl;
	}
}