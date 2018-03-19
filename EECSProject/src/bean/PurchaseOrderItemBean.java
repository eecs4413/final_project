package bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "billTo")
@XmlAccessorType(XmlAccessType.FIELD)
public class PurchaseOrderItemBean {
	@XmlAttribute
	private String partNum;
	@XmlElement
	private String productName;
	@XmlElement
	private int quantity;
	@XmlElement
	private float USPrice;
	@XmlElement
	private String shipDate;
	@XmlElement
	private String comment;

	public PurchaseOrderItemBean() {
		super();
	}

	public PurchaseOrderItemBean(String partNum, String productName, int quantity, float uSPrice, String shipDate,
			String comment) {
		super();
		this.partNum = partNum;
		this.productName = productName;
		this.quantity = quantity;
		USPrice = uSPrice;
		this.shipDate = shipDate;
		this.comment = comment;
	}

	public String getPartNum() {
		return partNum;
	}

	public void setPartNum(String partNum) {
		this.partNum = partNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getUSPrice() {
		return USPrice;
	}

	public void setUSPrice(float uSPrice) {
		USPrice = uSPrice;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
