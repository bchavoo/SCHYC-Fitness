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
		
		String personFile = "data/Persons.dat";
		Scanner s = null;
		
		try {
			s = new Scanner(new File(personFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		Person person[] = new Person [21];

		
		
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
			
			
			System.out.println("Person Code: " + personCode);
			System.out.println("Name: " + lastName + ", " + firstName);
			System.out.println("Address: " + street + ", " + city + ", " + zip + ", " + country);

		}
		
		

		
		
	}
    
    
    

}
