package it.polito.tdp.librettovoti;

import java.net.URL;
import java.util.*;

import it.polito.tdp.librettovoti.model.Libretto;
import it.polito.tdp.librettovoti.model.Voto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Libretto model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<Integer> cmbPunti;	//Una ComboBox contiene una serie di oggetti (lista di elementi)

    @FXML
    private TextField txtNome;

    @FXML
    private Label txtStatus;
    
    @FXML
    private TextArea txtVoti;

    @FXML
    void handleNuovoVoto(ActionEvent event) {
    	
    	//1. Acquisizione e controllo dati
    	String nome = txtNome.getText();
    	Integer punti = cmbPunti.getValue();
    	
    	//Controlli di Validità
    	if(nome.equals("") || punti == null) {
    		//Errore, non posso eseguire l'operazione
    		txtStatus.setText("ERRORE: occorre inserire nome e voto");
    		return;
    	}
    	
    	//2. Esecuzione dell'operazione(== chiedere al Model di farla)
    	boolean ok = model.add(new Voto(nome, punti));
    	
    	//3. Visualizzazione/Aggiornamento del risultato
    	if(ok == true) {
    		List<Voto> voti = model.getVoti();
    		txtVoti.clear();
    		txtVoti.appendText("Hai superato " + voti.size() + " esami.\n");
    		for(Voto v : voti) {
    			txtVoti.appendText(v.toString()+"\n");
    		}
    	
    		txtNome.clear();
    		cmbPunti.setValue(null);
    		txtStatus.setText("");
    	}	
    	else {
    		txtStatus.setText("ERRORE: esame già presente!");
    	}
    }
    
    public void setModel(Libretto model) {	//Metodo dell'oggetto Controller
    	this.model = model;
    }

    @FXML
    void initialize() {
        assert cmbPunti != null : "fx:id=\"cmbPunti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtStatus != null : "fx:id=\"txtStatus\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtVoti != null : "fx:id=\"txtVoti\" was not injected: check your FXML file 'Scene.fxml'.";

        cmbPunti.getItems().clear();	//getItems() = lista di elementi
        for(int p = 18; p <= 30; p++) {
        	cmbPunti.getItems().add(p);
        }
        
    }

}
