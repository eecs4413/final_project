package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PO")
@XmlAccessorType(XmlAccessType.FIELD)
public class PO {
	@XmlElement
	private String id;
	@XmlElement
	private String lname;
	@XmlElement
	private String fname;
	@XmlElement
	private String status;
	@XmlElement
	private Address address;
	public PO(String id, String lname, String fname, String status, Address address) {
		super();
		this.id = id;
		this.lname = lname;
		this.fname = fname;
		this.status = status;
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	
 	
	
}
