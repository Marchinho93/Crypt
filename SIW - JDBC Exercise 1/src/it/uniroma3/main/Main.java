package it.uniroma3.main;

import java.util.Set;

import it.uniroma3.model.Prodotto;
import it.uniroma3.model.Store;
import it.uniroma3.persistence.*;
public class Main {

	public static void main(String[] args) {
		ProdottoDaoJDBC products = new ProdottoDaoJDBC();
		StoreDaoJDBC stores = new StoreDaoJDBC();
		CatalogoDaoJDBC catalogs = new CatalogoDaoJDBC();
		
		Prodotto p1 = new Prodotto(1, "Penna USB", "Componenti PC", 15.00);
		Prodotto p2 = new Prodotto(2, "Scheda Video", "Componenti PC", 250.00);
		Prodotto p3 = new Prodotto(3, "Carne", "Alimentari", 5.00);
		Prodotto p4 = new Prodotto(4, "Pasta", "Alimentari", 2.00);
		Prodotto p5 = new Prodotto(5, "Concime", "Coltivazione", 10.00);
		
		products.save(p1);
		products.save(p2);
		products.save(p3);
		products.save(p4);
		products.save(p5);
		
		Store s1 = new Store(1, "PC Electronics", "Componenti PC", 8);
		Store s2 = new Store(2, "Computer Store", "Componenti PC", 3);
		Store s3 = new Store(3, "Coop", "Alimentari", 10);
		Store s4 = new Store(4, "Conad", "Alimentari", 5);
		Store s5 = new Store(5, "Consorzio Agrario", "Coltivazione", 2);
		
		stores.save(s1);
		stores.save(s2);
		stores.save(s3);
		stores.save(s4);
		stores.save(s5);
		
		catalogs.link(s1, p1);
		catalogs.link(s1, p2);
		catalogs.link(s2, p2);
		catalogs.link(s3, p3);
		catalogs.link(s3, p4);
		catalogs.link(s4, p3);
		catalogs.link(s5, p5);
		
		System.out.println("Il prodotto Carne è venduto da:");
		for(Store s:catalogs.findStoreFromProduct(p3)){
			System.out.println(s.toString());
		}
		
		System.out.println("Lista prodotti:");
		for(Prodotto p:products.findAll()){
			System.out.println(p.toString());
		}
		
		System.out.println("Negozio Coop:");
		Store tempStore = stores.findByPrimaryKey(3);
		System.out.println(tempStore.toString());
		
		System.out.println("Prodotti venduti da Coop:");
		Set<Prodotto> tempCatalog = tempStore.getCatalogo();
		for(Prodotto p:tempCatalog){
			System.out.println(p.toString());
		}
		
		Utility utility = new Utility();
		utility.clearProducts();
		utility.clearStores();
		utility.clearCatalogs();
	}
}
