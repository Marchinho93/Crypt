package it.uniroma3.persistence;

import java.sql.*;

public class DBOperations {



	public static Connection getConnection() throws PersistenceException{
		String dbURI = "jdbc:postgresql:postgres";
		String username = "postgres";
		String password = "k";
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(dbURI, username, password);
		} catch (ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		} catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}
