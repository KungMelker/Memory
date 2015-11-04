package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class HighScore {

	URL url = getClass().getResource("/savegame/highscore.txt");
	final String pathname = url.getPath();
	String[] scoreList = new String[5];

	/**
	 * Constructor for HighScore - runs method readFile
	 */
	public HighScore() {
		readFile();
	}

	/**
	 * Reads the file highscore.txt and populates the scoreList array
	 */
	public void readFile() {

		try (BufferedReader br = new BufferedReader(new FileReader(pathname));) {
			for (int i = 0; i < scoreList.length; i++) {
				scoreList[i] = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Writes scoreList to highscore.txt
	 */
	public void writeFile() {

		try (BufferedWriter bw = new BufferedWriter(new FileWriter(pathname));) {

			for (int i = 0; i < scoreList.length; i++) {
				bw.write(scoreList[i]);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
/**
 * Updates scoreList
 * @param score
 * 				new high score
 * @param board
 * 				the board at which the new high score should be set
 */
	void updateScore(double score, int board) {
		scoreList[board] = Double.toString(score);
	}

/**
 * Gets high score
 * @param board
 * 				the index of the board 
 * @return
 * 				the score at index board
 */
	String getScore(int board) {
		return scoreList[board];
	}
}
