package soapService;

import java.io.StringWriter;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import bean.BookBean;
import dao.BookDAO;
// This is complete
public class StoreWSSoap {

	Map<String, BookBean> catalog;

	public StoreWSSoap() {
		BookDAO bookDAO = new BookDAO();
		catalog = bookDAO.retrieve();
	}

	public String getProductInfo(String productId) {

		try {
			JAXBContext jc = JAXBContext.newInstance(catalog.get(productId).getClass());
			Marshaller marshaller = jc.createMarshaller();

			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);

			StringWriter sw = new StringWriter();
			sw.write("\n");

			marshaller.marshal(catalog.get(productId), new StreamResult(sw));
			return sw.toString();

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
