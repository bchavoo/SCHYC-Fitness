package product;

import entitites.Address;

public abstract class DayMemberships extends Membership {

	private String startDate;
	private Address address;
	private double cost;
	private String productType;


	//Constructor
	public DayMemberships(String startDate, Address address, double cost, String productCode, String productType) {
		super(productCode, startDate, address, productType);
		this.startDate = startDate;
		this.address = address;
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


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
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
	
	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return 0.06;
	}

	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

	



}