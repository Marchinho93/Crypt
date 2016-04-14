package it.uniroma3.persistence;

import it.uniroma3.model.Prodotto;

import java.util.List; 

	public interface ProdottoDAO {	
	public void save(Prodotto prodotto);
	public void delete(Prodotto prodotto);
	public void update(Prodotto prodotto);
	Prodotto findByPrimaryKey(Long id);
	List<Prodotto> findAll();
	}