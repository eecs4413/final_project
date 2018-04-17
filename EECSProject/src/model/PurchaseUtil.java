package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

import bean.AccountBean;
import bean.AddressBean;
import bean.BookBean;
import bean.POBean;
import bean.POItemBean;
import dao.AddressDAO;
import dao.PODAO;

//TODO CREATE A PURCHASE EVENT UNDER THE DAO

/**
 * The Class PurchaseUtil.
 */
public class PurchaseUtil {

	/** The account. */
	private static AccountBean account = null;

	/** The cart. */
	private Map<BookBean, Integer> cart = null;

	/** The pobean. */
	private static POBean pobean = new POBean();

	/**
	 * Gets the PO items.
	 *
	 * @return the PO items
	 */
	private ArrayList<POItemBean> getPOItems() {

		ArrayList<POItemBean> poi = new ArrayList<POItemBean>();

		for (Entry<BookBean, Integer> entry : cart.entrySet()) {

			int price = Integer.parseInt(entry.getKey().getPrice()) * entry.getValue();

			SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyy");
			Calendar cal = Calendar.getInstance();
			String date = sd.format(cal.getTime());

			POItemBean bean = new POItemBean(pobean.getId(), entry.getKey().getBid(), price + "", date);
			poi.add(bean);
		}
		return poi;

	}

	/**
	 * Processes the order if credit card is valid
	 *
	 * @param Cred
	 *            the credit card
	 * @param addbean
	 *            the Address information
	 */
	public static void checkout(CreditCard Cred, AddressBean addbean) {

		PurchaseUtil.validateCreditCard(Cred);
		// get address id
		AddressDAO addressDAO = new AddressDAO();
		int x = addressDAO.getID(addbean);
		// fill the bean
		addbean.setId(x + "");
		pobean.setAddress(addbean);
		pobean.setAid(getAccount().getEmail());
		// status is set just before bean is sent in to database;
		PODAO podao = new PODAO();
		podao.sendPO(pobean);

	}

	/**
	 * Validate credit card if billing address name = credit card name.
	 *
	 * @param cred
	 *            the credit card
	 * @return true, if  processed
	 * @return false, if  declined
	 */
	public static boolean validateCreditCard(CreditCard cred) {
		if (cred.getLname().equals(account.getLname()) && cred.getFname().equals(account.getFname())) {
			pobean.setStatus("PROCESSED");
			return true;
		}
		pobean.setStatus("DECLINED");
		return false;
	}

	/**
	 * Gets the account.
	 *
	 * @return the account
	 */
	public static AccountBean getAccount() {
		return account;
	}

	/**
	 * Sets the account.
	 *
	 * @param account
	 *            the new account
	 */
	public static void setAccount(AccountBean account) {
		PurchaseUtil.account = account;
	}

	/**
	 * Gets the comment.
	 *
	 * @return the comment
	 */
	public String getComment() {
		return pobean.getComment();
	}

	/**
	 * Sets the comment.
	 *
	 * @param comment
	 *            the new comment
	 */
	public void setComment(String comment) {
		pobean.setComment(comment);
	}

	/**
	 * Gets the cart.
	 *
	 * @return the cart
	 */
	public Map<BookBean, Integer> getCart() {
		return cart;
	}

	/**
	 * Sets the cart.
	 *
	 * @param cart
	 *            the cart
	 */
	public void setCart(Map<BookBean, Integer> cart) {
		this.cart = cart;
	}

}
