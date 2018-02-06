package entitites;

public class Parkingpass {
	
	public class ParkingPass extends Product {

		private String productCode;
		private String productType;
		private double parkingFee;

		//Constructor
		public ParkingPass(String productCode, String productCode2, String productType, double parkingFee) {
			super(productCode);
			//Do we need this? Does the Super below use productCode from its super class
			this.productCode = productCode;
			//Do we need product type? (Assignment II Part I Slide 21)
			this.productType = productType;
			this.parkingFee = parkingFee;
		}

		//Getters and Setters
		public String getProductCode() {
			return productCode;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

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
}