package model;

public class CreditCard {
	
	private String fname , lname , crednum;

	public CreditCard() {
		super();
	}

	public CreditCard(String fname, String lname, String crednum) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.crednum = crednum;
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
	



}
