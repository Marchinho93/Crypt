package it.uniroma3.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import it.uniroma3.model.Gruppo;
import it.uniroma3.model.Studente;

public class GruppoProxy extends Gruppo {
	final static Logger logger = Logger.getLogger(GruppoProxy.class);
	private DataSource dataSource;

	public GruppoProxy() {
		this.dataSource = new DataSource();
	}

	public Set<Studente> getStudenti() { 
		Set<Studente> studenti = new HashSet<>();
		Connection connection = this.dataSource.getConnection();
		try {
			PreparedStatement statement;
			String query = "select * from studente where gruppo_id = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, this.getId());
			logger.debug(statement);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				Studente studente = new Studente();
				studente.setMatricola(result.getString("matricola"));				
				studente.setNome(result.getString("nome"));
				studente.setCognome(result.getString("cognome"));
				long secs = result.getDate("datanascita").getTime();
				studente.setDataNascita(new java.util.Date(secs));
				studenti.add(studente);
			}
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
		this.setStudenti(studenti);
		return super.getStudenti(); 
	}
}
