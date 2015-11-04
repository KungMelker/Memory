
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

/**
 * 
 * @author Sam, Anton, Johan, Melker, Gustav
 * Creates the design of the project.
 * Adds objects, texts, labels and radiobuttons. 
 */

public class Main extends Application {

	GameEngine gameEngine = new GameEngine();
	GridPane centerBox = new GridPane();
	HighScore hs = new HighScore();
	int selectedCase;
	int row_column;
	ImageView imageView[];
	boolean win = false;

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

		topBox.getChildren().addAll(title, subtitle);

		// rightBox
		/**
		 * Adds a VBox on the right side of the project.
		 * Adds labels highscore, point, time and tries.
		 */
		VBox rightBox = new VBox();
		rightBox.setId("rightbox");

		Label stats = new Label("Player Stats:\n\n\n");
		stats.setAlignment(Pos.TOP_CENTER);
		Label highscore = new Label("Highscore");
		Label highpoint = new Label("0");

		Label points = new Label("Points");
		Label pointresult = new Label("0");

		Label timeLabel = new Label("Time");
		Label time = new Label("0");

		Label tries = new Label("Tries");
		Label presentTries = new Label("0");

		rightBox.getChildren().addAll(stats, highscore, highpoint, points, pointresult, timeLabel, time, tries,
				presentTries);

		// leftBox
		VBox leftBox = new VBox(5);
		leftBox.setId("leftbox");
		Label memorySize = new Label("Memory Size:\n\n\n\n");
		memorySize.setAlignment(Pos.TOP_CENTER);
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

		leftBox.getChildren().addAll(memorySize, pairs_2, pairs_4, pairs_6, pairs_8, pairs_10);

		// bottomBox
		HBox bottomBox = new HBox(50);
		bottomBox.setAlignment(Pos.TOP_CENTER);
		bottomBox.setPadding(new Insets(20));
		bottomBox.setId("bottombox");
		Button sQuit = new Button("Rage Quit");
		sQuit.setPrefWidth(150);
		sQuit.setId("QuitSave");
		Button newGame = new Button("New Game");
		newGame.setPrefWidth(150);
		newGame.setId("NewGame");

		bottomBox.getChildren().addAll(newGame, sQuit);

		root.setTop(topBox);
		root.setRight(rightBox);
		root.setLeft(leftBox);
		root.setBottom(bottomBox);

		primaryStage.show();
		primaryStage.setTitle("Memory v0.2 for Dummies");

		newGame.setOnAction(event -> {

			centerBox.getChildren().clear();
			switch (row_column) {
			case 2:
				centerBox = center_2();
				selectedCase = 0;
				highpoint.setText(hs.getScore(0));
				break;

			case 4:
				centerBox = center_4();
				selectedCase = 1;
				highpoint.setText(hs.getScore(1));
				break;

			case 6:
				centerBox = center_6();
				selectedCase = 2;
				highpoint.setText(hs.getScore(2));
				break;

			case 8:
				centerBox = center_8();
				selectedCase = 3;
				highpoint.setText(hs.getScore(3));
				break;

			default:
				centerBox = center_10();
				selectedCase = 4;
				highpoint.setText(hs.getScore(4));
				break;
			}
			centerBox.setAlignment(Pos.CENTER);
			root.setCenter(centerBox);
			gameEngine.initBoard(row_column);
			gameEngine.setTries(0);
			gameEngine.setFoundPairs(0);
			gameEngine.setStart(0);
			gameEngine.setCurrentScore(0);
			win = false;
		});

