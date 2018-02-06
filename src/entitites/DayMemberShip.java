package entitites;

public class DayMemberShip {
	
	public class DayMemberships extends Product {
//Day membership

		private String productCode;
		private String productType;
		private String dateTime;
		private Address address;
		private String cost;

		//Constructor
		public DayMemberships(String productCode, String productCode2, String productType, String dateTime, Address address, String cost) {
			super(productCode);
			//Do we need this? Does the Super below use productCode from its super class
			this.productCode = productCode;
			this.productType = productType;
			this.dateTime = dateTime;
			this.address = address;
			this.cost = cost;
		}

		//Getter and Setter
		public String getProductCode() {
			return productCode;
		}
		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}
		public String getProductType() {
			return productType;
		}
		public void setProductType(String productType) {
			this.productType = productType;
		}
		public String getDateTime() {
			return dateTime;
		}
		public void setDateTime(String dateTime) {
			this.dateTime = dateTime;
		}
		public Address getAddress() {
			return address;
		}
		public void setAddress(Address address) {
			this.address = address;
		}
		public String getCost() {
			return cost;
		}
		public void setCost(String cost) {
			this.cost = cost;
		}


	}

}
