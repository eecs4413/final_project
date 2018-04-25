package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import bean.BookBean;
import dao.BookDAO;

public class SearchUtil {

	private static ArrayList<BookBean> library = new ArrayList<BookBean>();

	// public static Map<String, ArrayList<BookBean>> search(String searchString) {
	//
	// Map<String, ArrayList<BookBean>> searchResults = new HashMap<String,
	// ArrayList<BookBean>>();
	// get_books();
	//
	//
	// searchResults.put("by_title", searchTitle(searchString));
	// searchResults.put("by_author", searchAuthor(searchString));
	// searchResults.put("by_category", searchCategory(searchString));
	//
	//
	// return searchResults;
	//
	// }

	//Search the query based on price
	public static ArrayList<ArrayList<BookBean>> searchByPrice(String searchString){
		
		ArrayList<BookBean> u15 = new ArrayList<BookBean>();
		ArrayList<BookBean> r15_r25 = new ArrayList<BookBean>();
		ArrayList<BookBean> r25_r50 = new ArrayList<BookBean>();
		ArrayList<BookBean> over50 = new ArrayList<BookBean>();
		
		ArrayList<ArrayList<BookBean>> priceList = new ArrayList<ArrayList<BookBean>>();
		
		Set<BookBean> books = search(searchString);
		
		for(BookBean book : books) {
			if(Float.parseFloat(book.price) < 15f) {
				u15.add(book);
			} else if (Float.parseFloat(book.price) >= 15f && Float.parseFloat(book.price) < 25f) {
				r15_r25.add(book);
			} else if (Float.parseFloat(book.price) >= 25f && Float.parseFloat(book.price) < 50f) {
				r25_r50.add(book);
			} else {
				over50.add(book);
			}
		}
		
		priceList.add(u15);
		priceList.add(r15_r25);
		priceList.add(r25_r50);
		priceList.add(over50);
		
		return priceList;
	}
	
	public static Set<BookBean> search(String searchString) {

		get_books();

		Set<BookBean> booksFromSearch = new HashSet<BookBean>();

		ArrayList<BookBean> temp = searchTitle(searchString);
		for (int i = 0; i < temp.size(); i++) {
			booksFromSearch.add(temp.get(i));
		}

		temp = searchAuthor(searchString);
		for (int i = 0; i < temp.size(); i++) {
			booksFromSearch.add(temp.get(i));
		}

		temp = searchCategory(searchString);
		for (int i = 0; i < temp.size(); i++) {
			booksFromSearch.add(temp.get(i));
		}

		return booksFromSearch;

	}

	private static void get_books() {

		if (library.isEmpty()) {

			BookDAO bookDAO = new BookDAO();
			Map<String, BookBean> mapList = bookDAO.retrieve();

			for (Entry<String, BookBean> entry : mapList.entrySet()) {
				library.add(entry.getValue());
			}
		}
		// System.out.println("there are book in the library dao " + library.size());
	}

	public static ArrayList<BookBean> searchAuthor(String searchString) {
		ArrayList<BookBean> temp = new ArrayList<BookBean>();

		for (BookBean addressBean : library) {
			if (addressBean.getAuthor().toLowerCase().contains(searchString.toLowerCase())) {
				temp.add(addressBean);
			}
		}
		return temp;

	}

	public static ArrayList<BookBean> searchTitle(String searchString) {
		ArrayList<BookBean> temp = new ArrayList<BookBean>();

		// System.out.println("YOu typed :" + searchString);

		for (BookBean addressBean : library) {

			// System.out.println("YOu compared to : " + addressBean.getTitle() );
			// System.out.println("Rsult :" +
			// addressBean.getTitle().toLowerCase().contains(searchString.toLowerCase()));
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
	
	public static ArrayList<BookBean> searchCategory(String searchString, ArrayList<BookBean> bookList) {
		ArrayList<BookBean> temp = new ArrayList<BookBean>();

		for (BookBean addressBean : bookList) {
			if (addressBean.getCategory().toLowerCase().contains(searchString.toLowerCase())) {
				temp.add(addressBean);
			}

		}
		return temp;
	}

	public static BookBean searchID(String searchString) {

		get_books();

		BookBean temp = null;

		for (BookBean book : library) {
			if (book.getBid().contains(searchString)) {
				temp = book;
			}

		}
		return temp;
	}

	public static ArrayList<String> getCatagories() {

		get_books();
		
		

		ArrayList<String> temp = new ArrayList<String>();

		for (BookBean book : library) {
			if (!temp.contains(book.getCategory())) {
				temp.add(book.category);
			}

		}
		return temp;
	}

}
