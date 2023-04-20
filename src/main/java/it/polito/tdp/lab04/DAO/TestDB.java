package it.polito.tdp.lab04.DAO;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class TestDB {

	public static void main(String[] args) {

		/*
		 * 	This is a main to check the DB connection
		 */
		
		CorsoDAO cdao = new CorsoDAO();
		cdao.getTuttiICorsi();
		
		Corso c = cdao.getTuttiICorsi().get(0);
		//cdao.getStudentiIscrittiAlCorso(c); 
		StudenteDAO s = new StudenteDAO(); 
		Studente ss = s.getTuttiGliStudenti().get(2); 
		
		System.out.println(ss.getMatricola());
		
		cdao.getCorsiSeguitiDalloStudente(ss); 
	}

}
