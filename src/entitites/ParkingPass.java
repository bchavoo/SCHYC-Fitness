package entitites;

public class ParkingPass extends Product {

	private String productType;
	private double parkingFee;

	//Constructor
	public ParkingPass(String productCode, String productType, double parkingFee) {
		super(productCode);
		this.productType = productType;
		this.parkingFee = parkingFee;
	}

	//Getters and Setters
	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public double getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(double parkingFee) {
		this.parkingFee = parkingFee;
	}


}
