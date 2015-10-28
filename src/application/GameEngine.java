package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameEngine {

	int firstCard;
	int secondCard;
	int numberOfPickedCards;
	int matchedPairs;
	int attempts;

	int row_column = 4;
	Image cardImage[] = new Image[row_column];
	ImageView imageView[][] = new ImageView[row_column][row_column];

	// adds ImageView array and calls method to create cards
	// functionality to be implemented in Card.java?
	public ImageView[][] addView() {
		for (int i = 0; i < row_column; i++)
			for (int j = 0; j < row_column; j++) {
				// calls method to make new image
				imageView[i][j] = new ImageView(newCard());
				// newCard(i, j);
			}
		return imageView;
	}

	// method to be implemented in Card.java?
	// creates card with coordinates and image
	public Image[] newCard() {
		for (int i = 0; i < 40; i++) {
			String randomImage = Integer.toString(i);
			cardImage[i] = new Image("/images/" + randomImage + ".jpg");
		}
		return cardImage;
	}

	// not used yet,
	public ImageView getImageAtPosition(int x, int y) {
		ImageView imageAtPosition = imageView[x][y];

		return imageAtPosition;
	}

	public void flipImage(int x, int y) {
		// Randomizing a new picture when flipped
		Random rand = new Random();
		// interval should be regulated depending on board size
		int randomImageValue = rand.nextInt(20);
		String randomImage = null;
		randomImage = Integer.toString(randomImageValue);
		imageView[x][y].setImage(new Image("/images/" + randomImage + ".jpg", 40, 40, true, true));
	}

	public void click(int x, int y) {
		imageView[x][y].setOnMouseClicked(event -> {
			// method call to engine flip, coordinates x,y
			flipImage(x, y);
		});
	}

	// needed?
	public void createCouples() {
	}

	// pseudo code && methods
	// create method that lets user click two cards,
	// if not same, wait X time and then turn them back, add "attempt" to score
	// tracking
	// if same (couple), mark as done, add score, exit method and restart it?
	// let user click new card

}