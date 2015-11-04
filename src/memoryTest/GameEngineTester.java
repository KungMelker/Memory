package memoryTest;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.Test;

import application.GameEngine;


//import static org.junit.Assert.*;

//import org.junit.Test;

public class GameEngineTester {

	
	
	@Test(timeout=10)
	public void testcalculateScore() {
		GameEngine ge = new GameEngine();
		ge.calculateScore(8, 200000);
	}

	@Test (expected = NullPointerException.class)
	public void testNullCalculateScore() {
		GameEngine ge = new GameEngine();
		ge.calculateScore(8, 0);
	}
	
	@Test (expected = ArithmeticException.class)
	public void testDivZeroCalculateScore() {
		GameEngine ge = new GameEngine();
		ge.calculateScore(8, 0);
	}
	
	@Test (expected = NullPointerException.class)
	public void testTimeRandomCard(){
		GameEngine ge = new GameEngine();
		ge.randomCard(2);
	}
	
}
