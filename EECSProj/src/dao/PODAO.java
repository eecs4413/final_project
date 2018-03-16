package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import bean.POBean;
import ctrl.DatabaseConnector;

public class PODAO {
	
	private DataSource ds;
	
	public PODAO() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}

	public Map<String, POBean> retrieve() throws SQLException {
		
		String query = "select * from PO;";
		Map<String, POBean> rv = new HashMap<String, POBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		
		ResultSet r = p.executeQuery();
		while (r.next()) {
			
			 String id = r.getString("id");
			
			 String lname = r.getString("lname");
			
			 String fname = r.getString("fname");
			
			 String status = r.getString("status");
			
			 String address = r.getString("address");
			 
			 rv.put(id, new POBean(id, lname, fname, status, address));
		

		}
		
		
		r.close();
		p.close();
		con.close();
		return rv;
	}
	
	
}
