package it.polito.tdp.lab04.model;

public class Studente {

	private String matricola; 
	private String cognome; 
	private String nome; 
	private String CDS;
	
	public Studente(String matricola, String cognome, String nome, String cDS) {
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		CDS = cDS;
	}

	public String getMatricola() {
		return matricola;
	}

	public String getCognome() {
		return cognome;
	}

	public String getNome() {
		return nome;
	}

	public String getCDS() {
		return CDS;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCDS(String cDS) {
		CDS = cDS;
	}

	@Override
	public String toString() {
		return "Studente [matricola=" + matricola + ", cognome=" + cognome + ", nome=" + nome + ", CDS=" + CDS + "]";
	} 
	
	
	
	
	
}
