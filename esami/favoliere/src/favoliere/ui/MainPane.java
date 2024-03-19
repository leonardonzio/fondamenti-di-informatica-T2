package favoliere.ui;

import java.nio.channels.NonWritableChannelException;
import java.util.ResourceBundle.Control;

import org.junit.jupiter.params.shadow.com.univocity.parsers.common.processor.BeanWriterProcessor;
import org.junit.runners.model.FrameworkField;

import favoliere.controller.Controller;
import favoliere.controller.MyController;
import favoliere.model.FasciaEta;
import favoliere.model.Impressionabilita;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainPane extends BorderPane{

	private Controller controller;
	private ComboBox<FasciaEta> comboEta;
	private ComboBox<Impressionabilita> comboImpressionabilita;
	private TextArea area;
	private Button buttonGenera, buttonStampa;
	
	
	public MainPane(Controller controller) {
		
		this.controller = controller;
		
		// top-----------
		VBox topBox = new VBox();
		Insets ins = new Insets(10, 10, 10, 10);
		
		Label l1 = new Label("Fascia d'età del bambino:");
		comboEta = new ComboBox<>();
		comboEta.setItems(FXCollections.observableArrayList(this.controller.getFasceEta()));
		comboEta.setEditable(false);
		comboEta.setValue(FasciaEta.GRANDE);
		
		Label l2 = new Label("Grado di impressionabilità:");
		comboImpressionabilita = new ComboBox<>();
		comboImpressionabilita.setItems(FXCollections.observableArrayList(this.controller.getLivelliImpressionabilita()));
		comboImpressionabilita.setEditable(false);
		comboImpressionabilita.setValue(Impressionabilita.PERNULLA_IMPRESSIONABILE);
		
		topBox.setPadding(ins);
		topBox.getChildren().addAll(l1,
				comboEta, 
				new Label(" "),
				l2,
				comboImpressionabilita);
		this.setTop(topBox);
		
		// center--------------------
		VBox centerBox = new VBox();
		
		l1 = new Label("Favola generata:");
		area = new TextArea();
		area.setText("");
		area.setEditable(false);
		
		centerBox.setPadding(ins);
		centerBox.getChildren().addAll(l1, area);
		this.setCenter(centerBox);
		
		
		// bottom
		VBox bottomBox = new VBox();
		
		buttonGenera = new Button("Genera favola");
		buttonStampa = new Button("Stampa su file");
		buttonStampa.setDisable(true);
		
		
		buttonGenera.setOnAction(this::handleGenera);
		buttonStampa.setOnAction(this::handleStampa);
		
		bottomBox.getChildren().addAll(
				buttonGenera,
				buttonStampa);
		
		bottomBox.setSpacing(10);
		bottomBox.setPadding(ins);
		this.setBottom(bottomBox);
		
		
	}
	
	private void handleGenera(ActionEvent ev) {
		
		var optFavola = controller.generaFavola(
				comboEta.getValue(), 
				comboImpressionabilita.getValue()
				);
		
		if (optFavola.isEmpty()) {
			Controller.alert("Errore irrecuperabile", "Errore", "impossibile generare favola con questi dati");
			return;
		}
		
		area.setText(optFavola.get().toString());
		buttonStampa.setDisable(false);
		
	}
	
	private void handleStampa(ActionEvent ev) {
		
		String nameFile = controller.getOutputFileName();
		Controller.alert("erre", "err file", "addio");
	}


}
