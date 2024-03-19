package mediaesami.ui;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import flights.model.Airport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import mediaesami.controller.Controller;

public class MainPane extends BorderPane {
	
	private Controller controller;
	private ComboBox<String> carriereCombo;
	private TextArea area;
	private String selectedValue;
	private TextField txtMediaPesata, txtCreditiAcquisiti;
	private NumberFormat formatter;

	public MainPane(Controller controller) {
		
		this.controller=controller;
		formatter = NumberFormat.getInstance(Locale.ITALY);
		
		
		// --------------left-----------
		VBox leftBox = new VBox();
		leftBox.setPrefWidth(100);
			HBox miniBoxIniziale = new HBox(); miniBoxIniziale.setAlignment(Pos.CENTER);
			Label titolino = new Label("Carriere studenti");
			titolino.setStyle("-fx-font-weight: bold");
			miniBoxIniziale.getChildren().addAll(titolino);
			leftBox.getChildren().addAll(new Label("  "), miniBoxIniziale, new Label("  "));
			
			//
			// DA FARE: popolamento combo
			//
			List<String> idCarriere = controller.getListaIdCarriere();
			ObservableList<String> observableIdCarriere = FXCollections.observableArrayList(idCarriere);
			
			carriereCombo = new ComboBox<>(observableIdCarriere);
			carriereCombo.setEditable(false);
			
			carriereCombo.setTooltip(new Tooltip("Scegliere la carriera da visualizzare"));
			leftBox.getChildren().addAll(new Label(" Scelta carriera:  "), carriereCombo);
			//
			// DA FARE: aggancio ascoltatore
			//
			carriereCombo.setOnAction(this::myHandle);
			
			
			txtMediaPesata = new TextField();
			txtCreditiAcquisiti = new TextField();
			//
			// DA FARE: impostazione font, allineamento, e immodificabilità dei campi di testo
			//
			
			txtMediaPesata.setEditable(false);
			txtCreditiAcquisiti.setEditable(false);
			txtMediaPesata.setAlignment(Pos.BASELINE_RIGHT);
			txtCreditiAcquisiti.setAlignment(Pos.BASELINE_RIGHT);

			
			txtMediaPesata.setFont(Font.font("Courier", FontWeight.BOLD, 11));
			txtCreditiAcquisiti.setFont(Font.font("Courier", FontWeight.BOLD, 11));

			
			
			leftBox.getChildren().addAll(new Label(" Media pesata:  "), txtMediaPesata);
			leftBox.getChildren().addAll(new Label(" Crediti acquisiti:  "), txtCreditiAcquisiti);
			this.setLeft(leftBox);
		
			
			
		// --------------right-----------------
		VBox rightBox = new VBox();
			rightBox.setPrefWidth(550);
			area = new TextArea();
			area.setPrefSize(550,500);
			//
			// DA FARE: impostazione font e immodificabilità dell'area di testo
			//
			area.setEditable(false);
			area.setFont(Font.font("Courier", FontWeight.NORMAL, 11));

			
			rightBox.getChildren().addAll(area);
		this.setRight(rightBox);
	}
	
	
	private void myHandle(ActionEvent ev) {
		
		selectedValue = carriereCombo.getValue();
		area.setText(controller.getCarriera(selectedValue).toString());
		
		double crediti = controller.getCreditiAcquisiti(selectedValue);
		txtCreditiAcquisiti.setText(formatter.format(crediti));
		
		double mediaPesata = controller.getMediaPesata(selectedValue);
		txtMediaPesata.setText(formatter.format(mediaPesata));
	}
	
	
}
