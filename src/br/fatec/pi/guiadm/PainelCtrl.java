package br.fatec.pi.guiadm;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;

public class PainelCtrl implements Initializable {
	
	@FXML
	private LineChart lcAlunos;
	
	@FXML
	private PieChart pcAlunos;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("aaaaaaaaa");
		 startSerie();
	}
	
	public void startSerie() {
		//lcAlunos = new LineChart<>(new CategoryAxis(), new NumberAxis());
		final String T1 = "07-2017";
		final String T2 = "08-2017";
		final String T3 = "09-2017";
		final String T4 = "10-2017";

		XYChart.Series prod1 = new XYChart.Series();
		prod1.setName("Média dos Alunos");

		prod1.getData().add(new XYChart.Data(T1, 3));
		prod1.getData().add(new XYChart.Data(T2, 5));
		prod1.getData().add(new XYChart.Data(T3, 6));
		prod1.getData().add(new XYChart.Data(T4, 7));

		lcAlunos.getData().addAll(prod1);
		lcAlunos.setPrefSize(500, 400);
		
		//pcAlunos = new PieChart();
		pcAlunos.getData().addAll(
				new PieChart.Data("Acertos", 11),
		   new PieChart.Data("Erros", 12));
		pcAlunos.setTitle("Acertos e Erros");
	}
	
}
