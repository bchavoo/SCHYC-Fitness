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

	public static int counter = 0;


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
				Address address = getAddress(rs.getInt("PersonAddressID"));
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

		String getEmail = "SELECT * FROM Emails WHERE EmailPersonID = ?";

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



	//Returns a list of all products on an invoice
	private static List<InvoiceProducts> getProductListOfAnInvoice() {

		Connection conn = DBUtility.connectMeToDatabase();
		counter++;


		List<InvoiceProducts> productList = new ArrayList<InvoiceProducts>();

		PreparedStatement ps;
		ResultSet rs;

		try {
			String getAllInvoiceProductsWithMemberships = "SELECT Products.ProductCode, InvoiceProducts.Quantity, InvoiceProducts.MembershipID FROM InvoiceProducts JOIN Products ON InvoiceProducts.ProductID = Products.ProductID JOIN Invoices ON Invoices.InvoiceID = InvoiceProducts.InvoiceID AND Invoices.InvoiceID = ? AND MembershipID IS NOT NULL";
			ps = conn.prepareStatement(getAllInvoiceProductsWithMemberships);
			ps.setInt(1, counter);
			rs = ps.executeQuery();

			while (rs.next()) {
				String productCode = rs.getString("ProductCode");
				int quantity = rs.getInt("Quantity");
				int MembershipID = rs.getInt("MembershipID");

				PreparedStatement ps1;
				ResultSet rs1;
				String findDiscountCode = "SELECT Products.ProductCode FROM Products WHERE Products.ProductID = ?";
				ps1 = conn.prepareStatement(findDiscountCode);
				ps1.setInt(1, MembershipID);
				rs1 = ps1.executeQuery();

				while(rs1.next()) {
					String discountCode = rs1.getString("ProductCode");
					InvoiceProducts ip = new InvoiceProducts(productCode, quantity, discountCode);
					productList.add(ip);

				}

				rs1.close();
				ps1.close();
			}
			rs.close();
			ps.close();


			String getAllInvoiceProductsNoMemberships = "SELECT Products.ProductCode, InvoiceProducts.Quantity, InvoiceProducts.MembershipID FROM InvoiceProducts JOIN Products ON InvoiceProducts.ProductID = Products.ProductID JOIN Invoices ON Invoices.InvoiceID = InvoiceProducts.InvoiceID AND Invoices.InvoiceID = ? AND MembershipID IS NULL";
			ps = conn.prepareStatement(getAllInvoiceProductsNoMemberships);
			ps.setInt(1, counter);
			rs = ps.executeQuery();

			while(rs.next()) {
				String productCode = rs.getString("ProductCode");
				int quantity = rs.getInt("Quantity");
				String discountCode = "";

				InvoiceProducts ip = new InvoiceProducts(productCode, quantity, discountCode);
				productList.add(ip);

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

		return productList;

	}


	public static List<Product> getAllProducts() {
		Connection conn = DBUtility.connectMeToDatabase();


		List<Product> productList = new ArrayList<Product>();

		PreparedStatement ps;
		ResultSet rs;

		try {
			//ps = conn.prepareStatement(getAllInvoiceProducts);
			//rs = ps.executeQuery();

			String getAllInformation = "SELECT * FROM InvoiceProducts JOIN Products ON InvoiceProducts.ProductID = Products.ProductID";
			ps = conn.prepareStatement(getAllInformation);
			rs = ps.executeQuery();

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

					DateTimeFormatter date = DateTimeFormat.forPattern("yyyy-MM-dd");
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

			rs.close();
			ps.close();
		}
		catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		DBUtility.closeConnection(conn);

		return productList;
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
				
				//Need to account for more than 1 email
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
				Address address = getAddress(rs.getInt("AddressID"));

				if(memberType.equals("General")){
					m = new General(memberCode, memberType, contact, name, address);
					memberList.add(m);
				}
				else if (memberType.equals("Student")){
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




	public static ArrayList<InvoiceProducts> getInvoiceProductList(String invoiceCode) {

		Connection conn = DBUtility.connectMeToDatabase();

		String getInvoiceInfo = "SELECT Products.ProductCode, InvoiceProducts.Quantity, InvoiceProducts.MembershipID FROM Invoices JOIN Members ON Invoices.InvoiceMemberID = Members.MemberID JOIN Persons ON Invoices.InvoicePersonID = Persons.PersonID JOIN InvoiceProducts ON InvoiceProducts.InvoiceID = Invoices.InvoiceID JOIN Products ON InvoiceProducts.ProductID = Products.ProductID WHERE Invoices.InvoiceCode = ?";
		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement(getInvoiceInfo);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();

			ArrayList<InvoiceProducts> productOnInvoiceList = new ArrayList<InvoiceProducts>();

			while(rs.next()) {

				String productCode = rs.getString("ProductCode");
				int quantity = rs.getInt("Quantity");
				int productID = rs.getInt("MembershipID");

				List<Product> allProducts = getAllProducts();

				for(int i = 0; i < allProducts.size(); i++) {
					if(allProducts.get(i).getProductCode().equals(productCode)) {
						String discountCode = "";
						
						if(productID != 0) {
							discountCode = allProducts.get(i).getProductCode();
						} else {
							discountCode = "";
						}


						InvoiceProducts ip = new InvoiceProducts(productCode, quantity, discountCode);
						productOnInvoiceList.add(ip);
					}
				}
			} 

			rs.close();
			ps.close();
			DBUtility.closeConnection(conn);

			return productOnInvoiceList;

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		} 


	}




	public static List<Invoice> createInvoiceList() {	
		Connection conn = DBUtility.connectMeToDatabase();

		String getInvoiceInfoNoRepeat = "SELECT Invoices.InvoiceCode, Members.MemberCode, Persons.PersonCode, Invoices.InvoiceDate FROM Invoices JOIN Members ON Invoices.InvoiceMemberID = Members.MemberID JOIN Persons ON Invoices.InvoicePersonID = Persons.PersonID JOIN InvoiceProducts ON InvoiceProducts.InvoiceID = Invoices.InvoiceID JOIN Products ON InvoiceProducts.ProductID = Products.ProductID GROUP BY Invoices.InvoiceCode";

		List<Invoice> invoiceList = new ArrayList<Invoice>();

		PreparedStatement ps;
		ResultSet rs;

		try {
			ps = conn.prepareStatement(getInvoiceInfoNoRepeat);
			rs = ps.executeQuery();

			while(rs.next()) {
				Member m = null;
				Person p = null;

				String invoiceCode = rs.getString("InvoiceCode");
				String memberCode = rs.getString("MemberCode");
				
				
				List<Member> memberList = getMemberList();
	
				for(int i = 0; i < memberList.size(); i++) {
					if(memberList.get(i).memberCode.equals(memberCode)) {
						m = memberList.get(i);
					}
				}

				String personalTrainerCode = rs.getString("PersonCode");

				List<Person> personList = getPersonList();

				for(int i = 0; i < personList.size(); i++) {
					if(personList.get(i).getPersonCode().equals(personalTrainerCode)) {
						p = personList.get(i);
					}
				}

				String invoiceDate = rs.getString("InvoiceDate");

				ArrayList<InvoiceProducts> productOnInvoiceList = getInvoiceProductList(invoiceCode);

				Invoice v = new Invoice(invoiceCode, m, p, invoiceDate, productOnInvoiceList);
				invoiceList.add(v);

			}

			ps.close();
			rs.close();
			DBUtility.closeConnection(conn); 

			return invoiceList;

		} catch (SQLException e) {
			System.out.println("SQLException: ");
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	} 
}



