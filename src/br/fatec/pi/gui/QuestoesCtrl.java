package br.fatec.pi.gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;

import br.fatec.pi.entidade.Questoes;
import br.fatec.pi.entidade.Respostas;
import br.fatec.pi.evento.RespostasDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class QuestoesCtrl implements Initializable {

	@FXML
	private JFXTextArea txtQuestao;
	
    @FXML
    private JFXButton btnVolFi;
	
	@FXML
	private VBox vbQuetoes;
	
	@FXML
	private AnchorPane apQuest;
	
	public JFXButton getBtnVolFi() {
		return btnVolFi;
	}

	private ArrayList<Respostas> arrayR, arraySele = new ArrayList<>();

	private ToggleGroup group = new ToggleGroup();
	
	public boolean isLast = false;
	
	@FXML
	private void confirmar(ActionEvent event) {
		StackPane sp = new StackPane();
		JFXDialogLayout content = new JFXDialogLayout();
		VBox vbRespostas = new VBox();
		
		for (Respostas respostas : arrayR) {
			vbRespostas.getChildren().add(addRespostaPanel(respostas));
		}
		
		
		content.setHeading(new Text("Respostas"));
		content.setBody(vbRespostas);

		JFXDialog dialog = new JFXDialog(sp,content,JFXDialog.DialogTransition.CENTER);
		
		JFXButton button = new JFXButton("Fechar");
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});
		
		apQuest.getChildren().add(sp);
		
		content.setActions(button);
		dialog.show();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void respostas(Questoes q) {
		RespostasDAO respostas = new RespostasDAO();
		respostas.setCodQuestoes(q.getCodQuestoes());
		txtQuestao.setText(q.getQuestoes());
		arrayR = respostas.selecionar();
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
			btnVolFi.setText("Finalizar");
			btnVolFi.setStyle("-fx-background-color:#0000ff");
		}else{
			btnVolFi.setText("Voltar");
			btnVolFi.setStyle("-fx-background-color:#ff0000");
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
	
	private AnchorPane addRespostaPanel(Respostas r) {
		AnchorPane ap = new AnchorPane();
		Label lblResposta = new Label(r.getResposta());
		Label lblObs = new Label(r.getObsResposta());
		if(r.getTipo().equals("T")) {
			lblResposta.setStyle("-fx-text-fill:green");
			lblObs.setStyle("-fx-text-fill:green");
		}else {
			if(estaNoArray(r.getCodRespostas())) {
				lblResposta.setStyle("-fx-text-fill:#ff0000");
				lblObs.setStyle("-fx-text-fill:#ff0000");
			}else {
				lblResposta.setStyle("-fx-text-fill:black");
				lblObs.setStyle("-fx-text-fill:black");
			}
		}
		ap.getChildren().add(lblResposta);
		ap.getChildren().add(lblObs);
		return ap;
	}
	
	private boolean estaNoArray(int i) {
		for (Respostas respostas : arraySele) {
			if(i==respostas.getCodRespostas()) {
				return true;
			}
		}
		return false;
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
