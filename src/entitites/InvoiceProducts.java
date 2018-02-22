package entitites;

public class InvoiceProducts {
	
	/**
	 * We create a invoice product class that would include the product and person code and the amount of products (quantity)
	 * @param productCode
	 * @param quantity
	 * @param personCode
	 */
	
	private String productCode;
	private int quantity;
	private String personCode;
	
	// here we create constructors and getter and setter
	public InvoiceProducts(String productCode, int quantity, String personCode) {
		super();
		this.productCode = productCode;
		this.quantity = quantity;
		this.personCode = personCode;
	}
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}



}
