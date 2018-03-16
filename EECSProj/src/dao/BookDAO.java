package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import bean.BookBean;
import ctrl.DatabaseConnector;

import javax.sql.DataSource;


public class BookDAO {
	private DataSource ds;

	public BookDAO() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}

	public Map<String, BookBean> retrieve() throws SQLException {
		
		String query = "select * from Book;";
		Map<String, BookBean> rv = new HashMap<String, BookBean>();
		Connection con = this.ds.getConnection();
		PreparedStatement p = con.prepareStatement(query);
		
		ResultSet r = p.executeQuery();
		while (r.next()) {
			
			 String bid  = r.getString("bid");
			
			 String title  = r.getString("title");
			
			 String price =  r.getString("price");
			
			 String category =  r.getString("category");
			 
			 rv.put(bid, new BookBean(bid,title,price,category));
		

		}
		
		
		r.close();
		p.close();
		con.close();
		return rv;
	}

}
