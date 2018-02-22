package product;

import org.joda.time.DateTime;

import entitites.Address;

public class DayMemberships extends Membership {

	private double cost;



	//Constructor
	/**
	 * The DayMembership is  subclass of the membership. The super helps us see that the 
	 * day membership INHERENTES attributes of the Membership class. 
	 * @param startDate
	 * @param address
	 * @param cost
	 * @param productCode
	 * @param productType
	 */
	 
	public DayMemberships(DateTime startDate, Address address, double cost, String productCode, String productType) {
		super(productCode, startDate, address, productType);
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
	 * these methods to help us calculate.
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