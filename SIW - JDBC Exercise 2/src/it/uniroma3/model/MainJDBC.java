package it.uniroma3.model;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import it.uniroma3.persistence.DatabaseUtility;
import it.uniroma3.persistence.GruppoDaoJDBC;
import it.uniroma3.persistence.IndirizzoDaoJDBC;
import it.uniroma3.persistence.StudenteDao;
import it.uniroma3.persistence.StudenteDaoJDBC;

public class MainJDBC {
	final static Logger logger = Logger.getLogger(MainJDBC.class);

	public static void main(String args[]) {
		Calendar cal = Calendar.getInstance();
		cal.set(1995, Calendar.MARCH, 21); // // 21 marzo 1995
		Date date1 = cal.getTime();
		cal.set(1996, Calendar.APRIL, 12); // 12 aprile 1996
		Date date2 = cal.getTime();
		cal.set(1998, Calendar.OCTOBER, 1);  // 1 ottobre 1998
		Date date3 = cal.getTime();

		StudenteDao studenteDao = new StudenteDaoJDBC();
		GruppoDaoJDBC gruppoDao = new GruppoDaoJDBC();
		IndirizzoDaoJDBC indirizzoDao = new IndirizzoDaoJDBC();

		DatabaseUtility.resetDatabase();
		
		Studente studente1 = new Studente();
		studente1.setCognome("Rossi");
		studente1.setNome("Mario");
		studente1.setMatricola("00000001");
		studente1.setDataNascita(date1);

		Studente studente2 = new Studente();
		studente2.setCognome("Verdi");
		studente2.setNome("Anna");
		studente2.setMatricola("00000002");
		studente2.setDataNascita(date2);

		Studente studente3 = new Studente();
		studente3.setCognome("Bianchi");
		studente3.setNome("Antonio");
		studente3.setMatricola("00000003");
		studente3.setDataNascita(date3);

		Indirizzo indirizzo1 = new Indirizzo();
		indirizzo1.setCitta("Roma");
		indirizzo1.setVia("Piazza del popolo");
		indirizzo1.setNumeroCivico(8);
		studente1.setIndirizzo(indirizzo1);
		
		Gruppo gruppo1 = new Gruppo();
		gruppo1.setNome("Le mele");
		gruppo1.addStudente(studente1);
		gruppo1.addStudente(studente2);

		//CREATE
		studenteDao.save(studente1);
		studenteDao.save(studente2);
		studenteDao.save(studente3);

		gruppoDao.save(gruppo1);
		
		System.out.println("indirizzo1:"+studenteDao.findByPrimaryKey(studente1.getMatricola()).getIndirizzo().toString());

		//RETRIEVE
		System.out.println("Retrieve all gruppo");
		for(Gruppo g : gruppoDao.findAll()) {
			System.out.println(g.getNome());  // NB: non viene invocato il metodo getStudenti()
			System.out.println(".-.-.-.");
			g.addStudente(studente3);
			System.out.println(".-.-.-.");

		}

		System.out.println("Retrieve all gruppo: proxy all'opera");
		for(Gruppo g : gruppoDao.findAll()) {
			System.out.println(g);
		}

//		gruppo1.addStudente(studente3);
//		gruppoDao.update(gruppo1);
		
		System.out.println("Retrieve all gruppo: proxy all'opera");
		for(Gruppo g : gruppoDao.findAll()) {
			System.out.println(g);
		}
		
		System.out.println("Elenco studenti");
		gruppoDao.delete(gruppo1);			
		for(Studente s : studenteDao.findAll()) {
			System.out.println(s);
		}		
		
		System.out.println("Indirizzo : roma piazza del popolo 8");
		System.out.println(studenteDao.findByAddress(indirizzo1).toString());

	}
}
