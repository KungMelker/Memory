
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
import javafx.scene.text.Text;

public class Main extends Application {

	GameEngine gameEngine = new GameEngine();
	GridPane centerBox = new GridPane();
	int row_column;
	ImageView imageView[];

	@Override
	public void start(Stage primaryStage) {

		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 1000, 700);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		// topBox
		VBox topBox = new VBox();
		topBox.setAlignment(Pos.CENTER);
		topBox.setId("topbox");
		Label title = new Label("Memories Lost");
		Label subtitle = new Label(
				"Nightmares from Git: Curses by the Oracle - Mission to MERGE\nReturn of the Cannibal Pixel Demons");
		title.setId("game-title");
		subtitle.setId("game-subtitle");


		topBox.getChildren().addAll(title,subtitle);


		// rightBox
		VBox rightBox = new VBox();
		rightBox.setId("leftbox");
		rightBox.setPadding(new Insets(20.0));
		rightBox.setAlignment(Pos.CENTER_LEFT);

		Label highscore = new Label("Highscore");
		Label highpoint = new Label("0");
		Label points = new Label("Points");
		Label pointresult = new Label("0");
		Label timeLabel = new Label("Time");
		Text time = new Text();

		Label tries = new Label("Tries");
		Label presentTries = new Label("0");

		rightBox.getChildren().addAll(highscore, highpoint, points, pointresult, timeLabel, time, tries, presentTries);

		// leftBox
		VBox leftBox = new VBox(5);
		leftBox.setAlignment(Pos.CENTER_LEFT);
		leftBox.setPadding(new Insets(20.0));
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
		row_column = 2;

		leftBox.getChildren().addAll(pairs_2, pairs_4, pairs_6, pairs_8, pairs_10);

		// bottomBox
		HBox bottomBox = new HBox(50);
		bottomBox.setAlignment(Pos.TOP_CENTER);
		bottomBox.setPadding(new Insets(20));
		bottomBox.setId("bottombox");
		Button sQuit = new Button("Quit");
		sQuit.setId("QuitSave");
		Button newGame = new Button("New Game");
		newGame.setId("NewGame");

		bottomBox.getChildren().addAll(newGame, sQuit);

		root.setTop(topBox);
		root.setRight(rightBox);
		root.setLeft(leftBox);
		root.setBottom(bottomBox);

		primaryStage.show();
		primaryStage.setTitle("Memory v0.1");

		// TODO - add a save function to sQuit - setOnAction
		newGame.setOnAction(event -> {

			centerBox.getChildren().clear();
			switch (row_column) {
			case 2:
				centerBox = center_2();
				break;

			case 4:
				centerBox = center_4();
				break;

			case 6:
				centerBox = center_6();
				break;

			case 8:
				centerBox = center_8();
				break;

			default:
				centerBox = center_10();
				break;
			}
			centerBox.setAlignment(Pos.CENTER);
			root.setCenter(centerBox);
			gameEngine.initBoard(row_column);
			gameEngine.setTryes(0);
			gameEngine.setFoundPairs(0);

		});

		sQuit.setOnAction(event -> {
			primaryStage.close();
		});

		pairs_2.setOnAction(event -> {

			row_column = 2;

		});

		pairs_4.setOnAction(event -> {
			row_column = 4;

		});

		pairs_6.setOnAction(event -> {
			row_column = 6;

		});

		pairs_8.setOnAction(event -> {
			row_column = 8;

		});

		pairs_10.setOnAction(event -> {
			row_column = 10;

		});

