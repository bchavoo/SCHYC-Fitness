package product;

public class Equipment extends Service {

	private String equipment;

	//Constructor
	public Equipment(String equipment, double cost, String productCode, String productType) {
		super(productCode, cost, productType);
		this.equipment = equipment;
	}

	//Getters and Setters
	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;

	}

	@Override
	public double getSubTotal(double cost, double quantity) {

		return cost * quantity;
	}

	@Override
	public double getTax(double cost) {

		return 0.04 * cost;
	}

	@Override
	public double getTotal(double cost, double tax) {

		return cost + tax;
	}


}














