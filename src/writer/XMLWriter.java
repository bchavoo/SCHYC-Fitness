package writer;


import java.util.List;
import com.thoughtworks.xstream.XStream;
import entitites.Member;
import entitites.Person;
import entitites.Product;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class XMLWriter {

	public static void createPersonXML(List<Person> personList) throws IOException {
		XStream person = new XStream();
		person.alias("person", Person.class);
		String xmlPersonString = person.toXML(personList);
		
		FileWriter fileWriter = new FileWriter("/Users/bryanchavez/downloads/persons.xml");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(xmlPersonString);
		printWriter.close();

	}



	public static void createMemberXML(List<Member> memberList) throws IOException {
		XStream member = new XStream();
		member.alias("member", Member.class);
		String xmlMemberString = member.toXML(memberList);
		
		FileWriter fileWriter = new FileWriter("/Users/bryanchavez/downloads/members.xml");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(xmlMemberString);
		printWriter.close();

	}

	public static void createProductXML(List<Product> productList) throws IOException {
		XStream product = new XStream();
		product.alias("member", Product.class);
		String xmlProductString = product.toXML(productList);
		
		FileWriter fileWriter = new FileWriter("/Users/bryanchavez/downloads/products.xml");
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.print(xmlProductString);
		printWriter.close();
		
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




