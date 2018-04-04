package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import bean.AccountBean;
import bean.AddressBean;
import ctrl.DatabaseConnector;

public class AccountDAO {

	private DataSource ds;

	public AccountDAO() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}

	public AccountBean retrieveAccount(String email, String password) {
		String query = "SELECT * from Account INNER JOIN Address ON Account.billAddress = Address.id Where Account.email='"+email+"' AND Account.password='"+password+"';";
		AccountBean rv = null ;
		
		Connection con;
		try {
			con = this.ds.getConnection();

			PreparedStatement p = con.prepareStatement(query);

			ResultSet r = p.executeQuery();
			while (r.next()) {

				String e_mail = r.getString("email");

				String pass_word = r.getString("password");

				String lname = r.getString("lname");

				String fname = r.getString("fname");
				
				String aid = r.getString("billAddress");
				
				String street =  r.getString("street");
				
				String province =  r.getString("province");
				
				String country =  r.getString("country");

				String zip = r.getString("zip");

				String phone = r.getString("phone");
				
				rv = new AccountBean(email, password, lname, fname,new AddressBean(aid, street, province, country, zip, phone));

				

			}
			r.close();
			p.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rv;

	}

	public void createAccount(AccountBean temp) {
		
		AddressDAO adao = new AddressDAO();
		
		int address_ID = adao.retriveAddressID(temp.getAddress());
		
		String e_mail = temp.getEmail();

		String pass_word = temp.getPassword();

		String lname = temp.getLname();

		String fname = temp.getFname();
		
		String query = "INSERT INTO Account (email, password, lname, fname, billAddress) VALUES ('"+e_mail+"', '"+pass_word+"', '"+lname+"', '+"+fname+"', '"+address_ID+"');";
		
		Connection con;
		try {
			con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			ResultSet r = p.executeQuery();
			r.close();
			p.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<String> retrieveEmails() {
		// TODO Auto-generated method stub
		return null;
	}

}
