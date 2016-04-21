package it.uniroma3.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.validator.routines.DateValidator;

import it.uniroma3.model.Facade;
import it.uniroma3.model.Studente;

public class Action {

	public String execute(HttpServletRequest request){
		Studente studente = new Studente();
		Facade facade = new Facade();
		studente.setNome(request.getParameter("nome"));
		studente.setCognome(request.getParameter("cognome"));
		DateValidator validator = new DateValidator();
		studente.setDataNascita(validator.validate(request.getParameter("dataDiNascita")));
		studente.setMatricola(request.getParameter("matricola"));
		facade.inserisciStudente(studente);
		request.setAttribute("studente", studente);
		return "/studente.jsp";
	}
}
