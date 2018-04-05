package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.sql.DataSource;

import bean.AddressBean;
import bean.POBean;
import ctrl.DatabaseConnector;

public class PODAO {

	private DataSource ds;

	public PODAO() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}

	public Map<String, POBean> retrieve() {

		String query = "select * from PO;";
		Map<String, POBean> rv = new HashMap<String, POBean>();
		try {
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);

			ResultSet r = p.executeQuery();
			while (r.next()) {

				String aid = r.getString("aid");

				String id = r.getString("id");

				String status = r.getString("status");

				String addressID = r.getString("address");

				String comment = r.getString("comment");

				AddressDAO addressDAO = new AddressDAO();

				Map<String, AddressBean> map = addressDAO.retrieve();

				rv.put(id, new POBean(id, aid, status, map.get(addressID), comment));

			}

			r.close();
			p.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rv;
	}

	public void sendPO(POBean pobean) {

		if (pobean.getStatus().equals("PROCESSED")) {
			pobean.setStatus("ORDERED");
		}
		String query = "INSERT INTO PO (aid, id, status, shipAddress) VALUES ('" + pobean.getAid() + "', "
				+ pobean.getId() + ", '" + pobean.getStatus() + "', " + pobean.getAddress().getId() + ");";

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
