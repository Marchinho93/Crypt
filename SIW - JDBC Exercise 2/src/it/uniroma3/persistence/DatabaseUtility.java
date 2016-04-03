package it.uniroma3.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DatabaseUtility {
	final static Logger logger = Logger.getLogger(DatabaseUtility.class);
	
	public static void resetDatabase() {
		DataSource dataSource = new DataSource();
		Connection connection = dataSource.getConnection();
		try {
			String delete = "delete FROM studente";
			PreparedStatement statement = connection.prepareStatement(delete);
			logger.debug(statement);
			statement.executeUpdate();

			delete = "delete FROM gruppo";
			statement = connection.prepareStatement(delete);
			logger.debug(statement);
			statement.executeUpdate();
			
			delete = "delete FROM indirizzo";
			statement = connection.prepareStatement(delete);
			logger.debug(statement);
			statement.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e);
				throw new PersistenceException(e.getMessage());
			}
		}
	}
}
