package ctrl;

import java.io.IOException;
import java.util.ArrayList;
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
		String target = "/Cart.jspx";
		
		CartUtil.setCart((ArrayList<POItemBean>) request.getSession().getAttribute("cart"));
		
		

		if (request.getParameter("addbook")!= null) {	
			CartUtil.addItem(SearchUtil.searchID(request.getParameter("addbook")),1, request.getParameter("comment"));		
		}
		
		if (request.getParameter("commentsubmit")!= null) {
		
			CartUtil.updateComment(request.getParameter("commentsubmit"),request.getParameter("comment"));
		}
		
		

		if (request.getParameter("removebook")!= null) {
			CartUtil.removeItem(SearchUtil.searchID(request.getParameter("removebook")),1);
		}
		
		if(request.getParameter("next")!= null) {
			target= "/Purchase.jspx";
		}
		
		request.getSession().setAttribute("cart", CartUtil.getCart());
		
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
