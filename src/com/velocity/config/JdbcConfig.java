package com.velocity.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.velocity.exception.ProjectException;

public class JdbcConfig {

	private static final String URL = "jdbc:mysql://localhost:3306/ecommerce";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "root123";
	
	static {
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		}
		catch(Exception e) {
			throw new ProjectException("Driver Not Found" + e.getMessage());
		}
	}
	
	private JdbcConfig() {};
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL,USERNAME,PASSWORD);
	}
}
