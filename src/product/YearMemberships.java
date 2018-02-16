package product;

import entitites.Address;

public abstract class YearMemberships extends Membership {

	public String startDate;
	public String endDate;
	public Address address;
	public String membershipName;
	public double cost;
	public String productType;

	//Constructor
	public YearMemberships(String startDate, String endDate, Address address, String membershipName, double cost, String productCode, String productType) {
		super(productCode, startDate, address, productType);
		this.startDate = startDate;
		this.endDate = endDate;
		this.address = address;
		this.membershipName = membershipName;
		this.cost = cost;
		this.productType = productType;
	}

	//Getters and Setters
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