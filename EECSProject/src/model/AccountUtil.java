package model;

import java.util.ArrayList;

import com.sun.org.glassfish.gmbal.ParameterNames;

import bean.AccountBean;
import bean.AddressBean;
import dao.AccountDAO;


/**
 * The Class AccountUtil takes care of validating , creating and logging of accounts .
 */
public class AccountUtil {
	
	/** The account information. */
	private static AccountBean account = null;
	
	/** The account database connection. */
	private static AccountDAO acc_dao = new AccountDAO();
	
	
	/**
	 * Logins in to an account. It sets the account of the class with the account information if found
	 *
	 * @param email the email of the account
	 * @param password the password of the account
	 * @return true, if successful
	 */
	public  static boolean login(String email , String password) {
		
		AccountBean temp = acc_dao.retrieveAccount(email, password);
		
		if (temp == null) {
			return false;
		}else {
			setAccount(temp);
			return true;
		}	
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
	 * @return false, if account exists already
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
