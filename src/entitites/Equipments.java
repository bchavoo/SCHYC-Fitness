package entitites;

public class Equipments {

	public class Equipment extends Product {
		private String productCode;
		private String productType;
		private double cost;

		//Constructor
		public Equipment(String productCode, String prodructCode, String productType, double cost) {
			super(productCode);
			//Do we need this? Does the Super below use productCode from its super class
			this.productCode = prodructCode;
			this.productType = productType;
			this.cost = cost;
		}
//Getters
		//Getter and Setter
		public String getProdructCode() {
			return productCode;
		}
		public void setProdructCode(String prodructCode) {
			this.productCode = prodructCode;
		}
		public String getProductType() {
			return productType;
		}
		public void setProductType(String productType) {
			this.productType = productType;
		}
		public double getCost() {
			return cost;
		}
		public void setCost(double cost) {
			this.cost = cost;
		}
	}



}


