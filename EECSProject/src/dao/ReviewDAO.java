package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import bean.ReviewBean;
import ctrl.DatabaseConnector;

public class ReviewDAO {
	private DataSource ds;
	
	public ReviewDAO() {
		ds = DatabaseConnector.retriveDatabaseInfo();
	}
	
	
	public  ArrayList<ReviewBean> retrieveReviews(String BookID) {
		
		Map<String, ArrayList<ReviewBean>> t = this.retrieveAllReviews();
		
		return t.get(BookID);
		
		
	}
	
	public Map<String, ArrayList<ReviewBean>> retrieveAllReviews() {
		String query = "SELECT * from Review ;";
		Map<String, ArrayList<ReviewBean>> rv = new HashMap<String,  ArrayList<ReviewBean>>();

		Connection con;
		try {
			con = this.ds.getConnection();

			PreparedStatement p = con.prepareStatement(query);

			ResultSet r = p.executeQuery();
			while (r.next()) {

				String aid = r.getString("aid");

				String bid = r.getString("bid");

				String comment = r.getString("comment");

				String rating = r.getString("rating");
				
				if(rv.containsKey(bid)) {
					rv.get(bid).add(new ReviewBean(aid,bid,comment,rating));
				}else {
					rv.put( bid ,new ArrayList<>());
					rv.get(bid).add(new ReviewBean(aid,bid,comment,rating));
				}

				

			}
			r.close();
			p.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return rv;

	}
	
	public void createReview(ReviewBean reviewBean) {
	
		String query = "INSERT INTO Review (aid, bid, comment, rating) VALUES (?, ?,?,?);";

		Connection con;
		try {
			con = this.ds.getConnection();
			PreparedStatement p = con.prepareStatement(query);

			p.setString(1, reviewBean.getAid());
			p.setString(2, reviewBean.getBid());
			p.setString(3, reviewBean.getComment());
			p.setString(4, reviewBean.getRating());
	

			p.executeUpdate();

			p.close();
			con.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}
}
