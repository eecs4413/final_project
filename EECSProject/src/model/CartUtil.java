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
	
	/** The account bean. */
	private static AccountBean accountBean = null;

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
		cart = other.getCart();
		accountBean = other.getAccount();
	}

	/**
	 * Gets the cart.
	 *
	 * @return the cart
	 */
	private Map<BookBean, Integer> getCart() {
		return cart;
	}

	/**
	 * Adds the item and registers a cart visit event.
	 *
	 * @param book the book
	 * @param quantity the quantity
	 */
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

	/**
	 * Removes the item deletes item from cart if quantity is negative
	 *
	 * @param book the book
	 * @param quantity the quantity
	 */
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

	/**
	 * Deletes the item from cart.
	 *
	 * @param book the book
	 */
	public void removeItem(BookBean book) {
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
	public static AccountBean getAccount() {
		return accountBean;
	}

	/**
	 * Sets the account bean.
	 *
	 * @param account the new account 
	 */
	public static void setAccount(AccountBean account) {
		CartUtil.accountBean = account;
	}

}
