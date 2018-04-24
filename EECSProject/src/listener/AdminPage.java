package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class AdminPage
 *
 */
@WebListener
public class AdminPage implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public AdminPage() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	handleEvent(arg0);
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0)  { 
         // TODO Auto-generated method stub
    	handleEvent(arg0);
    }
	
  //this method just helps encapsulate code
    public void handleEvent(HttpSessionBindingEvent event) {
    	if(event.getName().equals("principal")) {
    		double principalTest = (double) event.getSession().getAttribute("principal");
    		if(2 < principalTest) {
    			
    		}
    		//event.getSession().setAttribute(MAX_PRINCIPAL, maxPrincipal);
    		    		
    	}
    }
}
