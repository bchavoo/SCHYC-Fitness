package product;

public class Service extends Product {
	
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

		
	

}
