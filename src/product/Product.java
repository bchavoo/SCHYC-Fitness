package product;

//Abstract product class
public abstract class Product {

	private String productCode;

	//Constructor
	public Product(String productCode) {
		super();
		this.productCode = productCode;
	}


	//Getters and Setters
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	
	//Abstract methods to be overridden in Membership and Service
	public abstract double getTax();
	public abstract double getTotal();


}
