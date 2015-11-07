
package application;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.Toggle;
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
 * @author Sam, Anton, Johan, Melker, Gustav, Simon. Creates the design of the
 *         application. Adds objects, texts, labels and radiobuttons.
 */
public class Main extends Application {

	private GameEngine gameEngine = new GameEngine();
	private GridPane centerBox = new GridPane();
	private HighScore hs = new HighScore();
	private int selectedCase;
	private int row_column;
	private ImageView imageView[];
	private boolean win = false;
	// VBox addBottomBox = new BottomBox();

	// needs to be global? Should be protected ints/longs etc with
	// getters/setters
	private Text presentTries = new Text();
	private Text pointresult = new Text();
	private Text time = new Text();
	private Text highpoint = new Text();
	private Button sQuit = new Button();
	private Button newGame = new Button();
	private BorderPane root = new BorderPane();

	@Override
	public void start(Stage primaryStage) {
		MenuBar menuBar = addMenuBar();
		// BorderPane root = new BorderPane();
		// Scene scene = new Scene(root, 1000, 700);
		Scene scene = new Scene(new VBox());
		((VBox) scene.getRoot()).getChildren().addAll(menuBar, root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);

		// topBox and titleBox
		HBox topBox = addTitleBox();
		VBox rightBox = addRight();
		VBox leftBox = addLeft();
		VBox progress = addBottomBox();

		ImageView splashImage = new ImageView(new Image("/images/splashimage.png", 400, 400, true, true));

		root.setCenter(splashImage);
		root.setTop(topBox);
		root.setRight(rightBox);
		root.setLeft(leftBox);
		root.setBottom(progress);

		// adds the action listeners
		addActionListenersToBottomButton();
		addGameBoardEvents();

		/**
		 * Adds the name of the application window.
		 */
		primaryStage.show();
		primaryStage.setTitle("Memory v0.3 for Dummies");
	}

	public static void main(String[] args) {
		launch(args);
	}

	// TODO
	// implement parts of it in game engine.
	private void addGameBoardEvents() {
		root.setOnMouseClicked(event -> {
			presentTries.setText(Integer.toString(gameEngine.getTries()));
			checkWin();
		});
	}

