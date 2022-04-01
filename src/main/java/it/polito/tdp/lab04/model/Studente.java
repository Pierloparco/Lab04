package it.polito.tdp.lab04.model;

public class Studente {
	
	/* Faccio le stesse cose che ho fatto per la tabella Corso
	 * E' INUTILE andare a creare la classe "iscrizione" in quanto, 
	 * la matricola ed il codins sono già presenti in studente ed in corso */
	
	private int matricola;
	private String cognome;
	private String nome;
	private String cds;
	
	
	public Studente(int matricola, String cognome, String nome, String cds) {
		super();
		this.matricola = matricola;
		this.cognome = cognome;
		this.nome = nome;
		this.cds = cds;
	}

	public Studente(int matricola) {
		super();
		this.matricola = matricola;
	}

	public int getMatricola() {
		return matricola;
	}

	public void setMatricola(int matricola) {
		this.matricola = matricola;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCds() {
		return cds;
	}

	public void setCds(String cds) {
		this.cds = cds;
	}	

}
