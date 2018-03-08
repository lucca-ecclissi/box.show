package br.fatec.pi.guiadm;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ItemAdms implements Initializable {

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblNome;

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
	
	public void setTexts(String email,String nome) {
    	lblNome.setText(nome);
    	lblEmail.setText(email);
    }

}