		// TODO - add a save function to sQuit - setOnAction
		sQuit.setOnAction(event -> {
			hs.writeFile();
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

			if (!win) {
				if (gameEngine.getFoundPairs() == 0 && gameEngine.getStart() == 0) {
					gameEngine.startTime();
					pointresult.setText("0");
					time.setText("0");
				} else if (gameEngine.getFoundPairs() == (gameEngine.getCards().length / 2)) {

					gameEngine.checkTime();
					time.setText(Long.toString(gameEngine.timePlayed()) + " sec");
					pointresult.setText(
							Double.toString(gameEngine.calculateScore(row_column, gameEngine.getElapsedTime())));
					// Win message
					this.winText();
					win = true;

					// check score with hi-score
					double currScore = gameEngine.getCurrentScore();
					double hiScore = Double.parseDouble(hs.getScore(selectedCase));
					if (gameEngine.compareScore(currScore, hiScore)) {
						hs.updateScore(gameEngine.getCurrentScore(), selectedCase);
						highpoint.setText(hs.getScore(selectedCase));
					}
				}

				else {
					gameEngine.checkTime();
					time.setText(Long.toString(gameEngine.timePlayed()) + " sec");

				}
			}
		});

	}

	private void winText() {

		Text won = new Text("Fatality!");
		won.setId("win");
		won.setRotate(30);

		GridPane.setConstraints(won, 0, 0, 10, 10);
		centerBox.getChildren().add(won);

	}

	public GridPane center_2() {

		GridPane tempCenter = new GridPane();
		tempCenter.setPadding(new Insets(2));
		tempCenter.setHgap(2);
		tempCenter.setVgap(2);

		imageView = new ImageView[(int) Math.pow(row_column, 2)];

		for (int i = 0; i < imageView.length; i++) {
			int temp = i;
			imageView[i] = new ImageView(new Image("/abstract/50.png", 400 / row_column, 400 / row_column, true, true));
			imageView[i].setOnMouseClicked(event -> {
				gameEngine.getFrontImage(imageView, temp, row_column);
			});
		}

		int index = 0;
		for (int i = 0; i < row_column; i++)
			for (int j = 0; j < row_column; j++) {

				tempCenter.add(imageView[index], j, i);
				index++;
			}
		return tempCenter;
	}

	public GridPane center_4() {

		GridPane tempCenter = new GridPane();
		tempCenter.setPadding(new Insets(2));
		tempCenter.setHgap(2);
		tempCenter.setVgap(2);

		imageView = new ImageView[(int) Math.pow(row_column, 2)];
		for (int i = 0; i < imageView.length; i++) {
			int temp = i;
			imageView[i] = new ImageView(new Image("/abstract/50.png", 400 / row_column, 400 / row_column, true, true));
			imageView[i].setOnMouseClicked(event -> {
				gameEngine.getFrontImage(imageView, temp, row_column);
			});
		}

		int index = 0;
		for (int i = 0; i < row_column; i++)
			for (int j = 0; j < row_column; j++) {

				tempCenter.add(imageView[index], j, i);
				index++;
			}
		return tempCenter;
	}

	public GridPane center_6() {

		GridPane tempCenter = new GridPane();

		imageView = new ImageView[(int) Math.pow(row_column, 2)];
		for (int i = 0; i < imageView.length; i++) {
			int temp = i;
			imageView[i] = new ImageView(new Image("/abstract/50.png", 400 / row_column, 400 / row_column, true, true));
			imageView[i].setOnMouseClicked(event -> {
				gameEngine.getFrontImage(imageView, temp, row_column);
			});
		}

		int index = 0;
		for (int i = 0; i < row_column; i++)
			for (int j = 0; j < row_column; j++) {

				tempCenter.add(imageView[index], j, i);
				index++;
			}
		return tempCenter;
	}

	public GridPane center_8() {

		GridPane tempCenter = new GridPane();

		imageView = new ImageView[(int) Math.pow(row_column, 2)];
		for (int i = 0; i < imageView.length; i++) {
			int temp = i;
			imageView[i] = new ImageView(new Image("/abstract/50.png", 400 / row_column, 400 / row_column, true, true));
			imageView[i].setOnMouseClicked(event -> {
				gameEngine.getFrontImage(imageView, temp, row_column);
			});
		}

		int index = 0;
		for (int i = 0; i < row_column; i++)
			for (int j = 0; j < row_column; j++) {

				tempCenter.add(imageView[index], j, i);
				index++;
			}
		return tempCenter;
	}

	public GridPane center_10() {

		GridPane tempCenter = new GridPane();

		imageView = new ImageView[(int) Math.pow(row_column, 2)];
		for (int i = 0; i < imageView.length; i++) {
			int temp = i;
			imageView[i] = new ImageView(new Image("/abstract/50.png", 400 / row_column, 400 / row_column, true, true));
			imageView[i].setOnMouseClicked(event -> {
				gameEngine.getFrontImage(imageView, temp, row_column);
			});
		}

		int index = 0;
		for (int i = 0; i < row_column; i++)
			for (int j = 0; j < row_column; j++) {

				tempCenter.add(imageView[index], j, i);
				index++;
			}
		return tempCenter;
	}

	public static void main(String[] args) {
		launch(args);
	}
}