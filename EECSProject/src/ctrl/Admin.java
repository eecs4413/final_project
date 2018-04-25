package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import bean.BuyerStat;
import bean.VisitEventBean;
import dao.VisitEventDAO;
import model.SearchUtil;

/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Admin() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// calculate top ten by month
		
		if(request.getParameter("byDate") != null) {
			
			ArrayList<BookBean> data = new ArrayList<BookBean>();
			
			String date = request.getParameter("month") + request.getParameter("year");
			
			
			Map<String, VisitEventBean> visit_data = (new VisitEventDAO()).retrieve();
			
			
			for (Entry<String, VisitEventBean> entry : visit_data.entrySet()) {
			
				VisitEventBean bean = entry.getValue();
				System.out.println(bean.getDay());
				
				if( bean.getDay().endsWith(date)){
					data.add(SearchUtil.searchID(bean.getBid()));
					
				}
				
			}
	
			request.setAttribute("byDate", data);
			
		}
		
		


		// calculate ten books sold
		
		if(request.getParameter("topten") != null) {
			
			Map<String, Integer> sold = (Map<String, Integer>) request.getSession().getServletContext()
					.getAttribute("sold");

			if (sold == null) {
				System.out.println("..sold empty");
			} else {
				ArrayList<BookBean> topten = new ArrayList<>();

				List<Entry<String, Integer>> sorted = entriesSortedByValues(sold);

				int size = 10;
				if (sorted.size() < 10) {
					size = sorted.size();
				}

				for (int i = 0; i < size; i++) {

					topten.add(SearchUtil.searchID(sorted.get(i).getKey()));
				}	
				request.setAttribute("topten", topten);
			}
			
		}
		
		
		if(request.getParameter("byZipcode") != null) {
			
			
			ArrayList<BuyerStat> bs = (ArrayList<BuyerStat>) request.getSession().getServletContext().getAttribute("BuyerStats");
			if (bs == null) {
				System.out.println("..bs empty");
			} else {
				ArrayList<BuyerStat> bss = new ArrayList<BuyerStat>();
				
				
				for (BuyerStat b : bs) {
					
					boolean found = false;
					
					for (BuyerStat x : bss) {
						
						if(b.getZip().equals(x.getZip())) {
							
							b.setSpent( b.getSpent() + x.getSpent());
							found = true;
						}
					}
					
					if(!found) {
						bss.add(new BuyerStat("****", b.getSpent(), b.getZip()));
					}
				}
				request.setAttribute("byZipcode", bss);
			}
			
			
		}
		
		if(request.getParameter("byUser") != null) {
			
			ArrayList<BuyerStat> bs = (ArrayList<BuyerStat>) request.getSession().getServletContext().getAttribute("BuyerStats");
			if (bs == null) {
				System.out.println("..bs empty");
			} else {
				for (BuyerStat b : bs) {
					b.setAid("****");	
				}

			}
			request.setAttribute("byUser", bs);
		}

		request.getRequestDispatcher("/admin.jspx").forward(request, response);

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

	static <K, V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(Map<K, V> map) {

		List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());

		Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});

		return sortedEntries;
	}

}
