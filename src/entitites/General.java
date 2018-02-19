package entitites;

public class General extends Member {

	double cost;

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public General(String memberCode, String memberType, Person contact, String name, Address address) {
		super(memberCode, memberType, contact, name, address);
	}

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
