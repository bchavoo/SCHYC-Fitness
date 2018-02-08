package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import entitites.Address;
import entitites.DayMemberships;
import entitites.Equipment;
import entitites.Member;
import entitites.ParkingPass;
import entitites.Person;
import entitites.Product;
import entitites.YearMemberships;

public class FileReader {

	public static List<Person> personList = new ArrayList<Person>();
	public static List<Member> memberList = new ArrayList<Member>();
	public static List<Product> productList = new ArrayList<Product>();
	public static int numberOfPersons = 0;
	public static int numberOfMembers = 0;
	public static int numberOfProducts = 0;

	// Person
	// File-------------------------------------------------------------------------------------------------
	public static List<Person> createPersonList() {

		String personFile = "data/Persons.dat";
		Scanner s = null;

		try {
			s = new Scanner(new File(personFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		numberOfPersons = Integer.parseInt(s.nextLine());

		while (s.hasNext()) {
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

			// Instead make more generic with loop for email(larger size);
			if (token.length == 4) {
				String tokenEmail[] = token[3].split(",");
				for (int i = 0; i < tokenEmail.length; i++) {
					String email = tokenEmail[i];
					emailArray.add(email);
				}
			}

			Address a = new Address(street, city, state, zip, country);

			Person p = new Person(personCode, firstName, lastName, a, emailArray);

			// Add person to person List?
			personList.add(p);
		}
		return personList;
	}

	// Member
	// File-------------------------------------------------------------------------------------------------
	public static List<Member> createMemberList() {

		String memberFile = "data/Members.dat";
		Scanner m = null;

		try {
			m = new Scanner(new File(memberFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		numberOfMembers = Integer.parseInt(m.nextLine());

		while (m.hasNext()) {
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

			Address a = new Address(street, city, state, zip, country);

			// FIND AND ADD PERSON

			Person match = null;
			for (int i = 0; i < numberOfPersons; i++) {
				if (personList.get(i).getPersonCode().equals(personCode)) {
					match = personList.get(i);
				}
			}

			Member mem = new Member(memberCode, memberType, match, memberName, a);

			// Add member
			memberList.add(mem);
		}

		return memberList;
	}

	// Products
	// File-------------------------------------------------------------------------------------------------
	public static List<Product> createProductList() {

		String productFile = "data/Products.dat";
		Scanner pr = null;

		try {
			pr = new Scanner(new File(productFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		numberOfProducts = Integer.parseInt(pr.nextLine());

		while (pr.hasNext()) {
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

			if (token[1].equals("R")) {
				productCode = token[0];
				productType = token[1];
				String equipment = token[2];
				double cost = Double.parseDouble(token[3]);

				Equipment product = new Equipment(equipment, cost, productCode, productType);
				productList.add(product);

			} else if (token[1].equals("P")) {
				productCode = token[0];
				productType = token[1];
				double cost = Double.parseDouble(token[2]);
				ParkingPass product = new ParkingPass(cost, productCode, productType);
				productList.add(product);

			} else if (token[1].equals("Y")) {
				productCode = token[0];
				productType = token[1];
				String startDate = token[2];
				String endDate = token[3];
				String tokenAddress[] = token[4].split(",");
				street = tokenAddress[0];
				city = tokenAddress[1];
				state = tokenAddress[2];
				zip = tokenAddress[3];
				country = tokenAddress[4];
				membershipGroup = token[5];
				double cost = Double.parseDouble(token[6]);

				Address address = new Address(street, city, state, zip, country);

				YearMemberships product = new YearMemberships(startDate, endDate, address, membershipGroup, cost, productCode, productType);

				productList.add(product);

			} else if (token[1].equals("D")) {
				productCode = token[0];
				productType = token[1];
				String startDate = token[2];
				String tokenAddress[] = token[3].split(",");
				street = tokenAddress[0];
				city = tokenAddress[1];
				state = tokenAddress[2];
				zip = tokenAddress[3];
				country = tokenAddress[4];
				double cost = Double.parseDouble(token[4]);
				
				Address address = new Address(street, city, state, zip, country);

				DayMemberships product = new DayMemberships(startDate, address, cost, productCode, productType);

				productList.add(product);
			}

		
		}
		return productList;
	}

}
