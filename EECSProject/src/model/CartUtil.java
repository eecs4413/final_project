package model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import bean.AccountBean;
import bean.BookBean;
import bean.VisitEventBean;
import dao.VisitEventDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class CartUtil.
 */
public class CartUtil {
	


	/** The cart. */
	private static Map<BookBean, Integer> cart = new HashMap<BookBean, Integer>();

	/**
	 * Instantiates a new cart utility.
	 */
	public CartUtil() {

	}

	/**
	 * Instantiates a new cart utility from another cart.
	 *
	 * @param other the other cart
	 */
	public CartUtil(CartUtil other) {
		cart = other.cart ;
	}

	public CartUtil(Map<BookBean, Integer> cart2, AccountBean accountBean2) {
		cart = new HashMap<BookBean, Integer>(cart2);
	}

	public static void setCart(Map<BookBean, Integer> cart2) {
		cart = cart2;
		
	}

	/**
	 * Gets the cart.
	 *
	 * @return the cart
	 */
	public static Map<BookBean, Integer> getCart() {
		return cart;
	}

	/**
	 * Adds the item and registers a cart visit event.
	 *
	 * @param book the book
	 * @param quantity the quantity
	 */
	public static void addItem(BookBean book, int quantity ) {

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
		
		VisitEventBean vbean = new VisitEventBean(book.getBid(),date,"CART");
		ve.createEvent(vbean);

	}

	/**
	 * Removes the item deletes item from cart if quantity is negative
	 *
	 * @param book the book
	 * @param quantity the quantity
	 */
	public static void removeItem(BookBean book, int quantity) {

		if (cart.containsKey(book)) {
			int x = cart.get(book) - quantity;
			if (x <= 0) {
				removeItem(book);
			} else {
				cart.put(book, x);
			}
		}
	}

	/**
	 * Deletes the item from cart.
	 *
	 * @param book the book
	 */
	public static void removeItem(BookBean book) {
		cart.remove(book);
	}

	/**
	 * Clear cart.
	 */
	public void clearCart() {
		cart = new HashMap<BookBean, Integer>();
	}



	/**
	 * Gets the account bean.
	 *
	 * @return the account bean
	 */
	
}
