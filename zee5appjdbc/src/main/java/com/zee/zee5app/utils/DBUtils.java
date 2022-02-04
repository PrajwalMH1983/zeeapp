package com.zee.zee5app.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.JdbcConnection;

public class DBUtils {
	
	//This class is used to get the DB connection and to close the DB connection
	//Singleton DP
	private static DBUtils dbutils;
	public static DBUtils getInstance() throws IOException {
		if(dbutils == null)
			dbutils = new DBUtils();
		return dbutils;
	}
	
	private DBUtils() throws IOException{
		// TODO Auto-generated constructor stub
	}
	
	//We did that throws IOException because 
	//the loadProperties method we assigned it to the properties field
	//when we do this thing then in that case properties will get the object
	//but default constructor is responsible for providing return object to properties as we are implementing
	//singleton DP
	
	public Connection getConnection() {
		//write stuff to establish connection with the DB
		
		Connection connection = null;
		
		try {
			properties = loadProperties();
			
			connection = DriverManager.getConnection
			(properties.getProperty("jdbc.url"),
					properties.getProperty("jdbc.username") , 
					properties.getProperty("jdbc.password"));
			
			
			System.out.println(properties);
			return connection;
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	
	
	Properties properties = loadProperties();
	private Properties loadProperties() throws IOException {
		//read the properties file
		//This code does not read the input gives us Null pointer exception
		
//		InputStream inputStream = 
//				this.getClass()
//				.getResourceAsStream("application.properties");
		
		
		InputStream inputStream = 
				DBUtils.class.getClassLoader()
				.getResourceAsStream("application.properties");
		
		//getClassLoader() : it will load the class
		
		Properties properties = new Properties();
		//load method will read the properties internally
		properties.load(inputStream);
		
		return properties;
	}
	
//	public static void main(String[] args) {
//		
//		DBUtils dbUtils = DBUtils.getInstance();
//		Connection connection = dbUtils.getConnection();
//		//This gives us the confirmation that we are getting the connection object
//		System.out.println(connection != null);
//	}
}
