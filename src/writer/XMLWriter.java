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

	private String memberCode;
	private String memberType;
	private String personCode;
	private String name;
	private Address address;

	///private final List<Person> finalPersonList = new ArrayList<Person>();
	private final List<Member> finalMemberList = new ArrayList<Member>();
	///private final List<Product> finalProductList = new ArrayList<Product>();



	public static void main(String[] args) {

		XMLWriter member1 = new XMLWriter();

		XStream xstream = new XStream();

		xstream.alias("Member", XMLWriter.class);

		String xmlString = xstream.toXML(member1);





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




