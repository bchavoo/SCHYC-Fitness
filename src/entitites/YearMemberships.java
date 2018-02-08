package entitites;

import org.joda.time.DateTime;

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


