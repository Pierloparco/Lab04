package it.polito.tdp.lab04;

import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	// Andiamo a crearci il modello e lista corsi;
	private Model model;
	private List<Corso> corsi;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Corso> comboCorso;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCercaIscrittiCorsi;

    @FXML
    private Button btnCercaNome;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnReset;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	
    	// Prima cosa da fare, cancellare qualsiasi cosa sia scritta precedentemente
    	txtRisultato.clear();
    	
    	try {
    		// vado a prendere la mia matricola che è scritta in txtMatricola e la prendo con .getText();
    		int matricola = Integer.parseInt(txtMatricola.getText());
    		
    		// Adesso mi prendo lo studente, data la matricola
    		Studente studente = model.getStudente(matricola);
    		
    		// Se lo studente non esiste, quindi la mia richiesta mi ha dato NULL
    		if (studente == null) {
    			
    			// Allora ad output vado a dire, su txtResult;
    			txtRisultato.appendText("Nessun risultato : matricola inesistente");
    			return;
    		}
    		
    		// Altrimenti, se non è entrato nel mio if, ci sarà una lista di corsi da prendere
    		List<Corso> corsi = model.cercaCorsiDatoStudente(studente);
    		/* Da questa lista di corsi, vado a creare qualcosa da dare ad output 
    		 * al nostro user; */
    		StringBuilder sb = new StringBuilder(); 
    		
    		for (Corso corso : corsi) {
    			/* Per ogni corso presente nella lista il for --->
    			 * Poi facciamo String.format per vedere che sia stato scritto nel formato corretto
    			 */
    			sb.append(String.format("%-8s ", corso.getCodins()));
    			sb.append(String.format("%-4s ", corso.getCrediti()));
    			sb.append(String.format("%-45s ", corso.getNome()));
    			sb.append(String.format("%-4s ", corso.getPd()));
    			sb.append("\n");
    		}
    		
    		txtRisultato.appendText(sb.toString());
    		
    	} catch(NumberFormatException e) {
    		txtRisultato.setText("Inserire una matricola numerica"); // Con setText perché così vado anche a cancellare tutto ciò scritto prima 
    	} catch(RuntimeException e) {
    		txtRisultato.setText("ERRORE DI CONNESSIONE AL DB");
    	}

    }

    @FXML
    void doCercaIscrittiCorsi(ActionEvent event) {
    	
    	txtRisultato.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    	try {
    		/* Prendo un corso dalla comboBox e controllo se il corso
    		 * non è stato selezionato, dico allo user di selezionarne uno sempre
    		 * con setText, altrimenti non entra nell'if quindi il corso è stato selezionato e vado
    		 * a creare la lista di studenti che è ciò che ritorneremo;
    		 */
    		
    		Corso corso = comboCorso.getValue();
    		
    		if(corso == null) {
    			txtRisultato.setText("Selezionare un corso.");
    		}
    		
    		List<Studente> studenti = model.studentiIscrittiAlCorso(corso);
    		
    		StringBuilder sb = new StringBuilder();
    		
    		for(Studente studente : studenti) {
    			sb.append(String.format("%-10s ", studente.getMatricola()));
    			sb.append(String.format("%-20s ", studente.getCognome()));
    			sb.append(String.format("%-20s ", studente.getNome()));
    			sb.append(String.format("%-10s ", studente.getCds()));
    			sb.append("\n");
    		}
    		
    		txtRisultato.appendText(sb.toString());
    		
    	} catch(RuntimeException e) {
    		txtRisultato.setText("ERRORE DI CONNESSIONE AL DB"); 
    	}

    }

    @FXML
    void doCercaNome(ActionEvent event) {
    	
    	txtRisultato.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	
    	try {
    		/* Partiamo dalla matricola e vogliamo cercare nome della matricola
    		 * successivamente andiamo a cercare lo studente,
    		 * vediamo se è null, altrimenti se non entra nell'if, 
    		 * abbiamo uno studente !
    		 */
    		int matricola = Integer.parseInt(txtMatricola.getText());
    		
    		Studente studente = model.getStudente(matricola);
    		
    		if(studente == null) {
    			txtRisultato.appendText("Nessun risultato : matricola inesistente.");
    			return;
    		}
    		
    		txtNome.setText(studente.getNome());
    		txtCognome.setText(studente.getCognome());
    		
    	} catch(NumberFormatException e) {
    		txtRisultato.setText("Inserire una matricola numerica");
    	} catch(RuntimeException e) {
    		txtRisultato.setText("ERRORE DI CONNESSIONE AL DB"); 
    	}

    }

    @FXML
    void doIscrivi(ActionEvent event) {
    	
    	txtRisultato.clear();
    	
    	try {
    		/* Se la matricola è vuota */
    		if(txtMatricola.getText().isEmpty()) {
    			txtRisultato.setText("Inserire una matricola.");
    			return;
    		}
    		/* Se non ho inserito nessun corso di studi*/
    		if(comboCorso.getValue()==null) {
    			txtRisultato.setText("Selezionare un corso.");
    			return;
    		}
    		
    		int matricola = Integer.parseInt(txtMatricola.getText());
    		
    		Studente studente = model.getStudente(matricola);
    		
    		if(studente==null) {
    			txtRisultato.setText("Nessun risultato : matricola inesistente. ");
    			return; 
    		}
    		
    		// Se non entra nell'if, prendiamo nome e cognome
    		txtNome.setText(studente.getNome());
    		txtCognome.setText(studente.getCognome());
    		
    		// Prendo il corso dal comboCorso
    		Corso corso = comboCorso.getValue();
    		
    		// Se il nostro studente è già iscritto al corso, è vero
    		if(model.isStudenteIscrittoAlCorso(studente, corso)) {
    			txtRisultato.appendText("Studente già iscritto a questo corso. ");
    			return;
    		}
    		
    		// Altrimenti se non è iscritto
    		if(!model.iscriviStudenteAlCorso(studente, corso)) {
    			txtRisultato.appendText("Errore durante l'iscrizione. ");
    			return;
    		} else {
    			txtRisultato.appendText("Studente iscritto correttamente. ");
    			return;
    		}
    		
    		
    	} catch(NumberFormatException e) {
    		txtRisultato.setText("Inserire una matricola numerica");
    	} catch(RuntimeException e) {
    		txtRisultato.setText("ERRORE DI CONNESSIONE AL DB"); 
    	}

    }

    @FXML
    void doReset(ActionEvent event) {
    	
    	// Ci va a cancellare TUTTO
    	txtMatricola.clear();
    	txtNome.clear();
    	txtCognome.clear();
    	txtRisultato.clear();
    	comboCorso.getSelectionModel().clearSelection();

    }
    
    @FXML
    void initialize() {
        assert comboCorso != null : "fx:id=\"ComboCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscrittiCorsi != null : "fx:id=\"btnCercaIscrittiCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaNome != null : "fx:id=\"btnCercaNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    /* Prima di tutto con un metodo public che ritorni void (perchè non ho bisogno che ritorni nulla) e 
     * prendendo il modello in input, vado a SETTARE il mio modello
     */
    public void setModel(Model model) {
    	this.model=model;
    	corsi= model.getTuttiICorsi();
    	Collections.sort(corsi);	// Avevamo sovrascritto compareTo nella creazione di corso in modo che si mettano ora in ordine alfabetico;
    	comboCorso.getItems().addAll(corsi);
    }

}

