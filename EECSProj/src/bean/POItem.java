package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "PO")
@XmlAccessorType(XmlAccessType.FIELD)
public class POItem {
	@XmlElement
	private String id;
	@XmlElement
	private String bid;
	@XmlElement
	private String price;
	public POItem(String id, String bid, String price) {
		super();
		this.id = id;
		this.bid = bid;
		this.price = price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
}
