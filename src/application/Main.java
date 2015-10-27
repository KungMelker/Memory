package application;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {

	GameEngine gameEngine = new GameEngine();

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
		VBox rightBox = new VBox(15);

		Label player = new Label("Player");
		player.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
		rightBox.getChildren().add(0, player);
		rightBox.setAlignment(Pos.CENTER);
		Label points = new Label("Points");
		points.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
		rightBox.getChildren().add(1, points);

		Label pointresult = new Label("0");
		pointresult.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
		rightBox.getChildren().add(2, pointresult);

		// leftBox
		VBox leftBox = new VBox(5);

		ToggleGroup pairsGroup = new ToggleGroup();
		RadioButton pairs_2 = new RadioButton("2 x 2");
		RadioButton pairs_4 = new RadioButton("4 x 4");
		RadioButton pairs_6 = new RadioButton("6 x 6");
		RadioButton pairs_8 = new RadioButton("8 x 8");
		RadioButton pairs_10 = new RadioButton("10 x 10");
		pairs_2.setToggleGroup(pairsGroup);
		pairs_4.setToggleGroup(pairsGroup);
		pairs_6.setToggleGroup(pairsGroup);
		pairs_8.setToggleGroup(pairsGroup);
		pairs_10.setToggleGroup(pairsGroup);

		leftBox.getChildren().addAll(pairs_2, pairs_4, pairs_6, pairs_8, pairs_10);

		// centerBox
		GridPane centerBox = new GridPane();
		centerBox.setAlignment(Pos.CENTER);

		Image cardImage[][] = new Image[2][2];

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				cardImage[i][j] = new Image("/images/0.jpg", 100, 100, true, true);
		
		ImageView imageView[][] = new ImageView[2][2];
		// imageView = gameEngine.getCardImages(antal par);
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				// kalla på funktion för get image
				imageView[i][j] = new ImageView(cardImage[i][j]);

		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				centerBox.addRow(i, imageView[i][j]);


		// bottomBox

		HBox bottomBox = new HBox(50);
		bottomBox.setAlignment(Pos.TOP_CENTER);
		bottomBox.setPadding(new Insets(15));
		Button sQuit = new Button("Save & Quit");
		sQuit.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
		sQuit.setRotate(10.0);
		Button newGame = new Button("New Game");
		newGame.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
		newGame.setRotate(5.0);
		bottomBox.getChildren().addAll(sQuit, newGame);

		root.setTop(topBox);
		root.setRight(rightBox);
		root.setLeft(leftBox);
		root.setCenter(centerBox);
		root.setBottom(bottomBox);

		primaryStage.show();
		primaryStage.setTitle("Memory v0.1");

		// TODO - add a save function to sQuit - setOnAction
		sQuit.setOnAction(event -> {
			primaryStage.close();
		});

		imageView[0][0].setOnMouseClicked(event -> {
			imageView[0][0].setImage(new Image("images/44.jpg", 100, 100, true, true));
			// imageView[0][0].setImage --- gameEngine.flipImage(index)
		});

	}


	public static void main(String[] args) {
		launch(args);
	}
}