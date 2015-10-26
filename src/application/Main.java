package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		// A simple memory game

		GridPane root = new GridPane();
		
		Image cardImage = new Image("http://mobile-visuals.com/icon.png");
		ImageView imageView = new ImageView(cardImage);
		
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		root.setAlignment(Pos.CENTER);
		root.addRow(0, imageView);

		primaryStage.show();
		primaryStage.setTitle("Memory v0.1");

	}

	public static void main(String[] args) {
		launch(args);
	}
}
