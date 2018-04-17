package bean;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

// this class contains all of the information that will be passed as an xml in rest
@XmlRootElement(name="purchaseOrder")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseOrderBean {
	
	@XmlAttribute
	private String orderDate;
	@XmlElement
	private PurchaseOrderShipTo shipTo;
	@XmlElement
	private PurchaseOrderBillTo billTo;
	@XmlElement
	private String comment;
	@XmlElement
	private List<PurchaseOrderItemBean> items;

	public PurchaseOrderBean() {
		super();
	}

	public PurchaseOrderBean(String orderDate, PurchaseOrderShipTo shipto, PurchaseOrderBillTo billTo, String comment,
			List<PurchaseOrderItemBean> items) {
		super();
		this.orderDate = orderDate;
		this.shipTo = shipto;
		this.billTo = billTo;
		this.comment = comment;
		this.items = items;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public PurchaseOrderShipTo getShipto() {
		return shipTo;
	}

	public void setShipto(PurchaseOrderShipTo shipto) {
		this.shipTo = shipto;
	}

	public PurchaseOrderBillTo getBillTo() {
		return billTo;
	}

	public void setBillTo(PurchaseOrderBillTo purchaseOrderBillTo) {
		this.billTo = purchaseOrderBillTo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<PurchaseOrderItemBean> getItems() {
		return items;
	}

	public void setItems(List<PurchaseOrderItemBean> items) {
		this.items = items;
	}

	
}
