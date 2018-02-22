package product;

public abstract class Service extends Product {
	
	/**
	 * The service is  child class of the Product super class. The super helps us see that the 
	 *Service INHERENTES attributes of the Service class. 
	 * @param productCode
	 * @param cost
	 * @param productType
	 */
	
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
	/**
	 * Here we create abstract methods to successfully use to override the super class
	 * and calculations
	 * @param cost
	 * @param quantity
	 * @return
	 */
	public abstract double getSubTotal(double cost, double quantity);
	public abstract double getTax(double cost);
	public abstract double getTotal(double cost, double tax);
		
}
