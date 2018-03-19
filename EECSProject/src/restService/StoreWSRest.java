package restService;

import java.util.HashMap;
import javax.ws.rs.Path;

@Path("wsr")
public class StoreWSRest {

	HashMap<String, String> catalog;

	public StoreWSRest() {
		
		this.catalog = new HashMap<String, String>();
 // see database for a information to  create a purchase order bean
	}

	void getOrdersByPartNumber(String partNumber) {

	}

}
