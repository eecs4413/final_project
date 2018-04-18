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

	}


	public static POBean Process(CreditCard cred, AddressBean addbean, String comment, ArrayList<POItemBean> cart) {

		POBean pobean = new POBean(false, account.getLname(), account.getFname(), account.getEmail(), "DECLINED",
				addbean, comment);

		try {
			Integer.parseInt(cred.getCrednum());
			if (cred.getCrednum().length() <= 16 && cred.getCrednum().length() >= 14) {
				pobean.setStatus("PROCESSED");
			}
		} catch (Exception e) {

		}

		PODAO podao = new PODAO();
		pobean.setId(podao.sendPO(pobean) + "");

		for (POItemBean bean : cart) {
			bean.setId(pobean.getId());
		}

		(new POItemDAO()).sendItems(cart);

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
