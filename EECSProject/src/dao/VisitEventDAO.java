package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import bean.AddressBean;
import bean.VisitEventBean;
import ctrl.DatabaseConnector;

public class VisitEventDAO {

	private DataSource ds;

	public VisitEventDAO() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}

	public Map<String, VisitEventBean> retrieve() {

		String query = "select * from VisitEvent;";
		Map<String, VisitEventBean> rv = new HashMap<String, VisitEventBean>();
		try {
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);

			ResultSet r = p.executeQuery();
			while (r.next()) {

				String day = r.getString("day");
				String bid = r.getString("bid");
				String aid = r.getString("aid");
				String eventtype = r.getString("eventtype");
				
				rv.put(bid, new VisitEventBean(bid, day, eventtype));
// TODO remove aid from table cart
			}

			r.close();
			p.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rv;
	}

	public void createEvent(VisitEventBean vbean) {

		String query = "INSERT INTO VisitEvent (day, bid, eventtype) VALUES (?,?,?);";

		try {
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);

			p.setString(1, vbean.getDay());
			p.setString(2, vbean.getBid());
			p.setString(3, vbean.getEventtype());

			p.executeUpdate();

			p.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
