package eu.blky.net.ws.nslookup;
 
import java.util.List;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement(name = "executorList")
@XmlAccessorType (XmlAccessType.PUBLIC_MEMBER)
public class ExList
{
    @XmlElement(name="executor")
    private List<NativeNslookupExecutor> executorList;
 
    public List<NativeNslookupExecutor> getExecutorList() {
        return executorList;
    }
 
    public void setExecutorList(List<NativeNslookupExecutor> executorList) {
        this.executorList = executorList;
    }
}