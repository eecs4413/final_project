package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Address")
@XmlAccessorType(XmlAccessType.FIELD)

public class AddressBean {

	@XmlElement
	private String id;
	@XmlElement
	private String street;
	@XmlElement
	private String province;
	@XmlElement
	private String country;
	@XmlElement
	private String zip;
	@XmlElement
	private String phone;
	public AddressBean(String id, String street, String province, String country, String zip, String phone) {
		super();
		this.id = id;
		this.street = street;
		this.province = province;
		this.country = country;
		this.zip = zip;
		this.phone = phone;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
