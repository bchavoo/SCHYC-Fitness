package product;

public class ParkingPass extends Service {
	
	
	//Constructor
	public ParkingPass(double cost, String productCode, String productType) {
		super(productCode, cost, productType);
	}

	@Override
	public double getSubTotal(double cost, double quantity) {

		return cost * quantity;
	}

	@Override
	public double getTax(double cost) {

		return 0.06 * cost;
	}

	@Override
	public double getTotal(double cost, double tax) {

		return cost + tax;
	}











}
