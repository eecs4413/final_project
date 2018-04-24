package listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import bean.POBean;
import bean.POItemBean;

/**
 * Application Lifecycle Listener implementation class BookSoldCounter
 *
 */
@WebListener
public class BookSoldCounter implements HttpSessionAttributeListener {

	/**
	 * Default constructor.
	 */
	public BookSoldCounter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
	 */
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		POBean poBean;
		if (arg0.getName().equals("PoBean")) {
			poBean = (POBean) arg0.getValue();

			if (poBean.getStatus().equals("PROCESSED")) {

				ArrayList<POItemBean> cart = (ArrayList<POItemBean>) arg0.getSession().getAttribute("cart");

				for (POItemBean bean : cart) {
					Map<String, Integer> topten = (Map<String, Integer>) arg0.getSession().getServletContext()
							.getAttribute("sold");
					if (topten == null) {
						topten = new HashMap<String, Integer>();
						topten.put(bean.getBid(), Integer.parseInt(bean.getQuantity()));
					}

					if (topten.containsKey(bean.getBid())) {
						int value = Integer.parseInt(bean.getQuantity()) + topten.get(bean.getBid());
						topten.put(bean.getBid(), value );

					} else {
						topten.put(bean.getBid(), Integer.parseInt(bean.getQuantity()));
					}

					arg0.getSession().getServletContext().setAttribute("sold", topten);
				}

			}

		}

	}

	/**
	 * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
	 */
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
	 */
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
	}

}
