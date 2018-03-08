package br.fatec.pi.guiadm;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import br.fatec.pi.entidade.Aulas;
import br.fatec.pi.entidade.Pessoa;
import br.fatec.pi.entidade.Questoes;
import br.fatec.pi.evento.AulasDAO;
import br.fatec.pi.evento.PessoaDAO;
import br.fatec.pi.evento.QuestoesDAO;
import br.fatec.pi.gui.GUIMainCtrl;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class ListaAdmCtrl implements Initializable {

	@FXML
	private AnchorPane pnlMain;
	
	@FXML
	private BorderPane pnlLista;
	
	@FXML
	private JFXTextField txtPesquisa;
	
	public JFXTextField getTxtPesquisa() {
		return txtPesquisa;
	}

	@FXML
	private JFXButton btnNovo;
	
	@FXML
	private VBox vbLista;
	
	public JFXButton novo;
	
	public String id;
	
	public Aulas aula;
	
	public Questoes quest;
	
	public Pessoa pessoa;
	
	@FXML
	private void pesquisa() {
		busca();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		novo = btnNovo;
	}
	public void busca() {
		System.out.println("Aulas");
		vbLista.getChildren().clear();
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
	private void administradores() {
		PessoaDAO pess = new PessoaDAO();
		pess.setEmailPessoa(txtPesquisa.getText());
		pess.setNomePessoa(txtPesquisa.getText());
		for (Pessoa p : pess.executar()) {
			try {
				ItemAdms item = new ItemAdms();
				AnchorPane apQuestoes;
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemAdms.fxml"));
				fxmlLoader.setController(item);
				apQuestoes= fxmlLoader.load();
				
				item.setTexts(p.getEmailPessoa(), p.getNomePessoa()); 
				
				item.editar.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						pessoa = p;
						btnNovo.getOnAction().handle(event);			
					}
				});
				item.remover.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						pess.setCodPessoa(p.getCodPessoa());
						if(pess.remover() != 0) {
							Notifications notification = Notifications.create()
									.title("Remover")
									.text("Administrador removido com Sucesso!")
									.position(Pos.TOP_RIGHT);
							
							notification.showConfirm();
							busca();
			    		}else {
			    			Notifications notification = Notifications.create()
									.title("Remover")
									.text("Erro ao remover Administrador!")
									.position(Pos.TOP_RIGHT);
							notification.showError();
			    		}
					}
				});
				
				vbLista.getChildren().add(apQuestoes);
			} catch (IOException e) {
				Logger.getLogger(ListaAdmCtrl.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
	
	private void questoes() {
		QuestoesDAO ques = new QuestoesDAO();
		ques.setQuestoes(txtPesquisa.getText());
		for (Questoes q : ques.executar()) {
			try {
				ItemQuestoesCtrl item = new ItemQuestoesCtrl();
				AnchorPane apQuestoes;
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemQuestoes.fxml"));
				fxmlLoader.setController(item);
				apQuestoes= fxmlLoader.load();
				
				item.setTexts(q.getQuestoes(), q.getAulas().getNomeAula()); 
				
				item.editar.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						quest = q;
						btnNovo.getOnAction().handle(event);			
					}
				});
				item.remover.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						QuestoesDAO quest = new QuestoesDAO();
						quest.setCodQuestoes(q.getCodQuestoes());
						if(quest.remover() != 0) {
							Notifications notification = Notifications.create()
									.title("Remover")
									.text("Questão removida com Sucesso!")
									.position(Pos.TOP_RIGHT);
							
							notification.showConfirm();
							busca();
			    		}else {
			    			Notifications notification = Notifications.create()
									.title("Remover")
									.text("Erro ao remover Questão!")
									.position(Pos.TOP_RIGHT);
							notification.showError();
			    		}
					}
				});
				
				vbLista.getChildren().add(apQuestoes);
			} catch (IOException e) {
				Logger.getLogger(ListaAdmCtrl.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

	private void aulas() {
		AulasDAO aulas = new AulasDAO();
		for (Aulas a : aulas.selecionar()) {
			try {
				ItemAulasCtrl item = new ItemAulasCtrl();
				AnchorPane apAulas;
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemAulas.fxml"));
				fxmlLoader.setController(item);
				apAulas = fxmlLoader.load();
				
				item.setTexts(a.getNomeAula(), a.getDescricao()); 
				
				item.editar.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						aula = a;
						btnNovo.getOnAction().handle(event);			
					}
				});
				item.remover.setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent event) {
						AulasDAO aulas = new AulasDAO();
						aulas.setCodAula(a.getCodAula());
						if(aulas.remover() != 0) {
							Notifications notification = Notifications.create()
									.title("Remover")
									.text("Aula removida com Sucesso!")
									.position(Pos.TOP_RIGHT);
							
							notification.showConfirm();
							busca();
			    		}else {
			    			Notifications notification = Notifications.create()
									.title("Remover")
									.text("Erro ao remover Aula!")
									.position(Pos.TOP_RIGHT);
							notification.showError();
			    		}
					}
				});
				
				vbLista.getChildren().add(apAulas);
			} catch (IOException e) {
				Logger.getLogger(ListaAdmCtrl.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}

}
