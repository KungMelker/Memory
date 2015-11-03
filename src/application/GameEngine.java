
package application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class GameEngine {

	Card cards[];
	int takenCard[];
	int pairToCompare[] = { -1, -1 };

	long start, stop, elapsedTime;
	int tries = 0;
	int foundPairs = 0;
	Random rand = new Random();

	public long getStop() {
		return stop;
	}

	public void setStop(long stop) {
		this.stop = stop;
	}

	public long getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public Card[] getCards() {
		return cards;
	}

	public int getFoundPairs() {
		return foundPairs;
	}

	public void setFoundPairs(int foundPairs) {
		this.foundPairs = foundPairs;
	}

	public int getTries() {
		return tries;
	}

	public void setTries(int tries) {
		this.tries = tries;
	}

	// -----------------------< end of getters and setters
	// >------------------------------------

	/*
	 * Calculate score from time and tries takes in value from row column to
	 * calculate handicap and score.
	 */

	double calculateScore(int row_column, long timePlayed) {
		int decimals = 2;
		double evener = 100 - row_column * row_column;
		double temp_score = ((20000 / ((timePlayed) + tries + evener)));

		BigDecimal score = new BigDecimal(temp_score);
		score = score.setScale(decimals, RoundingMode.HALF_UP);

		return score.doubleValue();

	}

	long startTime() {
		start = System.currentTimeMillis();
		return start;
	}

	long stopTime() {
		stop = System.currentTimeMillis();
		return stop;
	}

	long timePlayed() {
		elapsedTime = stop - start;
		return (long) elapsedTime / 1000;
	}

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

		SequentialTransition transitionCard;
		// SequentialTransition transitionSecondCard;

		if (pairToCompare[0] == -1) {

			pairToCompare[0] = index;
			ivArr[index].setImage(cards[index].getFront());

		} else {

			pairToCompare[1] = index;
			ivArr[index].setImage(cards[index].getFront());

			if (compareCards()) {

				ivArr[pairToCompare[0]].setDisable(true);
				ivArr[pairToCompare[1]].setDisable(true);
				foundPairs++;
			} else {

				transitionCard = createTransition(ivArr[pairToCompare[0]],
						new Image("/abstract/50.png", 400 / row_column, 400 / row_column, true, true));
				transitionCard.play();
				transitionCard = createTransition(ivArr[pairToCompare[1]],
						new Image("/abstract/50.png", 400 / row_column, 400 / row_column, true, true));
				transitionCard.play();
			}

			tries++;
			pairToCompare[0] = -1;
			pairToCompare[1] = -1;

		}

	}

	SequentialTransition createTransition(ImageView iv, Image img) {
		FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(500), iv);
		fadeOutTransition.setFromValue(1.0);
		fadeOutTransition.setToValue(0.0);
		fadeOutTransition.setOnFinished(event -> {
			iv.setImage(img);
		});

		PauseTransition pt = new PauseTransition(Duration.millis(1000));

		FadeTransition fadeInTransition = new FadeTransition(Duration.millis(500), iv);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);
		SequentialTransition sequentialTransition = new SequentialTransition(pt, fadeOutTransition, fadeInTransition);

		return sequentialTransition;
	}
}
