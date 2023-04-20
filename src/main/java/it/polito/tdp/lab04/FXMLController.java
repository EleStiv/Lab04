package it.polito.tdp.lab04;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model; 
	
	public void setModel(Model model) {
		this.model = model; 
		
		this.comboCorsi.getItems().add(" "); 
        for(Corso c : model.getCorsi()) {
        	 this.comboCorsi.getItems().add(c.getNome()); 
        }
        
        //this.model.getStudenti(); 
	}

	@FXML
    private CheckBox checkBox;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCercaCorsi;

    @FXML
    private Button btnCercaIscritti;

    @FXML
    private Button btnIscrivi;

    @FXML
    private Button btnReset;

    @FXML
    private ComboBox<String> comboCorsi;

    @FXML
    private TextField txtCognome;

    @FXML
    private TextField txtMatricolaStudente;

    @FXML
    private TextField txtNome;

    @FXML
    private TextArea txtRisultato;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	boolean esiste = false; 
    	
    	String mat = this.txtMatricolaStudente.getText(); 
    	Studente stud = null; 
    	for(Studente s: model.getStudenti()) {
    		if(s.getMatricola().compareTo(mat)==0) {
    			stud = s; 
    			esiste = true; 
    		}
    	}
    	
    	if(esiste) {
    		List<Corso> l = model.getCorsiPerStudente(stud); 
    		for(Corso c: l) {
    			//if(txtRisultato.getText().compareTo("")!=0)
    				//txtRisultato.setText("\n"+c.toString());
    			//else
    				txtRisultato.appendText(c.toString() + "\n");
    		}
    	}
    	else 
    		this.txtRisultato.setText("la matricola selezionata non esiste");
    }

    @FXML
    void doCercaIscrittiCorso(ActionEvent event) {
    	String nomeCorso = this.comboCorsi.getValue(); 
    	Corso corso = null; 
    	
    	if(nomeCorso.compareTo(" ")==0)
    		txtRisultato.setText("non hai selezionato un corso");
    	
    	for(Corso c : this.model.getCorsi()) {
    		if(c.getNome().equals(nomeCorso)) {
    			corso = c; 
    		}
    	}
    	
    	if (this.txtMatricolaStudente.getText().compareTo("")==0) {
    	
    	List<Studente> lista = this.model.getStudentiIscrittiAlCorso(corso); 
    	
    	String r = ""; 
    	
    	for(Studente s : lista) {
    		if(r!="") {
    			r=r+"\n"+ s.getCognome() +" "+ s.getNome(); 
    		}
    		else
    			r = s.getCognome() +" "+ s.getNome(); 
    	}
    	this.txtRisultato.setText(r); 
    	
    	}else {
    		String matricola = this.txtMatricolaStudente.getText(); 
    		Studente stud = null; 
    		for(Studente s : model.getStudenti()) {
    			if(s.getMatricola().compareTo(matricola)==0)
    				stud = s; 
    		}
    		
    		if(model.iscrittoAlCorso(corso, stud)){
    			this.txtRisultato.setText("lo studente è già iscritto al corso");
    		}
    		else {
    			this.txtRisultato.setText("lo studente non è iscritto a questo corso");
    		}
    	}
    }
    
    @FXML
    void doCheck(ActionEvent event) {
    	
    	String mat = this.txtMatricolaStudente.getText(); 
    	boolean esiste = false; 
    	for(Studente s: this.model.getStudenti()) {
    		if(mat.compareTo(s.getMatricola())==0) {
    			this.txtNome.setText(s.getNome());
    			this.txtCognome.setText(s.getCognome());
    			esiste = true; 
    		}
    	if(!esiste)
    		this.txtRisultato.setText("non esiste lo studente corrispondente");
    	}

    }

    @FXML
    void doIscrivi(ActionEvent event) {

    }

    @FXML
    void doReset(ActionEvent event) {
    	this.txtCognome.clear();
    	this.txtMatricolaStudente.clear();
    	this.txtNome.clear();
    	this.txtRisultato.clear();
    	this.checkBox.setSelected(false);

    }

    @FXML
    void selezionaCorso(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert btnCercaCorsi != null : "fx:id=\"btnCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaIscritti != null : "fx:id=\"btnCercaIscritti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnIscrivi != null : "fx:id=\"btnIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert comboCorsi != null : "fx:id=\"comboCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricolaStudente != null : "fx:id=\"txtMatricolaStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert checkBox != null : "fx:id=\"checkBox\" was not injected: check your FXML file 'Scene.fxml'.";

        
       
    }

}
