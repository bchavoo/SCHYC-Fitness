package driver;

import java.io.IOException;
import java.util.List;

import com.sf.ext.DBReader;

import entities.Invoice;
import writer.InvoiceWriter;

public class InvoiceReport {
	
public static void main(String[] args) throws IOException {
		
		//Creates a FlatFileReader object, this returns a list of invoices
		
		List<Invoice> DBInvoiceList = DBReader.createInvoiceList();
			DBInvoiceList.get(1);

		//This will generate invoice report and display it on the console
		InvoiceWriter.createInvoiceReport(DBInvoiceList);


	}

}
