package writer;

import entitites.Invoice;
import entitites.Member;
import entitites.Person;
import product.Product;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class JSONWriter {

//	public static void createPersonJSON(List<Person> personList) throws IOException {
//
//
//		//Pretty Printing
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//		String jsonString = gson.toJson(personList);
//
//		FileWriter fileWriter = new FileWriter("data/Persons.json");
//		PrintWriter printWriter = new PrintWriter(fileWriter);
//		printWriter.print(jsonString);
//		printWriter.close();
//	}
//
//	public static void createMemberJSON(List<Member> memberList) throws IOException {
//
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//		String jsonString = gson.toJson(memberList);
//
//		FileWriter fileWriter = new FileWriter("data/Members.json");
//		PrintWriter printWriter = new PrintWriter(fileWriter);
//		printWriter.print(jsonString);
//		printWriter.close();	}
//
//	public static void createProductJSON(List<Product> productList) throws IOException {
//
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();
//
//		String jsonString = gson.toJson(productList);
//	
//		FileWriter fileWriter = new FileWriter("data/Products.json");
//		PrintWriter printWriter = new PrintWriter(fileWriter);
//		printWriter.print(jsonString);
//		printWriter.close();
//
//	}
	
	public static void createInvoiceList(List<Invoice> invoiceList) throws IOException {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String jsonString = gson.toJson(invoiceList);
		
		System.out.println(jsonString);
	
//		FileWriter fileWriter = new FileWriter("data/Products.json");
//		PrintWriter printWriter = new PrintWriter(fileWriter);
//		printWriter.print(jsonString);
//		printWriter.close();

	}
}



