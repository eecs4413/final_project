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
	 * @param cart 
	 *
	 * @return the PO items
	 */
	private static ArrayList<POItemBean> getPOItems(String string, Map<BookBean, Integer> cart ) {

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

	/**
	 * Processes the order if credit card is valid
	 *
	 * @param Cred
	 *            the credit card
	 * @param addbean
	 *            the Address information
	 */
	public static void checkout(POBean pob, Map<BookBean, Integer> cart ) {
		if (pob.getStatus().equals("PROCESSED")) {
			pob.setStatus("ORDERED");
			PODAO podao = new PODAO();
			podao.sendPO(pob);
		}
		
		
			(new POItemDAO()).sendItems(getPOItems(pob.getId() , cart));
		
		
	}

	/**
	 * Validate credit card if is not expired and has a valid num format.
	 *
	 * @param cred
	 *            the credit card
	 * @return true, if processed
	 * @return false, if declined
	 */
	public static POBean Process(CreditCard cred, AddressBean addbean, String comment) {
		
		POBean pobean = new POBean(false, account.getLname(), account.getFname(), account.getEmail(),
				"DECLINED", addbean, comment);

		try {
			Integer.parseInt(cred.getCrednum());
			if (cred.getCrednum().length() <= 16 && cred.getCrednum().length() >= 14) {
				pobean.setStatus("PROCESSED");
			}
		} catch (Exception e) {

		}

		PODAO podao = new PODAO();
		pobean.setId(podao.sendPO(pobean)+"");

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
