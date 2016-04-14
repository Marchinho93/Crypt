package it.uniroma3.persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class Utility {
	final static private Logger log = Logger.getLogger(Utility.class);
	private DBOperations DBO;
	
	public Utility(){
		 this.DBO= new DBOperations();
	}
	
	public void clearProducts(){
		Connection connection = this.DBO.getConnection();
		try{
			PreparedStatement statement;
			String query = "DELETE FROM prodotto";
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
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
	
	public void clearStores(){
		Connection connection = this.DBO.getConnection();
		try{
			PreparedStatement statement;
			String query = "DELETE FROM store";
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
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
	
	public void clearCatalogs(){
		Connection connection = this.DBO.getConnection();
		try{
			PreparedStatement statement;
			String query = "DELETE FROM catalogo";
			statement = connection.prepareStatement(query);
			statement.executeUpdate();
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
	
}
