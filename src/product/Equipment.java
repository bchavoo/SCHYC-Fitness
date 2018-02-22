package product;

public class Equipment extends Service {

	private String equipment;

	/**
	 * The Equipment is  child class of the Service super class. The super helps us see that the 
	 *Equipment INHERENTES attributes of the Service class. 
	 * @param equipment
	 * @param cost
	 * @param productCode
	 * @param productType
	 */
	
	 //Overridden from the super class, more arguments were added
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
	
    /*
	 * The method below are overridden, the abstraction creation helps with the process
	 * and with the calculations needed to be done.
	 */
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














