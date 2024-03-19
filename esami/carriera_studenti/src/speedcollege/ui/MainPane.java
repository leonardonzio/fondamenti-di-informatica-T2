package speedcollege.ui;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import speedcollege.controller.Controller;
import speedcollege.model.Carriera;


public class MainPane extends BorderPane {
	
	private Controller controller;
	private ComboBox<String> carriereCombo, funzioniCombo;
	private TextArea area;
	private String idCarriera;
	private TextField txtMediaPesata, txtCreditiAcquisiti;
	private NumberFormat formatter;
	private DatePicker picker;

	public MainPane(Controller controller) {
		this.controller	= controller;
		this.formatter 	= NumberFormat.getInstance(Locale.ITALY);
		
		//
		// DA FARE: PREDISPOSIZIONE E CONFIGURAZIONE DEL FORMATTATORE NUMERICO
		//
		VBox leftBox = new VBox();
		leftBox.setPrefWidth(200);
			HBox miniBoxIniziale = new HBox(); miniBoxIniziale.setAlignment(Pos.CENTER);
			Label titolino = new Label("Carriere studenti");
			titolino.setStyle("-fx-font-weight: bold");
			miniBoxIniziale.getChildren().addAll(titolino);
			leftBox.getChildren().addAll(new Label("  "), miniBoxIniziale, new Label("  "));
			
			
			Label label = new Label("Scelta carriera:");
			
			carriereCombo = new ComboBox<>();
			carriereCombo.setItems(FXCollections.observableArrayList(this.controller.getListaIdCarriere()));
			carriereCombo.setOnAction(this::myHandle);
			
			Label label2 = new Label("Funzione di decadimento:");
			
			funzioniCombo = new ComboBox<>();
			funzioniCombo.setItems(FXCollections.observableArrayList(this.controller.elencoFunzioni()));
			funzioniCombo.setValue(this.controller.elencoFunzioni().first());
			funzioniCombo.setOnAction(this::myHandle);
			leftBox.getChildren().addAll(label, carriereCombo, label2, funzioniCombo);


			Label label3 = new Label("Attualizzare al:");
			picker = new DatePicker(LocalDate.of(2020, 1, 1));
			picker.setOnAction(this::myHandle);
			leftBox.getChildren().addAll(label3, picker);
			
			
			
			//
			// DA FARE: POPOLAMENTO DI carriereCombo E SUA CONFIGURAZIONE + AGGANCIO DEL LISTENER
			//
			// DA FARE: POPOLAMENTO DI funzioniCombo E SUA CONFIGURAZIONE + AGGANCIO DEL LISTENER
			//
			// DA FARE: CONFIGURAZIONE DEL datePicker + AGGANCIO DEL LISTENER
			//
			txtMediaPesata = new TextField();
			txtCreditiAcquisiti = new TextField();
			txtMediaPesata.setFont(Font.font("Courier New", FontWeight.BOLD, 11));
			txtCreditiAcquisiti.setFont(Font.font("Courier New", FontWeight.BOLD, 11));
			txtMediaPesata.setAlignment(Pos.CENTER_RIGHT);
			txtMediaPesata.setMaxWidth(175);
			txtCreditiAcquisiti.setAlignment(Pos.CENTER_RIGHT);
			txtCreditiAcquisiti.setMaxWidth(175);
			txtMediaPesata.setEditable(false);
			txtCreditiAcquisiti.setEditable(false);
			leftBox.getChildren().addAll(new Label(" Media pesata attualizzata:  "), txtMediaPesata);
			leftBox.getChildren().addAll(new Label(" Crediti acquisiti:  "), txtCreditiAcquisiti);
			this.setLeft(leftBox);
		VBox rightBox = new VBox();
			rightBox.setPrefWidth(580);
			area = new TextArea();
			area.setPrefSize(580,500);
			area.setFont(Font.font("Courier New", FontWeight.NORMAL, 11));
			area.setEditable(false);
			rightBox.getChildren().addAll(area);
		this.setRight(rightBox);
	}
	
	private void myHandle(ActionEvent ev) {
		//
		// DA FARE
		// Logica di gestione evento: recuperare la carriera, la funzione di decadimento e la data di attualizzazione 
		// selezionate, poi utilizzare tali dati per popolare i vari campi di uscita tramite gli appositi metodi 
		// del controller 
		//
		
		var idCarriera	= carriereCombo.getValue();
		var carriera 	= controller.getCarriera(idCarriera);
		
		var idFunction 	= funzioniCombo.getValue();
		var function	= controller.getFunction(idFunction);	
		
		var date = picker.getValue();
		area.setText("Carriera base:" + carriera.toString());
		
		controller.setCarriera(idCarriera);
		controller.setFunction(function);
		controller.setDataDiAttualizzazione(date);
		
		area.appendText("\n\nCarriera attualizzata:\n" + controller.toString());
		
		txtMediaPesata.setText(formatter.format(controller.getMediaPesata()));
		txtCreditiAcquisiti.setText(formatter.format(controller.getCreditiAcquisiti(idCarriera)));
		
	}
	
}
