package linked;

import entities.Invoice;

public class InvoiceList<T> {
	
	private InvoiceListNode<T> start;
	private InvoiceListNode<T> end;
	private int size = 0;
	
	public InvoiceListNode<T> getStart() {
		return start;
	}

	public void setStart(InvoiceListNode<T> start) {
		this.start = start;
	}

	public InvoiceListNode<T> getEnd() {
		return end;
	}

	public void setEnd(InvoiceListNode<T> end) {
		this.end = end;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}
	
	public void clear() {
		start = null;
		end = null;
		size = 0;
		
	}
	
	public void addToStart(Invoice i) {
		// Add the to the start of the linked list that will be created
		InvoiceListNode<T> node = new InvoiceListNode<T>(i);
		
		size++;
		
		if(start == null){
			start = node;
			end = start;
		}
		else{
			start.setNext(node);
			node = start;
		}
		
		
	}
	
	
	public void addToEnd(Invoice i) {
		InvoiceListNode<T> node = new InvoiceListNode<T>(i);
		
		
		if(start == null){
			start = node;
			end = start;
		}
		else{
			start.setNext(node);
			node = start;
		}
		
		
	}
	
	
	public void remove(int position) {
		// remove from the list at a specefic position
		
		if(position == 0){
			start = start.getNext();
			return;
		}
		
		for(int i = 0; i < position-1; i++){
			
			start = start.getNext();
			
		}
		
		InvoiceListNode<T> removedNode = start.getNext();
		
		InvoiceListNode<T> nextNode = removedNode.getNext();
		
		start.setNext(nextNode);
		
		end = nextNode;
		size --;
		
		
		
		
		
	}
	
	private InvoiceListNode getInvoiceListNode(int position) {
	
		InvoiceListNode<T> s =  start;
		
		
		if(position<0 || position>size){
    		throw new IndexOutOfBoundsException();
		}
		
		for(int i = 0; i< position; i++){
			
			if(s.getNext() == null){
    		throw new IndexOutOfBoundsException();
			}

			s = s.getNext();
		}
		
		return s;
	}
	
	public Invoice getInvoice(int position) {
		
		return getInvoiceListNode(position).getInvoice();
	}
	
	public void print() {
		
		while(start != null){
			
			start = start.getNext();
		}
		
	}
 
}
