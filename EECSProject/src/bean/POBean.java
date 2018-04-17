package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PO")
@XmlAccessorType(XmlAccessType.FIELD)
public class POBean {
	@XmlElement
	private String id;
	@XmlElement
	private String lname;
	@XmlElement
	private String fname;
	@XmlElement
	private String aid;
	@XmlElement
	private String status;
	@XmlElement
	private AddressBean address;
	@XmlElement
	private String comment;

	public POBean() {
		
	}

	

	public POBean(String id, String lname, String fname, String aid, String status, AddressBean address,
			String comment) {
		super();
		this.id = id;
		this.lname = lname;
		this.fname = fname;
		this.aid = aid;
		this.status = status;
		this.address = address;
		this.comment = comment;
	}



	/**
	 * @return the lname
	 */
	public String getLname() {
		return lname;
	}



	/**
	 * @param lname the lname to set
	 */
	public void setLname(String lname) {
		this.lname = lname;
	}



	/**
	 * @return the fname
	 */
	public String getFname() {
		return fname;
	}



	/**
	 * @param fname the fname to set
	 */
	public void setFname(String fname) {
		this.fname = fname;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AddressBean getAddress() {
		return address;
	}

	public void setAddress(AddressBean address) {
		this.address = address;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
}
