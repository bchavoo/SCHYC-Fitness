package linked;

import java.util.Comparator;

import entities.Invoice;

public class TotalComparator<T> implements Comparator<T> {
	
	//Compare the new invoice with the another invoice in the list using conditionals
	@Override
	public int compare(T inv1, T inv2) {
		if(((Invoice) inv1).getTotal() == ((Invoice) inv2).getTotal()) {
			return 0;
		} else if (((Invoice) inv1).getTotal() > ((Invoice) inv2).getTotal()) {
			return 1;
		} else {
			return -1;
		}
	}

	//Override the equals and hashcode method
	@Override
	public boolean equals(Object inv) {
		// TODO Auto-generated method stub
		return super.equals(inv);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
