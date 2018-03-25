package bean;

import java.util.ArrayList;

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
	private PurchaseOrderShipTo shipto;
	@XmlElement
	private PurchaseOrderShipTo billTo;
	@XmlElement
	private String comment;
	@XmlElement
	private ArrayList<PurchaseOrderItemBean> items;

	public PurchaseOrderBean() {
		super();
	}

	public PurchaseOrderBean(String orderDate, PurchaseOrderShipTo shipto, PurchaseOrderShipTo billTo, String comment,
			ArrayList<PurchaseOrderItemBean> items) {
		super();
		this.orderDate = orderDate;
		this.shipto = shipto;
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
		return shipto;
	}

	public void setShipto(PurchaseOrderShipTo shipto) {
		this.shipto = shipto;
	}

	public PurchaseOrderShipTo getBillTo() {
		return billTo;
	}

	public void setBillTo(PurchaseOrderShipTo billTo) {
		this.billTo = billTo;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ArrayList<PurchaseOrderItemBean> getItems() {
		return items;
	}

	public void setItems(ArrayList<PurchaseOrderItemBean> items) {
		this.items = items;
	}

}
