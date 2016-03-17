package it.uniroma3.main;

import it.uniroma3.model.Prodotto;
import it.uniroma3.persistence.*;
public class Main {

	public static void main(String[] args) {
		Prodotto p = new Prodotto(0001,"gatto", "animale", 150);
		Prodotto p2 = new Prodotto(0002,"cane", "animale", 150);
		ProdottoDaoJDBC prodotti = new ProdottoDaoJDBC();
		
		prodotti.save(p);
		prodotti.save(p2);
		
		prodotti.update(new Prodotto(0001,"leone", "animale", 150));
		
		System.out.println(prodotti.findByPrimaryKey((long) 0001).toString());
		
		System.out.println(prodotti.findAll().toString());
		
		prodotti.delete(p);
		prodotti.delete(new Prodotto(0001,"leone", "animale", 150));
	}
}
