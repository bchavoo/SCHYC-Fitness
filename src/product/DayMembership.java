package product;

import org.joda.time.DateTime;

import entities.Address;

public class DayMembership extends Membership {

	private double cost;

	/**
	 * The DayMembership is a subclass of the membership. The super helps us see that the 
	 * day membership INHERITS attributes of the Membership class and overrides them. 
	 * @param startDate
	 * @param address
	 * @param cost
	 * @param productCode
	 * @param productType
	 */
	 
	public DayMembership(DateTime startDate, Address address, double cost, String productCode, String productType) {
		super(productCode, startDate, address, productType);
		//This is an instance where we override the super class Membership and added the attribute cost
		this.cost = cost;
	}

	//Getters and Setters
	public double getCost() {
		return cost;
	}


	public void setCost(double cost) {
		this.cost = cost;
	}


     /**
	 * The abstraction helped override the super class so now we can use each of the 
	 * these methods to help us calculate the totals we need
	 */

	@Override
	public double getDiscount() {

		return 0.50;
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