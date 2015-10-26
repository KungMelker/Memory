package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		// A simple memory game

		FlowPane root = new FlowPane();

		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		// Adds title to main window
		primaryStage.setTitle("Memory v0.1");

		// random comment from EseBring3n

		root.getChildren().addAll(createImageArray());
		
		}

	public static void main(String[] args) {
		launch(args);
	}

	public ImageView[] createImageArray() {

		//Creates an array of images
		ImageView[] array_of_images = new ImageView[20];
		for (int i = 0; i < array_of_images.length; i++) {
			array_of_images[i] = new ImageView(
					new Image("https://avatars0.githubusercontent.com/u/15321448?v=3&s=400", 30, 30, true, true));

		}
		return array_of_images;
	}
}
