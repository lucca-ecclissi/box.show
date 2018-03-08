package br.fatec.pi.guiadm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXButton;


import br.fatec.pi.gui.GUIMainCtrl;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class MenuAdm implements Initializable {
	
	@FXML
	private Label lblTitle;
	
	@FXML
	private ScrollPane spCenter;

	@FXML
	private AnchorPane  panel;
	
	@FXML
	private AnchorPane bpPane;
	
	private FXMLLoader loadBPanel;
	
	private String id;
	
	private ListaAdmCtrl listaAdm = new ListaAdmCtrl();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			panel = FXMLLoader.load(getClass().getResource("PainelAdm.fxml"));
			loadBPanel = new FXMLLoader(getClass().getResource("ListaAdm.fxml"));
			loadBPanel.setController(listaAdm);
			bpPane = loadBPanel.load();
			
			AddTransition(panel);
			AddTransition(bpPane);
			
			listaAdm.novo.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					switch (id) {
					case "btn02"://Aulas
						aulas();
						break;
					case "btn03"://Administradores
						administradores();
						break;
					case "btn04"://Questoes
						questoes();
						break;
					case "btn05"://FAQsvalue
						break;
					}
				}
			});
			
            spCenter.setContent(panel);
        } catch (IOException ex) {
            Logger.getLogger(GUIMainCtrl.class.getName()).log(Level.SEVERE, null, ex);
        }
	}
	
	@FXML
    private void changePage(ActionEvent event) {
		JFXButton btn = (JFXButton) event.getSource();
		id = btn.getId();
		listaAdm.id = id;
		listaAdm.busca();
		listaAdm.getTxtPesquisa().setText("");
		switch (btn.getId()) {
			case "btn01"://Panel
				lblTitle.setText("Panel");
				spCenter.setContent(panel);
				break;
			case "btn02"://Aulas
				lblTitle.setText("Aulas");
				spCenter.setContent(bpPane);
				break;
			case "btn03"://Administradores
				lblTitle.setText("Administradores");
				spCenter.setContent(bpPane);
				break;
			case "btn04"://Questoes
				lblTitle.setText("Questões");
				spCenter.setContent(bpPane);
				break;
			case "btn05"://FAQsvalue
				lblTitle.setText("FAQs");
				spCenter.setContent(bpPane);
				break;
		}
    }
	
	@FXML
    private void exit(ActionEvent event) {
		System.exit(0);
    }
	
	private void aulas() {
		try {
			NovasAulasCtrl nac = new NovasAulasCtrl();
			BorderPane bpAulas;
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NovasAulas.fxml"));
			fxmlLoader.setController(nac);
			bpAulas = fxmlLoader.load();
			
			if(listaAdm.aula != null) {
				nac.aula = listaAdm.aula;
				nac.showInfo();
				listaAdm.aula = null;
			}else {
				nac.limpaInfo();
			}
			
			nac.getBtnVoltar().setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					listaAdm.busca();
					lblTitle.setText("Aulas");
					spCenter.setContent(bpPane);
				}
			});;
			
			spCenter.setContent(bpAulas);
		} catch (IOException e) {
			Logger.getLogger(GUIMainCtrl.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	
	private void questoes() {
		try {
			NovasQuestoes nq = new NovasQuestoes();
			BorderPane bpAulas;
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NovasQuestoes.fxml"));
			fxmlLoader.setController(nq);
			bpAulas = fxmlLoader.load();
			
			if(listaAdm.quest!= null) {
				nq.quest = listaAdm.quest;
				nq.showInfo();
				listaAdm.quest = null;
			}else {
				nq.limpaInfo();
			}
			
			nq.getBtnVoltar().setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					listaAdm.busca();
					lblTitle.setText("Questões");
					spCenter.setContent(bpPane);
				}
			});;
			
			spCenter.setContent(bpAulas);
		} catch (IOException e) {
			Logger.getLogger(GUIMainCtrl.class.getName()).log(Level.SEVERE, null, e);
		}
	}
	private void administradores() {
		try {
			NovosAdms na = new NovosAdms();
			AnchorPane apAdms;
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NovosAdm.fxml"));
			fxmlLoader.setController(na);
			apAdms = fxmlLoader.load();
			
			if(listaAdm.pessoa!= null) {
				na.pessoa = listaAdm.pessoa;
				na.showInfo();
				listaAdm.pessoa = null;
			}else {
				na.limpaInfo();
			}
			
			na.getBtnVoltar().setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					listaAdm.busca();
					lblTitle.setText("Questões");
					spCenter.setContent(bpPane);
				}
			});;
			
			spCenter.setContent(apAdms);
		} catch (IOException e) {
			Logger.getLogger(GUIMainCtrl.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	private void AddTransition(AnchorPane panel) {
		FadeTransition ft = new FadeTransition(Duration.millis(300), panel);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
	}
}
