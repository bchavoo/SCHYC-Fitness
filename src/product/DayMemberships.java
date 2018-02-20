package product;

import org.joda.time.DateTime;

import entitites.Address;

public class DayMemberships extends Membership {

	private double cost;

	//Constructor
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