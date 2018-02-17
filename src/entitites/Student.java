package entitites;

public class Student extends Member {

	public Student(String memberCode, String memberType, Person contact, String name, Address address) {
		super(memberCode, memberType, contact, name, address);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getDiscount() {
		double total = 0;
		
		return 0.08 * total;
	}

	@Override
	public double additionalFee() {

		return 10.50;
	}

	@Override
	public double getTax() {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
