package br.fatec.pi.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.controlsfx.control.Notifications;

import br.fatec.pi.evento.PessoaDAO;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class GUIMain extends Application {
	
	private Parent cadastro, login, root;
	private CadastroCtrl cc = new CadastroCtrl();
	private LoginCtrl lc = new LoginCtrl();
	private Scene sceneLogin, sceneCadastro, scene;
	
	private PessoaDAO pessoa  = new PessoaDAO();
	
	@Override
    public void start(Stage stage) throws Exception {
		FXMLLoader load;
        root = FXMLLoader.load(getClass().getResource("GUIMain.fxml"));
        try {
			load = new FXMLLoader(getClass().getResource("Login.fxml"));
			load.setController(lc);
			login = load.load();
			load = new FXMLLoader(getClass().getResource("Cadastro.fxml"));
			load.setController(cc);
			cadastro = load.load();
        }catch (IOException ex) {
            Logger.getLogger(GUIMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        sceneLogin = new Scene(login);
        sceneCadastro = new Scene(cadastro);
        scene = new Scene(root);
        
        lc.getBtnCadastro().setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				stage.setScene(sceneCadastro);
			}
		});
        
        lc.getBtnLogin().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(lc.getTxtEmail().validate() &&  lc.getTxtSenha().validate()) {					
					pessoa.setEmailPessoa(lc.getTxtEmail().getText());
					pessoa.setSenhaPessoa(lc.getTxtSenha().getText());
					if (pessoa.login()) {
						Notifications notification = Notifications.create()
			 					.title("Login")
			 					.text("Login realizado com sucesso!")
			 					.position(Pos.TOP_RIGHT);
			 			
			 			notification.showConfirm();
			 			GUIMainCtrl.pessoa = pessoa;
			 			stage.setScene(scene);
					}else {
						Notifications notification = Notifications.create()
			 					.title("Login")
			 					.text("Email ou Senha inválido!")
			 					.position(Pos.TOP_RIGHT);
			 			
			 			notification.showWarning();
					}
				}
			}
		});
        
        cc.getBtnVoltar().setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				stage.setScene(sceneLogin);
			}
		});
        
        stage.getIcons().add(new Image(GUIMain.class.getResourceAsStream("logobox.png")));
        stage.setScene(sceneLogin);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
	    
}
