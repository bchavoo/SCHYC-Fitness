package driver;

import reader.FileReader;
import writer.JSONWriter;
import writer.XMLWriter;

public class DataConverter {
	
	public static void main (String[] args) {
		
		//Create a FlatFileReader object
		FileReader reader = new FileReader();		
		
		//Create JSON Writer Object
		JSONWriter PersonJSON = new JSONWriter();
		JSONWriter MemberJSON = new JSONWriter();
		JSONWriter ProductJSON = new JSONWriter();
		
		//Create XML Writer Object
		XMLWriter PersonXML = new XMLWriter();
		XMLWriter MemberXML = new XMLWriter();
		XMLWriter ProductXML = new XMLWriter();
		

	
	}
	

}
