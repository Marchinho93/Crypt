package it.uniroma3.persistence;

import it.uniroma3.model.Indirizzo;
import it.uniroma3.model.Studente;

public interface IndirizzoDao {

	public void save(Indirizzo indirizzo, String matricola);
	public void delete(String matricola);
	public Indirizzo findByStudent(Studente s);
}
