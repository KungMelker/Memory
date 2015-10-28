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
import javafx.scene.effect.Reflection;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {

	GameEngine gameEngine = new GameEngine();
	int row_column = 2;
	ImageView imageView[];
	int index;

	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1000, 600);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		// topBox
		HBox topBox = new HBox(5);
		topBox.setAlignment(Pos.CENTER);
		Label titel = new Label("Memory");
		titel.setId("game-title");

		topBox.getChildren().add(titel);

		Reflection refl = new Reflection();
		refl.setFraction(0.8);
		titel.setEffect(refl);

		// rightBox
		VBox rightBox = new VBox(15);
		Label player = new Label("Player");

		rightBox.getChildren().add(0, player);
		rightBox.setAlignment(Pos.CENTER);
		Label points = new Label("Points");

		rightBox.getChildren().add(1, points);

		Label pointresult = new Label("0");

		rightBox.getChildren().add(2, pointresult);

		// leftBox
		VBox leftBox = new VBox(5);
		leftBox.setAlignment(Pos.CENTER_LEFT);
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
		pairs_2.setSelected(true);

		leftBox.getChildren().addAll(pairs_2, pairs_4, pairs_6, pairs_8, pairs_10);

		// centerBox
		GridPane centerBox = new GridPane();
		centerBox.setAlignment(Pos.CENTER);

		
		// bottomBox

		HBox bottomBox = new HBox(50);
		bottomBox.setAlignment(Pos.TOP_CENTER);
		bottomBox.setPadding(new Insets(20));
		Button sQuit = new Button("Save & Quit");
		sQuit.setId("QuitSave");
		sQuit.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
		sQuit.setRotate(10.0);
		Button newGame = new Button("New Game");
		newGame.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 15));
		newGame.setId("NewGame");
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

		pairs_2.setOnAction(event -> {
			row_column = 2;
			displayBoard(centerBox);
			flipImage();
		});

		pairs_4.setOnAction(event -> {
			row_column = 4;
			displayBoard(centerBox);
			flipImage();
		});

		pairs_6.setOnAction(event -> {
			row_column = 6;
			displayBoard(centerBox);
			flipImage();
		});

		pairs_8.setOnAction(event -> {
			row_column = 8;
			displayBoard(centerBox);
			flipImage();
		});

		pairs_10.setOnAction(event -> {
			row_column = 10;
			displayBoard(centerBox);
			flipImage();
		});

	}

	private void flipImage() {

		for (int i = 0; i < imageView.length; i++){
			index = i;
			imageView[i].setOnMouseClicked(event -> {
				imageView[index].setImage(gameEngine.getFrontImage(index));
			});
		}

	}

	private void displayBoard(GridPane centerBox) {
		centerBox.getChildren().clear();
		imageView = gameEngine.initBoard(row_column);
		int index = 0;
		for (int i = 0; i < row_column; i++)
			for (int j = 0; j < row_column; j++) {

				centerBox.add(imageView[index], j, i);
				index++;
			}

	}
	private GridPane board(){
		
		return new GridPane();
	} 

	public static void main(String[] args) {
		launch(args);
	}
}