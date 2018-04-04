package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import bean.AccountBean;
import bean.BookBean;
import bean.VisitEventBean;
import dao.VisitEventDAO;

public class CartUtil {
	
	private static AccountBean accountBean = null;

	private static Map<BookBean, Integer> cart = new HashMap<BookBean, Integer>();

	public CartUtil() {

	}

	public CartUtil(CartUtil other) {
		cart = other.getCart();
	}

	private Map<BookBean, Integer> getCart() {
		return cart;
	}

	public void addItem(BookBean book, int quantity) {

		if (cart.containsKey(book)) {
			int x = cart.get(book) + quantity;
			cart.put(book, x);
		} else {
			cart.put(book, quantity);
		}
		
		SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyy");
		Calendar cal = Calendar.getInstance();
		String date = sd.format(cal.getTime());
		
		VisitEventDAO ve = new VisitEventDAO();
		
		VisitEventBean vbean = new VisitEventBean(book.getBid(),accountBean.getEmail(),date,"CART");
		
		
		ve.createEvent(vbean);

	}

	public void removeItem(BookBean book, int quantity) {

		if (cart.containsKey(book)) {
			int x = cart.get(book) - quantity;
			if (x <= 0) {
				removeItem(book);
			} else {
				cart.put(book, x);
			}
		}
	}

	public void removeItem(BookBean book) {
		cart.remove(book);
	}

	public void clearCart() {
		cart = new HashMap<BookBean, Integer>();
	}

	public static AccountBean getAccountBean() {
		return accountBean;
	}

	public static void setAccountBean(AccountBean accountBean) {
		CartUtil.accountBean = accountBean;
	}

}
