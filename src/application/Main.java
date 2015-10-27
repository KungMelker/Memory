package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 400);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);		
		
		
		// topBox
		HBox topBox = new HBox(5);
		topBox.setAlignment(Pos.CENTER);
		Label titel = new Label("Memory");
		titel.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 25));
		topBox.getChildren().add(titel);

		// rightBox
		VBox rightBox = new VBox(5);
		//rightBox.setAlignment(Pos.CENTER);
		Label player = new Label("Player");
		player.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
		rightBox.getChildren().add(0,player);
		
		Label points = new Label("Points");
		points.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
		rightBox.getChildren().add(1,points);
		
		Label pointresult = new Label("0");
		pointresult.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
		rightBox.getChildren().add(2,pointresult);

		// centerBox
		GridPane centerBox = new GridPane();
		centerBox.setAlignment(Pos.CENTER);

		Image cardImage[][] = new Image[2][2];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				cardImage[i][j] = new Image("http://mobile-visuals.com/icon.png", 40, 40, true, true);
		
		ImageView imageView[][] = new ImageView[2][2];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				// kalla på funktion för get image
				imageView[i][j] = new ImageView(cardImage[i][j]);

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				centerBox.addRow(i, imageView[i][j]);

		
		
		//bottomBox
		HBox bottomBox = new HBox(5);
		bottomBox.setAlignment(Pos.CENTER);
		Button sQuit = new Button("Save & Quit");
		sQuit.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
		Button newGame = new Button("New Game");
		newGame.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
		bottomBox.getChildren().addAll(sQuit,newGame);
		
		root.setTop(topBox);
		root.setRight(rightBox);
		root.setCenter(centerBox);
		root.setBottom(bottomBox);

		primaryStage.show();
		primaryStage.setTitle("Memory v0.1");

		imageView[0][0].setOnMouseClicked(event -> {
			imageView[0][0].setImage(new Image("http://vk.com/images/gifts/256/44.jpg", 40, 40, true, true));
		});

	}


	public static void main(String[] args) {
		launch(args);
	}
}