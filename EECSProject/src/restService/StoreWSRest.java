package restService;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;
import bean.AccountBean;
import bean.BookBean;
import bean.POBean;
import bean.POItemBean;
import bean.POXMLWrapper;
import bean.PurchaseOrderBean;
import bean.PurchaseOrderBillTo;
import bean.PurchaseOrderItemBean;
import bean.PurchaseOrderShipTo;
import dao.AccountDAO;
import dao.BookDAO;
import dao.PODAO;
import dao.POItemDAO;

@Path("wsr") // this is the path of the service
public class StoreWSRest {

	POXMLWrapper poxmlWrapper;

	public StoreWSRest() {

	}

	// this is a READ method on the service
	// http:localhost:8080/EECSProject/rest/wsr/getOrders?partNumber=b001
	@GET
	@Path("/getOrders/")
	@Produces("text/xml")
	public String getOrdersByPartNumber(@DefaultValue("b001") @QueryParam("partNumber") String partNumber) {

		List<PurchaseOrderBean> orderBeans = new ArrayList<PurchaseOrderBean>();
		
		
		
		
		for (POBean pobean : getPObeansWith(partNumber)) {
			
			ArrayList<PurchaseOrderItemBean> items = new ArrayList<PurchaseOrderItemBean>();

			PurchaseOrderBean orderBean = new PurchaseOrderBean();
			
			
			orderBean.setBillTo(new PurchaseOrderBillTo(getAccountofPObean(pobean)));
			orderBean.setShipto(new PurchaseOrderShipTo(pobean.getAddress(), pobean));
			orderBean.setComment(pobean.getComment());

			for (POItemBean bean : getPOItemsFor(pobean)) {
				
				items.add(new PurchaseOrderItemBean(partNumber, bean));
			}
		
			orderBean.setItems(items);
			orderBeans.add(orderBean);
		}
		StringWriter sw = new StringWriter();
	

		try {
			poxmlWrapper = new POXMLWrapper(orderBeans);
			

			JAXBContext jaxbContext = JAXBContext.newInstance(POXMLWrapper.class);

			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			// SchemaFactory sf =
			// SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			// Schema schema = sf.newSchema(new File(
			// "C:\\Users\\dwell\\git\\final_project\\EECSProject\\WebContent\\po.xsd"));

			// marshaller.setSchema(schema);
			// standard IO
			sw.write("\n");

			marshaller.marshal(poxmlWrapper, new StreamResult(sw));

		} catch (Exception e) {
			// System.out.println("Change the path of the po.xsd if you have not done so
			// already");
		//	e.printStackTrace();
		}

		return sw.toString();
	}

	private AccountBean getAccountofPObean(POBean pobean) {
		return (new AccountDAO()).retrieveAccount(pobean.getAid());
	}

	private ArrayList<POItemBean> getPOItemsFor(POBean pobean) {

		Map<String, POItemBean> poitems = (new POItemDAO()).retrieve();
		ArrayList<POItemBean> temp = new ArrayList<POItemBean>();

		for (Entry<String, POItemBean> entry : poitems.entrySet()) {
			if (entry.getKey().contains(pobean.getId() + ","))
				temp.add(entry.getValue());
		}
		return temp;
	}

	private ArrayList<POBean> getPObeansWith(String bid) {
		
		Map<String, POItemBean> poitems = (new POItemDAO()).retrieve();

		Map<String, POBean> po = (new PODAO()).retrieve();

		ArrayList<POBean> temp = new ArrayList<POBean>();

		for (Entry<String, POItemBean> entry : poitems.entrySet()) {
			
			
			if (entry.getValue().getBid().equals(bid)) {
				
				temp.add(po.get(entry.getValue().getId()));
				
				
			}
		}
		return temp;
	}

}
