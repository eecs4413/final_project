package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.SearchUtil;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String target = "SearchResults.jspx";
		
		if (request.getParameter("searchButton") != null) {

			request.setAttribute("results", SearchUtil.search(request.getParameter("searchBar")));

			request.setAttribute("isSearchFilter", true);
			request.setAttribute("u15", SearchUtil.searchByPrice(request.getParameter("searchBar")).get(0));
			request.setAttribute("r15_r25", SearchUtil.searchByPrice(request.getParameter("searchBar")).get(1));
			request.setAttribute("r25_r50", SearchUtil.searchByPrice(request.getParameter("searchBar")).get(2));
			request.setAttribute("o50", SearchUtil.searchByPrice(request.getParameter("searchBar")).get(3));
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
