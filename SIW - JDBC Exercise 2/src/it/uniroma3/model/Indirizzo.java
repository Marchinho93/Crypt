package it.uniroma3.model;

public class Indirizzo {
	String citta;
	String via;
	int numeroCivico;
	
	public Indirizzo(){}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public int getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(int numeroCivico) {
		this.numeroCivico = numeroCivico;
	}
	
	public String toString(){
		return "Città=" + this.citta + ", " +
				"Via=" + this.via + ", " +
				"N=" + this.numeroCivico; 
	}
}
