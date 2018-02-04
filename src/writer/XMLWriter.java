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
import entitites.Address;
import entitites.Product;

public class XMLWriter {
	
	
	///private final List<Person> finalPersonList = new ArrayList<Person>();
	///private final List<Member> finalMemberList = new ArrayList<Member>();
	///private final List<Product> finalProductList = new ArrayList<Product>();
	
	int id = 0;
	String name = "";
	
	public static void main(String[] args) {
		
		XMLWriter s1 = new XMLWriter();
		
		s1.id = 123;
		s1.name = "Bryan";
		System.out.println(s1.id);
		System.out.println(s1.name);
		
		
		XStream xstream = new XStream();
		
		xstream.alias("person", XMLWriter.class);
		
		String xmlString = xstream.toXML(s1);
		
		
		
		

		System.out.println(xmlString);
			
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
		
		
	

