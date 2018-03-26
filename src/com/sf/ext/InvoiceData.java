package com.sf.ext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/*
 * This is a collection of utility methods that define a general API for
 * interacting with the database supporting this application.
 * 16 methods in total, add more if required.
 * Do not change any method signatures or the package name.
 * 
 */

import product.DayMembership;
import entities.Invoice;
import entities.Member;
import product.Membership;
import product.ParkingPass;
import entities.Person;
import product.Product;
import product.RentalEquipment;
import product.YearMembership;

public class InvoiceData {

	/**
	 * 1. Method that removes every person record from the database
	 */
	public static void removeAllPersons() {
		/** TODO*/
		/**
		 *  Does this delete all the data from the database? To leave every table empty?
		 *  Because if we delete all people, members have a contact info so they no longer have that,
		 *  if we no longer have that member contact info then who is buying the products?
		 *  I thought that a person was buying the product or am I still confused on that part
		 */
		Connection conn = DBUtility.connectMeToDatabase();

		try {
			removeAllMembers();

			String removeAllEmails = "DELETE FROM Emails";
			conn.createStatement().executeUpdate(removeAllEmails);

			String removeAllPersons = "DELETE FROM Persons";
			conn.createStatement().executeUpdate(removeAllPersons);

			String removeAllAddress = "DELETE FROM Address";
			conn.createStatement().executeUpdate(removeAllAddress);

			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);
	}

	/**
	 * 2. Method to add a person record to the database with the provided data.
	 * 
	 * @param personCode
	 * @param firstName
	 * @param lastName
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addPerson(String personCode, String firstName, String lastName, String street, String city, String state, String zip, String country) {
		/** TODO*/
		Connection conn = DBUtility.connectMeToDatabase();
		PreparedStatement ps;
		ResultSet rs;

