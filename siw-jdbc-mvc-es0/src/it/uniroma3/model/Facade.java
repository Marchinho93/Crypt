package it.uniroma3.model;

import it.uniroma3.persistence.StudenteDaoJDBC;

public class Facade {
	
	public void inserisciStudente(Studente studente){
		StudenteDaoJDBC studenteDao = new StudenteDaoJDBC();
		studenteDao.save(studente);	
	}
}
