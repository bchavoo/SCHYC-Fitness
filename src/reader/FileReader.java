package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;

import entitites.Member;
import entitites.Person;
import entitites.Address;

public class FileReader {
	
	public static void main(String args[]) {
		
		String personFile = "data/Persons.dat";
		Scanner p = null;
		
		try {
			p = new Scanner(new File(personFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String person = "";
		
		
		int i = 0;
		while(p.hasNextLine()) {
			String line = p.nextLine();
						
			System.out.println(line);
			
 		}
		
		System.out.println(person);
		
		
		
		
		
		
		//Person person[] = new Person[20];
		
		/*
		for (Person i : person) {
			person[i] = person;
			System.out.println(i);
		}
		*/
		
		
	}
    
    
    

}
