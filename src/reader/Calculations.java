package reader;

import java.util.ArrayList;
import java.util.List;


public class Calculations {

	/**
	 * The calculation class is just an object with 'total' attributes
	 * that we use in the calculations algorithm to 
	 * compute the invoice and its subTotals and other numbers
	 * We also store each calculation in an array list to be used later in Executive Report
	 * @param subTotal
	 * @param taxes
	 * @param discount
	 * @param finalTotal
	 * @param studentFees;
	 * @param calcList
	 */

	private double subTotal;
	private double taxes;
	private double discount;
	private double finalTotal;
	private double studentFees;
	public static List<Calculations> calcList = new ArrayList<Calculations>();


	//This is the constructor for calculations
	public Calculations(double subTotal, double studentFees, double taxes, double discount, double finalTotal) {
		super();
		this.subTotal = subTotal;
		this.studentFees = studentFees;
		this.taxes = taxes;
		this.discount = discount;
		this.finalTotal = finalTotal;
	}

	//Getters and Setters
	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

	public double getStudentFees() {
		return studentFees;
	}

	public void setStudentFees(double studentFees) {
		this.studentFees = studentFees;
	}

	public double getTaxes() {
		return taxes;
	}

	public void setTaxes(double taxes) {
		this.taxes = taxes;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getFinalTotal() {
		return finalTotal;
	}

	public void setFinalTotal(double finalTotal) {
		this.finalTotal = finalTotal;
	}


}
