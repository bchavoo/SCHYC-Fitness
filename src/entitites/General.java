package entitites;

public abstract class General extends Member {

	
	
	public General(String memberCode, String memberType, Person contact, String name, Address address) {
		super(memberCode, memberType, contact, name, address);
	}

	@Override
	public double getTax() {
		return 0.06;
	}

	@Override
	public double getDiscount() {

		return 0;
	}

	@Override
	public double additionalFee() {

		return 0;
	}

	
}
