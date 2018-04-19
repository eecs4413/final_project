package filter;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class PuchaseFilter
 */
@WebFilter(dispatcherTypes = { DispatcherType.REQUEST }, urlPatterns = { "/Purchase" })
public class PuchaseSpamFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public PuchaseSpamFilter() {
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
		// TODO Auto-generated method stub
		HttpServletRequest sc = (HttpServletRequest) request;

		String target = null;

		if (sc.getSession().getAttribute("purchase_tries") == null) {
			sc.getSession().setAttribute("purchase_tries", 0);
		}

		int counter = (int) sc.getSession().getAttribute("purchase_tries");

		counter++;

		sc.getSession().setAttribute("purchase_tries", counter);

		if (sc.getServletContext().getAttribute("account") != null) {
			request.setAttribute("fowardAfter", "/Purchase.jspx");
			target = "/Login.jspx";
			System.out.println("toomany");
		}

		if (((counter - 1) % 3) == 0) {
			target = "/Errorpage.jspx";
		}

		if (target != null) {
			request.getRequestDispatcher(target).forward(request, response);
		} else {
			// pass the request along the filter chain
			chain.doFilter(request, response);
		}

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stubs
	}

}
