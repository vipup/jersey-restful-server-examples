package eu.blky.net.ws.nslookup;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nslookup")
@XmlAccessorType(XmlAccessType.FIELD)
public class NsCommand {
	private String cmd;
	private String responce;

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getResponce() {
		return responce;
	}

	public void setResponce(String responce) {
		this.responce = responce;
	}

 

 
}
