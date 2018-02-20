package writer;

import entitites.Invoice;
import entitites.Member;
import entitites.Person;
import product.Product;
import product.YearMemberships;
import product.DayMemberships;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

public class JSONWriter {

	public static void createPersonJSON(List<Person> personList) throws IOException {


		//Pretty Printing
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String jsonString = gson.toJson(personList);

		FileWriter fileWriter = new FileWriter("data/Persons.json");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(jsonString);
		printWriter.close();
	}

	public static void createMemberJSON(List<Member> memberList) throws IOException {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String jsonString = gson.toJson(memberList);

		FileWriter fileWriter = new FileWriter("data/Members.json");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(jsonString);
		printWriter.close();	}

	public static void createProductJSON(List<Product> productList) throws IOException {

		for(int i = 0; i < productList.size(); i++) {
			if(productList.get(i) instanceof YearMemberships) {
				YearMemberships newProd = (YearMemberships) productList.get(i);
				//String startDate = (String) newProd.getStartDate();
			} else if(productList.get(i) instanceof DayMemberships) {
				DayMemberships newProd = (DayMemberships) productList.get(i);
			}
		}
		
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String jsonString = gson.toJson(productList);
		String output = jsonString.toString();
		
		System.out.println(output);
	
//		FileWriter fileWriter = new FileWriter("data/Products.json");
//		PrintWriter printWriter = new PrintWriter(fileWriter);
//		printWriter.print(jsonString);
//		printWriter.close();

	}
	
	public static void createInvoiceList(List<Invoice> invoiceList) throws IOException {

		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String jsonString = gson.toJson(invoiceList);
		
		//System.out.println(jsonString);
	
//		FileWriter fileWriter = new FileWriter("data/Products.json");
//		PrintWriter printWriter = new PrintWriter(fileWriter);
//		printWriter.print(jsonString);
//		printWriter.close();

	}
}



