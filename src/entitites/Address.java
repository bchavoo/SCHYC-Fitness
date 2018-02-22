package entitites;

public class Address {


    /**
     * Encapsulation is shown here, the strings are all private which
     * can't be accessed by the user
     */
	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;

	/**
	 * Constructors are created for the class as well as their setters and getters
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public Address(String street, String city, String state, String zip, String country) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;
	}

	//Getters and Setters
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}







}
