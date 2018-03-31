package linked;

import entities.Invoice;

public class InvoiceList {
	
	private InvoiceListNode start;
	private InvoiceListNode end;
	private int size = 0;
	
	public int getSize() {
		return size;
	}
	
	public void clear() {
		start = null;
		end = null;
		size = 0;
		
	}
	
	public void addToStart(Invoice i) {
		
	}
	
	public void addToEnd(Invoice i) {
		
	}
	
	public void remove(int position) {
		
	}
	
	private <T> InvoiceListNode getInvoiceListNode(int position) {
	
		return null;
	}
	
	public Invoice getInvoice(int position) {
		
		return null;
	}
	
	public void print() {
		
	}
 
}
