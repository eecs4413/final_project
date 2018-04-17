package ctrl;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AccountBean;
import bean.AddressBean;
import dao.AddressDAO;
import model.CreditCard;
import model.PurchaseUtil;

/**
 * Servlet implementation class Purchase
 */
@WebServlet({ "/Purchase", "/Purchase/*" })
public class Purchase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Purchase() {
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
		String target = "/Purchase.jspx";
		
		if(request.getParameter("processOrderButton") != null) {
			CreditCard cred = new CreditCard(request.getParameter("fname"),request.getParameter("lname"),request.getParameter("creditcard"));
			
			
			
			PurchaseUtil.setAccount((AccountBean) request.getSession().getAttribute("account"));
			 (new AddressDAO()).makeID(new AddressBean(request.getParameter("street"), request.getParameter("city"), request.getParameter("province"), request.getParameter("zip"), request.getParameter("phone")));
			PurchaseUtil.validateCreditCard(cred);
			target = "/ConfirmOrder.jspx";
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
