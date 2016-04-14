package it.uniroma3.model;

import java.util.HashSet;
import java.util.Set;

public class Store {
	private long id;
	private String nome;
	private String categoria;
	private int dipendenti;
	private Set<Prodotto> catalogo = new HashSet<>();
	
	public Store(){}
	
	public Store(long id, String nome, String categoria, int dipendenti){
		this.id = id;
		this.nome = nome;
		this.categoria = categoria;
		this.dipendenti = dipendenti;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public int getDipendenti() {
		return dipendenti;
	}

	public void setDipendenti(int dipendenti) {
		this.dipendenti = dipendenti;
	}

	public Set<Prodotto> getCatalogo() {
		return catalogo;
	}

	public void setCatalogo(Set<Prodotto> catalogo) {
		this.catalogo = catalogo;
	}
	
	public void addProduct(Prodotto prodotto){
		this.getCatalogo().add(prodotto);
	}
	
	public void removeProduct(Prodotto prodotto){
		this.getCatalogo().remove(prodotto);
	}
	
	public String toString(){
		return "[" + this.id + ", " + this.nome + ", " + this.categoria + ", " + this.dipendenti +"]";
	}
}
