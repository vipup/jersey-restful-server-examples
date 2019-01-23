package com.howtodoinjava.jersey;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xmlenvelop")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLEnvelope {
	private String xmlbody;

	public String getXmlbody() {
		return xmlbody;
	}

	public void setXmlbody(String xmlbody) {
		this.xmlbody = xmlbody;
	}
}
