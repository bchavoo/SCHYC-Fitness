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

		for(int i = 0; i < DBInvoiceList.size(); i++) {
			System.out.println("HERE: " + DBInvoiceList.get(i).getMemberCode());
		}
		
		//This will generate invoice report and display it on the console
		InvoiceWriter.createInvoiceReport(DBInvoiceList);


	}

}
