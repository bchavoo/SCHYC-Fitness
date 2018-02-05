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

	public class FileReader {			
			
			private final DateTimeFormatter DATE_FORMATTER = DateTimeFormat.forPattern("MM-dd-yyyy");
			private List<Person> personList = new ArrayList<Person>();
			private List<Member> memberList = new ArrayList<Member>();
		    private List<Product> productList = new ArrayList<Product>();
			
			//Person File-------------------------------------------------------------------------------------------------
		public List<Person> createPersonList() {
			
			String personFile = "data/Persons.dat";
			Scanner s = null;
			
			try {
				s = new Scanner(new File(personFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			
	
			
			
			int n = Integer.parseInt(s.nextLine());
						
			ArrayList<Person> personList = new ArrayList<Person>();
			
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
				
				if(token.length == 4) {
					String tokenEmail[] = token[3].split(",");
				
					if(tokenEmail.length == 1) {
						String email = token[3];
						emailArray.add(email);
					} else if (tokenEmail.length == 2) {
						String email1 = tokenEmail[0];
						String email2 = tokenEmail[1];
						emailArray.add(email1);
						emailArray.add(email2);
					}
					
				} else if (token.length == 3) {
					emailArray.add(null);
				}
					
							
			
				
				Address a = new Address (street, city, state, zip, country);
								
				Person p = new Person(personCode, firstName, lastName, a, emailArray);
				
				//Add person to person List?
				personList.add(p);
			}
			return personList;
			}
			
		
	
			
			
			
			
			
			
			
			
		//Member File-------------------------------------------------------------------------------------------------
		public List<Member> createMemberList() {
			
			String memberFile = "data/Members.dat";
			Scanner m = null;
					
			try {
				m = new Scanner(new File(memberFile));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
					
					
					
					
			int j = Integer.parseInt(m.nextLine());
					
			System.out.println("The value of j is: " + j);
					
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
				Member mem = new Member(memberCode, memberType, personCode, memberName, a);
						
				//Add member	
				ArrayList<Member> memberList = new ArrayList<Member>();
				memberList.add(mem);
			}
			
			return memberList;
		}
			
			
			
			
			
			
			
			
			//Products File-------------------------------------------------------------------------------------------------
		public List<Product> createProductList() {
		
			String productFile = "data/Products.dat";
			Scanner pr = null;
			
			try {
				pr = new Scanner(new File(productFile));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
					
			int k = Integer.parseInt(pr.nextLine());
										
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
						
						
				Address a = new Address (street, city, state, zip, country);
				Product prod = new Product(productCode);
						
				//Add product (Take out of while loop to declare
				ArrayList<Product> productList = new ArrayList<Product>();
				
				productList.add(prod);
				}
			return productList;
		}
		}
			
			
	
	    
	
	
	
	
	
	
	
    


