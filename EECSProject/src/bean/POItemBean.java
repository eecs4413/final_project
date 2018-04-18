package bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import model.SearchUtil;

@XmlRootElement(name = "PO")
@XmlAccessorType(XmlAccessType.FIELD)
public class POItemBean {
	@XmlElement
	public String id;
	@XmlElement
	public String bid;
	@XmlElement
	public String price;
	@XmlElement
	public String quantity;
	@XmlElement
	public String comment;
	@XmlElement
	public String day;
	
	public BookBean bookinfo;
	
	public POItemBean() {
		
	}
	
	
	
	public POItemBean( String bid, String price, String quantity, String comment, String day) {
		super();
		
		this.bid = bid;
		this.price = price;
		this.quantity = quantity;
		this.comment = comment;
		
		if(day == null) {
			
			SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyy");
			Calendar cal = Calendar.getInstance();
			this.day = sd.format(cal.getTime());
		}
		
		this.bookinfo = SearchUtil.searchID(this.bid);
		
		
	}
	
	public POItemBean(String id, String bid, String price, String quantity, String comment, String day) {
		super();
		this.id = id;
		this.bid = bid;
		this.price = price;
		this.quantity = quantity;
		this.comment = comment;
		
		if(day == null) {
			
			SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyy");
			Calendar cal = Calendar.getInstance();
			this.day = sd.format(cal.getTime());
		}
		
		
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
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	/**
	 * @return the comment
	 */
	public String getComment() {
		return comment;
	}
	/**
	 * @param comment the comment to set
	 */
	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * @return the quantity
	 */
	public String getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
}
