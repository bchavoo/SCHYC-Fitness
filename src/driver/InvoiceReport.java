package driver;

import java.io.IOException;
import java.util.List;

import com.sf.ext.DBReader;

import entities.Invoice;
import reader.FileReader;
import writer.InvoiceWriter;

public class InvoiceReport {
	
public static void main(String[] args) throws IOException {
		
		//Creates a FlatFileReader object, this returns a list of invoices
		
		List<Invoice> DBinvoiceList = DBReader.createInvoiceList();


		//This will generate invoice report and display it on the console
		DBReader.createInvoiceReport(DBinvoiceList);


	}

}
