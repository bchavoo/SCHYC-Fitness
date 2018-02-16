package entitites;

public abstract class Student extends Member {

	public Student(String memberCode, String memberType, Person contact, String name, Address address) {
		super(memberCode, memberType, contact, name, address);
		// TODO Auto-generated constructor stub
	}

	@Override
	public double getTax() {

		return 0.06;
	}

	@Override
	public double getDiscount() {

		return 0.08;
	}

	@Override
	public double additionalFee() {

		return 10.50;
	}

	
}
