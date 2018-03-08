package br.fatec.pi.guiadm;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ItemResposta implements Initializable{

	@FXML
    private JFXTextField txtResposta;

    @FXML
    private JFXTextArea txtObs;

    @FXML
    private JFXCheckBox cbxResposta;

    public JFXTextField getTxtResposta() {
		return txtResposta;
	}

	public JFXTextArea getTxtObs() {
		return txtObs;
	}

	public JFXCheckBox getCbxResposta() {
		return cbxResposta;
	}

	public JFXButton getBtnRemover() {
		return btnRemover;
	}

	@FXML
    private JFXButton btnRemover;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		valida(txtResposta,"Resposta Obrigatoria!");
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

}
