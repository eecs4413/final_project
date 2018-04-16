package ctrl;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

		String target = "/Home.jspx";
		if (request.getSession().getAttribute("cart") == null) {
			request.getSession().setAttribute("cart", CartUtil.getCart());
		}

		// TODO add a value attribute to the search button to listen

		if (request.getPathInfo().contains("/ajax/search")) {
			request.setAttribute("results", SearchUtil.search(request.getParameter("searchBar")));
		}

		if (request.getPathInfo().contains("/ajax/addbook/")) {
			CartUtil.setCart((HashMap) request.getSession().getAttribute("cart"));
			CartUtil.addItem(SearchUtil.searchID(request.getParameter("bookid")), 1);
			request.getSession().setAttribute("cart", CartUtil.getCart());

		}
		if (request.getParameter("book")!= null) {
			
			request.setAttribute("book", SearchUtil.searchID(request.getParameter("book")));
			target = "BookInfo.jspx";
		}
		
if (request.getParameter("comment")!= null) {
			
			request.setAttribute("book", SearchUtil.searchID(request.getParameter("book")));
			target = "BookInfo.jspx";
		}

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
