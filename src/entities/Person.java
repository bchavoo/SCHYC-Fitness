package entities;

import java.util.ArrayList;

	/**
	 * Encapsulation is shown by making the strings address and array list of string to private
	 * Here we use type string for the person info but we also have to use type address so it would access the address attribute
	 */

public class Person {
	private String personCode;
	private String firstName;
	private String lastName;
	private Address address;
	private ArrayList<String>email;

	/**
	 * Here created a person class with its constructor
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param address
	 * @param email
	 */
	public Person(String personCode, String firstName, String lastName, Address address, ArrayList<String> email) {
		super();
		this.personCode = personCode;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
	}

	//Getters and Setters
	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public ArrayList<String> getEmail() {
		return email;
	}

	public void setEmail(ArrayList<String> email) {
		this.email = email;
	}


}
