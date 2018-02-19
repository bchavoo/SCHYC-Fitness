package entitites;

public class Student extends Member {

	double cost;
	
	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public Student(String memberCode, String memberType, Person contact, String name, Address address) {
		super(memberCode, memberType, contact, name, address);
		// TODO Auto-generated constructor stub
	}

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
