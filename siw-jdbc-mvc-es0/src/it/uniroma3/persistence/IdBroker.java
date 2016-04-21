package it.uniroma3.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class IdBroker {
	private static Logger logger = Logger.getLogger("it.uniroma3.persistence.IdBroker");

	// Standard SQL (queste stringhe andrebbero scritte in un file di configurazione
	// private static final String query = "SELECT NEXT VALUE FOR SEQ_ID AS id";
	// postgresql
	private static final String query = "SELECT nextval('sequenza_id') AS id";

	public static Long getId(Connection connection) {
		Long id = null;
		try {
			PreparedStatement statement = connection.prepareStatement(query);
			logger.debug(statement);
			ResultSet result = statement.executeQuery();
			result.next();
			id = result.getLong("id");
			logger.debug("Created NEW ID: " + id);
		} catch (SQLException e) {
			logger.error("Errore SQL: " + e.getMessage());
			throw new PersistenceException(e.getMessage());
		}
		return id;
	}
}