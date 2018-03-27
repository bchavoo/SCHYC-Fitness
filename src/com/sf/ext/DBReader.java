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


	private Address getAddress(int AddressID){

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



	//Finds and returns a person (Don't delete)
	private Person getPerson(String personCode){
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



	private ArrayList<String> getEmails(int PersonID){
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
	
	
	
	

	//We fixed/use this method (Don't delete)
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



	//	private InvoiceProducts getSomething(String invoiceCode){
	//
	//		String query = "SELECT * FROM InvoiceProducts WHERE productCode = ?";
	//
	//		InvoiceProducts ip = null;
	//
	//		try {
	//			Connection conn = DBUtility.connectMeToDatabase();
	//			PreparedStatement ps = conn.prepareStatement(query);
	//			ps.setString(1, productCode);
	//			ResultSet rs = ps.executeQuery();
	//
	//			while (rs.next()) {
	//				Invoice productInvoice = getInvoice(rs.getInt("ProductInvoiceID"));
	//				String type = rs.getString("ProductType");
	//				int quantity = rs.getInt("ProductQuantity");
	//				String personcode = rs.getString("");
	//
	//				ip = new InvoiceProducts(type, quantity, cost);
	//
	//			}
	//			DBUtility.closeConnection(conn);
	//			rs.close();
	//			ps.close();
	//
	//		} catch (SQLException e) {
	//			System.out.println("SQLException: ");
	//			e.printStackTrace();
	//			throw new RuntimeException(e);
	//		}
	//
	//		return ip;
	//
	//	}


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
			/**
			 * Here we call the member list so that we can access the data
			 */
			
			List<Member> memberList = getMemberList();
			
			//Find and store a matching member from the invoice
			for(int i = 0; i < numberOfMembers; i++) {
				if(memberList.get(i).memberCode.equals(memberCode)) {
					m = memberList.get(i);
				}
			}
			
			String personalTrainerCode = token[2];
			
			/**
			 * Here we call the person list so that we can access it's data
			 */
			List<Person> personList = FileReader.createPersonList();
			
			//Find and store Person
			for(int i = 0; i < numberOfPersons; i++) {
				if(personList.get(i).getPersonCode().equals(personalTrainerCode)) {
					p = personList.get(i);
				}
			}
			
			String invoiceDate = token[3];
			String productCode = "";
			String personCode = "";
			
			String tokenProducts[] = token[4].split(",");
			
			/**
			 * Here we create an array list called invoiceProductArray that will contain
			 * the products in that invoice
			 */
			ArrayList<InvoiceProducts> invoiceProductArray = new ArrayList<InvoiceProducts>();
			
			/**
			 * Here we use a for loop to loop the token products length to find if 
			 * it is 2 or 3 for the last part of the token to decide the if we will have 
			 * a related membership tied to the product so we can store it or
			 * if we have no extra data and can ignore it.
			 * We then store all of it into an Array of InvoiceProducts
			 */
			for(int i = 0; i < tokenProducts.length; i++) {
				int quantity = 0;
				String tokenType[] = tokenProducts[i].split(":");
					if(tokenType.length == 2) {
						productCode = tokenType[0];
						quantity = Integer.parseInt(tokenType[1]);
						personCode = "";
						
						InvoiceProducts ip = new InvoiceProducts(productCode, quantity, personCode);
						invoiceProductArray.add(ip);
						
					} else if (tokenType.length == 3) {
						productCode = tokenType[0];
						quantity = Integer.parseInt(tokenType[1]);
						personCode = tokenType[2];
						
						InvoiceProducts ip = new InvoiceProducts(productCode, quantity, personCode);
						invoiceProductArray.add(ip);
					}
			}

            /**
             * At the end we create a new invoice with all the
             * information we gathered from the invoice file into an Invoice object
             * and add the object to a list of Invoices
             */
			Invoice v = new Invoice(invoiceNumber, m, p, invoiceDate, invoiceProductArray);

			invoiceList.add(v);
		}
		return invoiceList;
	}
	}



	//	private DayMembership getDayMembership(String ProductType) {
	//
	//		//public DayMembership(DateTime startDate, Address address, double cost, String productCode, String productType) {
	//
	//		String query = "SELECT Products.StartDate, Products.AdressID, Products.ProductCost, Products.ProductCode, Products.ProductType FROM Products WHERE ProductType = ?";
	//
	//		DayMembership dm = null;
	//
	//		try {
	//			Connection conn = DBUtility.connectMeToDatabase();
	//			PreparedStatement ps = conn.prepareStatement(query);
	//			ps.setString(1, ProductType);
	//			ResultSet rs = ps.executeQuery();
	//
	//			while(rs.next()) {
	//
	//				String stringDate = rs.getString("StartDate");
	//				DateTime startDate = DateTime.parse(stringDate);
	//				Address address = getAddress(rs.getInt("AddressID"));
	//				double cost = rs.getDouble("Cost");
	//				String productCode = rs.getString("ProductCode");
	//				String productType = rs.getString("ProductType");
	//
	//				dm = new DayMembership(startDate, address, cost, productCode, productType);
	//			}
	//			DBUtility.closeConnection(conn);
	//			rs.close();
	//			ps.close();
	//
	//		} catch (SQLException e) {
	//			System.out.println("SQLException: ");
	//			e.printStackTrace();
	//			throw new RuntimeException(e);
	//		}
	//
	//		return dm;
	//
	//	}
	//
	//
	//	private YearMembership getYearMembership(String ProductType) {
	//
	//		String query = "SELECT FROM Products WHERE ProductType = ?";
	//
	//		YearMembership ym = null;
	//
	//		try {
	//			Connection conn = DBUtility.connectMeToDatabase();
	//			PreparedStatement ps = conn.prepareStatement(query);
	//			ps.setString(1, ProductType);
	//			ResultSet rs = ps.executeQuery();
	//
	//			while(rs.next()) {
	//				String StartDate = rs.getString("StartDate");
	//				String EndDate = rs.getString("EndDate");
	//				DateTime startDate = DateTime.parse(StartDate);
	//				DateTime endDate = DateTime.parse(EndDate);
	//				Address address = getAddress(rs.getInt("AddressID"));
	//				String membershipName = rs.getString("ProductName");
	//				double cost = rs.getDouble("Cost");
	//				String productCode = rs.getString("ProductCode");
	//				String productType = rs.getString("ProductType");
	//
	//				ym = new YearMembership(startDate, endDate, address, membershipName, cost, productCode, productType);
	//			}
	//			DBUtility.closeConnection(conn);
	//			rs.close();
	//			ps.close();
	//
	//		} catch (SQLException e) {
	//			System.out.println("SQLException: ");
	//			e.printStackTrace();
	//			throw new RuntimeException(e);
	//		}
	//
	//		return ym;
	//
	//	}

}
//	private DayMembership getDayMemberships(int productCode){
//		
//		//(DateTime startDate, Address address, double cost, String productCode, String productType) {
//			
//		String query = "SELECT DayMemberships.StartDate, Address.Street, Address.City, Address.State, Address";
//		
//			DayMembership dm = null;
//			
//			try {
//				Connection conn = DBUtility.connectMeToDatabase();
//				PreparedStatement ps = conn.prepareStatement(query);
//				ps.setInt(1, DayMembershipID);
//				ResultSet rs = ps.executeQuery();
//				
//	 
//				while (rs.next()) {
//					//Invoice productInvoice = getInvoice(rs.getString("ProductInvoiceID"));
//					Address address = getAddress(rs.get);
//				    String productCode = rs.getString("DayMembershipInvoiceProductID");
//					double cost = rs.getDouble("Cost");
//					String productType = rs.getString("productType");
//					dm = new DayMemberships(startDate, address, cost, productCode, productType);
//				
//			}
//
//			DBUtility.closeConnection(conn);
//			rs.close();
//			ps.close();
//
//		} catch (SQLException e) {
//			System.out.println("SQLException: ");
//			e.printStackTrace();
//			throw new RuntimeException(e);
//		}
//				
//			return dm;
//			
//		}
//			
//		
//private YearMembership getYearMemberships(int YearMembershipID){
//	
//	String query = "SELECT * FROM YearMemberships WHERE YearMembershipID = ?";
//	
//		YearMembership ym = null;
//		
//		try {
//			Connection conn = DBUtility.connectMeToDatabase();
//			PreparedStatement ps = conn.prepareStatement(query);
//			ps.setInt(1, YearMembershipID);
//			ResultSet rs = ps.executeQuery();
// 
//			while (rs.next()) {
//				//Invoice productInvoice = getInvoice(rs.getString("ProductInvoiceID"));
//				DateTime startDate = rs.getDate("StartDate");
//				Address address = getAddress(rs.get);
//				DateTime endDate = rs.getDate("EndDate");
//				String memberShipName =
//				
//				ym = new YearMembership(startDate, endDate, address);
//			
//		}
//			
//		DBUtility.closeConnection(conn);
//		rs.close();
//		ps.close();
//
//	} catch (SQLException e) {
//		System.out.println("SQLException: ");
//		e.printStackTrace();
//		throw new RuntimeException(e);
//	}
//			
//		return ym;
//		
//	}
//		
//	
//
//private RentalEquipment getEquipment(int EquipmentID){
//	
//	String query = "SELECT * FROM RentalEquipment WHERE EquipmentID = ?";
//	
//		DayMembership ym = null;
//		
//		try {
//			Connection conn = DBUtility.connectMeToDatabase();
//			PreparedStatement ps = conn.prepareStatement(query);
//			ps.setInt(1, EquipmentID);
//			ResultSet rs = ps.executeQuery();
// 
//			while (rs.next()) {
//				//Invoice productInvoice = getInvoice(rs.getString("ProductInvoiceID"));
//				Date startDate = rs.getDate("StartDate");
//				Address address = getAddress(rs.get);
//				Date endDate = rs.getDate("EndDate");
//				
//				ym = new InvoiceProducts();
//			
//		}
//			
//		DBUtility.closeConnection(conn);
//		rs.close();
//		ps.close();
//
//	} catch (SQLException e) {
//		System.out.println("SQLException: ");
//		e.printStackTrace();
//		throw new RuntimeException(e);
//	}
//			
//		return ym;
//		
//	}
//		
//	}
//
//
//
//	
//	
//
//	
