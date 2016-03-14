package it.uniroma3.persistence;

import java.sql.*;

import it.uniroma3.crypt.AesCrypt;

public class DBOperations {
	private AesCrypt crypt = new AesCrypt();
	private String dbURI = "jdbc:postgresql:postgres";
	private String username = "postgres";
	private String password = this.crypt.getValue1();
	

	public DBOperations() {
		super();
	}



	public Connection getConnection() throws PersistenceException{
		Connection connection = null;
		try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(this.dbURI, this.username, this.password);
		} catch (ClassNotFoundException e) {
			throw new PersistenceException(e.getMessage());
		} catch (SQLException e){
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}
