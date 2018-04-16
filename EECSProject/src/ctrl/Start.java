package ctrl;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AccountBean;
import bean.ReviewBean;
import dao.ReviewDAO;
import model.CartUtil;
import model.SearchUtil;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/Home", "/Home/*" })
public class Start extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Start() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(SearchUtil.search("little"));
		String target = "/Home.jspx";
		if (request.getSession().getAttribute("cart") == null) {
			request.getSession().setAttribute("cart", CartUtil.getCart());
		}
		
		if(request.getParameter("signOut") != null) {
			request.getSession().setAttribute("logged_in", false);
			request.getRequestDispatcher("/Home.jspx").forward(request, response);
		}

		// TODO add a value attribute to the search button to listen

		if (request.getRequestURI().contains("/ajax/search")) {
			request.setAttribute("results", SearchUtil.search(request.getParameter("searchBar")));
		}

		if (request.getRequestURI().contains("/ajax/addbook")) {
			CartUtil.setCart((HashMap) request.getSession().getAttribute("cart"));
			CartUtil.addItem(SearchUtil.searchID(request.getParameter("bookid")), 1);
			request.getSession().setAttribute("cart", CartUtil.getCart());

		}
		if (request.getParameter("book") != null) {
			String BookID = request.getParameter("book");

			request.setAttribute("book", SearchUtil.searchID(BookID));
			target = "BookInfo.jspx";
			ReviewDAO dao = new ReviewDAO();
			dao.retrieveReviews(BookID);
			request.setAttribute("reviews",dao.retrieveReviews(BookID));
		}

		if (request.getParameter("Review") != null) {
			if(request.getSession().getAttribute("account") == null) {
				request.setAttribute("error", "Please log in");
				System.out.println("LOG IN FIRST");
			}else {
				
				ReviewDAO dao = new ReviewDAO();
				target = "Home.jspx";  // gotta do something here later
				
				AccountBean accountBean = (AccountBean) request.getSession().getAttribute("account");
				dao.createReview(new ReviewBean(accountBean.getEmail(), request.getParameter("book"), request.getParameter("comment"), request.getParameter("rating")));;
				
			}
			
		}

		//	SearchUtil.searchID(request.getParameter("book"));
		//	ReviewDAO dao = new ReviewDAO();
		//	dao.
		//	request.setAttribute("reviews", );
	//		target = "BookInfo.jspx";
	//	}

		request.getRequestDispatcher(target).forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
