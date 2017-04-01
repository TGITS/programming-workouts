package tgits.programming.workout.simplejfx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
		System.out.println("JavaFX out");
	}

	@Override
	public void start(Stage stage) {
		System.out.println("JavaFX is taking the stage");
		String displayMessage = "A simple JavaFX program";
		stage.setTitle(displayMessage);
		Group root = new Group();
		Scene scene = new Scene(root, 300, 250);
		Label message = new Label(displayMessage);
		message.setLayoutX(40);
		message.setLayoutY(30);
		message.setFont(new Font(20));
		root.getChildren().add(message);
		Button btn = new Button();
		btn.setLayoutX(100);
		btn.setLayoutY(80);
		btn.setText("Click me");
		btn.setOnAction(event -> System.out.println(displayMessage));
		root.getChildren().add(btn);
		stage.setScene(scene);
		stage.setTitle("A message from JavaFX");
		stage.show();
	}

}
