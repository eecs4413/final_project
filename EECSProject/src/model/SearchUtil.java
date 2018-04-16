package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import bean.BookBean;
import dao.BookDAO;

public class SearchUtil {

	private static ArrayList<BookBean> library = new ArrayList<BookBean>();

	public static Map<String, ArrayList<BookBean>> search(String searchString) {

		Map<String, ArrayList<BookBean>> searchResults = new HashMap<String, ArrayList<BookBean>>();
		get_books();
		
		
		searchResults.put("by_title", searchTitle(searchString));
		//searchResults.put("by_aurthor", searchAurthor(searchString));
		//searchResults.put("by_category", searchCategory(searchString));
		

		return searchResults;

	}

	private static void get_books() {

		if (library.isEmpty()) {

			BookDAO bookDAO = new BookDAO();
			Map<String, BookBean> mapList = bookDAO.retrieve();

			for (Entry<String, BookBean> entry : mapList.entrySet()) {
				library.add(entry.getValue());
			}
		}
		System.out.println("there are book in the library dao " + library.size());
	}

	public static ArrayList<BookBean> searchAurthor(String searchString) {
		ArrayList<BookBean> temp = new ArrayList<BookBean>();
		
		
		

		for (BookBean addressBean : library) {
			
			
			if (addressBean.getAurthor().toLowerCase().contains(searchString.toLowerCase())) {
				temp.add(addressBean);
			}

		}
		return temp;

	}

	public static ArrayList<BookBean> searchTitle(String searchString) {
		ArrayList<BookBean> temp = new ArrayList<BookBean>();
		
		System.out.println("YOu typed :" + searchString);

		for (BookBean addressBean : library) {
			
			System.out.println("YOu compared to  : " + addressBean.getTitle() );
			System.out.println("Rsult :" + addressBean.getTitle().toLowerCase().contains(searchString.toLowerCase()));
			if (addressBean.getTitle().toLowerCase().contains(searchString.toLowerCase())) {
				temp.add(addressBean);
				
			}

		}
		return temp;

	}

	public static ArrayList<BookBean> searchCategory(String searchString) {
		ArrayList<BookBean> temp = new ArrayList<BookBean>();

		for (BookBean addressBean : library) {
			if (addressBean.getCategory().toLowerCase().contains(searchString.toLowerCase())) {
				temp.add(addressBean);
			}

		}
		return temp;
	}
	
	public static BookBean searchID(String searchString) {
		
		get_books();
		
		BookBean temp = new BookBean();

		for (BookBean addressBean : library) {
			if (addressBean.getBid().equals(searchString)) {
				return temp;
			}

		}
		return null;
	}

}
