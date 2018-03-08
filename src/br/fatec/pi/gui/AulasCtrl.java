package br.fatec.pi.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;

import br.fatec.pi.entidade.Aulas;
import br.fatec.pi.entidade.Questoes;
import br.fatec.pi.evento.AulasDAO;
import br.fatec.pi.evento.QuestoesDAO;
import br.fatec.pi.evento.RealizaDAO;
import br.fatec.pi.evento.RespondeDAO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.util.Callback;

public class AulasCtrl implements Initializable {

	@FXML
	private ScrollPane spMain;
	
	@FXML
	private VBox vbCenter;
	
	private BorderPane bpIntroAulas = new BorderPane();
	
	private AnchorPane apQuestoes = new AnchorPane();
	
	private IntroAulasCtrl intro;
	
	private QuestoesCtrl ques;
	
	private ArrayList<Questoes> array;
	
	private int sum=0,total=0,lastCod=0;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		FXMLLoader fxmlLoader;
		intro = new IntroAulasCtrl();
		ques = new QuestoesCtrl();
		vbCenter.setPadding(new Insets(10,0,0,45));
		try {
			fxmlLoader = new FXMLLoader(getClass().getResource("IntroAulasCtrl.fxml"));
			fxmlLoader.setController(intro);
			bpIntroAulas = fxmlLoader.load();
		} catch (IOException e) {
			Logger.getLogger(AulasCtrl.class.getName()).log(Level.SEVERE, null, e);
		}
		AulasDAO aulas = new AulasDAO();
		for (Aulas a : aulas.selecionar()) {
			novaAula(a);
		}
	}
	
	private void novaAula(Aulas a) {
		JFXButton btn = new JFXButton(); 
		//vbCenter.setMargin(btn, new Insets(10,0,0,45));
		btn.setText(a.getNomeAula());
     	btn.setButtonType(ButtonType.RAISED);
     	btn.setPrefSize(220.0, 40.0);
     	btn.setStyle("-fx-background-color: #f1f1f1;");
     	btn.setTextAlignment(TextAlignment.CENTER);
     	RealizaDAO rea = new RealizaDAO();
     	rea.setCodAula(lastCod);
     	if(rea.selecionar().size() > 0 || lastCod == 0) {
     		btn.setDisable(false);
     	}else {
     		btn.setDisable(true);
     	}
     	lastCod = a.getCodAula();
     	btn.setWrapText(true);
     	btn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				QuestoesDAO questoes = new QuestoesDAO();
				questoes.setCodAula(a.getCodAula());
				array = questoes.selecionar();
				if(array.size() > 0 || a.getTipo().equals("P")) {
					intro.getBtnFinalizar().setVisible(false);
				}else {
					intro.getBtnFinalizar().setVisible(true);
				}
		       
				spMain.setContent(bpIntroAulas);
				
				intro.setTexts(a.getNomeAula(), a.getDescricao());
				intro.btnVol.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						spMain.setContent(vbCenter);
					}
				});
				intro.btnCont.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						if(!a.getTipo().equals("P")) {
							questoes(a);
						}else{
							exame(a);
						}
					}
				});
				
			}
		});
		vbCenter.getChildren().add(btn);
	}
	
	 public AnchorPane createPage(int pageIndex) {
		 FXMLLoader fxmlLoader;
		 try {
			 fxmlLoader = new FXMLLoader(getClass().getResource("QuestoesCtrl.fxml"));
			 fxmlLoader.setController(ques);
			 apQuestoes = fxmlLoader.load();
		 } catch (IOException e) {
			 Logger.getLogger(AulasCtrl.class.getName()).log(Level.SEVERE, null, e);
		 }
		 ques.isLast = pageIndex == (array.size()-1);
		 ques.respostas(array.get(pageIndex));
		 ques.getBtnVolFi().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(ques.isLast) {
					RealizaDAO realiza = new RealizaDAO();
					realiza.setCodPessoa(GUIMainCtrl.pessoa.getCodPessoa());
					realiza.setCodAula(array.get(pageIndex).getCodAula());
					if(realiza.inserir() == 0) {
						Notifications notification = Notifications.create()
			 					.title("Finalizar")
			 					.text("Aula finalizada com Sucesso!")
			 					.position(Pos.TOP_RIGHT);
			 			
			 			notification.showConfirm();
			 			
					}
				}
				intro.btnVol.getOnAction().handle(event);
			}
		});
		 return apQuestoes;
	 }

	private void questoes(Aulas a) {
		QuestoesDAO questoes = new QuestoesDAO();
		questoes.setCodAula(a.getCodAula());
		array = questoes.selecionar();
		if(array.size() > 0) {
			intro.getBtnFinalizar().setVisible(true);
			Pagination pagination = new Pagination(array.size(), 0);
	        pagination.setPageFactory(new Callback<Integer, Node>() {
	            @Override
	            public Node call(Integer pageIndex) {
	                return createPage(pageIndex);
	            }
	        });
	        spMain.setContent(pagination);
		}else {
			intro.getBtnFinalizar().setVisible(true);
		}
       
	}
	
	private void exibeFinal() {
		FXMLLoader fxmlLoader;
		FinalExameCtrl fin = new FinalExameCtrl();
		 try {
			 fxmlLoader = new FXMLLoader(getClass().getResource("FinalExame.fxml"));
			 fxmlLoader.setController(fin);
			 apQuestoes = fxmlLoader.load();
		 } catch (IOException e) {
			 Logger.getLogger(AulasCtrl.class.getName()).log(Level.SEVERE, null, e);
		 }
		 fin.startSerie(sum, total);
		 spMain.setContent(apQuestoes);
	}
	
	private void proximaPagina(int i) {
		FXMLLoader fxmlLoader;
		QuestoesExameCtrl ques = new QuestoesExameCtrl();
		 try {
			 fxmlLoader = new FXMLLoader(getClass().getResource("QuestoesExameCtrl.fxml"));
			 fxmlLoader.setController(ques);
			 apQuestoes = fxmlLoader.load();
		 } catch (IOException e) {
			 Logger.getLogger(AulasCtrl.class.getName()).log(Level.SEVERE, null, e);
		 }
		 ques.isLast = i == (array.size()-1);
		 ques.respostas(array.get(i));
		 ques.getBtnProximo().setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(ques.isLast) {
					RespondeDAO res = new RespondeDAO();
					res.setCodPessoa(GUIMainCtrl.pessoa.getCodPessoa());
					res.setCodQuestao(array.get(i).getCodQuestoes());
					res.setSum(ques.acertou());
					sum += ques.acertou();
					total += ques.total;
					if(res.inserir() == 0) {
						Notifications notification = Notifications.create()
			 					.title("Finalizar")
			 					.text("Exame finalizado com Sucesso!")
			 					.position(Pos.TOP_RIGHT);
			 			
			 			notification.showConfirm();
			 			
			 			intro.btnVol.getOnAction().handle(event);
			 			exibeFinal();
					}
				}else {
					proximaPagina(i+1);
				}
			}
		});
		 spMain.setContent(apQuestoes);
	}
	
	
	
	private void exame(Aulas a){
		QuestoesDAO questoes = new QuestoesDAO();
		questoes.setCodAula(a.getCodAula());
		array = questoes.selecionar();
		if(array.size() > 0 || a.getTipo().equals("P") ) {
			intro.getBtnFinalizar().setVisible(false);
			if(array.size()<=40) {
				questoes.setQuestoes("");
				for (Questoes quest : questoes.executar() ) {
					if(array.size()<=40) {
						array.add(quest);
					}
				}
			}
			System.out.println(array.toString());
			Collections.shuffle(array);
			System.out.println(array.toString());
			proximaPagina(0);
	        //spMain.setContent();
		}else {
			intro.getBtnFinalizar().setVisible(true);
		}
	}
	

}
