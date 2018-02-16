package product;

import entitites.Address;

public abstract class Membership extends Product {

	
	private String startDate;
	private Address address;
	public String productType;
	

	
	
	public Membership(String productCode, String startDate, Address address, String productType) {
		super(productCode);
		this.startDate = startDate;
		this.address = address;
		this.productType = productType;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
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
	
	//Abstract Methods
	public abstract double getTax();
	public abstract double getTotal();

	

}
