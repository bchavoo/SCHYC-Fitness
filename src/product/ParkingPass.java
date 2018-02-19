package product;

public class ParkingPass extends Service {
	
	
	//Constructor
	public ParkingPass(double cost, String productCode, String productType) {
		super(productCode, cost, productType);
	}


	@Override
	public double getTax() {
		
		return 0.06 * this.getCost();
	}

	@Override
	public double getTotal() {
		if()
		
		return( 0.06 * this.getCost() + this.getCost());
	}







}
