package entitites;

public class YearMemberShip {
	public class YearMemberships extends Product {

		private String productCode;
		private String productType;
		private String startDate;
		private String endDate;
		private Address address;
		private String membershipName;
		private Double pricePerUnit;

		//Constructor
		public YearMemberships(String productCode, String productCode2, String productType, String startDate,
				String endDate, Address address, String membershipName, Double pricePerUnit) {
			super(productCode);
			productCode = productCode2;
			this.productType = productType;
			this.startDate = startDate;
			this.endDate = endDate;
			this.address = address;
			this.membershipName = membershipName;
			this.pricePerUnit = pricePerUnit;
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

		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public String getMembershipName() {
			return membershipName;
		}

		public void setMembershipName(String membershipName) {
			this.membershipName = membershipName;
		}

		public Double getPricePerUnit() {
			return pricePerUnit;
		}

		public void setPricePerUnit(Double pricePerUnit) {
			this.pricePerUnit = pricePerUnit;
		}

	}



}
