package entitites;

import org.joda.time.DateTime;

public class YearMemberships extends Product {

	public DateTime startDate;
	public DateTime endDate;
	public Address address;
	public String membershipName;
	public double cost;
	public String productType;
	
	//Constructor
	public YearMemberships(DateTime startDate, DateTime endDate, Address address,
			String membershipName, double cost, String productCode, String productType) {
		super(productCode);
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.membershipName = membershipName;
		this.cost = cost;
		this.productType = productType;
	}

	//Getters and Setters
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	
	

	
}