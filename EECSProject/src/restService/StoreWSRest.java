package restService;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import bean.PurchaseOrderBean;

@Path("wsr") // this is the path of the service
public class StoreWSRest {

	PurchaseOrderBean purchaseOrderBean;

	public StoreWSRest() {
		this.purchaseOrderBean = new PurchaseOrderBean();
		// see database for a information to create a purchase order bean
	}

	// this is a READ method on the service
	// http://localhost:8080/EECSProject/rest/wsr/getOrders?partNumber=b001
	@GET
	@Path("/getOrders/")
	@Produces("text/xml")
	void getOrdersByPartNumber(@DefaultValue("b001") @QueryParam("partNumber") String partNumber) {

		// TODO acess information for the purchaseOrderbean with Dao

		// TODO fill bean with dan retrive data

		// TODO marsall bean to xml

		// TODO return xml

		//

	}

}
