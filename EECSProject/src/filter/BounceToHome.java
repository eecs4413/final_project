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

import com.mysql.fabric.xmlrpc.base.Array;

import bean.POItemBean;

/**
 * Servlet Filter implementation class BounceToHome
 */
@WebFilter(dispatcherTypes = {
		DispatcherType.REQUEST }, description = "is cart is empty you cannot enter puchase information or checkout", urlPatterns = {
				"/Cart/*", "/Cart", "/Purchase", "/Purchase/*" })
public class BounceToHome implements Filter {

	/**
	 * Default constructor.
	 */
	public BounceToHome() {
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
		HttpServletRequest sc = (HttpServletRequest) request;
		ArrayList<POItemBean> cart = (ArrayList<POItemBean>) sc.getSession().getAttribute("cart");

		if (cart != null && !cart.isEmpty()) {
			chain.doFilter(request, response);
		} else {

			request.getRequestDispatcher("/Home").forward(request, response);

		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
