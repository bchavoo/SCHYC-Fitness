package entitites;

import java.util.ArrayList;

public class Invoice {
	
	private String invoiceCode;
	private String memberCode;
	private String personalTrainerCode;
	private String invoiceDate;
	private ArrayList<InvoiceProducts> invoiceProducts;
	
	
	public Invoice(String invoiceCode, String memberCode, String personalTrainerCode, String invoiceDate,
			ArrayList<InvoiceProducts> invoiceProducts) {
		super();
		this.invoiceCode = invoiceCode;
		this.memberCode = memberCode;
		this.personalTrainerCode = personalTrainerCode;
		this.invoiceDate = invoiceDate;
		this.invoiceProducts = invoiceProducts;
	}
	public String getInvoiceCode() {
		return invoiceCode;
	}
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getPersonalTrainerCode() {
		return personalTrainerCode;
	}
	public void setPersonalTrainerCode(String personalTrainerCode) {
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
