package product;

import org.joda.time.DateTime;

import entitites.Address;

public class YearMemberships extends Membership {


    /**
	 * The year membership is a child class of membership so it will inherit all the 
	 * attributes of the memberships class and its super class
	 */
    
    
	public DateTime endDate;
	public String membershipName;
	public double cost;

	//Constructor
	/**
	 * Here we create contructor as well as setter and getters
	 * @param startDate
	 * @param endDate
	 * @param address
	 * @param membershipName
	 * @param cost
	 * @param productCode
	 * @param productType
	 */
	public YearMemberships(DateTime startDate, DateTime endDate, Address address, String membershipName, double cost, String productCode, String productType) {
		super(productCode, startDate, address, productType);
		this.endDate = endDate;
		this.membershipName = membershipName;
		this.cost = cost;
	}

	//Getters and Setters
	public DateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(DateTime endDate) {
		this.endDate = endDate;
	}

	public String getMembershipName() {
		return membershipName;
	}

	public void setMembershipName(String membershipName) {
		this.membershipName = membershipName;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}
	
	/**
	 * Here the the super class is overridden and we used the abstraction method 
	 * to help us with returning the right calculations
	 */

	@Override
	public double getDiscount() {
		
		return 0.85;
	}

	@Override
	public double getSubTotal(double cost, double quantity) {
		
		return cost * quantity;
	}

	@Override
	public double getTax(double cost) {

		return 0.06 * cost;
	}

	@Override
	public double getTotal(double cost, double tax) {
		
		return cost + tax;
	}

	

		



}