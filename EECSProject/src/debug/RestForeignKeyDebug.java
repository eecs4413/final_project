package debug;

import java.io.StringWriter;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import bean.POBean;
import bean.POItemBean;
import bean.PurchaseOrderBean;
import dao.PODAO;
import dao.POItemDAO;
import dao.PurchaseOrderDAO;

@Path("fkt")
public class RestForeignKeyDebug {

	PODAO po;
	PurchaseOrderBean purchaseOrderBean;
	POItemDAO poItemDAO;
	PODAO2 po2;

	public RestForeignKeyDebug() {
		po = new PODAO();
		this.purchaseOrderBean = new PurchaseOrderBean();
		poItemDAO = new POItemDAO();
		po2 = new PODAO2();
		
		// see database for a information to create a purchase order bean
	}

	// this is a READ method on the service
	// http://localhost:8080/EECSProject/rest/fkt/test?partNumber=b001
	@GET
	@Path("/test/")
	@Produces("text/xml")
	public String getOrdersByPartNumber(@DefaultValue("b001") @QueryParam("partNumber") String partNumber) {

		// TODO acess information for the purchaseOrderbean with Dao
		
		//person1 (id=1) -> address1 (id=1)
		
		Map<String, POBean> temp = po.retrieve();
		Map<String, POBean> temp2 = po2.retrieve("1");
		
		for(POBean item : temp2.values()) {
			System.out.println(item.getId() + " " + item.getFname()
			+ " " + item.getLname() + " "
			+ item.getStatus() + " " + item.getAddress());
		}
		
		
		for(POBean item : temp.values()) {
		//	System.out.println(item.getAddress().toString());
			//if(item.getBid() == )
		}
		
		
		
//		for(PurchaseOrderItemBean item : purchaseOrderBean.getItems()) {
//			if(item.getPartNum() == partNumber) {
//				System.out.println(item.toString());
//			}	
//		}
		//purchaseOrderBean.setItems(items);
				try {
					
					
					//JAXBContext jc = JAXBContext.newInstance(purchaseOrderBean.getClass());
					JAXBContext jc = JAXBContext.newInstance(purchaseOrderBean.getClass());
					Marshaller marshaller = jc.createMarshaller();

					marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
					marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

					StringWriter sw = new StringWriter();
					sw.write("\n");

					marshaller.marshal(purchaseOrderBean, new StreamResult(sw));
					System.out.println(sw.toString());
					return sw.toString();

				} catch (JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("WTF!");
					return null;
				}

	}

}
