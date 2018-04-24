package model;

import java.util.ArrayList;
import bean.AccountBean;
import bean.BookBean;
import bean.POItemBean;
import bean.VisitEventBean;
import dao.VisitEventDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class CartUtil.
 */
public class CartUtil {

	/** The cart. */
	private static ArrayList<POItemBean> cart = new ArrayList<POItemBean>();

	/**
	 * Instantiates a new cart utility.
	 */
	public CartUtil() {

	}

	/**
	 * Instantiates a new cart utility from another cart.
	 *
	 * @param other
	 *            the other cart
	 */

	public CartUtil(ArrayList<POItemBean> cart2, AccountBean accountBean2) {
		cart = new ArrayList<POItemBean>(cart2);
	}

	public static void setCart(ArrayList<POItemBean> cart2) {
		cart = cart2;

	}

	/**
	 * Gets the cart.
	 *
	 * @return the cart
	 */
	public static ArrayList<POItemBean> getCart() {
		return cart;
	}

	/**
	 * Adds the item and registers a cart visit event.
	 *
	 * @param book
	 *            the book
	 * @param quantity
	 *            the quantity
	 */
	public static void addItem(BookBean book, int quantity, String comment) {
		boolean incart = false;

		for (POItemBean bean : cart) {

			if (bean.getBid().equals(book.getBid())) {
				incart = true;
				int x = Integer.parseInt(bean.getQuantity()) + quantity;
				bean.setQuantity(x + "");
				bean.setComment(comment);
			}
		}

		if (!incart) {

			cart.add(new POItemBean(book.getBid(), book.getPrice(), quantity + "", comment, null, book.getTitle(),
					book.getAuthor(), book.getCategory()));

		}

	}

	/**
	 * Removes the item deletes item from cart if quantity is negative
	 *
	 * @param book
	 *            the book
	 * @param quantity
	 *            the quantity
	 */
	public static void removeItem(BookBean book, int quantity) {

		for (int i = 0; i < cart.size(); i++) {
			POItemBean bean = cart.get(i);

			if (bean.getBid() == book.getBid()) {

				int x = Integer.parseInt(bean.getQuantity()) - quantity;

				if (x <= 0) {
					cart.remove(i);
				} else {
					bean.setQuantity(x + "");
				}

			}
		}
	}

	/**
	 * Clear cart.
	 */
	public void clearCart() {
		cart = new ArrayList<POItemBean>();
	}

	public static void updateComment(String bookID, String comment) {
		for (POItemBean bean : cart) {
			if (bean.getBid().equals(bookID)) {
				bean.setComment(comment);
			}
		}

	}

}
