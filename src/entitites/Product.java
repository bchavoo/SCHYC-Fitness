package entitites;

import org.joda.time.DateTime;

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

		
		public String productType;
		public DateTime startDate;
		public DateTime endDate;
		public Address address;
		public String membershipName;
		public double pricePerUnit;
		
		//Constructor
		public YearMemberships(String productCode, String productType, DateTime startDate, DateTime endDate,
				Address address, String membershipName, double pricePerUnit) {
			super(productCode);
			this.productType = productType;
			this.startDate = startDate;
			this.endDate = endDate;
			this.address = address;
			this.membershipName = membershipName;
			this.pricePerUnit = pricePerUnit;
		}

		//Getters and Setters
		public String getProductType() {
			return productType;
		}

		public void setProductType(String productType) {
			this.productType = productType;
		}

		public DateTime getStartDate() {
			return startDate;
		}

		public void setStartDate(DateTime startDate) {
			this.startDate = startDate;
		}

		public DateTime getEndDate() {
			return endDate;
		}

		public void setEndDate(DateTime endDate) {
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

		private String productType;
		private String dateTime;
		private Address address;
		private String cost;
		
		//Constructor
		public DayMemberships(String productCode, String productType, String dateTime, Address address, String cost) {
			super(productCode);
			this.productType = productType;
			this.dateTime = dateTime;
			this.address = address;
			this.cost = cost;
		}

		//Getters and Setters
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

		private String productType;
		private double parkingFee;

		//Constructor
		public ParkingPass(String productCode, String productType, double parkingFee) {
			super(productCode);
			this.productType = productType;
			this.parkingFee = parkingFee;
		}

		//Getters and Setters
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
		private String productType;
		private double cost;

		//Constructor
		public Equipment(String prodructCode, String productType, double cost) {
			super(productCode);
			this.productType = productType;
			this.cost = cost;
		}

		//Getter and Setters
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
