package sample.hello.resources;
 
 

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "XmlContainer")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlContainer {
	
	private String xmlAsString;
	
	public void Change(String _change) {
		xmlAsString += _change;
	}

	public String getXmlAsString() {
		return xmlAsString;
	}

	public void setXmlAsString(String _xmlAsString) {
		xmlAsString = _xmlAsString;
	}
	
}