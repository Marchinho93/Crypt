package it.uniroma3.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import it.uniroma3.model.Prodotto;
import it.uniroma3.model.Store;

public class CatalogoDaoJDBC {
	final static private Logger log = Logger.getLogger(CatalogoDaoJDBC.class);
	private DBOperations DBO;
	
	public CatalogoDaoJDBC(){
		this.DBO = new DBOperations();
	}
	
	public void link(Store store, Prodotto prodotto){
		Connection connection = this.DBO.getConnection();
		try{
			PreparedStatement statement;
			String query = "INSERT INTO catalogo(idStore, idProdotto) VALUES (?,?)";
			statement = connection.prepareStatement(query);
			statement.setLong(1, store.getId());
			statement.setLong(2, prodotto.getId());
			statement.executeUpdate();
		}catch(SQLException e){
			log.error(e);
			throw new PersistenceException(e.getMessage());
		} finally{
			try{ 
				connection.close();
			} catch (SQLException e){
				log.error(e);
				throw new PersistenceException(e.getMessage());
			}
		}		
	}
	
	public void unLink(Store store, Prodotto prodotto){
		Connection connection = this.DBO.getConnection();
		try{
			PreparedStatement statement;
			String query = "DELETE FROM catalogo WHERE idStore=?, idProdotto=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, store.getId());
			statement.setLong(2, prodotto.getId());
			statement.executeUpdate();
		}catch(SQLException e){
			log.error(e);
			throw new PersistenceException(e.getMessage());
		} finally{
			try{ 
				connection.close();
			} catch (SQLException e){
				log.error(e);
				throw new PersistenceException(e.getMessage());
			}
		}		
	}
	
	public Set<Store> findStoreFromProduct(Prodotto prodotto){
		Set<Store> sellers = new HashSet<>();
		Connection connection = this.DBO.getConnection();
		try{
			PreparedStatement statement;
			String query = "SELECT * FROM catalogo JOIN store ON idStore=id WHERE idProdotto=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, prodotto.getId());
			ResultSet res = statement.executeQuery();
			while(res.next()){
				Store store = new Store();
				store.setId(res.getLong("id"));
				store.setNome(res.getString("nome"));
				store.setCategoria(res.getString("categoria"));
				store.setDipendenti(res.getInt("dipendenti"));
				sellers.add(store);
			}
		}catch (SQLException e){
			log.error(e);
			throw new PersistenceException(e.getMessage());
		}finally{				
			try{
				connection.close();
			} catch (SQLException e){
				log.error(e);
				throw new PersistenceException (e.getMessage());
			}
		}
		return sellers;
	}
}
