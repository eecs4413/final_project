package ctrl;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AccountBean;
import model.AccountUtil;

/**
 * Servlet implementation class Login
 */
@WebServlet({"/Login","/Login/*" })
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
		
	
		
		String target = "/Login.jspx";
		
		String username ;
		String password;
		
		System.out.println( request.getRequestURL());
		
		if(request.getAttribute("login") != null) {
			
			username = (String) request.getAttribute("username");
			password = (String) request.getAttribute("password");
			
			AccountBean account = AccountUtil.login(username, password);
			
			if (account == null){
					request.setAttribute("ERROR", "account does not exsist");
					System.out.println("account does not exsist");
			}else {
				request.getSession().setAttribute("account", account );
			}	System.out.println("Logged in");
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
