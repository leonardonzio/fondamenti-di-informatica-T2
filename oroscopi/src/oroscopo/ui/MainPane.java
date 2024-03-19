package oroscopo.ui;



import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.swing.text.DateFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import oroscopo.controller.AbstractController;
import oroscopo.model.Oroscopo;
import oroscopo.model.SegnoZodiacale;

public class MainPane extends BorderPane{

	private AbstractController controller = null;
	private int fortunaMin;
	private TextArea output;
	private ComboBox<SegnoZodiacale> segniZodiacali;	
	
	public MainPane(AbstractController controller, int fortunaMin){

		this.controller = controller;
		this.fortunaMin = fortunaMin;
		
		// center
		VBox center = new VBox();
		{
			
			Label label = new Label("Segno zodiacale");
			label.setAlignment(Pos.BASELINE_RIGHT);
			center.getChildren().add(label);
			
			//-----
			var listSegni 		= Arrays.asList(controller.getSegni());
			var observableSegni = FXCollections.observableArrayList(listSegni);
			
			segniZodiacali = new ComboBox<>(observableSegni);
			segniZodiacali.setEditable(false);
			
			segniZodiacali.getSelectionModel().selectedItemProperty().addListener(
					(obs, oldV, newV) -> sceltaCombo()
					);
			
			center.getChildren().add(segniZodiacali);
			
			//-----
			label = new Label("\nOroscopo mensile del segno:");
			label.setAlignment(Pos.BASELINE_RIGHT);
			center.getChildren().add(label);
			
			//-----
			output = new TextArea();
			output.setEditable(false);
			center.getChildren().add(output);

			//-----
			Button b = new Button("Stampa annuale");
			b.setFont(Font.font("Courier new", 14));
			
			b.setOnAction(ev -> {
				try {
					handleButton(ev);
				} catch (IOException e) {
					e.printStackTrace();
				}
			});

			center.getChildren().add(b);
			
		}
		this.setCenter(center);
	
	}
	
	private void sceltaCombo() {
		var segno = segniZodiacali.getValue();
		var oroscopo = controller.generaOroscopoCasuale(segno);
		
		output.setText(oroscopo.toString());
	}
	
	private void handleButton (ActionEvent ev) throws IOException {
	
		try (var writer = new BufferedWriter(new FileWriter("provaUI.txt"))) {
			
			var segno = segniZodiacali.getValue();
			Oroscopo[] oroscopi = controller.generaOroscopoAnnuale(segno, fortunaMin);
			
			writer.write(segno.name());
			writer.newLine();
			writer.write("-------");
			writer.newLine();
			
			for (int i = 0; i < oroscopi.length; i++) {
				
				Oroscopo o = oroscopi[i];
				Month month = Month.of(i + 1);
				String formattedMonth = month.getDisplayName(TextStyle.FULL, Locale.ITALY).toUpperCase();
				
				writer.write(formattedMonth);
				writer.newLine();
				
				writer.write("Amore:\t");
				writer.write(o.getPrevisioneAmore().getPrevisione());
				writer.newLine();
				
				writer.write("Lavoro:\t");
				writer.write(o.getPrevisioneLavoro().getPrevisione());
				writer.newLine();
				
				writer.write("Salute:\t");
				writer.write(o.getPrevisioneSalute().getPrevisione());
				writer.newLine();
				
				writer.newLine();
			}
			
			writer.write("GRADO DI FORTUNA:\t");
			
			var fortunaMedia = (Arrays.asList(oroscopi).stream()
					.mapToInt(Oroscopo::getFortuna)
					.sum())
					/ oroscopi.length;
			
			writer.write(String.valueOf(fortunaMin));
			writer.newLine();
			
		}
		catch (IOException e) {
			throw new IOException(e.getMessage());
		}
		
	}

	
	
	
	
	
	
	
	
	
}
