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
<<<<<<< HEAD
	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
		return 0;
	}

		
=======
>>>>>>> branch 'master' of ssh://git@git.unl.edu/chavezbryanr/SCHYC_Fitness.git
	
	//Abstract Methods
	public abstract double getTax();
	public abstract double getTotal();
		
}
