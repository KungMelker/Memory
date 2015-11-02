package application;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GameEngine {

	Card cards[];
	int takenCard[];
	int pairToCompare[] = { -1, -1 };
	Random rand = new Random();

	void initBoard(int row_column) {

		int numCards = (int) Math.pow(row_column, 2);
		cards = new Card[numCards];
		Card cardsTemp[] = new Card[numCards / 2];
		takenCard = new int[numCards / 2];

		for (int i = 0; i < cardsTemp.length; i++) {
			cardsTemp[i] = new Card(i, 400 / row_column);
			takenCard[i] = 0;
		}

		for (int i = 0; i < cards.length; i++) {

			cards[i] = cardsTemp[randomCard(cardsTemp.length)];
		}
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

	boolean compareCards() {

		return (cards[pairToCompare[0]].getValue() == cards[pairToCompare[1]].getValue() ? true : false);
	}

	void getFrontImage(ImageView ivArr[], int index, int row_column) {

		if (pairToCompare[1] != -1) {
			if (compareCards()) {

				ivArr[pairToCompare[0]].setDisable(true);
				ivArr[pairToCompare[1]].setDisable(true);

			} else {

				ivArr[pairToCompare[0]]
						.setImage(new Image("/images/49.jpg", 400 / row_column, 400 / row_column, true, true));
				ivArr[pairToCompare[1]]
						.setImage(new Image("/images/49.jpg", 400 / row_column, 400 / row_column, true, true));
			}

			pairToCompare[0] = -1;
			pairToCompare[1] = -1;
		}

		if (pairToCompare[0] == -1) {

			pairToCompare[0] = index;
			ivArr[index].setImage(cards[index].getFront());

		} else {

			pairToCompare[1] = index;
			ivArr[index].setImage(cards[index].getFront());

		}
	}
}
