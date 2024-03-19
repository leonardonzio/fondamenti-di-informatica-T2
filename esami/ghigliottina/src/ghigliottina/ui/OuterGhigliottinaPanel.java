package ghigliottina.ui;

import ghigliottina.model.Ghigliottina;
import javafx.css.converter.EffectConverter;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.effect.Effect;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class OuterGhigliottinaPanel extends BorderPane {
 
	private GhigliottinaPanel gPanel;
	private TextField txtRispostaUtente, txtRispostaEsatta;
	private Label rightLabel, leftLabel;
	private Button svela;
	private String rispostaEsatta;
	private Controller controller;
	private Ghigliottina gh;
	private int montepremi;
	
	public OuterGhigliottinaPanel(int montepremi, Controller controller) {
		this.controller=controller;
		this.montepremi=montepremi;
		setupGhigliottinaPanel();
		//
		
		// leftBox
		VBox leftBox = new VBox();
		Label leftLabel = new Label("La parola segreta:");
		leftLabel.setTextFill(Color.WHITE);
		leftLabel.setFont(Font.font("Courier new", FontWeight.BOLD, 11));
		txtRispostaEsatta = new TextField();
		//campo di testo disabilitato all interazione
		txtRispostaEsatta.setDisable(true);
		txtRispostaEsatta.setOpacity(0.7);
		leftBox.getChildren().addAll(leftLabel, txtRispostaEsatta);
		VBox.setMargin(leftLabel, new Insets(5,5,5,5));


		// rightBox
		VBox rightBox = new VBox();
		Label rightLabel = new Label("La tua risposta:");
		rightLabel.setTextFill(Color.WHITE);
		rightLabel.setFont(Font.font("Courier new", FontWeight.BOLD, 11));
		txtRispostaUtente = new TextField();
		rightBox.getChildren().addAll(rightLabel, txtRispostaUtente);
		VBox.setMargin(rightLabel, new Insets(5,5,5,5));
		
		// box di left + right + bottone svela
		HBox revealBox = new HBox();
		revealBox.setAlignment(Pos.CENTER);
		revealBox.setStyle("-fx-background-color: blue;");
		svela = new Button("SVELA");
		svela.setFont(Font.font("Arial", FontWeight.BOLD, 12));
		svela.setTextFill(Color.RED);
		svela.setPrefHeight(60);
		svela.setOnAction(this::svelaPressed);
		revealBox.getChildren().addAll(leftBox, rightBox, svela);
		
		
		this.setTop(revealBox);
	}
	
	private void setupGhigliottinaPanel() {
		// initial setup
		gh = controller.sorteggiaGhigliottina();
		this.rispostaEsatta=gh.getRispostaEsatta();
		gPanel = new GhigliottinaPanel(montepremi, gh.getTerne());
		this.setBottom(gPanel);
	}
	
	private void reset() {
		setupGhigliottinaPanel();
		txtRispostaUtente.setText("");
		txtRispostaEsatta.setText("");
	}
	

	private void svelaPressed(ActionEvent ev) {
		var rispostaUtente = txtRispostaUtente.getText().trim();
		if (rispostaUtente == null || rispostaUtente.isBlank()) {
			alert("Errore", "Risposta mancante", "Non hai scritto alcuna parola valida");
			return;
		}
		
		txtRispostaEsatta.setText(rispostaEsatta);
		boolean isGiusta = rispostaUtente.equalsIgnoreCase(rispostaEsatta);
		
		info("Risultato", 
				isGiusta ? "HAI VINTO" : "HAI PERSO",
				isGiusta 
				? "Montepremi: " + gPanel.montepremiAsString() 
				: "Purtroppo hai perso " + gPanel.montepremiAsString());
		
		reset();
	}
	
	
	// COMPLETARE: gestione evento pressione pulsante SVELA

	
	public static void alert(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}
	
	public static void info(String title, String headerMessage, String contentMessage) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(headerMessage);
		alert.setContentText(contentMessage);
		alert.showAndWait();
	}

}
