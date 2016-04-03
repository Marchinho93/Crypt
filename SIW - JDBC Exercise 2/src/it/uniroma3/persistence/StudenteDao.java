package it.uniroma3.persistence;

import java.util.List;

import it.uniroma3.model.Indirizzo;
import it.uniroma3.model.Studente;

public interface StudenteDao {
	
	public void save(Studente studente);  // Create
	public Studente findByPrimaryKey(String matricola);     // Retrieve
	public List<Studente> findAll();       
	public void update(Studente studente); //Update
	public void delete(Studente studente); //Delete	
	public Studente findByAddress(Indirizzo indirizzo);
}
