package it.uniroma3.persistence;

import java.sql.*;
import org.apache.log4j.*;

public class DataSource {
	final static Logger logger = Logger.getLogger(DataSource.class);

	private String dbURI = "jdbc:postgresql:postgres";
	private String userName = "postgres";
	private String password = "k";

	public Connection getConnection() throws PersistenceException {
		Connection connection = null;
		try {
		    Class.forName("org.postgresql.Driver");
		    connection = DriverManager.getConnection(dbURI,userName, password);
			logger.debug(connection);
		} catch (ClassNotFoundException e) {
			logger.error(e);
			throw new PersistenceException(e.getMessage());
		} catch(SQLException e) {
			logger.error(e);
			throw new PersistenceException(e.getMessage());
		}
		return connection;
	}
}
