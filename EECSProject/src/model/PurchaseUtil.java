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

public class PurchaseUtil {

	private static AccountBean account = null;
	private Map<BookBean, Integer> cart = null;

	private static POBean pobean = new POBean();

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

	public void checkout(CreditCard Cred, AddressBean addbean) {

		PurchaseUtil.validateCreditCard(Cred);

		AddressDAO addressDAO = new AddressDAO();
		int x = addressDAO.retriveAddressID(addbean);

		addbean.setId(x + "");
		pobean.setAddress(addbean);
		pobean.setAid(getAccount().getEmail());

		// status is set just before bean is sent in to database;
		PODAO podao = new PODAO();
		podao.sendPO(pobean);
		
		
		// TODO store po items
	}

	

	private static boolean validateCreditCard(CreditCard cred) {
		// simple if billing address name = credit card name
		if (cred.getLname().equals(account.getLname()) && cred.getFname().equals(account.getFname())) {
			pobean.setStatus("PROCESSED");
			return true;
		}
		pobean.setStatus("DECLINED");
		return false;
	}

	public AccountBean getAccount() {
		return account;
	}

	public void setAccount(AccountBean account) {
		PurchaseUtil.account = account;
	}

	public String getComment() {
		return pobean.getComment();
	}

	public void setComment(String comment) {
		pobean.setComment(comment);
	}

	public Map<BookBean, Integer> getCart() {
		return cart;
	}

	public void setCart(Map<BookBean, Integer> cart) {
		this.cart = cart;
	}

}
