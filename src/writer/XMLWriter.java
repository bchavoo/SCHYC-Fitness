package writer;


import java.util.List;
import com.thoughtworks.xstream.XStream;

import entities.Member;
import entities.Person;
import product.DayMembership;
import product.RentalEquipment;
import product.ParkingPass;
import product.Product;
import product.YearMembership;

//import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;

public class XMLWriter {

	public static void createPersonXML(List<Person> personList) throws IOException {
		XStream person = new XStream();
		person.alias("person", Person.class);
		String xmlPersonString = person.toXML(personList);
		
		System.out.println(xmlPersonString);
		
//		FileWriter fileWriter = new FileWriter("data/Persons.xml");
//		PrintWriter printWriter = new PrintWriter(fileWriter);
//		printWriter.print(xmlPersonString);
//		printWriter.close();

	}



	public static void createMemberXML(List<Member> memberList) throws IOException {
		XStream member = new XStream();
		member.alias("member", Member.class);
		String xmlMemberString = member.toXML(memberList);
		
		System.out.println(xmlMemberString);
		
//		FileWriter fileWriter = new FileWriter("data/Members.xml");
//		PrintWriter printWriter = new PrintWriter(fileWriter);
//		printWriter.print(xmlMemberString);
//		printWriter.close();

	}

	public static void createProductXML(List<Product> productList) throws IOException {
		XStream product = new XStream();
		product.alias("product", Product.class);
		product.alias("day-long-memberships", YearMembership.class);
		product.alias("year-long-memberships", DayMembership.class);
		product.alias("rentals", RentalEquipment.class);
		product.alias("parkingpass", ParkingPass.class);


		
		String xmlProductString = product.toXML(productList);
		
		System.out.println(xmlProductString);
		
//		FileWriter fileWriter = new FileWriter("data/Products.xml");
//		PrintWriter printWriter = new PrintWriter(fileWriter);
//		printWriter.print(xmlProductString);
//		printWriter.close();
		
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




