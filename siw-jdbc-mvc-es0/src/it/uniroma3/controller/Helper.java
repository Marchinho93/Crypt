package it.uniroma3.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.DateValidator;


public class Helper{
	public boolean validate(HttpServletRequest request,
			HttpServletResponse response){
		boolean corretto = true;
		String nome, cognome, dataDiNascita, matricola;
		String nomeError, cognomeError, dataDiNascitaError, matricolaError;
		nome = request.getParameter("nome");
		cognome = request.getParameter("cognome");
		dataDiNascita = request.getParameter("dataDiNascita");
		matricola = request.getParameter("matricola");
		DateValidator validator = new DateValidator();
		if(nome.equals("")){
			corretto = false;
			nomeError = "Nome: Campo Obbligatorio";
			request.setAttribute("nomeError", nomeError);
		}
		if(cognome.equals("")){
			corretto = false;
			cognomeError = "Cognome: Campo Obbligatorio";
			request.setAttribute("cognomeError", cognomeError);
		}
		if(dataDiNascita.equals("")){
			corretto = false;
			dataDiNascitaError = "DataDiNascita: Campo Obbligatorio";
			request.setAttribute("dataDiNascitaError", dataDiNascitaError);
		}
		if(validator.validate(dataDiNascita)==null){
			corretto = false;
			dataDiNascitaError = "Formato data non valido";
			request.setAttribute("dataDiNascitaError", dataDiNascitaError);
		}
		if(matricola.equals("")){
			corretto = false;
			matricolaError = "Matricola: Campo Obbligatorio";
			request.setAttribute("matricolaError", matricolaError);
		}

		return corretto;
	}
}
