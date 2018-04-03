package linked;

import java.util.Comparator;
import java.util.Iterator;

import entities.Invoice;

public class InvoiceList implements Iterable<Invoice> {
	
	private InvoiceNode<Invoice> start;
	private InvoiceNode<Invoice> end;
	private int size = 0;
	private Comparator<Invoice> comp;
	
	public InvoiceList(Comparator<Invoice> comp) {
		this.start = null;
		this.size = 0;
		this.comp = comp;
	}
	
	public InvoiceNode<Invoice> getStart() {
		return start;
	}

	public void setStart(InvoiceNode<Invoice> start) {
		this.start = start;
	}

	public InvoiceNode<Invoice> getEnd() {
		return end;
	}

	public void setEnd(InvoiceNode<Invoice> end) {
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
	
	public Comparator<Invoice> getCompe(){
		return comp;
	}
	
	
	public void add(Invoice item){
		InvoiceNode<Invoice> newInvoiceNode = new InvoiceNode<Invoice>(item);
		
		if(start == null){
			start = newInvoiceNode;
		}
		
		else if(size == 1){
			
			
			if(){
				
				size++;
			}
			
			else{
				
				size++;
			}
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	@Override
	public Iterator<Invoice> iterator() {
		return new IteratorInvoice();
	}
	
	class IteratorInvoice implements Iterator<Invoice> {
		int index = 0;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Invoice next() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public void remove() {
			
		}
		
	}
 
}

	
	
	
	
	
	
	
	
//	public void addToStart(Invoice i) {
//		// Add the to the start of the linked list that will be created
//		InvoiceNode<Invoice> node = new InvoiceNode<Invoice>(i);
//		
//		size++;
//		
//		if(start == null){
//			start = node;
//			end = start;
//		}
//		else{
//			start.setNext(node);
//			node = start;
//		}
//		
//		
//	}
//	
//	
//	public void addToEnd(Invoice i) {
//		InvoiceNode<Invoice> node = new InvoiceNode<Invoice>(i);
//		
//		
//		if(start == null){
//			start = node;
//			end = start;
//		}
//		else{
//			start.setNext(node);
//			node = start;
//		}
//		
//		
//	}
//	
//	
//	public void remove(int position) {
//		// remove from the list at a specefic position
//		
//		if(position == 0){
//			start = start.getNext();
//			return;
//		}
//		
//		for(int i = 0; i < position-1; i++){
//			
//			start = start.getNext();
//			
//		}
//		
//		InvoiceNode<Invoice> removedNode = start.getNext();
//		
//		InvoiceNode<Invoice> nextNode = removedNode.getNext();
//		
//		start.setNext(nextNode);
//		
//		end = nextNode;
//		size --;
//		
//		
//		
//		
//		
//	}
//	
//	private InvoiceNode<Invoice> getInvoiceListNode(int position) {
//	
//		InvoiceNode<Invoice> s =  start;
//		
//		
//		if(position<0 || position>size){
//    		throw new IndexOutOfBoundsException();
//		}
//		
//		for(int i = 0; i< position; i++){
//			
//			if(s.getNext() == null){
//    		throw new IndexOutOfBoundsException();
//			}
//
//			s = s.getNext();
//		}
//		
//		return s;
//	}
//	
//	public Invoice getInvoice(int position) {
//		
//		return getInvoiceListNode(position).getInvoice();
//	}
//	
//	public void print() {
//		
//		while(start != null){
//			
//			start = start.getNext();
//		}
//		
//	}	