package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import bean.POItemBean;
import ctrl.DatabaseConnector;

public class POItemDAO {
	
	private DataSource ds;

	public POItemDAO() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}

	public Map<String, POItemBean > retrieve() throws SQLException {
		
		String query = "select * from POItem;";
		Map<String, POItemBean> rv = new HashMap<String, POItemBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		
		ResultSet r = p.executeQuery();
		while (r.next()) {
			
			 String id = r.getString("id");
			
			 String bid = r.getString("bid");
			
			 String price = r.getString("price");
			 
			 rv.put(id+bid, new POItemBean(id, bid, price));

		}
		
		
		r.close();
		p.close();
		con.close();
		return rv;
	}

}
