package it.uniroma3.main;

import it.uniroma3.model.Prodotto;
import it.uniroma3.persistence.*;
public class Main {

	public static void main(String[] args) {
		Prodotto p = new Prodotto(0001,"gatta", "animale", 150);
		Prodotto p2 = new Prodotto(0002,"cana", "animale", 150);
		ProdottoDaoJDBC prodotti = new ProdottoDaoJDBC();
		System.out.println(prodotti.findByPrimaryKey((long)0001).toString());
	}
}
