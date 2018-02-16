package driver;


import java.io.IOException;
import java.util.List;

import entitites.Invoice;
import entitites.Member;
import entitites.Person;
import entitites.Product;
import reader.FileReader;
import writer.JSONWriter;
import writer.XMLWriter;

public class DataConverter {

	public static void main (String[] args) throws IOException {

		//Returns Person, Member, and Product List
		List<Person> personList = FileReader.createPersonList();
		List<Member> memberList = FileReader.createMemberList();
		List<Product> productList = FileReader.createProductList();
		List<Invoice> invoiceList = FileReader.createInvoiceList();


		//Create JSON Writer Object
		JSONWriter.createPersonJSON(personList);
		JSONWriter.createMemberJSON(memberList);
		JSONWriter.createProductJSON(productList);
		JSONWriter.createInvoiceJSON(invoiceList);


		//Create XML Writer Object
		XMLWriter.createPersonXML(personList);
		XMLWriter.createMemberXML(memberList);
		XMLWriter.createProductXML(productList);


	}


}
