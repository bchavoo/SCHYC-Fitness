package driver;

import java.io.IOException;
import java.util.List;

import entitites.Invoice;
import reader.FileReader;
import writer.InvoiceWriter;
import writer.InvoiceWriterCALC;
import writer.JSONWriter;
public class InvoiceReport {

	public static void main(String[] args) throws IOException {
		
		//Creates a FlatFileReader object, this returns a list of invoices
		List<Invoice> invoiceList = FileReader.createInvoiceList();

		//This will generate invoice report
		InvoiceWriter.createInvoiceReport(invoiceList);
		
		JSONWriter.createInvoiceList(invoiceList);
		
		
		
		
		//Create JSON Writer Object
		

		//Create XML Writer Object

	}

}
