package it.uniroma3.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import it.uniroma3.model.Indirizzo;
import it.uniroma3.model.Studente;

public class IndirizzoDaoJDBC {
	final static private Logger log = Logger.getLogger(IndirizzoDaoJDBC.class);
	private DataSource dataSource;
	
	public IndirizzoDaoJDBC(){
		this.dataSource = new DataSource();
	}
	
	public void save(Indirizzo indirizzo, String matricola){
		Connection connection = dataSource.getConnection();
		try{
			PreparedStatement statement;
			String query = "INSERT INTO indirizzo(id, citta, via, numeroCivico) VALUES (?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			statement.setString(2, indirizzo.getCitta());
			statement.setString(3, indirizzo.getVia());
			statement.setInt(4, indirizzo.getNumeroCivico());
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	
	public void delete(String matricola){
		Connection connection = dataSource.getConnection();
		try{
			PreparedStatement statement;
			String query = "DELETE FROM indirizzo WHERE id = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, matricola);
			statement.executeUpdate();
		} catch (SQLException e) {
			log.error(e);
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new PersistenceException(e.getMessage());
			}
		}
	}
	
	public Indirizzo findByStudent(Studente s){
		Connection connection = dataSource.getConnection();
		Indirizzo indirizzo = new Indirizzo();
		try{
			PreparedStatement statement;
			String query = "SELECT * FROM Indirizzo WHERE id = ?";
			statement = connection.prepareStatement(query);
			statement.setString(1, s.getMatricola());
			ResultSet res = statement.executeQuery();
			while(res.next()){
				indirizzo.setCitta(res.getString("citta"));
				indirizzo.setVia(res.getString("via"));
				indirizzo.setNumeroCivico(res.getInt("numeroCivico"));
			}
		}
		catch(SQLException e) {
			log.error(e);
			throw new PersistenceException(e.getMessage());
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				log.error(e);
				throw new PersistenceException(e.getMessage());
			}
			
		}
		return indirizzo;
	}

	public String getMatricola(Indirizzo indirizzo) {
		String matricola=null;
		Connection connection = this.dataSource.getConnection();
		try{
			String query = "SELECT * FROM indirizzo WHERE citta = ? and via = ? and numeroCivico = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, indirizzo.getCitta());
			statement.setString(2, indirizzo.getVia());
			statement.setInt(3, indirizzo.getNumeroCivico());
			ResultSet res = statement.executeQuery();
			if(res.next()){
				matricola = res.getString("id");
			}
		}catch(SQLException e){
			log.error(e);
			throw new PersistenceException(e.getMessage());
		}
		return matricola;
	}
}
