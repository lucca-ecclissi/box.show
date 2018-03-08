package br.fatec.pi.gui;

import com.jfoenix.controls.JFXButton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class MenuCtrl implements Initializable {
	

    @FXML
    private Label lblNome;

    public Label getLblNome() {
		return lblNome;
	}

	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
    		GUIMainCtrl.rootSp.setContent(FXMLLoader.load(getClass().getResource("ConhecaOCurso.fxml")));
    		
    	}catch(IOException e) {
    		Logger.getLogger(MenuCtrl.class.getName()).log(Level.SEVERE, null, e);
    	}
    }    

    @FXML
    private void changePage(ActionEvent event) {
        JFXButton btn = (JFXButton) event.getSource();
        GUIMainCtrl.rootLbl.setText(btn.getText());
        if(GUIMainCtrl.rootDr.isShown())
        	GUIMainCtrl.rootDr.close();
        try {
	        switch(btn.getId())
	        {
	            case "b1":
					GUIMainCtrl.rootSp.setContent(FXMLLoader.load(getClass().getResource("ConhecaOCurso.fxml")));
	                break;
	            case "b2":
	            	GUIMainCtrl.rootSp.setContent(FXMLLoader.load(getClass().getResource("AulasCtrl.fxml")));
	            	break;
	            case "b3":
	            	GUIMainCtrl.rootSp.setContent(FXMLLoader.load(getClass().getResource("FAQS.fxml")));
	            	break;
	            case "b4":
	            	//GUIMainCtrl.rootSp.setContent(FXMLLoader.load(getClass().getResource(".fxml")));
	            	break;
	        }
        } catch (IOException e) {
        	 Logger.getLogger(MenuCtrl.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }
    
}
