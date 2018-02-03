package entitites;

public class Member {
	
	private String memberCode;
	private Person contact;
	private String name;
	private Address address;
	
	//Constructor
	public Member (String memberCode, Person contact, String name, Address address) {
		super();
		this.memberCode = memberCode;
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
	
	
	
	

}
