package br.fatec.pi.guiadm;

import org.controlsfx.control.Notifications;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import br.fatec.pi.evento.PessoaDAO;
import br.fatec.pi.gui.GUIMain;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
/**
 * Classe principal que monta a tela
 * @author luccame
 */
public class GUIAdmMain  extends Application {
	
	 	private JFXButton btnLogin;
	 	private JFXTextField txtUsuario;
	 	private JFXPasswordField txtSenha;
	 	private Parent second;
	 	private Scene scene;
	 	
	 	/**
	 	 * Metodo de sobreescrita da interface Application
	 	 */
	 	@Override
	    public void start(Stage stage) throws Exception {
	        Parent root = FXMLLoader.load(getClass().getResource("GUIAdmMain.fxml"));
	        second = FXMLLoader.load(getClass().getResource("MenuAdm.fxml"));
	        
	        
	        scene = new Scene(root);
	        
	        stage.getIcons().add(new Image(GUIMain.class.getResourceAsStream("logobox.png")));
	        stage.setScene(scene);
	        stage.show();
	        
	        btnLogin = (JFXButton) scene.lookup("#btnLogar");
	 		txtUsuario = (JFXTextField) scene.lookup("#txtUsuario");
	 		txtSenha = (JFXPasswordField) scene.lookup("#txtSenha");
	 		
	 		txtUsuario.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent ke) {
		            if (ke.getCode().equals(KeyCode.ENTER)){
		            	login(stage);    
			        }
				}
				
			});
	 		
			txtSenha.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent ke) {
		            if (ke.getCode().equals(KeyCode.ENTER)){
		            	login(stage);    
			        }
				}
				
			});
			btnLogin.setOnMouseClicked(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					login(stage);
				}
			});
	        
	    }
	 	/**
	 	 * Metoto que seta a pagina de Login do Administrador
	 	 * @param stage
	 	 */
	 	private void login(Stage stage) {
	 		if(txtUsuario.validate() && txtSenha.validate()) {
	 			PessoaDAO pessoa = new PessoaDAO();
	 			pessoa.setEmailPessoa(txtUsuario.getText());
	 			pessoa.setSenhaPessoa(txtSenha.getText());
	 			if(pessoa.login()) {
		 			Scene scene01 = new Scene(second);
		 			stage.setScene(scene01);
		 			
		 			Notifications notification = Notifications.create()
		 					.title("Login")
		 					.text("Login realizado com Sucesso!")
		 					.position(Pos.TOP_RIGHT);
		 			
		 			notification.showConfirm();
	 			}else {
	 				Notifications notification = Notifications.create()
		 					.title("Login")
		 					.text("Email ou Senha inválido!")
		 					.position(Pos.TOP_RIGHT);
		 			
		 			notification.showWarning();
	 			}
			}
	        
	 	}
	 	/**
	 	 * Metodo principal
	 	 * @param args
	 	 */
	    public static void main(String[] args) {
	        launch(args);
	    }
	    

}
