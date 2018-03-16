package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import bean.AddressBean;
import ctrl.DatabaseConnector;

import javax.sql.DataSource;

public class AddressDAO {
	private DataSource ds;

	public AddressDAO() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}

	public Map<String, AddressBean> retrieve() {

		String query = "select * from Address;";
		Map<String, AddressBean> rv = new HashMap<String, AddressBean>();
		Connection con;
		try {
			con = this.ds.getConnection();

			PreparedStatement p = con.prepareStatement(query);

			ResultSet r = p.executeQuery();
			while (r.next()) {

				String id = r.getString("id");

				String street = r.getString("street");

				String province = r.getString("province");

				String country = r.getString("country");

				String zip = r.getString("zip");

				String phone = r.getString("phone");

				rv.put(id, new AddressBean(id, street, province, country, zip, phone));

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

}
