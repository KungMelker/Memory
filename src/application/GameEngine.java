
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
	double currentScore;
	long start, stop, elapsedTime;
	int tries = 0;
	int foundPairs = 0;
	
	Random rand = new Random();

	public double getCurrentScore() {
		return currentScore;
	}

	public void setCurrentScore(double currentScore) {
		this.currentScore = currentScore;
	}

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

	// ---< end of getters and setters >---

	/**
	 * Calculates score from time and tries. Takes in value from row_column to
	 * calculate handicap and score.
	 * 
	 * @param row_column
	 * @param eTime
	 * @return
	 */

	double calculateScore(int row_column, long eTime) {

		int decimals = 2;
		double evener = 100 - row_column * row_column;

		double temp_score = ((20000 / (((eTime / 1000)) + tries + evener)));

		BigDecimal score = new BigDecimal(temp_score);

		score = score.setScale(decimals, RoundingMode.HALF_UP);
		currentScore = score.doubleValue();

		return currentScore;

	}

	/**
	 * Takes in currScore and hiScore and test if currScore is bigger than
	 * hiScore. returns true or false
	 * 
	 * @param currScore
	 * @param hiScore
	 * @return boolean
	 */

	boolean compareScore(double currScore, double hiScore) {
		if (currScore > hiScore) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Save the current time in milliseconds from the System.class when mouse is
	 * clicked and sets that value to the variable start.
	 * 
	 * @return number of milliseconds.
	 */
	long startTime() {
		start = System.currentTimeMillis();
		return start;
	}

	/**
	 * Save the current time in milliseconds and sets that value to the variable
	 * stop.
	 * 
	 * @return number of milliseconds.
	 */
	long checkTime() {
		stop = System.currentTimeMillis();
		return stop;
	}

	/**
	 * The variable elapsedTime gets the difference value from subtracting the
	 * variables stop and start. elapsedTime is then divided by 1000 to return
	 * its value in seconds and not milliseconds.
	 * 
	 * @return the difference of stop and start in elapsedTime.
	 */
	long timePlayed() {
		elapsedTime = stop - start;
		return (long) elapsedTime / 1000;
	}

	/**
	 * Sets size of bord takes param row_column to set the bord to right size.
	 * 
	 * @param row_column
	 */

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

	/**
	 * takes max value that is length of array cards. and returns index of the
	 * random taken card.
	 * 
	 * @param max
	 * @return index.
	 * 
	 */

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

	/**
	 * compare cards to see if they are a matching pair.
	 * 
	 * @return true if match / false if not.
	 */

	boolean compareCards() {

		return (cards[pairToCompare[0]].getValue() == cards[pairToCompare[1]].getValue() ? true : false);
	}

	/**
	 * Schows the right backside of the cards and fade away wrong pairs.
	 * 
	 * @param ivArr
	 * @param index
	 * @param row_column
	 */

	void getFrontImage(ImageView ivArr[], int index, int row_column) {

		SequentialTransition transitionCard;

		if (pairToCompare[0] == -1 ) {

			pairToCompare[0] = index;
			ivArr[index].setImage(cards[index].getFront());
			clickMax--;

		} else if (index != pairToCompare[0] ) {

			pairToCompare[1] = index;
			ivArr[index].setImage(cards[index].getFront());

			if (compareCards()) {

				ivArr[pairToCompare[0]].setDisable(true);
				ivArr[pairToCompare[1]].setDisable(true);
				foundPairs++;
			
			
			} else {

				transitionCard = createTransition(ivArr[pairToCompare[0]],
						new Image("/images/50.png", 400 / row_column, 400 / row_column, true, true));
				transitionCard.play();
				transitionCard = createTransition(ivArr[pairToCompare[1]],
						new Image("/images/50.png", 400 / row_column, 400 / row_column, true, true));
				transitionCard.play();
				
			}

			tries++;

			pairToCompare[0] = -1;
			pairToCompare[1] = -1;

		}

	}

	/**
	 * Shows the cards for one seconds then fade out.
	 * 
	 * @param iv
	 * @param img
	 * @return
	 */
	SequentialTransition createTransition(ImageView iv, Image img) {
		FadeTransition fadeOutTransition = new FadeTransition(Duration.millis(300), iv);
		fadeOutTransition.setFromValue(1.0);
		fadeOutTransition.setToValue(0.0);
		fadeOutTransition.setOnFinished(event -> {
			iv.setImage(img);
		});

		PauseTransition pt = new PauseTransition(Duration.millis(1000));

		FadeTransition fadeInTransition = new FadeTransition(Duration.millis(300), iv);
		fadeInTransition.setFromValue(0.0);
		fadeInTransition.setToValue(1.0);
		SequentialTransition sequentialTransition = new SequentialTransition(pt, fadeOutTransition, fadeInTransition);

		return sequentialTransition;
	}
}
