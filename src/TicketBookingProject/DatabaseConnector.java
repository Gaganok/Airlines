package TicketBookingProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnector {
	
	private Connection connection;
	private Statement stm;
	private String URL, user, password, currentDatabase;
	
	DatabaseConnector(String URL, String user, String password){
		this.URL = URL;
		this.user = user;
		this.password = password;
		
		connect();
	}
	
	private void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			try {

				connection = DriverManager.getConnection("jdbc:mysql://" + URL, user, password);
				stm = connection.createStatement();
				
			} catch (SQLException e) {
				System.out.println("No Connection");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("No Driver");
		}
	}
	
	public void createDatabase(String databaseName) {
		try {
			stm.executeUpdate("CREATE DATABASE " + databaseName);
			System.out.println(databaseName + " database successfully created");
		} catch (SQLException e) {
			System.out.println("Faild to create a database");
		}
	}
	
	public void deleteRow(String tableName, String condition) {
		try {
			stm.executeUpdate(
					 "DELETE FROM " + tableName 
					+" WHERE " + condition );
		} catch (SQLException e) {
			System.out.println("Failed to delete row in table " + tableName);
		}
	}
	
	public void deleteDatabase(String databaseName) {
		try {
			stm.executeUpdate("DROP DATABASE " + databaseName);
			System.out.println(databaseName + " database successfully deleted");
		} catch (SQLException e) {
			System.out.println("Faild to delete a database");
		}
	}

	public void openDatabase(String databaseName) {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://" + URL + "/" + databaseName, user, password);
			stm = connection.createStatement();
			currentDatabase = databaseName;
			
			System.out.println(databaseName + " database in use");
		} catch (SQLException e) {
			System.out.println("Can not open " + databaseName +" database");
		}
	}
	
	public void createTable(String tableName, String tableFields) {
		try {
			stm.executeUpdate("CREATE TABLE " + tableName + "(" + tableFields + ")");
			System.out.println("Tabel " + tableName + " successfully created");
		} catch (SQLException e) {
			System.out.println("Failed to Create Table");
		}

	}
	
	public void deleteTable(String tableName) {
		try {
			stm.executeUpdate("DROP TABLE " + tableName);
			System.out.println("Tabel " + tableName + " successfully deleted");
		} catch (SQLException e) {
			System.out.println("Failed to delete Table");
		}
	}
	
	public void insertInto(String table, String value) {
		try {
			String query = "INSERT INTO " + table.toLowerCase() +
			        " VALUES(" + value + ")";
			stm.executeUpdate(query);
		} catch (SQLException e) {
			System.out.println("Failed to insert data");
		}
	}
	
	
	public ResultSet query(String query) {
		
		try {
			return stm.executeQuery(query);
		} catch (SQLException e) {
			System.out.println("Failed to Query");
		}
		
		return null;
	}
}
