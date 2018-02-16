package member;

import entitites.Address;
import entitites.Person;

public class Member {

	private String memberCode;
	private String memberType;
	private Person contact;
	private String name;
	private Address address;


	//Constructor
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


}