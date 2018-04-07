package ctrl;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AddressDAO;

/**
 * Servlet implementation class Start
 */
@WebServlet({ "/Start", "/Startup", "/Start/*" })
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AddressDAO  addressDAO = new AddressDAO();
		System.out.println(addressDAO.retrieve().toString());
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String path = request.getPathInfo();
		String target = "/Home.jspx";
		
		if (request.getSession().getAttribute("cart") == null) {
			request.getSession().setAttribute("cart", 0);
		}
		
		if ( path != null && path.equals("/Login") ) {
			// forward to login page.
			target = "/Login.jspx";
		}  else if ( path != null && path.equals("/Register") ) {
			// Forward to Register Page.
			target = "/Register.jspx";
		}  else if ( path != null && path.equals("/Cart") ) {
			target = "/Cart.jspx";
		} else if ( path != null && path.equals("/Home") ) {
			// forward to home page
			target = "/Home.jspx";
		}
		request.getRequestDispatcher(target).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
