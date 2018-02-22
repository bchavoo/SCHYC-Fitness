package product;

//Abstract Product class
public abstract class Product {

	private String productCode;
	
	/**
	 * our product class includes strings of product code and product type
	 * @param productCode
	 */
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
	


}
