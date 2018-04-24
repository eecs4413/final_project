package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AccountBean;
import bean.BookBean;
import bean.POItemBean;
import bean.ReviewBean;
import dao.ReviewDAO;
import model.CartUtil;
import model.SearchUtil;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/Home", "/Home/*", "" })
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

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("catagorylist", SearchUtil.getCatagories());

		String target = "/Home.jspx";

		// Set up a new cart
		if (request.getSession().getAttribute("cart") == null) {
			request.getSession().setAttribute("cart", CartUtil.getCart());
		}

		// Signing out

		if (request.getParameter("signOut") != null) {
			request.getSession().removeAttribute("account");
			System.out.println("Logged out");
			target = "/Home.jspx";
		}

		// Filter books by header category

		if (request.getParameter("headerButton") != null) {
			request.setAttribute("results", SearchUtil.search(request.getParameter("headerButton")));

		}

		/*
		 * Testing Search button
		 */
		String search = request.getParameter("searchBar");
		request.getSession().setAttribute("search", search);

		if (request.getParameter("searchButton") != null) {
			System.out.println("WHY NO SEARCH");
			request.setAttribute("results", SearchUtil.search(request.getParameter("searchBar")));

			request.setAttribute("isSearchFilter", true);
			request.setAttribute("u15", SearchUtil.searchByPrice(request.getParameter("searchBar")).get(0));
			request.setAttribute("r15_r25", SearchUtil.searchByPrice(request.getParameter("searchBar")).get(1));
			request.setAttribute("r25_r50", SearchUtil.searchByPrice(request.getParameter("searchBar")).get(2));
			request.setAttribute("o50", SearchUtil.searchByPrice(request.getParameter("searchBar")).get(3));
		}

		request.getSession().setAttribute("search", search);

		/*
		 * // TODO add a value attribute to the search button to listen
		 * 
		 * if (request.getRequestURL().toString().contains("/ajax/search")) { if
		 * (request.getRequestURL().toString().contains("/ajax/search")) {
		 * System.out.println("Got an AJAX request"); if
		 * (request.getRequestURL().toString().contains("/ajax/search")) {
		 * System.out.println("Got an AJAX request"); request.setAttribute("results",
		 * SearchUtil.search(request.getParameter("searchBar"))); String search =
		 * request.getParameter("searchBar"); System.out.println(search); }
		 */

		if (request.getParameter("bookID") != null) {
			System.out.println(request.getParameter("bookID"));
			request.setAttribute("item", SearchUtil.searchID(request.getParameter("bookID")));
			target = "Item.jspx";
		}

		if (request.getParameter("addCart") != null) {
			String bookID = request.getParameter("addCart");
			BookBean book = SearchUtil.searchID(bookID);
			ArrayList<POItemBean> cart = (ArrayList<POItemBean>) request.getSession().getAttribute("cart");

			if (cart == null) {
				cart = new ArrayList<POItemBean>();
			}
			CartUtil.setCart(cart);

			CartUtil.addItem(book, 1, null);
			cart = CartUtil.getCart();

			request.getSession().setAttribute("cart", cart);
			request.getSession().setAttribute("cartcount", cart.size());

		}

		if (request.getParameter("book") != null) {
			String BookID = request.getParameter("book");

			request.setAttribute("book", SearchUtil.searchID(BookID));
			target = "BookInfo.jspx";
			ReviewDAO dao = new ReviewDAO();
			dao.retrieveReviews(BookID);
			request.setAttribute("reviews", dao.retrieveReviews(BookID));
		}

		if (request.getParameter("Review") != null) {
			if (request.getSession().getAttribute("account") == null) {
				request.setAttribute("error", "Please log in");
				System.out.println("LOG IN FIRST");
			} else {

				ReviewDAO dao = new ReviewDAO();
				target = "Home.jspx"; // gotta do something here later

				AccountBean accountBean = (AccountBean) request.getSession().getAttribute("account");
				dao.createReview(new ReviewBean(accountBean.getEmail(), request.getParameter("book"),
						request.getParameter("comment"), request.getParameter("rating")));
				;

			}

		}

		// SearchUtil.searchID(request.getParameter("book"));
		// ReviewDAO dao = new ReviewDAO();
		// dao.
		// request.setAttribute("reviews", );
		// target = "BookInfo.jspx";
		// }
		// System.out.println("-" + request.getQueryString() );

		// Enumeration<String> x = request.getAttributeNames();
		// while(x.hasMoreElements()) {
		// String str = x.nextElement();
		// System.out.println(str);
		// System.out.println(request.getAttribute(str));
		// }
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
