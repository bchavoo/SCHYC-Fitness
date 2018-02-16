package entitites;

public class Invoice {
	
	private String invoiceCode;
	private Member memberCode;
	private String personalTrainerCode;
	private Integer invoiceDate;
	private String productsList;
	
	
	public Invoice(String invoiceCode, Member memberCode, String personalTrainerCode, Integer invoiceDate,
			String productsList) {
		super();
		this.invoiceCode = invoiceCode;
		this.memberCode = memberCode;
		this.personalTrainerCode = personalTrainerCode;
		this.invoiceDate = invoiceDate;
		this.productsList = productsList;
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
	public String getProductsList() {
		return productsList;
	}
	public void setProductsList(String productsList) {
		this.productsList = productsList;
	}
	
	

}
