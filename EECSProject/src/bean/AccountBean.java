package bean;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "account")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountBean {

		@XmlElement
		private String email;
		@XmlElement
		private String password;
		@XmlElement
		private String lname;
		@XmlElement
		private String fname;
		@XmlElement
		private AddressBean address;
		@XmlElement
		private List<PurchaseOrderBean> orders;

		public AccountBean() {
		}
		
		//for simplicity, account username is the email (tbd)
		public AccountBean(String email, String password, String lname,
				String fname, AddressBean address, List<PurchaseOrderBean> orders) {
			this.email = email;
			this.password = password;
			this.lname = lname;
			this.fname = fname;
			this.address = address;
			this.orders = orders;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getLname() {
			return lname;
		}

		public void setLname(String lname) {
			this.lname = lname;
		}

		public String getFname() {
			return fname;
		}

		public void setFname(String fname) {
			this.fname = fname;
		}

		public AddressBean getAddress() {
			return this.address;
		}

		public void setAddress(AddressBean address) {
			this.address = address;
		}
		
		public List<PurchaseOrderBean> getOrders() {
			return orders;
		}

		public void setOrders(List<PurchaseOrderBean> orders) {
			this.orders = orders;
		}
	
}