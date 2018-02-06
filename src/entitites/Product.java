package entitites;

public class Product {

	private String productCode;

	//Constructor
	public Product(String productCode) {
		super();
		this.productCode = productCode;
	}


	//Getters and Setters
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}



	//Year Long Memberships-------------------------------------------------------------------------------------------------
	public class YearMemberships extends Product {

		public String productCode;
		public String productType;
		public String startDate;
		public String endDate;
		public Address address;
		public String membershipName;
		public double pricePerUnit;

		//Constructor
		public YearMemberships(String productCode, String productType, String startDate,
				String endDate, Address address, String membershipName, double pricePerUnit) {
			super(productCode);
			this.productType = productType;
			this.startDate = startDate;
			this.endDate = endDate;
			this.address = address;
			this.membershipName = membershipName;
			this.pricePerUnit = pricePerUnit;
		}

		//Getters and Setters
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

		public double getPricePerUnit() {
			return pricePerUnit;
		}

		public void setPricePerUnit(double pricePerUnit) {
			this.pricePerUnit = pricePerUnit;
		}


	}



	//Day Memberships-------------------------------------------------------------------------------------------------
	public class DayMemberships extends Product {


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



	//ParkingPass-------------------------------------------------------------------------------------------------
	public class ParkingPass extends Product {

		private String productCode;
		private String productType;
		private double parkingFee;

		//Constructor
		public ParkingPass(String productCode, String productCode2, String productType, double parkingFee) {
			super(productCode);
			//Do we need this? Does the Super below use productCode from its super class
			this.productCode = productCode;
			//Do we need product type? (Assignment II Part I Slide 21)
			this.productType = productType;
			this.parkingFee = parkingFee;
		}

		//Getters and Setters
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

		public double getParkingFee() {
			return parkingFee;
		}

		public void setParkingFee(double parkingFee) {
			this.parkingFee = parkingFee;
		}


	}


	//Equipment-------------------------------------------------------------------------------------------------
	public class Equipment extends Product {
		private String productCode;
		private String productType;
		private double cost;

		//Constructor
		public Equipment(String productCode, String prodructCode, String productType, double cost) {
			super(productCode);
			//Do we need this? Does the Super below use productCode from its super class
			this.productCode = prodructCode;
			this.productType = productType;
			this.cost = cost;
		}

		//Getter and Setter
		public String getProdructCode() {
			return productCode;
		}
		public void setProdructCode(String prodructCode) {
			this.productCode = prodructCode;
		}
		public String getProductType() {
			return productType;
		}
		public void setProductType(String productType) {
			this.productType = productType;
		}
		public double getCost() {
			return cost;
		}
		public void setCost(double cost) {
			this.cost = cost;
		}
	}



}
