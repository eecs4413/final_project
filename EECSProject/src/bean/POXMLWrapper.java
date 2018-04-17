package bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="POListWrapper")
@XmlAccessorType(XmlAccessType.FIELD)
public class POXMLWrapper {
	
	@XmlElement
	private List<PurchaseOrderBean> purchaseOrder;

	public POXMLWrapper() {
		super();
	}

	public POXMLWrapper(List<PurchaseOrderBean> list) {
		super();
		this.purchaseOrder = list;
	}

	/**
	 * @return the list
	 */
	public List<PurchaseOrderBean> getList() {
		return purchaseOrder;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<PurchaseOrderBean> list) {
		this.purchaseOrder = list;
	}
}
