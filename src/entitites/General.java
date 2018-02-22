package entitites;

public class General extends Member {

	double cost;
	
	/*
	 * Inheritance is used here because the child class (General) inherits all of the attributes of the Member class
	 * then below we add getters and setters or the general attributes which is its cost.
	 */

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public General(String memberCode, String memberType, Person contact, String name, Address address) {
		super(memberCode, memberType, contact, name, address);
	}
	/*
	 * Here the abstraction is in use and overrides the parent class 
	 * we have the calculations of the taxes and discount and its total
	 */

	@Override
	public double getTax() {
		double totalT = 0.06 * this.getCost();

		return totalT;
	}

	@Override
	public double getDiscount() {

		return 0;
	}

	@Override
	public double additionalFee() {

		return 0;
	}

	@Override
	public double getTotal() {

		double totalT = 0.06 * this.getCost();
		double totalG = this.getCost();

		return totalT + totalG;
	}


}
