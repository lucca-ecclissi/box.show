package br.fatec.pi.guiadm;


import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class GUIAdmMainCtrl implements Initializable {
	
	@FXML
	private JFXTextField txtUsuario;
	
	@FXML
	private JFXPasswordField txtSenha;
		
	@FXML 
	private JFXButton btnLogar;
	
	@Override
    public void initialize(URL url, ResourceBundle rb) {
		RequiredFieldValidator userValid = new RequiredFieldValidator();
		RequiredFieldValidator senhaValid = new RequiredFieldValidator();
		
		txtUsuario.getValidators().add(userValid);
		userValid.setMessage("Usuário Obrigatorio!");

		txtSenha.getValidators().add(senhaValid);
		senhaValid.setMessage("Senha Obrigatoria!");
		
		txtSenha.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					txtSenha.validate();
				}
			}
		});
		
		txtUsuario.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					txtUsuario.validate();
				}
			}
		});
	}
	
}
