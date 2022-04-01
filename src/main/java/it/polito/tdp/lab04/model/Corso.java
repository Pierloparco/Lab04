package it.polito.tdp.lab04.model;

public class Corso implements Comparable<Corso> {
	
	// Ricreo ciò che trovo nel database
	private String codins;
	private int crediti;
	private String nome;
	private int pd;
	
	// Creo il costruttore con tutto e uno solo con codins che è anche la chiave;
	public Corso(String codins, int crediti, String nome, int pd) {
		super();
		this.codins = codins;
		this.crediti = crediti;
		this.nome = nome;
		this.pd = pd;
	}
	
	public Corso(String codins) {
		super();
		this.codins = codins;
	}

	public String getCodins() {
		return codins;
	}

	public void setCodins(String codins) {
		this.codins = codins;
	}

	public int getCrediti() {
		return crediti;
	}

	public void setCrediti(int crediti) {
		this.crediti = crediti;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getPd() {
		return pd;
	}

	public void setPd(int pd) {
		this.pd = pd;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		return true;
	}

	// vado a sovrascivere il compareTo tra due corsi ed in particolare sul nome, in modo che 
	// siano in ordine alfabetico
	public int compareTo(Corso corsoIn) {
		return this.nome.compareTo(corsoIn.nome);
	}

	/* Vado a fare anche il toString, ma ritorno SOLO il nome*/
	@Override
	public String toString() {
		// return "Corso [codins=" + codins + ", crediti=" + crediti + ", nome=" + nome + ", pd=" + pd + "]";
		return nome;
	}
	
	
	
	
	
	
	

}
