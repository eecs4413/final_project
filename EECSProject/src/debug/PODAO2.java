package debug;

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


// THIS FILE IS FOR TESTING/DEBUGGING PURPOSES
public class PODAO2 {
	
	private DataSource ds;
	
	public PODAO2() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}

	public Map<String, POBean> retrieve(String clientID)  {
		
		//String query = "select " + clientID + " from Address, PO";
		String query = "select * from Address, PO, POItem";
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rv;
	}
	
	
}
