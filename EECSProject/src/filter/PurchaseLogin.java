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
 * Servlet Filter implementation class PurchaseLogin
 */
@WebFilter(
		dispatcherTypes = {
				DispatcherType.REQUEST
		}
					, 
		urlPatterns = { 
				"/Purchase", 
				"/Purchase/*", 
				"/Purchase/"
		})
public class PurchaseLogin implements Filter {

    /**
     * Default constructor. 
     */
    public PurchaseLogin() {
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
		HttpServletRequest sc = (HttpServletRequest) request;
		
		if(sc.getSession().getAttribute("account") == null) {
			request.setAttribute("forwardafter", "/Purchase.jspx");
			request.getRequestDispatcher("/Login/").forward(request, response);
			
		}else {
			chain.doFilter(request, response);
		}

		// pass the request along the bleh chain
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
