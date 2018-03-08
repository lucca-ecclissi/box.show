package br.fatec.pi.guiadm;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ItemQuestoesCtrl implements Initializable {

    @FXML
    private Label lblAula;

    @FXML
    private Label lblTitulo;

    @FXML
    private JFXButton btnEditar;

    @FXML
    private JFXButton btnRemover;
    
    public JFXButton remover, editar;
    
    @Override
	public void initialize(URL location, ResourceBundle resources) {
    	remover = btnRemover;
    	editar = btnEditar;
	}
    
    public void setTexts(String titulo,String aula) {
    	lblAula.setText(aula);
    	lblTitulo.setText(titulo);
    }

}
