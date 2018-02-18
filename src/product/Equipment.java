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
	public double getTax() {

		return 0.06;
	}

	@Override
	public double getTotal() {
		
		return 0;
	}









}




