package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import bean.AddressBean;
import bean.PurchaseOrderBean;
import ctrl.DatabaseConnector;

public class PurchaseOrderDAO {
	
	private DataSource ds;
	
	public PurchaseOrderDAO() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}

	public Map<String, PurchaseOrderBean> retrieve()  {
		
		//PO = person info, address = shipTo, 
		String query = "select * from POItem, Address, PO, ";
		Map<String, PurchaseOrderBean> rv = new HashMap<String, PurchaseOrderBean>();
		try {
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);
			
			ResultSet r = p.executeQuery();
			while (r.next()) {
				
				//String orderDate, PurchaseOrderShipTo shipto, PurchaseOrderShipTo billTo, String comment,	ArrayList<PurchaseOrderItemBean> items
				
				 String id = r.getString("bid");
				
				 String lname = r.getString("lname");
				
				 String fname = r.getString("fname");
				
				 String status = r.getString("status");
				
				 AddressBean temp = new AddressBean();
				 
				 String address = r.getString("address");
				 
				 temp.setId(address);
				 
				// rv.put(id, new PurchaseOrderBean(id, lname, fname, status, temp));
			

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
