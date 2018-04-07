package model;

import java.util.ArrayList;


import bean.AccountBean;
import bean.AddressBean;
import dao.AccountDAO;


// TODO: Auto-generated Javadoc
/**
 * The Class AccountUtil takes care of validating , creating and logging of accounts .
 */
public class AccountUtil {
	
	/** The account information. */
	private static AccountBean account = null;
	
	/** The account database connection. */
	private static AccountDAO acc_dao = new AccountDAO();
	
	
	
	/**
	 * Login.
	 *
	 * @param email the email
	 * @param password the password
	 * @return the account bean
	 */
	public  static AccountBean login(String email , String password) {
		return acc_dao.retrieveAccount(email, password);
	}
	
	
	/**
	 * Resets login information.
	 */
	public static void logout() {
		setAccount(null);
	}
	
	/**
	 * Creates the account.
	 *
	 * @param email the email of the account
	 * @param password the password of the account
	 * @param fname the first name of the account
	 * @param lname the last name of the account
	 * @param address the address bean of the account
	 * @return true, if successful created account
	 * false, if account exists already
	 */
	public static boolean createAccount(String email, String password,String fname,String lname,AddressBean address) {
		
		if(Exists(email)) {
			return false;
		}
		
		AccountBean temp = new AccountBean(email,password,fname,lname,address);
		acc_dao.createAccount(temp);
		
		return true;
		
	}
	
	/**
	 * Checks to see if the email exists in the database.
	 *
	 * @param email the email
	 * @return true, if exists
	 */
	public static boolean Exists(String email) {
		ArrayList<String> emails = acc_dao.retrieveEmails();

		return emails.contains(email);
		
	}


	/**
	 * Gets the account.
	 *
	 * @return the account 
	 */
	public static AccountBean getAccount() {
		return account;
	}


	/**
	 * Sets the account.
	 *
	 * @param account the new account
	 */
	public static void setAccount(AccountBean account) {
		AccountUtil.account = account;
	}
	

}
