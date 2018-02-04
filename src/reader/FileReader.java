package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;
import entitites.Member;
import entitites.Person;
import entitites.Address;

public class FileReader {
	
	public static void main(String args[]) {
		
		
		//Person File-------------------------------------------------------------------------------------------------
		String personFile = "data/Persons.dat";
		Scanner s = null;
		
		try {
			s = new Scanner(new File(personFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		

		
		
		int n = Integer.parseInt(s.nextLine());
		
		System.out.println("The value of n is: " + n);
		
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
			
			
			//Address a = new Address (street, city, state, zip, country);
			//Person p = new Person(personCode, firstName, lastName, a, email??);
			
			//Add person to directory
			//lib.addPerson(p);

		}
		
		

		
		
		
		
		
		
		//Member File-------------------------------------------------------------------------------------------------
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
					
			System.out.println("Member Code: " + memberCode + " Member type: " + memberType + " Person code: " + personCode + " Member Name: " + memberName 
					+ "Address : " + street + ", " + state + ", " + zip + ", " + country);
			
			
			Address a = new Address (street, city, state, zip, country);
			Member mem = new Member(memberCode, memberType, personCode, memberName, a);
					
			//Add person to directory
			//lib.addPerson(p);

			}
		
		
		
		
		
		
		
		
		
		//Products File-------------------------------------------------------------------------------------------------
		String productFile = "data/Products.dat";
		Scanner pr = null;
		
		try {
			pr = new Scanner(new File(productFile));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
				
		int k = Integer.parseInt(pr.nextLine());
				
		System.out.println("The value of n is: " + n);
				
		while(pr.hasNext()) {
			String line = pr.nextLine();
			String token[] = line.split(";");
			String productCode = token[0];
			String productType = token[1];
			
			if(token[1].equals("R")) {
				String equipment = token[2];
				double equipmentCost = Double.parseDouble(token[3]);
				System.out.println("Product Code: " + productCode);
				System.out.println("Product Type: " + productType);
				System.out.println("Equipment: " + equipment);
				System.out.println("Equipment Cost: " + equipmentCost);
				System.out.println("------------------------------------------------");
			} else if (token[1].equals("P")) {
				double parkingFee = Double.parseDouble(token[2]);
				System.out.println("Product Code: " + productCode);
				System.out.println("Product Type: " + productType);
				System.out.println("Parking Fee: " + parkingFee);
				System.out.println("------------------------------------------------");
			} else if (token[1].equals("Y")) {
				String startDate = token[2];
				String endDate = token[3];
				String tokenAddress[] = token[4].split(",");
				String street = tokenAddress[0];
				String city = tokenAddress[1];
				String state = tokenAddress[2];
				String zip = tokenAddress[3];
				String country = tokenAddress[4];
				String membershipGroup = token[5];
				double costPerUnit = Double.parseDouble(token[6]);
				System.out.println("Product Code: " + productCode);
				System.out.println("Product Type: " + productType);
				System.out.println("Start Date: " + startDate);
				System.out.println("End Date: " + endDate);
				System.out.println("Address: " + street + ", " + city + ", " + state + ", " + zip + ", " + country);
				System.out.println("Group: " + membershipGroup);
				System.out.println("Cost of Membership: " + costPerUnit);
				System.out.println("------------------------------------------------");
			} else {			
			String dateTime = token[2];
			String tokenAddress[] = token[3].split(",");
			String street = tokenAddress[0];
			String city = tokenAddress[1];
			String state = tokenAddress[2];
			String zip = tokenAddress[3];
			String country = tokenAddress[4];
			String costPerUnit = token[4];
			
			
			
			System.out.println("Product Code: " + productCode);
			System.out.println("Product Type: " + productType);
			System.out.println("Date & Time: " + dateTime);
			System.out.println("Address : " + street + ", " + city + ", " + state + ", " + zip + ", " + country);
			System.out.println("Membership Cost: " + costPerUnit);
			System.out.println("------------------------------------------------");
			}
					
					
			//Address a = new Address (street, city, state, zip, country);
			//Person p = new Person(personCode, firstName, lastName, a, email??);
					
			//Add person to directory
			//lib.addPerson(p);

			}
		
	}
    
    
    

}
