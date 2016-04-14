package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Studente;

@WebServlet("/controllerStudente")

public class StudenteController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// leggo i parametri
		String matricola = request.getParameter("matricola");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");

		// verifica dei dati
		boolean ciSonoErrori = false;
		String nextPage = null;
		if (matricola.equals("")) {
			ciSonoErrori = true;
			request.setAttribute("matricolaError", "Matricola Obbligatoria");
		}
		if (nome.equals("")) {
			ciSonoErrori = true;
			request.setAttribute("nomeError", "Nome Obbligatorio");
		}
		if (cognome.equals("")) {
			ciSonoErrori = true;
			request.setAttribute("cognomeError", "Cognome Obbligatorio");
		}

		if (ciSonoErrori) {
			nextPage = "/newStudente.jsp";
		} else { // Tutti i dati sono corretti
			Studente studente = new Studente();
			studente.setCognome(cognome);
			studente.setMatricola(matricola);
			studente.setNome(nome);
			request.setAttribute("studente", studente);
			nextPage = "/studente.jsp";
		}
		ServletContext servletContext = getServletContext();
		RequestDispatcher rd = servletContext.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
