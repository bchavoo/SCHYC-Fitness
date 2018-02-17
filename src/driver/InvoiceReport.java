package driver;

import java.io.IOException;
import java.util.List;

import entitites.Invoice;
import reader.FileReader;
import writer.JSONWriter;

public class InvoiceReport {

	public static void main(String[] args) throws IOException {
		
		List<Invoice> invoiceList = FileReader.createInvoiceList();

		JSONWriter.createInvoiceList(invoiceList);
		
		System.out.println("These are all the INVOICES: " + invoiceList);
		
		
		
		
		
		
		//Create JSON Writer Object
		

		//Create XML Writer Object

	}

}
