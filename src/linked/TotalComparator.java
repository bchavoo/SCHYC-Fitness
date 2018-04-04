package linked;

import java.util.Comparator;

import entities.Invoice;

public class TotalComparator implements Comparator<Invoice> {

	@Override
	public int compare(Invoice inv1, Invoice inv2) {
		// TODO Auto-generated method stub
		double id1 = inv1.getTotal();
		double id2 = inv2.getTotal();
		
		if(id1 == id2){
			return 0;
		}else if(id1>id2){
			return 1;
		}
		else
		return -1;
	}

}
