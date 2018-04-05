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
		
		InvoiceList invoiceOrderedList = new InvoiceList(new TotalComparator());
		
		//This will generate invoice report, with a given list of invoices and display it on the console
		InvoiceWriter.createInvoiceReport((List<Invoice>) invoiceOrderedList);


	}

}
