package cityparking.ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import cityparking.controller.BadTimeFormatException;
import cityparking.controller.Controller;
import cityparking.model.BadTimeIntervalException;
import cityparking.model.Ricevuta;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class CityParkingPane extends BorderPane {

	private TextArea outputArea;
	private Controller controller;
	private Button calcButton;
	private VBox leftPane, centerPane, bottomPane;
	private DatePicker startPicker, endPicker;
	private TextField startTime, endTime;
	
	private final DateTimeFormatter dateFormatter = DateTimeFormatter
			.ofLocalizedTime(FormatStyle.SHORT)
			.withLocale(Locale.ITALY);
	
	public CityParkingPane(Controller controller) {
		this.controller = controller;
		Insets insets = new Insets(10);
		//
		leftPane = new VBox();
		leftPane.setPrefWidth(150);
		leftPane.getChildren().add(new Label(" Inizio sosta: "));
		//
		// DA FARE: creazione e configurazione DatePicker di inizio sosta
		// DA FARE: creazione e configurazione campo di testo per orario di inizio sosta (formato italiano short HH:MM)
		// NB: entrambi devono essere impostati a data e ora correnti
		//
	
		startPicker = new DatePicker(LocalDate.now());
		startTime = new TextField(LocalTime.now().format(dateFormatter));
		startTime.setEditable(true);
		
		leftPane.getChildren().addAll(startPicker,startTime);
		this.setLeft(leftPane);
		BorderPane.setMargin(leftPane, insets);
		//
		centerPane = new VBox();
		centerPane.setPrefWidth(150);
		centerPane.getChildren().add(new Label(" Fine sosta: "));
		//
		// DA FARE: creazione e configurazione DatePicker di fine sosta
		// DA FARE: creazione e configurazione campo di testo per orario di fine sosta (formato italiano short HH:MM)
		// NB: entrambi devono essere impostati a data e ora correnti

		endPicker = new DatePicker(LocalDate.now());
		endTime = new TextField(LocalTime.now().format(dateFormatter));
		endTime.setEditable(true);
		
		centerPane.getChildren().addAll(endPicker,endTime);
		this.setCenter(centerPane);
		BorderPane.setMargin(centerPane, insets);
		//
		bottomPane = new VBox();
		var buttonBox = new HBox();
		buttonBox.setAlignment(Pos.CENTER);
		calcButton = new Button("Calcola costo");
		//
		// DA FARE: aggancio ascoltatore degli eventi
		//
		
		calcButton.setOnAction(this::calcolaCosto);
		
		buttonBox.getChildren().add(calcButton);
		bottomPane.getChildren().add(buttonBox);
		outputArea = new TextArea();
		outputArea.setEditable(false);
		outputArea.setPrefHeight(100);
		outputArea.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		outputArea.setText("");
		bottomPane.getChildren().addAll(new Label(" Costo sosta: "), outputArea);
		this.setBottom(bottomPane);
		BorderPane.setMargin(bottomPane, insets);
	}

	private void calcolaCosto(ActionEvent event) {
		
		var oraInizioAsString 	= startTime.getText();
		var oraFineAsString 	= endTime.getText();
		var dataInizio 	= startPicker.getValue();
		var dataFine 	= endPicker.getValue();
		
		Ricevuta r;
		try {
			r = controller.calcolaSosta(dataInizio, 
					oraInizioAsString, 
					dataFine, 
					oraFineAsString);
			
		} catch (BadTimeFormatException e) {
			Controller.alert("Formato orario errato", 
					"Uno degli orari specificati è errato", 
					e.getMessage());
			return;
			
		} catch (BadTimeIntervalException e) {
			Controller.alert("Errore", 
					"Impossibile tornare indietro nel tempo", 
					e.getMessage());
			return;
		}
		
		this.outputArea.setText(r.toString());
	}


}
