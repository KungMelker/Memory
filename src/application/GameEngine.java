package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameEngine {

	Image cardImage[][] = new Image[2][2];
	ImageView imageView[][] = new ImageView[2][2];

	// method to be implemented in Card.java?
	// creates card with coordinates and image
	public Image[][] newCard(int x, int y) {
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				for (int j2 = 0; j2 < 50; j2++) {
					cardImage[i][j] = new Image("/images/" + j2 + ".jpg", 40, 40, true, true);
				}
		return cardImage;
	}

	// adds ImageView array and calls method to create cards
	// functionality to be implemented in Card.java?
	public ImageView[][] addView() {
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++) {
				// calls method to make new image
				imageView[i][j] = new ImageView(cardImage[i][j]);
				newCard(i, j);
			}
		return imageView;
	}


	// not used yet,
	public ImageView getImageAtPosition(int x, int y) {
		ImageView imageAtPosition = imageView[x][y];

		return imageAtPosition;
	}

	public void flipImage(int x, int y) {
		// Randomizing a new picture when flipped
		Random rand = new Random();
		//interval should be regulated depending on board size
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
	// if not same, wait X time and then turn them back, add "attempt" to score tracking
	// if same (couple), mark as done, add score,  exit method and restart it? let user click new card

}