		try {
			String addAddress = "INSERT INTO Address (Street, City, State, Zip, Country) VALUES (?, ?, ?, ?, ?)";

			ps = conn.prepareStatement(addAddress);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			ps.executeUpdate();
			ps.close();

			//Find address? Using input you just used? OR is there an easier way
			String findAddressID = "SELECT AdressID FROM Address WHERE Street = ? AND City = ? AND State = ?";
			ps = conn.prepareStatement(findAddressID);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			rs = ps.executeQuery();
			int addressID = Integer.parseInt(rs.getString("AddressID"));
			ps.close();
			rs.close();


			String addPerson = "INSERT INTO Persons (PersonCode, FirstName, LastName, PersonAddressID) VALUES (?, ?, ?, ?)";

			ps = conn.prepareStatement(addPerson);
			ps.setString(1, personCode);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setInt(4, addressID);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);

	}

	/**
	 * 3. Adds an email record corresponding person record corresponding to the
	 * provided <code>personCode</code>
	 * 
	 * @param personCode
	 * @param email
	 */
	public static void addEmail(String personCode, String email) {		
		/** TODO*/
		Connection conn = DBUtility.connectMeToDatabase();
		PreparedStatement ps;
		ResultSet rs;

		try {
			String findPerson = "SELECT PersonID FROM Persons WHERE PersonCode = ?";
			ps = conn.prepareStatement(findPerson);
			ps.setString(1, personCode);
			rs = ps.executeQuery();
			int personID = Integer.parseInt(rs.getString("PersonID"));
			ps.close();
			rs.close();

			String addEmail = "INSERT INTO Emails (EmailPersonID, Email) VALUES (?, ?)";

			ps = conn.prepareStatement(addEmail);
			ps.setInt(1, personID);
			ps.setString(2, email);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);


	}

	/**
	 * 4. Method that removes every member record from the database
	 */
	public static void removeAllMembers() {
		/** TODO*/
		/**
		 * If we delete all members that means there is no invoices? I thought that members are the ones
		 * who create and do the invoices, they sell the products, so if we have no members,
		 * we can't have any invoices either?
		 */
		Connection conn = DBUtility.connectMeToDatabase();

		try {
			removeAllInvoices();

			String removeAllMembers = "DELETE FROM Members";
			conn.createStatement().executeUpdate(removeAllMembers);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);

	}
	/**
	 * 5. Method to add a member record to the database with the provided data
	 * @param memberCode
	 * @param memberType
	 * @param primaryContactPersonCode
	 * @param name
	 * @param street
	 * @param city
	 * @param state
	 * @param zip
	 * @param country
	 */
	public static void addMember(String memberCode, String memberType, String primaryContactPersonCode,String name, String street, String city, String state, String zip, String country) {
		/** TODO*/
		Connection conn = DBUtility.connectMeToDatabase();
		PreparedStatement ps;
		ResultSet rs;

		try {
			String findPersonID = "SELECT PersonID FROM Persons WHERE PersonCode = ?";
			ps = conn.prepareStatement(findPersonID);
			ps.setString(1, primaryContactPersonCode);
			rs = ps.executeQuery();
			int personID = Integer.parseInt(rs.getString("PersonID"));
			ps.close();
			rs.close();
			
			String addAddress = "INSERT INTO Address (Street, City, State, Zip, Country) VALUES (?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(addAddress);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			ps.executeUpdate();
			ps.close();
			
			//Find the AddressID from the address just inserted into the data, is there an easy way
			//to do this or do I have to write another query to find it
			String findAddressID = "SELECT AdressID FROM Address WHERE Street = ? AND City = ? AND State = ?";
			ps = conn.prepareStatement(findAddressID);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			rs = ps.executeQuery();
			int addressID = rs.getInt("AddressID");
			ps.close();
			rs.close();
			
			
			String addMember = "INSERT INTO Members (MemberCode, MemberType, MemberPersonID, MemberName, MemberAddressID) VALUES (?, ?, ?, ?, ?)";
			
			ps = conn.prepareStatement(addMember);
			ps.setString(1, memberCode);
			ps.setString(2, memberType);
			ps.setInt(3, personID);
			ps.setString(4, name);
			ps.setInt(5, addressID);
			ps.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);
		
		
	}

	/**
	 * 6. Removes all product records from the database
	 */
	public static void removeAllProducts() {
		/** TODO*/
		
		Connection conn = DBUtility.connectMeToDatabase();

		try {
			String removeAllParking = "DELETE FROM ParkingPasses";
			conn.createStatement().executeUpdate(removeAllParking);

			String removeAllRental = "DELETE FROM RentalEquipments";
			conn.createStatement().executeUpdate(removeAllRental);

			String removeAllDay = "DELETE FROM DayMemberships";
			conn.createStatement().executeUpdate(removeAllDay);

			String removeAllYear = "DELETE FROM YearMemberships";
			conn.createStatement().executeUpdate(removeAllYear);

			String removeAllProducts = "DELETE FROM InvoiceProducts";
			conn.createStatement().executeUpdate(removeAllProducts);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBUtility.closeConnection(conn);
	}

	/**
	 * 7. Adds a day-pass record to the database with the provided data.
	 */
	public static void addDayPass(String productCode, String dateTime, String street, String city, String state, String zip, String country, double pricePerUnit) {
		/** TODO*/
		
		Connection conn = DBUtility.connectMeToDatabase();

		try {
			String addDayPay = "INSERT INTO InvoiceProducts (ProductInvoiceID, ProductCode, ProductType, ProductQuantity, ProductCost) VALUES (?, ?, ?, ?, ?)";

		

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		DBUtility.closeConnection(conn);
	}

	/**
	 * 8. Adds a year-long-pass record to the database with the provided data.
	 */
	public static void addYearPass(String productCode, String StartDate, String EndDate,String street, String city, String state, String zip, String country, String name, double pricePerUnit) {
		/** TODO*/
	}

	/**
	 * 9. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingFee) {
		/** TODO*/
	}

	/**
	 * 10. Adds an equipment rental record to the database with the provided data.
	 */
	public static void addRental(String productCode, String name, double cost) {
		/** TODO*/
	}

	/**
	 * 11. Removes all invoice records from the database
	 */
	public static void removeAllInvoices() {
		/** TODO*/
		
		Connection conn = DBUtility.connectMeToDatabase();

		try {
			
			removeAllProducts();

			String removeAllInvoices = "DELETE FROM Invoices";
			conn.createStatement().executeUpdate(removeAllInvoices);

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);

		
	}

	/**
	 * 12. Adds an invoice record to the database with the given data.
	 */
	public static void addInvoice(String invoiceCode, String memberCode, String personalTrainerCode, String invoiceDate) {
		/** TODO*/
		Connection conn = DBUtility.connectMeToDatabase();
		PreparedStatement ps;
		ResultSet rs;

		try {
			String findMemberID = "SELECT MemberID FROM Members WHERE MemberCode = ?";
			ps = conn.prepareStatement(findMemberID);
			ps.setString(1, memberCode);
			rs = ps.executeQuery();
			int memberID = Integer.parseInt(rs.getString("MemberID"));
			ps.close();
			rs.close();
			
			String findPersonID = "SELECT PersonID FROM Persons WHERE PersonCode = ?";
			ps = conn.prepareStatement(findPersonID);
			ps.setString(1, personalTrainerCode);
			rs = ps.executeQuery();
			int personID = Integer.parseInt(rs.getString("PersonID"));
			ps.close();
			rs.close();
			
			String addInvoice = "INSERT INTO Invoices (InvoiceCode, InvoiceMemberID, InvoicePersonID, InvoiceDate) VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(addInvoice);
			ps.setString(1, invoiceCode);
			ps.setInt(2, memberID);
			ps.setInt(3, personID);
			ps.setString(4, invoiceDate);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);

		
		
	}

	/**
	 * 13. Adds a particular day-pass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given number of units
	 */

	public static void addDayPassToInvoice(String invoiceCode, String productCode, int quantity) {
		/** TODO*/
		String findInvoiceID;
		
	}

	/**
	 * 14. Adds a particular year-long-pass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addYearPassToInvoice(String invoiceCode, String productCode, int quantity) {
		/** TODO*/
	}

	/**
	 * 15. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 * NOTE: membershipCode may be null
	 */
	public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String membershipCode) {
		/** TODO*/
	}

	/**
	 * 16. Adds a particular equipment rental (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity. 
	 * NOTE: membershipCode may be null
	 */
	public static void addRentalToInvoice(String invoiceCode, String productCode, int quantity, String membershipCode) {
		/** TODO*/
	}


}
