package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;

import bean.AddressBean;
import bean.POBean;
import ctrl.DatabaseConnector;

public class PODAO {
	
	private DataSource ds;
	
	public PODAO() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}

	public Map<String, POBean> retrieve()  {
		
		String query = "select * from PO;";
		Map<String, POBean> rv = new HashMap<String, POBean>();
		try {
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			
			ResultSet r = p.executeQuery();
			while (r.next()) {
				
				 String id = r.getString("id");
				
				 String lname = r.getString("lname");
				
				 String fname = r.getString("fname");
				
				 String status = r.getString("status");
				
				 AddressBean temp = new AddressBean();
				 
				 String address = r.getString("address");
				 
				 temp.setId(address);
				 
				 rv.put(id, new POBean(id, lname, fname, status, temp));
			

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
