package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.AccountBean;
import bean.AddressBean;
import dao.AccountDAO;

/**
 * Servlet implementation class Register
 */
@WebServlet({ "/Register", "/Register/*" })
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String target = "/Register.jspx";
	if (request.getParameter("register_login") != null) {
			if ( !request.getParameter("password").equals(request.getParameter("repassword"))
					|| request.getParameter("fname") == null || request.getParameter("fname").equals("")
					|| request.getParameter("lname") == null || request.getParameter("lname").equals("")
					|| request.getParameter("email") == null || request.getParameter("email").equals("")
					|| request.getParameter("password") == null || request.getParameter("password").equals("")
					|| request.getParameter("repassword") == null || request.getParameter("repassword").equals("")) {
				target = "/Register.jspx";
			}else{
				request.getSession().setAttribute("fname", request.getParameter("fname"));
				request.getSession().setAttribute("lname", request.getParameter("lname"));
				request.getSession().setAttribute("email", request.getParameter("email"));
				request.getSession().setAttribute("password", request.getParameter("password"));
				request.getSession().setAttribute("repassword", request.getParameter("repassword"));
				// TODO create page
				target = "/Register_Address.jspx";
			}
		}


		if (request.getParameter("register_address") != null) {
			// get these from session
			String fname, lname, email, password;

			// Retrieve attributes from the request
			String street, province, country, zip, phone , city;

			
			street = request.getParameter("street");
			city = request.getParameter("city");
			province = request.getParameter("province");
			country = request.getParameter("country");
			zip = request.getParameter("zip");
			phone = request.getParameter("phone");

			
			
			if (  street == null || street.equals("")
				|| province == null || province.equals("")
				|| country == null || country.equals("")
				|| zip == null || zip.equals("")
				|| phone == null || phone.equals("")) {
				target = "/Register_Address.jspx";
			}else{
			
			
			// create account
			AddressBean address = new AddressBean(null, street, province, country, city, zip, phone);

			email = (String) request.getSession().getAttribute("email");
			password = (String) request.getSession().getAttribute("password");
			lname = (String) request.getSession().getAttribute("lname");
			fname = (String) request.getSession().getAttribute("fname");

			AccountBean accountBean = new AccountBean(email, password, lname, fname, address);

			// put account in data base

			AccountDAO accountDAO = new AccountDAO();
			accountDAO.createAccount(accountBean);

			// clean session for security

			request.getSession().removeAttribute("fname");
			request.getSession().removeAttribute("lname");
			request.getSession().removeAttribute("email");
			request.getSession().removeAttribute("password");
			request.getSession().removeAttribute("repassword");

			// redirect to login page

			//target = "/Login.jspx";
			target = "/Registered.jspx";
			}

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
