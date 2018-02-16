package product;


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
	
<<<<<<< HEAD
	public abstract double getTax();
	public abstract double getTotal();

=======
>>>>>>> branch 'master' of ssh://git@git.unl.edu/chavezbryanr/SCHYC_Fitness.git

}
