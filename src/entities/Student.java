package entities;

public class Student extends Member {

	double cost;
	/**
	 * Here the student is a sub class for the member super class and the student class 
	 * inherits the attributes of the member class
	 */
	public Student(String memberCode, String memberType, Person contact, String name, Address address) {
		super(memberCode, memberType, contact, name, address);
	}

	//Getters and Setters
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	/**
	 * Here the the super class methods are overridden and we used the abstraction method 
	 * to help us with returning the right calculations
	 */

	@Override
	public double getDiscount() {
		double totalD = this.getCost() - 0.08 * this.getCost();

		return totalD;
	}

	@Override
	public double additionalFee() {
		double totalA = 10.50;

		return totalA;
	}

	@Override
	public double getTax() {

		return 0;
	}

	@Override
	public double getTotal() {
		double totalA = 10.50;
		double totalD = this.getCost() - 0.08 * this.getCost();

		return totalD + totalA;
	}


}
