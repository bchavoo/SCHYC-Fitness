package writer;


import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.Writer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;


import entitites.Member;
import entitites.Person;
import entitites.Address;
import entitites.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;
//import com.datacontainers.Person;
import com.thoughtworks.xstream.XStream;

public class XMLWriter {

	public static void createPersonXML(List<Person> personList) {

	
		public class XMLWriter {

			public void xmlConverter(List<Person> persons) {
				XStream  xstream = new XStream();
				
		        File xmlOutput = new File("data/Persons.xml");
				
				PrintWriter xmlPrintWriter = null;
				try {
					xmlPrintWriter = new PrintWriter(xmlOutput);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
				
				xstream.alias("person", Person.class); 
				for(Person aPerson : persons) {
					// Use toXML method to convert Person object into a String
					String personOutput = xstream.toXML(aPerson);
					xmlPrintWriter.write(personOutput);
				}
				xmlPrintWriter.close();	
			}
		}
		//XStream person = new XStream();
		//person.alias("Person", XMLWriter.class);
		//String xmlPerson = person.toXML(person);
		
		//System.out.println(xmlPerson);
	}

	public static void createMemberXML(List<Member> memberList) {

	}

	public static void createProductJSON(List<Product> productList) {
	
	}



}


























//		public List<Person> exportFinalPersonList() {
//			
//			
//			
//			
//			return finalPersonList;
//		}
//		
//		
//		public List<Member> exportFinalMemberList() {
//			
//			
//			return finalMemberList;
//		}
//		
//		public List<Product> exportFinalProductList() {
//		
//		
//		
//			return finalProductList;
//		}




