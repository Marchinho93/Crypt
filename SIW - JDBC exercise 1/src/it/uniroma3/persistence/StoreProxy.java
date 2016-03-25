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

public class StoreProxy extends Store{
	final static private Logger log = Logger.getLogger(StoreProxy.class);
	private DBOperations DBO;
	
	public StoreProxy (){
		this.DBO = new DBOperations();
	}

	public Set<Prodotto> getCatalogo(){
		Set<Prodotto> catalogo = new HashSet<>();
		Connection connection = DBO.getConnection();
		try{
			PreparedStatement statement;
			String query = "SELECT * FROM catalogo JOIN prodotto ON idProdotto=id WHERE idStore=?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, this.getId());
			log.debug(statement);
			ResultSet res = statement.executeQuery();
			
			while(res.next()){
				Prodotto p = new Prodotto(res.getLong("id"), res.getString("nome"), res.getString("descrizione"), res.getDouble("prezzo"));
				catalogo.add(p);
			}
		}catch(SQLException e){
			log.error(e);
			throw new PersistenceException(e.getMessage());
		} finally{
			try {
				connection.close();
			}catch(SQLException e){
				throw new PersistenceException(e.getMessage());
			}
		}
		
		this.setCatalogo(catalogo);
		return super.getCatalogo();
	}
	
}
