package product;

public class Equipment extends Service {

	private String equipment;
	private double cost;
	private String productType;

	
	//Constructor
	public Equipment(String equipment, double cost, String productCode, String productType) {
		super(productCode, cost, productType);
		this.equipment = equipment;
		this.cost = cost;
		this.productType = productType;
	}

	//Getters and Setters
	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
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

		return 0.06;
	}

	@Override
	public double getTotal() {
		
		return 0;
	}









}



