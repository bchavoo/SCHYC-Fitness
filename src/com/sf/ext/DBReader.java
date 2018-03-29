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
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

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

	//Method to get a list of all persons in database
	public static List<Person> getPersonList() {
		//Connect to database
		Connection conn = DBUtility.connectMeToDatabase();

		
		/**
		 * We than create a type list of persons so that we can put the person info into 
		 * an object and eventually add it to the personlist.
		 */
		
		List<Person> personList = new ArrayList<Person>();

		//Query to find all Persons in database
		String getAllPersons = "SELECT Persons.PersonID, Persons.PersonCode, Persons.FirstName, Persons.LastName, Address.AddressID FROM Persons JOIN Address ON Persons.PersonAddressID = Address.AddressID GROUP BY PersonID";
		PreparedStatement ps;
		ResultSet rs;

		try {
			//Create and execute query
			ps = conn.prepareStatement(getAllPersons);
			rs = ps.executeQuery();

			//While the query returns information we will store the values
			while (rs.next()) {
				int personEmailID = rs.getInt("PersonID");
				String personCode = rs.getString("PersonCode");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				int addressID = rs.getInt("AddressID");
				Address address = getAddress(addressID);

				ArrayList<String> emailList = getEmails(personEmailID);
				
				//Using the values we get we create a person object, and add to a list
				Person p = new Person (personCode, firstName, lastName, address, emailList);
				personList.add(p);

			}
			
			//After we are done we close the prepared statement and result set
			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		//Close the connection to the database
		DBUtility.closeConnection(conn);
		
		//Return the list of persons
		return personList;


	}






	//Method to get a list of all Members in database
	private static List<Member> getMemberList(){
		//Connect to database
		Connection conn = DBUtility.connectMeToDatabase();

		/**
		 * Here we create a memberlist of type member and list so that we can store 
		 * a member object that will have the information we need to add to the list 
		 * and eventually use
		 */
		
		List<Member> memberList = new ArrayList<Member>();

		//Query to find all Members in the database
		String getAllMembers = "SELECT Members.MemberCode, Members.MemberType, Persons.PersonCode, Members.MemberName, Address.AddressID FROM Members JOIN Persons ON MemberPersonID = Persons.PersonID JOIN Address ON Members.MemberAddressID = Address.AddressID";
		PreparedStatement ps;
		ResultSet rs;

		try {	
			//Create and execute query
			ps = conn.prepareStatement(getAllMembers);
			rs = ps.executeQuery();

			//While the query returns something, we get information and store it
			while (rs.next()) {

				String memberCode = rs.getString("MemberCode");
				String memberType = rs.getString("MemberType");
				String personCode = rs.getString("PersonCode");
				String memberName = rs.getString("MemberName");
				int addressID = rs.getInt("AddressID");
				Address address = getAddress(addressID);

				//Using the personCode from the query, we find and get a person object with that code
				Person match = getPerson(personCode);

				//Create an appropriate type of Member and add to a list of Members
				if(memberType.equals("General")){
					Member m = new General(memberCode, memberType, match, memberName, address);
					memberList.add(m);
				}
				else if (memberType.equals("Student")){
					Member m = new Student(memberCode, memberType, match, memberName, address);
					memberList.add(m);

				}
			}

			//After we're done close the prepared statement and result set
			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		//Close connection to database
		DBUtility.closeConnection(conn);

		//Return the list of members created
		return memberList;
	}





	//Method to get a list of all the Products(sold/offered) in database
	public static List<Product> getAllProducts() {
		//Connect to database
		Connection conn = DBUtility.connectMeToDatabase();

		/** 
		 * Create a product list that will include objects created to receive
		 * the info from each product
		 */

		List<Product> productList = new ArrayList<Product>();
		
		//Query to get all the products(not invoice products) in the database
		String getAllInformation = "SELECT * FROM InvoiceProducts JOIN Products ON InvoiceProducts.ProductID = Products.ProductID";
		PreparedStatement ps;
		ResultSet rs;

		try {
			//Create and execute query
			ps = conn.prepareStatement(getAllInformation);
			rs = ps.executeQuery();

			//While the database returns something we will determine the specific product
			//and create an object of that product type and then add to a list of Products
			while (rs.next()) {
				if (rs.getString("ProductType").equals("R")) {
					String productCode = rs.getString("ProductCode");
					String productType = rs.getString("ProductType");
					String productName = rs.getString("ProductName");
					double cost = rs.getDouble("ProductCost");

					Product product = new RentalEquipment(productName, cost, productCode, productType);
					productList.add(product);

				} else if (rs.getString("ProductType").equals("P")) {
					String productCode = rs.getString("ProductCode");
					String productType = rs.getString("ProductType");
					double cost = rs.getDouble("ProductCost");

					Product product = new ParkingPass(cost, productCode, productType);
					productList.add(product);

				} else if (rs.getString("ProductType").equals("Y")) {
					String productCode = rs.getString("ProductCode");
					String productType = rs.getString("ProductType");

					DateTimeFormatter date = DateTimeFormat.forPattern("yyyy-MM-dd");
					DateTime startDate = date.parseDateTime(rs.getString("StartDate"));
					DateTime endDate = date.parseDateTime(rs.getString("EndDate"));

					Address a = getAddress(rs.getInt("AddressID"));
					String street = a.getStreet();
					String city = a.getCity();
					String state = a.getState();
					String zip = a.getZip();
					String country = a.getCountry();
					Address address = new Address (street, city, state, zip, country);

					String productName = rs.getString("ProductName");
					Double cost = rs.getDouble("ProductCost");

					Product product = new YearMembership(startDate, endDate, address, productName, cost, productCode, productType);
					productList.add(product);

				} else if (rs.getString("ProductType").equals("D")) {
					String productCode = rs.getString("ProductCode");
					String productType = rs.getString("ProductType");

					DateTimeFormatter date = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
					DateTime startDate = date.parseDateTime(rs.getString("StartDate"));

					Address a = getAddress(rs.getInt("AddressID"));
					String street = a.getStreet();
					String city = a.getCity();
					String state = a.getState();
					String zip = a.getZip();
					String country = a.getCountry();
					Address address = new Address (street, city, state, zip, country);

					double cost = rs.getDouble("ProductCost");
					Product product = new DayMembership(startDate, address, cost, productCode, productType);
					productList.add(product);
				}
			}

			//After we are done, close the prepared statement and result set
			rs.close();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		//Close connection to database
		DBUtility.closeConnection(conn);
		
		//Return the list of all the products created
		return productList;
	}


	//Method to get an address object of a specified AddressID
	private static Address getAddress(int AddressID){
		//Open connection to database
		Connection conn = DBUtility.connectMeToDatabase();

		//Query to find an address at a specific AddressID
		String getAddress = "SELECT * FROM Address WHERE AddressID = ?";

		PreparedStatement ps;
		ResultSet rs;
		Address a = null;

		try {
			//Create and execute query
			ps = conn.prepareStatement(getAddress);
			ps.setInt(1, AddressID);
			rs = ps.executeQuery();

			//Gather the data needed and create an address object
			while (rs.next()) {
				String street = rs.getString("Street");
				String city = rs.getString("City");
				String state = rs.getString("State");
				String zip = rs.getString("Zip");
				String country = rs.getString("Country");

				a = new Address(street, city, state, zip, country);

			}

			//Close the prepared statement and result set
			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		//Close connection to database
		DBUtility.closeConnection(conn);

		//Return the address object we created
		return a;

	}



	//Method that returns an array list of emails of a specific PersonID
	private static ArrayList<String> getEmails(int PersonID){
		Connection conn = DBUtility.connectMeToDatabase();

		ArrayList<String> allEmails = new ArrayList<String>();

		//Query to find all the emails of a specific Person
		String getEmail = "SELECT * FROM Emails WHERE EmailPersonID = ?";

		PreparedStatement ps;
		ResultSet rs;


		try {
			//Prepare and execute query
			ps = conn.prepareStatement(getEmail);
			ps.setInt(1, PersonID);
			rs = ps.executeQuery();


			while(rs.next()){		
				String email = rs.getString("Email");
				allEmails.add(email);
			}

			//Close connection to database
			DBUtility.closeConnection(conn);
			
			//Close prepared statement and result set
			rs.close();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}


		//Return the list of emails
		return allEmails;
	}


	//Method that finds and returns a person object of specificed personCode
	private static Person getPerson(String personCode){
		//Open connection to database
		Connection conn = DBUtility.connectMeToDatabase();

		//Query to find a specific Person
		String getPerson = "SELECT * FROM Persons WHERE PersonCode = ?";

		Person p = null;

		try {
			//Prepare and execute query
			PreparedStatement ps = conn.prepareStatement(getPerson);
			ps.setString(1, personCode);
			ResultSet rs = ps.executeQuery();

			//While the query returns something, we get the information we need and create a Person object
			while (rs.next()) {
				int personID = rs.getInt("PersonID");
				String firstName = rs.getString("FirstName");
				String lastName = rs.getString("LastName");
				int addressID = rs.getInt("PersonAddressID");
				Address address = getAddress(addressID);

				ArrayList<String> email = getEmails(personID);

				p = new Person(personCode, firstName, lastName, address, email);

			}

			//Close prepared statement and result set
			rs.close();
			ps.close();

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		//Close connection to database
		DBUtility.closeConnection(conn);

		//Return the person object created and found
		return p;
	}

	//Method that gets all the products that are in a specific invoice
	public static ArrayList<InvoiceProducts> getInvoiceProductList(String invoiceCode) {

		//Open connection to database
		Connection conn = DBUtility.connectMeToDatabase();

		//Query to get information on products on a specific invoice
		String getInvoiceInfo = "SELECT Products.ProductCode, InvoiceProducts.Quantity, InvoiceProducts.MembershipID FROM Invoices JOIN Members ON Invoices.InvoiceMemberID = Members.MemberID JOIN Persons ON Invoices.InvoicePersonID = Persons.PersonID JOIN InvoiceProducts ON InvoiceProducts.InvoiceID = Invoices.InvoiceID JOIN Products ON InvoiceProducts.ProductID = Products.ProductID WHERE Invoices.InvoiceCode = ?";
		PreparedStatement ps;
		ResultSet rs;

		try {
			//Prepare and execute query
			ps = conn.prepareStatement(getInvoiceInfo);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();

			ArrayList<InvoiceProducts> productOnInvoiceList = new ArrayList<InvoiceProducts>();

			//While the query returns information, we will store and create objects
			while(rs.next()) {

				String productCode = rs.getString("ProductCode");
				int quantity = rs.getInt("Quantity");
				int productID = rs.getInt("MembershipID");
				String discountCode = "";

				//If the productID which holds a productID of a ParkingPass or RentalEquipment
				//is not 0, then there is a productID which will entitle the product to a discount
				if(productID != 0) {
					String findDiscountID = "SELECT ProductCode FROM Products WHERE ProductID = ?";
					PreparedStatement ps1 = conn.prepareStatement(findDiscountID);
					ps1.setInt(1, productID);
					ResultSet rs1 = ps1.executeQuery();
					while(rs1.next()) {
						discountCode = rs1.getString("ProductCode");
					}
					ps1.close();
					rs1.close();
				} else {
					discountCode = "";
				}

				//Create an InvoiceProduct object and add to list
				InvoiceProducts ip = new InvoiceProducts(productCode, quantity, discountCode);
				productOnInvoiceList.add(ip);

				

			} 
			//Close prepared statement and result set
			rs.close();
			ps.close();
			
			//Close connection to database
			DBUtility.closeConnection(conn);

			//Return the list of InvoiceProducts (products on an invoice)
			return productOnInvoiceList;

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} 


	}

	//Method that creates and returns a list of invoices from given data
	public static List<Invoice> createInvoiceList() {	
		//Open connection to database
		Connection conn = DBUtility.connectMeToDatabase();

		//Query to get information of all the invoices
		String getInvoiceInfo = "SELECT Invoices.InvoiceCode, Members.MemberCode, Persons.PersonCode, Invoices.InvoiceDate FROM Invoices JOIN Members ON Invoices.InvoiceMemberID = Members.MemberID JOIN Persons ON Invoices.InvoicePersonID = Persons.PersonID JOIN InvoiceProducts ON InvoiceProducts.InvoiceID = Invoices.InvoiceID JOIN Products ON InvoiceProducts.ProductID = Products.ProductID GROUP BY Invoices.InvoiceCode";

		List<Invoice> invoiceList = new ArrayList<Invoice>();

		PreparedStatement ps;
		ResultSet rs;

		try {
			//Prepare and execute query
			ps = conn.prepareStatement(getInvoiceInfo);
			rs = ps.executeQuery();

			//While the query has information, we will store and create invoices
			while(rs.next()) {
				Member m = null;
				Person p = null;

				String invoiceCode = rs.getString("InvoiceCode");
				String memberCode = rs.getString("MemberCode");

				//Get the list of Members by calling the respective method
				List<Member> memberList = getMemberList();
				
				//Search and find a member of specific MemberCode and store that Member
				for(int i = 0; i < memberList.size(); i++) {
					if(memberList.get(i).memberCode.equals(memberCode)) {
						m = memberList.get(i);
					}
				}

				String personalTrainerCode = rs.getString("PersonCode");

				//Get the list of Persons by calling the respective method
				List<Person> personList = getPersonList();

				//Search and find a Person of specific PersonCode and store that Person
				for(int i = 0; i < personList.size(); i++) {
					if(personList.get(i).getPersonCode().equals(personalTrainerCode)) {
						p = personList.get(i);
					}
				}

				String invoiceDate = rs.getString("InvoiceDate");

				//Get the list of products on an invoice by called the specific method
				ArrayList<InvoiceProducts> productOnInvoiceList = getInvoiceProductList(invoiceCode);
				
				//Create an invoce object of all the information found and add to a list of invoices
				Invoice v = new Invoice(invoiceCode, m, p, invoiceDate, productOnInvoiceList);
				invoiceList.add(v);

			}

			//Close the prepared statement and result set
			ps.close();
			rs.close();
			
			//Close connection to database
			DBUtility.closeConnection(conn); 

			//Return the list of invoices
			return invoiceList;

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	} 
}



