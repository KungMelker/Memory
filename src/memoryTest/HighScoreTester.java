package memoryTest;

import org.junit.Test;

import application.HighScore;

public class HighScoreTester {

	HighScore hs = new HighScore();

	@Test(timeout = 10)
	public void testreadFile() {
		hs.readFile();
	}
}
