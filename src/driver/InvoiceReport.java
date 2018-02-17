package driver;

import java.io.IOException;
import java.util.List;

import entitites.Invoice;
import reader.FileReader;
import writer.InvoiceWriter;

public class InvoiceReport {

	public static void main(String[] args) throws IOException {
		
		//Creates a FlatFileReader object, this returns a list of invoices
		List<Invoice> invoiceList = FileReader.createInvoiceList();

		//This will generate invoice report
		InvoiceWriter.createInvoiceReport(invoiceList);
		
		System.out.println("These are all the INVOICES: " + invoiceList);
		
		
		
		
		
		
		//Create JSON Writer Object
		

		//Create XML Writer Object

	}

}
