package application;

import java.util.Random;

import javafx.scene.image.Image;

public class GameEngine {

	Card cards[];
	int takenCard[];
	int numCards;
	Random rand = new Random();

	void initBoard(int row_column) {

		numCards = (int) Math.pow(row_column, 2);
		cards = new Card[numCards];
		Card cardsTemp[] = new Card[numCards / 2];
		takenCard = new int[numCards / 2];

		for (int i = 0; i < cardsTemp.length; i++) {
			cardsTemp[i] = new Card(i, 500 / row_column);
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

	boolean compareCards(int card1, int card2) {

		return (cards[card1].getValue() == cards[card2].getValue() ? true : false);
	}

	Image getFrontImage(int index) {

		return cards[index].getFront();
	}
}
