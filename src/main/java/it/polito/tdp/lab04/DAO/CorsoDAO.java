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
				
				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi --->
				corsi.add(new Corso(codins, numeroCrediti, nome, periodoDidattico));	
			}
			
			rs.close();
			st.close();
			conn.close();
			
			return corsi;
	
		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String nomeCorso) {
		
		Corso res = null;
		
		final String sql = "SELECT * FROM corso c WHERE c.nome = ? ";

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, nomeCorso);

			ResultSet rs = st.executeQuery();

			if(rs.first())
				res = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
	
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		return res;
		
	}

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 * Quindi modifico il void perchè mi deve ritornare una lista di Studenti;
	 * Ciò che, solitamente, faccio è copiare ed incollare ciò che si fa di solito 
	 * per un collegamento al database, che è gia fatto sopra in getTuttiICorsi 
	 * e lo vado a MODIFICARE;
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
			
			final String sql = " SELECT * "
					+ " FROM iscrizione, studente "
					+ " WHERE iscrizione.matricola=studente.matricola AND codins = ? ";
			
			// Ritorneremo una lista di studenti quindi : 
			List<Studente> studenti = new LinkedList<Studente>();

			try {
				Connection conn = ConnectDB.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);
				
				/* Una differenza è che qui dobbiamo andare ad inserire nella stringa sql,
				ovvero in ciò che andremo poi a chiedere al database, una parte e lo facciamo
				con setString; */
				st.setString(1, corso.getCodins());
				
				/* Poi facciamo executeQuery, finchè avremo un altro risultato
				 * andremo ad aggiungere alla lista di studenti, lo studente che abbiamo creato; */
				ResultSet rs = st.executeQuery();

				while (rs.next()) {

					studenti.add(new Studente(rs.getInt("matricola"), rs.getString("nome"), rs.getString("cognome"), rs.getString("cds")));
					// Creato un nuovo studente da aggiungere alla lista studenti;
				}
				conn.close();
			
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Errore Db", e);
			}
			
			return studenti;
			
	}

	/*
	 * Data una matricola ed il codice insegnamento, iscrivi lo studente al corso.
	 */
	
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {

		final String sql = "INSERT IGNORE INTO iscrizione VALUES(?, ?) ";

		boolean toreturn = false; // ciò che ritorno che inizializzo a false;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			// i valori da aggiungere in questo caso sono due : matricola e codins
			st.setInt(1, studente.getMatricola());
			st.setString(2, corso.getCodins());

			// vado ad aggiugerlo con executeUpdate();
			int rs = st.executeUpdate();

			if (rs==1) {
				
				toreturn = true;
			}
			
			conn.close();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
		return toreturn;
	}

}
