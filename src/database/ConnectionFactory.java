package database;

import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public java.sql.Connection connection;
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DBNAME = "myrh";
	public static final String URL = "jdbc:mysql://localhost:3306/" + DBNAME;
	public static final String USER = "root";
	public static final String PASSWORD = "Dev2020@";
	
	public boolean getConnection() {
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("Connection successifuly.");
			return true;
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver not found!");
			return false;
		} catch (SQLException ex) {
			System.out.println("Connection failed!");
			return false;
		}
	}
	
	public void close() {
		try {
			connection.close();
			System.out.println("Disconnected.");
		} catch (SQLException ex) {
			System.out.println("Error: " + ex);
		}
	}
}