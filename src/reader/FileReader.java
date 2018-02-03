package reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Comparator;
import java.util.Scanner;

import entitites.Member;

public class FileReader {
	
	public static void main(String args[]) {
		
		String fileName = "data/Persons.dat";
		Scanner s = null;
		
		try {
			s = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		Member[] members = new Member[];
		
		
		
		
	}
    
    
    

}
