package it.uniroma3.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import it.uniroma3.model.Prodotto;
import it.uniroma3.persistence.ProdottoDAO;
import it.uniroma3.persistence.DBOperations;

public class ProdottoDaoJDBC implements ProdottoDAO {
	final static Logger log = Logger.getLogger(ProdottoDaoJDBC.class);
	private DBOperations DBO;
	
	public ProdottoDaoJDBC(){
		this.DBO = new DBOperations();
	}
	
	public void save(Prodotto prodotto) throws PersistenceException{
 		log.debug("-> Save");
 		Connection connection = this.DBO.getConnection();
 		log.debug("	> ConnectionOpened");
		String query = "INSERT into prodotto(id, nome, descrizione, prezzo) values (?,?,?,?)";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(query);
			statement.setLong(1, prodotto.getId());
			statement.setString(2, prodotto.getNome());
			statement.setString(3, prodotto.getDescrizione());
			statement.setDouble(4, prodotto.getPrezzo());
			log.debug("	> DbOperation (Query = " + statement +")");
			statement.executeUpdate();
			log.debug("	> Inserted product = " + prodotto.toString());
		} catch (SQLException e) {
			log.error(e);
			throw new PersistenceException(e.getMessage());
		} finally{
			try {
			log.debug("	> ClosingConnection = " + connection);
			connection.close();
			log.debug("	> Closed.");
			log.debug("	> FlushingStatement = " + statement);
			statement.close();
			log.debug("	> Flushed.");
			} catch (SQLException e) {
			log.error(e);
			throw new PersistenceException(e.getMessage());
			}
		}
	}
	
	public void delete(Prodotto prodotto){
 		log.debug("-> Delete");
 		Connection connection = this.DBO.getConnection();
 		log.debug("	> ConnectionOpened");
		String query = "DELETE from prodotto where id = ?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(query);
			statement.setLong(1, prodotto.getId());
			log.debug("	> DbOperation (Query = " + statement + ")");
			statement.executeUpdate();
			log.debug("	> Deleted product = " + prodotto.toString());
		} catch (SQLException e) {
			log.error(e);
			throw new PersistenceException(e.getMessage());
		} finally{
			try {
			log.debug("	> ClosingConnection = " + connection);
			connection.close();
			log.debug("	> Closed.");
			log.debug("	> FlushingStatement = " + statement);
			statement.close();
			log.debug("	> Flushed.");
			} catch (SQLException e) {
			log.error(e);
			throw new PersistenceException(e.getMessage());
			}
		}
	}
	
	public void update(Prodotto prodotto){
 		log.debug("\n-> Update");
 		Connection connection = this.DBO.getConnection();
 		log.debug("	> ConnectionOpened");
		String query = "UPDATE prodotto SET nome=?, descrizione=?, prezzo=? where id=?";
		PreparedStatement statement = null;
		try {
			statement = connection.prepareStatement(query);
			statement.setString(1, prodotto.getNome());
			statement.setString(2, prodotto.getDescrizione());
			statement.setDouble(3, prodotto.getPrezzo());
			statement.setLong(4, prodotto.getId());
			log.debug("	> DbOperation (Query = " + statement +")");
			statement.executeUpdate();
			log.debug("	> Updated product = " + prodotto.toString());
		} catch (SQLException e) {
			log.error(e);
			throw new PersistenceException(e.getMessage());
		} finally{
			try {
			log.debug("	> ClosingConnection = " + connection);
			connection.close();
			log.debug("	> Closed.");
			log.debug("	> FlushingStatement = " + statement);
			statement.close();
			log.debug("	> Flushed.");
			} catch (SQLException e) {
			log.error(e);
			throw new PersistenceException(e.getMessage());
			}
		}
	}
	
	public Prodotto findByPrimaryKey(Long id){
		log.debug("-> FindByPrimaryKey");
 		Connection connection = this.DBO.getConnection();
 		log.debug("	> ConnectionOpened");
		String query = "SELECT id,nome,descrizione,prezzo FROM prodotto WHERE id=?";
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			log.debug("	> DbOperation (Query = " + statement +")");
			result = statement.executeQuery();
			result.next();
			Prodotto p = new Prodotto(result.getLong("id"), normalize(result.getString("nome")),normalize(result.getString("descrizione")),result.getDouble("prezzo"));
			log.debug("	> GotResult = " + p.getId()+ " " + p.getNome()+ " " + p.getDescrizione()+ " " + p.getPrezzo());
			return p;
		} catch (SQLException e){
			log.error(e);
			throw new PersistenceException(e.getMessage());
		} finally{
			try {
			log.debug("	> ClosingConnection = " + connection);
			connection.close();
			log.debug("	> Closed.");
			log.debug("	> FlushingStatement = " + statement);
			statement.close();
			log.debug("	> Flushed.");
			log.debug("	> CleaningResult = " + result);
			result.close();
			log.debug("	> Cleaned.");
			} catch (SQLException e) {
			log.error(e);
			throw new PersistenceException(e.getMessage());
			}
		}
	}
	
 	public List<Prodotto> findAll(){
 		log.debug("-> FindAll");
 		Connection connection = this.DBO.getConnection();
 		log.debug("	> ConnectionOpened");
		String query = "SELECT id,nome,descrizione,prezzo FROM prodotto";
		PreparedStatement statement = null;
		ResultSet result = null;
		try{
			statement = connection.prepareStatement(query);
			log.debug("	> DbOperation (Query = " + statement +")");
			result = statement.executeQuery();
			LinkedList<Prodotto> lp = new LinkedList<>(); 
			while(result.next()){
			Prodotto p = new Prodotto(result.getLong("id"),result.getString("nome"),result.getString("descrizione"),result.getDouble("prezzo"));
			log.debug("	> GotResult = " + p.getId()+ " " + p.getNome()+ " " + p.getDescrizione()+ " " + p.getPrezzo());
			lp.add(p);
			}
			return lp;
		} catch (SQLException e){
			log.error(e);
			throw new PersistenceException(e.getMessage());
		} finally{
			try {
			log.debug("	> ClosingConnection = " + connection);
			connection.close();
			log.debug("	> Closed.");
			log.debug("	> FlushingStatement = " + statement);
			statement.close();
			log.debug("	> Flushed.");
			log.debug("	> CleaningResult = " + result);
			result.close();
			log.debug("	> Cleaned.");
			} catch (SQLException e) {
			log.error(e);
			throw new PersistenceException(e.getMessage());
			}
		}
 	}
 	
 	public String normalize(String s){
	 	String res = "";
	 	char add = ' ';
	 	for(int i = 0; i<s.length(); i++){
	 		add = s.charAt(i);
	 		if(add!=' ')
	 			res += add;
	 	}
	 	return res;
 	}
}
