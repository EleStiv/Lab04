package it.polito.tdp.lab04.DAO;

import java.sql.*;
import java.util.*;

import it.polito.tdp.lab04.model.*;

public class StudenteDAO {
	
	public List<Studente> getTuttiGliStudenti(){
		
		final String sql = "SELECT * FROM studente"; 
		
		LinkedList<Studente> listaStudenti = new LinkedList<Studente>(); 
		
		
		try {
			Connection conn = ConnectDB.getConnection(); 
			PreparedStatement st = conn.prepareStatement(sql);
			
			ResultSet rt = st.executeQuery(); 
			
			while(rt.next()) {
				
				String mat = rt.getString("matricola"); 
				String cognome = rt.getString("cognome");
				String nome = rt.getString("nome"); 
				String cds = rt.getString("CDS"); 
				
				Studente s = new Studente(mat, cognome, nome, cds); 
				listaStudenti.add(s); 
				
				//System.out.println(s);
			}
			
			rt.close();
			st.close();
			conn.close();
			
			return listaStudenti; 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("errore in StudenteDAO");
			return null; 
		}  
	}
	
	public List<Corso> cercaCorsiPerStudente(Studente s){
		final String sql = "SELECT corso.* "
				+ "FROM iscrizione, corso "
				+ "WHERE corso.codins=iscrizione.codins AND iscrizione.matricola=?; "; 
		
		LinkedList<Corso> corsi = new LinkedList<Corso>(); 
		
		try {
			Connection conn = ConnectDB.getConnection(); 
			PreparedStatement st = conn.prepareStatement(sql); 
			
			st.setString(1, s.getMatricola());
			
			ResultSet rs = st.executeQuery(); 
			
			while (rs.next()) {
				String cod = rs.getString("codins");
				int crediti = rs.getInt("crediti"); 
				String nome = rs.getString("nome"); 
				int periodo = rs.getInt("pd"); 
				
				Corso c = new Corso(cod, crediti, nome, periodo); 
				corsi.add(c); 
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return corsi; 
			
		}catch (SQLException e) {
			e.printStackTrace();
			System.out.println("errore in StudenteDAO");
			return null; 
		}
	}
		
		public Boolean IascrittoAlCorso(Corso c, Studente s) {
			
			final String  sql = "SELECT studente.* "
					+ "FROM iscrizione, studente  "
					+ "WHERE studente.matricola= iscrizione.matricola AND  codins = ?; "; 
			
			try {
				Connection conn = ConnectDB.getConnection(); 
			
				PreparedStatement st = conn.prepareStatement(sql); 
				st.setString(1, c.getCodins());
				
				ResultSet rs = st.executeQuery(); 
				
				while(rs.next()) {
					String matricola = rs.getString("matricola"); 
					if (matricola.compareTo(s.getMatricola())==0 ) {
						return true; 
					}
				}
				
				conn.close();
				return false; 
				
			}catch(SQLException e) {
				e.printStackTrace();
				System.out.println("problemi in StudenteDAO");
				return null; 
			}
		}
	
	

}
