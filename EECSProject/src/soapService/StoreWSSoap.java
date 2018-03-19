package soapService;

import java.util.Map;

import bean.BookBean;
import dao.BookDAO;

public class StoreWSSoap {

	Map<String, BookBean> catalog;

	public StoreWSSoap() {
		BookDAO bookDAO = new BookDAO();
		catalog = bookDAO.retrieve();
	}

	public String getProductInfo(String productId) {
		return catalog.get(productId).toString();
	}

}
