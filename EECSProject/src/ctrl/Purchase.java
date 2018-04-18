package ctrl;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.AccountBean;
import bean.AddressBean;
import bean.BookBean;
import bean.POBean;
import bean.PurchaseOrderItemBean;
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
		
		if(request.getParameter("processOrderButtonProceed") != null) {
			
				
				// Retrieve attributes from the request
				String street, province, country, zip, phone , city,fname,lname,crednum , expMonth , expYear , comment;

				
				street = request.getParameter("street");
				city = request.getParameter("city");
				province = request.getParameter("province");
				country = request.getParameter("country");
				zip = request.getParameter("zip");
				phone = request.getParameter("phone");
				fname = request.getParameter("fname");
				lname = request.getParameter("lname");
				crednum = request.getParameter("crednum");
				expMonth = request.getParameter("expMonth");
				expYear = request.getParameter("expYear");
				comment = request.getParameter("comment");
				
				
				PurchaseUtil.setAccount((AccountBean) request.getSession().getAttribute("account"));
				
				AddressBean billto = new AddressBean(null, street, province, country, city, zip, phone);
				request.getSession().setAttribute("billTo", billto);
				
				CreditCard cred = new CreditCard(crednum,expMonth, expYear);
				
				POBean poBean = PurchaseUtil.Process(cred, billto, comment) ;
				request.getSession().setAttribute("PoBean", poBean);

				
					 if(poBean.getStatus().equals("PROCESSED")) {
						 target = "/ConfirmOrder.jspx";
					 }else {
						 target = "/Purchase.jspx";
						 request.setAttribute("error", "cOuld not process");
					 }
					
				}
		
		
		
		if(request.getParameter("ConfirmOrder" )!=null){
			
			PurchaseUtil.checkout((POBean) request.getSession().getAttribute("poBean" ) ,(Map<BookBean, Integer>)request.getSession().getAttribute("cart"));
			 target = "/Home";
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
