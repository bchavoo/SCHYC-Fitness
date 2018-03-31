package linked;

import entities.Invoice;

public class InvoiceListNode<T> {
	
	private InvoiceListNode<T> next;
	private Invoice item;
	
	public InvoiceListNode(Invoice item) {
		this.item = item;
		this.next = null;
	}
	
	public Invoice getInvoice() {
		return item;
	}
	
	public InvoiceListNode<T> getNext() {
		return next;
	}
	
	public void setNext(InvoiceListNode<T> next) {
		this.next = next;
	}
	
	
		
	
	

}
