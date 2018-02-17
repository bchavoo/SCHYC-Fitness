package driver;

import java.io.IOException;
import java.util.List;

import entitites.Invoice;
import reader.FileReader;
import writer.JSONWriter;

public class InvoiceReport {

	public static void main(String[] args) throws IOException {
		
		List<Invoice> invoiceList = FileReader.createInvoiceList();

		System.out.println(invoiceList.get(2).getInvoiceDate());
		//2016-11-26
		System.out.println(invoiceList.get(2).getProductsList().get(0).getProductCode());
		//782g
		//JSONWriter.createInvoiceList(invoiceList);
		
		//System.out.println("These are all the INVOICES: " + invoiceList);
		
		
		
		
		
		
		//Create JSON Writer Object
		

		//Create XML Writer Object

	}

}
