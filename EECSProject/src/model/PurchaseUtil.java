package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.Map.Entry;

import com.ibm.wsdl.util.StringUtils;

import bean.AccountBean;
import bean.AddressBean;
import bean.BookBean;
import bean.POBean;
import bean.POItemBean;
import dao.AddressDAO;
import dao.PODAO;
import dao.POItemDAO;

//TODO CREATE A PURCHASE EVENT UNDER THE DAO

/**
 * The Class PurchaseUtil.
 */
public class PurchaseUtil {

	/** The account. */
	private static AccountBean account = null;

	/**
	 * Gets the PO items.
	 * 
	 * @param cart
	 *
	 * @return the PO items
	 */
	private static ArrayList<POItemBean> getPOItems(String string, Map<BookBean, Integer> cart) {

		ArrayList<POItemBean> poi = new ArrayList<POItemBean>();

		for (Entry<BookBean, Integer> entry : cart.entrySet()) {

			int price = Integer.parseInt(entry.getKey().getPrice()) * entry.getValue();

			SimpleDateFormat sd = new SimpleDateFormat("ddMMyyyy");
			Calendar cal = Calendar.getInstance();
			String date = sd.format(cal.getTime());
			// TODO get comment
			POItemBean bean = new POItemBean(string, entry.getKey().getBid(), price + "", date, "");
			poi.add(bean);
		}
		return poi;

	}

	public static void checkout(POBean pobean, ArrayList<POItemBean> cart) {

		String id = "";

		if (pobean.getStatus().equals("PROCESSED")) {
			pobean.setStatus("ORDERED");
			pobean.getNewID();

			for (POItemBean bean : cart) {
				bean.setId(pobean.getId());
			}

			(new POItemDAO()).sendItems(cart);

		}

		System.out.println("Order Status :" + pobean.getStatus());

	}

	public static POBean Process(CreditCard cred, AddressBean addbean, String comment, ArrayList<POItemBean> cart) {

		POBean pobean = new POBean(false, account.getLname(), account.getFname(), account.getEmail(), "DENIED", addbean,
				comment);

		try {
			for (char s : cred.getCrednum().toCharArray()) {
				Integer.parseInt(s + "");
			}

			if (cred.getCrednum().length() < 17 && cred.getCrednum().length() > 13) {
				pobean.setStatus("PROCESSED");
				pobean.getNewID();

			} else {
				System.out.println(cred.getCrednum().length() + " Credit card too Long " + cred.getCrednum());
			}
		} catch (Exception e) {
			System.out.println("Credit Not A Number");
		}

		PODAO podao = new PODAO();
		pobean.setId(podao.sendPO(pobean) + "");
		System.out.println(pobean.getId());

		for (POItemBean bean : cart) {
			bean.setId(pobean.getId());
		}

		(new POItemDAO()).sendItems(cart);
		System.out.println("Your Order Has Been " + pobean.getStatus());
		return pobean;
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

}
