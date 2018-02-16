package product;

public class ParkingPass extends Service {

	private double cost;
	private String productType;
	
	
	//Constructor
	public ParkingPass(double cost, String productCode, String productType) {
		super(productCode, cost, productType);
		this.cost = cost;
		this.productType = productType;
	}

	//Getters and Setters
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
		
		return 0.06;
	}

	@Override
	public double getTotal() {
		
		return 0;
	}







}
