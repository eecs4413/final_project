package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SearchUtil;

/**
 * Servlet implementation class SearchFilter
 */
@WebServlet("/SearchFilter")
public class SearchFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String target = "SearchFilter.jspx";
		
		if (request.getParameter("searchButton") != null) {

			request.setAttribute("results", SearchUtil.search(request.getParameter("searchBar")));

			request.setAttribute("u15", SearchUtil.searchByPrice(request.getParameter("searchBar")).get(0).size());
			request.setAttribute("r15_r25", SearchUtil.searchByPrice(request.getParameter("searchBar")).get(1).size());
			request.setAttribute("r25_r50", SearchUtil.searchByPrice(request.getParameter("searchBar")).get(2).size());
			request.setAttribute("o50", SearchUtil.searchByPrice(request.getParameter("searchBar")).get(3).size());
			
			request.setAttribute("crime", SearchUtil.searchCategory("Crime").size());
			request.setAttribute("eng", SearchUtil.searchCategory("Engineering").size());
			request.setAttribute("fantasy", SearchUtil.searchCategory("Fantasy").size());
			request.setAttribute("kids", SearchUtil.searchCategory("Kids").size());
			request.setAttribute("sciFi", SearchUtil.searchCategory("Science Fiction").size());
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
