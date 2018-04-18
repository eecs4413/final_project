package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.POItemBean;
import model.CartUtil;
import model.SearchUtil;

/**
 * Servlet implementation class Cart
 */
@WebServlet({ "/Cart", "/Cart/*" })
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "/Purchase.jspx";

		if (request.getRequestURI().contains("/ajax/addbook")) {
			CartUtil.setCart((ArrayList<POItemBean>) request.getSession().getAttribute("cart"));
			CartUtil.addItem(SearchUtil.searchID(request.getParameter("bookid")),
					Integer.parseInt(request.getParameter("quantity")), request.getParameter("comment"));
			request.getSession().setAttribute("cart", CartUtil.getCart());
		}
		
		

		if (request.getRequestURI().contains("/ajax/removebook")) {
			CartUtil.setCart((ArrayList<POItemBean>) request.getSession().getAttribute("cart"));
			CartUtil.removeItem(SearchUtil.searchID(request.getParameter("bookid")),
					Integer.parseInt(request.getParameter("quantity")));
			request.getSession().setAttribute("cart", CartUtil.getCart());
		}
		
		if(request.getParameter("proceed") != null) {
			request.getRequestDispatcher(target).forward(request, response);
		}

	
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
