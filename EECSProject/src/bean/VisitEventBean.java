package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "VisitEvent")
@XmlAccessorType(XmlAccessType.FIELD)
public class VisitEventBean {

	@XmlElement
	private String day;
	@XmlElement
	private String bid;
	@XmlElement
	private String aid;
	@XmlElement
	private String eventtype;

	public VisitEventBean() {

	}



	public VisitEventBean(String bid, String aid, String day, String eventtype) {
		super();
		this.day = day;
		this.bid = bid;
		this.aid = aid;
		this.eventtype = eventtype;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getEventtype() {
		return eventtype;
	}

	public String getAid() {
		return aid;
	}



	public void setAid(String aid) {
		this.aid = aid;
	}



	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

}
