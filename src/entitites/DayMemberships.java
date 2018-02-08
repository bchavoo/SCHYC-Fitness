package entitites;

public class DayMemberships extends Product {

	private String productType;
	private String dateTime;
	private Address address;
	private double cost;
	
	//Constructor
	public DayMemberships(String productCode, String productType, String dateTime, Address address, double cost) {
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	

	


}

