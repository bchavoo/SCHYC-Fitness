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

			String findAddressID = "SELECT AddressID FROM Address WHERE Street = ? AND City = ? AND State = ?";
			ps = conn.prepareStatement(findAddressID);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			rs = ps.executeQuery();
			int addressID = 0;
			while(rs.next()) {
				addressID = rs.getInt("AddressID");
			}
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
			int personID = 0;
			while(rs.next()) {
				personID = rs.getInt("PersonID");
			}
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
			int personID = 0;
			while(rs.next()) {
				personID = rs.getInt("PersonID");
			}
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
			String findAddressID = "SELECT AddressID FROM Address WHERE Street = ? AND City = ? AND State = ? AND Zip = ? AND Country = ?";
			ps = conn.prepareStatement(findAddressID);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			ps.setString(4, zip);
			ps.setString(5, country);
			rs = ps.executeQuery();
			int addressID = 0;
			while(rs.next()) {
				addressID = rs.getInt("AddressID");
			}
			ps.close();
			rs.close();


			String addMember = "INSERT INTO Members (MemberPersonID, MemberCode, MemberType, MemberName, MemberAddressID) VALUES (?, ?, ?, ?, ?)";

			ps = conn.prepareStatement(addMember);
			ps.setInt(1, personID);
			ps.setString(2, memberCode);
			ps.setString(3, memberType);
			ps.setString(4, name);
			ps.setInt(5, addressID);
			ps.executeUpdate();
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
			String removeAllInvoiceProducts = "DELETE FROM InvoiceProducts";
			conn.createStatement().executeUpdate(removeAllInvoiceProducts);

			String removeAllProducts = "DELETE FROM Products";
			conn.createStatement().executeUpdate(removeAllProducts);


		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);
	}

	/**
	 * 7. Adds a day-pass record to the database with the provided data.
	 */
	public static void addDayPass(String productCode, String startDate, String street, String city, String state, String zip, String country, double pricePerUnit) {
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

			//Find the AddressID from the address just inserted into the data, is there an easy way
			//to do this or do I have to write another query to find it
			String findAddressID = "SELECT AddressID FROM Address WHERE Street = ? AND City = ? AND State = ?";
			ps = conn.prepareStatement(findAddressID);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			rs = ps.executeQuery();
			int addressID = 0;
			while(rs.next()) {
				addressID = rs.getInt("AddressID");
			}
			ps.close();
			rs.close();

			String addDayPass = "INSERT INTO Products (ProductCode, ProductType, StartDate, AddressID, ProductCost) VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(addDayPass);
			ps.setString(1, productCode);
			ps.setString(2, "D");
			ps.setString(3, startDate);
			ps.setInt(4, addressID);
			ps.setDouble(5, pricePerUnit);
			ps.executeUpdate();




		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);
	}

	/**
	 * 8. Adds a year-long-pass record to the database with the provided data.
	 */
	public static void addYearPass(String productCode, String startDate, String endDate,String street, String city, String state, String zip, String country, String name, double pricePerUnit) {
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

			//Find the AddressID from the address just inserted into the data, is there an easy way
			//to do this or do I have to write another query to find it
			String findAddressID = "SELECT AddressID FROM Address WHERE Street = ? AND City = ? AND State = ?";
			ps = conn.prepareStatement(findAddressID);
			ps.setString(1, street);
			ps.setString(2, city);
			ps.setString(3, state);
			rs = ps.executeQuery();
			int addressID = 0;
			while(rs.next()) {
				addressID = rs.getInt("AddressID");
			}
			ps.close();
			rs.close();

			String addDayPass = "INSERT INTO Products (ProductCode, ProductType, StartDate, EndDate, AddressID, ProductCost) VALUES (?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(addDayPass);
			ps.setString(1, productCode);
			ps.setString(2, "Y");
			ps.setString(3, startDate);
			ps.setString(4, endDate);
			ps.setInt(5, addressID);
			ps.setDouble(6, pricePerUnit);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);

	}

	/**
	 * 9. Adds a ParkingPass record to the database with the provided data.
	 */
	public static void addParkingPass(String productCode, double parkingCost) {
		/** TODO*/
		Connection conn = DBUtility.connectMeToDatabase();
		PreparedStatement ps;

		try {

			String addDayPass = "INSERT INTO Products (ProductCode, ProductType, ProductCost) VALUES (?, ?, ?)";
			ps = conn.prepareStatement(addDayPass);
			ps.setString(1, productCode);
			ps.setString(2, "P");
			ps.setDouble(3, parkingCost);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);
	}

	/**
	 * 10. Adds an equipment rental record to the database with the provided data.
	 */
	public static void addRental(String productCode, String name, double cost) {
		/** TODO*/
		Connection conn = DBUtility.connectMeToDatabase();
		PreparedStatement ps;

		try {

			String addDayPass = "INSERT INTO Products (ProductCode, ProductType, ProductName, ProductCost) VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(addDayPass);
			ps.setString(1, productCode);
			ps.setString(2, "R");
			ps.setString(3, name);
			ps.setDouble(4, cost);
			ps.executeUpdate();
			ps.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);
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
			int memberID = 0;
			while(rs.next()) {
				memberID = rs.getInt("MemberID");
			}
			ps.close();
			rs.close();

			String findPersonID = "SELECT PersonID FROM Persons WHERE PersonCode = ?";
			ps = conn.prepareStatement(findPersonID);
			ps.setString(1, personalTrainerCode);
			rs = ps.executeQuery();
			int personID = 0;
			while(rs.next()) {
				personID = rs.getInt("PersonID");
			}
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
		Connection conn = DBUtility.connectMeToDatabase();
		PreparedStatement ps;
		ResultSet rs;

		try{
			String findInvoiceID = "SELECT InvoiceID FROM Invoices WHERE InvoiceCode = ?";
			ps = conn.prepareStatement(findInvoiceID);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			int invoiceID = 0;
			while(rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
			}
			ps.close();
			rs.close();

			String findProductID = "SELECT ProductID FROM Products WHERE ProductCode = ?";
			ps = conn.prepareStatement(findProductID);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			int productID = 0;
			while(rs.next()) {
				productID = rs.getInt("ProductID");
			}
			ps.close();
			rs.close();


			String addDayMembershiptoInvoice = "INSERT INTO InvoiceProducts (InvoiceID, ProductID, Quantity) VALUES (?, ?, ?)";

			ps = conn.prepareStatement(addDayMembershiptoInvoice);
			ps.setInt(1, invoiceID);
			ps.setInt(2, productID);
			ps.setInt(3, quantity);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);


		//String findInvoiceID;

	}

	/**
	 * 14. Adds a particular year-long-pass (corresponding to <code>productCode</code>
	 * to an invoice corresponding to the provided <code>invoiceCode</code> with
	 * the given begin/end dates
	 */
	public static void addYearPassToInvoice(String invoiceCode, String productCode, int quantity) {
		/** TODO*/
		Connection conn = DBUtility.connectMeToDatabase();
		PreparedStatement ps;
		ResultSet rs;

		try{
			String findInvoiceID = "SELECT InvoiceID FROM Invoices WHERE InvoiceCode = ?";
			ps = conn.prepareStatement(findInvoiceID);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			int invoiceID = 0;
			while(rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
			}
			ps.close();
			rs.close();

			String findProductID = "SELECT ProductID FROM Products WHERE ProductCode = ?";
			ps = conn.prepareStatement(findProductID);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			int productID = 0;
			while(rs.next()) {
				productID = rs.getInt("ProductID");
			}
			ps.close();
			rs.close();


			String addYearMembershiptoInvoice = "INSERT INTO InvoiceProducts (InvoiceID, ProductID, Quantity) VALUES (?, ?, ?)";

			ps = conn.prepareStatement(addYearMembershiptoInvoice);
			ps.setInt(1, invoiceID);
			ps.setInt(2, productID);
			ps.setInt(3, quantity);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);
	}

	/**
	 * 15. Adds a particular ParkingPass (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity.
	 * NOTE: membershipCode may be null
	 */
	public static void addParkingPassToInvoice(String invoiceCode, String productCode, int quantity, String membershipCode) {
		/** TODO*/
		Connection conn = DBUtility.connectMeToDatabase();
		PreparedStatement ps;
		ResultSet rs;

		try{
			String findInvoiceID = "SELECT InvoiceID FROM Invoices WHERE InvoiceCode = ?";
			ps = conn.prepareStatement(findInvoiceID);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			int invoiceID = 0;
			while(rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
			}
			ps.close();
			rs.close();

			String findProductID = "SELECT ProductID FROM Products WHERE ProductCode = ?";
			ps = conn.prepareStatement(findProductID);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			int productID = 0;
			while(rs.next()) {
				productID = rs.getInt("ProductID");
			}
			ps.close();
			rs.close();
			
			String findMembershipID = "SELECT ProductID FROM Products WHERE ProductCode = ?";
			ps = conn.prepareStatement(findMembershipID);
			ps.setString(1, membershipCode);
			rs = ps.executeQuery();
			int membershipID = 0;
			while(rs.next()) {
				membershipID = rs.getInt("ProductID");
			}
			ps.close();
			rs.close();


			String addParkingPassToInvoice = "INSERT INTO InvoiceProducts (InvoiceID, ProductID, Quantity, MembershipID) VALUES (?, ?, ?, ?)";

			ps = conn.prepareStatement(addParkingPassToInvoice);
			ps.setInt(1, invoiceID);
			ps.setInt(2, productID);
			ps.setInt(3, quantity);
			ps.setInt(4, membershipID);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);
	}

	/**
	 * 16. Adds a particular equipment rental (corresponding to <code>productCode</code> to an 
	 * invoice corresponding to the provided <code>invoiceCode</code> with the given
	 * number of quantity. 
	 * NOTE: membershipCode may be null
	 */
	public static void addRentalToInvoice(String invoiceCode, String productCode, int quantity, String membershipCode) {
		/** TODO*/
		Connection conn = DBUtility.connectMeToDatabase();
		PreparedStatement ps;
		ResultSet rs;

		try{
			String findInvoiceID = "SELECT InvoiceID FROM Invoices WHERE InvoiceCode = ?";
			ps = conn.prepareStatement(findInvoiceID);
			ps.setString(1, invoiceCode);
			rs = ps.executeQuery();
			int invoiceID = 0;
			while(rs.next()) {
				invoiceID = rs.getInt("InvoiceID");
			}
			ps.close();
			rs.close();

			String findProductID = "SELECT ProductID FROM Products WHERE ProductCode = ?";
			ps = conn.prepareStatement(findProductID);
			ps.setString(1, productCode);
			rs = ps.executeQuery();
			int productID = 0;
			while(rs.next()) {
				productID = rs.getInt("ProductID");
			}
			ps.close();
			rs.close();
			
			
			String findMembershipID = "SELECT ProductID FROM Products WHERE ProductCode = ?";
			ps = conn.prepareStatement(findMembershipID);
			ps.setString(1, membershipCode);
			rs = ps.executeQuery();
			int membershipID = 0;
			while(rs.next()) {
				membershipID = rs.getInt("ProductID");
			}
			ps.close();
			rs.close();


			String addRentalToInvoice = "INSERT INTO InvoiceProducts (InvoiceID, ProductID, Quantity, MembershipID) VALUES (?, ?, ?, ?)";

			ps = conn.prepareStatement(addRentalToInvoice);
			ps.setInt(1, invoiceID);
			ps.setInt(2, productID);
			ps.setInt(3, quantity);
			ps.setInt(4, membershipID);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		DBUtility.closeConnection(conn);
	}


}
