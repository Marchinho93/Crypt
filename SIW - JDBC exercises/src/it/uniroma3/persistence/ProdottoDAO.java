package it.uniroma3.persistence;

import it.uniroma3.model.Prodotto;

import java.util.List; 

	public interface ProdottoDAO {	
		public static void save(Prodotto prodotto){};
		public static void delete(Prodotto prodotto){};
		public static void update(Prodotto prodotto){};
		static Prodotto findByPrimaryKey(Long id){
			return null;};
	 	static List<Prodotto> findAll(){
			return null;};
	}