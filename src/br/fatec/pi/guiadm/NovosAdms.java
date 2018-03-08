package br.fatec.pi.guiadm;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import br.fatec.pi.entidade.Pessoa;
import br.fatec.pi.evento.PessoaDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

public class NovosAdms implements Initializable {

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
    
    public JFXButton getBtnVoltar() {
		return btnVoltar;
	}
	public Pessoa pessoa;

    @FXML
    private void cadastrar(ActionEvent event) {
    	if(txtConfEmail.validate() && txtEmail.validate() 
    			&& txtNome.validate() && txtSenha.validate()
    			&& txtTelefone.validate() && txtConfSenha.validate()) {
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
    			pessoa.setTipo("A");
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

    @FXML
    private void limpar(ActionEvent event) {
    	limpaInfo();
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		valida(txtConfEmail, "Confirmar Email é obrigatorio!");
		valida(txtEmail, "Email é obrigatorio!");
		valida(txtTelefone, "Telefone é obrigatorio!");
		valida(txtNome, "Nome é obrigatorio!");
		valida(txtSenha, "Senha é obrigatorio!");
		valida(txtSenha, "Confirmar Senha é obrigatorio!");
	}
	

	public void showInfo() {
		txtConfEmail.setText(pessoa.getEmailPessoa());
		txtConfSenha.setText(pessoa.getSenhaPessoa());
		txtEmail.setText(pessoa.getEmailPessoa());
		txtNome.setText(pessoa.getNomePessoa());
		txtSenha.setText(pessoa.getSenhaPessoa());
		txtTelefone.setText(pessoa.getTelefonePessoa());
	}
	
	public void limpaInfo() {
		txtConfEmail.setText("");
		txtConfSenha.setText("");
		txtEmail.setText("");
		txtNome.setText("");
		txtSenha.setText("");
		txtTelefone.setText("");
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
