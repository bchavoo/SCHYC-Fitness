package linked;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import entities.Invoice;

public class InvoiceList<Invoice> implements Iterable<Invoice> {

	private InvoiceNode<Invoice> start;
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

	//Setters and getter for the initalized variables
	public InvoiceNode<Invoice> getStart() {
		return start;
	}

	public void setStart(InvoiceNode<Invoice> start) {
		this.start = start;
	}




	public void setSize(int size) {
		this.size = size;
	}

	public int getSize() {
		return size;
	}

	public void clear() {
		start = null;

		size = 0;

	}


	public void add(Invoice item){
		InvoiceNode<Invoice> newInvoiceNode = new InvoiceNode<Invoice>(item);

		if(start == null){
			start = newInvoiceNode;
			size++;
		}

		else if(size == 1){

			/**
			 * New start if the new node is bigger than the start, the new node will be placed in front
			 * and the new node point to the old start and then will become the new start
			 */
			if(this.comp.compare(newInvoiceNode.getInvoice(),start.getInvoice()) == 1){
				newInvoiceNode.setNext(start);
				start = newInvoiceNode;
				size++;

			} else {
				// the start remains at the start and the new node is the neighbor of it
				start.setNext(newInvoiceNode);
				size++;
			}

		}


		else {

			if(this.comp.compare(newInvoiceNode.getInvoice(), start.getInvoice()) == 1){
				newInvoiceNode.setNext(start);
				start = newInvoiceNode;
				size++;

			} else if (this.comp.compare(newInvoiceNode.getInvoice(), start.getNext().getInvoice()) == 1) {
				newInvoiceNode.setNext(start.getNext());
				start.setNext(newInvoiceNode);
				size++;


			} else {
				//Start is the now the current node with more than two nodes in the list
				InvoiceNode<Invoice> current = start;

				//Boolean to check if nodes are in the list 
				boolean inList = false;

				for(int i = 0; i < size; i++){


					//If the current nodes neigbors are not null
					if (current.getNext() != null){

						//Compare the new node with the current node
						if(this.comp.compare(newInvoiceNode.getInvoice(), current.getInvoice()) == -1 && this.comp.compare(newInvoiceNode.getInvoice(), current.getNext().getInvoice()) == 1 && inList == false){
							inList = true;
							newInvoiceNode.setNext(current.getNext());
							current.setNext(newInvoiceNode);
							break;
						}

						//At the end of the loop, set the current to the next node to search through list
						current = current.getNext();

					}

				}

				if(inList == false){
					//If invoice is not added
					//Add to the end

					current.setNext(newInvoiceNode);
				}
			}
		}
	}




	//Here we override the hashcode and equals methods so that it would give us the right output

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comp == null) ? 0 : comp.hashCode());
		result = prime * result + size;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} if (obj == null) {
			return false;
		} if (getClass() != obj.getClass()) {
			return false;
		}

		InvoiceList other = (InvoiceList) obj;

		if (comp == null) {
			if (other.comp != null) {
				return false;
			}
		} else if (!comp.equals(other.comp)) {
			return false;
		}


		if (size != other.size) {
			return false;
		} if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start)) {
			return false;
		}
		return true;
	}

	@Override
	public Iterator<Invoice> iterator() {
		return new IteratorInvoice();
	}

	class IteratorInvoice implements Iterator<Invoice> {
		int index = 0;
		InvoiceNode<Invoice> current;
		public IteratorInvoice(){
			this.current = InvoiceList.this.start;
		}

		@Override
		public boolean hasNext() {
			//Check if node at [position] list.get(i).getNext == null return false, else return true;


			if (current == null){
				return false;
			} else {
				return true;
			}
		}

		@Override
		public Invoice next() {
			//Stores the current node in a new result node which is returned when getting the invoice info
			if (hasNext()) {
				InvoiceNode<Invoice> result = current;
				//Current is now its own neighbor
				current = current.getNext();
				index++;
				return result.getInvoice();
			} else {
				return null;
			}

		}


	}

}