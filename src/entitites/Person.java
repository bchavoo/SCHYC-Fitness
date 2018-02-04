package entitites;

import java.util.ArrayList;

public class Person {
	private String firstName;
	private String lastName;
	private ArrayList<String>email;
	
	//Constructor
	public Person (String firstName, String lastName, ArrayList<String> email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	//Getters and Setters
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

	public ArrayList<String> getEmail() {
		return email;
	}

	public void setEmail(ArrayList<String> email) {
		this.email = email;
	}

	
	
}
	
	
	