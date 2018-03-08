package br.fatec.pi.gui;

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
import javafx.scene.image.ImageView;

public class LoginCtrl  implements Initializable {
	
    @FXML
    private ImageView imgLogo;

    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    private JFXButton btnLogin;

	@FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXButton btnCadastro;
    
    public JFXPasswordField getTxtSenha() {
    	return txtSenha;
    }
    
    public JFXButton getBtnLogin() {
    	return btnLogin;
    }
    
    public JFXTextField getTxtEmail() {
    	return txtEmail;
    }
    
    public JFXButton getBtnCadastro() {
    	return btnCadastro;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		valida(txtEmail, "Email é Obrigatorio!");
		valida(txtSenha, "Senha é Obrigatorio!");
		
	}
    
	private void valida(JFXTextField txt, String men) {
		RequiredFieldValidator validate = new RequiredFieldValidator();
		
		txt.getValidators().add(validate);
		
		validate.setMessage(men);
		
		txt.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					txt.validate();
				}
			}
		});
		
	}	

	private void valida(JFXPasswordField txt, String men) {
		RequiredFieldValidator validate = new RequiredFieldValidator();
		
		txt.getValidators().add(validate);
		
		validate.setMessage(men);
		
		txt.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					txt.validate();
				}
			}
		});
		
	}	
}
