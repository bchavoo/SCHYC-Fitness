package linked;

import java.util.Comparator;
import java.util.Iterator;
import entities.Invoice;

public class InvoiceList<T> implements Iterable<T> {

	private InvoiceNode<T> start;
	private int size = 0;
	private Comparator<T> comp;
	//The comparator will be used to compare two Invoice objects before adding to the list

	public InvoiceList(Comparator<T> comp) {
		this.start = null;
		this.size = 0;
		this.comp = comp;
	}

	public Comparator<T> getComp(){
		return comp;
	}

	//Setters and getter for the initalized variables
	public InvoiceNode<T> getStart() {
		return start;
	}

	public void setStart(InvoiceNode<T> start) {
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
		InvoiceNode<T> newInvoiceNode = new InvoiceNode<T>(item);

		/**
		 * If the list is empty, the new node will become the start
		 */
		if (start == null) {
			start = (InvoiceNode<T>) newInvoiceNode;
			size++;
		}

		/**
		 * If the list has only one node, compare the new node with the start node
		 * and add to the appropriate position in the list
		 */
		else if (size == 1) {

			/**
			 * If the new node is bigger than the start, the new node will be placed in front
			 * and the new node will point to the old start and then will become the new start
			 */
			if (this.comp.compare((T) newInvoiceNode.getInvoice(),(T) start.getInvoice()) == 1) {
				newInvoiceNode.setNext((InvoiceNode<T>) start);
				start = (InvoiceNode<T>) newInvoiceNode;
				size++;

			/**
			 * If the new node is not bigger, then it is smaller and we simply 
			 * tell the start to point to the new node
			 */
			} else {
				//The start remains at the start and the new node is the neighbor of it
				start.setNext((InvoiceNode<T>) newInvoiceNode);
				size++;
			}

		}


		/**
		 * If the start is not null, and it's size is not 1 then the list
		 * contains more than one element
		 */
		else {

			/**
			 * If the new Invoice is bigger than the start, we have the new Invoice
			 * point to the old start and set it as the new start
			 */
			if (this.comp.compare((T) newInvoiceNode.getInvoice(), (T) start.getInvoice()) == 1) {
				newInvoiceNode.setNext((InvoiceNode<T>) start);
				start = (InvoiceNode<T>) newInvoiceNode;
				size++;
				
			/**
			 * If the new Invoice is bigger than the neighbor of start, we have the new Invoice
			 * point to the neighbor of start and start will now point to the new Invoice
			 */	
			} else if (this.comp.compare((T) newInvoiceNode.getInvoice(), (T) start.getNext().getInvoice()) == 1) {
				newInvoiceNode.setNext((InvoiceNode<T>) start.getNext());
				start.setNext((InvoiceNode<T>) newInvoiceNode);
				size++;

			/**
			 * If the new Invoice is not bigger than the start or the neighbor of start
			 * then we have to crawl throught the list to find it's place
			 */
			} else {
				//Start is the now the current node with more than two nodes in the list
				//This is used to jump and check each position of the list
				InvoiceNode<T> current = start;

				//Boolean to check if nodes are in the list 
				boolean addList = false;

				//For loop to loop through the size of the list
				for(int i = 0; i < size; i++){
					
					//If the current nodes point are not null then we compare
					if (current.getNext() != null){

						//Compare the new node with the current node and see if it is smaller than the current node
						//And also check that it is bigger than the next node so we know it fits directly in place
						//If it satisfies these conditions then we will add into the list
						if (this.comp.compare((T) newInvoiceNode.getInvoice(), (T) current.getInvoice()) == -1 && this.comp.compare((T) newInvoiceNode.getInvoice(), (T) current.getNext().getInvoice()) == 1 && addList == false) {
							addList = true;
							//New node points to the current's pointer, and current points to the new node
							newInvoiceNode.setNext((InvoiceNode<T>) current.getNext());
							current.setNext((InvoiceNode<T>) newInvoiceNode);
							break;
						}

						//At the end of the loop, set the current to the next node to move and search through list
						current = current.getNext();

					}
				}

				//If nothing was ever added to the list
				if(addList == false) {
					//Add to the end
					current.setNext((InvoiceNode<T>) newInvoiceNode);
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
	public Iterator<T> iterator() {
		return (Iterator<T>) new IteratorInvoice();
	}

	class IteratorInvoice implements Iterator<T> {
		int index = 0;
		InvoiceNode<T> current;
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
		public T next() {
			//Stores the current node in a new result node which is returned when getting the invoice info
			if (hasNext()) {
				InvoiceNode<T> result = current;
				//Current is now its own neighbor
				current = current.getNext();
				index++;
				return (T) result.getInvoice();
			} else {
				return null;
			}
		}
	}
}