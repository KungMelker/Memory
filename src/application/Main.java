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
	GridPane centerBox = new GridPane();
	int row_column;
	ImageView imageView[];

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

		leftBox.getChildren().addAll(pairs_2, pairs_4, pairs_6, pairs_8, pairs_10);

		// centerBox

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
		root.setBottom(bottomBox);

		primaryStage.show();
		primaryStage.setTitle("Memory v0.1");

		// TODO - add a save function to sQuit - setOnAction
		sQuit.setOnAction(event -> {
			primaryStage.close();
		});

		pairs_2.setOnAction(event -> {
			row_column = 2;
			centerBox.getChildren().clear();
			centerBox = center_2();
			centerBox.setAlignment(Pos.CENTER);
			root.setCenter(centerBox);
			gameEngine.initBoard(row_column);
		});

		pairs_4.setOnAction(event -> {
			row_column = 4;
			centerBox.getChildren().clear();
			centerBox = center_4();
			centerBox.setAlignment(Pos.CENTER);
			root.setCenter(centerBox);
			gameEngine.initBoard(row_column);
		});

		pairs_6.setOnAction(event -> {
			row_column = 6;
			centerBox.getChildren().clear();
			centerBox = center_6();
			centerBox.setAlignment(Pos.CENTER);
			root.setCenter(centerBox);
			gameEngine.initBoard(row_column);

		});

		pairs_8.setOnAction(event -> {
			row_column = 8;

		});

		pairs_10.setOnAction(event -> {
			row_column = 10;

		});

	}

	public GridPane center_2() {

		GridPane tempCenter = new GridPane();

		imageView = new ImageView[(int) Math.pow(row_column, 2)];
		for (int i = 0; i < imageView.length; i++) {
			imageView[i] = new ImageView(new Image("/images/49.jpg", 500 / row_column, 500 / row_column, true, true));
		}

		int index = 0;
		for (int i = 0; i < row_column; i++)
			for (int j = 0; j < row_column; j++) {

				tempCenter.add(imageView[index], j, i);
				index++;
			}

		imageView[0].setOnMouseClicked(event -> {imageView[0].setImage(gameEngine.getFrontImage(0));});
		imageView[1].setOnMouseClicked(event -> {imageView[1].setImage(gameEngine.getFrontImage(1));});
		imageView[2].setOnMouseClicked(event -> {imageView[2].setImage(gameEngine.getFrontImage(2));});
		imageView[3].setOnMouseClicked(event -> {imageView[3].setImage(gameEngine.getFrontImage(3));});

		return tempCenter;
	}

	public GridPane center_4() {

		GridPane tempCenter = new GridPane();

		imageView = new ImageView[(int) Math.pow(row_column, 2)];
		for (int i = 0; i < imageView.length; i++) {
			imageView[i] = new ImageView(new Image("/images/49.jpg", 500 / row_column, 500 / row_column, true, true));
		}

		int index = 0;
		for (int i = 0; i < row_column; i++)
			for (int j = 0; j < row_column; j++) {

				tempCenter.add(imageView[index], j, i);
				index++;
			}

		imageView[0].setOnMouseClicked(event -> {imageView[0].setImage(gameEngine.getFrontImage(0));});
		imageView[1].setOnMouseClicked(event -> {imageView[1].setImage(gameEngine.getFrontImage(1));});
		imageView[2].setOnMouseClicked(event -> {imageView[2].setImage(gameEngine.getFrontImage(2));});
		imageView[3].setOnMouseClicked(event -> {imageView[3].setImage(gameEngine.getFrontImage(3));});
		imageView[4].setOnMouseClicked(event -> {imageView[4].setImage(gameEngine.getFrontImage(4));});
		imageView[5].setOnMouseClicked(event -> {imageView[5].setImage(gameEngine.getFrontImage(5));});
		imageView[6].setOnMouseClicked(event -> {imageView[6].setImage(gameEngine.getFrontImage(6));});
		imageView[7].setOnMouseClicked(event -> {imageView[7].setImage(gameEngine.getFrontImage(7));});
		imageView[8].setOnMouseClicked(event -> {imageView[8].setImage(gameEngine.getFrontImage(8));});
		imageView[9].setOnMouseClicked(event -> {imageView[9].setImage(gameEngine.getFrontImage(9));});
		imageView[10].setOnMouseClicked(event -> {imageView[10].setImage(gameEngine.getFrontImage(10));});
		imageView[11].setOnMouseClicked(event -> {imageView[11].setImage(gameEngine.getFrontImage(11));});
		imageView[12].setOnMouseClicked(event -> {imageView[12].setImage(gameEngine.getFrontImage(12));});
		imageView[13].setOnMouseClicked(event -> {imageView[13].setImage(gameEngine.getFrontImage(13));});
		imageView[14].setOnMouseClicked(event -> {imageView[14].setImage(gameEngine.getFrontImage(14));});
		imageView[15].setOnMouseClicked(event -> {imageView[15].setImage(gameEngine.getFrontImage(15));});
		
		return tempCenter;
	}
	
	public GridPane center_6() {

		GridPane tempCenter = new GridPane();

		imageView = new ImageView[(int) Math.pow(row_column, 2)];
		for (int i = 0; i < imageView.length; i++) {
			imageView[i] = new ImageView(new Image("/images/49.jpg", 500 / row_column, 500 / row_column, true, true));
		}

		int index = 0;
		for (int i = 0; i < row_column; i++)
			for (int j = 0; j < row_column; j++) {

				tempCenter.add(imageView[index], j, i);
				index++;
			}

		imageView[0].setOnMouseClicked(event -> {imageView[0].setImage(gameEngine.getFrontImage(0));});
		imageView[1].setOnMouseClicked(event -> {imageView[1].setImage(gameEngine.getFrontImage(1));});
		imageView[2].setOnMouseClicked(event -> {imageView[2].setImage(gameEngine.getFrontImage(2));});
		imageView[3].setOnMouseClicked(event -> {imageView[3].setImage(gameEngine.getFrontImage(3));});
		imageView[4].setOnMouseClicked(event -> {imageView[4].setImage(gameEngine.getFrontImage(4));});
		imageView[5].setOnMouseClicked(event -> {imageView[5].setImage(gameEngine.getFrontImage(5));});
		imageView[6].setOnMouseClicked(event -> {imageView[6].setImage(gameEngine.getFrontImage(6));});
		imageView[7].setOnMouseClicked(event -> {imageView[7].setImage(gameEngine.getFrontImage(7));});
		imageView[8].setOnMouseClicked(event -> {imageView[8].setImage(gameEngine.getFrontImage(8));});
		imageView[9].setOnMouseClicked(event -> {imageView[9].setImage(gameEngine.getFrontImage(9));});
		imageView[10].setOnMouseClicked(event -> {imageView[10].setImage(gameEngine.getFrontImage(10));});
		imageView[11].setOnMouseClicked(event -> {imageView[11].setImage(gameEngine.getFrontImage(11));});
		imageView[12].setOnMouseClicked(event -> {imageView[12].setImage(gameEngine.getFrontImage(12));});
		imageView[13].setOnMouseClicked(event -> {imageView[13].setImage(gameEngine.getFrontImage(13));});
		imageView[14].setOnMouseClicked(event -> {imageView[14].setImage(gameEngine.getFrontImage(14));});
		imageView[15].setOnMouseClicked(event -> {imageView[15].setImage(gameEngine.getFrontImage(15));});
		imageView[16].setOnMouseClicked(event -> {imageView[16].setImage(gameEngine.getFrontImage(16));});
		imageView[17].setOnMouseClicked(event -> {imageView[17].setImage(gameEngine.getFrontImage(17));});
		imageView[18].setOnMouseClicked(event -> {imageView[18].setImage(gameEngine.getFrontImage(18));});
		imageView[19].setOnMouseClicked(event -> {imageView[19].setImage(gameEngine.getFrontImage(19));});
		imageView[20].setOnMouseClicked(event -> {imageView[20].setImage(gameEngine.getFrontImage(20));});
		imageView[21].setOnMouseClicked(event -> {imageView[21].setImage(gameEngine.getFrontImage(21));});
		imageView[22].setOnMouseClicked(event -> {imageView[22].setImage(gameEngine.getFrontImage(22));});
		imageView[23].setOnMouseClicked(event -> {imageView[23].setImage(gameEngine.getFrontImage(23));});
		imageView[24].setOnMouseClicked(event -> {imageView[24].setImage(gameEngine.getFrontImage(24));});
		imageView[25].setOnMouseClicked(event -> {imageView[25].setImage(gameEngine.getFrontImage(25));});
		imageView[26].setOnMouseClicked(event -> {imageView[26].setImage(gameEngine.getFrontImage(26));});
		imageView[27].setOnMouseClicked(event -> {imageView[27].setImage(gameEngine.getFrontImage(27));});
		imageView[28].setOnMouseClicked(event -> {imageView[28].setImage(gameEngine.getFrontImage(28));});
		imageView[29].setOnMouseClicked(event -> {imageView[29].setImage(gameEngine.getFrontImage(29));});
		imageView[30].setOnMouseClicked(event -> {imageView[30].setImage(gameEngine.getFrontImage(30));});
		imageView[31].setOnMouseClicked(event -> {imageView[31].setImage(gameEngine.getFrontImage(31));});
		imageView[32].setOnMouseClicked(event -> {imageView[32].setImage(gameEngine.getFrontImage(32));});
		imageView[33].setOnMouseClicked(event -> {imageView[33].setImage(gameEngine.getFrontImage(33));});
		imageView[34].setOnMouseClicked(event -> {imageView[34].setImage(gameEngine.getFrontImage(34));});
		imageView[35].setOnMouseClicked(event -> {imageView[35].setImage(gameEngine.getFrontImage(35));});
		
		return tempCenter;
	}

	public static void main(String[] args) {
		launch(args);
	}
}