package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import bean.AddressBean;
import bean.POItemBean;
import ctrl.DatabaseConnector;

public class POItemDAO {

	private DataSource ds;

	public POItemDAO() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}

	public Map<String, POItemBean> retrieve() {

		String query = "select * from POItem;";
		Map<String, POItemBean> rv = new HashMap<String, POItemBean>();
		try {
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);

			ResultSet r = p.executeQuery();
			while (r.next()) {

				String id = r.getString("id");

				String bid = r.getString("bid");

				String price = r.getString("price");

				String day = r.getString("day");

				rv.put(id + bid, new POItemBean(id, bid, price, day));

			}

			r.close();
			p.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rv;
	}

	public void sendItems(ArrayList<POItemBean> items) {

		for (POItemBean item : items) {
			String query = "INSERT INTO POItem (id, bid, price, day) VALUES (" + item.getId() + ", '" + item.getBid()
					+ "', '" + item.getPrice() + "','" + item.getDay() + "');";

			Connection con;
			try {
				con = this.ds.getConnection();

				PreparedStatement p = con.prepareStatement(query);

				p.executeQuery();

				p.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

	}

}
