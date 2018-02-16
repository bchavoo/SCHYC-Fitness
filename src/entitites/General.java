package entitites;

public abstract class General extends Member {

	double tax = 0;
	double discount = 0;
	double additionalFee = 0;
	
	public General(String memberCode, String memberType, Person contact, String name, Address address) {
		super(memberCode, memberType, contact, name, address);
		this.tax = tax;
		this.discount = discount;
		this.additionalFee = additionalFee;
	}

	public double getTax() {
		return 0;
	}

	public double getDiscount() {
		return 0;
	}
	
	public double additionalFee() {
		return 0;
	}
}
