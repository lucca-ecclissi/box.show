package br.fatec.pi.guiadm;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;

import br.fatec.pi.entidade.Aulas;
import br.fatec.pi.entidade.Questoes;
import br.fatec.pi.entidade.Respostas;
import br.fatec.pi.evento.AulasDAO;
import br.fatec.pi.evento.QuestoesDAO;
import br.fatec.pi.evento.RespostasDAO;
import br.fatec.pi.gui.GUIMainCtrl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class NovasQuestoes implements Initializable {


    @FXML
    private JFXComboBox<String> cbxAulas;

    @FXML
    private JFXButton btnVoltar;

    @FXML
    private JFXTextField txtNome;
    
    @FXML
    private VBox vbRespostas;

	private ObservableList<String> array = FXCollections.observableArrayList();
	
	private ArrayList<Aulas> aulasArray = new ArrayList<>();
	
	public Questoes quest;

    @FXML
    private void adicionar(ActionEvent event) {
    	novaResposta(null);
    }

    private void novaResposta(Respostas r) {
    	try {
			ItemResposta ia = new ItemResposta();
			AnchorPane apAulas;
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ItemResposta.fxml"));
			fxmlLoader.setController(ia);
			apAulas = fxmlLoader.load();
			if (r != null) {
				apAulas.setId(r.getCodRespostas()+"");
				ia.getTxtResposta().setText(r.getResposta());
				ia.getCbxResposta().setSelected(r.getTipo().equals("T"));
				ia.getTxtObs().setText(r.getObsResposta());
			}
			ia.getBtnRemover().setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					if(r!=null) {
						RespostasDAO respostas = new RespostasDAO();
						respostas.setCodRespostas(r.getCodRespostas());
						if(respostas.remover() == 0) {
							Notifications notification = Notifications.create()
				 					.title("Questões")
				 					.text("Erro ao Remover!")
				 					.position(Pos.TOP_RIGHT);
				 			
				    		notification.showError();
						}
					}
					vbRespostas.getChildren().remove(apAulas);
				}
			});
			vbRespostas.getChildren().add(apAulas);
		} catch (IOException e) {
			Logger.getLogger(GUIMainCtrl.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	@FXML
    private void salvar(ActionEvent event) {
    	ArrayList<Respostas> resposta = getRespostas();
    	if(valida(resposta) == 1) {
 			Notifications notification = Notifications.create()
 					.title("Respostas")
 					.text("Nenhum resposta certa!")
 					.position(Pos.TOP_RIGHT);
 			
 			notification.showWarning();
    	}else if(valida(resposta) == 2) {
    		Notifications notification = Notifications.create()
 					.title("Respostas")
 					.text("Alguma resposta não esta preenchida!")
 					.position(Pos.TOP_RIGHT);
 			
 			notification.showWarning();
    	}else if(cbxAulas.getValue().trim().length() == 0) {
    		Notifications notification = Notifications.create()
 					.title("Questões")
 					.text("Escolha uma Aula!")
 					.position(Pos.TOP_RIGHT);
 			
 			notification.showWarning();
    	}else if(txtNome.validate()) {
    		QuestoesDAO questoes = new QuestoesDAO();
    		RespostasDAO respostas = new RespostasDAO();
    		boolean salvo = false;
    		if(getAula()!= 0) {    			
	    		questoes.setQuestoes(txtNome.getText());
	    		questoes.setCodAula(getAula());
	    		if(quest == null) {
	    			respostas.setCodQuestoes(questoes.inserir());
		    		if(respostas.getCodQuestoes() != 0) {
	    				salvo = respostas.inserirArray(resposta)!=0;	    				
		    		}
	    		}else {
	    			questoes.setCodQuestoes(quest.getCodQuestoes());
	    			if(questoes.alterar() != 0) {
	    				for (Respostas r : resposta) {
							respostas.setCodRespostas(r.getCodRespostas());
							respostas.setObsResposta(r.getObsResposta());
							respostas.setResposta(r.getResposta());
							respostas.setTipo(r.getTipo());
							salvo = respostas.alterar() != 0;
						}
	    			}
	    		}
	    		
    		}
    		if(salvo) {
    			limpar(event);
    			Notifications notification = Notifications.create()
	 					.title("Questões")
	 					.text("Questão Salva com sucesso!")
	 					.position(Pos.TOP_RIGHT);
	 			
	    		notification.showConfirm();
    		}else {
    			Notifications notification = Notifications.create()
	 					.title("Questões")
	 					.text("Erro ao Salvar!")
	 					.position(Pos.TOP_RIGHT);
	 			
	    		notification.showError();
    		}
    	}
    }
    
    public void showInfo() {
    	txtNome.setText(quest.getQuestoes());
    	cbxAulas.setValue(quest.getAulas().getNomeAula());
    	RespostasDAO resp = new RespostasDAO();
    	resp.setCodQuestoes(quest.getCodQuestoes());
    	for (Respostas r : resp.selecionar()) {
			novaResposta(r);
		}
    }
    
    public void limpaInfo() {
    	txtNome.setText("");
    	vbRespostas.getChildren().clear();
    	cbxAulas.setValue("");
    }
    
    private int getAula() {
		for (Aulas a : aulasArray) {
			if(cbxAulas.getValue().equals(a.getNomeAula())) {
				return a.getCodAula();
			}
		}
		return 0;
	}

	private int valida(ArrayList<Respostas> respostas) {
    	boolean teste = true;
    	int i = 0;
    	for (Respostas r: respostas) {
			if(teste) {
				if(r.getTipo()=="T") {
					i++;
				}
				if(r.getResposta().trim().length() ==0) {
					teste=false;
				}
			}
		}
    	if(i==0) return 1;
    	if(!teste) return 2;
    	return 0;
    }
    private ArrayList<Respostas> getRespostas(){
    	ArrayList<Respostas> array = new ArrayList<>(); 
    	for (Node n : vbRespostas.getChildren()) {
    		Respostas r = new Respostas();
			AnchorPane ap = (AnchorPane)n;
			if(quest!=null) {
				System.out.println(ap.getId());
				r.setCodRespostas(Integer.parseInt(ap.getId()));
			}
			for (Node n2 : ap.getChildren()) {
				if(n2.getId().equals("txtResposta")) {
					JFXTextField txt = ((JFXTextField)n2);
					r.setResposta(txt.getText());
					txt.validate();
				}else if(n2.getId().equals("txtObs")) {
					JFXTextArea txt = ((JFXTextArea)n2);
					r.setObsResposta(txt.getText());
				}else if(n2.getId().equals("cbxResposta")) {
					JFXCheckBox cbx = ((JFXCheckBox)n2); 
					r.setTipo((cbx.isSelected())?"T":"F");
				}
			}
			array.add(r);
		}
    	return array;
    }

    @FXML
    private void limpar(ActionEvent event) {
    	limpaInfo();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		RequiredFieldValidator validate = new RequiredFieldValidator();
		
		txtNome.getValidators().add(validate);
		
		validate.setMessage("Questão Obrigatorio!");
		
		AulasDAO aulas = new AulasDAO();
		aulasArray = aulas.selecionar();
		for(Aulas a:aulasArray) {
			array.add(a.getNomeAula());
		}
		cbxAulas.setItems(array);
	}

	public JFXButton getBtnVoltar() {
		return btnVoltar;
	}
	
	
	
}
