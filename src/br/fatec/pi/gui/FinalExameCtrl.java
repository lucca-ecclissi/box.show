package br.fatec.pi.gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;

public class FinalExameCtrl  implements Initializable {
	
	@FXML
	private Label lblResultado;
	
	@FXML
	private PieChart pcAcertos;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void startSerie(double soma,double total) {
		pcAcertos.getData().addAll(
				new PieChart.Data("Acertos", soma),
		   new PieChart.Data("Erros", (total-soma)));
		pcAcertos.setTitle("Acertos e Erros");
		System.out.println(total);
		System.out.println(soma);
		System.out.println((total*0.6));
		if(soma>=(total*0.6)) {
			lblResultado.setText("Passou "+((soma/total)*100)+"%");
		}else {
			lblResultado.setText("Não Passou "+((soma/total)*100)+"%");
		}
	}
	
}
