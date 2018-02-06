package driver;


import java.util.List;

import entitites.Member;
import entitites.Person;
import reader.FileReader;
import writer.JSONWriter;
import writer.XMLWriter;

public class DataConverter {

	public static void main (String[] args) {

		//Returns Person, Member, and Product List
		List<Person> personList = FileReader.createPersonList();
		List<Member> memberList = FileReader.createMemberList();


		//Create JSON Writer Object

		JSONWriter.createPersonJSON(personList);
		JSONWriter.createMemberJSON(memberList);
		
		//		JSONWriter ProductJSON = new JSONWriter(productList);
		//		
		//		//Create XML Writer Object
		//		XMLWriter PersonXML = new XMLWriter();
		//		XMLWriter MemberXML = new XMLWriter();
		//		XMLWriter ProductXML = new XMLWriter();



	}


}
