package linked;

public class LinkedListNode<T> {
	
	private LinkedListNode<T> next;
	private Object item;
	
	public LinkedListNode(Object item) {
		this.item = item;
		this.next = null;
	}
	
	public Object getObject() {
		return item;
	}
	
	public LinkedListNode<T> getNext() {
		return next;
	}
	
	public void setNext(LinkedListNode<T> next) {
		this.next = next;
	}
	
	
		
	
	

}
