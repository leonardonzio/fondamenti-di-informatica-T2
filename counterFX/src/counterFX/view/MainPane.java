package counterFX.view;


import java.awt.Color;

import org.w3c.dom.html.HTMLOptGroupElement;

import counterFX.controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class MainPane extends BorderPane{

	private Controller controller;
	private TextField txtVal;
	private Button reset, increase, decrease;
	private RadioButton[] buttonsCounter;
	private int[] counters;
	private int currentCounter;
	private ToggleGroup tg;
	
	public MainPane(Controller controller) {
		
		this.controller = controller;
		buttonsCounter = new RadioButton[controller.getCapacity()];
		counters = new int[controller.getCapacity()];
		
		// top
		HBox topBox = new HBox();
		tg = new ToggleGroup();
		topBox.setAlignment(Pos.CENTER);
		
		for (int i = 0; i < 3; i++) {
			
			buttonsCounter[i] = new RadioButton("counter: " + i);
			buttonsCounter[i].setFont(Font.font("Courier New", FontWeight.NORMAL, 14));
			buttonsCounter[i].setToggleGroup(tg);
			
			counters[i] = controller.newCounter();
			topBox.getChildren().addAll(buttonsCounter[i]);
			buttonsCounter[i].setOnAction(this::setCurrentCounter);
		}
		
		currentCounter = 0;
		tg.selectToggle(buttonsCounter[currentCounter]);
		this.setTop(topBox);
	
		
		// center
		HBox centerBox = new HBox();
		Label titolo = new Label("valore attuale: ");
		titolo.setFont(Font.font("Courier New", FontWeight.NORMAL, 16));
		titolo.setStyle("-fx-font-weight: bold");
		
		txtVal = new TextField();
		txtVal.setPrefWidth(40);
		txtVal.setEditable(false);
		txtVal.setText(controller.getCounterValueAsString());
		
		centerBox.getChildren().addAll(titolo, txtVal);
		this.setCenter(centerBox);
		
		// bottom
		HBox bottomBox = new HBox();
		
		increase = new ButtonOfCounter("aumenta di 1", "lightgreen");
		decrease = new ButtonOfCounter("decrementa di 1", "red");
		reset = new ButtonOfCounter("reset", "lightblue");
		
		increase.setOnAction(ev -> myHandle(controller::incCounter));
		decrease.setOnAction(ev -> myHandle(controller::decCounter));
		reset.setOnAction(ev -> myHandle(controller::resetCounter));
		
		bottomBox.getChildren().addAll(increase, decrease, reset);
		this.setBottom(bottomBox);
		
		// topBox
		HBox topBox = new HBox();
		
		
		
		
	}
	
	@FunctionalInterface
	public interface Action {
		void doOp();
	}
	
	private void setCurrentCounter(ActionEvent ev) {
		currentCounter = 
	}
	
	
	private void myHandle(Action action) {
		action.doOp();
		txtVal.setText(controller.getCounterValueAsString());
	}
		
}
