package linked;

import java.util.Comparator;

import entities.Invoice;

public class TotalComparator implements Comparator<Invoice> {

	@Override
	public int compare(Invoice inv1, Invoice inv2) {
		//We check with conditionals to compare the new invoice with the others in the list
		if(inv1.getTotal() == inv2.getTotal()) {
			return 0;
		} else if (inv1.getTotal() > inv2.getTotal()) {
			return 1;
		} else {
			return -1;
		}
	}

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
