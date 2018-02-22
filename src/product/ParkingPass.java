package product;

public class ParkingPass extends Service {
	
	//Parking pass is the child class of the service and inherits all its attributes
	//Constructor
	public ParkingPass(double cost, String productCode, String productType) {
		super(productCode, cost, productType);
	}

	/** 
	 * Here the the super class is overridden and we used the abstraction method 
	 * to help us with returning the right calculations
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
