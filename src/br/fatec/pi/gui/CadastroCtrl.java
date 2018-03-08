package br.fatec.pi.gui;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import br.fatec.pi.evento.PessoaDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

public class CadastroCtrl implements Initializable {


    @FXML
    private JFXPasswordField txtSenha;

    @FXML
    private JFXPasswordField txtConfSenha;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXTextField txtConfEmail;

    @FXML
    private JFXTextField txtEmail;

    @FXML
    private JFXTextField txtTelefone;    
    
    @FXML
    private JFXTextField txtNome;
    
	public JFXPasswordField getTxtSenha() {
		return txtSenha;
	}

	public JFXPasswordField getTxtConfSenha() {
		return txtConfSenha;
	}

	public JFXButton getBtnVoltar() {
		return btnVoltar;
	}

	public JFXTextField getTxtConfEmail() {
		return txtConfEmail;
	}

	public JFXTextField getTxtEmail() {
		return txtEmail;
	}

	public JFXTextField getTxtTelefone() {
		return txtTelefone;
	}

	public JFXTextField getTxtNome() {
		return txtNome;
	}
	

    @FXML
    private void cadastrar(ActionEvent event) {
    	if(txtNome.validate() && txtConfEmail.validate() && txtConfSenha.validate() && txtEmail.validate() && txtTelefone.validate()) {
    		if(!txtConfEmail.getText().equals(txtEmail.getText())) {
    			Notifications notification = Notifications.create()
	 					.title("Email")
	 					.text("Email não confere!")
	 					.position(Pos.TOP_RIGHT);
	 			
	 			notification.showWarning();
    		}else if(!txtSenha.getText().equals(txtConfSenha.getText())) {
    			Notifications notification = Notifications.create()
	 					.title("Senha")
	 					.text("Senha não confere!")
	 					.position(Pos.TOP_RIGHT);
	 			
	 			notification.showWarning();
    		}else{
    			PessoaDAO pessoa = new PessoaDAO();
    			pessoa.setEmailPessoa(txtEmail.getText());
    			pessoa.setSenhaPessoa(txtSenha.getText());
    			pessoa.setTelefonePessoa(txtSenha.getText());
    			pessoa.setNomePessoa(txtNome.getText());
    			pessoa.setTipo("S");
    			if(pessoa.cadastrar() != 0) {
    				Notifications notification = Notifications.create()
    	 					.title("Cadastro")
    	 					.text("Cadastro realizado com Sucesso!")
    	 					.position(Pos.TOP_RIGHT);
    	 			
    	 			notification.showConfirm();
    	 			btnVoltar.getOnAction().handle(event);
    			}else {
    				Notifications notification = Notifications.create()
    	 					.title("Cadastro")
    	 					.text("Erro ao realizar Cadastro!")
    	 					.position(Pos.TOP_RIGHT);
    	 			
    	 			notification.showError();
    			}
    		}
    	}
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		valida(txtConfEmail, "Confirmar Email é Obrigatorio!");
		valida(txtEmail, "Email é Obrigatorio!");
		valida(txtTelefone, "Telefone é Obrigatorio!");
		valida(txtConfSenha, "Confirmar Senha é Obrigatorio!");
		valida(txtSenha, "Senha é Obrigatorio!");
		valida(txtNome, "Nome é Obrigatorio!");
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