		root.setOnMouseClicked(event -> {
			presentTries.setText(Integer.toString(gameEngine.getTries()));
			if (gameEngine.getFoundPairs() == 0 && gameEngine.getStart() == 0) {
				gameEngine.startTime();
			} else if (gameEngine.getFoundPairs() == (gameEngine.getCards().length / 2)) {
				gameEngine.stopTime();
				time.setText(Long.toString(gameEngine.timePlayed())+" secs");
			}

		});

	}

	public GridPane center_2() {

		GridPane tempCenter = new GridPane();
		tempCenter.setPadding(new Insets(2));
		tempCenter.setHgap(2);
		tempCenter.setVgap(2);

		imageView = new ImageView[(int) Math.pow(row_column, 2)];
		for (int i = 0; i < imageView.length; i++) {
			imageView[i] = new ImageView(new Image("/abstract/50.png", 400 / row_column, 400 / row_column, true, true));
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
		tempCenter.setPadding(new Insets(2));
		tempCenter.setHgap(2);
		tempCenter.setVgap(2);

		imageView = new ImageView[(int) Math.pow(row_column, 2)];
		for (int i = 0; i < imageView.length; i++) {
			imageView[i] = new ImageView(new Image("/abstract/50.png", 400 / row_column, 400 / row_column, true, true));
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
			imageView[i] = new ImageView(new Image("/abstract/50.png", 400 / row_column, 400 / row_column, true, true));
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


	public GridPane center_8() {

		GridPane tempCenter = new GridPane();

		imageView = new ImageView[(int) Math.pow(row_column, 2)];
		for (int i = 0; i < imageView.length; i++) {
			imageView[i] = new ImageView(new Image("/abstract/50.png", 400 / row_column, 400 / row_column, true, true));
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
		imageView[36].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 36, row_column);
		});
		imageView[37].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 37, row_column);
		});
		imageView[38].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 38, row_column);
		});
		imageView[39].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 39, row_column);
		});
		imageView[40].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 40, row_column);
		});
		imageView[41].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 41, row_column);
		});
		imageView[42].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 42, row_column);
		});
		imageView[43].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 43, row_column);
		});
		imageView[44].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 44, row_column);
		});
		imageView[45].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 45, row_column);
		});
		imageView[46].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 46, row_column);
		});
		imageView[47].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 47, row_column);
		});
		imageView[48].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 48, row_column);
		});
		imageView[49].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 49, row_column);
		});
		imageView[50].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 50, row_column);
		});
		imageView[51].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 51, row_column);
		});
		imageView[52].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 52, row_column);
		});
		imageView[53].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 53, row_column);
		});
		imageView[54].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 54, row_column);
		});
		imageView[55].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 55, row_column);
		});
		imageView[56].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 56, row_column);
		});
		imageView[57].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 57, row_column);
		});
		imageView[58].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 58, row_column);
		});
		imageView[59].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 59, row_column);
		});
		imageView[60].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 60, row_column);
		});
		imageView[61].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 61, row_column);
		});
		imageView[62].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 62, row_column);
		});
		imageView[63].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 63, row_column);
		});

		return tempCenter;
	}

	public GridPane center_10() {

		GridPane tempCenter = new GridPane();

		imageView = new ImageView[(int) Math.pow(row_column, 2)];
		for (int i = 0; i < imageView.length; i++) {
			imageView[i] = new ImageView(new Image("/abstract/50.png", 400 / row_column, 400 / row_column, true, true));
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
		imageView[36].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 36, row_column);
		});
		imageView[37].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 37, row_column);
		});
		imageView[38].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 38, row_column);
		});
		imageView[39].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 39, row_column);
		});
		imageView[40].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 40, row_column);
		});
		imageView[41].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 41, row_column);
		});
		imageView[42].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 42, row_column);
		});
		imageView[43].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 43, row_column);
		});
		imageView[44].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 44, row_column);
		});
		imageView[45].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 45, row_column);
		});
		imageView[46].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 46, row_column);
		});
		imageView[47].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 47, row_column);
		});
		imageView[48].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 48, row_column);
		});
		imageView[49].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 49, row_column);
		});
		imageView[50].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 50, row_column);
		});
		imageView[51].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 51, row_column);
		});
		imageView[52].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 52, row_column);
		});
		imageView[53].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 53, row_column);
		});
		imageView[54].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 54, row_column);
		});
		imageView[55].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 55, row_column);
		});
		imageView[56].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 56, row_column);
		});
		imageView[57].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 57, row_column);
		});
		imageView[58].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 58, row_column);
		});
		imageView[59].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 59, row_column);
		});
		imageView[60].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 60, row_column);
		});
		imageView[61].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 61, row_column);
		});
		imageView[62].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 62, row_column);
		});
		imageView[63].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 63, row_column);
		});
		imageView[64].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 64, row_column);
		});
		imageView[65].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 65, row_column);
		});
		imageView[66].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 66, row_column);
		});
		imageView[67].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 67, row_column);
		});
		imageView[68].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 68, row_column);
		});
		imageView[69].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 69, row_column);
		});
		imageView[70].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 70, row_column);
		});
		imageView[71].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 71, row_column);
		});
		imageView[72].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 72, row_column);
		});
		imageView[73].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 73, row_column);
		});
		imageView[74].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 74, row_column);
		});
		imageView[75].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 75, row_column);
		});
		imageView[76].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 76, row_column);
		});
		imageView[77].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 77, row_column);
		});
		imageView[78].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 78, row_column);
		});
		imageView[79].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 79, row_column);
		});
		imageView[80].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 80, row_column);
		});
		imageView[81].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 81, row_column);
		});
		imageView[82].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 82, row_column);
		});
		imageView[83].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 83, row_column);
		});
		imageView[84].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 84, row_column);
		});
		imageView[85].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 85, row_column);
		});
		imageView[86].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 86, row_column);
		});
		imageView[87].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 87, row_column);
		});
		imageView[88].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 88, row_column);
		});
		imageView[89].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 89, row_column);
		});
		imageView[90].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 90, row_column);
		});
		imageView[91].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 91, row_column);
		});
		imageView[92].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 92, row_column);
		});
		imageView[93].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 93, row_column);
		});
		imageView[94].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 94, row_column);
		});
		imageView[95].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 95, row_column);
		});
		imageView[96].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 96, row_column);
		});
		imageView[97].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 97, row_column);
		});
		imageView[98].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 98, row_column);
		});
		imageView[99].setOnMouseClicked(event -> {
			gameEngine.getFrontImage(imageView, 99, row_column);
		});

		return tempCenter;
	}

	public static void main(String[] args) {
		launch(args);
	}
}