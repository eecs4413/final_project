package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import bean.BookBean;
import ctrl.DatabaseConnector;

public class DOTWDAO {
	private DataSource ds;

	public DOTWDAO() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}

	public Map<String, BookBean> retrieve() {

		String query = "select * from DOTW;";
		Map<String, BookBean> rv = new HashMap<String, BookBean>();
		try {
			Connection con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);

			ResultSet r = p.executeQuery();
			while (r.next()) {

				String bid = r.getString("bid");

				String title = r.getString("title");
				// TODO update database chart to reflect this
				String author = r.getString("author");

				String price = r.getString("price");

				String category = r.getString("category");
				
				String reducedPrice = r.getString("reducedPrice");				
				
				rv.put(bid, new BookBean(bid, title, author, price, category, reducedPrice));

			}

			r.close();
			p.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rv;
	}
	
	public void enroll(String cID, String stuID, int credits) throws Exception {
		
		DataSource ds = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
		Connection con = ds.getConnection();
		Statement stm = con.createStatement();
		
		stm.executeUpdate("INSERT INTO ENROLLMENT (CID, SID, CREDIT) VALUES " +
				"('" + cID + "'), ('" + stuID + "'), ('" + credits + "')");
		
	}

}