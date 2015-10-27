package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameEngine {

	Image cardImage[][] = new Image[2][2];

	public Image[][] newCard() {
		//Image cardImage[][] = new Image[2][2];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				cardImage[i][j] = new Image("http://mobile-visuals.com/icon.png", 40, 40, true, true);
		return cardImage;
	}

	public ImageView[][] addView() {
		ImageView imageView[][] = new ImageView[2][2];
		for (int i = 0; i < 2; i++)
			for (int j = 0; j < 2; j++)
				// kalla på funktion för get image
				imageView[i][j] = new ImageView(cardImage[i][j]);
		return imageView;
	}

	// for (int i = 0; i < 2; i++)
	// for (int j = 0; j < 2; j++)
	// centerBox.addRow(i, imageView[i][j]);
}
