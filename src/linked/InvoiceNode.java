package linked;

import entities.Invoice;

public class InvoiceNode<T> {

	//Invoice node used in linked list
	private InvoiceNode<T> next;
	private Invoice item;

	public InvoiceNode(Invoice item) {
		this.item = item;
		this.next = null;
	}

	//Method to get item in the node
	public Invoice getInvoice() {
		return item;
	}

	//Method to get node's neighbor
	public InvoiceNode<T> getNext() {
		return (InvoiceNode<T>) next;
	}

	//Method to set node's neighbor
	public void setNext(InvoiceNode<T> next) {
		this.next = (InvoiceNode<T>) next;
	}

}
