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
		
		
		//Person File
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
			String tokenCode[] = line.split(";");
			String personCode = tokenCode[0];
			String tokenName[] = tokenCode[1].split(",");
			String lastName = tokenName[0];
			String firstName = tokenName[1];
			String tokenAddress[] = tokenCode[2].split(",");
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
		
		

		
		
		
		
		
		
		//Member File
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
			String tokenCode[] = line.split(";");
			String memberCode = tokenCode[0];
			String memberType = tokenCode[1];
			String personCode = tokenCode[2];
			String memberName = tokenCode[3];
			String tokenAddress[] = tokenCode[4].split(",");
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
		
	}
    
    
    

}
