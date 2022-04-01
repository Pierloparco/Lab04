package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	// Creo le instanze DAO
	private StudenteDAO studenteDAO;
	private CorsoDAO corsoDAO;
	
	public Model () {
		studenteDAO= new StudenteDAO();
		corsoDAO = new CorsoDAO();	
	}

	public List<Corso> getTuttiICorsi() {	// Per riempire la nostra ComboBox
		return corsoDAO.getTuttiICorsi();
	}
	
	public Studente getStudente(int matricola) {
		return studenteDAO.getStudente(matricola);
	}
	
	public List<Studente> studentiIscrittiAlCorso(Corso corso) {
		return corsoDAO.getStudentiIscrittiAlCorso(corso);
	}
	
	public List<Corso> cercaCorsiDatoStudente(Studente studente) {
		return studenteDAO.getCorsiFromStudente(studente);
	}
	
	public boolean isStudenteIscrittoAlCorso(Studente studente, Corso corso) {
		return studenteDAO.isStudenteIscrittoAlCorso(studente, corso);
	}
	
	public boolean iscriviStudenteAlCorso(Studente studente, Corso corso) {
		return corsoDAO.inscriviStudenteACorso(studente, corso);
	}
	

}
