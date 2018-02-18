package writer;

//import java.io.IOException;
import java.util.List;

import entitites.Address;
import entitites.Invoice;
import entitites.InvoiceProducts;
import entitites.Member;
import entitites.Person;
import product.Product;
import reader.FileReader;

public class InvoiceWriter {
	
    
	public static void createInvoiceReport(List<Invoice> invoiceList)  {
		//Do all calculations and formatting here
		int i = 0;
		while(i < invoiceList.size()) {
		String invoiceNumber = invoiceList.get(i).getInvoiceCode();
		String trainerLastName = invoiceList.get(i).getPersonalTrainerCode().getLastName();
		String trainerFirstName = invoiceList.get(i).getPersonalTrainerCode().getFirstName();
		Member temp = invoiceList.get(i).getMemberCode();
		String memberName = temp.getName();
		String memberCode = temp.getMemberCode();
		String memberType = temp.getMemberType();
		if(memberType.equals("G")) {
			memberType = "General";
		} else if (memberType.equals("S")) {
			memberType = "Student";
		}
		String personLastName = invoiceList.get(i).getMemberCode().contact.getLastName();
		String personFirstName = invoiceList.get(i).getMemberCode().contact.getFirstName();
		
		Address memberAddress = invoiceList.get(i).getMemberCode().getAddress();

		List<InvoiceProducts> productList = invoiceList.get(i).getProductsList();
		
		
		//List<InvoiceProducts> productList
		
		
		
		
		
		InvoiceWriter.createExcutiveReport(invoiceNumber, memberName, memberType, trainerLastName, trainerFirstName );
		InvoiceWriter.createSingleInvoiceReport(invoiceNumber, trainerLastName, trainerFirstName, memberName, memberCode, memberType, personLastName, personFirstName, memberAddress, productList);
		i++;
		}
	}
	
	
	
	
	public static void createExcutiveReport(String invoiceNumber, String memberName, String memberType, String trainerLastName, String trainerFistName) {
		
		System.out.println("Executive Summary Report");
		System.out.println("===========================");
		System.out.println("Invoice            Member                                              Personal Trainer               Subtotal           Fees          Taxes           Discount        Total");
		System.out.println(invoiceNumber + "             " + memberName + " [" + memberType + "]                          "  +  trainerLastName + "," + trainerFistName);
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
		
		List<Product> productFileList = FileReader.createProductList();
		
		for(int i = 0; i < productFileList.size(); i++) {
			for(int j = 0; j < productList.size(); j++) {
				if(productFileList.get(i).getProductCode().equals(productList.get(j).getProductCode())) {
					System.out.println("HERE " + productFileList.get(i));
				}
			}
		}
		
		
	}
	
}
