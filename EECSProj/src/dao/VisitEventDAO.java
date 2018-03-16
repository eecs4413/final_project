package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import bean.VisitEventBean;
import ctrl.DatabaseConnector;

public class VisitEventDAO {

	
	private DataSource ds;

	public VisitEventDAO() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}

	public Map<String, VisitEventBean > retrieve() throws SQLException {
		
		String query = "select * from VisitEvent;";
		Map<String, VisitEventBean> rv = new HashMap<String, VisitEventBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		
		ResultSet r = p.executeQuery();
		while (r.next()) {
			
			 String day = r.getString("day");
			
			 String bid = r.getString("bid");
			
			 String eventtype = r.getString("eventtype");
			 
			 rv.put(bid, new VisitEventBean(day, bid, eventtype));

		}
		
		
		r.close();
		p.close();
		con.close();
		return rv;
	}

	
}
