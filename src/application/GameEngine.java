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
		cards = new Card[numCards / 2];
		takenCard = new int[numCards / 2];


		for (int i = 0; i < cards.length; i++) {
			cards[i] = new Card(i, 500 / row_column);
			takenCard[i] = 0;
		}

		for (int i = 0; i < tempIV.length; i++)
			tempIV[i] = new ImageView(cards[randomCard(cards.length)].getFront());

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

	Image getFrontImage(int index){
		
		return cards[index].getFront();
	}
}
