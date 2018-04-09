package linked;

import entities.Invoice;

public class InvoiceNode<T> {

	private InvoiceNode<T> next;
	private Invoice item;

	public InvoiceNode(Invoice item) {
		this.item = item;
		this.next = null;
	}

	public Invoice getInvoice() {
		return item;
	}

	public InvoiceNode<T> getNext() {
		return (InvoiceNode<T>) next;
	}

	public void setNext(InvoiceNode<T> next) {
		this.next = (InvoiceNode<T>) next;
	}

}
