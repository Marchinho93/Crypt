package it.uniroma3.persistence;

import it.uniroma3.model.Indirizzo;
import it.uniroma3.model.Studente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

public class StudenteDaoJDBC implements StudenteDao {
	final static Logger logger = Logger.getLogger(StudenteDaoJDBC.class);
	private DataSource dataSource;

	public StudenteDaoJDBC() {
		this.dataSource = new DataSource();
	}

	public void save(Studente studente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String insert = "insert into studente(matricola, nome, cognome, datanascita) values (?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(insert);
			statement.setString(1, studente.getMatricola());
			statement.setString(2, studente.getNome());
			statement.setString(3, studente.getCognome());
			long secs = studente.getDataNascita().getTime();
			statement.setDate(4, new java.sql.Date(secs));
			logger.debug(statement);
			statement.executeUpdate();
			Indirizzo i = studente.getIndirizzo();
			if(i!=null){
				IndirizzoDaoJDBC iDao = new IndirizzoDaoJDBC();
				iDao.save(i, studente.getMatricola());
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
	}  

	public Studente findByPrimaryKey(String matricola) {
		Connection connection = this.dataSource.getConnection();
		Studente studente = null;
		try {
			PreparedStatement statement;
			String query = "select * from studente LEFT OUTER JOIN indirizzo ON id=matricola WHERE matricola=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			logger.debug(statement);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				studente = new Studente();
				studente.setMatricola(result.getString("matricola"));				
				studente.setNome(result.getString("nome"));
				studente.setCognome(result.getString("cognome"));
				long secs = result.getDate("datanascita").getTime();
				studente.setDataNascita(new java.util.Date(secs));
				if(result.getString("id")!=null){
					Indirizzo indirizzo = new Indirizzo();
					indirizzo.setCitta(result.getString("citta"));
					indirizzo.setVia(result.getString("via"));
					indirizzo.setNumeroCivico(result.getInt("numeroCivico"));
					studente.setIndirizzo(indirizzo);
				}
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
		return studente;
	}

	public List<Studente> findAll() {
		Connection connection = this.dataSource.getConnection();
		List<Studente> studenti = new LinkedList<>();
		try {
			Studente studente;
			PreparedStatement statement;
			String query = "select * from studente LEFT OUTER JOIN indirizzo ON matricola=id";
			statement = connection.prepareStatement(query);
			logger.debug(statement);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				studente = new Studente();
				studente.setMatricola(result.getString("matricola"));				
				studente.setNome(result.getString("nome"));
				studente.setCognome(result.getString("cognome"));
				long secs = result.getDate("datanascita").getTime();
				studente.setDataNascita(new java.util.Date(secs));
				Indirizzo indirizzo = new Indirizzo();
				indirizzo.setCitta(result.getString("citta"));
				indirizzo.setVia(result.getString("via"));
				indirizzo.setNumeroCivico(result.getInt("numeroCivico"));
				studente.setIndirizzo(indirizzo);
				studenti.add(studente);
			}
		} catch (SQLException e) {
			logger.error(e);
			throw new PersistenceException(e.getMessage());
		}	 finally {
			try {
				connection.close();
			} catch (SQLException e) {
				logger.error(e);
				throw new PersistenceException(e.getMessage());
			}
		}
		return studenti;
	}

	public void update(Studente studente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String update = "update studente SET nome = ?, cognome = ?, datanascita = ? WHERE matricola=?";
			PreparedStatement statement = connection.prepareStatement(update);
			statement.setString(1, studente.getNome());
			statement.setString(2, studente.getCognome());
			long secs = studente.getDataNascita().getTime();
			statement.setDate(3, new java.sql.Date(secs));
			statement.setString(4, studente.getMatricola());
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

	public void delete(Studente studente) {
		Connection connection = this.dataSource.getConnection();
		try {
			String delete = "delete FROM studente WHERE matricola = ? ";
			PreparedStatement statement = connection.prepareStatement(delete);
			statement.setString(1, studente.getMatricola());
			logger.debug(statement);
			statement.executeUpdate();
			Indirizzo i = studente.getIndirizzo();
			if(i!=null){
				IndirizzoDaoJDBC iDao = new IndirizzoDaoJDBC();
				iDao.delete(studente.getMatricola());
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
	}
	
	public Studente findByAddress(Indirizzo indirizzo){
		Studente studente = new Studente();
		Connection connection = this.dataSource.getConnection();
		try{
			String query = "SELECT * FROM studente LEFT OUTER JOIN indirizzo ON id = matricola WHERE id=?";
			PreparedStatement statement = connection.prepareStatement(query);
			IndirizzoDaoJDBC iDao = new IndirizzoDaoJDBC();
			statement.setString(1, iDao.getMatricola(indirizzo));
			ResultSet res = statement.executeQuery();
			if(res.next()){
				studente.setMatricola(res.getString("matricola"));
				studente.setNome(res.getString("nome"));
				studente.setCognome(res.getString("cognome"));
				long secs = res.getDate("datanascita").getTime();
				studente.setDataNascita(new java.util.Date(secs));
				studente.setIndirizzo(indirizzo);
			}
		}catch(SQLException e){
			logger.error(e);
			throw new PersistenceException(e.getMessage());
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				logger.error(e);
				throw new PersistenceException(e.getMessage());
			}
		}
		return studente;
	}
}
