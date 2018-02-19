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
	public double getSubTotal(int quantity) {

		return this.cost * 0.06;
	}

	@Override
	public double getTax() {

		return this.cost * 0.06;
	}

	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
//		if(getStartDate().subSequence(5,6) == ""){
//
//		}
		return this.cost * 0.06 + this.cost;
	}






}