package it.uniroma3.persistence;

import java.util.Set;

import it.uniroma3.model.Prodotto;
import it.uniroma3.model.Store;

public interface CatalogoDAO {

	public abstract void link(Store store, Prodotto prodotto);
	public abstract void unLink(Store store, Prodotto prodotto);
	public abstract Set<Store> findStoreFromProduct(Prodotto prodotto);
}
