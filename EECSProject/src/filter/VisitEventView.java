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
import bean.VisitEventBean;
import dao.VisitEventDAO;

/**
 * Servlet Filter implementation class VisitEventRegister
 */
@WebFilter(dispatcherTypes = {DispatcherType.REQUEST }
					, filterName = "ViewBookRegister", urlPatterns = { "/Home/Book" })
public class VisitEventView implements Filter {

    /**
     * Default constructor. 
     */
    public VisitEventView() {
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if (request.getParameter("book") != null) {
			(new VisitEventDAO()).createEvent(new VisitEventBean(request.getParameter("book"), null, "VIEW"));
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
