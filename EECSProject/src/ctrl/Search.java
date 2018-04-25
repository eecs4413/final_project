package ctrl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.BookBean;
import bean.POItemBean;
import model.CartUtil;
import model.SearchUtil;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	Set<BookBean> booksByFilter = new HashSet<BookBean>();
	Set<BookBean> temp = new HashSet<BookBean>();
	
	boolean u15Toggle = false;
	boolean r15_r25Toggle = false;
	boolean r25_r50Toggle = false;
	boolean o50Toggle = false;
	
	boolean isCrime = false;
	boolean isEng = false;
	boolean isFantasy = false;
	boolean isKids = false;
	boolean isSciFi = false;
	

       
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
			
			temp.addAll(SearchUtil.search(request.getParameter("searchBar")));
			
			request.setAttribute("results", SearchUtil.search(request.getParameter("searchBar")));

			
		}
				
		if(request.getParameter("u15") != null) {
			u15Toggle = !u15Toggle;
			if(u15Toggle) {
				booksByFilter.addAll(SearchUtil.searchByPrice(request.getParameter("searchBar")).get(0));

			} else {
				booksByFilter.removeAll(SearchUtil.searchByPrice(request.getParameter("searchBar")).get(0));
			}

		} 
		
		if(request.getParameter("r15_r25") != null) {
			r15_r25Toggle = !r15_r25Toggle;
			if(r15_r25Toggle) {
				booksByFilter.addAll(SearchUtil.searchByPrice(request.getParameter("searchBar")).get(1));
			} else {
				booksByFilter.removeAll(SearchUtil.searchByPrice(request.getParameter("searchBar")).get(1));
			}

		} 
	
		if(request.getParameter("r25_r50") != null) {
			r25_r50Toggle = !r25_r50Toggle;
			if(r25_r50Toggle) {
				booksByFilter.addAll(SearchUtil.searchByPrice(request.getParameter("searchBar")).get(2));
			} else {
				booksByFilter.removeAll(SearchUtil.searchByPrice(request.getParameter("searchBar")).get(2));
			}
		}
		
		if(request.getParameter("o50") != null) {
			o50Toggle = !o50Toggle;
			if(o50Toggle) {
				booksByFilter.addAll(SearchUtil.searchByPrice(request.getParameter("searchBar")).get(3));
				request.setAttribute("results", booksByFilter);
			} else {
				booksByFilter.removeAll(SearchUtil.searchByPrice(request.getParameter("searchBar")).get(3));
				request.setAttribute("results", booksByFilter);
			}
		}
		
		request.setAttribute("results", booksByFilter);
		
		if((request.getParameter("u15") == null) && (request.getParameter("r15_r25") == null)
				&& (request.getParameter("r25_r50") == null) && (request.getParameter("o15") == null)) {
			//System.out.println("ALL checkboxes null");
			request.setAttribute("results", SearchUtil.search(request.getParameter("searchBar")));
			
		}
				
		String categories[] = {"Crime", "Engineering", "Fantasy", "Kids", "Science Fiction"};
		
		if(request.getParameter("crime") != null) {
			isCrime = !isCrime;
			if(isCrime) {
				if(booksByFilter.isEmpty()) {
					for(BookBean book : temp) {
						if(book.getCategory().equals("Crime")) {
							booksByFilter.add(book);
						}
						
					}
				} else {
					for(int i = 0; i < categories.length; i++) {
						if(!categories[i].equals("Crime")) {
							booksByFilter.removeAll(SearchUtil.searchCategory(categories[i]));
						}
					}
				}
			} else {
				if(booksByFilter.isEmpty()) {
					for(BookBean book : temp) {
						if(book.getCategory().equals("Crime")) {
							booksByFilter.remove(book);
						}
						
					}
				} else {
					booksByFilter.removeAll(SearchUtil.searchCategory("Crime"));
				}
			}
				
			request.setAttribute("results", booksByFilter);

		}
		
		if(request.getParameter("eng") != null) {
			isEng = !isEng;
			if(isEng) {
				System.out.print("is ENGING");
				if(booksByFilter.isEmpty()) {
					for(BookBean book : temp) {
						if(book.getCategory().equals("Engineering")) {
							booksByFilter.add(book);
						}
						
					}
				} else {
					for(int i = 0; i < categories.length; i++) {
						if(!categories[i].equals("Engineering")) {
							booksByFilter.removeAll(SearchUtil.searchCategory(categories[i]));
						}
					}

				}
			} else {
				System.out.print("NOT ENG");
				if(booksByFilter.isEmpty()) {
					for(BookBean book : temp) {
						if(book.getCategory().equals("Engineering")) {
							booksByFilter.remove(book);
						}
						
					}
				} else {
					booksByFilter.removeAll(SearchUtil.searchCategory("Engineering"));
				}
			}
				
			request.setAttribute("results", booksByFilter);

		}
		
		if(request.getParameter("fantasy") != null) {
			isFantasy = !isFantasy;
			if(isFantasy) {
				if(booksByFilter.isEmpty()) {
					for(BookBean book : temp) {
						if(book.getCategory().equals("Fantasy")) {
							booksByFilter.add(book);
						}
						
					}
				} else {
					for(int i = 0; i < categories.length; i++) {
						if(!categories[i].equals("Fantasy")) {
							booksByFilter.removeAll(SearchUtil.searchCategory(categories[i]));
						}
					}
				}
			} else {
				if(booksByFilter.isEmpty()) {
					for(BookBean book : temp) {
						if(book.getCategory().equals("Fantasy")) {
							booksByFilter.remove(book);
						}
						
					}
				} else {
					booksByFilter.removeAll(SearchUtil.searchCategory("Fantasy"));
				}
			}
				
			request.setAttribute("results", booksByFilter);

		}
		
		if(request.getParameter("kids") != null) {
			isKids = !isKids;
			if(isKids) {
				if(booksByFilter.isEmpty()) {
					System.out.println("ITS EMPTY?");
					for(BookBean book : temp) {
						if(book.getCategory().equals("Kids")) {
							booksByFilter.add(book);
						}
						
					}
				} else {
					for(int i = 0; i < categories.length; i++) {
						if(!categories[i].equals("Kids")) {
							booksByFilter.removeAll(SearchUtil.searchCategory(categories[i]));
						}
					}
				}
			} else {
				if(booksByFilter.isEmpty()) {
					for(BookBean book : temp) {
						if(book.getCategory().equals("Kids")) {
							booksByFilter.remove(book);
						}
						
					}
				} else {
					booksByFilter.removeAll(SearchUtil.searchCategory("Kids"));
				}
			}
				
			request.setAttribute("results", booksByFilter);

		}
		
		if(request.getParameter("sciFi") != null) {
			isSciFi = !isSciFi;
			if(isSciFi) {
				if(booksByFilter.isEmpty()) {
					for(BookBean book : temp) {
						if(book.getCategory().equals("Science Fiction")) {
							booksByFilter.add(book);
						}
						
					}
				} else {
					for(int i = 0; i < categories.length; i++) {
						if(!categories[i].equals("Science Fiction")) {
							booksByFilter.removeAll(SearchUtil.searchCategory(categories[i]));
						}
					}
				}
			} else {
				if(booksByFilter.isEmpty()) {
					for(BookBean book : temp) {
						if(book.getCategory().equals("Science Fiction")) {
							booksByFilter.remove(book);
						}
						
					}
				} else {
					booksByFilter.removeAll(SearchUtil.searchCategory("Science Fiction"));
				}
			}
				
			request.setAttribute("results", booksByFilter);

		}
		
		
		
		
		
				
		if (request.getParameter("addCart") != null) {
			String bookID = request.getParameter("addCart");
			BookBean book = SearchUtil.searchID(bookID);
			ArrayList<POItemBean> cart = (ArrayList<POItemBean>) request.getSession().getAttribute("cart");

			if (cart == null) {
				cart = new ArrayList<POItemBean>();
			}
			CartUtil.setCart(cart);
			
			CartUtil.addItem(book, 1, null);
			cart = CartUtil.getCart();
			
			request.getSession().setAttribute("cart", cart);
			request.getSession().setAttribute("cartcount", cart.size());

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
