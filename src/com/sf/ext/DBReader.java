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

		String getAddress = "SELECT * FROM Address WHERE AddressID = ?";

		Connection conn = DBUtility.connectMeToDatabase();
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

			DBUtility.closeConnection(conn);
			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return a;

	}



	//Finds and returns a single person (Don't delete)
	private static Person getPerson(String personCode){
		String getPerson = "SELECT * FROM Persons WHERE PersonCode = ?";

		Person p = null;

		try {
			Connection conn = DBUtility.connectMeToDatabase();
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

			DBUtility.closeConnection(conn);
			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return p;


	}


	//Returns an array list of emails of a specific person (Don't delete)
	private static ArrayList<String> getEmails(int PersonID){
		ArrayList<String> allEmails = new ArrayList<String>();

		String getEmail = "SELECT * FROM Emails WHERE PersonID = ?";

		Connection conn = DBUtility.connectMeToDatabase();
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






	private Invoice getInvoice(int InvoiceID){
		String getInvoice = "SELECT * FROM Invoices WHERE InvoiceCode = ?";
		Invoice i = null;
		try {
			Connection conn = DBUtility.connectMeToDatabase();
			PreparedStatement ps = conn.prepareStatement(getInvoice);
			ps.setInt(1, InvoiceID);
			ResultSet rs = ps.executeQuery();


			while(rs.next()){
				String invoiceCode = rs.getString("InvoiceCode");

				Member memberCode = getMembers(rs.getString("InvoiceMemberID")) ;
				Person personalTrainer = getPerson(Integer.parseInt(rs.getString("InvoicePersonID")));
				String invoiceDate = rs.getString("InvoiceDate");
				ArrayList<InvoiceProducts> invoiceProducts = getInvoiceProducts(InvoiceID);

				i = new Invoice(invoiceCode, memberCode, personalTrainer, invoiceDate, invoiceProducts);
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

		return i;

	}


	private ArrayList<InvoiceProducts> getInvoiceProducts(int InvoiceID){

		ArrayList<InvoiceProducts> invoiceProducts = new ArrayList<InvoiceProducts>();

		String getIProducts = "SELECT Products.ProductCode, InvoiceProducts.Quantity, Persons.PersonCode FROM Invoices JOIN InvoiceProducts ON Invoices.InvoiceID  = InvoiceProducts.InvoiceID JOIN Products ON InvoiceProducts.ProductID = Products.ProductID JOIN Persons ON Invoices.InvoicePersonID = Persons.PersonID";

		try {
			Connection conn = DBUtility.connectMeToDatabase();
			PreparedStatement ps = conn.prepareStatement(getIProducts);
			ps.setInt(1, InvoiceID);
			ResultSet rs = ps.executeQuery();
			InvoiceProducts ip = null;

			while(rs.next()){
				String productCode = rs.getString("ProductCode");
				int quantity = rs.getInt("Quantity");
				String personCode = rs.getString("PersonCode");

				ip = new InvoiceProducts (productCode, quantity, personCode);

				invoiceProducts.add(ip);
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

		return invoiceProducts;



	}
	
	//Returns a list of all persons (Don't delete)
	public static List<Person> getPersonList() {

		List<Person> personList = null;
		Person p = null;


		String getAllPersons = "SELECT Persons.PersonID, Persons.PersonCode, Persons.FirstName, Persons.LastName, Address.AddressID, Emails.EmailID FROM Persons JOIN Address ON Persons.PersonAddressID = Address.AddressID JOIN Emails ON Emails.EmailPersonID = Persons.PersonID";
		PreparedStatement ps;
		ResultSet rs;

		try {
			Connection conn = DBUtility.connectMeToDatabase();
			
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

			DBUtility.closeConnection(conn);
			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return personList;


	}
	

	//Returns a list of all members (Don't delete)
	private static List<Member> getMemberList(){

		List<Member> memberList = null;
		Member m = null;


		String getAllMembers = "SELECT Members.MemberCode, Members.MemberType, Persons.PersonCode, Members.MemberName, Address.AddressID FROM Members JOIN Persons ON MemberPersonID = Persons.PersonID JOIN Address ON MemberAddressID = Address.AddressID";
		PreparedStatement ps;
		ResultSet rs;

		try {
			Connection conn = DBUtility.connectMeToDatabase();
			
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

			DBUtility.closeConnection(conn);
			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return memberList;

	}





	private Product getProducts(String ProductCode) {

		String getProduct = "SELECT * FROM Products WHERE ProductCode = ?";
		Product p = null;

		try {
			Connection conn = DBUtility.connectMeToDatabase();
			PreparedStatement ps = conn.prepareStatement(getProduct);
			ps.setString(1, ProductCode);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				String productCode = rs.getString("ProductCode");
				String productType = rs.getString("ProductType");

				if(productType.equals("D")){
					String stringDate = rs.getString("StartDate");
					DateTime startDate = DateTime.parse(stringDate);
					Address address = getAddress(rs.getInt("AddressID"));
					double cost = rs.getDouble("ProductCost");

					p = new DayMembership (startDate, address, cost, productCode, productType);

				} else if (productType.equals("Y")) {
					String StartDate = rs.getString("StartDate");
					String EndDate = rs.getString("EndDate");
					DateTime startDate = DateTime.parse(StartDate);
					DateTime endDate = DateTime.parse(EndDate);
					Address address = getAddress(rs.getInt("AddressID"));
					String productName = rs.getString("ProductName");
					double cost = rs.getDouble("ProductCost");

					p = new YearMembership (startDate, endDate, address, productName, cost, productCode, productType);		

				} else if(productType.equals("R")) {

					String equipmentName = rs.getString("ProductName");
					double cost = rs.getDouble("ProductCost");

					p = new RentalEquipment(equipmentName, cost, productCode, productType);

				} else if (productType.equals("P")) {
					double cost = rs.getDouble("ProductCost");

					p = new ParkingPass(cost, productCode, productType); 

				}


			}
			DBUtility.closeConnection(conn);
			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return p;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static List<Invoice> createInvoiceList() {
		List<Invoice> invoiceList = null;
			/**
			 * Here we call the member list so that we can access the data
			 */
			
			List<Member> memberList = getMemberList();
			
		
		return invoiceList;

	}
}


