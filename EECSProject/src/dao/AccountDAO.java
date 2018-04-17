package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		String query = "SELECT * from Account INNER JOIN Address ON Account.billAddress = Address.id Where Account.email='"
				+ email + "' AND Account.password='" + password + "';";
		AccountBean rv = null;

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

				String street = r.getString("street");

				String province = r.getString("province");

				String country = r.getString("country");
				String city = r.getString("city");

				String zip = r.getString("zip");

				String phone = r.getString("phone");

				rv = new AccountBean(e_mail, pass_word, lname, fname,
						new AddressBean(aid, street,  province, country, city,zip, phone));

			}
			if(rv == null) {
				
			}
			r.close();
			p.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rv;

	}

	public AccountBean retrieveAccount(String email) {
		String query = "SELECT * from Account INNER JOIN Address ON Account.billAddress = Address.id Where Account.email='"
				+ email + "';";
		AccountBean rv = null;

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

				String street = r.getString("street");

				String province = r.getString("province");

				String country = r.getString("country");
				String city = r.getString("city");

				String zip = r.getString("zip");

				String phone = r.getString("phone");

				rv = new AccountBean(e_mail, pass_word, lname, fname,
						new AddressBean(aid, street,  province, country, city,zip, phone));

			}
			if(rv == null) {
				System.out.println("dao did not get anything");
			}
			r.close();
			p.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rv;
		
	}
	
	
	public void createAccount(AccountBean temp) {
		
		System.out.println(temp.getEmail());
		
		AddressDAO adao = new AddressDAO();

		int address_ID = adao.getID(temp.getAddress());

		if (address_ID < 0) {

			address_ID = adao.makeID(temp.getAddress());

		}

		String query = "INSERT INTO Account (email, password, lname, fname, billAddress) VALUES (?, ?,?,?,?);";

		Connection con;
		try {
			con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);

			p.setString(1, temp.getEmail());
			p.setString(2, temp.getPassword());
			p.setString(3, temp.getLname());
			p.setString(4, temp.getFname());
			p.setInt(5, address_ID);

			p.executeUpdate();

			p.close();
			con.close();
		} catch (SQLException e) {
			if(e.getMessage().contains("Duplicate entry")) {
				System.out.println("Account is already registered");
			}

		
		}

	}

	public ArrayList<String> retrieveEmails() {
		String query = "SELECT email from Account;";

		ArrayList<String> rv = new ArrayList<String>();

		Connection con;
		try {
			con = this.ds.getConnection();

			PreparedStatement p = con.prepareStatement(query);

			ResultSet r = p.executeQuery();
			while (r.next()) {

				String e_mail = r.getString("email");

				rv.add(e_mail);
			}
			r.close();
			p.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rv;
	}

}
