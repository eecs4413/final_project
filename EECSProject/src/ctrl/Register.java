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

		// TODO _____> validate these parameters in the javaScript
		// TODO remember to add values to the submit button

		if (request.getParameter("register_login") != null) {
			if (request.getParameter("password").equals(request.getParameter("repassword"))) {
				// persist attributes in session scope

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
			String street, province, country, zip, phone;

			street = request.getParameter("street");
			province = request.getParameter("province");
			country = request.getParameter("country");
			zip = request.getParameter("zip");
			phone = request.getParameter("phone");

			// create account
			AddressBean address = new AddressBean(street, province, country, zip, phone);

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

			target = "/Login.jspx";

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
