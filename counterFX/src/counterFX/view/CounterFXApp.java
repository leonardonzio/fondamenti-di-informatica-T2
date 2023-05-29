package counterFX.view;

import counterFX.controller.Controller;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class CounterFXApp extends Application{

	private Controller controller;

	@Override
	public void start(Stage stage) {
		stage.setTitle("CounterFX");
		controller = new Controller(3);
		BorderPane root = new MainPane(controller);
		Scene scene = new Scene(root, 820, 550, Color.ALICEBLUE);
		stage.setScene(scene);
		stage.show();
	}

	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
}
