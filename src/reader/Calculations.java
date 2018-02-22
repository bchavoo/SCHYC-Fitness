package reader;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import entitites.Address;
import entitites.InvoiceProducts;
import entitites.Person;
import product.DayMemberships;
import product.Equipment;
import product.ParkingPass;
import product.Product;
import product.YearMemberships;

public class Calculations {
	
	private double subTotal;
	private double taxes;
	private double discount;
	private double finalTotal;
	private double studentFees;
	public static List<Calculations> calcList = new ArrayList<Calculations>();


	public Calculations(double subTotal, double studentFees, double taxes, double discount, double finalTotal) {
		super();
		this.subTotal = subTotal;
		this.studentFees = studentFees;
		this.taxes = taxes;
		this.discount = discount;
		this.finalTotal = finalTotal;
	}
	
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
