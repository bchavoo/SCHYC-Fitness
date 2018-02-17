package entitites;

public class InvoiceProducts {
	
	private String productCode;
	private double quantity;
	private String personCode;
	
	public InvoiceProducts(String productCode, double quantity, String personCode) {
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
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	public String getPersonCode() {
		return personCode;
	}
	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}



}
