package driver;

import reader.FileReader;
import writer.JSONWriter;
import writer.XMLWriter;

public class DataConverter {
	
	public static void main (String[] args) {
		
		//Create a FlatFileReader object
		FileReader reader = new FileReader();	
		//Returns Person, Member, and Product List
		
		//Create JSON Writer Object
		//Do something like this to use each list returned and convert to output file?
		JSONWriter PersonJSON = new JSONWriter(personList);
		JSONWriter MemberJSON = new JSONWriter(memberList);
		JSONWriter ProductJSON = new JSONWriter(productList);
		
		//Create XML Writer Object
		XMLWriter PersonXML = new XMLWriter();
		XMLWriter MemberXML = new XMLWriter();
		XMLWriter ProductXML = new XMLWriter();
		

	
	}
	

}
