package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "shipTo")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseOrderShipTo {
	@XmlAttribute
	private String country;
	@XmlElement
	private String name;
	@XmlElement
	private String street;
	@XmlElement
	private String city;
	@XmlElement
	private String province;
	@XmlElement
	private String zip;

	
	
	public PurchaseOrderShipTo() {
		super();
	}

	public PurchaseOrderShipTo( AddressBean addressBean, POBean po) {
		super();
		this.country = addressBean.getCountry();
		this.name = po.getFname() +" "+po.getLname();
		this.street = addressBean.getStreet();
		this.city = addressBean.getCity();
		this.province = addressBean.getProvince();
		this.zip = addressBean.getZip();
	}

	public PurchaseOrderShipTo(String country, String name, String street, String city, String province, String zip) {
		super();
		this.country = country;
		this.name = name;
		this.street = street;
		this.city = city;
		this.province = province;
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
}
