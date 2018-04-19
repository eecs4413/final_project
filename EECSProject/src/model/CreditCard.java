package model;

public class CreditCard {
	
	private String fname , lname , crednum , expYear , expMonth;

	public CreditCard() {
		super();
	}

	

	public CreditCard(String fname, String lname, String crednum, String expYear, String expMonth) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.crednum = crednum;
		this.expYear = expYear;
		this.expMonth = expMonth;
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
	 * @return the crednum
	 */
	public String getCrednum() {
		return crednum;
	}

	/**
	 * @param crednum the crednum to set
	 */
	public void setCrednum(String crednum) {
		this.crednum = crednum;
	}



	/**
	 * @return the expYear
	 */
	public String getExpYear() {
		return expYear;
	}



	/**
	 * @param expYear the expYear to set
	 */
	public void setExpYear(String expYear) {
		this.expYear = expYear;
	}



	/**
	 * @return the expMonth
	 */
	public String getExpMonth() {
		return expMonth;
	}



	/**
	 * @param expMonth the expMonth to set
	 */
	public void setExpMonth(String expMonth) {
		this.expMonth = expMonth;
	}
	



}
