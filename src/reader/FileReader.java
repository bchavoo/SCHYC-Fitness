package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import entities.Address;
import entities.General;
import entities.Invoice;
import entities.InvoiceProducts;
import entities.Member;
import entities.Person;
import entities.Student;
import product.DayMembership;
import product.RentalEquipment;
import product.ParkingPass;
import product.Product;
import product.YearMembership;

public class FileReader {
	
    /**
	 * Here we store the person member and product in a list
	 */
	public static List<Person> personList = new ArrayList<Person>();
	public static List<Member> memberList = new ArrayList<Member>();
	public static List<Product> productList = new ArrayList<Product>();
	public static List<Invoice> invoiceList = new ArrayList<Invoice>();
	public static int numberOfPersons = 0;
	public static int numberOfMembers = 0;
	public static int numberOfProducts = 0;
	public static int numberOfInvoices = 0;

	// Person File-------------------------------------------------------------------------------------------------
	public static List<Person> createPersonList() {

		String personFile = "data/Persons.dat";
		Scanner s = null;

		try {
			s = new Scanner(new File(personFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		numberOfPersons = Integer.parseInt(s.nextLine());
		
         /**
		 * Here we tokenize the person file 
		 */
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

            /**
			 * Here we store the email in an arraylist
			 */
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
            /**
			 * We create two new objects that will contain the address and person inff
			 */


			Person p = new Person(personCode, firstName, lastName, a, emailArray);

			// Add person to person List?
			personList.add(p);
		}
		return personList;
	}

	// Member File-------------------------------------------------------------------------------------------------
	public static List<Member> createMemberList() {

		String memberFile = "data/Members.dat";
		Scanner m = null;

		try {
			m = new Scanner(new File(memberFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		numberOfMembers = Integer.parseInt(m.nextLine());
		
        /**
		 * Here we tokenize the member file
		 */
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
			List<Person> personList = FileReader.createPersonList();
			/**
			 * Here we create a for loop that will help match a persons info
			 *
			 */
			for (int i = 0; i < numberOfPersons; i++) {
				if (personList.get(i).getPersonCode().equals(personCode)) {
					match = personList.get(i);
				}
			}
			
		
			/**
			 * Here we have runtime polymorphism, we declare a Member variable but
			 * instead we create and store a new General or Student object
			 */
			if(memberType.equals("G")) {
				Member mem = new General(memberCode, memberType, match, memberName, a);
				memberList.add(mem);
			} else if (memberType.equals("S")) {
				Member mem = new Student(memberCode, memberType, match, memberName, a);
				memberList.add(mem);
			}

		}

		return memberList;
	}

	// Products File-------------------------------------------------------------------------------------------------
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
			// We initialize all the variables as an empty string that
			//We will eventually fill in with values
			String productCode = "";
			String productType = "";
			String street = "";
			String city = "";
			String state = "";
			String zip = "";
			String country = "";
			String membershipGroup = "";
             /**
			 * For the next conditionals we tokenize the product through if its a 
			 * rental, parking, day or year memberships. And we portray the variables
			 * needed for each product
			 */
			if (token[1].equals("R")) {
				productCode = token[0];
				productType = token[1];
				String equipment = token[2];
				double cost = Double.parseDouble(token[3]);

				/**
				 * Here we again have runtime polymorphism, we declare a Product variable
				 * but we store a new Equipment or Parking Pass
				 */
				Product product = new RentalEquipment(equipment, cost, productCode, productType);
				productList.add(product);

			} else if (token[1].equals("P")) {
				productCode = token[0];
				productType = token[1];
				double cost = Double.parseDouble(token[2]);
				
				//POLYMORPHISM 
				Product product = new ParkingPass(cost, productCode, productType);
				productList.add(product);

			} else if (token[1].equals("Y")) {
				productCode = token[0];
				productType = token[1];
				
			
				DateTimeFormatter date = DateTimeFormat.forPattern("yyyy-MM-dd");
				DateTime startDate = date.parseDateTime(token[2]);
				DateTime endDate = date.parseDateTime(token[3]);
								
				String tokenAddress[] = token[4].split(",");
				street = tokenAddress[0];
				city = tokenAddress[1];
				state = tokenAddress[2];
				zip = tokenAddress[3];
				country = tokenAddress[4];
				membershipGroup = token[5];
				double cost = Double.parseDouble(token[6]);

				Address address = new Address(street, city, state, zip, country);

				/**
				 * Here we again have runtime polymorphism, we declare a Product variable
				 * but we store a new Year or DayMembership
				 */
				Product product = new YearMembership(startDate, endDate, address, membershipGroup, cost, productCode, productType);

				productList.add(product);

			} else if (token[1].equals("D")) {
				productCode = token[0];
				productType = token[1];
				
				//Create date and time as date time formatters 
				DateTimeFormatter date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
				DateTime startDate = date.parseDateTime(token[2]);
				
				String tokenAddress[] = token[3].split(",");
				street = tokenAddress[0];
				city = tokenAddress[1];
				state = tokenAddress[2];
				zip = tokenAddress[3];
				country = tokenAddress[4];
				double cost = Double.parseDouble(token[4]);

				Address address = new Address(street, city, state, zip, country);

				//POLYMORPHISM 
				Product product = new DayMembership(startDate, address, cost, productCode, productType);

				productList.add(product);
			}


		}
		return productList;

	}
	
	
	// Invoice File-------------------------------------------------------------------------------------------------
		public static List<Invoice> createInvoiceList() {

			String invoiceFile = "data/Invoices.dat";
			Scanner iv = null;

			try {
				iv = new Scanner(new File(invoiceFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			numberOfInvoices = Integer.parseInt(iv.nextLine());

			while (iv.hasNext()) {
				Member m = null;
				Person p = null;
				String line = iv.nextLine();
				String token[] = line.split(";");
				String invoiceNumber = token[0];
				
				String memberCode = token[1];
				
				/**
				 * Here we call the member list so that we can access the data
				 */
				
				List<Member> memberList = FileReader.createMemberList();
				
				//Find and store a matching member from the invoice
				for(int i = 0; i < numberOfMembers; i++) {
					if(memberList.get(i).memberCode.equals(memberCode)) {
						m = memberList.get(i);
					}
				}
				
				String personalTrainerCode = token[2];
				
				/**
				 * Here we call the person list so that we can access it's data
				 */
				List<Person> personList = FileReader.createPersonList();
				
				//Find and store Person
				for(int i = 0; i < numberOfPersons; i++) {
					if(personList.get(i).getPersonCode().equals(personalTrainerCode)) {
						p = personList.get(i);
					}
				}
				
				String invoiceDate = token[3];
				String productCode = "";
				String personCode = "";
				
				String tokenProducts[] = token[4].split(",");
				
				/**
				 * Here we create an array list called invoiceProductArray that will contain
				 * the products in that invoice
				 */
				ArrayList<InvoiceProducts> invoiceProductArray = new ArrayList<InvoiceProducts>();
				
				/**
				 * Here we use a for loop to loop the token products length to find if 
				 * it is 2 or 3 for the last part of the token to decide the if we will have 
				 * a related membership tied to the product so we can store it or
				 * if we have no extra data and can ignore it.
				 * We then store all of it into an Array of InvoiceProducts
				 */
				for(int i = 0; i < tokenProducts.length; i++) {
					int quantity = 0;
					String tokenType[] = tokenProducts[i].split(":");
						if(tokenType.length == 2) {
							productCode = tokenType[0];
							quantity = Integer.parseInt(tokenType[1]);
							personCode = "";
							
							InvoiceProducts ip = new InvoiceProducts(productCode, quantity, personCode);
							invoiceProductArray.add(ip);
							
						} else if (tokenType.length == 3) {
							productCode = tokenType[0];
							quantity = Integer.parseInt(tokenType[1]);
							personCode = tokenType[2];
							
							InvoiceProducts ip = new InvoiceProducts(productCode, quantity, personCode);
							invoiceProductArray.add(ip);
						}
				}

                /**
                 * At the end we create a new invoice with all the
                 * information we gathered from the invoice file into an Invoice object
                 * and add the object to a list of Invoices
                 */
				Invoice v = new Invoice(invoiceNumber, m, p, invoiceDate, invoiceProductArray);

				invoiceList.add(v);
			}
			return invoiceList;
		}
	
	
	

}
