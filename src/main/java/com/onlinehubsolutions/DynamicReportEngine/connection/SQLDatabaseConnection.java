package com.onlinehubsolutions.DynamicReportEngine.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLDatabaseConnection {
	static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String DB_URL = "jdbc:sqlserver://10.254.20.60\\SQL2017;databaseName=CoffeeSystem";
	static final String DB_URL_LOCAL = "jdbc:sqlserver://localhost;databaseName=CoffeeSystem;SelectMethod=cursor;integratedSecurity=true";
	static final String DB_USER = "user=Dormans";
	static final String DB_PASS = "password=namrod";
	static final String DB_CONNECTION = DB_URL_LOCAL+";"+ DB_USER+";"+DB_PASS;
	
	public Connection Connection() {

		Connection Conn = null;
		
		try {
			Class.forName(DB_DRIVER);
			
			System.out.println("Connecting to Database...." + DB_CONNECTION);
			Conn = DriverManager.getConnection(DB_CONNECTION);
			
			System.out.println("Connected to Database...." + DB_CONNECTION);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return Conn;
		
	}
}
