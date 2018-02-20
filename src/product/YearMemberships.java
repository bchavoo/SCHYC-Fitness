package product;

import org.joda.time.DateTime;

import entitites.Address;

public class YearMemberships extends Membership {

	public DateTime endDate;
	public String membershipName;
	public double cost;

	//Constructor
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