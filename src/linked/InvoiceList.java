package linked;

import java.util.Comparator;
import java.util.Iterator;

import entities.Invoice;

public class InvoiceList<Invoice> implements Iterable<Invoice> {

	private InvoiceNode<Invoice> start;
	private InvoiceNode<Invoice> end;
	private int size = 0;
	private Comparator<Invoice> comp;

	public InvoiceList(Comparator<Invoice> comp) {
		this.start = null;
		this.size = 0;
		this.comp = comp;
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


			if(this.comp.compare(){
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


			if(){
				start.setNext(newInvoiceNode);
				start = newInvoiceNode;
				size++;
			}


			else if (){
				for(int i =0; i < size; i++){
					start = start.getNext();
				}

				InvoiceNode<Invoice> neighbor1;

				start.setNext(newInvoiceNode);

				neighbor1 = start.getNext();

				newInvoiceNode.setNext(neighbor1);


				size++;
			}

			else {

				for(int i = 0; i > size; i++){
					start = start.getNext();
				}

				InvoiceNode<Invoice> currentNode;

				currentNode = start.getNext();

				currentNode.setNext(newInvoiceNode);



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


