package br.fatec.pi.guiadm;

import java.net.URL;
import java.util.ResourceBundle;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import br.fatec.pi.entidade.Aulas;
import br.fatec.pi.evento.AulasDAO;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;

public class NovasAulasCtrl implements Initializable {

	@FXML
	private JFXButton btnVoltar;
	
	@FXML
	private JFXTextField txtNome;

	@FXML
	private JFXTextArea txtDescricao;

    @FXML
    private JFXCheckBox cbxProva;
	
	public Aulas aula; 
	
	public void showInfo() {
		txtDescricao.setText(aula.getDescricao());
		txtNome.setText(aula.getNomeAula());
		cbxProva.setSelected(aula.getTipo().equals("P"));
	}
	public void limpaInfo() {
		txtNome.setText("");
    	txtDescricao.setText("");
    	cbxProva.setSelected(false);
    	aula = null;
	}
	
    @FXML
    private void salvar(ActionEvent event) {
    	if(txtDescricao.validate() && txtNome.validate()) {
    		
    		AulasDAO aulas = new AulasDAO();
    		aulas.setNomeAula(txtNome.getText());
    		aulas.setDescricao(txtDescricao.getText());
    		aulas.setTipo((cbxProva.isSelected())?"P":"A");
    		System.out.println(aulas.getTipo());
    		int isOk = 1;
    		
    		if(aula != null) {
    			aulas.setCodAula(aula.getCodAula());
    			isOk = aulas.alterar();
    		}else {
    			isOk = aulas.inserir();
    		}
    		
    		if(isOk != 0) {
				Notifications notification = Notifications.create()
						.title("Salvar")
						.text("Aula salva com Sucesso!")
						.position(Pos.TOP_RIGHT);
				
				notification.showConfirm();
				btnVoltar.getOnAction().handle(event);
    		}else {
    			Notifications notification = Notifications.create()
						.title("Salvar")
						.text("Erro ao salvar Aulas!")
						.position(Pos.TOP_RIGHT);
				notification.showError();
    		}
    	}
    }

    public JFXButton getBtnVoltar() {
		return btnVoltar;
	}

	@FXML
    private void limpar(ActionEvent event) {
    	limpaInfo();
    }
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RequiredFieldValidator validate = new RequiredFieldValidator();
		RequiredFieldValidator validateDes = new RequiredFieldValidator();
		
		txtNome.getValidators().add(validate);
		txtDescricao.getValidators().add(validateDes);
		
		validate.setMessage("Titulo Obrigatorio!");
		validateDes.setMessage("Descrição Obrigatorio!");
		
		txtNome.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					txtNome.validate();
				}
			}
		});
		
		txtDescricao.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(!newValue) {
					txtDescricao.validate();
				}
			}
		});
	}

}
