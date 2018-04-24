package filter;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;

import bean.POItemBean;
import bean.VisitEventBean;
import dao.VisitEventDAO;

/**
 * Servlet Filter implementation class VisitEvent
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, description = "registers various visit events", urlPatterns = {
		"/Home/*", "/Home", "/Purchase/", "/Purchase/*" })
public class VisitEvent implements Filter {

	/**
	 * Default constructor.
	 */
	public VisitEvent() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// book view event
		HttpServletRequest sc = (HttpServletRequest) request;

		if (request.getParameter("bookID") != null) {
			(new VisitEventDAO()).createEvent(new VisitEventBean(request.getParameter("bookID"), null, "VIEW"));
			System.out.println("Registered View Book :" + request.getParameter("bookID"));

		}
		// book purchase event
		if (request.getParameter("ConfirmOrderButton") != null) {
			ArrayList<POItemBean> cart = (ArrayList<POItemBean>) sc.getSession().getAttribute("cart");

			for (POItemBean bean : cart) {
				(new VisitEventDAO()).createEvent(new VisitEventBean(bean.getBid(), null, "PURCHASE"));
				System.out.println("Registered Purchase Book :" + bean.getBid());
			}

		}
		// book add to cart event
		String item = request.getParameter("addCart");

		if (item != null && !item.isEmpty()) {
			(new VisitEventDAO()).createEvent(new VisitEventBean(item, null, "CART"));
			System.out.println("Registered Cart Book :" + item);
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
