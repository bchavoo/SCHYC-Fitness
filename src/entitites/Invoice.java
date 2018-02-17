package entitites;

import java.util.ArrayList;
import java.util.List;

import product.InvoiceProducts;

public class Invoice {
	
	private String invoiceCode;
	private Member memberCode;
	private String personalTrainerCode;
	private Integer invoiceDate;
	private List<InvoiceProducts> invoiceProducts;
	
	
	public Invoice(String invoiceCode, Member memberCode, String personalTrainerCode, Integer invoiceDate,
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
	public Member getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(Member memberCode) {
		this.memberCode = memberCode;
	}
	public String getPersonalTrainerCode() {
		return personalTrainerCode;
	}
	public void setPersonalTrainerCode(String personalTrainerCode) {
		this.personalTrainerCode = personalTrainerCode;
	}
	public Integer getInvoiceDate() {
		return invoiceDate;
	}
	public void setInvoiceDate(Integer invoiceDate) {
		this.invoiceDate = invoiceDate;
	}
	public List<InvoiceProducts> getProductsList() {
		return invoiceProducts;
	}
	public void setProductsList(List<InvoiceProducts> invoiceProducts) {
		this.invoiceProducts = invoiceProducts;
	}
	
	

}
