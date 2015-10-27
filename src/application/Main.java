package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		
		
		Image cardImage[][] = new Image[2][2];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				cardImage[i][j] = new Image("http://mobile-visuals.com/icon.png", 40, 40, true, true);

		ImageView imageView[][] = new ImageView[2][2];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				// kalla på funktion för get image
				imageView[i][j] = new ImageView(cardImage[i][j]);

		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		GridPane centerBox = new GridPane();
		centerBox.setAlignment(Pos.CENTER);
		
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				centerBox.addRow(i, imageView[i][j]);

		root.setCenter(centerBox);
		primaryStage.show();
		primaryStage.setTitle("Memory v0.1");
		

		imageView[0][0].setOnMouseClicked(event -> {
			imageView[0][0].setImage(new Image("http://vk.com/images/gifts/256/44.jpg",40,40,true,true));
		});

	}

	public static void main(String[] args) {
		launch(args);
	}
}