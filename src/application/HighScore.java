package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScore {

	GameEngine ge = new GameEngine();
	final String pathname = "/highscore.txt";
	String playerName = "Anonymous";
	int highscore = 0;
	long time = 0;
	int attempts;
	String addScore = "Name: " + playerName + " Score: " + highscore + " Time: " + time;

	// crap
	String[] scoreList = new String[10];
	// needed?
	String[] playerList = new String[10];
	String[] timeList = new String[10];

	
	// method tester
	public void score() {

		sortHighscores(openFile(pathname));
		time = ge.elapsedTime();
		attempts = ge.getTries();
		
		writeFile();
	}

	// creates a BufferedReader, reads highscore file
	public BufferedReader openFile(String path) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return br;
	}

	public void sortHighscores(BufferedReader instream) {

		// currently void, return score[] instead

		String scoreEntry = null;
		try {
			for (int j = 0; j < scoreList.length; j++) {
				scoreEntry = instream.readLine();
				scoreList[j] = scoreEntry;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// writes highscore to file
	public void writeFile() {
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(pathname));
			bw.write(addScore);
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
