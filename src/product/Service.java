package product;

public abstract class Service extends Product {
	
	/**
	 * The Service class is a child class of the Product super class. The super helps us see that  
	 * Service INHERITS attributes of the Service class. 
	 * @param cost
	 * @param productType
	 */
	private double cost;
	private String productType;
	

	//Constructor
	public Service(String productCode, double cost, String productType) {
		super(productCode);
		//This is an instance where we override the super class Product and added the attribute cost and productType
		this.cost = cost;
		this.productType = productType;
	}
	
	//Getters and setters
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
	

	/**
	 * AbstractMethods
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
