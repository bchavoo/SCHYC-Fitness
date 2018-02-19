package product;

public class ParkingPass extends Service {
	
	
	//Constructor
	public ParkingPass(double cost, String productCode, String productType) {
		super(productCode, cost, productType);
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
