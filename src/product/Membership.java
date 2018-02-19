package product;

import org.joda.time.DateTime;

import entitites.Address;

//Abstract class of Membership
public abstract class Membership extends Product {

	
	private DateTime startDate;
	private Address address;
	public String productType;
	

	
	public Membership(String productCode, DateTime startDate, Address address, String productType) {
		super(productCode);
		this.startDate = startDate;
		this.address = address;
		this.productType = productType;
	}
	public DateTime getStartDate() {
		return startDate;
	}
	public void setStartDate(DateTime startDate) {
		this.startDate = startDate;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	
	//Abstract Methods to be overridden in General/Student memberships
	public abstract double getTax();
	public abstract double getTotal();
	
	
}
