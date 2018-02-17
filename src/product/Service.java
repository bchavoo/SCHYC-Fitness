package product;

public abstract class Service extends Product {
	
	private double cost;
	private String productType;
	

	
	public Service(String productCode, double cost, String productType) {
		super(productCode);
		this.cost = cost;
		this.productType = productType;
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
	

	//Abstract Methods
	public abstract double getTax();
	public abstract double getTotal();
		
}
