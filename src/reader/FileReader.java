package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import entitites.Member;
import entitites.Person;
import entitites.Address;
import entitites.Product;
import entitites.Product.YearMemberships;
import entitites.Product.DayMemberships;
import entitites.Product.ParkingPass;
import entitites.Product.Equipment;


public class FileReader {			

	private final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("MM-dd-yyyy");
	public static List<Person> personList = new ArrayList<Person>();
	public static List<Member> memberList = new ArrayList<Member>();
	public static List<Product> productList = new ArrayList<Product>();
	public static int numberOfPersons = 0;
	public static int numberOfMembers = 0;
	public static int numberOfProducts = 0;






	//Person File-------------------------------------------------------------------------------------------------
	public static List<Person> createPersonList() {


		String personFile = "data/Persons.dat";
		Scanner s = null;

		try {
			s = new Scanner(new File(personFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}





		numberOfPersons = Integer.parseInt(s.nextLine());

		while(s.hasNext()) {
			String line = s.nextLine();
			String token[] = line.split(";");
			String personCode = token[0];
			String tokenName[] = token[1].split(",");
			String lastName = tokenName[0];
			String firstName = tokenName[1];
			String tokenAddress[] = token[2].split(",");
			String street = tokenAddress[0];
			String city = tokenAddress[1];
			String state = tokenAddress[2];
			String zip = tokenAddress[3];
			String country = tokenAddress[4];

			ArrayList<String> emailArray = new ArrayList<String>();



			//Instead make more generic with loop for email(larger size);
			if(token.length == 4) {
				String tokenEmail[] = token[3].split(",");
				for(int i = 0; i < tokenEmail.length; i++) {
					String email = tokenEmail[i];
					emailArray.add(email);
				}
			}



			Address a = new Address (street, city, state, zip, country);

			Person p = new Person(personCode, firstName, lastName, a, emailArray);

			//Add person to person List?
			personList.add(p);
		}
		return personList;
	}











	//Member File-------------------------------------------------------------------------------------------------
	public static List<Member> createMemberList() {

		String memberFile = "data/Members.dat";
		Scanner m = null;

		try {
			m = new Scanner(new File(memberFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}




		numberOfMembers = Integer.parseInt(m.nextLine());

		while(m.hasNext()) {
			String line = m.nextLine();
			String token[] = line.split(";");
			String memberCode = token[0];
			String memberType = token[1];
			String personCode = token[2];
			String memberName = token[3];
			String tokenAddress[] = token[4].split(",");
			String street = tokenAddress[0];
			String city = tokenAddress[1];
			String state = tokenAddress[2];
			String zip = tokenAddress[3];
			String country = tokenAddress[4];


			Address a = new Address (street, city, state, zip, country);

			//FIND AND ADD PERSON


			Person match = null;
			for(int i = 0; i < numberOfPersons; i++) {
				if(personList.get(i).getPersonCode().equals(personCode)) {
					match = personList.get(i);
				}
			}

			Member mem = new Member(memberCode, memberType, match, memberName, a);

			//Add member	
			memberList.add(mem);
		}


		return memberList;
	}








	//Products File-------------------------------------------------------------------------------------------------
	public static List<Product> createProductList() {

		String productFile = "data/Products.dat";
		Scanner pr = null;

		try {
			pr = new Scanner(new File(productFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		numberOfProducts = Integer.parseInt(pr.nextLine());

		Product prod = null;

		while(pr.hasNext()) {
			String line = pr.nextLine();
			String token[] = line.split(";");

			String productCode = "";
			String productType = "";
			String street = "";
			String city = "";
			String state = "";
			String zip = "";
			String country = "";
			String membershipGroup = "";
			double costPerUnit = 0;


			if(token[1].equals("R")) {
				String equipment = token[2];
				double equipmentCost = Double.parseDouble(token[3]);

			} else if (token[1].equals("P")) {
				double parkingFee = Double.parseDouble(token[2]);

			} else if (token[1].equals("Y")) {
				String startDate = token[2];
				String endDate = token[3];
				String tokenAddress[] = token[4].split(",");
				street = tokenAddress[0];
				city = tokenAddress[1];
				state = tokenAddress[2];
				zip = tokenAddress[3];
				country = tokenAddress[4];
				membershipGroup = token[5];
				costPerUnit = Double.parseDouble(token[6]);

				Address address = new Address(street, city, state, zip, country);

				//YearMemberships productList = new YearMemberships (productCode, productType, startDate, endDate, address, membershipGroup, costPerUnit);
				//productList.add(productList);

			} else if (token[1].equals("D")){			
				String dateTime = token[2];
				String tokenAddress[] = token[3].split(",");
				street = tokenAddress[0];
				city = tokenAddress[1];
				state = tokenAddress[2];
				zip = tokenAddress[3];
				country = tokenAddress[4];
				costPerUnit = Double.parseDouble(token[4]);
			}



		}
		return productList;
	}




}














