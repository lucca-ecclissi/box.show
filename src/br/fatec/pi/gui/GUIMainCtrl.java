package br.fatec.pi.gui;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import br.fatec.pi.entidade.Pessoa;

public class GUIMainCtrl implements Initializable {

	    @FXML
	    private JFXDrawer drawer;

	    @FXML
	    private JFXHamburger hamburger;
	    
	    @FXML
	    private ScrollPane spCenter;
	    
	    @FXML 
	    private Label lblTitle;
	    
	    public static Label rootLbl;
	    
	    public static ScrollPane rootSp;
	    
	    public static JFXDrawer rootDr;
	    
	    public static Pessoa pessoa; 
	    
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {     
	    	rootSp = spCenter;
	    	rootLbl = lblTitle;
	    	rootDr = drawer;
	    	VBox box;
	    	FXMLLoader fxmlLoader;
			MenuCtrl mc = new MenuCtrl();
			try {
				fxmlLoader = new FXMLLoader(getClass().getResource("MenuCtrl.fxml"));
				fxmlLoader.setController(mc);
				box = fxmlLoader.load();
				drawer.setSidePane(box);
	        } catch (IOException ex) {
	            Logger.getLogger(GUIMainCtrl.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	        
	        
	        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
	        transition.setRate(-1);
	        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED,(e)->{
	            transition.setRate(transition.getRate()*-1);
	            transition.play();
	            mc.getLblNome().setText(pessoa.getNomePessoa());
	            
	            if(drawer.isShown())
	            {
	                drawer.close();
	            }else
	                drawer.open();
	        });
	        
	        drawer.setOnDrawerClosed(new EventHandler<Event>() {

				@Override
				public void handle(Event event) {
					transition.setRate(transition.getRate()*-1);
		            transition.play();
				}
	        	
			});
	    }

}
