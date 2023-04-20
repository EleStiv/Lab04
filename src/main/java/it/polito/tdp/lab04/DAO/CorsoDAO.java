package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
				
				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico); 
				corsi.add(c); 
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("errore nel leggere i corsi in CorsoDAO");
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
		
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		String codiceCorso = corso.getCodins(); 
		
		final String sql = "SELECT studente.* "
				+ "FROM iscrizione, corso, studente "
				+ "WHERE  iscrizione.codins= corso.codins AND iscrizione.codins = ? AND iscrizione.matricola = studente.matricola; "; 
		
		List<Studente> listaStudentiIscritti = new LinkedList(); 
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, codiceCorso);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String matricola = rs.getString("matricola"); 
				String cognome = rs.getString("cognome"); 
				String nome = rs.getString("nome"); 
				String CDS = rs.getString("CDS");

				System.out.println(matricola);

				
				Studente s = new Studente(matricola, cognome, nome, CDS); 
				listaStudentiIscritti.add(s); 
			}
			conn.close();
			
			return listaStudentiIscritti;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			System.out.println("errore nel leggere gli studenti iscritti in CorsoDAO");
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		// ritorna true se l'iscrizione e' avvenuta con successo
		return false;
	}
	
	public List<Corso> getCorsiSeguitiDalloStudente(Studente s){
		
		String sql = "SELECT corso.*"
				+ "FROM iscrizione, corso"
				+ "WHERE iscrizione.codins = corso.codins AND iscrizione.matricola = ?; "; 
		
		List<Corso> listaCorsiSeguiti = new LinkedList(); 
		
		try {
			Connection conn = ConnectDB.getConnection(); 
			PreparedStatement st = conn.prepareStatement(sql); 
			
			st.setString(1, s.getMatricola());
			ResultSet rs = st.executeQuery(); 
			
			while(rs.next()) {
				System.out.println(rs.getString("codins"));
			}
			
			conn.close();
			
			return listaCorsiSeguiti; 
		}catch(SQLException e ) {
			System.out.println("errore nel leggere i corsi a cui lo studente è iscritto in CorsoDAO");
			throw new RuntimeException("Errore Db", e);
		} 
	}

}
