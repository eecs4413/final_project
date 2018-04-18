package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Book")
@XmlAccessorType(XmlAccessType.FIELD)
public class BookBean {
	@XmlElement
	public String bid;
	@XmlElement
	public String title;
	@XmlElement
	public String aurthor;
	@XmlElement
	public String price;
	@XmlElement
	public String category;

	public BookBean() {
	}

	public BookBean(String bid, String title,String aurthor, String price, String category) {
		super();
		this.bid = bid;
		this.title = title;
		this.price = price;
		this.category = category;
		this.aurthor = aurthor;
	}

	/**
	 * @return the aurthor
	 */
	public String getAurthor() {
		return aurthor;
	}

	/**
	 * @param aurthor the aurthor to set
	 */
	public void setAurthor(String aurthor) {
		this.aurthor = aurthor;
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
