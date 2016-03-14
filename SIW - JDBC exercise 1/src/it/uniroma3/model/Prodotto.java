package it.uniroma3.model;

public class Prodotto {
	private long id;
	private String nome;
	private String descrizione;
	private double prezzo;
	
	public Prodotto(long id, String nome, String descrizione, double prezzo){
		this.id = id;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo  =prezzo;
	}
	
	public void setId(long id){
		this.id = id;
	}
	
	public void setNome(String nome){
		this.nome = nome; 
	}
	
	public void setCognome(String descrizione){
		this.descrizione = descrizione;
	}
	
	public void setPrezzo(double prezzo){
		this.prezzo = prezzo;
	}
	
	public long getId(){
		return this.id;
	}
	
	public String getNome(){
		return this.nome;
	}
	
	public String getDescrizione(){
		return this.descrizione;
	}
	
	public double getPrezzo(){
		return this.prezzo;
	}
	
	public String toString(){
		return this.id + " - " + this.nome + " - " + this.descrizione + " - " + this.prezzo + "\n";
	}
}
