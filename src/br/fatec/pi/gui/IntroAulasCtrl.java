package br.fatec.pi.gui;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import br.fatec.pi.config.Renderer;
import br.fatec.pi.config.Wrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class IntroAulasCtrl implements Initializable {

	@FXML
    private JFXButton btnContinuar;
	
	@FXML
    private JFXButton btnFinalizar;

    @FXML
    private Label lblTitutlo;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private WebView wvAulas;

	public JFXButton btnVol, btnCont;
    
	public JFXButton getBtnFinalizar() {
		return btnFinalizar;
	}

	private Renderer renderer;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		btnVol = btnVoltar;
		btnCont = btnContinuar;
		Wrapper wrapper = new Wrapper();
		renderer = wrapper.getRenderer();
		wvAulas.setContextMenuEnabled(false);
	}
	
	public void setTexts(String titulo, String descricao) {
		lblTitutlo.setText(titulo);
		WebEngine we = wvAulas.getEngine();
		we.loadContent(renderer.render(descricao), "text/html");
		
	}
	
}
