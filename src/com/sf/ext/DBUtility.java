package com.sf.ext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtility {

	//JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String DB_URL = "jdbc:mysql://cse.unl.edu:3306/bchavez";


	//Database credentials
	static final String USER = "bchavez";
	static final String PASS = "12345";
	
	public DBUtility() {
		super();
	}
	
	//Method to call and get a connection to database
	public static Connection connectMeToDatabase () {
		try {
			Class.forName(JDBC_DRIVER);
			
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			return conn;
		} catch (SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			System.err.println(e);
			System.exit (-1);
		}
		return null;
	}
	
	
	
	//Method to close the connection once done using
	public static void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
