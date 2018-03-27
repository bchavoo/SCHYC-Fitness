package com.sf.ext;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.joda.time.DateTime;

import entities.Person;
import entities.Student;
import product.DayMembership;
import product.ParkingPass;
import product.Product;
import product.RentalEquipment;
import product.YearMembership;
import reader.FileReader;
import entities.Address;
import entities.General;
import entities.Invoice;
import entities.InvoiceProducts;
import entities.Member;

public class DBReader {

	//Returns a single address object (Do not delete)
	private static Address getAddress(int AddressID){
		Connection conn = DBUtility.connectMeToDatabase();

		String getAddress = "SELECT * FROM Address WHERE AddressID = ?";

		PreparedStatement ps;
		ResultSet rs;
		Address a = null;

		try {
			ps = conn.prepareStatement(getAddress);
			ps.setInt(1, AddressID);
			rs = ps.executeQuery();

			while (rs.next()) {
				String street = rs.getString("Street");
				String city = rs.getString("City");
				String state = rs.getString("State");
				String zip = rs.getString("Zip");
				String country = rs.getString("Country");

				a = new Address(street, city, state, zip, country);

			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		DBUtility.closeConnection(conn);
		
		return a;

	}



	//Finds and returns a single person (Don't delete)
	private static Person getPerson(String personCode){
		Connection conn = DBUtility.connectMeToDatabase();

		String getPerson = "SELECT * FROM Persons WHERE PersonCode = ?";

		Person p = null;

		try {
			PreparedStatement ps = conn.prepareStatement(getPerson);
			ps.setString(1, personCode);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int personID = rs.getInt("PersonID");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				Address address = getAddress(rs.getInt("AddressID"));
				ArrayList<String> email = getEmails(personID);

				p = new Person(personCode, firstName, lastName, address, email);

			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		DBUtility.closeConnection(conn);

		return p;


	}


	//Returns an array list of emails of a specific person (Don't delete)
	private static ArrayList<String> getEmails(int PersonID){
		Connection conn = DBUtility.connectMeToDatabase();

		ArrayList<String> allEmails = new ArrayList<String>();

		String getEmail = "SELECT * FROM Emails WHERE PersonID = ?";

		PreparedStatement ps;
		ResultSet rs;


		try {
			ps = conn.prepareStatement(getEmail);
			ps.setInt(1, PersonID);
			rs = ps.executeQuery();


			while(rs.next()){			
				allEmails.add(rs.getString("Email"));
			}

			DBUtility.closeConnection(conn);
			rs.close();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}


		return allEmails;
	}




//	private Invoice getInvoice(int InvoiceID){
//		String getInvoice = "SELECT * FROM Invoices WHERE InvoiceCode = ?";
//		Invoice i = null;
//		try {
//			Connection conn = DBUtility.connectMeToDatabase();
//			PreparedStatement ps = conn.prepareStatement(getInvoice);
//			ps.setInt(1, InvoiceID);
//			ResultSet rs = ps.executeQuery();
//
//
//			while(rs.next()){
//				String invoiceCode = rs.getString("InvoiceCode");
//
//				Member memberCode = getMembers(rs.getString("InvoiceMemberID")) ;
//				Person personalTrainer = getPerson(Integer.parseInt(rs.getString("InvoicePersonID")));
//				String invoiceDate = rs.getString("InvoiceDate");
//				ArrayList<InvoiceProducts> invoiceProducts = getInvoiceProducts(InvoiceID);
//
//				i = new Invoice(invoiceCode, memberCode, personalTrainer, invoiceDate, invoiceProducts);
//			}
//			DBUtility.closeConnection(conn);
//			rs.close();
//			ps.close();
//		}
//		catch (SQLException e) {
//			System.out.println("SQLException: ");
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//
//		return i;
//
//	}

	//Returns a list of all products on an invoice
	private static ArrayList<InvoiceProducts> getInvoiceProductList() {
		Connection conn = DBUtility.connectMeToDatabase();

		
		ArrayList<InvoiceProducts> invoiceProducts = new ArrayList<InvoiceProducts>();
		InvoiceProducts ip = null;
		
		String getAllInvoiceProducts = "SELECT Products.ProductCode, InvoiceProducts.Quantity, Persons.PersonCode FROM Products JOIN InvoiceProducts ON InvoiceProducts.ProductID = Products.ProductID JOIN Invoices ON InvoiceProducts.InvoiceID = Invoices.InvoiceID JOIN Persons ON Invoices.InvoicePersonID = Persons.PersonID";   
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement(getAllInvoiceProducts);
			rs = ps.executeQuery();

			while(rs.next()){
				String productCode = rs.getString("ProductCode");
				int quantity = rs.getInt("Quantity");
				String personCode = rs.getString("PersonCode");

				ip = new InvoiceProducts (productCode, quantity, personCode);
				invoiceProducts.add(ip);
			}

			rs.close();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		DBUtility.closeConnection(conn);

		return invoiceProducts;



	}
	
	//Returns a list of all persons (Don't delete)
	public static List<Person> getPersonList() {
		Connection conn = DBUtility.connectMeToDatabase();

		List<Person> personList = new ArrayList<Person>();
		Person p = null;


		String getAllPersons = "SELECT Persons.PersonID, Persons.PersonCode, Persons.FirstName, Persons.LastName, Address.AddressID, Emails.EmailID FROM Persons JOIN Address ON Persons.PersonAddressID = Address.AddressID JOIN Emails ON Emails.EmailPersonID = Persons.PersonID";
		PreparedStatement ps;
		ResultSet rs;

		try {
			
			ps = conn.prepareStatement(getAllPersons);
			rs = ps.executeQuery();
			

			while (rs.next()) {
				String personCode = rs.getString("PersonCode");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				Address address = getAddress(rs.getInt("AddressID"));
				ArrayList<String> emailList = getEmails(rs.getInt("PersonID"));

				p = new Person (personCode, firstName, lastName, address, emailList);
				personList.add(p);
				
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		DBUtility.closeConnection(conn);

		return personList;


	}
	

	//Returns a list of all members (Don't delete)
	private static List<Member> getMemberList(){
		Connection conn = DBUtility.connectMeToDatabase();

		List<Member> memberList = new ArrayList<Member>();
		Member m = null;


		String getAllMembers = "SELECT Members.MemberCode, Members.MemberType, Persons.PersonCode, Members.MemberName, Address.AddressID FROM Members JOIN Persons ON MemberPersonID = Persons.PersonID JOIN Address ON MemberAddressID = Address.AddressID";
		PreparedStatement ps;
		ResultSet rs;

		try {			
			ps = conn.prepareStatement(getAllMembers);
			rs = ps.executeQuery();
			

			while (rs.next()) {
				String memberCode = rs.getString("MemberCode");
				String memberType = rs.getString("MemberType");
				Person contact = getPerson(rs.getString("PersonCode"));
				String name = rs.getString("MemberName");
				Address address = getAddress(rs.getInt("MemberAddressID"));

				if(memberType.equals("G")){
					m = new General(memberCode, memberType, contact, name, address);
					memberList.add(m);
				}
				else if (memberType.equals("S")){
					m = new Student(memberCode, memberType, contact, name, address);
					memberList.add(m);
				}
			}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		DBUtility.closeConnection(conn);

		return memberList;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private static List<Invoice> getInvoiceList() {
		Connection conn = DBUtility.connectMeToDatabase();
		
		List<Invoice> invoiceList = new ArrayList<Invoice>();
		ArrayList<InvoiceProducts> productList = new ArrayList<InvoiceProducts>();
		
		String getInvoiceInfo = "SELECT Invoices.InvoiceCode, Members.MemberCode, Persons.PersonCode, Invoices.InvoiceDate, Products.ProductCode, InvoiceProducts.Quantity, InvoiceProducts.MembershipID FROM Invoices JOIN Members ON Invoices.InvoiceMemberID = Members.MemberID JOIN Persons ON Invoices.InvoicePersonID = Persons.PersonID JOIN InvoiceProducts ON InvoiceProducts.InvoiceID = Invoices.InvoiceID JOIN Products ON InvoiceProducts.ProductID = Products.ProductID";
		PreparedStatement ps;
		ResultSet rs;
		
		try {			
			ps = conn.prepareStatement(getInvoiceInfo);
			rs = ps.executeQuery();
			

			while (rs.next()) {
				String invoiceCode = rs.getString("InvoiceCode");
				String memberCode = rs.getString("MemberCode");
				String personCode = rs.getString("PersonCode");
				String stringInvoiceDate = rs.getString("InvoiceDate");
				DateTime invoiceDate = DateTime.parse(stringInvoiceDate);
				
				//Iterate? To get all products in the same invoice??
				String productCode = rs.getString("ProductCode");
				int quantity = rs.getInt("Quantity");
				InvoiceProducts ip = new InvoiceProducts(productCode, quantity, personCode);
				productList.add(ip);
				
				
				//After iteration and all products of one invoice are in an array...
				//Create invoice and added to list of invoices
				//Invoice iv = new Invoice(invoiceNumber, m, p, invoiceDate, productList);
				Invoice iv = null;
				invoiceList.add(iv);
				
				
				
				
				}

			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		DBUtility.closeConnection(conn);
		return invoiceList;

	}



	
	
	
	
	
	
	
	public static List<Invoice> createInvoiceList() {
		//Gets a list of all invoices
		List<Invoice> invoiceList = getInvoiceList();
				
		//Gets a list of all members
		List<Member> memberList = getMemberList();
		
		for(int i = 0; i < memberList.size(); i++) {
			if(memberList.get(i).memberCode.equals(m))
		}
			
		//Gets a list of all persons
		List<Person> personList = getPersonList();
			
		//Gets a list of all products in an invoice
		ArrayList<InvoiceProducts> invoiceProductList = getInvoiceProductList();
			
		Invoice iv = new Invoice();
			
		
		return invoiceList;

	}
}


