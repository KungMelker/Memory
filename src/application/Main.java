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
import javafx.scene.text.Text;

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
		rightBox.setAlignment(Pos.CENTER);
		
		Label highscore = new Label("Highscore");
        rightBox.getChildren().add(0, highscore);
		
		Label highpoint = new Label("0");
		rightBox.getChildren().add(1, highpoint);
		
		Label points = new Label("Points");
		rightBox.getChildren().add(2, points);

		Label pointresult = new Label("0");

		rightBox.getChildren().add(2, pointresult);
		
		Label timeLabel = new Label("Time");
		Text time = new Text();
		rightBox.getChildren().add(3, pointresult);
		rightBox.getChildren().add(4, timeLabel);
		rightBox.getChildren().add(5, time);

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
			imageView[i] = new ImageView(new Image("/images/49.jpg", 400 / row_column, 400 / row_column, true, true));
		}

		int index = 0;
		for (int i = 0; i < row_column; i++)
			for (int j = 0; j < row_column; j++) {

				tempCenter.add(imageView[index], j, i);
				index++;
			}

		imageView[0].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 0, row_column);
		});
		imageView[1].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 1, row_column);
		});
		imageView[2].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 2, row_column);
		});
		imageView[3].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 3, row_column);
		});

		return tempCenter;
	}

	public GridPane center_4() {

		GridPane tempCenter = new GridPane();

		imageView = new ImageView[(int) Math.pow(row_column, 2)];
		for (int i = 0; i < imageView.length; i++) {
			imageView[i] = new ImageView(new Image("/images/49.jpg", 400 / row_column, 400 / row_column, true, true));
		}

		int index = 0;
		for (int i = 0; i < row_column; i++)
			for (int j = 0; j < row_column; j++) {

				tempCenter.add(imageView[index], j, i);
				index++;
			}

		imageView[0].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 0, row_column);
		});
		imageView[1].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 1, row_column);
		});
		imageView[2].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 2, row_column);
		});
		imageView[3].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 3, row_column);
		});
		imageView[4].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 4, row_column);
		});
		imageView[5].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 5, row_column);
		});
		imageView[6].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 6, row_column);
		});
		imageView[7].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 7, row_column);
		});
		imageView[8].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 8, row_column);
		});
		imageView[9].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 9, row_column);
		});
		imageView[10].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 10, row_column);
		});
		imageView[11].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 11, row_column);
		});
		imageView[12].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 12, row_column);
		});
		imageView[13].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 13, row_column);
		});
		imageView[14].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 14, row_column);
		});
		imageView[15].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 15, row_column);
		});

		return tempCenter;
	}

	public GridPane center_6() {

		GridPane tempCenter = new GridPane();

		imageView = new ImageView[(int) Math.pow(row_column, 2)];
		for (int i = 0; i < imageView.length; i++) {
			imageView[i] = new ImageView(new Image("/images/49.jpg", 400 / row_column, 400 / row_column, true, true));
		}

		int index = 0;
		for (int i = 0; i < row_column; i++)
			for (int j = 0; j < row_column; j++) {

				tempCenter.add(imageView[index], j, i);
				index++;
			}

		imageView[0].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 0, row_column);
		});
		imageView[1].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 1, row_column);
		});
		imageView[2].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 2, row_column);
		});
		imageView[3].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 3, row_column);
		});
		imageView[4].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 4, row_column);
		});
		imageView[5].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 5, row_column);
		});
		imageView[6].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 6, row_column);
		});
		imageView[7].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 7, row_column);
		});
		imageView[8].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 8, row_column);
		});
		imageView[9].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 9, row_column);
		});
		imageView[10].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 10, row_column);
		});
		imageView[11].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 11, row_column);
		});
		imageView[12].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 12, row_column);
		});
		imageView[13].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 13, row_column);
		});
		imageView[14].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 14, row_column);
		});
		imageView[15].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 15, row_column);
		});
		imageView[16].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 16, row_column);
		});
		imageView[17].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 17, row_column);
		});
		imageView[18].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 18, row_column);
		});
		imageView[19].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 19, row_column);
		});
		imageView[20].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 20, row_column);
		});
		imageView[21].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 21, row_column);
		});
		imageView[22].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 22, row_column);
		});
		imageView[23].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 23, row_column);
		});
		imageView[24].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 24, row_column);
		});
		imageView[25].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 25, row_column);
		});
		imageView[26].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 26, row_column);
		});
		imageView[27].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 27, row_column);
		});
		imageView[28].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 28, row_column);
		});
		imageView[29].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 29, row_column);
		});
		imageView[30].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 30, row_column);
		});
		imageView[31].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 31, row_column);
		});
		imageView[32].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 32, row_column);
		});
		imageView[33].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 33, row_column);
		});
		imageView[34].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 34, row_column);
		});
		imageView[35].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 35, row_column);
		});

		return tempCenter;
	}

	public static void main(String[] args) {
		launch(args);
	}
}