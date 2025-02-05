package rfd.ui;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import rfd.controller.Controller;
import rfd.model.Route;


public class MainPane extends BorderPane {
	
	private TextArea outputArea; 
	private ComboBox<String> comboFrom, comboTo;
	private Controller controller;
	private RadioButton b1, b2;
	private ToggleGroup tg;

	public MainPane(Controller controller) {
		this.controller=controller;
		//
		HBox topBox = new HBox();
		topBox.setPrefHeight(40);
		// populate combos
		comboFrom = new ComboBox<>(); populateCombo(comboFrom);
		comboTo   = new ComboBox<>(); populateCombo(comboTo);
		comboFrom.setOnAction(this::reset);
		comboTo.setOnAction(this::reset);
		topBox.getChildren().addAll(new Label("Partenza "), comboFrom, new Label("  Arrivo "), comboTo);
		this.setTop(topBox);
		//
		HBox centerBox = new HBox();
		centerBox.setPrefHeight(80);
		centerBox.setAlignment(Pos.CENTER);
		b1 = new RadioButton("per distanza");
		b2 = new RadioButton("per durata");
		tg = new ToggleGroup();
		b1.setToggleGroup(tg);
		b2.setToggleGroup(tg);
		centerBox.getChildren().addAll(new Label("Ordina soluzioni:    "), b1, new Label("   "), b2);
		this.setCenter(centerBox);
		//
		b1.setOnAction(e -> showSorted(OrderType.BY_DISTANCE)); 
		b2.setOnAction(e -> showSorted(OrderType.BY_DURATION)); 
		//
		outputArea = new TextArea();
		outputArea.setPrefSize(500,400);
		this.setBottom(outputArea);
	}

	private void populateCombo(ComboBox<String> combo) {
		
		var stationNames = controller.getStationNames();
		combo.setItems(FXCollections.observableArrayList(stationNames).sorted());
	}
	
	private void reset(ActionEvent event) {
		outputArea.setText("");
		if(tg.getSelectedToggle() != null) 
			tg.getSelectedToggle().setSelected(false);
		
	}
	
	private void showSorted(OrderType orderType) {
		
		String from = comboFrom.getValue();
		String to 	= comboTo.getValue();
		var cmp = (orderType.equals(OrderType.BY_DISTANCE))
				? Comparator.comparing(Route::getLength)
				: Comparator.comparing(controller::getRouteDuration);
		
		List<Route> routes = controller.getRoutes(from, to);
		routes.sort(cmp);
		
		outputArea.setText(routes.stream()
				.map(this::toRouteString)
				.collect(Collectors.joining(System.lineSeparator())));
		
	}
	
	private String toRouteString(Route route) {

		var routeDuration = controller.getRouteDuration(route);
		var strBuilder = new StringBuilder();
		var minuti = routeDuration.toMinutes();
		
		return strBuilder.append(route.toString())
				.append(System.lineSeparator())
				.append("Durata: ")
				.append(String.format("%d:%02d", minuti/60, minuti%60))
				.toString();
		
	}

}
