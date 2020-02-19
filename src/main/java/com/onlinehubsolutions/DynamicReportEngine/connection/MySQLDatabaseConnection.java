package com.onlinehubsolutions.DynamicReportEngine.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySQLDatabaseConnection {
	static final String DB_DRIVER = "org.mariadb.jdbc.Driver";
	static final String DB_URL = "jdbc:mariadb://127.0.0.1:3306/onliunca_dormans";
	static final String DB_USER = "root";
	static final String DB_PASS = "";
	
	public Connection Connection() {

		Connection Conn = null;
		
		try {
			Class.forName(DB_DRIVER);
			
			System.out.println("Connecting to Database...." + DB_URL);
			Conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
			
			System.out.println("Connected to Database...." + DB_URL);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return Conn;
		
	}
}
