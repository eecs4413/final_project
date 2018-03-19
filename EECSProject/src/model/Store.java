package model;

import java.util.HashMap;
import java.util.Map;
import bean.AddressBean;
import bean.BookBean;
import bean.POBean;
import bean.POItemBean;
import bean.VisitEventBean;
import dao.*;

public class Store {

	AddressDAO addressDAO;
	BookDAO bookDAO;
	PODAO podao;
	POItemDAO poItemDAO;
	VisitEventDAO visitEventDAO;

	public Store() {

		addressDAO = new AddressDAO();
		bookDAO = new BookDAO();
		podao = new PODAO();
		poItemDAO = new POItemDAO();
		visitEventDAO = new VisitEventDAO();

	}

	public Map<String, AddressBean> retrieveAddress() {
		return addressDAO.retrieve();
	}

	public Map<String, BookBean> retrieveBook() {
		return bookDAO.retrieve();
	}

	// retrieve and complete the bean
	public Map<String, POBean> retrievePO() {

		Map<String, AddressBean> address_lst = this.retrieveAddress();
		Map<String, POBean> po_lst = new HashMap<String, POBean>();

		for (Map.Entry<String, POBean> entry : podao.retrieve().entrySet()) {

			AddressBean filled_bean = address_lst.get(entry.getValue().getAddress().getId());
			po_lst.put(entry.getKey(), entry.getValue());
			po_lst.get(entry.getKey()).setAddress(filled_bean);
		}

		return po_lst;
	}

	public Map<String, POItemBean> retrievePOItem() {
		return poItemDAO.retrieve();
	}

	public Map<String, VisitEventBean> retrieveVisitEvent() {
		return visitEventDAO.retrieve();
	}
	
	

}
