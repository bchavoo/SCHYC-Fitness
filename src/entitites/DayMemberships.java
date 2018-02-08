package entitites;

public class DayMemberships extends Product {

	private String startDate;
	private Address address;
	private double cost;
	private String productType;


	//Constructor
	public DayMemberships(String startDate, Address address, double cost, String productCode, String productType) {
		super(productCode);
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




}