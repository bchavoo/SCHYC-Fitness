package driver;

import java.io.IOException;
import java.util.List;

import com.sf.ext.DBReader;

import entities.Invoice;
import linked.InvoiceList;
import linked.TotalComparator;
import writer.InvoiceWriter;

public class InvoiceReport {

	public static void main(String[] args) throws IOException {

		//Calls a method to get a list of invoices
		List<Invoice> DBInvoiceList = DBReader.createInvoiceList();		

		System.out.println("Unordered List: ");
		for(int i = 0; i < DBInvoiceList.size(); i++) {
			System.out.println("Invoice Code: " + DBInvoiceList.get(i).getInvoiceCode() + " Total: " + DBInvoiceList.get(i).getTotal());
		}
		
		System.out.println("\n");


		InvoiceList<Invoice> invoiceOrderedList = new InvoiceList<Invoice>(new TotalComparator());
		
		for(int i = 0; i < DBInvoiceList.size(); i++) {
			invoiceOrderedList.add(DBInvoiceList.get(i));
			System.out.println("Adding invoice... " + DBInvoiceList.get(i).getInvoiceCode());
		}
		
		System.out.println("\nOrdered List: ");
		System.out.println("Invoice Code: " + invoiceOrderedList.getStart().getInvoice().getInvoiceCode() + " Total: " + invoiceOrderedList.getStart().getInvoice().getTotal());
		System.out.println("Invoice Code: " + invoiceOrderedList.getStart().getNext().getInvoice().getInvoiceCode() + " Total: " + invoiceOrderedList.getStart().getNext().getInvoice().getTotal());
		System.out.println("Invoice Code: " + invoiceOrderedList.getStart().getNext().getNext().getInvoice().getInvoiceCode() + " Total: " + invoiceOrderedList.getStart().getNext().getNext().getInvoice().getTotal());
		System.out.println("Invoice Code: " + invoiceOrderedList.getStart().getNext().getNext().getNext().getInvoice().getInvoiceCode() + " Total: " + invoiceOrderedList.getStart().getNext().getNext().getNext().getInvoice().getTotal());
		System.out.println("Invoice Code: " + invoiceOrderedList.getStart().getNext().getNext().getNext().getNext().getInvoice().getInvoiceCode() + " Total: " + invoiceOrderedList.getStart().getNext().getNext().getNext().getNext().getInvoice().getTotal() + "\n\n");



		//This will generate invoice report, with a given list of invoices and display it on the console
		InvoiceWriter.createInvoiceReport(invoiceOrderedList);
		
		


	}

}
