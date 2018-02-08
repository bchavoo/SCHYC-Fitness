package entitites;

public class Equipment extends Product {
	private String productType;
	private double cost;

	//Constructor
	public Equipment(String productCode, String productType, double cost) {
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




