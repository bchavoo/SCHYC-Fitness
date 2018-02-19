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

		return (0.06 * this.getCost());
	}

	@Override
	public double getTotal() {
		if(getProductType() == "Y"){
			
		}
		else if (this.equipment.equals(getProductType())){
			
			return (0.06 * this.getCost() + this.getCost() * 0.05);
			
		}
		else {
			
		
		return (0.06 * this.getCost() + this.getCost());
		
	
	}
	}
}














