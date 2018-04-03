package entities;

import java.util.ArrayList;

public class Invoice {

	/**
	 * The invoice class takes in many attributes from the text file we create an invoice here
	 * which takes in an invoiceCode, memberCode, personalTrainerCode, date, and
	 * an array list of products (where we store all the products of each invoice
	 */
	private String invoiceCode;
	private Member memberCode;
	private Person personalTrainerCode;
	private String invoiceDate;
	private ArrayList<InvoiceProducts> invoiceProducts;

	//Here we create constructor for this class
	public Invoice(String invoiceCode, Member memberCode, Person personalTrainerCode, String invoiceDate, ArrayList<InvoiceProducts> invoiceProducts) {
		super();
		this.invoiceCode = invoiceCode;
		this.memberCode = memberCode;
		this.personalTrainerCode = personalTrainerCode;
		this.invoiceDate = invoiceDate;
		this.invoiceProducts = invoiceProducts;
	}

	//Getters and Setters
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public Member getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(Member memberCode) {
		this.memberCode = memberCode;
	}
	public Person getPersonalTrainerCode() {
		return personalTrainerCode;
	}
	public void setPersonalTrainerCode(Person personalTrainerCode) {
		this.personalTrainerCode = personalTrainerCode;
	}
	public String getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(String invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public ArrayList<InvoiceProducts> getProductsList() {
		return invoiceProducts;
	}
	public void setProductsList(ArrayList<InvoiceProducts> invoiceProducts) {
		this.invoiceProducts = invoiceProducts;
	}




}
