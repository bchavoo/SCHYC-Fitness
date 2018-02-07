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

public class XMLWriter {

	public static void createPersonXML(List<Person> personList) {

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




