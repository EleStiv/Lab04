package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.*;

public class Model {

	private CorsoDAO corso; 
	private StudenteDAO studente; 
	
	public Model() {
		this.corso = new CorsoDAO(); 
		this.studente = new StudenteDAO(); 
	}
	
	public List<Corso> getCorsi(){
		return this.corso.getTuttiICorsi(); 
	}
	
	public List<Studente> getStudenti(){
		return this.studente.getTuttiGliStudenti(); 
	}
	
	public List<Studente> getStudentiIscrittiAlCorso(Corso c){
		return this.corso.getStudentiIscrittiAlCorso(c); 
	}
	
	public List<Corso> getCorsiPerStudente(Studente s){
		return this.studente.cercaCorsiPerStudente(s); 
	}
	
	public boolean iscrittoAlCorso(Corso c, Studente s ) {
		return this.studente.IascrittoAlCorso(c, s); 
	}
}
