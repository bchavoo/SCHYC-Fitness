package driver;


import java.io.IOException;
import java.util.List;

import entitites.Member;
import entitites.Person;
import product.Product;
import reader.FileReader;
import writer.JSONWriter;
import writer.XMLWriter;

public class DataConverter {

	public static void main (String[] args) throws IOException {

		//Returns Person, Member, and Product List
		List<Person> personList = FileReader.createPersonList();
		List<Member> memberList = FileReader.createMemberList();
		List<Product> productList = FileReader.createProductList();


		//Create JSON file for each of the lists
		//JSONWriter.createPersonJSON(personList);
		//JSONWriter.createMemberJSON(memberList);
		JSONWriter.createProductJSON(productList);

		//Create XML file for each of the same lists 
		//XMLWriter.createPersonXML(personList);
		//XMLWriter.createMemberXML(memberList);
		//XMLWriter.createProductXML(productList);


	}


}
