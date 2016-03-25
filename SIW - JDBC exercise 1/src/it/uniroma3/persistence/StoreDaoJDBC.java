package it.uniroma3.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import it.uniroma3.model.Store;

public class StoreDaoJDBC implements StoreDAO{
	final static private Logger log = Logger.getLogger(StoreDaoJDBC.class);
	DBOperations DBO;
	
	public StoreDaoJDBC(){
		this.DBO = new DBOperations();
	}

	@Override
	public void save(Store store) {
		Connection connection = this.DBO.getConnection();
		try{
			PreparedStatement statement;
			String query = "INSERT INTO store(id, nome, categoria, dipendenti) VALUES (?,?,?,?)";
			statement = connection.prepareStatement(query);
			statement.setLong(1, store.getId());
			statement.setString(2, store.getNome());
			statement.setString(3, store.getCategoria());
			statement.setInt(4, store.getDipendenti());
			statement.executeUpdate();
			log.debug(statement);
		}catch(SQLException e){
			log.error(e);
			throw new PersistenceException(e.getMessage());
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				log.error(e);
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void update(Store store) {
		Connection connection = this.DBO.getConnection();
		try{
			PreparedStatement statement;
			String query = "UPDATE store SET nome=?, categoria=?, dipendenti=? WHERE id=?";
			statement = connection.prepareStatement(query);
			statement.setString(1, store.getNome());
			statement.setString(2, store.getCategoria());
			statement.setInt(3, store.getDipendenti());
			statement.setLong(4, store.getId());
			statement.executeUpdate();
			log.debug(statement);
		}catch(SQLException e){
			log.error(e);
			throw new PersistenceException(e.getMessage());
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				log.error(e);
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public void delete(Store store) {
		Connection connection = this.DBO.getConnection();
		try{
			PreparedStatement statement;
			String query = "DELETE FROM store WHERE id=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, store.getId());
			statement.executeUpdate();
			log.debug(statement);
		}catch(SQLException e){
			log.error(e);
			throw new PersistenceException(e.getMessage());
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				log.error(e);
				throw new PersistenceException(e.getMessage());
			}
		}
	}

	@Override
	public Store findByPrimaryKey(long id) {
		Store store = null;
		Connection connection = this.DBO.getConnection();
		try{
			PreparedStatement statement;
			String query = "SELECT * FROM store WHERE id=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			ResultSet res = statement.executeQuery();
			if(res.next()){
				store = new StoreProxy();
				store.setId(res.getLong("id"));
				store.setNome(res.getString("nome"));
				store.setCategoria(res.getString("categoria"));
				store.setDipendenti(res.getInt("dipendenti"));
			}
		}catch(SQLException e){
			log.error(e);
			throw new PersistenceException(e.getMessage());
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				log.error(e);
				throw new PersistenceException(e.getMessage());
			}
		}
		return store;
	}

	@Override
	public Set<Store> findAll() {
		Set<Store> setStore = new HashSet<>();
		Connection connection = this.DBO.getConnection();
		try{
			PreparedStatement statement;
			String query = "SELECT * FROM store";
			statement = connection.prepareStatement(query);
			ResultSet res = statement.executeQuery();
			while(res.next()){
				Store store = new StoreProxy();
				store.setId(res.getLong("id"));
				store.setNome(res.getString("nome"));
				store.setCategoria(res.getString("categoria"));
				store.setDipendenti(res.getInt("dipendenti"));
				setStore.add(store);
			}
		}catch(SQLException e){
			log.error(e);
			throw new PersistenceException(e.getMessage());
		}finally{
			try{
				connection.close();
			}catch(SQLException e){
				log.error(e);
				throw new PersistenceException(e.getMessage());
			}
		}
		return setStore;
	}
	
}
