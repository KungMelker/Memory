package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class HighScore extends GameEngine {

	
	// Main appli = new Main();
	URL url = getClass().getResource("highscore.txt");
	
	
	final String pathname = url.getPath();
	double highscore = 0;
	long time = 0;
	int attempts = 0;
	int board = 0;
	String addScore;
	
	String[] scoreList = new String[10];

	// method tester
	
	
	
	 public void score(long timePlayed, int tries, double calculateScore) {

		this.time = timePlayed;
		this.attempts = tries;
		this.highscore = calculateScore;
		// board = appli.row_column;
		addScore = "Score: " + highscore + " Difficulty: " + " Time: " + time;

		BufferedReader test = openFile(pathname);
		sortHighscores(test);
		writeFile();
	}
	

	// creates a BufferedReader, reads highscore file
	public BufferedReader openFile(String path) {
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return br;
	}



	public String[] sortHighscores(BufferedReader instream) {
		// currently void, return score[] instead
		String scoreEntry = null;
		try {
			for (int j = 0; j < 10; j++) {
				scoreEntry = instream.readLine();
				scoreList[j] = scoreEntry;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return scoreList;
	}

	// writes highscore to file
	public void writeFile() {

		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(pathname));
			for (int i = 0; i < scoreList.length; i++) {
				bw.write(scoreList[i]);
				bw.write(System.lineSeparator());
			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


			
	
}
