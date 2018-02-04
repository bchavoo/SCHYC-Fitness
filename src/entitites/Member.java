package entitites;

public class Member {
	
	private String memberCode;
	private String memberType;
	private String personCode;
	private String name;
	private Address address;
	
	//Constructor
	public Member(String memberCode, String memberType, String personCode, String name, Address address) {
		super();
		this.memberCode = memberCode;
		this.memberType = memberType;
		this.personCode = personCode;
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

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
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
