package bean;

public class BuyerStat {
	
	String aid;
	float spent;
	String zip;
	public BuyerStat(String aid, float spent, String zip) {
		super();
		this.aid = aid;
		this.spent = spent;
		this.zip = zip;
	}
	public BuyerStat() {
		super();
	}
	/**
	 * @return the aid
	 */
	public String getAid() {
		return aid;
	}
	/**
	 * @param aid the aid to set
	 */
	public void setAid(String aid) {
		this.aid = aid;
	}
	/**
	 * @return the spent
	 */
	public float getSpent() {
		return spent;
	}
	/**
	 * @param spent the spent to set
	 */
	public void setSpent(float spent) {
		this.spent = spent;
	}
	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}
	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	

}
