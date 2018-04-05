package linked;

import entities.Invoice;

public class InvoiceNode<Invoice> {

	private InvoiceNode<Invoice> next;
	private Invoice item;

	public InvoiceNode(Invoice item) {
		this.item = item;
		this.next = null;
	}

	public Invoice getInvoice() {
		return item;
	}

	public InvoiceNode<Invoice> getNext() {
		return next;
	}

	public void setNext(InvoiceNode<Invoice> next) {
		this.next = next;
	}

}
