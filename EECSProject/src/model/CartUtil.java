package model;

import java.util.HashMap;
import java.util.Map;

import bean.BookBean;

public class CartUtil {

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

}
