package flights.ui;

import java.time.LocalDate;
import java.util.List;

import flights.controller.Controller;
import flights.model.Airport;
import flights.model.FlightSchedule;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainPane extends BorderPane{

	private Controller controller;
	private ComboBox<Airport> depAirportComboBox;
	private ComboBox<Airport> arrAirportComboBox;
	private DatePicker scegliData;
	private FlightScheduleListPanel  flightScheduleListPanel;
	
	public MainPane(Controller controller) {
		
		this.controller = controller;
		
		
		// ---------------leftbox------------------
		
		VBox leftBox = new VBox();
		{
			List<Airport> airports = controller.getSortedAirports();
			ObservableList<Airport> observableAirports = FXCollections.observableArrayList(airports);
			
			// departure
			Label label = new Label("Departure Airport");
			leftBox.getChildren().add(label);
					
			depAirportComboBox = new ComboBox<Airport>(observableAirports);
			depAirportComboBox.setEditable(false);
			depAirportComboBox.setValue(observableAirports.get(0));
			leftBox.getChildren().add(depAirportComboBox);
		
			// arrival
			label = new Label("Arrival Airport");
			leftBox.getChildren().add(label);
		
			arrAirportComboBox = new ComboBox<Airport>(observableAirports);
			arrAirportComboBox.setEditable(false);
			arrAirportComboBox.setValue(observableAirports.get(0));
			leftBox.getChildren().add(arrAirportComboBox);
		
			// data partenza
			label = new Label("Departure Date");
			leftBox.getChildren().add(label);
		
			scegliData = new DatePicker(LocalDate.now());
			scegliData.setValue(LocalDate.now());
			leftBox.getChildren().add(scegliData);
		
			// bottone per cercare
			Button searchButton = new Button("Find");
			searchButton.setOnAction(this::myHandle);
			leftBox.getChildren().add(searchButton);
		}
		
		leftBox.setAlignment(Pos.BASELINE_RIGHT);
		this.setLeft(leftBox);
		
		// ---------------right-------
		flightScheduleListPanel = new FlightScheduleListPanel();
		this.setCenter(flightScheduleListPanel);
		
	}
	
	private void myHandle(ActionEvent ev) {

		var depAirport = depAirportComboBox.getValue();
		var arrAirport = arrAirportComboBox.getValue();
		var date = scegliData.getValue();
		
		List<FlightSchedule> schedules = controller.searchFlights(depAirport, arrAirport, date);
		flightScheduleListPanel.setFlightSchedules(schedules);		
	}
	
	
	
}
