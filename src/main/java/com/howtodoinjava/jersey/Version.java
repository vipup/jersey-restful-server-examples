package com.howtodoinjava.jersey;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Version {

	private String value;

	public Version() {}
			
	public Version(String ver) {
		this.setVersion(ver);
	}

	public String getVersion() {
		return value;
	}

	public void setVersion(String version) {
		this.value = version;
	}

}
