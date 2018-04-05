package model;

import java.util.ArrayList;

import bean.AccountBean;
import bean.AddressBean;
import dao.AccountDAO;

public class AccountUtil {
	
	private static AccountBean acc = null;
	private static AccountDAO acc_dao = new AccountDAO();
	
	// Checks for  to see if account exists if it does , set account details as the account 
	public boolean login(String email , String password) {
		
		AccountBean temp = acc_dao.retrieveAccount(email, password);
		
		if (temp == null) {
			return false;
		}else {
			
			setAccount(temp);
			return true;
		}	
	}
	
	
	public void logout() {
		setAccount(null);
	}
	
	public boolean createAccount(String email, String password,String fname,String lname,AddressBean address) {
		
		if(Exists(email)) {
			return false;
		}
		
		AccountBean temp = new AccountBean(email,password,fname,lname,address);
		acc_dao.createAccount(temp);
		
		return true;
		
	}
	
	public boolean Exists(String email) {
		ArrayList<String> emails = acc_dao.retrieveEmails();
		
		if (emails.contains(email)) {
			return true;
		}
		
		return false;
		
	}


	public static AccountBean getAccount() {
		return acc;
	}


	public static void setAccount(AccountBean acc) {
		AccountUtil.acc = acc;
	}
	

}
