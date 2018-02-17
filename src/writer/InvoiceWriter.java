package writer;

//import java.io.IOException;
import java.util.List;

import entitites.Address;
import entitites.Invoice;
import entitites.InvoiceProducts;

public class InvoiceWriter {
	
    
	public static void createInvoiceReport(List<Invoice> invoiceList)  {
		//Do all calculations and formatting here
		
		
		
		
		
		InvoiceWriter.createExcutiveReport(null, null, null, null);
		InvoiceWriter.createSingleInvoiceReport(null, null, null, null, null, null, null, null, null, null);
		
	}
	
	
	
	public static void createExcutiveReport(String invoiceNumber, String memberName, String memberType, String trainerName) {
		
		System.out.println("Executive Summary Report");
		System.out.println("===========================");
		System.out.println("Invoice            Member                           Personal Trainer               Subtotal           Fees          Taxes           Discount        Total");
		System.out.println(invoiceNumber + "             " + memberName + " [" + memberType + "]                          "  +  trainerName );
	}
	
	
	public static void createSingleInvoiceReport(String invoiceNumber, String trainerLastName, String trainerFirstName, String memberName, String memberCode, String memberType, String personLastName, String personFirstName, Address memberAddress, List<InvoiceProducts> productList) {
		System.out.println("Invoice  " + invoiceNumber);
		System.out.println("==================================================");
		System.out.println("Personal Trainer: " + trainerLastName + ", " + trainerFirstName);
		System.out.println("Member Info:");
		System.out.println("    " + memberName + "  (" + memberCode + ")");
		System.out.println("    [" + memberType + "]");
		System.out.println("    " + personLastName + ", " + personFirstName);
		System.out.println("    " + memberAddress.getStreet());
		System.out.println("    " + memberAddress.getCity() + " " + memberAddress.getState() + " " + memberAddress.getZip() + " "  + memberAddress.getCountry());
		System.out.println("------------------------------------------");
		System.out.println("Code             Item                                         SubTotal                 Tax            Total");
		System.out.println("");
		
		
	}
	
}
