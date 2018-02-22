package entitites;

public abstract class Member {

	/**
	 * Here we have variables needed for the member class
	 */
	public String memberCode;
	public String memberType;
	public Person contact;
	public String name;
	public Address address;


	/**
	 * Created constructor with its arguments of the member class. And we created getters and setters
	 * @param memberCode
	 * @param memberType
	 * @param contact
	 * @param name
	 * @param address
	 */
	public Member(String memberCode, String memberType, Person contact, String name, Address address) {
		super();
		this.memberCode = memberCode;
		this.memberType = memberType;
		this.contact = contact;
		this.name = name;
		this.address = address;
	}

	//Getters and Setters
	public String getMemberCode() {
		return memberCode;
	}


	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}


	public String getMemberType() {
		return memberType;
	}


	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}


	public Person getContact() {
		return contact;
	}


	public void setContact(Person contact) {
		this.contact = contact;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}
	
	//Abstract Methods
	/**
	 * Here we use abstraction methods so that it could help us with calculations and later
	 * override the super class
	 */
	 // The abstraction will hide some details and only show the right
	//features of the object
	public abstract double getTax();
	public abstract double getDiscount();
	public abstract double additionalFee();
	public abstract double getTotal();

}