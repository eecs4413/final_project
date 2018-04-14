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
		searchResults.put("by_aurthor", searchAurthor(searchString));
		searchResults.put("by_category", searchCategory(searchString));
		searchResults.put("by_title", searchTitle(searchString));

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
	}

	public static ArrayList<BookBean> searchAurthor(String searchString) {
		ArrayList<BookBean> temp = new ArrayList<BookBean>();

		for (BookBean addressBean : library) {
			if (addressBean.getAurthor().equals(searchString)) {
				temp.add(addressBean);
			}

		}
		return temp;

	}

	public static ArrayList<BookBean> searchTitle(String searchString) {
		ArrayList<BookBean> temp = new ArrayList<BookBean>();

		for (BookBean addressBean : library) {
			if (addressBean.getTitle().equals(searchString)) {
				temp.add(addressBean);
			}

		}
		return temp;

	}

	public static ArrayList<BookBean> searchCategory(String searchString) {
		ArrayList<BookBean> temp = new ArrayList<BookBean>();

		for (BookBean addressBean : library) {
			if (addressBean.getCategory().equals(searchString)) {
				temp.add(addressBean);
			}

		}
		return temp;
	}

}
