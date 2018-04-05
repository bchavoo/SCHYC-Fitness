package linked;

import java.util.Comparator;
import java.util.Iterator;

import entities.Invoice;

public class InvoiceList<Invoice> implements Iterable<Invoice> {

	private InvoiceNode<Invoice> start;
	private InvoiceNode<Invoice> end;
	private int size = 0;
	private Comparator<Invoice> comp;
//	private IteratorInvoice iterator;
	
	public InvoiceList(Comparator<Invoice> comp) {
		this.start = null;
		this.size = 0;
		this.comp = comp;
		//this.iterator = new IteratorInvoice();
	}

	public Comparator<Invoice> getComp(){
		return comp;
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


	public void add(Invoice item){
		InvoiceNode<Invoice> newInvoiceNode = new InvoiceNode<Invoice>(item);
		
		if(start == null){
			start = newInvoiceNode;
		}

		else if(size == 1){


			if(this.comp.compare(newInvoiceNode.getInvoice(),start.getInvoice()) == 1){
				start.setNext(newInvoiceNode);
				start = newInvoiceNode;

				size++;
			}

			else{
				end.setNext(newInvoiceNode);
				end = newInvoiceNode;

				size++;
			}

		}

		else{


			if(this.comp.compare(newInvoiceNode.getInvoice(),start.getInvoice()) == 1){
				start.setNext(newInvoiceNode);
				start = newInvoiceNode;
				size++;
			}else{


				boolean inList = false;
				for(int i =0; i < size; i++){
					start = start.getNext();
//					if (newNode belongs before current node && not added){
//						inList = true;
//						//add node
//					}
//				
					if(this.comp.compare(newInvoiceNode.getInvoice(),start.getInvoice()) == -1 && !inList){
						inList = true;
					}
				}
				
				if(!inList){
				//if not added
				//add as new end
				
				//start = old end
				//InvoiceNode<Invoice> neighbor1;
				//new end = newnode
				start.setNext(newInvoiceNode);
				//neighbor1 =  newnode
				//neighbor1 = start.getNext();
				end = newInvoiceNode;
				//newnode.next = newnode (infinite loop)
				//newInvoiceNode.setNext(neighbor1);
/*
				c
				
				a b d e f
				
				
				
				
*/				
				size++;
				}
			
			}
//			else {
//
//				for(int i = 0; i > size; i++){
//					start = start.getNext();
//				}
//
//				InvoiceNode<Invoice> currentNode;
//
//				currentNode = start.getNext();
//
//				currentNode.setNext(newInvoiceNode);
//
//
//
//				size++;
//			}
		}
	}


	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comp == null) ? 0 : comp.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + size;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvoiceList other = (InvoiceList) obj;
		if (comp == null) {
			if (other.comp != null)
				return false;
		} else if (!comp.equals(other.comp))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (size != other.size)
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		return true;
	}

	@Override
	public Iterator<Invoice> iterator() {
		return new IteratorInvoice(this.start);
	}
	/*
	 * a b c d e f
	 * 
	 * for (Invoice i: InvoiceList){
	 * 	//counter, condition, incrementer
	 * 	while (InvoiceList.hasNext()){
	 * 	
	 * 
	 * i = InvoiceList.next();
	 * 		}
	 * }
	 */
	public class IteratorInvoice implements Iterator<Invoice> {
		int index = 0;
		InvoiceNode<Invoice> current;
		public IteratorInvoice(InvoiceNode<Invoice> start){
			this.current = start;
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			//check if node at [position] list.get(i).getNext == null return false, else return true;
			if (current.getNext()==null){
				return false;
			}else{
				return true;
			}
		}

		@Override
		public Invoice next() {
			// TODO Auto-generated method stub
			if (hasNext()){
				current = current.getNext();
				index++;
				return current.getInvoice();
			}else{
				return null;
			}
			
		}


	}

}


