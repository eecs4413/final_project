package restService;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import bean.BookBean;
import bean.POBean;
import bean.POItemBean;
import bean.PurchaseOrderBean;
import bean.PurchaseOrderItemBean;
import bean.ResultsBean;
import dao.BookDAO;
import dao.PODAO;
import dao.POItemDAO;

@Path("wsr") // this is the path of the service
public class StoreWSRest {

	Map<String, BookBean> catalog;

	PurchaseOrderBean purchaseOrderBean;
	POItemDAO poItemDAO;
	PODAO po;

	public StoreWSRest() {
		this.purchaseOrderBean = new PurchaseOrderBean();
		poItemDAO = new POItemDAO();
		po = new PODAO();

		this.catalog = (new BookDAO()).retrieve();
		// see database for a information to create a purchase order bean
	}
	//
	// // this is a READ method on the service
	// // http://localhost:8080/EECSProject/rest/wsr/getOrders?partNumber=b001
	// @GET
	// @Path("/getOrders/")
	// @Produces("text/xml")
	// public String getOrdersByPartNumber(@DefaultValue("b001")
	// @QueryParam("partNumber") String partNumber) {
	//
	// // TODO acess information for the purchaseOrderbean with Dao
	//
	// // person1 (id=1) -> address1 (id=1)
	//
	// Map<String, POBean> temp = po.retrieve();
	// System.out.println("wTf!");
	// for (POBean item : temp.values()) {
	// System.out.println(item.getAddress());
	// // if(item.getBid() == )
	// }
	//
	// // for(PurchaseOrderItemBean item : purchaseOrderBean.getItems()) {
	// // if(item.getPartNum() == partNumber) {
	// // System.out.println(item.toString());
	// // }
	// // }
	// // purchaseOrderBean.setItems(items);
	// try {
	//
	// // JAXBContext jc = JAXBContext.newInstance(purchaseOrderBean.getClass());
	// JAXBContext jc = JAXBContext.newInstance(purchaseOrderBean.getClass());
	// Marshaller marshaller = jc.createMarshaller();
	//
	// marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
	// marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
	//
	// StringWriter sw = new StringWriter();
	// sw.write("\n");
	//
	// marshaller.marshal(purchaseOrderBean, new StreamResult(sw));
	// System.out.println(sw.toString());
	// return sw.toString();
	//
	// } catch (JAXBException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// System.out.println("WTF!");
	// return null;
	// }
	//
	// // TODO fill bean with dan retrive data
	//
	// // TODO marsall bean to xml
	//
	// // TODO return xml
	//
	// }

	

}