	private void checkWin() {

		if (!win) {
			if (gameEngine.getFoundPairs() == 0 && gameEngine.getStart() == 0) {
				gameEngine.startTime();
				pointresult.setText("0");
				time.setText("0");
			} else if (gameEngine.getFoundPairs() == (gameEngine.getCards().length / 2)) {

				gameEngine.checkTime();
				time.setText(Long.toString(gameEngine.timePlayed()) + " sec");
				pointresult
						.setText(Double.toString(gameEngine.calculateScore(row_column, gameEngine.getElapsedTime())));
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
	}

	private void addActionListenersToBottomButton() {
		newGame.setOnAction(event -> {
			selectBoardSize();
		});

		sQuit.setOnAction(event -> {
			hs.writeFile();
			System.exit(0);
		});
	}

	private void selectBoardSize() {
		centerBox.getChildren().clear();
		switch (row_column) {
		case 2:
			centerBox = center();
			selectedCase = 0;
			highpoint.setText(hs.getScore(0));
			break;

		case 4:
			centerBox = center();
			selectedCase = 1;
			highpoint.setText(hs.getScore(1));
			break;

		case 6:
			centerBox = center();
			selectedCase = 2;
			highpoint.setText(hs.getScore(2));
			break;

		case 8:
			centerBox = center();
			selectedCase = 3;
			highpoint.setText(hs.getScore(3));
			break;

		default:
			centerBox = center();
			selectedCase = 4;
			highpoint.setText(hs.getScore(4));
			break;
		}
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setId("centerBox");
		root.setCenter(centerBox);
		gameEngine.initBoard(row_column);
		gameEngine.setTries(0);
		gameEngine.setFoundPairs(0);
		gameEngine.setStart(0);
		gameEngine.setCurrentScore(0);
		win = false;
	}

	/**
	 * Adds a Text label indicating "Won" condition to the BorderPane's
	 * CenterBox
	 * 
	 * @see Text
	 */
	private void winText() {

		Label won = new Label("MERGE\nCOMPLETE");
		won.setId("win");
		// won.setRotate(30);
		won.setAlignment(Pos.BASELINE_CENTER);
		GridPane.setConstraints(won, 0, 0, 10, 10);
		centerBox.getChildren().add(won);
	}

	/**
	 * Returns a GridPane for the game board.
	 * <p>
	 * Returns a GridPane for the game board, that is then painted in the center
	 * of the BorderPane. The GridPane contains an ImageView array with the
	 * specified number of columns. The method fills the array with the
	 * requested number of images, and adds action listeners and event handlers.
	 * 
	 * @return GridPane
	 * @see GridPane
	 */
	private GridPane center() {

		GridPane tempCenter = new GridPane();

		/**
		 * Creates an ImageView array with the size the number of columns raised
		 * to the power of two.
		 * 
		 * @param row_column
		 * @see ImageView
		 */
		imageView = new ImageView[(int) Math.pow(row_column, 2)];

		for (int i = 0; i < imageView.length; i++) {
			int temp = i;
			imageView[i] = new ImageView(new Image("/images/50.png", 400 / row_column, 400 / row_column, true, true));
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

	// still not fully implemented

	private MenuBar addMenuBar() {
		MenuBar menuBar = new MenuBar();
		Menu menuGame = new Menu("Game");
		MenuItem newGa = new MenuItem("New Game");
		ToggleGroup boardSize = new ToggleGroup();
		RadioMenuItem menu2x2 = new RadioMenuItem("2x2");
		RadioMenuItem menu4x4 = new RadioMenuItem("4x4");
		RadioMenuItem menu6x6 = new RadioMenuItem("6x6");
		RadioMenuItem menu8x8 = new RadioMenuItem("8x8");
		RadioMenuItem menu10x10 = new RadioMenuItem("10x10");
		menu2x2.setToggleGroup(boardSize);
		menu4x4.setToggleGroup(boardSize);
		menu6x6.setToggleGroup(boardSize);
		menu8x8.setToggleGroup(boardSize);
		menu10x10.setToggleGroup(boardSize);

		boardSize.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
				// TODO Auto-generated method stub
				selectBoardSize();
			}
		});
		MenuItem exit = new MenuItem("Exit");
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				System.exit(0);
			}
		});
		menuGame.getItems().addAll(newGa, new SeparatorMenuItem(), menu2x2, menu4x4, menu6x6, menu8x8, menu10x10, exit);

		Menu menuSettings = new Menu("Settings");
		CheckMenuItem menuFullScreen = new CheckMenuItem("Fullscreen");
		CheckMenuItem menuSound = new CheckMenuItem("Sound");
		menuSettings.getItems().addAll(menuFullScreen, menuSound);
		menuFullScreen.setDisable(true);
		menuSound.setDisable(true);
		Menu menuHelp = new Menu("Help");

		menuBar.getMenus().addAll(menuGame, menuSettings, menuHelp);
		return menuBar;
	}

	private HBox addTitleBox() {
		// titleBox contains name, topBox contains titleBox and logos
		VBox titleBox = new VBox();
		titleBox.setAlignment(Pos.CENTER);
		// titleBox.setId("topbox");
		Text title = new Text("MEMORIES // LOST");
		Label subtitle = new Label(
				"Nightmares from Git: Curses by the Oracle - Mission to MERGE\nReturn of the Cannibal Pixel Demons");
		title.setId("game-title");
		subtitle.setId("game-subtitle");
		titleBox.setPadding(new Insets(0, 60, 0, 60));

		titleBox.getChildren().addAll(title, subtitle);

		HBox topBox = new HBox();
		ImageView logo0 = new ImageView(new Image("/images/sidelogo0.png", 100, 100, true, true));
		ImageView logo1 = new ImageView(new Image("/images/sidelogo1.png", 100, 100, true, true));

		topBox.getChildren().addAll(logo0, titleBox, logo1);
		topBox.setAlignment(Pos.CENTER);
		topBox.setPrefWidth(1000);
		topBox.setId("topbox");

		return topBox;
	}

	/**
	 * Adds a VBox to the left side of the application. Adds radiobuttons and
	 * labels.
	 */
	private VBox addLeft() {

		// leftBox

		VBox leftBox = new VBox(5);
		leftBox.setId("leftbox");
		Label memorySize = new Label("Estimated size\nof your\n memory:\n");
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

		leftBox.getChildren().addAll(memorySize, pairs_2, pairs_4, pairs_6, pairs_8, pairs_10);
		return leftBox;
	}

	/**
	 * Adds a VBox on the right side of the application. Adds labels highscore,
	 * point, time and tries.
	 */
	private VBox addRight() {
		// rightBox

		VBox rightBox = new VBox();
		rightBox.setId("rightbox");

		// stats.setAlignment(Pos.TOP_CENTER);
		Label highscore = new Label("HIGHSCORE");
		highpoint.setText("0");
		Label points = new Label("POINTS");
		pointresult.setText("0");
		Label timeLabel = new Label("TIME");
		time.setText("0");
		Label tries = new Label("TRIES");
		presentTries.setText("0");

		highpoint.setId("rightTexts");
		pointresult.setId("rightTexts");
		time.setId("rightTexts");
		presentTries.setId("rightTexts");

		rightBox.getChildren().addAll(highscore, highpoint, points, pointresult, timeLabel, time, tries, presentTries);

		return rightBox;
	}

	/**
	 * Adds a Hbox to the bottom of the application. Adds Buttons Rage Quit and
	 * New Game.
	 */
	private VBox addBottomBox() {
		// bottomBox

		VBox bottomBox = new VBox();
		HBox newsquitBox = new HBox(50);
		ProgressBar pb = new ProgressBar();
		pb.setId("progressbar");
		pb.setPrefHeight(40);
		pb.setPrefWidth(1000);

		bottomBox.setAlignment(Pos.BOTTOM_CENTER);

		newsquitBox.setAlignment(Pos.TOP_CENTER);
		newsquitBox.setPadding(new Insets(20));
		newsquitBox.setId("bottombox");
		sQuit.setText("Rage Quit");
		sQuit.setPrefWidth(150);
		sQuit.setId("QuitSave");
		// Button newGame = new Button("New Game");
		newGame.setText("NEW");
		newGame.setPrefWidth(150);
		newGame.setId("NewGame");

		newsquitBox.getChildren().addAll(newGame, sQuit);
		bottomBox.getChildren().addAll(pb, newsquitBox);
		return bottomBox;
	}
}