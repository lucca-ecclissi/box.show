package br.fatec.pi.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;

import br.fatec.pi.entidade.Questoes;
import br.fatec.pi.entidade.Respostas;
import br.fatec.pi.evento.RespostasDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class QuestoesExameCtrl implements Initializable {

	@FXML
	private JFXTextArea txtQuestao;
	
    @FXML
    private JFXButton btnProximo;
	
	@FXML
	private VBox vbQuetoes;
	
	@FXML
	private AnchorPane apQuest;

	public int total=0;
	
	public JFXButton getBtnProximo() {
		return btnProximo;
	}

	private ArrayList<Respostas> arrayR, arraySele = new ArrayList<>();

	public ArrayList<Respostas> getArraySele() {
		return arraySele;
	}

	private ToggleGroup group = new ToggleGroup();
	
	public boolean isLast = false;
	
	public int certasTotal, certas;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void respostas(Questoes q) {
		RespostasDAO respostas = new RespostasDAO();
		respostas.setCodQuestoes(q.getCodQuestoes());
		txtQuestao.setText(q.getQuestoes());
		arrayR = respostas.selecionar();
		total = certas(arrayR);
		if(certas(arrayR) <= 1) {
			for (Respostas r : arrayR) {
				addResposta(r);
			}
		}else {
			for (Respostas r : arrayR) {
				addRespostaCbx(r);
			}
		}
		if(isLast) {
			btnProximo.setText("Finalizar");
			btnProximo.setStyle("-fx-background-color:#0000ff");
		}else{
			btnProximo.setText("Avançar");
			btnProximo.setStyle("-fx-background-color:#DAA520");
		}
	}
	
	private void addResposta(Respostas r) {
		JFXRadioButton btn = new JFXRadioButton();
		btn.setText(r.getResposta());
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				arraySele.clear();
				arraySele.add(r);
			}
		});
		btn.setToggleGroup(group);
		vbQuetoes.getChildren().add(btn);
	}
	
	private void addRespostaCbx(Respostas r) {
		JFXCheckBox btn = new JFXCheckBox();
		btn.setText(r.getResposta());
		btn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(estaNoArray(r.getCodRespostas())) {
					arraySele.remove(r);
				}else {
					arraySele.add(r);
				}
			}
		});
		vbQuetoes.getChildren().add(btn);
	}
	
	private boolean estaNoArray(int i) {
		for (Respostas respostas : arraySele) {
			if(i==respostas.getCodRespostas()) {
				return true;
			}
		}
		return false;
	}

	public int acertou() {
		int count =0 ;
		for (Respostas respostas : arrayR) {
			if(estaNoArray(respostas.getCodRespostas())) {
				count++;
			}
		}
		return count;
	}
	
	private int certas(ArrayList<Respostas> r) {
		int i =0;
		for (Respostas respostas : r) {
			if(respostas.getTipo().equals("T")) {
				i++;
			}
		}
		return i;
	}
	


}
