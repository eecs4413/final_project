package bean;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
	private String eventtype;

	



	public VisitEventBean(String bid, String day, String eventtype) {
		super();
		this.day = day;
		
		if(day == null) {
			SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyy");
			Calendar cal = Calendar.getInstance();
			this.day = sd.format(cal.getTime());
		}

		
		this.bid = bid;
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



	public void setEventtype(String eventtype) {
		this.eventtype = eventtype;
	}

}
