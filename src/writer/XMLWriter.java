package writer;


import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.Writer;


import com.thoughtworks.xstream.XStream;


import entitites.Member;
import entitites.Person;
import entitites.Product;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
//import com.datacontainers.Person;

public class XMLWriter {

	public static void createPersonXML(List<Person> personList) {
		XStream person = new XStream();
		
		person.alias("person", Person.class);
		
		String xmlPersonString = person.toXML(personList);
		
		System.out.println(xmlPersonString);
	

				
			}
		
		//XStream person = new XStream();
		//person.alias("Person", XMLWriter.class);
		//String xmlPerson = person.toXML(person);
		
		//System.out.println(xmlPerson);
	

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




