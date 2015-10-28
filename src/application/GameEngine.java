package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameEngine {

	Card cards[];
	int takenCard[];
	int numCards;
	Random rand = new Random();

	ImageView[] initBoard(int row_column) {

		numCards = (int) Math.pow(row_column, 2);

		ImageView tempIV[] = new ImageView[numCards];
		cards = new Card[numCards];

		Card cardsTemp[] = new Card[numCards / 2];
		takenCard = new int[numCards / 2];

		for (int i = 0; i < cardsTemp.length; i++) {
			cardsTemp[i] = new Card(i, 500 / row_column);
			takenCard[i] = 0;
		}
		int index;
		for (int i = 0; i < tempIV.length; i++) {
			index = randomCard(cardsTemp.length);
			tempIV[i] = new ImageView(cardsTemp[index].getFront());
			cards[i] = cardsTemp[index];
		}

		return tempIV;
	}

	int randomCard(int max) {
		int index = 0;
		boolean foundFree = false;

		while (!foundFree) {
			index = rand.nextInt(max);
			if (takenCard[index] < 2) {
				takenCard[index]++;
				foundFree = true;
			}
		}

		return index;
	}

	Image getFrontImage(int index) {

		return cards[index].getFront();
	}
}
