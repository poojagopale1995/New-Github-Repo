package Sel1;

import java.sql.Connection;
import java.sql.DriverManager;


public class DB_Connection{
	public Connection connection;
	public DB_Connection()
	{
	try {

		String dbURL = "jdbc:mysql://192.168.1.49/bmsp";
		String usename = "root";
		String password = "root";
		Class.forName("com.mysql.jdbc.Driver");  
		connection = DriverManager.getConnection(dbURL,usename,password);
		

	}catch(Exception e) {
		System.out.println("Error in Dbconnection"+e);
	}
	}

}
