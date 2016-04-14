package it.uniroma3.persistence;

import java.util.Set;

import it.uniroma3.model.Store;

interface StoreDAO {

	public abstract void save(Store store);
	public abstract void update(Store store);
	public abstract void delete(Store store);
	public abstract Store findByPrimaryKey(long id);
	public abstract Set<Store> findAll();
}
