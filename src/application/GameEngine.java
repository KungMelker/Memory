package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameEngine {

	Image cardImage[][] = new Image[2][2];
	ImageView imageView[][] = new ImageView[2][2];

	// method to be implemented in Card.java
	// creates card with coordinates and image
	public Image[][] newCard(int x, int y) {
		// Image cardImage[][] = new Image[2][2];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				cardImage[i][j] = new Image("http://mobile-visuals.com/icon.png", 40, 40, true, true);
		return cardImage;
	}

	// adds imageview array and calls method to create cards
	public ImageView[][] addView() {
		// ImageView imageView[][] = new ImageView[2][2];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++) {
				// kalla på funktion för get image
				imageView[i][j] = new ImageView(cardImage[i][j]);
				newCard(i, j);
			}
		return imageView;
	}

	public ImageView getImageAtPosition(int x, int y) {
		ImageView imageAtPosition = imageView[x][y];

		return imageAtPosition;
	}

	public void flipImage(int x, int y) {
				getImageAtPosition(x, y).setImage(new Image("http://vk.com/images/gifts/256/44.jpg"));
				

	}

}