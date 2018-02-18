package product;

import entitites.Address;

public class YearMemberships extends Membership {

	public String endDate;
	public String membershipName;
	public double cost;

	//Constructor
	public YearMemberships(String startDate, String endDate, Address address, String membershipName, double cost, String productCode, String productType) {
		super(productCode, startDate, address, productType);
		this.endDate = endDate;
		this.membershipName = membershipName;
		this.cost = cost;
	}

	//Getters and Setters
	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
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
	public double getTax() {
		// TODO Auto-generated method stub
		double totalCost = 0;
		
		return 0.06 * totalCost;
	}

	@Override
	public double getTotal() {
		// TODO Auto-generated method stub
		double cost = 450;
		 
		return cost;
	}
		



}