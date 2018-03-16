package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Book")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookBean {
	@XmlElement
	private String bid;
	@XmlElement
	private String title;
	@XmlElement
	private String price;
	@XmlElement
	private String category;

	public BookBean() {

	}

	public BookBean(String bid, String title, String price, String category) {
		super();
		this.bid = bid;
		this.title = title;
		this.price = price;
		this.category = category;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
