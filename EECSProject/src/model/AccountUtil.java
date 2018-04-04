package model;

import java.util.ArrayList;

import bean.AccountBean;
import bean.AddressBean;
import dao.AccountDAO;

public class AccountUtil {
	
	private static AccountBean acc = null;
	private static AccountDAO acc_dao = new AccountDAO();
	
	public boolean login(String email , String password) {
		
		
		AccountBean temp = acc_dao.retrieveAccount(email, password);
		
		if (temp == null) {
			return false;
		}else {
			
			acc = temp;
			return true;
		}	
	}
	
	
	public void logout() {
		acc = null;
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
	

}
