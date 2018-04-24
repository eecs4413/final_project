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

				String shipAddress = r.getString("shipAddress");

				String comment = r.getString("comment");
				
				String fname = r.getString("fname");
				
				String lname = r.getString("lname");
				
				AddressDAO addressDAO = new AddressDAO();

				Map<String, AddressBean> map = addressDAO.retrieve();
				
				
				rv.put(id, new POBean(id, lname, fname, aid, status, map.get(shipAddress), comment));

			}

			r.close();
			p.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rv;
	}

	public int sendPO(POBean pobean) {


		String query = "INSERT INTO PO (aid, fname, lname, status, shipAddress, comment) VALUES (?,?,?,?,?,?);";
		Connection con;
		try {
			con = this.ds.getConnection();

			PreparedStatement p = con.prepareStatement(query);
			
			p.setString(1, pobean.getAid());
			p.setString(2, pobean.getFname());
			p.setString(3, pobean.getLname());
			p.setString(4, pobean.getStatus());
			p.setString(5, pobean.getAddress().getId());
			p.setString(6, pobean.getComment());
			
			p.executeUpdate();

			p.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
			
		
		
		
		return  this.retrieve().size();

	}

	

}
