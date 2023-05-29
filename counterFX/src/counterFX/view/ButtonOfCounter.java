package counterFX.view;

import javafx.scene.control.Button;

public class ButtonOfCounter extends Button{

	public ButtonOfCounter(String txt, String color) {
		super(txt);
		this.setStyle("-fx-font-weight: bold");
		this.setStyle("-fx-background-color: " + color);
		this.setPrefWidth(100);
	}
	
	
}
